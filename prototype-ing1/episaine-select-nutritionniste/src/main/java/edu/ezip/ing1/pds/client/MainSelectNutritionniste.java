package edu.ezip.ing1.pds.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import de.vandermeer.asciitable.AsciiTable;
import edu.ezip.commons.LoggingUtils;
import edu.ezip.ing1.pds.business.dto.Nutritionniste;
import edu.ezip.ing1.pds.business.dto.Nutritionnistes;
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

public class MainSelectNutritionniste {

    private final static String LoggingLabel = "S e l e c t - N U T R I T I O N N I S T E ";
    private final static Logger logger = LoggerFactory.getLogger(LoggingLabel);
    private final static String networkConfigFile = "network.yaml";
    private static final String requestOrder = "SELECT_ALL_NUTRITIONNISTES";
    private static final Deque<ClientRequest> nutritionnisteRequests = new ArrayDeque<ClientRequest>();

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
        final SelectAllNutritionnisteRequest nutritionnisteRequest = new SelectAllNutritionnisteRequest(
                networkConfig,
                birthdate++, request, null, requestBytes);
                nutritionnisteRequests.push(nutritionnisteRequest);

        while (!nutritionnisteRequests.isEmpty()) {
            final ClientRequest joinedNutritionnisteRequest = nutritionnisteRequests.pop();
            nutritionnisteRequest.join();
            logger.debug("Thread {} complete.", joinedNutritionnisteRequest.getThreadName());
            try {
                final Nutritionnistes nutritionnistes = (Nutritionnistes) joinedNutritionnisteRequest.getResult();
                final AsciiTable asciiTable = new AsciiTable();
                ArrayList<String[]> nutritionnisteList = new ArrayList<>();
                for (final Nutritionniste nutritionniste : nutritionnistes.getNutritionnistes()) {
                    String[] nutritionnisteLine = new String[4];
                    nutritionnisteLine[0] = nutritionniste.getnom_N();
                    nutritionnisteLine[1] = nutritionniste.getprenom_N();
                    nutritionnisteLine[2] = String.valueOf(nutritionniste.getnumero_de_telephone_N());
                    nutritionnisteLine[3] = nutritionniste.getmail_N();
                    nutritionnisteList.add(nutritionnisteLine);
                    
                    asciiTable.addRule();
                    asciiTable.addRow(nutritionniste.getnom_N(), nutritionniste.getprenom_N(),
                    nutritionniste.getnumero_de_telephone_N(), nutritionniste.getmail_N()
                    );
                }
                asciiTable.addRule();
                StringAsciiTableNutritionniste.setNutritionnisteList(nutritionnisteList);
                logger.debug("\n{}\n", asciiTable.render());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
