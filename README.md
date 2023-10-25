# Firebase Cloud Messaging Connector Usage Guide

## Introduction
This README provides a comprehensive guide on how to use your connector. Follow the steps below to get started.

## Prerequisites
1. **Create a Firebase project and add a web app**.
2. **Generate a new private key**: Go to Settings -> Service Accounts in the Firebase console and press ‘Generate new private key’.
3. **Download the ServiceAccount.json file**: Click on ‘Generate key’. This will download a service account JSON file.

Please remember to securely store the JSON file containing the key. When authorizing via a service account, you have two choices for providing the credentials to your application. You can either set the `GOOGLE_APPLICATION_CREDENTIALS` environment variable, or you can explicitly pass the path to the service account key in code. The first option is more secure and is strongly recommended.


## Steps to Use the Connector

1. **Copy the JSON Object**: After generating your private key, you will receive a JSON object. Copy this object into the 'Service Account' input field in your connector setup.

2. **Choose Cloud Messaging Type**: In your connector settings, select the type of Cloud Messaging you want to use. This could be 'Topic Messaging' or 'Token Messaging', depending on your needs. You can send messages to multiple Tokens by adding multiple Tokens seperated by a comma.

3. **Add Data**: Finally, add the data that you want to send via Cloud Messaging. This could be notification data, data messages, or both.

And that's it! You have successfully set up and used your connector. If you encounter any issues or need further assistance, feel free to reach out for support.

## Runtime

Build a jar file with dependencies

```bash
mvn clean package
```
After building the jar file this can be set up to work with both SaaS or Self-Managed instances of Camunda.   
Follow the steps outlined in the [Camunda Documentation](https://docs.camunda.io/docs/guides/host-custom-connectors/)
