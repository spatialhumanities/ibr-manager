package de.i3mainz.ibr.pc;

import de.i3mainz.ibr.geometry.Point;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class XYZInputStream implements Closeable {
	
	private double x, y, z;
	private BufferedReader stream;
	private int size;
	
	public XYZInputStream(String filename) throws IOException {
		size = countLines(filename);
		stream = new BufferedReader(new FileReader(filename));
		readFirstPoint();
	}
	
	public static int countLines(String filename) throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(filename));
		try {
			byte[] c = new byte[1024];
			int count = 0;
			int readChars = 0;
			boolean empty = true;
			while ((readChars = is.read(c)) != -1) {
				empty = false;
				for (int i = 0; i < readChars; ++i) {
					if (c[i] == '\n') {
						++count;
					}
				}
			}
			return (count == 0 && !empty) ? 1 : count;
		} finally {
			is.close();
		}
	}
	
	private void readFirstPoint() throws IOException {
		String line = stream.readLine();
		String[] split = line.split(" ");
		x = Float.parseFloat(split[0]);
		y = -Float.parseFloat(split[2]);
		z = Float.parseFloat(split[1]);
		System.out.println(x + " " + y + " " + z);
	}
	
	public PTGPoint readPoint() throws IOException {
		String line = stream.readLine();
		if (line == null)
			return null;
		String[] split = line.split(" ");
		if (split.length >= 6) {
			PTGPoint point = new PTGPoint(Double.parseDouble(split[0])-x,-Double.parseDouble(split[2])-y,Double.parseDouble(split[1])-z);
			Point dir = new Point(Double.parseDouble(split[3]),-Double.parseDouble(split[5]),Double.parseDouble(split[4]));
			point.setRem((float)(Math.acos(Point.dot(dir,point)/(dir.norm()*point.norm()))/Math.PI));
			return point;
		} else if (split.length >= 3) {
			return new PTGPoint(Double.parseDouble(split[0])-x,-Double.parseDouble(split[2])-y,Double.parseDouble(split[1])-z);
		} else
			return null;
	}
	
	public int size() {
		return size;
	}

	@Override
	public void close() throws IOException {
		stream.close();
	}
	
}
