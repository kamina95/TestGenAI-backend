package com.finalProject.apiTester;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class CoverageComparisonWriter {
    public static void appendCoverageComparisonToFile( Map<String, Double> coveragePercentages1, Map<String, Double> coveragePercentages2) {
        // Ensure the file exists or create it if it doesn't
        String outputPath = "C:\\Users\\Antonio\\Downloads\\apiTester\\coveragePercentages.txt";
        File file = new File(outputPath);
        try {
            file.createNewFile(); // This method will not overwrite an existing file
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            // Adjust the column widths as needed
            String headerFormat = "%-15s %-20s %-20s %-10s %n";
            String rowFormat = "%-15s %-20.2f %-20.2f %-10.2f %n";

            // Write headers
            writer.append(String.format(headerFormat, "Metric", "Initial C (%)", "Generated C (%)", "Diff (%)"));

            for (String key : coveragePercentages1.keySet()) {
                double coverage1 = coveragePercentages1.getOrDefault(key, 0.0);
                double coverage2 = coveragePercentages2.getOrDefault(key, 0.0);
                double difference = coverage2 - coverage1;

                // Write row with formatted spacing
                writer.append(String.format(rowFormat, key, coverage1, coverage2, difference));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void appendCoverageComparisonToCSVFile(Map<String, Double> coveragePercentages1, Map<String, Double> coveragePercentages2, String className, String LLM) {
        String outputPath = "C:\\Users\\Antonio\\Downloads\\apiTester\\coveragePercentages.csv";
        File file = new File(outputPath);

        // Use LinkedHashMap to maintain the order of metrics
        Map<String, String> headerMap = new LinkedHashMap<>();
        StringBuilder dataLine = new StringBuilder();
        String classNameHeader = "ClassName";
        headerMap.put(classNameHeader, className);
        int lineCount = countLines(className);
        headerMap.put("LineCount", String.valueOf(lineCount));
        headerMap.put("LLM", LLM);

        // Iterate over the keys to build the header and data line
        dataLine.append(className);
        dataLine.append(",");
        dataLine.append(lineCount);
        dataLine.append(",");
        dataLine.append(LLM);
        for (String key : coveragePercentages1.keySet()) {
            double coverage1 = coveragePercentages1.getOrDefault(key, 0.0);
            double coverage2 = coveragePercentages2.getOrDefault(key, 0.0);
            double difference = coverage2 - coverage1;

            String iCHeader = key + "-I-C";
            String gCHeader = key + "-G-C";
            String diffHeader = key + "-Diff";

            headerMap.putIfAbsent(iCHeader, "");
            headerMap.putIfAbsent(gCHeader, "");
            headerMap.putIfAbsent(diffHeader, "");

            if (dataLine.length() > 0) {
                dataLine.append(",");
            }
            dataLine.append(String.format("%.2f,%.2f,%.2f", coverage1, coverage2, difference));
        }

        // Write to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            if (file.length() == 0) {
                // Write header only if the file is new/empty
                String headerLine = String.join(",", headerMap.keySet());
                writer.write(headerLine);
                writer.newLine();
            }
            writer.write(dataLine.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int countLines(String className) {
        String classFilePath = "C:\\Users\\Antonio\\Downloads\\apiTester\\src\\main\\java\\temp\\" + className + ".java";
        int lines = 0;
        File file = new File(classFilePath);
        System.out.println("File path: " + file.getAbsolutePath());
        System.out.println("File exists: " + file.exists());
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.readLine() != null) lines++;
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
            return -1; // Return -1 or any error indicator of your choice
        }

        return lines;
    }


    public static String getClassNameFromXML() {
        try {
            File xmlFile = new File("C:\\Users\\Antonio\\Downloads\\apiTester\\target\\site\\jacoco\\jacoco.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

            // Disable loading of external DTDs
            dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // Assuming there's only one class per XML file for simplicity
            NodeList classList = doc.getElementsByTagName("class");
            System.out.println("Class list length: " + classList.getLength());
            if (classList.getLength() > 0) {
                String fullName = classList.item(0).getAttributes().getNamedItem("name").getTextContent();
//                System.out.println("Full class name: " + fullName);
                // Extracting simple class name from the full name (e.g., "temp/NoteMarker" -> "NoteMarker")
                String[] parts = fullName.split("/");
                return parts[parts.length - 1]; // Return simple class name
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "UnknownClass"; // Default if class name can't be extracted
    }

}


