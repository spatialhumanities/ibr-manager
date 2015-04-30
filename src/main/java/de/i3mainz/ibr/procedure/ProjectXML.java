package de.i3mainz.ibr.procedure;

import de.i3mainz.ibr.project.Project;
import java.io.PrintWriter;
//import java.time.Clock;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ProjectXML extends Procedure {

    protected final String name = "CreateProjectXML";

    @Override
    public String toString() {
        return name;
    }

    /**
     * Creates ProjectXML from intermediate ProjectXML.
     *
     * @param source
     * @param destination
     * @param param
     */
    @Override
    protected void perform(String source, String destination, int param) {
        destination = modifyDestination(destination);
        
        try (PrintWriter out = new PrintWriter(destination)) {
            String xml = Project.getProjectXML(source);
            out.println(xml);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Replaces "intermediate" in file name or adds "ProjectXML" to it
     * .
     * @param destination
     * @return 
     */
    private String modifyDestination(String destination) {
        String path = destination.substring(0, destination.lastIndexOf("\\")+1);
        String filename = destination.substring(destination.lastIndexOf("\\") + 1);
        if(filename.toLowerCase().contains("intermediate"))
            destination = path + filename.replaceAll("(?i)intermediate", "ProjectXML");
        else {
            String t = filename.substring(filename.lastIndexOf("."));
            filename = filename.substring(0, filename.lastIndexOf("."));
            destination = path + filename + "_ProjectXML" + t;
        }
        return destination;
    }

    @Override
    public FileNameExtensionFilter getFileNameExtensionFilter() {
        return new FileNameExtensionFilter("ConfigXML", "xml");
    }

    @Override
    protected String getOutputFileExtension() {
        return "xml";
    }
    
    
    @Override
    public String getProcedureDescription() {
        return "<h2>" + name + "</h2>"
                + "<b>Input:</b> XML (Intermediate ProjectXML) <br>"
                + "<b>Output:</b> XML <br>"
                + "<b>Parameter:</b> - <br><br>"
                
                + "Creates from the intermediate ProjectXML the extended ProjectXML that is used for project integreation.";
    }

}
