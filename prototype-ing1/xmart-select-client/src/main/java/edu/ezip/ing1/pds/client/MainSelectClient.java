package edu.ezip.ing1.pds.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import de.vandermeer.asciitable.AsciiTable;
import edu.ezip.commons.LoggingUtils;
import edu.ezip.commons.connectionpool.config.impl.ConnectionPoolImpl;
import edu.ezip.ing1.pds.business.dto.Student;
import edu.ezip.ing1.pds.business.dto.Students;
import edu.ezip.ing1.pds.business.server.XMartCityService;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;
import edu.ezip.ing1.pds.commons.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.stringtemplate.v4.ST;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.UUID;

public class MainSelectClient {

    private final static String LoggingLabel = "I n s e r t e r - C l i e n t";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
    private final static String networkConfigFile = "network.yaml";
    private static final String requestOrder = "SELECT_ALL_STUDENTS";
    private static final Deque<ClientRequest> clientRequests = new ArrayDeque<ClientRequest>();

    public static void main(String[] args) throws IOException, InterruptedException, SQLException {

        final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile); // Fichier contenant l'ip et le port de la BDD
        logger.debug("Load Network config file : {}", networkConfig.toString());

        int birthdate = 0;
        final ObjectMapper objectMapper = new ObjectMapper();

        final String requestId = UUID.randomUUID().toString();  // ID random pour requestId
        final Request request = new Request();
        request.setRequestId(requestId);                        // On associe a la request son Id
        request.setRequestOrder(requestOrder);                  // Order de la request, ici "SELECT_ALL_STUDENTS"

        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final byte [] requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);
        LoggingUtils.logDataMultiLine(logger, Level.TRACE, requestBytes);
        final SelectAllStudentsClientRequest clientRequest = new SelectAllStudentsClientRequest(
                                                                    networkConfig,
                                                                    birthdate++, request, null, requestBytes);
        clientRequests.push(clientRequest);

        XMartCityService xmartCityService = XMartCityService.getInstance();
        ConnectionPoolImpl connectionPoolImpl = ConnectionPoolImpl.getInstance("postgresql");
        Connection connection = connectionPoolImpl.get();
        
        while (!clientRequests.isEmpty()) {
            final ClientRequest joinedClientRequest = clientRequests.pop();
            joinedClientRequest.join();
            logger.debug("Thread {} complete.", joinedClientRequest.getThreadName());
            //final Students students = (Students) joinedClientRequest.getResult();
            try {
            final Students students = arrayToStudents(getResp(request,xmartCityService,connection));
            final AsciiTable asciiTable = new AsciiTable();
            for (final Student student : students.getStudents()) {
                asciiTable.addRule();
                asciiTable.addRow(student.getFirstname(), student.getName(), student.getGroup());
            }
            asciiTable.addRule();
            logger.debug("\n{}\n", asciiTable.render());
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static ArrayList<String> getResp(Request request, XMartCityService xmartCityService, Connection connection) {
        ArrayList<String> resList = new ArrayList<String>();
        try{
            Response res = xmartCityService.dispatch(request, connection);
            String[] s = res.getResponseBody().split("\n");
            for (String l : s) {
                System.out.println(l);
            }
            resList = new ArrayList<String>(Arrays.asList(s));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return resList;
    }

    private static Students arrayToStudents(ArrayList<String> l) {
        Students students = new Students();
        for (String s : l) {
            String name = s.substring(0, s.indexOf(","));
            String firstname = s.substring(s.indexOf(",")+1, s.indexOf(";"));
            String group = s.substring(s.indexOf(";")+1, s.length());
            Student student = new Student(name,firstname,group);
            students.add(student);
        }
        return students;
    }
}
