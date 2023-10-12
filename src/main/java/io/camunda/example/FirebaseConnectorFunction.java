package io.camunda.example;

import com.google.firebase.messaging.FirebaseMessagingException;
import io.camunda.connector.api.annotation.OutboundConnector;
import io.camunda.connector.api.outbound.OutboundConnectorContext;
import io.camunda.connector.api.outbound.OutboundConnectorFunction;
import io.camunda.example.config.FirebaseConfiguration;
import io.camunda.example.dto.ConnectorRequestDto;
import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.regex.Matcher;

@OutboundConnector(
        name = "FIREBASECONNECTOR",
        inputVariables = {"serviceAccount", "messageType", "tokens", "topic", "messageTitle", "messageData"},
        type = "firebaseConnector")
public class FirebaseConnectorFunction implements OutboundConnectorFunction {
    private FirebaseMessagingService service;
    public FirebaseConnectorFunction() {}
    private static final Logger LOGGER = LoggerFactory.getLogger(FirebaseConnectorFunction.class);

    @Override
    public Object execute(OutboundConnectorContext context) throws FirebaseMessagingException, IOException {
        var requestVariables = context.bindVariables(ConnectorRequestDto.class);
        return executeConnector(requestVariables);
    }

    private String executeConnector(final ConnectorRequestDto requestVariables) throws FirebaseMessagingException, IOException {

        //Create credential input stream from Service Account Input
        InputStream inputStream = converter(requestVariables.getServiceAccount());

        //Create the connection to Firebase App
        FirebaseConfiguration fbc = new FirebaseConfiguration(inputStream);
        FirebaseMessagingService service = new FirebaseMessagingService(fbc.getFirebaseMessaging());

        //Run Firebase Messaging Logic
        service.execute(requestVariables.getMessageType(), requestVariables.getMessageTitle(), requestVariables.getMessageData(), requestVariables.getTokens(), requestVariables.getTopic());

        return "Cloud Message sent";
    }

    InputStream converter(String serviceAccount){

        String unEscaped = StringEscapeUtils.unescapeJava(serviceAccount);
        String credentials = unEscaped.replaceAll(Matcher.quoteReplacement("\\n"), Matcher.quoteReplacement("\n"));

        return new ByteArrayInputStream(credentials.getBytes());
    }


}
