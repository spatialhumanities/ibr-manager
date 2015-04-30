package de.i3mainz.ibr.procedure;

import de.i3mainz.ibr.gui.MainWindow;
import de.i3mainz.ibr.pc.PTGInputStream;
import de.i3mainz.ibr.pc.PTGOutputStream;
import de.i3mainz.ibr.pc.PTGPoint;
import de.i3mainz.ibr.pc.XYZInputStream;
import javax.swing.filechooser.FileNameExtensionFilter;

public class XYZtoRasterPTG extends Procedure {
    
	protected final String name = "XYZtoRasterPTG";
	
	public String toString() {
		return name;
	}
	
	public FileNameExtensionFilter getFileNameExtensionFilter() {
		return new FileNameExtensionFilter("Pointcloud","XYZ","xyz");
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
			XYZInputStream in = new XYZInputStream(source);
			PTGOutputStream out = new PTGOutputStream(destination,size,PTGPoint.FLOAT_XYZ_REM);
			PTGPoint point = in.readPoint();
			double counter = 0;
			while(point != null) {
				if (!MainWindow.progressPanel().ping(counter/in.size()))
					break;
				out.write(point);
				point = in.readPoint();
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
        String descr = "<h2>" + name + "</h2>"
                + "<b>Input:</b> xyz <br>"
                + "<b>Output:</b> PTG (rasterized) <br>"
                + "<b>Parameter:</b> number of lines in the raster <br><br>"
                
                + "Calculates from a whole point cloud a rastered point cloud for one view point. "
                + "The view point is defined by the first point of the input file.";
        return descr;
    }
}
