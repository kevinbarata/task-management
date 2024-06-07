//package com.project.task.util;
//import java.io.IOException;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//public class ExternalServiceConnector {
//
//    public void connectToExternalService(String serviceUrl) {
//        try {
//            URL url = new URL(serviceUrl);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//            connection.setConnectTimeout(5000);
//            connection.setReadTimeout(5000);
//            int responseCode = connection.getResponseCode();
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                System.out.println("Connected to external service successfully.");
//            } else {
//                System.out.println("Failed to connect to external service. Response code: " + responseCode);
//            }
//            connection.disconnect();
//        } catch (IOException e) {
//            System.out.println("Error connecting to external service: " + e.getMessage());
//        } catch (Exception e) {
//            System.out.println("An unexpected error occurred: " + e.getMessage());
//        }
//    }
//
//    public static void main(String[] args) {
//        ExternalServiceConnector connector = new ExternalServiceConnector();
//        connector.connectToExternalService("https://example.com/api");
//    }
//}
