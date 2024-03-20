package com.finalProject.apiTester;

import javax.tools.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.finalProject.apiTester.PythonExecutor.passError;

public class CompileCode {

    private static String classCode;
    private static String testCode;
    private static String errorMessage;


    public static String startCompile(CodeSubmission codeSubmission) {
        classCode = codeSubmission.getCode();
        testCode = codeSubmission.getTests();
        String response = tryCompileCode();
        if(response.equals("success")){
            return callPython("FirstAssistant");
        }else {
            int count = 1;
            while(response.equals("error") && count < 3){
                System.out.println("retrying..." + count );
                System.out.println(errorMessage);
                count++;
                String newTests = passError(errorMessage, classCode, "RetryAssistant");
                response = tryCompileCode();
                if(response.equals("success")){
                    return newTests;
                }
            }
        }

        return "error";
    }


    public static String tryCompileCode() {
//        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
//        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        try{
            writeCodeToFile(getClassName(classCode), classCode);
            writeTestToFile(getClassName(testCode), testCode);
            ProcessBuilder testBuilder = new ProcessBuilder("C:\\Program Files\\apache-maven-3.9.5\\bin\\mvn.cmd", "test", "-Dtest=" + getClassName(testCode));
            testBuilder.redirectErrorStream(true); // Merge error output with standard output
            Process testProcess = testBuilder.start();

            // Read the combined output (standard + error)
            BufferedReader testReader = new BufferedReader(new InputStreamReader(testProcess.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = testReader.readLine()) != null) {
                System.out.println(line);
                output.append(line).append("\n"); // Collect output for potential parsing
            }

            int testExitCode = testProcess.waitFor();
            if (testExitCode != 0) {
                System.out.println("Tests or compilation failed.");
                System.out.println("retrying...");
                System.out.println(output); // Print the output for debugging
                errorMessage = output.toString();
                return "error";
            } else {
                System.out.println("Tests passed successfully.");
                return "success";
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


//    public static String checkIfCompile(String newTests) throws IOException {
//        testCode = newTests;
//        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
//        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
//        try {
//            File classFile = writeCodeToFile(getClassName(classCode), classCode);
//            File testFile = writeTestToFile(getClassName(testCode), testCode);
//
//            System.out.println(classFile.getAbsolutePath());
//            System.out.println(testFile.getAbsolutePath());
//
//            // Configure the ProcessBuilder to include error output
//            ProcessBuilder testBuilder = new ProcessBuilder("C:\\Program Files\\apache-maven-3.9.5\\bin\\mvn.cmd", "test", "-Dtest=" + getClassName(testCode));
//            testBuilder.redirectErrorStream(true); // Merge error output with standard output
//            Process testProcess = testBuilder.start();
//
//            // Read the combined output (standard + error)
//            BufferedReader testReader = new BufferedReader(new InputStreamReader(testProcess.getInputStream()));
//            StringBuilder output = new StringBuilder();
//            String line;
//            while ((line = testReader.readLine()) != null) {
//                System.out.println(line);
//                output.append(line).append("\n"); // Collect output for potential parsing
//            }
//
//            int testExitCode = testProcess.waitFor();
//            if (testExitCode != 0) {
//                System.out.println("Tests or compilation failed.");
//                System.out.println("retrying");
//                System.out.println(output); // Print the output for debugging
//                return passError(output.toString(), classCode, "RetryAssistant");
//            } else {
//                System.out.println("Tests passed successfully.");
//            }
//
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//            if (e instanceof InterruptedException) {
//                Thread.currentThread().interrupt(); // Re-interrupt the thread if it was interrupted
//            }
//        } finally {
//            try {
//                fileManager.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return testCode;
//    }
//
//    public static String compile(CodeSubmission codeSubmission) {
//
//        classCode = codeSubmission.getCode();
//        testCode = codeSubmission.getTests();
//
//
//        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
//        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
//
//        try {
//
//            File classFile = writeCodeToFile(getClassName(classCode), classCode);
//            File testFile = writeTestToFile(getClassName(testCode), testCode);
//
//            System.out.println(classFile.getAbsolutePath());
//            System.out.println(testFile.getAbsolutePath());
//
//            //Runtime.getRuntime().exec("C:\\Program Files\\apache-maven-3.9.5\\bin\\mvn.cmd test -Dtest=HelloWorldTest");
//            ProcessBuilder testBuilder = new ProcessBuilder("C:\\Program Files\\apache-maven-3.9.5\\bin\\mvn.cmd", "test", "-Dtest="+getClassName(testCode));
//            Process testProcess = testBuilder.start();
//
//            // Print the output of the test execution
//            BufferedReader testReader = new BufferedReader(new InputStreamReader(testProcess.getInputStream()));
//            String testLine;
//            while ((testLine = testReader.readLine()) != null) {
//                System.out.println(testLine);
//            }
//
//            int testExitCode = testProcess.waitFor();
//            if (testExitCode != 0) {
//                System.out.println("Tests failed.");
//            } else {
//                System.out.println("Tests passed successfully.");
//            }
//
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//
//            ArrayList<String> linesNotCovered = XMLParser.parseXML();
//            for(String line: linesNotCovered){
//                System.out.println(line);
//            }
////            if (classCode.contains("package ")){
////                return PythonExecutor.execute(classCode, linesNotCovered);
////            }
//            String testResponse = PythonExecutor.execute(getCompleteCode(classCode), linesNotCovered, "FirstAssistant");
//            return checkIfCompile(testResponse);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } finally {
//            try {
//                fileManager.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }

    public static String callPython(String assistantName){
        ArrayList<String> linesNotCovered = XMLParser.parseXML();
        for(String line: linesNotCovered){
            System.out.println(line);
        }
        return PythonExecutor.execute(classCode, linesNotCovered, assistantName);
    }


    public static String getClassName(String code){
        String[] codeArr = code.split("[\\n\\s]+");

        for(int i = 0; i < codeArr.length - 2; i++){
            if(codeArr[i].equals("public") && codeArr[i + 1].equals("class")){
                return codeArr[i+2];
            }
        }
        return null;
    }

    private static File writeCodeToFile(String fileName, String code) throws IOException {
        String directoryPath = "C:\\Users\\Antonio\\Downloads\\apiTester\\src\\main\\java\\temp";
        Path directory = Path.of(directoryPath);
        Path filePath = directory.resolve(fileName+".java");
        Files.write(filePath, getCompleteCode(code).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        return filePath.toFile();
    }

    private static String getCompleteCode(String code) throws IOException {
        if(code.contains("package ")){
            //remove package declaration
            String[] codeArr = code.split("\\n+");
            StringBuilder newCode = new StringBuilder();
            for(int i = 0; i < codeArr.length; i++){
                if(codeArr[i].contains("package")){
                    i ++;
                }
                newCode.append(codeArr[i]).append("\n");
            }
            return "package temp; \n\n" + newCode;
        }
        return "package temp; \n\n" + code;
    }



    private static File writeTestToFile(String fileName, String code) throws IOException {
        String directoryPath = "C:\\Users\\Antonio\\Downloads\\apiTester\\src\\test\\java\\temp";
        Path directory = Path.of(directoryPath);
        code = getCompleteCode(code);
        Path filePath = directory.resolve(fileName+".java");
        Files.write(filePath, code.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        return filePath.toFile();
    }

    // Method to extract content between ```java and ```
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
