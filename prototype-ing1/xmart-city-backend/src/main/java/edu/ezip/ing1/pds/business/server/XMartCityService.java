package edu.ezip.ing1.pds.business.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.Student;
import edu.ezip.ing1.pds.business.dto.Students;
import edu.ezip.ing1.pds.business.dto.Client;
import edu.ezip.ing1.pds.business.dto.Clients;
import edu.ezip.ing1.pds.commons.Request;
import edu.ezip.ing1.pds.commons.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.*;

public class XMartCityService {

    private final static String LoggingLabel = "B u s i n e s s - S e r v e r";
    private final Logger logger = LoggerFactory.getLogger(LoggingLabel);

    private enum Queries {
        SELECT_ALL_STUDENTS("SELECT t.name, t.firstname, t.group FROM \"ezip-ing1\".students t"),
        SELECT_ALL_CLIENTS("SELECT t.nom_client, t.prenom_client, t.date_de_naissance_client, t.poids, t.genre, t.taille, t.numero_de_telephone_client, t.mail_client, t.ville, t.adresse, t.code_postal FROM \"episaine-schema\".clients t"),

        INSERT_STUDENT("INSERT into \"ezip-ing1\".students (\"name\", \"firstname\", \"group\") values (?, ?, ?)"),
        INSERT_CLIENT("INSERT into \"episaine-schema\".clients (\"nom_client\", \"prenom_client\", \"date_de_naissance_client\", \"poids\", \"genre\", \"taille\", \"numero_de_telephone_client\", \"mail_client\", \"ville\", \"adresse\", \"code_postal\") values (?,?,?,?,?,?,?,?,?,?,?)");
        private final String query;

        private Queries(final String query) {
            this.query = query;
        }
    }

    public static XMartCityService inst = null;
    public static final XMartCityService getInstance()  {
        if(inst == null) {
            inst = new XMartCityService();
        }
        return inst;
    }

    private XMartCityService() {

    }

    public final Response dispatch(final Request request, final Connection connection)
            throws InvocationTargetException, IllegalAccessException {
            Response response = null;
        
        PreparedStatement pstmt;
        Statement stmt;
        ResultSet res;
        ObjectMapper mapper;
        int rows;
        try {
            switch (request.getRequestOrder()) {
                // Premier essai avec la bdd de test, inutile maintenant mais on garde temporairement pour l'exemple
                case "SELECT_ALL_STUDENTS":
                    stmt = connection.createStatement();
                    res = stmt.executeQuery(Queries.SELECT_ALL_STUDENTS.query);
                    Students students = new Students();
                    while (res.next()) {
                        Student student = new Student().build(res);
                        students.add(student);
                    }
                    mapper = new ObjectMapper();

                    response = new Response();
                    response.setRequestId(request.getRequestId());
                    response.setResponseBody(mapper.writeValueAsString(students));
                    break;

                case "INSERT_STUDENT" :
                    mapper = new ObjectMapper();
                    Student student = mapper.readValue(request.getRequestBody(), Student.class);
                    pstmt = connection.prepareStatement(Queries.INSERT_STUDENT.query);
                    pstmt.setString(1, student.getName());
                    pstmt.setString(2, student.getFirstname());
                    pstmt.setString(3, student.getGroup());
                    rows = pstmt.executeUpdate();

                    response = new Response();
                    response.setRequestId(request.getRequestId());
                    response.setResponseBody("{\"student_id\": " + rows + " }");
                    break;
                // ------------------

                case "SELECT_ALL_CLIENTS" :
                    stmt = connection.createStatement();
                    res = stmt.executeQuery(Queries.SELECT_ALL_CLIENTS.query);
                    Clients clients = new Clients();
                    while (res.next()) {
                    Client client = new Client().build(res);
                        clients.add(client);
                    }
                    mapper = new ObjectMapper();

                    response = new Response();
                    response.setRequestId(request.getRequestId());
                    response.setResponseBody(mapper.writeValueAsString(clients));
                    break;

                case "INSERT_CLIENT" : 
                    mapper = new ObjectMapper();
                    Client client = mapper.readValue(request.getRequestBody(), Client.class);
                    pstmt = connection.prepareStatement(Queries.INSERT_CLIENT.query);
                    pstmt.setString(1, client.getNomClient());
                    pstmt.setString(2, client.getPrenomClient());
                    Date date = Date.valueOf(client.getDateDeNaissanceClient());
                    pstmt.setDate(3, date);
                    pstmt.setBigDecimal(4, new BigDecimal(client.getPoids()));
                    pstmt.setString(5, client.getGenre());
                    pstmt.setInt(6, Integer.parseInt(client.getTaille()));
                    pstmt.setString(7, client.getNumero_de_telephone_Client());
                    pstmt.setString(8, client.getMail_Client());
                    pstmt.setString(9, client.getVille());
                    pstmt.setString(10, client.getAdresse());
                    pstmt.setString(11, client.getCode_Postal());
                    rows = pstmt.executeUpdate();

                    response = new Response();
                    response.setRequestId(request.getRequestId());
                    response.setResponseBody("{\"id_client\": " + rows + " }");
                    break;

                default:
                    break;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
