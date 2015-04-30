package de.i3mainz.ibr.math;

public class Transformation {
	
	private double[][] matrix = new double[4][4];
	
	public Transformation() {
		matrix[0][0] = 1;
		matrix[0][1] = 0;
		matrix[0][2] = 0;
		matrix[0][3] = 0;
		matrix[1][0] = 0;
		matrix[1][1] = 1;
		matrix[1][2] = 0;
		matrix[1][3] = 0;
		matrix[2][0] = 0;
		matrix[2][1] = 0;
		matrix[2][2] = 1;
		matrix[2][3] = 0;
		matrix[3][0] = 0;
		matrix[3][1] = 0;
		matrix[3][2] = 0;
		matrix[3][3] = 1;
	}
	
	public Transformation(Transformation t) {
		this.matrix = t.matrix.clone();
	}
	
	public Transformation(double[][] matrix) {
		this.matrix = matrix;
	}
	
	public Transformation(double a00, double a01, double a02, double a03, double a10, double a11, double a12, double a13, double a20, double a21, double a22, double a23, double a30, double a31, double a32, double a33) {
		matrix[0][0] = a00;
		matrix[0][1] = a01;
		matrix[0][2] = a02;
		matrix[0][3] = a03;
		matrix[1][0] = a10;
		matrix[1][1] = a11;
		matrix[1][2] = a12;
		matrix[1][3] = a13;
		matrix[2][0] = a20;
		matrix[2][1] = a21;
		matrix[2][2] = a22;
		matrix[2][3] = a23;
		matrix[3][0] = a30;
		matrix[3][1] = a31;
		matrix[3][2] = a32;
		matrix[3][3] = a33;
	}
	
	public Transformation(String csv) {
		String[] split = csv.split(",");
		for (int i=0; i<16; i++) {
			matrix[i/4][i%4] = Double.parseDouble(split[i]);
		}
	}
	
	public Transformation(double scale) {
		matrix[0][0] = scale;
		matrix[0][1] = 0;
		matrix[0][2] = 0;
		matrix[0][3] = 0;
		matrix[1][0] = 0;
		matrix[1][1] = scale;
		matrix[1][2] = 0;
		matrix[1][3] = 0;
		matrix[2][0] = 0;
		matrix[2][1] = 0;
		matrix[2][2] = scale;
		matrix[2][3] = 0;
		matrix[3][0] = 0;
		matrix[3][1] = 0;
		matrix[3][2] = 0;
		matrix[3][3] = 1;
	}
	
	public Transformation(double transX, double transY, double transZ) {
		matrix[0][0] = 1;
		matrix[0][1] = 0;
		matrix[0][2] = 0;
		matrix[0][3] = transX;
		matrix[1][0] = 0;
		matrix[1][1] = 1;
		matrix[1][2] = 0;
		matrix[1][3] = transY;
		matrix[2][0] = 0;
		matrix[2][1] = 0;
		matrix[2][2] = 1;
		matrix[2][3] = transZ;
		matrix[3][0] = 0;
		matrix[3][1] = 0;
		matrix[3][2] = 0;
		matrix[3][3] = 1;
	}
	
