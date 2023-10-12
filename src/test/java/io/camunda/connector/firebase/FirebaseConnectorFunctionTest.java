package io.camunda.connector.firebase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.camunda.connector.api.error.ConnectorException;
import io.camunda.connector.firebase.dto.ConnectorRequestDto;
import io.camunda.connector.test.outbound.OutboundConnectorContextBuilder;
import org.junit.jupiter.api.Test;

public class FirebaseConnectorFunctionTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldThrowErrorWithIncorrectCredentialFormat() throws Exception{
        //given
        FirebaseConnectorFunction firebaseConnectorFunction = new FirebaseConnectorFunction();
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
                .hasMessageContaining("Wrong Firebase credential format. Unable to create connection");
    }
}
