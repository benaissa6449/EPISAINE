package edu.ezip.ing1.pds.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.Nutritionniste;
import edu.ezip.ing1.pds.business.dto.Nutritionnistes;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

import java.io.IOException;


public class SelectAllNutritionnisteRequest extends ClientRequest<Object, Nutritionnistes> {

    public SelectAllNutritionnisteRequest(
            NetworkConfig networkConfig, int myBirthDate, Request request, Object info, byte[] bytes)
            throws IOException {
        super(networkConfig, myBirthDate, request, info, bytes);
    }

    @Override
    public Nutritionnistes readResult(String body) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Nutritionnistes nutritionnistes = mapper.readValue(body, Nutritionnistes.class);
        return nutritionnistes;
    }
}