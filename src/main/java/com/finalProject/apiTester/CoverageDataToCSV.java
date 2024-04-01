package com.finalProject.apiTester;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CoverageDataToCSV {

    public static int countLinesOfClass(String classFilePath) {
        int lineCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(classFilePath))) {
            while (reader.readLine() != null) lineCount++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineCount;
    }

    public static void appendCoverageDataToCSV(String csvFilePath,  Map<String, Double> initialCoverage, Map<String, Double> generatedCoverage, Map<String, Double> diffCoverage) {
        // Ensure the CSV file exists or create it if it doesn't
        File file = new File(csvFilePath);
        try {
            file.createNewFile(); // This method will not overwrite an existing file
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            // Start with the number of lines in the class
//            writer.append(String.valueOf(numberOfLines));

            // For each metric, append the initial coverage, generated coverage, and difference
            for (String key : initialCoverage.keySet()) {
                writer.append(",");
                writer.append(String.format("%.2f", initialCoverage.get(key)));
                writer.append(",");
                writer.append(String.format("%.2f", generatedCoverage.get(key)));
                writer.append(",");
                writer.append(String.format("%.2f", diffCoverage.get(key)));
            }
            writer.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Example usage
//        String classFilePath = "path/to/your/Class.java";
//        String csvFilePath = "path/to/your/coverageData.csv";
        String csvFilePath = "C:\\Users\\Antonio\\Downloads\\apiTester\\coveragePercentages.csv";

        // Assuming these maps are already populated with your coverage data
        Map<String, Double> initialCoverage = new HashMap<>();
        Map<String, Double> generatedCoverage = new HashMap<>();
        Map<String, Double> diffCoverage = new HashMap<>();

        // Calculate the number of lines in the class
//        int numberOfLines = countLinesOfClass(classFilePath);

        // Append the coverage data to the CSV
        appendCoverageDataToCSV(csvFilePath, initialCoverage, generatedCoverage, diffCoverage);
    }
}
