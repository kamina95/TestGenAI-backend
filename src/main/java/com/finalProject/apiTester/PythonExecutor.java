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

public class PythonExecutor {
    public static void main(String[] args) {

    }

    public static String execute(String code, ArrayList<String> lineToCover, String assistant) {
        try {
            // Convert your arguments into a JSON string
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> data = new HashMap<>();
            data.put("code_msg", createPrompt(code, lineToCover)); // Assuming createPrompt returns a string
            data.put("assistant_name", assistant);

            String requestBody = objectMapper.writeValueAsString(data);

            // Create the HttpClient
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:5000/generate_response"))
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
                String responseBody = (String) responseMap.get("response");

                // Here you can process the responseBody further if needed
                String tests =extractContent(responseBody);
//                if(tests != null){
//                    tests = formatJavaCode(tests);
//                }
                System.out.println(tests);
                return tests;
            } else {
                // Handle non-200 responses
                System.err.println("API request failed with status code: " + response.statusCode());
                return null;
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "error";
        }
    }

    public static String passError(String errorMessage,String code, String assistant) {
        try {
            // Convert your arguments into a JSON string
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> data = new HashMap<>();
            data.put("prompt", createErrorPrompt(errorMessage, code)); // Assuming createPrompt returns a string
            data.put("assistant_name", assistant); //RetryAssistant

            String requestBody = objectMapper.writeValueAsString(data);

            // Create the HttpClient
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:5000/generate_response"))
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
                String tests =extractContent(responseBody);
                System.out.println(tests);
                return tests;
            } else {
                // Handle non-200 responses
                System.err.println("API request failed with status code: " + response.statusCode());
                return null;
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

    public static String formatJavaCode(String input) {
        // Step 1: Basic replacements for new lines and spaces
        String formatted = input
                .replace(";", ";\n") // New line after semicolon
                .replaceAll("\\{", "{\n") // New line after opening brace
                .replaceAll("\\}", "\n}\n") // New line before closing brace
//                .replaceAll("package ", "\npackage ") // New line before package
                .replaceAll("import ", "\nimport ") // New line before import
                .replace("public", "\npublic") // New line before public
                .replace("private", "\nprivate") // New line before private
                .replace("@Test", "\n@Test"); // New line before if
        // Step 2: Adding indentation
        int indentLevel = 0;
        StringBuilder indented = new StringBuilder();
        for (String line : formatted.split("\n")) {
            if (line.contains("}")) indentLevel--; // Decrease indent before closing brace
            char[] indent = new char[indentLevel * 4]; // Assuming 4 spaces per indent level
            java.util.Arrays.fill(indent, ' ');
            indented.append(indent).append(line.trim()).append("\n");
            if (line.contains("{")) indentLevel++; // Increase indent after opening brace
        }

        return indented.toString();
    }
}
