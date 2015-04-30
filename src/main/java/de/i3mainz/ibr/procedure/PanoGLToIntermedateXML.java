/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.i3mainz.ibr.procedure;

import de.i3mainz.ibr.files.PanoglReader;
import de.i3mainz.ibr.files.TransformParam;
import de.i3mainz.ibr.files.ViewpointWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author julia.ganicheva NOT WORKING; INPUT FOLDER (all files in the folder);
 * Output 1 file
 */
public class PanoGLToIntermedateXML extends Procedure {

    protected final String name = "PanoGLToIntermedateXML";

    public String toString() {
        return name;
    }

    /**
     * Initiates to read the PanoGL report and to write the intermediate
     * ProjectXML from transformation parameters.
     *
     * @param source PanoGL report
     * @param destination
     * @param param
     */
    @Override
    protected void perform(String source, String destination, int param) {
        try {
            int index = destination.lastIndexOf("\\");
            destination = destination.substring(0, index);
            destination += "\\rgbtrafo.xml";
            List<TransformParam> rgbtrans = new ArrayList<TransformParam>();
            rgbtrans = PanoglReader.readPanogl(source);
            File output = new File(destination);
            ViewpointWriter.writeScannPostion(rgbtrans, output, "rgbtrans");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public FileNameExtensionFilter getFileNameExtensionFilter() {
        return new FileNameExtensionFilter("panogl", "txt", "xml");

    }

    @Override
    protected String getOutputFileExtension() {
        return "xml";
    }
    
    
    @Override
    public String getProcedureDescription() {
        return "<h2>" + name + "</h2>"
                + "<b>Input:</b> XML (PanoGL project file)<br>"
                + "<b>Output:</b>  XML <br>"
                + "<b>Parameter:</b> - <br><br>"
                
                + "Extracts transformation parameters from one or more PanoGL project files. "
                + "The output XML file contains all transformation parameters from all input files (one line per file). ";
    }
}
