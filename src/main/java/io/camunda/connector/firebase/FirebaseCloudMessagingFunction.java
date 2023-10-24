package io.camunda.connector.firebase;

import io.camunda.connector.api.annotation.OutboundConnector;
import io.camunda.connector.api.outbound.OutboundConnectorContext;
import io.camunda.connector.api.outbound.OutboundConnectorFunction;
import io.camunda.connector.firebase.dto.ConnectorRequestDto;
import io.camunda.connector.firebase.config.FirebaseConfiguration;
import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.regex.Matcher;

@OutboundConnector(
        name = "FIREBASECLOUDMESSAGING",
        inputVariables = {"serviceAccount", "messageType", "tokens", "topic", "messageTitle", "messageData"},
        type = "firebaseCloudMessaging")
public class FirebaseCloudMessagingFunction implements OutboundConnectorFunction {
    public FirebaseCloudMessagingFunction() {}
    private static final Logger LOGGER = LoggerFactory.getLogger(FirebaseCloudMessagingFunction.class);

    @Override
    public Object execute(OutboundConnectorContext context) {
        var requestVariables = context.bindVariables(ConnectorRequestDto.class);
        return executeConnector(requestVariables);
    }

    private String executeConnector(final ConnectorRequestDto requestVariables) {
        try{
            //Create credential input stream from Service Account Input
            InputStream inputStream = converter(requestVariables.getServiceAccount());

            //Create the connection to Firebase App
            FirebaseConfiguration fbc = new FirebaseConfiguration(inputStream);
            FirebaseMessagingService service = new FirebaseMessagingService(fbc.getFirebaseMessaging());

            //Run Firebase Messaging Logic
            service.execute(requestVariables.getMessageType(),
                    concatString(requestVariables.getMessageTitle(),200),
                    concatString(requestVariables.getMessageData(),1000),
                    requestVariables.getTokens(),
                    requestVariables.getTopic());

            return "Cloud Message sent";

        } catch (Exception e){
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    private InputStream converter(String serviceAccount){
        String unEscaped = StringEscapeUtils.unescapeJava(serviceAccount);
        String credentials = unEscaped.replaceAll(Matcher.quoteReplacement("\\n"), Matcher.quoteReplacement("\n"));
        return new ByteArrayInputStream(credentials.getBytes());
    }

    private String concatString (String input, Integer length){
        if (input.length() > length) {
            return input.substring(0, length);
        } else {
            return input;
        }
    }
}
