/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.i3mainz.ibr.procedure;

import java.io.File;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author s3b31293
 */
public class Move extends Procedure {

    protected final String name = "Move";

    public String toString() {
        return name;
    }

    @Override
    protected void perform(String source, String destination, int param) {
        try {
            destination += source.substring(source.lastIndexOf(".") + 1);
            if (!new File(source).getParentFile().exists()) {
                new File(source).mkdirs();
            }
            java.nio.file.Files.move(Paths.get(source), Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public FileNameExtensionFilter getFileNameExtensionFilter() {
        return new FileNameExtensionFilter("Projekt Dateien", "PTG", "ptg", "png");
    }

    @Override
    protected String getOutputFileExtension() {
        return "";
    }

    
    @Override
    public String getProcedureDescription() {
        return "<h2>" + name + "</h2>"
                + "<b>Input:</b> files of all types can be used <br>"
                + "<b>Output:</b> file type is unmodified <br>"
                + "<b>Parameter:</b> - <br>"
                + "<font color=\"red\"><b><u>Attention!</u></b></font> "
                + "The file is deleted in the primary directory while it is moved.<br><br>"
                
                + "";
    }
}
