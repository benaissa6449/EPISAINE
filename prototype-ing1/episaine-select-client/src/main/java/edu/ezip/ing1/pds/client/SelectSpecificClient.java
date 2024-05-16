package edu.ezip.ing1.pds.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.ezip.ing1.pds.business.dto.Client;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.UUID;

public class SelectSpecificClient {

    private final static String LoggingLabel = "S e l e c t - C l i e n t";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
    private final static String networkConfigFile = "network.yaml";

    public static Client getValue(String requestOrder, String requestCondition) {
        Client client = new Client();
        try {
            final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);

            logger.debug("Load Network config file : {}", networkConfig.toString());
            int birthdate = 0;
            final ObjectMapper objectMapper = new ObjectMapper();

            final String requestId = UUID.randomUUID().toString();
            final Request request = new Request();
            request.setRequestId(requestId);
            request.setRequestOrder(requestOrder);
            request.setRequestContent(requestCondition);
            logger.info("new request : " + request);

            objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
            final byte[] requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);

            SelectAllClientRequest clientRequest = new SelectAllClientRequest(
                    networkConfig, birthdate++, request, requestCondition, requestBytes);
            clientRequest.join();

            client = objectMapper.convertValue(clientRequest.getResult(), Client.class);
            logger.info("data requested : " + client.toString());
        } catch (IOException | InterruptedException ioe) {
            logger.warn(ioe.getMessage());
        }
        return client;
    }
}
