package com.nodero.connector.dto;

import java.io.Serializable;
import java.util.Objects;

public class ConnectorRequestDto implements Serializable {

    private String serviceAccount;
    private String messageType;
    private String tokens;
    private String topic;
    private String messageTitle;
    private String messageData;

    public String getServiceAccount() {
        return serviceAccount;
    }

    public void setServiceAccount(String serviceAccount) {
        this.serviceAccount = serviceAccount;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getTokens() {
        return tokens;
    }

    public void setTokens(String tokens) {
        this.tokens = tokens;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageData() {
        return messageData;
    }

    public void setMessageData(String messageData) {
        this.messageData = messageData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConnectorRequestDto that = (ConnectorRequestDto) o;
        return Objects.equals(serviceAccount, that.serviceAccount) && Objects.equals(messageType, that.messageType) && Objects.equals(tokens, that.tokens) && Objects.equals(topic, that.topic) && Objects.equals(messageTitle, that.messageTitle) && Objects.equals(messageData, that.messageData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceAccount, messageType, tokens, topic, messageTitle, messageData);
    }

    @Override
    public String toString() {
        return "ConnectorRequestDto{" +
                "serviceAccount='" + serviceAccount + '\'' +
                ", messageType='" + messageType + '\'' +
                ", tokens='" + tokens + '\'' +
                ", topic='" + topic + '\'' +
                ", messageTitle='" + messageTitle + '\'' +
                ", messageData='" + messageData + '\'' +
                '}';
    }
}
