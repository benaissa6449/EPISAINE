package edu.ezip.ing1.pds.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.Recette;
import edu.ezip.ing1.pds.business.dto.Recettes;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;
import java.io.IOException;
import java.util.Map;


public class InsertRecetteRequest extends ClientRequest<Recette, String> {
    public InsertRecetteRequest(
            NetworkConfig networkConfig, int myBirthDate, Request request, Recette info, byte[] bytes)
            throws IOException {
        super(networkConfig, myBirthDate, request, info, bytes);
    }

    @Override
    public String readResult(String body) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Map<String, Integer> recetteIdMap = mapper.readValue(body, Map.class);
        final String result = recetteIdMap.get("id_recette").toString();
        return result;
    }
}