	public Transformation(double rotation, Point axis) {
		matrix[0][0] = axis.getX() * axis.getX() * (1 - Math.cos(rotation)) + Math.cos(rotation);
		matrix[0][1] = axis.getX() * axis.getY() * (1 - Math.cos(rotation)) - Math.sin(rotation) * axis.getZ();
		matrix[0][2] = axis.getX() * axis.getZ() * (1 - Math.cos(rotation)) + Math.sin(rotation) * axis.getY();
		matrix[0][3] = 0;
		matrix[1][0] = axis.getY() * axis.getX() * (1 - Math.cos(rotation)) + Math.sin(rotation) * axis.getZ();
		matrix[1][1] = axis.getY() * axis.getY() * (1 - Math.cos(rotation)) + Math.cos(rotation);
		matrix[1][2] = axis.getY() * axis.getZ() * (1 - Math.cos(rotation)) - Math.sin(rotation) * axis.getX();
		matrix[1][3] = 0;
		matrix[2][0] = axis.getZ() * axis.getX() * (1 - Math.cos(rotation)) - Math.sin(rotation) * axis.getY();
		matrix[2][1] = axis.getZ() * axis.getY() * (1 - Math.cos(rotation)) + Math.sin(rotation) * axis.getX();
		matrix[2][2] = axis.getZ() * axis.getZ() * (1 - Math.cos(rotation)) + Math.cos(rotation);
		matrix[2][3] = 0;
		matrix[3][0] = 0;
		matrix[3][1] = 0;
		matrix[3][2] = 0;
		matrix[3][3] = 1;
	}
	
	public Transformation(double rotX, double rotY, double rotZ, double transX, double transY, double transZ, double m) {
		Transformation t = new Transformation(transX,transY,transZ);
		Transformation rx = new Transformation(rotX,new Point(1,0,0));
		Transformation ry = new Transformation(rotY,new Point(0,1,0));
		Transformation rz = new Transformation(rotZ,new Point(0,0,1));
		Transformation r = new Transformation(m);
		r.transform(rz); r.transform(ry); r.transform(rx); r.transform(t);
		matrix = r.matrix;
	}
	
	public Transformation(double px1, double py1, double px2, double py2, double px3, double py3,
			double rx1, double ry1, double rx2, double ry2, double rx3, double ry3) {
		Transformation tmp = new Transformation(px1,py1,1,0,px2,py2,1,0,px3,py3,1,0,0,0,0,1);		
		tmp.inverse();
		Point x = tmp.transform(new Point(rx1,rx2,rx3));
		Point y = tmp.transform(new Point(ry1,ry2,ry3));
		matrix[0][0] = x.getX();
		matrix[0][1] = x.getY();
		matrix[0][2] = x.getZ();
		matrix[0][3] = 0;
		matrix[1][0] = y.getX();
		matrix[1][1] = y.getY();
		matrix[1][2] = y.getZ();
		matrix[1][3] = 0;
		matrix[2][0] = 0;
		matrix[2][1] = 0;
		matrix[2][2] = 1;
		matrix[2][3] = 0;
		matrix[3][0] = 0;
		matrix[3][1] = 0;
		matrix[3][2] = 0;
		matrix[3][3] = 1;
	}
	
	public double determinant() {
		double d = 0, tmp;
		tmp  = matrix[1][1] * (matrix[2][2] * matrix[3][3] - matrix[2][3] * matrix[3][2]);
		tmp += matrix[1][2] * (matrix[2][3] * matrix[3][1] - matrix[2][1] * matrix[3][3]);
		tmp += matrix[1][3] * (matrix[2][1] * matrix[3][2] - matrix[2][2] * matrix[3][1]);
		d += matrix[0][0] * tmp;
		tmp  = matrix[0][1] * (matrix[2][2] * matrix[3][3] - matrix[2][3] * matrix[3][2]);
		tmp += matrix[0][2] * (matrix[2][3] * matrix[3][1] - matrix[2][1] * matrix[3][3]);
		tmp += matrix[0][3] * (matrix[2][1] * matrix[3][2] - matrix[2][2] * matrix[3][1]);
		d -= matrix[1][0] * tmp;
		tmp  = matrix[0][1] * (matrix[1][2] * matrix[3][3] - matrix[1][3] * matrix[3][2]);
		tmp += matrix[0][2] * (matrix[1][3] * matrix[3][1] - matrix[1][1] * matrix[3][3]);
		tmp += matrix[0][3] * (matrix[1][1] * matrix[3][2] - matrix[1][2] * matrix[3][1]);
		d += matrix[2][0] * tmp;
		tmp  = matrix[0][1] * (matrix[1][2] * matrix[2][3] - matrix[1][3] * matrix[2][2]);
		tmp += matrix[0][2] * (matrix[1][3] * matrix[2][1] - matrix[1][1] * matrix[2][3]);
		tmp += matrix[0][3] * (matrix[1][1] * matrix[2][2] - matrix[1][2] * matrix[2][1]);
		d -= matrix[3][0] * tmp;
		return d;
	}
	
