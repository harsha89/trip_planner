/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Romesh
 */
public class XMLReader {
    
    public NodeList readCityInfo(String filename){
        
        NodeList listOfcities = null;
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = (Document) docBuilder.parse (new File("src\\data\\"+filename));
            
            // normalize text representation
            doc.getDocumentElement().normalize();
            listOfcities = doc.getElementsByTagName("city");

            
        } catch (SAXException ex) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listOfcities;
        
    }
    
public NodeList readAdjacencyMatrix(String filename){
        
        NodeList listOfcities = null;
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = (Document) docBuilder.parse (new File("src\\data\\"+filename));
            
            // normalize text representation
            doc.getDocumentElement().normalize();
            listOfcities = doc.getElementsByTagName("row");

            
        } catch (SAXException ex) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listOfcities;
        
    }
    
    public NodeList readMapper(String filename){
        
        NodeList mapping = null;
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = (Document) docBuilder.parse (new File(filename));
            
            // normalize text representation
            doc.getDocumentElement().normalize();
            mapping = doc.getElementsByTagName("place");

            
        } catch (SAXException ex) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return mapping;
        
    }
}
