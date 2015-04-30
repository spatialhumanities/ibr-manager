package de.i3mainz.ibr.project;

public class Transformation extends de.i3mainz.ibr.math.Transformation {
	
	private String srccrs;
	private String dstcrs;
	
	public Transformation() {
		super();
	}
	
	public Transformation(Transformation t) {
		super(t);
		this.srccrs = t.srccrs;
		this.dstcrs = t.dstcrs;
	}
	
	public Transformation(String params) {
		super(params);
	}
	
	public Transformation(String transl_x, String transl_y, String transl_z, String rot_x, String rot_y, String rot_z, String scale) {
		super(Double.parseDouble(rot_x),Double.parseDouble(rot_y),Double.parseDouble(rot_z),Double.parseDouble(transl_x),Double.parseDouble(transl_y),Double.parseDouble(transl_z),Double.parseDouble(scale));
	}
	
	public Transformation(String cols, String rows, String px1, String py1, String px2, String py2, String px3, String py3, String rx1, String ry1, String rx2, String ry2, String rx3, String ry3) {
		super(Double.parseDouble(px1)/Double.parseDouble(cols),Double.parseDouble(py1)/Double.parseDouble(rows),Double.parseDouble(px2)/Double.parseDouble(cols),Double.parseDouble(py2)/Double.parseDouble(rows),Double.parseDouble(px3)/Double.parseDouble(cols),Double.parseDouble(py3)/Double.parseDouble(rows),Double.parseDouble(rx1),Double.parseDouble(ry1),Double.parseDouble(rx2),Double.parseDouble(ry2),Double.parseDouble(rx3),Double.parseDouble(ry3));
	}
	
	public void setSrc(String crs) {
		srccrs = crs;
	}
	
	public void setDst(String crs) {
		dstcrs = crs;
	}
	
	@Override
	public String toString() {
		String s = "<transformation>";
		s += "<srccrs>" + srccrs + "</srccrs>";
		s += "<dstcrs>" + dstcrs + "</dstcrs>";
		s += "<params>" + super.toString() + "</params>";
		s += "</transformation>";
		return s;
	}
	
}
