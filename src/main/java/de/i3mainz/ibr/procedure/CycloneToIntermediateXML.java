/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.i3mainz.ibr.procedure;

import de.i3mainz.ibr.files.CycloneReader;
import de.i3mainz.ibr.files.TransformParam;
import de.i3mainz.ibr.files.ViewpointWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author julia.ganicheva
 */
public class CycloneToIntermediateXML extends Procedure {

    protected final String name = "CycloneToIntermediateXML";

    public String toString() {
        return name;
    }

    /**
     * Initiates reading of the Cyclone report, calculation of transformation
     * parameters and saving as intermediate ProjectXML.
     * 
     * @param source
     * @param destination
     * @param param 
     */
    @Override
    protected void perform(String source, String destination, int param) {
        try {
            File input = new File(source);
            List<TransformParam> trans = new ArrayList<TransformParam>();
            trans = CycloneReader.readCyclone(input);

            File output = new File(destination);
            ViewpointWriter.writeScannPostion(trans, output, "trans");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public FileNameExtensionFilter getFileNameExtensionFilter() {
        return new FileNameExtensionFilter("Cyclone", "txt", "xml");

    }

    @Override
    protected String getOutputFileExtension() {
        return "xml";
    }
    
    
    @Override
    public String getProcedureDescription() {
        return "<h2>" + name + "</h2>"
                + "<b>Input:</b> txt <br>"
                + "<b>Output:</b> xml (IntermediateXML) <br>"
                + "<b>Parameter:</b> - <br><br>"
                
                + "Exracts transformation parameters from Cyclone Report and"
                + "puts it together for the IntermediateXML.";
    }

}
