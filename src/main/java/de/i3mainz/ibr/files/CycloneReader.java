/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.i3mainz.ibr.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * CycloneReport File Reader
 *
 * @author julia.ganicheva
 */
public class CycloneReader {

    /**
     * Reads Cyclone Report and parses transformation values into 
     * {@link de.i3mainz.ibr.files.TransformParam} object list.
     *
     * @param file CycloneReport
     * @return ArrayList(TransformParam)
     * @throws IOException
     */
    static public List<TransformParam> readCyclone(File file) throws IOException {

        List<TransformParam> param = new ArrayList<TransformParam>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            int i = 1;
            int j = 0;
            String name = null;
            double xtrans = 0;
            double ytrans = 0;
            double ztrans = 0;
            double xrot = 0;
            double yrot = 0;
            double zrot = 0;
            double scale = 0;

            while ((line = br.readLine()) != null) {
                if (line.contains("Transformations")) {//start reading from line: "Transformation"                      
                    while (line.contains("Unused ControlSpace Objects") == false) { //read  until line: "Unused ControlSpace Objects"
                        line = br.readLine();
                        if (line.trim().length() == 0) {//skip empty lines
                            continue;
                        }
                        switch (i) {
                            case 1: //line: "20100219_HDS6000_Kirche029"
                                name = line;
                                break;
                            case 2: //line: "translation: (3564611.1646, 5780323.4962, 93.6004) m"
                                //xtrans = 3564611.1646; ytrans = 5780323.4962; ztrans = 93.6004;                                
                                StringTokenizer st = new StringTokenizer(line);
                                st.nextToken();//skip first part                                
                                String translation = st.nextToken();
                                xtrans = Double.valueOf(translation.substring(1, translation.length() - 1));
                                translation = st.nextToken();
                                ytrans = Double.valueOf(translation.substring(0, translation.length() - 1));
                                translation = st.nextToken();
                                ztrans = Double.valueOf(translation.substring(0, translation.length() - 1));
                                break;
                            case 3: //line: "rotation: (-0.0006, 0.0010, -1.0000):75.266749 deg"
                                //xvalue = -0.0006; yvalue = 0.0010; zvalue= -1.0000; multiply = 75.266749                                
                                StringTokenizer st2 = new StringTokenizer(line, ":");
                                st2.nextToken();//skip
                                String rotation = st2.nextToken();//rotation: (-0.0006, 0.0010, -1.0000)
                                StringTokenizer st3 = new StringTokenizer(rotation.substring(2, rotation.length() - 1), ",");//rotation.substring: -0.0006, 0.0010, -1.0000
                                double xvalue = Double.valueOf(st3.nextToken());
                                double yvalue = Double.valueOf(st3.nextToken());
                                double zvalue = Double.valueOf(st3.nextToken());
                                String multiplier = st2.nextToken();// multiplier: 75.266749 deg
                                StringTokenizer st4 = new StringTokenizer(multiplier);
                                double multiply = Double.valueOf(st4.nextToken());// multiply: 75.266749
                                
                                xrot = Math.toRadians(multiply * xvalue);
                                yrot = Math.toRadians(multiply * yvalue);
                                zrot = Math.toRadians(multiply * zvalue);
                                
                                // necessary to adapt transformation parameters! this is for parameters from cyclone.
                                // better solution MAY be this:
//                                xrot = Math.toRadians( multiply * yvalue);
//                                yrot = Math.toRadians(-multiply * xvalue);
//                                zrot = Math.toRadians( multiply * zvalue);
                                scale = 1;
                                break;
                        }
                        i++;
                        if (i > 3) {
                            TransformParam element = new TransformParam();
                            element.setAll(name, xtrans, ytrans, ztrans, xrot, yrot, zrot, scale);
                            param.add(j, element);
                            i = 1;
                            j++;
                        }
                    }
                }
            }
            Collections.sort(param, TransformParam.NameComparator);
            br.close();
        } catch (FileNotFoundException e) {
            throw e;
        }
        return param;
    }
}
