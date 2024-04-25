package edu.ezip.ing1.pds.client;

import java.io.IOException;
import java.util.UUID;

import edu.ezip.ing1.pds.business.dto.Nutritionnistes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

public class SelectNutritionist {

    private final static String LoggingLabel = "S e l e c t - N u t r i t i o n i s t";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
    private final static String networkConfigFile = "network.yaml";

    public static Nutritionnistes getValue(String requestOrder) {
        Nutritionnistes nutritionnistes = new Nutritionnistes();
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

            nutritionnistes = objectMapper.convertValue(clientRequest.getResult(), Nutritionnistes.class);
            logger.info("data requested : " + nutritionnistes.toString());
        } catch (IOException | InterruptedException ioe) {
            logger.warn(ioe.getMessage());
        }
        return nutritionnistes;
    }

    public static void main(String[] args) {
        Nutritionnistes nutritionnistes = SelectNutritionist.getValue("SELECT_ALL_NUTRITIONNISTES");
        System.out.println(nutritionnistes.toString());
    }
}
