package edu.ezip.ing1.pds.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.Recettes;
import edu.ezip.ing1.pds.business.dto.Recette;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

import java.io.IOException;


public class SelectAllRecetteRequest extends ClientRequest<Object, Recettes> {

    public SelectAllRecetteRequest(
            NetworkConfig networkConfig, int myBirthDate, Request request, Object info, byte[] bytes)
            throws IOException {
        super(networkConfig, myBirthDate, request, info, bytes);
    }

    @Override
    public Recettes readResult(String body) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Recettes recettes = mapper.readValue(body, Recettes.class);
        return recettes;
    }
}
