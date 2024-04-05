package edu.ezip.ing1.pds.business.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.Student;
import edu.ezip.ing1.pds.business.dto.Students;
import edu.ezip.ing1.pds.business.dto.Update;
import edu.ezip.ing1.pds.business.dto.Client;
import edu.ezip.ing1.pds.business.dto.Clients;
import edu.ezip.ing1.pds.business.dto.Recette;
import edu.ezip.ing1.pds.business.dto.Recettes;

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
            SELECT_ALL_CLIENTS("SELECT t.nom_client, t.prenom_client, t.date_de_naissance_client, t.poids, t.genre, t.taille, t.numero_de_telephone_client, t.mail_client, t.ville, t.adresse, t.code_postal FROM \"episaine-schema\".clients t"),
            SELECT_ALL_RECETTES("SELECT t.nom_recette, t.nombre_de_calories, t.ingredients, t.instructions, t.regimealimentaire FROM \"episaine-schema\".recettes t"),
            SELECT_ALL_INFORMATIONS("SELECT t.id_Client, t.but, t.allergie, t.nbDeRepas FROM \"episaine-schema\".informations t"),
        
            INSERT_CLIENT("INSERT into \"episaine-schema\".clients (\"nom_client\", \"prenom_client\", \"date_de_naissance_client\", \"poids\", \"genre\", \"taille\", \"numero_de_telephone_client\", \"mail_client\", \"ville\", \"adresse\", \"code_postal\") values (?,?,?,?,?,?,?,?,?,?,?)"),
            INSERT_RECETTE("INSERT INTO \"episaine-schema\".recettes (\"nom_recette\", \"nombre_de_calories\", \"ingredients\", \"instructions\", \"regimealimentaire\", \"id_nutritionniste\") VALUES (?, ?, ?, ?, ?, ?)"),
            INSERT_INFORMATION("INSERT INTO \"episaine-schema\".informations (\"id_client\",\"but\", \"allergie\", \"nbderepas\") VALUES (?,?,?,?)"),
        
            DELETE_CLIENT("DELETE FROM \"episaine-schema\".clients WHERE \"id_client\" = ? "),
            DELETE_RECETTES("DELETE FROM \"episaine-schema\".recettes WHERE \"id_recettes\" = ?"),
            DELETE_INFORMATION("DELETE FROM \"episaine-schema\".informations WHERE \"id_info\" = ?");
        private final String query;

        private Queries(String query) {
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
                case "UPDATE_CLIENT":
                    mapper = new ObjectMapper();
                    Update update = (Update)mapper.readValue(request.getRequestBody(), Update.class);
                    pstmt = connection.prepareStatement("UPDATE \"episaine-schema\".clients SET " + update.getNewColumn() + "= ? WHERE " + update.getConditionColumn() + "= ?");
                    switch (update.getNewColumn()) {
                       case "id_client":
                          pstmt.setInt(1, Integer.parseInt(update.getNewValue()));
                          break;
                       case "Date de naissance":
                          pstmt.setDate(1, Date.valueOf(update.getNewValue()));
                          break;
                       case "Poids (en kg)":
                          pstmt.setBigDecimal(1, new BigDecimal(update.getNewValue()));
                          break;
                       case "Taille":
                          pstmt.setInt(1, Integer.parseInt(update.getNewValue()));
                          break;
                       default:
                          pstmt.setString(1, update.getNewValue());
                          break;
                    }
     
                    switch (update.getConditionColumn()) {
                       case "id_client":
                          pstmt.setInt(2, Integer.parseInt(update.getConditionValue()));
                          break;
                       case "Date de naissance":
                          pstmt.setDate(2, Date.valueOf(update.getConditionValue()));
                          break;
                       case "Poids (en kg)":
                          pstmt.setBigDecimal(2, new BigDecimal(update.getConditionValue()));
                          break;
                       case "Taille":
                          pstmt.setInt(2, Integer.parseInt(update.getConditionValue()));
                          break;
                       default:
                          pstmt.setString(2, update.getConditionValue());
                          break;
                    }
     
                    rows = pstmt.executeUpdate();
                    response = new Response();
                    response.setRequestId(request.getRequestId());
                    response.setResponseBody("{\"update\": " + rows + " }");
                    break;

                case "DELETE_CLIENT":
                    pstmt = connection.prepareStatement(Queries.DELETE_CLIENT.query);
                    pstmt.setInt(1, Integer.parseInt(request.getRequestBody()));
                    rows = pstmt.executeUpdate();
                    response = new Response();
                    response.setRequestId(request.getRequestId());
                    response.setResponseBody("{\"delete\": " + rows + " }");
                    break;
                 
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
                    pstmt.setDate(3, client.getDateDeNaissanceClient());
                    pstmt.setBigDecimal(4, client.getPoids());
                    pstmt.setString(5, client.getGenre());
                    pstmt.setInt(6, client.getTaille());
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

                    case "SELECT_ALL_RECETTES" :
                        stmt = connection.createStatement();
                        res = stmt.executeQuery(Queries.SELECT_ALL_RECETTES.query);
                        Recettes recettes = new Recettes();
                        while (res.next()) {
                            Recette recette = new Recette().build(res);
                            recettes.add(recette);
                        }
                        mapper = new ObjectMapper();

                        response = new Response();
                        response.setRequestId(request.getRequestId());
                        response.setResponseBody(mapper.writeValueAsString(recettes));
                        break;

                    case "INSERT_RECETTE" :
                        mapper = new ObjectMapper();
                        Recette recette = mapper.readValue(request.getRequestBody(), Recette.class);
                        pstmt = connection.prepareStatement(Queries.INSERT_RECETTE.query);
                        pstmt.setString(1, recette.getNom_Recette());
                        pstmt.setInt(2, recette.getNombre_de_Calories());
                        pstmt.setString(3, recette.getIngredients());
                        pstmt.setString(4, recette.getInstructions());
                        pstmt.setString(5, recette.getRegimeAlimentaire());
                        pstmt.setInt(6, recette.getId_nutritionniste());
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
