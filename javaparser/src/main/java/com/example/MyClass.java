package com.example;


import com.sun.org.apache.xerces.internal.dom.DeferredElementImpl;

import java.io.File;
import java.io.IOException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MyClass {


    public static void main(String[] args) {
        try
        {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder;
            docBuilder = docBuilderFactory.newDocumentBuilder();
         //   org.w3c.dom.Document document = docBuilder.parse(new File("C://Users/SAZID ALI/Desktop/staff.xml"));
            org.w3c.dom.Document document = docBuilder.parse(new File("C:/Users/aabdulqadir/Desktop/JAVA Parser/staff.xml"));
            document.getDocumentElement().normalize();
            Node rootNode=document.getDocumentElement(); //saving root node in a variable.
            NodeList nList=rootNode.getChildNodes(); //to store the child nodes as node list.
            for(int i=0;i<nList.getLength();i++)
            {
                printTags(nList.item(i));
            }
        }
        catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void printTags(Node nodes)
    {
        if (nodes.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) nodes;
            System.out.print(eElement.getAttribute("id"));
        }
        if(nodes.hasChildNodes() || nodes.getNodeType()!=3)
        {
            NodeList nl=nodes.getChildNodes();
            System.out.print(((DeferredElementImpl)nl).getTextContent().replace("\n","~").replace("\t",""));
        }
    }

}

