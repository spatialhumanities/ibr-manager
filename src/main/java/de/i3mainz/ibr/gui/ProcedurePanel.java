package de.i3mainz.ibr.gui;

import de.i3mainz.ibr.procedure.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

public class ProcedurePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JComboBox<Procedure> procedure;
	private JTextField srcTextField;
	private JButton srcButton;
	private JTextField destTextField;
	private JButton destButton;
	private JTextField paramField;
        
        private JFileChooser chooser = new JFileChooser();;
	       
	ProcedurePanel() {
		super();               
		setLayout(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 1;
                c.gridy = 1;
                
                 c.gridwidth = 4;
		
                // label for description
                final JEditorPane procedureDescr = new JEditorPane(); 
                procedureDescr.setContentType("text/html");
                procedureDescr.setEditable(false);
                procedureDescr.setPreferredSize(new Dimension(0, 170));
                
                final String ibrManDescr = "<h2> Welcome to IBR-Manager </h2>"
                        + "Choose a procedure to process your data.";
                procedureDescr.setText(ibrManDescr);

                JScrollPane sp = new JScrollPane(procedureDescr);
                sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //vertikale Scrollbar wird immer angezeigt
                add(sp, c);
                c.gridy++;
                
                
                // JComboBox for Procedures
		procedure = new JComboBox<Procedure>();
                procedure.addItem(null);
		procedure.addItem(new XYZtoRasterPTG());
		procedure.addItem(new PTGtoRasterPTG());
		procedure.addItem(new RasterPTGtoEquirectangular());
		procedure.addItem(new EquirectangulartoCubic());
		procedure.addItem(new ProjectXML());
		procedure.addItem(new Move());
		procedure.addItem(new Copy());
		procedure.addItem(new CycloneToIntermediateXML());
		procedure.addItem(new PanoGLToIntermedateXML());
                procedure.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if(procedure.getSelectedItem() == null) {
                            procedureDescr.setText(ibrManDescr);
                        } else {
                            procedureDescr.setText(((Procedure) procedure.getSelectedItem()).getProcedureDescription());
                        }
                    }
                });
                
               
		add(procedure, c);
                c.gridy++;
                
                c.gridwidth = 1;
                
                // tfs and btns for source, destination, parameter
		JPanel srcPanel = new JPanel();
		srcPanel.setLayout(new BorderLayout());
		srcTextField = new JTextField(20);
		srcPanel.add(srcTextField,BorderLayout.LINE_START);
		srcButton = new JButton();
		srcButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
			chooseSrc();
		}});
		srcPanel.add(srcButton,BorderLayout.LINE_END);
		
                c.gridx++;
                add(srcPanel, c);
                
		JPanel destPanel = new JPanel();
		destPanel.setLayout(new BorderLayout());
		destTextField = new JTextField(20);
		destPanel.add(destTextField,BorderLayout.LINE_START);
		destButton = new JButton();
		destButton.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {
			chooseDest();
		}});
		destPanel.add(destButton,BorderLayout.LINE_END);
                
		c.gridx++;
                add(destPanel,c);
		
		paramField = new JTextField(5);
                c.gridx++;
		add(paramField,c);
	}

        
        
	void start() {
		Procedure p = procedure.getItemAt(procedure.getSelectedIndex());
                if (p != null) {
                    p.execute(srcTextField.getText(),destTextField.getText(),paramField.getText());
                }
	}
	
	private void chooseSrc() {
		Procedure p = procedure.getItemAt(procedure.getSelectedIndex());
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setFileFilter(p.getFileNameExtensionFilter());
		chooser.setMultiSelectionEnabled(true);
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File[] files = chooser.getSelectedFiles();
			String text = files.length == 1 ? files[0].getAbsolutePath() : "\"" + files[0].getAbsolutePath() + "\"";
			for (int i=1; i<files.length; i++) {
				text += " \"" + files[i].getAbsolutePath() + "\"";
			}
			srcTextField.setText(text);
		}
	}
	 
	private void chooseDest() {
                chooser.setFileFilter(null);
		//Modified
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			String text = chooser.getSelectedFile().getAbsolutePath();
			destTextField.setText(text);
		}
	}
}
