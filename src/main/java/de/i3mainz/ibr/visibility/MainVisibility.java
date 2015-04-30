/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.i3mainz.ibr.visibility;

import de.i3mainz.ibr.geometry.Point;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author s3b31293
 */
public class MainVisibility {

	//change to NOT void for Server aplication
	public static void visWithGround(Point[] objectPoints, double zPlane, double ignoreOffset, String discreteSpace, String wallColor, String fromColor, String toColor) throws IOException, Exception {
		DiscreteSpaceFileWriter dsfw = new DiscreteSpaceFileWriter(discreteSpace);
		ArrayList<boolean[]> visAnalysis = dsfw.visibillity(objectPoints, zPlane, ignoreOffset);
		BufferedImage image = dsfw.visExportForObjects(visAnalysis , fromColor,toColor);
		BufferedImage groundImage = dsfw.export(zPlane, wallColor);
		BufferedImage combine = combineImages(image, groundImage);
		ImageIO.write(combine, "png", new java.io.File("test.png"));
		dsfw.close();
	}

	public static void visibilityWhitOutGround(Point[] objectPoints, double zPlane, double ignoreOffset, String discreteSpace, String fromColor, String toColor) throws IOException, Exception {
		DiscreteSpaceFileWriter dsfw = new DiscreteSpaceFileWriter(discreteSpace);
		ArrayList<boolean[]> visAnalysis = dsfw.visibillity(objectPoints, zPlane, ignoreOffset);
		BufferedImage image = dsfw.visExportForObjects(visAnalysis,fromColor,toColor);
		ImageIO.write(image, "png", new java.io.File("test.png"));
		dsfw.close();
	}

	public static void ground(double zPlane, String discreteSpace, String wallColor) throws IOException {
		DiscreteSpaceFileWriter dsfw = new DiscreteSpaceFileWriter(discreteSpace);
		BufferedImage groundImage = dsfw.export(zPlane, wallColor);
		ImageIO.write(groundImage, "png", new java.io.File("test.png"));
		dsfw.close();
	}

	private static BufferedImage combineImages(BufferedImage imageOne, BufferedImage imageTow) {
		BufferedImage combine = new BufferedImage(imageOne.getWidth(), imageOne.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics g = combine.getGraphics();
		g.drawImage(imageOne, 0, 0, null);
		g.drawImage(imageTow, 0, 0, null);

		return combine;

	}

}
