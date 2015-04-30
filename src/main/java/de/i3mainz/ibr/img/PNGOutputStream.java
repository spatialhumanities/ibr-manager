package de.i3mainz.ibr.img;

import de.i3mainz.ibr.pc.PTGPoint;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.Closeable;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PNGOutputStream implements Closeable {

    String filename;
    int size;
    byte[] data;
    double range = 1;
    double min = 0;

    public PNGOutputStream(String filename, int size) throws IOException {
        this.filename = filename;
        this.size = size;
        data = new byte[2 * size * size];
    }
    
    /**
     * Sets minimum and range.
     * 
     * @param min
     * @param max 
     */
    public void setRange(double min, double max) {
        this.min = min;
        this.range = max - min;
    }

    /**
     * Writes remission values of a point column into PNG image.
     * Normalizes the remission values before writing. 
     * 
     * @param points
     * @param col 
     */
    public void writeColumn(PTGPoint[] points, int col) {
        for (int i = 0; i < points.length; i++) {
            if (points[i] != null && points[i].getRemission() >= min)
                data[2 * i * points.length + col] = (byte) (250 * (points[i].getRemission() - min) / range);
            else
                data[2 * i * points.length + col] = 0;
        }
    }

    /**
     * Writes data to image.
     * 
     * @throws IOException 
     */
    public void close() throws IOException {
        BufferedImage image = new BufferedImage(2 * size, size, BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster wr = image.getRaster();
        wr.setDataElements(0, 0, 2 * size, size, data);
        ImageIO.write(image, "png", new java.io.File(filename));
    }

}
