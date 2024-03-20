package com.finalProject.apiTester;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;

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
//                            String instructionCovered = lineElement.getAttribute("ci");
//                            String branchMissed = lineElement.getAttribute("mb");
//                            String branchCovered = lineElement.getAttribute("cb");

//                            System.out.println("Line Number: " + lineNumber);
//                            System.out.println("Instruction Missed: " + instructionMissed);
//                            System.out.println("Instruction Covered: " + instructionCovered);
//                            System.out.println("Branch Missed: " + branchMissed);
//                            System.out.println("Branch Covered: " + branchCovered);
//                            System.out.println("---------------------");

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
}
