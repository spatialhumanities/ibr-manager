package de.i3mainz.ibr.gui;

import javax.swing.JFrame;

public class Main {
	
	private static JFrame frame;
	
	public static void main(String[] args) {

            frame = new MainWindow();
	}
	
	public static void openCreator() {
		frame.setVisible(false);
		frame = new ProjectCreator();
	}

}
