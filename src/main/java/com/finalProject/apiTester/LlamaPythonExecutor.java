package com.finalProject.apiTester;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LlamaPythonExecutor {

    public static String execute(String code, ArrayList<String> lineToCover) {
        try {
            // Convert your arguments into a JSON string
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> data = new HashMap<>();
            data.put("code_msg", createPrompt(code, lineToCover)); // Assuming createPrompt returns a string

            String requestBody = objectMapper.writeValueAsString(data);

            // Create the HttpClient
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:5000/generate_llama_response"))
//                    .uri(URI.create("http://localhost:5000/generate_response"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();

            // Send the request
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Check the response
            if (response.statusCode() == 200) {
                // Parse the JSON response body to extract the "response" field
                Map<String,Object> responseMap = objectMapper.readValue(response.body(), Map.class);
                String responseBody =  (String) responseMap.get("response");
                String tests =extractContent(responseBody);
                System.out.println(tests);
                return tests;
            } else {
                // Handle non-200 responses
                System.err.println("API request failed with status code: " + response.statusCode());
                return "error";
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String passError(String errorMessage,String code) {
        try {
            // Convert your arguments into a JSON string
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> data = new HashMap<>();
            data.put("code_msg", createErrorPrompt(errorMessage, code)); // Assuming createPrompt returns a string

            String requestBody = objectMapper.writeValueAsString(data);

            // Create the HttpClient
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:5000/generate_llama_error_response"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();

            // Send the request
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Check the response
            if (response.statusCode() == 200) {
                // Parse the JSON response body to extract the "response" field
                Map<String,Object> responseMap = objectMapper.readValue(response.body(), Map.class);
                String responseBody = (String) responseMap.get("response");

                // Here you can process the responseBody further if needed
                System.out.println(responseBody);
                return responseBody;
            } else {
                // Handle non-200 responses
                System.err.println("API request failed with status code: " + response.statusCode());
                return "error";
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String createErrorPrompt(String errorMessage, String code){
        String prompt = "The code below has an error: " + errorMessage;
        prompt += "\n\nSolve the error from this code:";
        prompt += "\n\n" + code;
        System.out.println(prompt);
        return prompt;
    }

    public static String createPrompt(String code, ArrayList<String> lineToCover){
        String prompt = "The code below is missing test coverage for the following lines: ";
        for(String line: lineToCover){
            prompt += line + ", ";
        }
        prompt += "\n\nWrite the tests to cover the lines above from this code:";
        prompt += "\n\n" + code;
        System.out.println(prompt);
        return prompt;
    }

    public static String extractContent(String input) {
        // This pattern matches content between ```java and ```, capturing the content in between
        Pattern pattern = Pattern.compile("```java(.*?)```", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(input);

        // Check if the pattern is found
        if (matcher.find()) {
            // Return the content between the delimiters
            return matcher.group(1).trim(); // .trim() to remove leading and trailing whitespaces
        }

        // Return null or an appropriate response if no match is found
        return null;
    }

}
