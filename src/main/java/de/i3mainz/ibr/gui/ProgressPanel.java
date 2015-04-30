package de.i3mainz.ibr.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ProgressPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private static final int COUNTERLENGTH = 100;
	
	private JProgressBar full;
	private JProgressBar part;
	private JButton start;
	private JButton stop;
	private JLabel message;
	
	private boolean running = false;
	private int parts = 0;
	private int partNr = 0;
	
	ProgressPanel() {
		super();
		setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		full = new JProgressBar(0,COUNTERLENGTH);
		full.setStringPainted(true);
		panel.add(full,BorderLayout.PAGE_START);
		message = new JLabel("-\n to \n-");
		panel.add(message,BorderLayout.CENTER);
		part = new JProgressBar(0,COUNTERLENGTH);
		part.setStringPainted(true);
		panel.add(part,BorderLayout.PAGE_END);
		add(panel,BorderLayout.CENTER);
		
		start = new JButton("START");
		start.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {
			start();
		}});
		add(start,BorderLayout.LINE_END);
		stop = new JButton("STOP");
		stop.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {
			stop();
		}});
		add(stop,BorderLayout.LINE_START);
		
	}
	
	public boolean ping(double progress) {
		setCounter(part,(int)(progress*COUNTERLENGTH));
		setCounter(full,(int)(((partNr+progress)/parts)*COUNTERLENGTH));
		return running;
	}
	
	public boolean nextPart(String source, String destination) {
		partNr++;
		message.setText(source + "\n to \n" + destination);
		return running;
	}
	
	public void init(int parts) {
		this.parts = parts;
	}
	
	public void finish() {
		setCounter(part,0);
		setCounter(full,0);
		message.setText("-\n to \n-");
	}
	
	private void start() {
		partNr = -1;
		running = true;
		try {
			new Thread(new Runnable() {public void run() {
				MainWindow.procedurePanel().start();
			}}).start();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void stop() {
		running = false;
	}
	
	private static void setCounter(JProgressBar bar, int value) {
		final int _value = value;
		final JProgressBar _bar = bar;
		SwingUtilities.invokeLater(new Runnable() {public void run() {
			_bar.setValue(_value);
		}});
	}
	

}
