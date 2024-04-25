package edu.ezip.ing1.pds.client;

import java.io.IOException;
import java.util.UUID;

import edu.ezip.ing1.pds.business.dto.Informations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import edu.ezip.ing1.pds.business.dto.Clients;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

public class SelectInformation {

    private final static String LoggingLabel = "S e l e c t - I n f o r m a t i o n";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
    private final static String networkConfigFile = "network.yaml";

    public static Informations getValue(String requestOrder) {
        Informations informations = new Informations();
        try {
            final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);

            logger.debug("Load Network config file : {}", networkConfig.toString());
            int birthdate = 0;
            final ObjectMapper objectMapper = new ObjectMapper();

            final String requestId = UUID.randomUUID().toString();
            final Request request = new Request();
            request.setRequestId(requestId);
            request.setRequestOrder(requestOrder);

            logger.info("new request : " + request.toString());

            objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
            final byte[] requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);

            SelectAllClientRequest clientRequest = new SelectAllClientRequest(
                    networkConfig, birthdate++, request, null, requestBytes);
            clientRequest.join();

            informations = objectMapper.convertValue(clientRequest.getResult(), Informations.class);
            logger.info("data requested : " + informations.toString());
        } catch (IOException | InterruptedException ioe) {
            logger.warn(ioe.getMessage());
        }
        return informations;
    }
}
