package de.i3mainz.ibr.procedure;

import javax.swing.filechooser.FileNameExtensionFilter;

import de.i3mainz.ibr.gui.MainWindow;
import de.i3mainz.ibr.pc.PTGInputStream;
import de.i3mainz.ibr.pc.PTGOutputStream;
import de.i3mainz.ibr.pc.PTGPoint;

public class PTGtoRasterPTG extends Procedure {
    
	protected final String name = "PTGtoRasterPTG";
	
	public String toString() {
		return name;
	}
	
	public FileNameExtensionFilter getFileNameExtensionFilter() {
		return new FileNameExtensionFilter("Pointcloud","PTG","ptg");
	}
	
	protected String getOutputFileExtension() {
		return "PTG";
	}
	
        
        /**
         * {@inheritDoc}
         * 
         * @param source
         * @param destination
         * @param size 
         */
	protected void perform(String source, String destination, int size) {
		try {
			PTGInputStream in = new PTGInputStream(source);        
			if (size == 0)
				size = in.getSize();
                        System.out.println(source + "\tsize: " + size);
			PTGOutputStream out = new PTGOutputStream(destination,size,in.getProperties());
			PTGPoint[] points = in.readColumn();
			double counter = 0;
			while(points != null) {
				if (!MainWindow.progressPanel().ping(counter/in.getCols()))
					break;
				for (int i=0; i<points.length; i++) {
					out.write(points[i]);
				}
				points = in.readColumn();
				counter += 1;
			}
			in.close();
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
        
        
    @Override
    public String getProcedureDescription() {
        return "<h2>" + name + "</h2>"
                + "<b>Input:</b> PTG <br>"
                + "<b>Output:</b> PTG (rasterized) <br>"
                + "<b>Parameter:</b> raster height (optional)<br><br>"
                
                + "Rasterizes a PTG file.";
    }

}
