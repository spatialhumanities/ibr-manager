/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.i3mainz.ibr.triangulation;

import de.i3mainz.ibr.triangulation.*;
import de.i3mainz.ibr.geometry.Point;

/**
 *
 * @author s3b31293
 */
public class Line2D {

	private static final double epsilon = 0.01;
	private Point2D start, end;

	public Line2D(Point2D start, Point2D end) {
		this.start = start;
		this.end = end;
	}

	public Point2D cut(Line2D other) {
		Point a = new Point(this.start.getX(), this.start.getY(), 1);
		Point b = new Point(this.end.getX(), this.end.getY(), 1);
		Point c = new Point(other.start.getX(), other.start.getY(), 1);
		Point d = new Point(other.end.getX(), other.end.getY(), 1);

		Point lineOne = Point.cross(a, b);
		Point lineTow = Point.cross(c, d);

		Point cut = Point.cross(lineOne, lineTow);

		cut.mul(1 / cut.getZ());

		return new Point2D(cut.getX(), cut.getY());
	}

	public boolean isCut(Line2D other) {

		Point2D cut = this.cut(other);
		if (boundingBoxCheck(this, cut)) {
			return boundingBoxCheck(other, cut);
		}
		return false;
	}

	/**
	 *
	 * @param other
	 * @return true if boundingBox intersect
	 */
	private static boolean boundingBoxCheck(Line2D other, Point2D cut) {
		if (other.start.getY() > other.end.getY()) {
			Point2D tmp = other.start;
			other.start = other.end;
			other.end = tmp;
		}

		if (other.start.getX() < other.end.getX()) {
			if (cut.getX() >= other.start.getX() && cut.getX() <= other.end.getX()) {
				if (cut.getY() >= other.start.getY() && cut.getY() <= other.end.getY()) {
					return true;
				}
			}
		} else {
			if (cut.getX() <= other.start.getX() && cut.getX() >= other.end.getX()) {
				if (cut.getY() >= other.start.getY() && cut.getY() <= other.end.getY()) {
					return true;
				}
			}
		}
		return false;
	}
}
