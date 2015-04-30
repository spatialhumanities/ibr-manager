package de.i3mainz.ibr.visibility;

import de.i3mainz.ibr.geometry.Angle;
import de.i3mainz.ibr.geometry.Line;
import de.i3mainz.ibr.geometry.Plane;
import de.i3mainz.ibr.geometry.Point;
import de.i3mainz.ibr.geometry.Transformation;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class DiscreteSpaceFileWriter {

	private static final int PARAMETEROFFSET = 44;
	private RandomAccessFile stream;
	private double unit;
	private double xmin;
	private double ymin;
	private double zmin;
	private long xsize;
	private long ysize;
	private long zsize;

	/**
	 * creates a new file with set bits. The size is specified. min specifies
	 * one corner of the space.
	 *
	 * @param filename
	 * @param unit the size of a unit in the discrete space
	 * @param xmin
	 * @param ymin
	 * @param zmin
	 * @param xsize
	 * @param ysize
	 * @param zsize
	 * @throws java.io.FileNotFoundException
	 */
	public DiscreteSpaceFileWriter(String filename, double unit, double xmin, double ymin, double zmin, int xsize, int ysize, int zsize) throws FileNotFoundException, IOException, Exception {
		if (xsize % 8 != 0 || ysize % 8 != 0 || zsize % 8 != 0) {
			throw new Exception("xsize,ysize,zsize müssen vielfache von 8 sein!!");
		}
		this.unit = unit;
		this.xmin = xmin;
		this.ymin = ymin;
		this.zmin = zmin;
		this.xsize = xsize;
		this.ysize = ysize;
		this.zsize = zsize;
		File file = new File(filename);
		file.delete();
		stream = new RandomAccessFile(filename, "rw");
		stream.writeDouble(unit);
		stream.writeDouble(xmin);
		stream.writeDouble(ymin);
		stream.writeDouble(zmin);
		stream.writeInt(xsize);
		stream.writeInt(ysize);
		stream.writeInt(zsize);

		byte[] array = new byte[zsize];
		for (int i = 0; i < zsize; i++) {
			array[i] = (byte) 0b11111111;
		}
		for (long i = xsize * ysize / 8; i > 0; i--) {
			stream.write(array);
		}
	}

	public DiscreteSpaceFileWriter(String filename) throws FileNotFoundException, IOException {
		stream = new RandomAccessFile(filename, "rw");
		this.unit = stream.readDouble();
		this.xmin = stream.readDouble();
		this.ymin = stream.readDouble();
		this.zmin = stream.readDouble();
		this.xsize = stream.readInt();
		this.ysize = stream.readInt();
		this.zsize = stream.readInt();
	}

	private int pointToPixelX(Point p) {
		return (int) (Math.round((p.getX() - xmin) / unit));
	}

	private int pointToPixelY(Point p) {
		return (int) (Math.round((p.getY() - ymin) / unit));
	}

	private int pointToPixelZ(Point p) {
		return (int) (Math.round((p.getZ() - zmin) / unit));
	}

	private int pointToPixelZ(double p) {
		return (int) Math.round((p - zmin) / unit);
	}

	private Point pixelToPoint(int x, int y, int z) {
		return new Point(unit * x + xmin, unit * y + ymin, unit * z + zmin);
	}

	private int max(int v0, int v1, int v2) {
		if (v0 > v1 && v0 > v2) {
			return v0;
		}
		if (v1 > v2) {
			return v1;
		}
		return v2;
	}

	private boolean isInCheck(Point p) {
		return (pointToPixelX(p) <= xsize && pointToPixelX(p) >= 0) && (pointToPixelY(p) <= ysize && pointToPixelY(p) >= 0) && (pointToPixelZ(p) <= zsize && pointToPixelZ(p) >= 0);
	}

	/**
	 *
	 * @param pc
	 * @param trans
	 * @throws IOException
	 */
	public void add(Pointcloud pc, Transformation trans) throws IOException {
		Transformation inverse = trans.inverse();
		Point center = new Point(0, 0, 0);
		center.transform(trans);
		int cx = pointToPixelX(center);
		int cy = pointToPixelY(center);
		int cz = pointToPixelZ(center);

		for (int px = 0; px < xsize; px++) {
			for (int py = 0; py < ysize; py++) {
				long zstep = (px == 0 || px == xsize - 1 || py == 0 || py == ysize - 1) ? 1 : zsize - 1;
				for (int pz = 0; pz < zsize; pz += zstep) {
					int dx = px - cx;
					int dy = py - cy;
					int dz = pz - cz;
					Point p = new Point(dx, dy, dz);
					p.transform(inverse);
					Point point = pc.getPoint(new Angle(p.getAzim(), p.getElev()));
					if (point != null) {
						point.transform(trans);
						int sx = pointToPixelX(point);
						int sy = pointToPixelY(point);
						int sz = pointToPixelZ(point);
						int xyz = max(Math.abs(dx), Math.abs(dy), Math.abs(dz));
						for (int i = 0; i < xyz; i++) {
							int x = px - dx * i / xyz;
							int y = py - dy * i / xyz;
							int z = pz - dz * i / xyz;
							if (x >= 0 && x < xsize && ((x >= cx && x <= sx) || (x <= cx && x >= sx))
									&& y >= 0 && y < ysize && ((y >= cy && y <= sy) || (y <= cy && y >= sy))
									&& z >= 0 && z < zsize && ((z >= cz && z <= sz) || (z <= cz && z >= sz))) {
								long seekByte = (x + xsize * y + xsize * ysize * z) / 8;
								byte seekBit = (byte) Math.pow(2, ((x + xsize * y + xsize * ysize * z) % 8));
								stream.seek(PARAMETEROFFSET + seekByte);
								byte b = stream.readByte();
								stream.seek(PARAMETEROFFSET + seekByte);
								stream.write(b & ~seekBit);
							}
						}
					}
				}
			}
		}
	}

	/**
	 *
	 *
	 * @param objectPoints
	 * @param zPlane
	 * @param ignoreOffset In Units of the Grid
	 * @return 
	 * @throws IOException
	 * @throws Exception
	 */
	public ArrayList<boolean[]> visibillity(Point[] objectPoints, double zPlane, double ignoreOffset) throws IOException, Exception {

		ArrayList<boolean[]> visAnalysis = new ArrayList<>();
		for (Point objectPoint : objectPoints) {
			if (!isInCheck(objectPoint)) {
				throw new Exception("Punkt nicht innerhalb des Raumbereiches!");
			}
			int zslide = pointToPixelZ(zPlane);
			int ox = pointToPixelX(objectPoint);
			int oy = pointToPixelY(objectPoint);
			int oz = pointToPixelZ(objectPoint);
			int zMin = zslide < oz ? zslide : oz;
			int zMax = zslide > oz ? zslide : oz;

			// setze Feld auf true
			boolean[] visiblePlane = new boolean[(int) (xsize * ysize)];
			for (int i = 0; i < visiblePlane.length; i++) {
				visiblePlane[i] = true;
			}

			//erstelle Array über alle Benötigten Ebenen und mische diese
			ArrayList<Integer> slids = new ArrayList<>();
			int signum = zslide > oz ? 1 : -1;
			for (int z = oz; z != (zslide + signum); z += signum) {
				slids.add(z);
			}
			Collections.shuffle(slids);

			// iteration über alle benötigten ebenen
			for (Integer z : slids) {
				System.out.println(z);

				//Auslesen der Daten für die entsprechende ebene
				byte[] fileData = new byte[(int) (xsize * ysize / 8)];
				stream.seek(PARAMETEROFFSET + xsize * ysize * z / 8);
				stream.read(fileData);

				//iteration über x und y in der Ebene 
				for (int x = 0; x < xsize; x++) {
					for (int y = 0; y < ysize; y++) {
						if (visiblePlane[x + (int) xsize * y]) {
							Point planePoint = new Point(x, y, zslide);
							Plane pOne, pTow;

							//definition eines Bereichs um die Ebene (+- 1/2 auflöung) 
							if (z == zMin) {
								pOne = new Plane(new Point(0, 0, z), new Point(1, 0, z), new Point(0, 1, z));
								pTow = new Plane(new Point(0, 0, z + 0.5), new Point(1, 0, z + 0.5), new Point(0, 1, z + 0.5));
							} else if (z == zMax) {
								pOne = new Plane(new Point(0, 0, z - 0.5), new Point(1, 0, z - 0.5), new Point(0, 1, z - 0.5));
								pTow = new Plane(new Point(0, 0, z), new Point(1, 0, z), new Point(0, 1, z));
							} else {
								pOne = new Plane(new Point(0, 0, z - 0.5), new Point(1, 0, z - 0.5), new Point(0, 1, z - 0.5));
								pTow = new Plane(new Point(0, 0, z + 0.5), new Point(1, 0, z + 0.5), new Point(0, 1, z + 0.5));
							}

							// Linie zwischen Objekt und dem aktuellen Punkt in der Ebene
							Line activeLine = new Line(planePoint, new Point(ox, oy, oz));

							// Schnitt der Gerade mit den Definierten Ebenen
							Point sectionPointOne;
							Point sectionPointTow;

							if (oz == zslide) {
								sectionPointOne = activeLine.getA();
								sectionPointTow = activeLine.getB();
							} else {
								sectionPointOne = Point.cutnew(activeLine, pOne);
								sectionPointTow = Point.cutnew(activeLine, pTow);
							}
							// Überprüfe ob der Linien Teil zwischen den Bereichen einer Ebene sichtbar ist
							if (!isInsideCircel(sectionPointOne, new Point(ox, oy, oz), ignoreOffset) && !isInsideCircel(sectionPointTow, new Point(ox, oy, oz), ignoreOffset)) {
								if (!lineCheck(sectionPointOne, sectionPointTow, fileData)) {
									visiblePlane[ x + (int) xsize * y] = false;
								}
							}
						}

					}
				}
			}
			visAnalysis.add(visiblePlane);
		}

		return visAnalysis;
	}

	private boolean lineCheck(Point one, Point tow, byte[] planeData) {
		double dx = tow.getX() - one.getX();
		double dy = tow.getY() - one.getY();

		// bestimme maximalen Abstand
		double max = Math.abs(dx) > Math.abs(dy) ? Math.abs(dx) : Math.abs(dy);
		for (int i = 0; i <= max; i++) {

			// Such bit
			int seekbit = (int) (Math.round(one.getX() + dx / max * i) + Math.round(one.getY() + dy / max * i) * xsize);															//(xsize * ysize)

			// Auslesen des Suchbits
			if (((planeData[seekbit / 8] & (byte) Math.pow(2, (seekbit % 8))) != 0)) {

//				// rückgabe falls nicht sichtbar
				return false;
			}

		}

		//rückgabe wenn die gesamte linie sichbar war
		return true;

	}

	/**
	 *
	 * @param visPlanes
	 * @param fromColor
	 * @param toColor
	 * @return
	 * @throws IOException
	 */
	protected BufferedImage visExportForObjects(ArrayList<boolean[]> visPlanes, String fromColor, String toColor) throws IOException {
		Color fColor = new Color((int) Long.parseLong(fromColor, 16), true);
		Color tColor = new Color((int) Long.parseLong(toColor, 16), true);
		int changeAlpha = (tColor.getAlpha() - fColor.getAlpha()) / visPlanes.size();
		int changeRed = (tColor.getRed() - fColor.getRed()) / visPlanes.size();
		int changeGreen = (tColor.getGreen() - fColor.getGreen()) / visPlanes.size();
		int changeBlue = (tColor.getBlue() - fColor.getBlue()) / visPlanes.size();
		Color[] imageData = new Color[(int) (xsize * ysize)];

		for (int i = 0; i < imageData.length; i++) {
			imageData[i] = fColor;
		}
		for (boolean[] visPlane : visPlanes) {
			for (int i = 0; i < visPlane.length; i++) {
				if (visPlane[i]) {
					imageData[i] = new Color(imageData[i].getRed() + changeRed, imageData[i].getGreen() + changeGreen, imageData[i].getBlue() + changeBlue, imageData[i].getAlpha() + changeAlpha);
				}
			}
		}

		int[] colorImage = new int[imageData.length];
		for (int i = 0; i < colorImage.length; i++) {
			colorImage[i] = imageData[i].getRGB();
		}
		BufferedImage image = new BufferedImage((int) xsize, (int) ysize, BufferedImage.TYPE_INT_ARGB);
		WritableRaster wr = image.getRaster();
		wr.setDataElements(0, 0, (int) xsize, (int) ysize, colorImage);
		return mirror(image);
	}

	/**
	 *
	 *
	 * @param zslide Zu Untersuchende Ebene
	 * @param wallColor Color of the not visible parts in Hex (black = FF000000)
	 * @return
	 * @throws IOException
	 */
	public BufferedImage export(double zslide, String wallColor) throws IOException {

		int zero = 0;		//sichbar
		int one = (int) Long.parseLong(wallColor, 16);
		byte[] fileData = new byte[(int) (xsize * ysize / 8)];
		stream.seek(PARAMETEROFFSET + xsize * ysize * pointToPixelZ(zslide) / 8);
		stream.read(fileData);
		int[] imageData = new int[(int) (xsize * ysize)];
		for (int i = 0; i < imageData.length; i++) {
			imageData[i] = ((fileData[i / 8] & (byte) Math.pow(2, (i % 8))) == 0) ? zero : one;
		}
		BufferedImage image = new BufferedImage((int) xsize, (int) ysize, BufferedImage.TYPE_INT_ARGB);
		WritableRaster wr = image.getRaster();
		wr.setDataElements(0, 0, (int) xsize, (int) ysize, imageData);
		return mirror(image);
	}

	public void close() throws IOException {
		stream.close();
	}

	private static BufferedImage mirror(BufferedImage orginal) {
		int width = orginal.getWidth();
		int hight = orginal.getHeight();
		BufferedImage destination = new BufferedImage(width, hight, orginal.getType());

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < hight; j++) {
				destination.setRGB(i, hight - 1 - j, orginal.getRGB(i, j));
			}
		}
		return destination;
	}

	private boolean isInsideCircel(Point point, Point middle, double radius) {
		return radius > Math.sqrt(Math.pow(point.getX() - middle.getX(), 2) + Math.pow(point.getY() - middle.getY(), 2) + Math.pow(point.getZ() - middle.getZ(), 2));

	}

}
