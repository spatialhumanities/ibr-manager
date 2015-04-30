/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.i3mainz.ibr.files;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

/**
 * Reads PanoglView RGB to REM transformation.xml
 *
 *
 * @author julia.ganicheva
 */
public class PanoglReader {

    public static List<TransformParam> readPanogl(String source) throws IOException {
        List<TransformParam> param = new ArrayList<TransformParam>();
        File file = new File(source);
        int j = 0;
        String name = file.getName();
        try {
            double xtrans = 0;
            double ytrans = 0;
            double ztrans = 0;
            double xrot = 0;
            double yrot = 0;
            double zrot = 0;
            double scale = 0;
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("trafo");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    xtrans = Double.valueOf(eElement.getAttribute("transl-x"));
                    ytrans = Double.valueOf(eElement.getAttribute("transl-y"));
                    ztrans = Double.valueOf(eElement.getAttribute("transl-z"));
                    xrot = Double.valueOf(eElement.getAttribute("rot-x"));
                    yrot = Double.valueOf(eElement.getAttribute("rot-y"));
                    zrot = Double.valueOf(eElement.getAttribute("rot-z"));
                    scale = Double.valueOf(eElement.getAttribute("scale"));

                }
            }

            // Translationen anpassen
            int m = 1000;
            xtrans /= m;
            ytrans /= m;
            ztrans /= m;

            // umrechnen der Rotationen (Grad nach Radiant)
            double rho = Math.PI / 200;
            xrot *= rho;
            yrot *= rho;
            zrot *= rho;

            TransformParam element = new TransformParam();
            element.setAll(name, xtrans, ytrans, ztrans, xrot, yrot, zrot, scale);
            param.add(j, element);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return param;
    }
}