	public void inverse() {
		double[][] inverse = new double[4][4];
		
		inverse[0][0]  = matrix[1][1] * matrix[2][2] * matrix[3][3];
		inverse[0][0] += matrix[1][2] * matrix[2][3] * matrix[3][1];
		inverse[0][0] += matrix[1][3] * matrix[2][1] * matrix[3][2];
		inverse[0][0] -= matrix[1][1] * matrix[2][3] * matrix[3][2];
		inverse[0][0] -= matrix[1][2] * matrix[2][1] * matrix[3][3];
		inverse[0][0] -= matrix[1][3] * matrix[2][2] * matrix[3][1];
		inverse[0][0] = inverse[0][0] / this.determinant();
		
		inverse[0][1]  = matrix[0][1] * matrix[2][3] * matrix[3][2];
		inverse[0][1] += matrix[0][2] * matrix[2][1] * matrix[3][3];
		inverse[0][1] += matrix[0][3] * matrix[2][2] * matrix[3][1];
		inverse[0][1] -= matrix[0][1] * matrix[2][2] * matrix[3][3];
		inverse[0][1] -= matrix[0][2] * matrix[2][3] * matrix[3][1];
		inverse[0][1] -= matrix[0][3] * matrix[2][1] * matrix[3][2];
		inverse[0][1] /= this.determinant();
		
		inverse[0][2]  = matrix[0][1] * matrix[1][2] * matrix[3][3];
		inverse[0][2] += matrix[0][2] * matrix[1][3] * matrix[3][1];
		inverse[0][2] += matrix[0][3] * matrix[1][1] * matrix[3][2];
		inverse[0][2] -= matrix[0][1] * matrix[1][3] * matrix[3][2];
		inverse[0][2] -= matrix[0][2] * matrix[1][1] * matrix[3][3];
		inverse[0][2] -= matrix[0][3] * matrix[1][2] * matrix[3][1];
		inverse[0][2] /= this.determinant();
		
		inverse[0][3]  = matrix[0][1] * matrix[1][3] * matrix[2][2];
		inverse[0][3] += matrix[0][2] * matrix[1][1] * matrix[2][3];
		inverse[0][3] += matrix[0][3] * matrix[1][2] * matrix[2][1];
		inverse[0][3] -= matrix[0][1] * matrix[1][2] * matrix[2][3];
		inverse[0][3] -= matrix[0][2] * matrix[1][3] * matrix[2][1];
		inverse[0][3] -= matrix[0][3] * matrix[1][1] * matrix[2][2];
		inverse[0][3] /= this.determinant();
		
		inverse[1][0]  = matrix[1][0] * matrix[2][3] * matrix[3][2];
		inverse[1][0] += matrix[1][2] * matrix[2][0] * matrix[3][3];
		inverse[1][0] += matrix[1][3] * matrix[2][2] * matrix[3][0];
		inverse[1][0] -= matrix[1][0] * matrix[2][2] * matrix[3][3];
		inverse[1][0] -= matrix[1][2] * matrix[2][3] * matrix[3][0];
		inverse[1][0] -= matrix[1][3] * matrix[2][0] * matrix[3][2];
		inverse[1][0] /= this.determinant();
		
		inverse[1][1]  = matrix[0][0] * matrix[2][2] * matrix[3][3];
		inverse[1][1] += matrix[0][2] * matrix[2][3] * matrix[3][0];
		inverse[1][1] += matrix[0][3] * matrix[2][0] * matrix[3][2];
		inverse[1][1] -= matrix[0][0] * matrix[2][3] * matrix[3][2];
		inverse[1][1] -= matrix[0][2] * matrix[2][0] * matrix[3][3];
		inverse[1][1] -= matrix[0][3] * matrix[2][2] * matrix[3][0];
		inverse[1][1] /= this.determinant();
		
		inverse[1][2]  = matrix[0][0] * matrix[1][3] * matrix[3][2];
		inverse[1][2] += matrix[0][2] * matrix[1][0] * matrix[3][3];
		inverse[1][2] += matrix[0][3] * matrix[1][2] * matrix[3][0];
		inverse[1][2] -= matrix[0][0] * matrix[1][2] * matrix[3][3];
		inverse[1][2] -= matrix[0][2] * matrix[1][3] * matrix[3][0];
		inverse[1][2] -= matrix[0][3] * matrix[1][0] * matrix[3][2];
		inverse[1][2] /= this.determinant();
		
		inverse[1][3]  = matrix[0][0] * matrix[1][2] * matrix[2][3];
		inverse[1][3] += matrix[0][2] * matrix[1][3] * matrix[2][0];
		inverse[1][3] += matrix[0][3] * matrix[1][0] * matrix[2][2];
		inverse[1][3] -= matrix[0][0] * matrix[1][3] * matrix[2][2];
		inverse[1][3] -= matrix[0][2] * matrix[1][0] * matrix[2][3];
		inverse[1][3] -= matrix[0][3] * matrix[1][2] * matrix[2][0];
		inverse[1][3] /= this.determinant();
		
		inverse[2][0]  = matrix[1][0] * matrix[2][1] * matrix[3][3];
		inverse[2][0] += matrix[1][1] * matrix[2][3] * matrix[3][0];
		inverse[2][0] += matrix[1][3] * matrix[2][0] * matrix[3][1];
		inverse[2][0] -= matrix[1][0] * matrix[2][3] * matrix[3][1];
		inverse[2][0] -= matrix[1][1] * matrix[2][0] * matrix[3][3];
		inverse[2][0] -= matrix[1][3] * matrix[2][1] * matrix[3][0];
		inverse[2][0] /= this.determinant();
		
		inverse[2][1]  = matrix[0][0] * matrix[2][3] * matrix[3][1];
		inverse[2][1] += matrix[0][1] * matrix[2][0] * matrix[3][3];
		inverse[2][1] += matrix[0][3] * matrix[2][1] * matrix[3][0];
		inverse[2][1] -= matrix[0][0] * matrix[2][1] * matrix[3][3];
		inverse[2][1] -= matrix[0][1] * matrix[2][3] * matrix[3][0];
		inverse[2][1] -= matrix[0][3] * matrix[2][0] * matrix[3][1];
		inverse[2][1] /= this.determinant();
		
		inverse[2][2]  = matrix[0][0] * matrix[1][1] * matrix[3][3];
		inverse[2][2] += matrix[0][1] * matrix[1][3] * matrix[3][0];
		inverse[2][2] += matrix[0][3] * matrix[1][0] * matrix[3][1];
		inverse[2][2] -= matrix[0][0] * matrix[1][3] * matrix[3][1];
		inverse[2][2] -= matrix[0][1] * matrix[1][0] * matrix[3][3];
		inverse[2][2] -= matrix[0][3] * matrix[1][1] * matrix[3][0];
		inverse[2][2] /= this.determinant();
		
		inverse[2][3]  = matrix[0][0] * matrix[1][3] * matrix[2][1];
		inverse[2][3] += matrix[0][1] * matrix[1][0] * matrix[2][3];
		inverse[2][3] += matrix[0][3] * matrix[1][1] * matrix[2][0];
		inverse[2][3] -= matrix[0][0] * matrix[1][1] * matrix[2][3];
		inverse[2][3] -= matrix[0][1] * matrix[1][3] * matrix[2][0];
		inverse[2][3] -= matrix[0][3] * matrix[1][0] * matrix[2][1];
		inverse[2][3] /= this.determinant();
		
		inverse[3][0]  = matrix[1][0] * matrix[2][2] * matrix[3][1];
		inverse[3][0] += matrix[1][1] * matrix[2][0] * matrix[3][2];
		inverse[3][0] += matrix[1][2] * matrix[2][1] * matrix[3][0];
		inverse[3][0] -= matrix[1][0] * matrix[2][1] * matrix[3][2];
		inverse[3][0] -= matrix[1][1] * matrix[2][2] * matrix[3][0];
		inverse[3][0] -= matrix[1][2] * matrix[2][0] * matrix[3][1];
		inverse[3][0] /= this.determinant();
		
		inverse[3][1]  = matrix[0][0] * matrix[2][1] * matrix[3][2];
		inverse[3][1] += matrix[0][1] * matrix[2][2] * matrix[3][0];
		inverse[3][1] += matrix[0][2] * matrix[2][0] * matrix[3][1];
		inverse[3][1] -= matrix[0][0] * matrix[2][2] * matrix[3][1];
		inverse[3][1] -= matrix[0][1] * matrix[2][0] * matrix[3][2];
		inverse[3][1] -= matrix[0][2] * matrix[2][1] * matrix[3][0];
		inverse[3][1] /= this.determinant();
		
		inverse[3][2]  = matrix[0][0] * matrix[1][2] * matrix[3][1];
		inverse[3][2] += matrix[0][1] * matrix[1][0] * matrix[3][2];
		inverse[3][2] += matrix[0][2] * matrix[1][1] * matrix[3][0];
		inverse[3][2] -= matrix[0][0] * matrix[1][1] * matrix[3][2];
		inverse[3][2] -= matrix[0][1] * matrix[1][2] * matrix[3][0];
		inverse[3][2] -= matrix[0][2] * matrix[1][0] * matrix[3][1];
		inverse[3][2] /= this.determinant();
		
		inverse[3][3]  = matrix[0][0] * matrix[1][1] * matrix[2][2];
		inverse[3][3] += matrix[0][1] * matrix[1][2] * matrix[2][0];
		inverse[3][3] += matrix[0][2] * matrix[1][0] * matrix[2][1];
		inverse[3][3] -= matrix[0][0] * matrix[1][2] * matrix[2][1];
		inverse[3][3] -= matrix[0][1] * matrix[1][0] * matrix[2][2];
		inverse[3][3] -= matrix[0][2] * matrix[1][1] * matrix[2][0];
		inverse[3][3] /= this.determinant();
		
		matrix = inverse;
	}
	
