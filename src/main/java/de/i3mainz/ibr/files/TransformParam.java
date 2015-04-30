/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.i3mainz.ibr.files;

import java.util.Comparator;

/**
 * Object Class stores 7 parameters transformation values
 * @author julia.ganicheva
 */
public class TransformParam  {
    
    private String name;
    private double xtrans;
    private double ytrans;
    private double ztrans;
    private double xrot;
    private double yrot;
    private double zrot;
    private double scale;
    
    /**
     * Set 7 parameter transformation values
     * @param name 
     * @param xtrans
     * @param ytrans
     * @param ztrans
     * @param xrot
     * @param yrot
     * @param zrot
     * @param scale 
     */
    
    public void setAll (String name, double xtrans,double ytrans,double ztrans,double xrot,double yrot,double zrot,double scale){        
        this.name=name;
        this.xtrans=xtrans;
        this.ytrans=ytrans;
        this.ztrans=ztrans;
        this.xrot=xrot;
        this.yrot=yrot;
        this.zrot=zrot;
        this.scale=scale;    
    }
    
    public void setName (String name){        
        this.name=name;        
    }
    public void setXtrans ( double xtrans){        
        this.xtrans=xtrans;           
    }
    public void setYtrans (double ytrans){ 
        this.ytrans=ytrans;
    }
    public void setZtrans (double ztrans){        
        this.ztrans=ztrans;          
    }
    public void setXrot (double xrot){ 
        this.xrot=xrot;
    }
    public void setYrot (double yrot){ 
        this.yrot=yrot;
    }
    public void setZrot (double zrot){ 
        this.zrot=zrot;
    }
    public void setScale (double scale){ 
        this.scale=scale;
    }
    
    public String getName (){
        return name;
    }
    public double getXtrans (){
        return xtrans;
    }
    public double getYtrans (){
        return ytrans;
    }
    public double getZtrans (){
        return ztrans;
    }
    public double getXrot (){
        return xrot;
    }
    public double getYrot (){
        return yrot;
    }
    public double getZrot (){
        return zrot;
    }
    public double getScale (){
        return scale;
    }
    
    /**
     *
     */
    public static Comparator<TransformParam> NameComparator = new Comparator<TransformParam>() {

	public int compare(TransformParam t1, TransformParam t2) {
	   String ViewpointName1 = t1.getName().toUpperCase();
	   String ViewpointName2 = t2.getName().toUpperCase();
	   //ascending order
	   return ViewpointName1.compareTo(ViewpointName2);
	   //descending order
	   //return StudentName2.compareTo(StudentName1);
        }

        
    };
}
