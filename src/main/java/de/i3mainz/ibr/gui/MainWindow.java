package de.i3mainz.ibr.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private static ProgressPanel progressPanel;
	private static ProcedurePanel procedurePanel;
	
	MainWindow() {
		super("IBR Admin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		progressPanel = new ProgressPanel();
		procedurePanel = new ProcedurePanel();
		
		add(procedurePanel,BorderLayout.PAGE_START);
		add(progressPanel,BorderLayout.PAGE_END);
		
		setLocation(300,200);
		pack();
		setVisible(true);
	}
	
	public static ProgressPanel progressPanel() {
		return progressPanel;
	}
	
	public static ProcedurePanel procedurePanel() {
		return procedurePanel;
	}

}
