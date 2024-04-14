package com.finalProject.apiTester;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Map;

import static com.finalProject.apiTester.CoverageComparisonWriter.getClassNameFromXML;
import static com.finalProject.apiTester.PythonExecutor.passError;
import static com.finalProject.apiTester.XMLParser.getCoveragePercentage;

public class CompileCode {

    private static String classCode;
    private static String testCode;
    private static String errorMessage;


    public static String startCompile(CodeSubmission codeSubmission) {
        classCode = codeSubmission.getCode();
        testCode = codeSubmission.getTests();
        String response = tryCompileCode();
        Map<String, Double> initialcoveragePercentages = getCoveragePercentage();
        if(response.equals("success")){
            String className = getClassNameFromXML();
            System.out.println("calling python");
            callPython("FirstAssistant");
            response = tryCompileCode();
            if(response.equals("success")){
                Map<String, Double> generatedCoveragePercentages = getCoveragePercentage();
                assert initialcoveragePercentages != null;
                CoverageComparisonWriter.appendCoverageComparisonToFile(initialcoveragePercentages, generatedCoveragePercentages);
                CoverageComparisonWriter.appendCoverageComparisonToCSVFile(initialcoveragePercentages, generatedCoveragePercentages, className, "GPT-4");
                return testCode;
            }else {
                System.out.println("error");
                int count = 1;
                while(response.equals("error") && count < 3){
                    System.out.println("retrying..." + count );
                    System.out.println(errorMessage);
                    count++;
                    String newTests = passError(errorMessage, classCode, "RetryAssistant");
                    response = tryCompileCode();
                    if(response.equals("success")){
                        Map<String, Double> generatedCoveragePercentages = getCoveragePercentage();
                        CoverageComparisonWriter.appendCoverageComparisonToCSVFile(initialcoveragePercentages, generatedCoveragePercentages, className, "GPT-4");
                        return newTests;
                    }
                }
//                CoverageComparisonWriter.appendErrorMessageToCsv(response, className,"GPT-4");
            }
        }else{
            return "The code below has an error: " + errorMessage + "\n\nSolve the error from this code:\n\n" + classCode;
        }

        return "error";
    }

    public static String tryCompileCode() {
        try{
            writeCodeToFile(getClassName(classCode), classCode);
            writeTestToFile(getClassName(testCode), testCode);
//            ProcessBuilder testBuilder = new ProcessBuilder("C:\\Program Files\\apache-maven-3.9.5\\bin\\mvn.cmd", "test", "-Dtest=" + getClassName(testCode));
            ProcessBuilder testBuilder = new ProcessBuilder("C:\\Program Files\\apache-maven-3.9.5\\bin\\mvn.cmd", "test");
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
//                return determineErrorType(errorMessage);
                return "error";
            } else {
                System.out.println("Tests passed successfully.");
                return "success";
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


    private static String determineErrorType(String output) {
        if (output.contains("COMPILATION ERROR")) {
            return "CompilationError";
        } else if (output.contains("There are test failures")) {
            return "TestsFailedError";
        } else if (output.contains("Could not find or load main class")) {
            return "ClassNotFoundError";
        } else if (output.contains("BUILD FAILURE")) {
            return "BuildFailureError";
        } else if (output.contains("Failed to execute goal")) {
            if (output.contains("SurefireReport")) {
                return "SurefireReportError";
            }
            return "ExecutionGoalFailure";
        } else if (output.contains("Non-resolvable parent POM")) {
            return "ParentPomNotFoundError";
        } else if (output.contains("Plugin execution not covered by lifecycle configuration")) {
            return "LifecycleConfigurationError";
        } else if (output.contains("Project dependencies could not be resolved")) {
            return "DependenciesResolutionError";
        } else if (output.contains("No tests were executed")) {
            return "NoTestsExecutedError";
        } else if (output.contains("The forked VM terminated without saying properly goodbye")) {
            return "VMCrashError";
        } else if (output.contains("NullPointerException")) {
            return "NullPointerException";
        } else if (output.contains("ArrayIndexOutOfBoundsException")) {
            return "ArrayIndexOutOfBoundsException";
        } else if (output.contains("IllegalArgumentException")) {
            return "IllegalArgumentException";
        } else if (output.contains("RuntimeException")) {
            return "RuntimeExceptionError";
        } else {
            return "UnknownError";
        }
    }


    public static void callPython(String assistantName){
        ArrayList<String> linesNotCovered = XMLParser.parseXML();
        getCoveragePercentage();
        for(String line: linesNotCovered){
            System.out.println(line);
        }
        testCode =  PythonExecutor.execute(classCode, linesNotCovered, assistantName);
        try {
            assert testCode != null;
            writeTestToFile(getClassName(testCode), testCode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    public static void deleteTempFiles(String pathToDirectory){
        File directory = new File(pathToDirectory);
        File[] files = directory.listFiles(); // This lists all files in the directory

        if (files != null) { // Make sure the directory is not empty
            for (File file : files) {
                if (file.delete()) {
                    System.out.println(file.getName() + " was deleted successfully.");
                } else {
                    System.out.println("Failed to delete " + file.getName());
                }
            }
        }

    }
    public static void cleanAllFiles(){
        deleteTempFiles("C:\\Users\\Antonio\\Downloads\\apiTester\\src\\main\\java\\temp");
        deleteTempFiles("C:\\Users\\Antonio\\Downloads\\apiTester\\src\\test\\java\\temp");
        try {
            new ProcessBuilder("C:\\Program Files\\apache-maven-3.9.5\\bin\\mvn.cmd", "clean").start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
