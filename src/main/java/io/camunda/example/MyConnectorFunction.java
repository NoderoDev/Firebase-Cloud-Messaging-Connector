package io.camunda.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.gson.Gson;
import io.camunda.connector.api.annotation.OutboundConnector;
import io.camunda.connector.api.outbound.OutboundConnectorContext;
import io.camunda.connector.api.outbound.OutboundConnectorFunction;
import io.camunda.example.config.FirebaseConfiguration;
import io.camunda.example.dto.MyConnectorResult;
import io.camunda.example.dto.ServiceAccountDto;
import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;

@OutboundConnector(
        name = "MYCONNECTOR",
        inputVariables = {"type","project_id","private_key_id","private_key","client_email","client_id","auth_uri","token_uri","auth_provider_x509_cert_url","client_x509_cert_url","universe_domain"},
        type = "connectorTest")
public class MyConnectorFunction implements OutboundConnectorFunction {

    private FirebaseMessagingService service;


    public MyConnectorFunction() {}

    private static final Logger LOGGER = LoggerFactory.getLogger(MyConnectorFunction.class);


    @Override
    public Object execute(OutboundConnectorContext context) throws FirebaseMessagingException, IOException {
        var serviceAccount = context.bindVariables(ServiceAccountDto.class);
        return executeConnector(serviceAccount);
    }

    private MyConnectorResult executeConnector(final ServiceAccountDto serviceAccountDto) throws FirebaseMessagingException, IOException {
        InputStream inputStream = converter(serviceAccountDto);
        FirebaseConfiguration fbc = new FirebaseConfiguration(inputStream);
        FirebaseMessagingService service = new FirebaseMessagingService(fbc.getFirebaseMessaging());


        service.execute();
        // TODO: implement connector logic


        LOGGER.info("DONE");

        LOGGER.info("Executing my connector with request {}", serviceAccountDto);
        return new MyConnectorResult("Message received");
    }

    InputStream converter(ServiceAccountDto serviceAccountDto) throws JsonProcessingException {
        Gson gson = new Gson();

        String unEscaped = StringEscapeUtils.unescapeJava(gson.toJson(serviceAccountDto));
        String credentials = unEscaped.replaceAll(Matcher.quoteReplacement("\\n"), Matcher.quoteReplacement("\n"));

        return new ByteArrayInputStream(credentials.getBytes());
    }


}
