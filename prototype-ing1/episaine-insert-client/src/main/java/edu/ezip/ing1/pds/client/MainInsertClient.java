package edu.ezip.ing1.pds.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import edu.ezip.ing1.pds.business.dto.Client;
import edu.ezip.ing1.pds.business.dto.Clients;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class MainInsertClient {

    private final static String LoggingLabel = "I n s e r t e r - C l i e n t";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
    private final static String clientsToBeInserted = "clients-to-be-inserted.yaml";
    private final static String networkConfigFile = "network.yaml";
    private static final String threadName = "inserter-client";
    private static final String requestOrder = "INSERT_CLIENT";
    private static final Deque<ClientRequest> clientRequests = new ArrayDeque<ClientRequest>();

    public static void main(String[] args) throws IOException, InterruptedException, SQLException {
        
        final Clients guys = ConfigLoader.loadConfig(Clients.class, clientsToBeInserted);
        final NetworkConfig networkConfig =  ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
        
        logger.trace("Clients loaded : {}", guys.toString());
        logger.debug("Load Network config file : {}", networkConfig.toString());

        int birthdate = 0;

        for(final Client guy : guys.getClients()) {
            final ObjectMapper objectMapper = new ObjectMapper();
            final String jsonifiedGuy = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(guy);
            logger.trace("Client with its JSON face : {}", jsonifiedGuy);
            final String requestId = UUID.randomUUID().toString();
            final Request request = new Request();
            request.setRequestId(requestId);
            request.setRequestOrder(requestOrder);
            request.setRequestContent(jsonifiedGuy);
            objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
            final byte [] requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);

            final InsertStudentsClientRequest clientRequest = new InsertStudentsClientRequest (
                                                                        networkConfig,
                                                                        birthdate++, request, guy, requestBytes);
        }
        while (!clientRequests.isEmpty()) {
            final ClientRequest clientRequest = clientRequests.pop();
            clientRequest.join();
            final Client guy = (Client)clientRequest.getInfo();
            logger.debug("Thread {} complete : {} {} {} --> {}",
                                    clientRequest.getThreadName(),
                                    guy.getNom_Client(), guy.getPrenom_Client(), guy.getDate_de_naissance_Client(), guy.getPoids(), guy.getGenre(),
                                    guy.getTaille(), guy.getNumero_de_telephone_Client(), guy.getMail_Client(), guy.getVille(), guy.getAdresse(),
                                    guy.getCode_Postal_(),
                                    clientRequest.getResult());
        }
    }
}
