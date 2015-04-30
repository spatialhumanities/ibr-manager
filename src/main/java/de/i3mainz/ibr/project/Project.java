package de.i3mainz.ibr.project;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Project {

    /**
     * Reads and returns the spatial context from input file (intermediate
     * ProjectXML)
     *
     * @param xml path of intermediate project xml file
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static String getProjectXML(String xml) throws ParserConfigurationException, SAXException, IOException {
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(xml));

        // read spatial context
        NodeList metadata = document.getElementsByTagName("spatialcontext").item(0).getChildNodes();
        String name = "", place = "", date = "";
        Transformation spatialcontext_transformation = new Transformation();
        for (int i = 0; i < metadata.getLength(); i++) {
            if (metadata.item(i).getNodeName().equals("name")) {
                name = metadata.item(i).getFirstChild().getTextContent();
            }
            if (metadata.item(i).getNodeName().equals("place")) {
                place = metadata.item(i).getFirstChild().getTextContent();
            }
            if (metadata.item(i).getNodeName().equals("date")) {
                date = metadata.item(i).getFirstChild().getTextContent();
            }
            if (metadata.item(i).getNodeName().equals("transformation")) {
                spatialcontext_transformation = getTransformation(metadata.item(i));
            }
        }
        Spatialcontext spatialcontext = new Spatialcontext(name, place, date, spatialcontext_transformation);
        
        // read floorplans
        NodeList floorplans = document.getElementsByTagName("floorplan");
        for (int i = 0; i < floorplans.getLength(); i++) {
            String filename = "", description = "";
            Transformation transformation = new Transformation();
            NodeList nodes = floorplans.item(i).getChildNodes();
            for (int j = 0; j < nodes.getLength(); j++) {
                if (nodes.item(j).getNodeName().equals("filename")) {
                    filename = nodes.item(j).getFirstChild().getTextContent();
                }
                if (nodes.item(j).getNodeName().equals("description")) {
                    description = nodes.item(j).getFirstChild().getTextContent();
                }
                if (nodes.item(j).getNodeName().equals("transformation")) {
                    transformation.transform(getTransformation(nodes.item(j)));
                }
            }
            spatialcontext.addFloorplan(filename, description, transformation);
        }

        // read viewpoints, transformations, point clouds and images
        NodeList nodes = document.getElementsByTagName("viewpoints").item(0).getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            String id = null;
            Node node = nodes.item(i);
            String nodeName = node.getNodeName();

            if (node.getAttributes() != null && node.getAttributes().getNamedItem("id") != null) {
                id = node.getAttributes().getNamedItem("id").getNodeValue();
            }

            if (nodeName.equals("name")) {
                spatialcontext.setName(id, node.getFirstChild().getTextContent());
            } else if (nodeName.equals("ptgfile")) {
                spatialcontext.setPtgFile(id, node.getFirstChild().getTextContent());
            } else if (nodeName.equals("trans")) {
                spatialcontext.addTrans(id, getTransformation(node));
            } else if (nodeName.equals("ptgtrans")) {
                spatialcontext.addPtgTrans(id, getTransformation(node));
            } else if (nodeName.equals("remtrans")) {
                spatialcontext.addRemTrans(id, getTransformation(node));
            } else if (nodeName.equals("rgbtrans")) {
                spatialcontext.addRgbTrans(id, getTransformation(node));
            } else if (nodeName.equals("remimg")) {
                try {
                    String panoType = node.getAttributes().getNamedItem("pano").getNodeValue();
                    spatialcontext.addRemImg(id, node.getFirstChild().getTextContent(), panoType);
                } catch (NullPointerException e) {
                    spatialcontext.addRemImg(id, node.getFirstChild().getTextContent(), null);
                }
            } else if (nodeName.equals("rgbimg")) {
                try {
                    String panoType = node.getAttributes().getNamedItem("pano").getNodeValue();
                    spatialcontext.addRgbImg(id, node.getFirstChild().getTextContent(), panoType);
                } catch (NullPointerException e) {
                    spatialcontext.addRgbImg(id, node.getFirstChild().getTextContent(), null);
                }
            }
        }

        return spatialcontext.toString();
    }

    /**
     * Returns a {@link de.i3mainz.ibr.project.Transformation} from
     * transformation node.
     *
     * @param node
     * @return
     */
    private static Transformation getTransformation(Node node) {
        NamedNodeMap attributes = node.getAttributes();
        Transformation transformation = null;
        if (attributes.getNamedItem("params") != null) {
            transformation = new Transformation(attributes.getNamedItem("params").getNodeValue());
        } else if (attributes.getNamedItem("transl-x") != null && attributes.getNamedItem("transl-y") != null && attributes.getNamedItem("transl-z") != null && attributes.getNamedItem("rot-x") != null && attributes.getNamedItem("rot-y") != null && attributes.getNamedItem("rot-z") != null) {
            transformation = new Transformation(attributes.getNamedItem("transl-x").getNodeValue(), attributes.getNamedItem("transl-y").getNodeValue(), attributes.getNamedItem("transl-z").getNodeValue(),
                    attributes.getNamedItem("rot-x").getNodeValue(), attributes.getNamedItem("rot-y").getNodeValue(), attributes.getNamedItem("rot-z").getNodeValue(), attributes.getNamedItem("scale").getNodeValue());
        } else if (attributes.getNamedItem("cols") != null && attributes.getNamedItem("rows") != null) {
            transformation = new Transformation(attributes.getNamedItem("cols").getNodeValue(), attributes.getNamedItem("rows").getNodeValue(),
                    attributes.getNamedItem("col1").getNodeValue(), attributes.getNamedItem("row1").getNodeValue(), attributes.getNamedItem("col2").getNodeValue(), attributes.getNamedItem("row2").getNodeValue(), attributes.getNamedItem("col3").getNodeValue(), attributes.getNamedItem("row3").getNodeValue(),
                    attributes.getNamedItem("x1").getNodeValue(), attributes.getNamedItem("y1").getNodeValue(), attributes.getNamedItem("x2").getNodeValue(), attributes.getNamedItem("y2").getNodeValue(), attributes.getNamedItem("x3").getNodeValue(), attributes.getNamedItem("y3").getNodeValue());
        }
        if (attributes.getNamedItem("inverse") != null) {
            transformation.inverse();
        }
        return transformation;
    }
}
