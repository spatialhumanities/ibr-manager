package de.i3mainz.ibr.project;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.LinkedHashMap;

public class Spatialcontext {

    private String name;
    private String place;
    private Date date;

    private Transformation transformation;
    private LinkedHashMap<String, Viewpoint> viewpoints;
    private ArrayList<Floorplan> floorplans;

    private Transformation defaultTrans = new Transformation();
    private Transformation defaultPtg = new Transformation();
    private Transformation defaultRem = new Transformation();
    private Transformation defaultRgb = new Transformation();

    public Spatialcontext(String name, String place, String date, Transformation transformation) {
        this.name = name;
        this.place = place;
        this.date = Date.valueOf(date);
        this.transformation = transformation;
        this.transformation.setSrc("spatialcontext");
        this.transformation.setDst("spatialcontext");
        viewpoints = new LinkedHashMap<String, Viewpoint>();
        floorplans = new ArrayList<Floorplan>();
    }

    public void addFloorplan(String filename, String description, Transformation transformation) {
        floorplans.add(new Floorplan(filename, description, transformation));
    }

    /**
     * Sets the name of a viewpoint with id. If id is null, ids will be set as
     * names for all viewpoints.
     *
     * @param id id of the viewpoint
     * @param name name of the viewpoint
     */
    public void setName(String id, String name) {
        if (id == null) {
            for (Entry<String, Viewpoint> e : viewpoints.entrySet()) {
                e.getValue().setName(getString(name, e.getKey()));
            }
        } else {
            if (!viewpoints.containsKey(id)) {
                viewpoints.put(id, new Viewpoint(defaultTrans, defaultPtg, defaultRem, defaultRgb));
            }
            viewpoints.get(id).setName(name);
        }
    }

    /**
     * Sets a point cloud (ptg-file) for a viewpoint with id. If id is null,
     * point clouds will be set for all viewpoints.
     *
     * @param id id of the viewpoint
     * @param filename name of the ptg-file
     */
    public void setPtgFile(String id, String filename) {
        if (id == null) {
            for (Entry<String, Viewpoint> e : viewpoints.entrySet()) {
                e.getValue().setPointcloud(getString(filename, e.getKey()));
            }
        } else {
            if (!viewpoints.containsKey(id)) {
                viewpoints.put(id, new Viewpoint(defaultTrans, defaultPtg, defaultRem, defaultRgb));
            }
            viewpoints.get(id).setPointcloud(filename);
        }
    }

    
    public void addRemImg(String id, String filename, String panoType) {
        if (panoType == null) {
            addRemImg(id, filename);
        } else if (panoType.equals("cubic")) {
            for (int i = 0; i <= 5; i++) {
                addRemImg(id, filename + "_" + i);
            }
        }
    }

    /**
     * Sets a rem image for a viewpoint with id. If id is null, rem images will
     * be set for all viewpoints.
     *
     * @param id id of the viewpoint
     * @param filename filename of the rem image
     */
    public void addRemImg(String id, String filename) {
        if (id == null) {
            for (Entry<String, Viewpoint> e : viewpoints.entrySet()) {
                e.getValue().addRemImage(getString(filename, e.getKey()));
            }
        } else {
            if (!viewpoints.containsKey(id)) {
                viewpoints.put(id, new Viewpoint(defaultTrans, defaultPtg, defaultRem, defaultRgb));
            }
            viewpoints.get(id).addRemImage(filename);
        }
    }

    /**
     * Sets rgb images for a viewpoint with id considering the panorama type (e.g. cubic).
     * 
     * @param id
     * @param filename
     * @param panoType 
     */
    public void addRgbImg(String id, String filename, String panoType) {
        if (panoType == null) {
            addRgbImg(id, filename);
        } else if (panoType.equals("cubic")) {
            for (int i = 0; i <= 5; i++) {
                addRgbImg(id, filename + "_" + i);
            }
        }
    }

    /**
     * Sets a rgb image for a viewpoint with id. If id is null, rgb images will
     * be set for all viewpoints.
     *
     * @param id id of the viewpoint
     * @param filename filename of the rgb image
     */
    public void addRgbImg(String id, String filename) {
        if (id == null) {
            for (Entry<String, Viewpoint> e : viewpoints.entrySet()) {
                e.getValue().addRgbImage(getString(filename, e.getKey()));
            }
        } else {
            if (!viewpoints.containsKey(id)) {
                viewpoints.put(id, new Viewpoint(defaultTrans, defaultPtg, defaultRem, defaultRgb));
            }
            viewpoints.get(id).addRgbImage(filename);
        }
    }

