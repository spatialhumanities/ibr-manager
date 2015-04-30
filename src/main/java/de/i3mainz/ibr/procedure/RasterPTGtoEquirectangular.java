package de.i3mainz.ibr.procedure;

import javax.swing.filechooser.FileNameExtensionFilter;

import de.i3mainz.ibr.gui.MainWindow;
import de.i3mainz.ibr.img.*;
import de.i3mainz.ibr.math.OnlineStatistic;
import de.i3mainz.ibr.pc.*;

public class RasterPTGtoEquirectangular extends Procedure {
	
	protected final String name = "RasterPTGtoEquirectangular";
	
	public String toString() {
		return name;
	}
	
	public FileNameExtensionFilter getFileNameExtensionFilter() {
		return new FileNameExtensionFilter("Raster Pointcloud","PTG","ptg");
	}
	
	protected String getOutputFileExtension() {
		return "png";
	}
	
        /**
         * {@inheritDoc } <br>
         * Sets statistic for remission. Writes png file. 
         * 
         * @param source
         * @param destination
         * @param size 
         */
	protected void perform(String source, String destination, int size) {
		try {
			
			PTGInputStream in = new PTGInputStream(source);
			size = in.getSize();
			
                        // statistic for remission
			OnlineStatistic os = new OnlineStatistic(100);
			for (int i=0; i<1000; i++) {
				int col = (int)(2*size*Math.random());
				PTGPoint[] points = in.readColumn(col);
				for (int j=0; j<points.length; j++) {
					float rem = points[j].getRemission();
					if (rem > 0)
						os.add(rem);
				}
			}
			in.close();
			os.print();
			
                        // read points, write png
			in = new PTGInputStream(source);
			PTGPoint[] points = in.readFullColumn();
			PNGOutputStream png = new PNGOutputStream(destination,points.length);
			png.setRange(os.getMin(),os.getMax());
			int i=0;
			while(points != null) {
				if (!MainWindow.progressPanel().ping(1.0*i/in.getCols()))
					break;
				png.writeColumn(points,i);
				points = in.readFullColumn();
				i++;
			}
			png.close();
			in.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
        
        
        
    @Override
    public String getProcedureDescription() {
        return "<h2>" + name + "</h2>"
                + "<b>Input:</b> PTG (rasterized) <br>"
                + "<b>Output:</b> png <br>"
                + "<b>Parameter:</b> - <br><br>"
                
                + "Calculates a Rectangular projection image from the rasterized point cloud.";
    }

}
