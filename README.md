# Firebase Cloud Messaging Connector Usage Guide

## Introduction
This README provides a comprehensive guide on how to use your connector. Follow the steps below to get started.

## Prerequisites
Before you begin, make sure you have generated a private key for the Firebase Admin SDK. You can do this in the Firebase console under Project Settings -> Service Accounts.

## Steps to Use the Connector

1. **Copy the JSON Object**: After generating your private key, you will receive a JSON object. Copy this object into the 'Service Account' input field in your connector setup.

2. **Choose Cloud Messaging Type**: In your connector settings, select the type of Cloud Messaging you want to use. This could be 'Topic Messaging' or 'Token Messaging', depending on your needs. You can send messages to multiple Tokens by adding multiple Tokens seperated by a comma.

3. **Add Data**: Finally, add the data that you want to send via Cloud Messaging. This could be notification data, data messages, or both.

And that's it! You have successfully set up and used your connector. If you encounter any issues or need further assistance, feel free to reach out for support.
