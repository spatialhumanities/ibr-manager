package de.i3mainz.ibr.img;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Equirectangular {
	
	private byte[] data;
	private int width, height;
	private int pixelSize;
	
	int getPixelSize() {
		return pixelSize;
	}
/**
 * 
 * @param array output array
 * @param pos possition in the output array
 * @param h
 * @param v
 * @param counter
 */
	
	void getData(byte[] array, int pos, float h, float v,int counter) {
		int j = (int)(height*v/Math.PI);
		if (j < 0) j = 0;
		if (j >= height) j = height-1;
		int i = (int)(width*(Math.PI-h)/(2*Math.PI));
		if (i < 0) i = 0;
		if (i >= width) i = width-1;

		for (int k = 0; k < pixelSize; k++) {
			int oldValue = array[pos * pixelSize + k];
			oldValue = (oldValue + 256) % 256;
			int newValue = (data[((j) * width + (i)) * pixelSize + k] + 256) % 256;
			array[pos * pixelSize + k] = (byte)(((oldValue * counter) + newValue) / (counter + 1));

		}

	}
	
	static Equirectangular read(String filename) throws IOException {
		Equirectangular file = new Equirectangular();
		BufferedImage img = ImageIO.read(new File(filename));
		file.pixelSize = setPixelSize(img.getType());
		file.width = img.getWidth();
		file.height = img.getHeight();
		file.data = (byte[])img.getData().getDataElements(0,0,file.width,file.height,null);
		return file;
	}
	
	private static int setPixelSize(int imgType) {
		switch(imgType) {
		case BufferedImage.TYPE_BYTE_GRAY: return 1;
		case BufferedImage.TYPE_3BYTE_BGR: return 3;
		default: return 0;
		}
	}

	public int getWidth(){
		return this.width;
	}
}
