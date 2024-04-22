package edu.ezip.ing1.pds.business.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.Update;
import edu.ezip.ing1.pds.business.dto.Client;
import edu.ezip.ing1.pds.business.dto.Clients;
import edu.ezip.ing1.pds.business.dto.Information;
import edu.ezip.ing1.pds.business.dto.Informations;
import edu.ezip.ing1.pds.business.dto.Nutritionniste;
import edu.ezip.ing1.pds.business.dto.Nutritionnistes;
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
            SELECT_ALL_CLIENTS("SELECT t.id_client, t.nom_client, t.prenom_client, t.date_de_naissance_client, t.poids, t.genre, t.taille, t.numero_de_telephone_client, t.mail_client, t.ville, t.adresse, t.code_postal FROM \"episaine-schema\".clients t"),
            SELECT_ALL_RECETTES("SELECT t.id_recette, t.nom_recette, t.nombre_de_calories, t.ingredients, t.instructions, t.regimealimentaire FROM \"episaine-schema\".recettes t"),
            SELECT_ALL_INFORMATIONS("SELECT t.id_info, t.id_client, t.but, t.allergie, t.nbderepas FROM \"episaine-schema\".informations t"),
            SELECT_ALL_NUTRITIONNISTES("SELECT t.id_nutritionniste, t.nom_n, t.prenom_n, t.numero_de_telephone_n, t.mail_n FROM \"episaine-schema\".nutritionnistes t"),
            
            INSERT_CLIENT("INSERT into \"episaine-schema\".clients (\"nom_client\", \"prenom_client\", \"date_de_naissance_client\", \"poids\", \"genre\", \"taille\", \"numero_de_telephone_client\", \"mail_client\", \"ville\", \"adresse\", \"code_postal\") values (?,?,?,?,?,?,?,?,?,?,?)"),
            INSERT_RECETTE("INSERT INTO \"episaine-schema\".recettes (\"nom_recette\", \"nombre_de_calories\", \"ingredients\", \"instructions\", \"regimealimentaire\", \"id_nutritionniste\") VALUES (?, ?, ?, ?, ?, ?)"),
            INSERT_INFORMATION("INSERT INTO \"episaine-schema\".informations (\"id_client\", \"but\", \"allergie\", \"nbderepas\") VALUES (?,?,?,?)"),
            INSERT_NUTRITIONNISTE("INSERT INTO \"episaine-schema\".nutritionnistes (\"nom_n\", \"prenom_n\", \"numero_de_telephone_n\", \"mail_n\") VALUES (?,?,?,?)"),

            DELETE_CLIENT("DELETE FROM \"episaine-schema\".clients WHERE \"id_client\" = ? "),
            DELETE_RECETTE("DELETE FROM \"episaine-schema\".recettes WHERE \"id_recettes\" = ?"),
            DELETE_INFORMATION("DELETE FROM \"episaine-schema\".informations WHERE \"id_info\" = ?"),
            DELETE_NUTRITIONNISTE("DELETE FROM \"episaine-schema\".nutritionnistes WHERE \"id_nitritionniste\" = ? "),
            ;
        
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
        Update update;

        try {
            switch (request.getRequestOrder()) {
                
                case "SELECT_ALL_CLIENTS" :
                logger.info("requestOrder : " + request.getRequestOrder());
                    stmt = connection.createStatement();
                    res = stmt.executeQuery(Queries.SELECT_ALL_CLIENTS.query);
                    Clients clients = new Clients();
                    while (res.next()) {
                        Client client = new Client().build(res);
                        clients.add(client);
                    }
                    logger.info(request.getRequestOrder() + " : precessing done");
                    mapper = new ObjectMapper();
                    response = new Response();
                    response.setRequestId(request.getRequestId());
                    response.setResponseBody(mapper.writeValueAsString(clients));
                    break;

                case "INSERT_CLIENT" : 
                logger.info("requestOrder : " + request.getRequestOrder());
                    mapper = new ObjectMapper();
                    Client client = (Client) mapper.readValue(request.getRequestBody(), Client.class);
                    pstmt = connection.prepareStatement(Queries.INSERT_CLIENT.query);
                    pstmt.setString(1, client.getNom_client());
                    pstmt.setString(2, client.getPrenom_client());
                    pstmt.setDate(3, client.getDate_de_naissance_client());
                    pstmt.setBigDecimal(4, client.getPoids());
                    pstmt.setString(5, client.getGenre());
                    pstmt.setInt(6, client.getTaille());
                    pstmt.setString(7, client.getNumero_de_telephone_client());
                    pstmt.setString(8, client.getMail_client());
                    pstmt.setString(9, client.getVille());
                    pstmt.setString(10, client.getAdresse());
                    pstmt.setString(11, client.getCode_Postal());
                    rows = pstmt.executeUpdate();
                    logger.info(request.getRequestOrder() + " : precessing done");
                    response = new Response();
                    response.setRequestId(request.getRequestId());
                    response.setResponseBody("{\"insert\": " + rows + " }");
                    break; 

                case "UPDATE_CLIENT":
                logger.info("requestOrder : " + request.getRequestOrder());
                    mapper = new ObjectMapper();
                    update = (Update) mapper.readValue(request.getRequestBody(), Update.class);
                    pstmt = connection.prepareStatement("UPDATE \"episaine-schema\".clients SET " + update.getNewColumn() + "= ? WHERE " + update.getConditionColumn() + "= ?");
                    switch (update.getNewColumn()) {
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
                    pstmt.setInt(2, Integer.parseInt(update.getConditionValue()));
                    rows = pstmt.executeUpdate();
                    logger.info(request.getRequestOrder() + " : precessing done");
                    response = new Response();
                    response.setRequestId(request.getRequestId());
                    response.setResponseBody("{\"update\": " + rows + " }");
                    break;

                case "DELETE_CLIENT":
                logger.info("requestOrder : " + request.getRequestOrder());
                    pstmt = connection.prepareStatement(Queries.DELETE_CLIENT.query);
                    pstmt.setInt(1, Integer.parseInt(request.getRequestBody()));
                    rows = pstmt.executeUpdate();
                    logger.info(request.getRequestOrder() + " : precessing done");
                    response = new Response();
                    response.setRequestId(request.getRequestId());
                    response.setResponseBody("{\"delete\": " + rows + " }");
                    break;
                 
                //-----------------------------

                case "SELECT_ALL_RECETTES" :
                logger.info("requestOrder : " + request.getRequestOrder());
                    stmt = connection.createStatement();
                    res = stmt.executeQuery(Queries.SELECT_ALL_RECETTES.query);
                    Recettes recettes = new Recettes();
                    while (res.next()) {
                        Recette recette = new Recette().build(res);
                        recettes.add(recette);
                    }
                    logger.info(request.getRequestOrder() + " : precessing done");
                    mapper = new ObjectMapper();
                    response = new Response();
                    response.setRequestId(request.getRequestId());
                    response.setResponseBody(mapper.writeValueAsString(recettes));
                    break;

                case "INSERT_RECETTE" :
                logger.info("requestOrder : " + request.getRequestOrder());
                    mapper = new ObjectMapper();
                    Recette recette = (Recette) mapper.readValue(request.getRequestBody(), Recette.class);
                    pstmt = connection.prepareStatement(Queries.INSERT_RECETTE.query);
                    pstmt.setString(1, recette.getNom_Recette());
                    pstmt.setInt(2, recette.getNombre_de_Calories());
                    pstmt.setString(3, recette.getIngredients());
                    pstmt.setString(4, recette.getInstructions());
                    pstmt.setString(5, recette.getRegimeAlimentaire());
                    pstmt.setInt(6, recette.getId_nutritionniste());
                    rows = pstmt.executeUpdate();
                    logger.info(request.getRequestOrder() + " : precessing done");
                    response = new Response();
                    response.setRequestId(request.getRequestId());
                    response.setResponseBody("{\"insert\": " + rows + " }");
                    break;
                
                case "UPDATE_RECETTE" :
                logger.info("requestOrder : " + request.getRequestOrder());
                    mapper = new ObjectMapper();
                    update = (Update) mapper.readValue(request.getRequestBody(), Update.class);
                    pstmt = connection.prepareStatement("UPDATE \"episaine-schema\".recettes SET " + update.getNewColumn() + "= ? WHERE " + update.getConditionColumn() + "= ?");
                    switch (update.getNewColumn()) {
                    case "Nombre de calories" :
                        pstmt.setInt(1,Integer.parseInt(update.getNewValue()));
                        break;
                    case "ID Nutritionniste":
                        pstmt.setInt(1, Integer.parseInt(update.getNewValue()));
                        break;
                    default:
                        pstmt.setString(1, update.getNewValue());
                        break;
                    }
                    pstmt.setInt(2, Integer.parseInt(update.getConditionValue()));
                    rows = pstmt.executeUpdate();
                    logger.info(request.getRequestOrder() + " : precessing done");
                    response = new Response();
                    response.setRequestId(request.getRequestId());
                    response.setResponseBody("{\"update\": " + rows + " }");
                    break;

                case "DELETE_RECETTE":
                logger.info("requestOrder : " + request.getRequestOrder());
                    pstmt = connection.prepareStatement(Queries.DELETE_RECETTE.query);
                    pstmt.setInt(1, Integer.parseInt(request.getRequestBody()));
                    rows = pstmt.executeUpdate();
                    logger.info(request.getRequestOrder() + " : precessing done");
                    response = new Response();
                    response.setRequestId(request.getRequestId());
                    response.setResponseBody("{\"delete\": " + rows + " }");
                    break;

                //-----------------------------

                case "SELECT_ALL_NUTRITIONNISTES" :
                logger.info("requestOrder : " + request.getRequestOrder());
                    stmt = connection.createStatement();
                    res = stmt.executeQuery(Queries.SELECT_ALL_NUTRITIONNISTES.query);
                    Nutritionnistes nutritionnistes = new Nutritionnistes();
                    while (res.next()) {
                        Nutritionniste nutritionniste = new Nutritionniste().build(res);
                        nutritionnistes.add(nutritionniste);
                    }
                    logger.info(request.getRequestOrder() + " : precessing done");
                    mapper = new ObjectMapper();
                    response = new Response();
                    response.setRequestId(request.getRequestId());
                    response.setResponseBody(mapper.writeValueAsString(nutritionnistes));
                    break;

                case "INSERT_NUTRITIONNISTE" : 
                logger.info("requestOrder : " + request.getRequestOrder());
                    mapper = new ObjectMapper();
                    Nutritionniste nutritionniste = (Nutritionniste) mapper.readValue(request.getRequestBody(), Nutritionniste.class);
                    pstmt = connection.prepareStatement(Queries.INSERT_NUTRITIONNISTE.query);
                    pstmt.setString(1, nutritionniste.getnom_N());
                    pstmt.setString(2, nutritionniste.getprenom_N());
                    pstmt.setString(3, nutritionniste.getnumero_de_telephone_N());
                    pstmt.setString(4, nutritionniste.getmail_N());
                    rows = pstmt.executeUpdate();
                    logger.info(request.getRequestOrder() + " : precessing done");
                    response = new Response();
                    response.setRequestId(request.getRequestId());
                    response.setResponseBody("{\"insert\": " + rows + " }");
                    break; 

                case "UPDATE_NUTRITIONNISTE" :
                logger.info("requestOrder : " + request.getRequestOrder());
                    mapper = new ObjectMapper();
                    update = (Update) mapper.readValue(request.getRequestBody(), Update.class);
                    pstmt = connection.prepareStatement("UPDATE \"episaine-schema\".nutritionnistes SET " + update.getNewColumn() + "= ? WHERE " + update.getConditionColumn() + "= ?");
                    pstmt.setString(1, update.getNewValue());
                    pstmt.setInt(2, Integer.parseInt(update.getConditionValue()));
                    rows = pstmt.executeUpdate();
                    logger.info(request.getRequestOrder() + " : precessing done");
                    response = new Response();
                    response.setRequestId(request.getRequestId());
                    response.setResponseBody("{\"update\": " + rows + " }");
                    break;

                case "DELETE_NUTRITIONNISTE":
                logger.info("requestOrder : " + request.getRequestOrder());
                    pstmt = connection.prepareStatement(Queries.DELETE_NUTRITIONNISTE.query);
                    pstmt.setInt(1, Integer.parseInt(request.getRequestBody()));
                    rows = pstmt.executeUpdate();
                    logger.info(request.getRequestOrder() + " : precessing done");
                    response = new Response();
                    response.setRequestId(request.getRequestId());
                    response.setResponseBody("{\"delete\": " + rows + " }");
                    break;

                //-----------------------------    

                case "SELECT_ALL_INFORMATIONS" :
                logger.info("requestOrder : " + request.getRequestOrder());
                    stmt = connection.createStatement();
                    res = stmt.executeQuery(Queries.SELECT_ALL_INFORMATIONS.query);
                    Informations informations = new Informations();
                    while (res.next()) {
                        Information information = new Information().build(res);
                        informations.add(information);
                    }
                    logger.info(request.getRequestOrder() + " : precessing done");
                    mapper = new ObjectMapper();
                    response = new Response();
                    response.setRequestId(request.getRequestId());
                    response.setResponseBody(mapper.writeValueAsString(informations));
                    break;

                case "INSERT_INFORMATION" : 
                logger.info("requestOrder : " + request.getRequestOrder());
                    mapper = new ObjectMapper();
                    Information information = (Information) mapper.readValue(request.getRequestBody(), Information.class);
                    pstmt = connection.prepareStatement(Queries.INSERT_INFORMATION.query);
                    pstmt.setInt(1, information.getId_Client());
                    pstmt.setString(2, information.getBut());
                    pstmt.setString(3, information.getAllergie());
                    pstmt.setInt(4, information.getNbDeRepas());
                    rows = pstmt.executeUpdate();
                    logger.info(request.getRequestOrder() + " : precessing done");
                    response = new Response();
                    response.setRequestId(request.getRequestId());
                    response.setResponseBody("{\"insert\": " + rows + " }");
                    break; 

                case "UPDATE_INFORMATION":
                logger.info("requestOrder : " + request.getRequestOrder());
                    mapper = new ObjectMapper();
                    update = (Update) mapper.readValue(request.getRequestBody(), Update.class);
                    pstmt = connection.prepareStatement("UPDATE \"episaine-schema\".informations SET " + update.getNewColumn() + "= ? WHERE " + update.getConditionColumn() + "= ?");
                    switch (update.getNewColumn()) {
                       case "ID Client":
                          pstmt.setInt(1, Integer.parseInt(update.getNewValue()));
                          break;
                       case "Nombre de repas":
                          pstmt.setInt(1, Integer.parseInt(update.getNewValue()));
                          break;
                       default:
                          pstmt.setString(1, update.getNewValue());
                          break;
                    }
                    pstmt.setInt(2, Integer.parseInt(update.getConditionValue()));
                    rows = pstmt.executeUpdate();
                    logger.info(request.getRequestOrder() + " : precessing done");
                    response = new Response();
                    response.setRequestId(request.getRequestId());
                    response.setResponseBody("{\"update\": " + rows + " }");
                    break;

                case "DELETE_INFORMATION":
                logger.info("requestOrder : " + request.getRequestOrder());
                    pstmt = connection.prepareStatement(Queries.DELETE_INFORMATION.query);
                    pstmt.setInt(1, Integer.parseInt(request.getRequestBody()));
                    rows = pstmt.executeUpdate();
                    logger.info(request.getRequestOrder() + " : precessing done");
                    response = new Response();
                    response.setRequestId(request.getRequestId());
                    response.setResponseBody("{\"delete\": " + rows + " }");
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
