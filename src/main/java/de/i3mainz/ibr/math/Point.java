package de.i3mainz.ibr.math;

public class Point {
	
	private double[] vector = new double[4];
	
	public Point(double x, double y, double z) {
		vector[0] = x;
		vector[1] = y;
		vector[2] = z;
		vector[3] = 1;
	}
	
	double getX() {
		return vector[0];
	}
	
	double getY() {
		return vector[1];
	}
	
	double getZ() {
		return vector[2];
	}
	
}
