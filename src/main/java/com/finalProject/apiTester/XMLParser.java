package com.finalProject.apiTester;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class XMLParser {

    public static ArrayList<String> parseXML() {
        try {
            // Load the XML file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("C:\\Users\\Antonio\\Downloads\\apiTester\\target\\site\\jacoco\\jacoco.xml"));
            ArrayList<String> linesNotCovered = new ArrayList<>();
            // Get the <sourcefile> element
            NodeList sourcefileList = document.getElementsByTagName("sourcefile");
            for (int i = 0; i < sourcefileList.getLength(); i++) {
                Node sourcefileNode = sourcefileList.item(i);

                if (sourcefileNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element sourcefileElement = (Element) sourcefileNode;

                    // Extract information from the <sourcefile> element
                    String fileName = sourcefileElement.getAttribute("name");
                    System.out.println("File Name: " + fileName);

                    // Get the <line> elements
                    NodeList lineList = sourcefileElement.getElementsByTagName("line");
                    for (int j = 0; j < lineList.getLength(); j++) {
                        Node lineNode = lineList.item(j);

                        if (lineNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element lineElement = (Element) lineNode;

                            // Extract information from the <line> element
                            String lineNumber = lineElement.getAttribute("nr");
                            String instructionMissed = lineElement.getAttribute("mi");
                            if(!instructionMissed.equals("0")){
                                linesNotCovered.add(lineNumber);
                            }
                        }
                    }
                }
            }
            return linesNotCovered;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, Double> getCoveragePercentage() {
        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("C:\\Users\\Antonio\\Downloads\\apiTester\\target\\site\\jacoco\\jacoco.xml"));

            NodeList counterList = document.getElementsByTagName("counter");
            Map<String, Double> coveragePercentages = new HashMap<>();
            for (int i = 0; i < counterList.getLength(); i++) {
                Node counterNode = counterList.item(i);
                if (counterNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element counterElement = (Element) counterNode;
                    String type = counterElement.getAttribute("type");
                    int covered = Integer.parseInt(counterElement.getAttribute("covered"));
                    int missed = Integer.parseInt(counterElement.getAttribute("missed"));
                    double percentage = (double) covered / (covered + missed) * 100;
                    coveragePercentages.put(type, percentage);
                }
            }
//            System.out.println("Coverage Percentages: " + coveragePercentages);
            System.out.println("Line Coverage: " + coveragePercentages.get("LINE"));
            System.out.println("It did get the coverage percentages!");
            return coveragePercentages;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
