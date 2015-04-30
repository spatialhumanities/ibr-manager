package de.i3mainz.ibr.project;

import java.util.ArrayList;

public class Viewpoint {

    private String name;
    private final Transformation transformation;

    private String pointcloudFile;
    private final Transformation pointcloudTransformation;

    private final ArrayList<String> remImages;
    private final Transformation remTransformation;
    private final ArrayList<String> rgbImages;
    private final Transformation rgbTransformation;

    public Viewpoint(Transformation trans, Transformation ptg, Transformation rem, Transformation rgb) {
        transformation = new Transformation(trans);
        transformation.setSrc("viewpoint");
        transformation.setDst("spatialcontext");
        pointcloudTransformation = new Transformation(ptg);
        pointcloudTransformation.setSrc("pointcloud");
        pointcloudTransformation.setDst("viewpoint");
        remTransformation = new Transformation(rem);
        remTransformation.setSrc("panorama");
        remTransformation.setDst("viewpoint");
        rgbTransformation = new Transformation(rgb);
        rgbTransformation.setSrc("panorama");
        rgbTransformation.setDst("viewpoint");
        remImages = new ArrayList<String>();
        rgbImages = new ArrayList<String>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addTransformation(Transformation transformation) {
        if (transformation == null) {
            this.transformation.inverse();
        } else {
            this.transformation.transform(transformation);
        }
    }

    public void setPointcloud(String filename) {
        this.pointcloudFile = filename;
    }

    public void addPointcloudTransformation(Transformation transformation) {
        if (transformation == null) {
            this.pointcloudTransformation.inverse();
        } else {
            this.pointcloudTransformation.transform(transformation);
        }
    }

    public void addRemImage(String filename) {
        this.remImages.add(filename);
    }

    public final void addRemTransformation(Transformation transformation) {
        if (transformation == null) {
            this.remTransformation.inverse();
        } else {
            this.remTransformation.transform(transformation);
        }
    }

    public void addRgbImage(String filename) {
        this.rgbImages.add(filename);
    }

    public final void addRgbTransformation(Transformation transformation) {
        if (transformation == null) {
            this.rgbTransformation.inverse();
        } else {
            this.rgbTransformation.transform(transformation);
        }
    }

    @Override
    public String toString() {
        String s = "<viewpoint name=\"" + name + "\">";

        s += "<metadata>";
        s += "<viewpointname>" + name + "</viewpointname>";
        s += transformation;
        s += "</metadata>";

        s += "<pointclouds>";
        s += "<pointcloud>";
        s += "<metadata>";
        s += "<filename>" + pointcloudFile + "</filename>";
        s += "<type>PTG</type>";
        s += "<bbox_local>0,0,0,0</bbox_local>";
        s += "<bbox_global>0,0,0,0</bbox_global>";
        s += "<bbox_polar>0,0,0,0</bbox_polar>";
        s += "<remissionrange>0,0</remissionrange>";
        s += "<rows>0</rows>";
        s += "<cols>0</cols>";
        s += "</metadata>";
        s += pointcloudTransformation;
        s += "</pointcloud>";
        s += "</pointclouds>";

        s += "<panoramas>";

        // only add panorama if pictures are set
        if (rgbImages.size() > 0) {
            s += "<panorama>";
            s += "<structuraltype>cube</structuraltype>";
            s += "<kindof>Farbpanorama (rgb)</kindof>";
            s += rgbTransformation;
            s += "<images>";
            for (String img : rgbImages) {
                s += "<img>" + img + "</img>";
            }
            s += "</images>";
            s += "</panorama>";
        }
        s += "<panorama>";
        s += "<structuraltype>cube</structuraltype>";
        s += "<kindof>Intensitaetsbild des Laserscanners (rem)</kindof>";
        s += remTransformation;
        s += "<images>";
        for (String img : remImages) {
            s += "<img>" + img + "</img>";
        }
        s += "</images>";
        s += "</panorama>";
        s += "</panoramas>";

        s += "</viewpoint>";
        return s;
    }

}
