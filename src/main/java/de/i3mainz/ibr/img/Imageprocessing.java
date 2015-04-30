package de.i3mainz.ibr.img;

import de.i3mainz.ibr.img.Cubic.Face;

public class Imageprocessing {

	/**
	 * converts PNG/JPG equirectangular panorama file to 6*resolution.length PNG
	 * cubic panorama faces (each filename with _face_resolution)
	 *
	 * @param fromFile
	 * @param toFile
	 * @param resolution List of Resolutions
	 */
	public static boolean convertToCubes(String fromFile, String toFile, int[] resolution) {

		toFile = toFile.substring(0, toFile.length() - 4);
		try {
			Equirectangular data = Equirectangular.read(fromFile);
			if (resolution[0] == 0) {
				int i = 1;

				while (true) {
					if (Math.pow(2, i) <= data.getWidth() / 4.0) {
						i++;
					} else {
						resolution[0] = (int) Math.pow(2, (i-1));
						break;
					}
				}

			}

			if (data == null) {
				return false;
			}

			if (!Cubic.export(toFile + "_0", data, Face.FRONT, resolution)) {
				return false;
			}

			if (!Cubic.export(toFile + "_1", data, Face.RIGHT, resolution)) {
				return false;
			}

			if (!Cubic.export(toFile + "_2", data, Face.BACK, resolution)) {
				return false;
			}

			if (!Cubic.export(toFile + "_3", data, Face.LEFT, resolution)) {
				return false;
			}

			if (!Cubic.export(toFile + "_4", data, Face.TOP, resolution)) {
				return false;
			}

			if (!Cubic.export(toFile + "_5", data, Face.BOTTOM, resolution)) {
				return false;
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * converts PNG/JPG equirectangular panorama file to six PNG images cubic
	 * panorama (each filename with _face)
	 *
	 * @param fromFile
	 * @param toFile
	 * @param res The resolution of the exported image (width and height)
	 */
	public static boolean convertToCubes(String fromFile, String toFile, int res) {
		int[] resolution = {res};
		return convertToCubes(fromFile, toFile, resolution);
	}

}
