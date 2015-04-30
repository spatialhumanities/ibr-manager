/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.i3mainz.ibr.files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Example implementation
 * @author julia.ganicheva
 */
public class MainTest {

    public static void main(String[] args) throws IOException {
        //Read & write viewpoint trans
        //File input = new File("f:/1.Oberwesel/05.Upload/01.Data/140519_hildesheim/02.Cyclone_Report/Diagnostik_Registrierung_Kirche.txt ");
		File input = new File("f:/L-Test/05.Cyclone_Report/cyclone_report.txt ");
		
//      //File file = new File("P:/DIO_3D/_Abgabe/Hildesheim/Diagnostik_Registrierung_Kirche.txt");
        //File output1 = new File ("f:/1.Oberwesel/05.Upload/01.Data/140519_hildesheim/07.PRJXML/trans_Kirche.xml");  
		File output1 = new File ("f:/L-Test/06.Project_XML/trans_gamma.xml"); 
        List <TransformParam> trans = new ArrayList <TransformParam>();        
		trans = CycloneReader.readCyclone(input);  
		
        ViewpointWriter.writeScannPostion(trans,output1,"trans");
        
        //Read & write rgb/rem rgbtrans
        File folder = new File ("f:/1.Oberwesel/05.Upload/01.Data/140519_hildesheim/05.TransXML/kirche_recalc");  
        //File output2 = new File ("f:/1.Oberwesel/5.Upload/01.Data/140508_obw_all/01.Project_xml/140509/rgbtrans_p5.xml");
        File output2 = new File ("f:/1.Oberwesel/05.Upload/01.Data/140519_hildesheim/07.PRJXML/rgbtrans_kirche_inv.xml");
        List <TransformParam> rgbtrans = new ArrayList <TransformParam>();        
        //rgbtrans=PanoglReader.readPanogl(folder);
        ViewpointWriter.writeScannPostion(rgbtrans,output2,"rgbtrans");
        
        
        }
    }

