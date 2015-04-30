/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.i3mainz.ibr.procedure;

import de.i3mainz.ibr.img.Imageprocessing;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author s3b31293
 */
public class EquirectangulartoCubic extends Procedure {

    protected final String name = "EquirectangulartoCubic";

	@Override
    public String toString() {
        return name;
    }

    @Override
    public FileNameExtensionFilter getFileNameExtensionFilter() {
		return new FileNameExtensionFilter("Bilder","png","jpg");
    }

    @Override
    protected String getOutputFileExtension() {
        return "png";
    }
    
    
    /**
     * Initiates calculation of the cubic panorama.
     * 
     * @param source
     * @param destination
     * @param size 
     */
    //TODO Progress nicht implementiert
    @Override
    protected void perform(String source, String destination, int size) {
		if(size == 0){
			for(int i = 7; i < 12; i++){
			Imageprocessing.convertToCubes(source,destination,(int) Math.pow(2,i));
			}
		}
		else{
			Imageprocessing.convertToCubes(source,destination,size);
		}
    
    }
    
    
    @Override
    public String getProcedureDescription() {
        return "<h2>" + name + "</h2>"
                + "<b>Input:</b> image files (jgp, png, ...) <br>"
                + "<b>Output:</b> png (multiple files) <br>"
                + "<b>Parameter:</b> images size <br>"
                + "&emsp;- images size (6 output files per input file)<br>" 
                + "&emsp;- no parameter: generates 6 images and each with different heihgts (30 images per input file)<br><br>"
                
                + "Calculates 6 cubic panorama images from Rectangular projection image with a given size. "
                + "If <b>no size</b> is set, each image will be put out in 5 sizes (128px, 256px, 512px, 1024px, 2048px; 30 images per input file).";
    }

}
