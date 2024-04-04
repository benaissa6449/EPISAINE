package edu.ezip.ing1.pds.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

import java.io.IOException;

public class SelectAllClientRequest extends ClientRequest<Object, Object> {

    public SelectAllClientRequest(
            NetworkConfig networkConfig, int myBirthDate, Request request, Object info, byte[] bytes)
            throws IOException {
        super(networkConfig, myBirthDate, request, info, bytes);
    }

    @Override
    public Object readResult(String body) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Object object = mapper.readValue(body, Object.class);
        return object;
    }
}
