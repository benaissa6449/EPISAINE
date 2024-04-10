package edu.ezip.ing1.pds.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import edu.ezip.ing1.pds.business.dto.Nutritionniste;
import edu.ezip.ing1.pds.business.dto.Nutritionnistes;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class MainInsertNutritionniste {

    private final static String LoggingLabel = "I n s e r t e r - N u t r i t i o n n i s t e ";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
    //private final static String clientsToBeInserted = "clients-to-be-inserted.yaml";
    private final static String networkConfigFile = "network.yaml";
    private static final String threadName = "inserter-nutritionniste";
    private static final String requestOrder = "INSERT_NUTRITIONNISTE";
    private static final Deque<ClientRequest> nutritionnisteRequests = new ArrayDeque<ClientRequest>();
    private static Nutritionniste nutritionniste;

    public static void setNutritionniste(Nutritionniste nutritionnisteVar) {nutritionniste = nutritionnisteVar;}

    public static void main(String[] args) throws IOException, InterruptedException, SQLException {
        
        //final Clients guys = ConfigLoader.loadConfig(Clients.class, clientsToBeInserted);
        final Nutritionnistes guys = new Nutritionnistes();
        guys.add(nutritionniste);
        final NetworkConfig networkConfig =  ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
        
        logger.trace("Nutritionnistes loaded : {}", guys.toString());
        logger.debug("Load Network config file : {}", networkConfig.toString());

        int birthdate = 0;

        for(final Nutritionniste guy : guys.getNutritionnistes()) {
            final ObjectMapper objectMapper = new ObjectMapper();
            final String jsonifiedGuy = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(guy);
            logger.trace("Nutritionniste with its JSON face : {}", jsonifiedGuy);
            final String requestId = UUID.randomUUID().toString();
            final Request request = new Request();
            request.setRequestId(requestId);
            request.setRequestOrder(requestOrder);
            request.setRequestContent(jsonifiedGuy);
            objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
            final byte [] requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);

            final InsertNutritionnisteRequest nutritionnisteRequest = new InsertNutritionnisteRequest(networkConfig, birthdate, request, guy, requestBytes);
        }
        while (!nutritionnisteRequests.isEmpty()) {
            final ClientRequest nutritionnisteRequest = nutritionnisteRequests.pop();
            nutritionnisteRequest.join();
            final Nutritionniste guy = (Nutritionniste) nutritionnisteRequest.getInfo();
            logger.debug("Thread {} complete : {} {} {} --> {}",
                                    nutritionnisteRequest.getThreadName(),
                                    guy.getnom_N(), guy.getprenom_N(), guy.getnumero_de_telephone_N(), guy.getmail_N(),
                                    nutritionnisteRequest.getResult());
        }
    }
}