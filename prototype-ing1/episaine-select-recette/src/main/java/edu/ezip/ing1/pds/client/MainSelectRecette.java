package edu.ezip.ing1.pds.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import de.vandermeer.asciitable.AsciiTable;
import edu.ezip.commons.LoggingUtils;
import edu.ezip.ing1.pds.business.dto.Recette;
import edu.ezip.ing1.pds.business.dto.Recettes;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.UUID;

public class MainSelectRecette {

    private final static String LoggingLabel = "S e l e c t - R e c e t t e";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
    private final static String networkConfigFile = "network.yaml";
    private static final String requestOrder = "SELECT_ALL_RECETTES";
    private static final Deque<ClientRequest> recetteRequests = new ArrayDeque<ClientRequest>();

    public static void main(String[] args) throws IOException, InterruptedException, SQLException {

        final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, networkConfigFile);
        logger.debug("Load Network config file : {}", networkConfig.toString());

        int birthdate = 0;
        final ObjectMapper objectMapper = new ObjectMapper();

        final String requestId = UUID.randomUUID().toString();
        final Request request = new Request();
        request.setRequestId(requestId);
        request.setRequestOrder(requestOrder);

        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        final byte[] requestBytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(request);
        LoggingUtils.logDataMultiLine(logger, Level.TRACE, requestBytes);
        final SelectAllRecetteRequest recetteRequest = new SelectAllRecetteRequest(
                networkConfig,
                birthdate++, request, null, requestBytes);
        recetteRequests.push(recetteRequest);

        while (!recetteRequests.isEmpty()) {
            final ClientRequest joinedRecetteRequest = recetteRequests.pop();
            joinedRecetteRequest.join();
            logger.debug("Thread {} complete.", joinedRecetteRequest.getThreadName());
            try {
                final Recettes recettes = (Recettes) joinedRecetteRequest.getResult();
                final AsciiTable asciiTable = new AsciiTable();
                ArrayList<String[]> recetteList = new ArrayList<>();
                for (final Recette recette : recettes.getRecettes()) {
                    String[] recetteLine = new String[5];
                    recetteLine[0] = recette.getNom_Recette();
                    recetteLine[1] = String.valueOf(recette.getNombre_de_Calories());
                    recetteLine[2] = recette.getIngredients();
                    recetteLine[3] = recette.getInstructions();
                    recetteLine[4] = recette.getRegimeAlimentaire();
                    recetteList.add(recetteLine);

                    asciiTable.addRule();
                    asciiTable.addRow(recette.getNom_Recette(), recette.getNombre_de_Calories(),
                            recette.getIngredients(), recette.getInstructions(), recette.getRegimeAlimentaire()
                            );
                }
                asciiTable.addRule();

                StringAsciiTableRecette.setRecetteList(recetteList);

                logger.debug("\n{}\n", asciiTable.render());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
