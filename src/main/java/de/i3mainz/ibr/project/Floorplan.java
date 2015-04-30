package de.i3mainz.ibr.project;

public class Floorplan {
	
	private String filename;
	private String description;
	private Transformation transformation;
	
	public Floorplan(String filename, String description, Transformation transformation) {
		this.filename = filename;
		this.description = description;
		this.transformation = transformation;
		this.transformation.setSrc("media");
		this.transformation.setDst("spatialcontext");
	}
	
	@Override
	public String toString() {
		String s = "<media type=\"groundplan\">";
		s += "<data>";
		s += "<mediatype>groundplan</mediatype>";
		s += "<filename>" + filename + "</filename>";
		s += "<description>" + description + "</description>";
		s += "</data>";
		s += transformation;
		s += "</media>";
		return s;
	}
	
}
