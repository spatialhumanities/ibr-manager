package de.i3mainz.ibr.gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class ProjectCreator extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	ProjectCreator() {
		super("IBR Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		
		
		setLocation(300,200);
		pack();
		setVisible(true);
	}
	
}
