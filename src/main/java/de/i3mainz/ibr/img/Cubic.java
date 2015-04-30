package de.i3mainz.ibr.img;

import de.i3mainz.ibr.gui.MainWindow;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Cubic {

	enum Face {

		FRONT, RIGHT, BACK, LEFT, TOP, BOTTOM;
	}

	private static float smoothParm = 0.5f;
	
        /**
         * Calculates and saves a face of the cubic panorama from equirectangular.
         * 
         * @param filename
         * @param input
         * @param face
         * @param resolution
         * @return
         * @throws IOException 
         */
	static boolean export(String filename, Equirectangular input, Face face, int[] resolution) throws IOException {
		for (int r = 0; r < resolution.length; r++) {
			int res = resolution[r];
			double part = 0.0;
			switch (face) {
				case FRONT:
					part = 0.1;
					break;
				case RIGHT:
					part = 0.25;
					break;
				case BACK:
					part = 0.4;
					break;
				case LEFT:
					part = 0.55;
					break;
				case TOP:
					part = 0.7;
					break;
				case BOTTOM:
					part = 0.85;
					break;
			}
			byte[] output = new byte[res * res * input.getPixelSize()];
			for (int i = 0; i < res; i++) {
				MainWindow.progressPanel().ping(part + 1.0 * i / res * 0.25);
				for (int j = 0; j < res; j++) {
					float x, y, z;
					float h = 0;
					float v = 0;
					int counter=0;
					for (int a = -1; a <= 1; a++) {
						for (int b = -1; b <= 1; b++) {
							switch (face) {
								case FRONT:
									x = 1.0f;
									y = -2.0f * i / res + 1.0f +smoothParm*a/res;
									z = -2.0f * j / res + 1.0f +smoothParm*b/res;
									h = (float) Math.atan2(y, x);
									v = (float) Math.acos(z / Math.sqrt(x * x + y * y + z * z));
									break;
								case RIGHT:
									x = -2.0f * i / res + 1.0f +smoothParm*a/res;
									y = -1.0f;
									z = -2.0f * j / res + 1.0f +smoothParm*a/res;
									h = (float) Math.atan2(y, x);
									v = (float) Math.acos(z / Math.sqrt(x * x + y * y + z * z));
									break;
								case BACK:
									x = -1.0f;
									y = 2.0f * i / res - 1.0f +smoothParm*a/res;
									z = -2.0f * j / res + 1.0f +smoothParm*a/res;
									h = (float) Math.atan2(y, x);
									v = (float) Math.acos(z / Math.sqrt(x * x + y * y + z * z));
									break;
								case LEFT:
									x = 2.0f * i / res - 1.0f +smoothParm*a/res;
									y = 1.0f;
									z = -2.0f * j / res + 1.0f +smoothParm*a/res;
									h = (float) Math.atan2(y, x);
									v = (float) Math.acos(z / Math.sqrt(x * x + y * y + z * z));
									break;
								case TOP:
									x = 2.0f * j / res - 1.0f +smoothParm*a/res;
									y = -2.0f * i / res + 1.0f +smoothParm*a/res;
									z = 1.0f;
									h = (float) Math.atan2(y, x);
									v = (float) Math.acos(z / Math.sqrt(x * x + y * y + z * z));
									break;
								case BOTTOM:
									x = -2.0f * j / res + 1.0f +smoothParm*a/res;
									y = -2.0f * i / res + 1.0f +smoothParm*a/res;
									z = -1.0f;
									h = (float) Math.atan2(y, x);
									v = (float) Math.acos(z / Math.sqrt(x * x + y * y + z * z));
									break;
							}
							input.getData(output, j * res + i, h, v, counter);
							counter++;
						}
					}
				}
			}
			BufferedImage image;
			switch (input.getPixelSize()) {
				case 1:
					image = new BufferedImage(res, res, BufferedImage.TYPE_BYTE_GRAY);
					break;
				case 3:
					image = new BufferedImage(res, res, BufferedImage.TYPE_3BYTE_BGR);
					break;
				default:
					return false;
			}
			WritableRaster wr = image.getRaster();
			wr.setDataElements(0, 0, res, res, output);
			ImageIO.write(image, "png", new java.io.File(filename + "_" + res + ".png"));
		}
		return true;
	}

}