	/**
	 * this = transformation * this * transformation^-1
	 * @param transformation
	 */
	public void permut(Transformation transformation) {
		this.transform(transformation);
		this.inverse();
		this.transform(transformation);
		this.inverse();
	}
	
	/**
	 * this = transformation * this 
	 * @param transformation
	 */
	public void transform(Transformation transformation) {
		double[][] transformed = new double[4][4];
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				transformed[i][j] = 0;
				for (int k=0; k<4; k++) {
					transformed[i][j] += transformation.matrix[i][k] * this.matrix[k][j];
				}
			}
		}
		this.matrix = transformed;
	}
	
	/**
	 * return = this * p
	 * @param p
	 * @return 
	 */
	public Point transform(Point p) {
		double x = p.getX()*matrix[0][0] + p.getY()*matrix[0][1] + p.getZ()*matrix[0][2] + matrix[0][3];
		double y = p.getX()*matrix[1][0] + p.getY()*matrix[1][1] + p.getZ()*matrix[1][2] + matrix[0][3];
		double z = p.getX()*matrix[2][0] + p.getY()*matrix[2][1] + p.getZ()*matrix[2][2] + matrix[0][3];
		return new Point(x,y,z);
	}
	
	@Override
	public String toString() {
		String s = "";
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				s += "," + matrix[i][j];
			}
		}
		return s.substring(1);
	}
	
}
