package com.nodero.connector;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nodero.connector.dto.ConnectorRequestDto;
import io.camunda.connector.test.outbound.OutboundConnectorContextBuilder;
import org.junit.jupiter.api.Test;

public class FirebaseCloudMessagingFunctionTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldThrowErrorWithIncorrectCredentialFormat() throws Exception {
        //given
        FirebaseCloudMessagingFunction firebaseConnectorFunction = new FirebaseCloudMessagingFunction();
        ConnectorRequestDto connectorRequestDto = new ConnectorRequestDto();
        connectorRequestDto.setServiceAccount("Wrong info");
        var context = OutboundConnectorContextBuilder.create()
                .variables(objectMapper.writeValueAsString(connectorRequestDto))
                .build();

        //when
        var result = catchThrowable(() -> firebaseConnectorFunction.execute(context));

        //then
        assertThat(result)
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Wrong Firebase credentials format. Unable to create connection");
    }
}