    /**
     * Adds transformation parameters for viewpoint with id. If id is null,
     * transformations are set for all viewpoints.
     *
     * @param id id of the viewpoint
     * @param trans {@link Transformation} for this viewpoint
     */
    public void addTrans(String id, Transformation trans) {
        if (id == null) {
            for (Viewpoint v : viewpoints.values()) {
                v.addTransformation(trans);
            }
            if (trans == null) {
                defaultTrans.inverse();
            } else {
                defaultTrans.transform(trans);
            }
        } else {
            if (!viewpoints.containsKey(id)) {
                viewpoints.put(id, new Viewpoint(defaultTrans, defaultPtg, defaultRem, defaultRgb));
            }
            viewpoints.get(id).addTransformation(trans);
        }
    }

    public void addPtgTrans(String id, Transformation trans) {
        if (id == null) {
            for (Viewpoint v : viewpoints.values()) {
                v.addPointcloudTransformation(trans);
            }
            if (trans == null) {
                defaultPtg.inverse();
            } else {
                defaultPtg.transform(trans);
            }
        } else {
            if (!viewpoints.containsKey(id)) {
                viewpoints.put(id, new Viewpoint(defaultTrans, defaultPtg, defaultRem, defaultRgb));
            }
            viewpoints.get(id).addPointcloudTransformation(trans);
        }
    }

    public void addRemTrans(String id, Transformation trans) {
        if (id == null) {
            for (Viewpoint v : viewpoints.values()) {
                v.addRemTransformation(trans);
            }
            if (trans == null) {
                defaultRem.inverse();
            } else {
                defaultRem.transform(trans);
            }
        } else {
            if (!viewpoints.containsKey(id)) {
                viewpoints.put(id, new Viewpoint(defaultTrans, defaultPtg, defaultRem, defaultRgb));
            }
            viewpoints.get(id).addRemTransformation(trans);
        }
    }

    /**
     * Adds rgb transformation parameters for viewpoint with id. If id is null,
     * rgb transformations are set for all viewpoints.
     *
     * @param id id of the viewpoint
     * @param trans rgb {@link Transformation} for this viewpoint
     */
    public void addRgbTrans(String id, Transformation trans) {
        if (id == null) {
            for (Viewpoint v : viewpoints.values()) {
                v.addRgbTransformation(trans);
            }
            if (trans == null) {
                defaultRgb.inverse();
            } else {
                defaultRgb.transform(trans);
            }
        } else {
            if (!viewpoints.containsKey(id)) {
                viewpoints.put(id, new Viewpoint(defaultTrans, defaultPtg, defaultRem, defaultRgb));
            }
            viewpoints.get(id).addRgbTransformation(trans);
        }
    }

    /**
     * Replaces $-signs with IDs.
     *
     * @param original original String
     * @param id
     * @return
     */
    private static String getString(String original, String id) {
        return original.replaceAll("\\$", id);
    }

    public String toString() {
        String s = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
        s += "<genericviewer version=\"0.1\">";
        s += "<project name=\"" + place + "\">";
        s += "<creator>i3mainz</creator>";
        s += "<date>" + new java.util.Date() + "</date>";
        s += "<comment></comment>";
        s += "</project>";

        s += "<spatialcontext id=\"" + name + "\">";
        s += "<resources>";

        s += "<resource id=\"metadata\">";
        s += "<spatialcontext>";
        s += "<place>" + place + "</place>";
        s += "<date>" + date + "</date>";
        s += "</spatialcontext>";
        s += transformation;
        s += "</resource>";

        s += "<resource id=\"media\">";
        for (Floorplan f : floorplans) {
            s += f;
        }
        s += "</resource>";

        s += "<resource id=\"viewpoint\">";
        for (Viewpoint v : viewpoints.values()) {
            s += v;
        }
        s += "</resource>";

        s += "</resources>";
        s += "</spatialcontext>";

        s += "</genericviewer>";
        return s;
    }

}
