package edu.ezip.ing1.pds.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import edu.ezip.ing1.pds.business.dto.Recette;
import edu.ezip.ing1.pds.business.dto.Recettes;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class MainInsertRecette {

    private final static String LoggingLabel = "I n s e r t e r - R e c e t t e";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
    //private final static String clientsToBeInserted = "clients-to-be-inserted.yaml";
    private final static String networkConfigFile = "network.yaml";
    private static final String threadName = "inserter-recette";
    private static final String requestOrder = "INSERT_RECETTE";
    private static final Deque<ClientRequest> recetteRequests = new ArrayDeque<ClientRequest>();
    private static Recette recette;

    public static void setRecette(Recette recetteVar) {recette = recetteVar;}

    public static void main(String[] args) throws IOException, InterruptedException, SQLException {
        
        //final Clients guys = ConfigLoader.loadConfig(Clients.class, clientsToBeInserted);
        final Recettes guys = new Recettes();
        guys.add(recette);
        final NetworkConfig networkConfig =  ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
        
        logger.trace("Recettes loaded : {}", guys.toString());
        logger.debug("Load Network config file : {}", networkConfig.toString());

        int birthdate = 0;

        for(final Recette guy : guys.getRecettes()) {
            final ObjectMapper objectMapper = new ObjectMapper();
            final String jsonifiedGuy = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(guy);
            logger.trace("Recette with its JSON face : {}", jsonifiedGuy);
            final String requestId = UUID.randomUUID().toString();
            final Request request = new Request();
            request.setRequestId(requestId);
            request.setRequestOrder(requestOrder);
            request.setRequestContent(jsonifiedGuy);
            objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
            final byte [] requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);

            final InsertRecetteRequest recetteRequest = new InsertRecetteRequest(networkConfig, birthdate, request, null, requestBytes);
        }
        while (!recetteRequests.isEmpty()) {
            final ClientRequest recetteRequest = recetteRequests.pop();
            recetteRequest.join();
            final Recette guy = (Recette) recetteRequest.getInfo();
            logger.debug("Thread {} complete : {} {} {} --> {}",
                                    recetteRequest.getThreadName(),
                                    guy.getNom_Recette(), guy.getNombre_de_Calories(), guy.getIngredients(), guy.getInstructions(), guy.getRegimeAlimentaire(),
                                    recetteRequest.getResult());
        }
    }
}
