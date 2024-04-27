package edu.ezip.ing1.pds.client;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

public class CountTableRow {

    private final static String LoggingLabel = "C o u n t - T a b l e - R o w";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
    private final static String networkConfigFile = "network.yaml";

    public static Integer getRow(String requestOrder) {
        Integer res = 0;
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

            res = objectMapper.convertValue(clientRequest.getResult(), Integer.class);
            logger.info("data requested : " + res.toString());
        } catch (IOException | InterruptedException ioe) {
            logger.warn(ioe.getMessage());
        }
        return res;
    }
}
