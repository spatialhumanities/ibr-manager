/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.i3mainz.ibr.files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author julia.ganicheva
 */
public class ViewpointWriter {

    /**
     * Writes xml nodes for transformation to intermediate project file.
     *
     * @param list ArrayList(TransformParam)
     * @param file output file name and path
     * @param transformationtype fill in tag name "rgbtrans" or "trans" or
     * "remtrans"
     * @return
     */
    static public File writeScannPostion(List<TransformParam> list, File file, String transformationtype) {
        String inverse = "";
        if (transformationtype.equals("rgbtrans"))
            inverse = "inverse=\"true\" ";
        
        try (FileWriter fw = new FileWriter(file.getAbsoluteFile(), true); BufferedWriter bw = new BufferedWriter(fw)) {
            String content = "";
            for (int i = 0; i < list.size(); i++) {
                content += "<" + transformationtype + " id=\"" + list.get(i).getName() + "\" "
                        + inverse
                        + "transl-x=\"" + list.get(i).getXtrans() + "\" "
                        + "transl-y=\"" + list.get(i).getYtrans() + "\" "
                        + "transl-z=\"" + list.get(i).getZtrans() + "\" "
                        + "rot-x=\"" + list.get(i).getXrot() + "\" "
                        + "rot-y=\"" + list.get(i).getYrot() + "\" "
                        + "rot-z=\"" + list.get(i).getZrot() + "\" "
                        + "scale=\"" + list.get(i).getScale() + "\" "
                        + "/>\n";
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            bw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
