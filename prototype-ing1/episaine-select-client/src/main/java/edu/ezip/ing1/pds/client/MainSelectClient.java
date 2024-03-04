package edu.ezip.ing1.pds.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import de.vandermeer.asciitable.AsciiTable;
import edu.ezip.commons.LoggingUtils;
import edu.ezip.ing1.pds.business.dto.Client;
import edu.ezip.ing1.pds.business.dto.Clients;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.UUID;

public class MainSelectClient {

    private final static String LoggingLabel = "I n s e r t e r - C l i e n t";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
    private final static String networkConfigFile = "network.yaml";
    private static final String requestOrder = "SELECT_ALL_CLIENTS";
    private static final Deque<ClientRequest> clientRequests = new ArrayDeque<ClientRequest>();

    public static void main(String[] args) throws IOException, InterruptedException, SQLException {

        final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
        logger.debug("Load Network config file : {}", networkConfig.toString());

        int birthdate = 0;
        final ObjectMapper objectMapper = new ObjectMapper();

        final String requestId = UUID.randomUUID().toString();
        final Request request = new Request();
        request.setRequestId(requestId);
        request.setRequestOrder(requestOrder);

        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final byte [] requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);
        LoggingUtils.logDataMultiLine(logger, Level.TRACE, requestBytes);
        final SelectAllClientRequest clientRequest = new SelectAllClientRequest(
                                                                    networkConfig,
                                                                    birthdate++, request, null, requestBytes);
        clientRequests.push(clientRequest);

        while (!clientRequests.isEmpty()) {
            final ClientRequest joinedClientRequest = clientRequests.pop();
            joinedClientRequest.join();
            logger.debug("Thread {} complete.", joinedClientRequest.getThreadName());
            try {
            final Clients clients = (Clients) joinedClientRequest.getResult();
            final AsciiTable asciiTable = new AsciiTable();
            ArrayList<String[]> clientList = new ArrayList<>();
            for (final Client client: clients.getClients()) {
                // TODO : trouver une méthode moins barbare pour faire ça
                String[] clientLine = new String[12];
                clientLine[0] = client.getId_Clients();
                clientLine[1] = client.getNom_Client();
                clientLine[2] = client.getPrenom_Client();
                clientLine[3] = client.getDate_de_naissance_Client();
                clientLine[4] = client.getPoids();
                clientLine[5] = client.getGenre();
                clientLine[6] = client.getTaille();
                clientLine[7] = client.getNumero_de_telephone_Client();
                clientLine[8] = client.getMail_Client();
                clientLine[9] = client.getVille();
                clientLine[10] = client.getAdresse();
                clientLine[11] = client.getCode_Postal_();

                clientList.add(clientLine);

                asciiTable.addRule();
                asciiTable.addRow(client.getId_Clients(), client.getNom_Client(), client.getPrenom_Client(), client.getDate_de_naissance_Client(), 
                                  client.getPoids(), client.getGenre(), client.getTaille(), client.getNumero_de_telephone_Client(),
                                  client.getMail_Client(), client.getVille(), client.getAdresse(), client.getCode_Postal_());
            }
            asciiTable.addRule();
            
            StringAsciiTable.setClientList(clientList);
            
            logger.debug("\n{}\n", asciiTable.render());
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
