package edu.ezip.ing1.pds.backend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.ezip.commons.LoggingUtils;
import edu.ezip.ing1.pds.business.server.XMartCityService;
import edu.ezip.ing1.pds.commons.Request;
import edu.ezip.ing1.pds.commons.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.sql.Connection;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class RequestHandler implements Runnable {
    private final Socket socket;
    private final Connection connection;
    private final Thread self;
    private static final String threadNamePrfx = "core-request-handler";
    private final InputStream instream;
    private final OutputStream outstream;
    private final static String LoggingLabel = "C o re - B a c k e n d - S e r v e r";
    private final Logger logger = LoggerFactory.getLogger(LoggingLabel);
    private int requestCount = 0;

    private final XMartCityService xmartCityService = XMartCityService.getInstance();
    private final CoreBackendServer father;

    private static final int maxTimeLapToGetAClientPayloadInMs = 5000;
    private static final int timeStepMs = 300;
    private final BlockingDeque<Integer> waitArtifact = new LinkedBlockingDeque<Integer>(1);

    protected RequestHandler(final Socket socket,
                             final Connection connection,
                             final int myBirthDate,
                             final CoreBackendServer father) throws IOException {
        this.socket = socket;
        this.connection = connection;
        this.father = father;
        final StringBuffer threadName = new StringBuffer();
        threadName.append(threadNamePrfx).append("★").append(String.format("%04d",myBirthDate));
        self = new Thread(this, threadName.toString());
        instream = socket.getInputStream();
        outstream = socket.getOutputStream();
        self.start();
    }



    @Override
    public void run() {
        try {

            int timeout = maxTimeLapToGetAClientPayloadInMs;
            while (0 == instream.available() && 0 < timeout) {
                waitArtifact.pollFirst(timeStepMs, TimeUnit.MILLISECONDS);
                timeout-=timeStepMs;
            }
            if (0>timeout) return;

            final byte [] inputData = new byte[instream.available()];
            instream.read(inputData);
            final Request request = getRequest(inputData);
            final Response response = xmartCityService.dispatch(request, connection);

            final byte [] outoutData = getResponse(response);
            LoggingUtils.logDataMultiLine(logger, Level.DEBUG, outoutData);
            outstream.write(outoutData);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            father.completeRequestHandler(this);
        }
    }

    private final Request getRequest(byte [] data) throws IOException {
        logger.debug("data received {} bytes", data.length);
        LoggingUtils.logDataMultiLine(logger, Level.DEBUG, data);
        final ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
        final Request request = mapper.readValue(data, Request.class);
        logger.debug(request.toString());
        return request;
    }

    private final byte [] getResponse(final Response response) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        //mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(response);
    }

    public final Connection getConnection() {
        return connection;
    }

    public final Socket getSocket() {
        return socket;
    }


}
