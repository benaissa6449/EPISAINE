package edu.ezip.ing1.pds.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.Recettes;
import edu.ezip.ing1.pds.business.dto.Recette;
import edu.ezip.ing1.pds.client.commons.RecetteRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

import java.io.IOException;


public class SelectAllRecetteRequest extends RecetteRequest<Object, Recette> {

    public SelectAllRecetteRequest(
            NetworkConfig networkConfig, int myBirthDate, Request request, Object info, byte[] bytes)
            throws IOException {
        super(networkConfig, myBirthDate, request, info, bytes);
    }

    @Override
    public Recette readResult(String body) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Recette recette = mapper.readValue(body, Recette.class);
        return recette;
    }
}
