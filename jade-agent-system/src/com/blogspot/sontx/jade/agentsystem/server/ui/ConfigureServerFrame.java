package com.blogspot.sontx.jade.agentsystem.server.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ConfigureServerFrame extends JFrame {
	public ConfigureServerFrame() {
		setTitle("Configure Server");
		setSize(347, 238);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(19, 155, 302, 45);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(10, 11, 89, 23);
		panel.add(btnOk);
		
		JButton btnDefault = new JButton("Default");
		btnDefault.setBounds(109, 11, 89, 23);
		panel.add(btnDefault);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(208, 11, 89, 23);
		panel.add(btnCancel);
		
		JLabel lblSaveImagesDirectory = new JLabel("Save Images Directory:");
		lblSaveImagesDirectory.setBounds(10, 11, 124, 14);
		getContentPane().add(lblSaveImagesDirectory);
		
		JLabel lblNewLabel = new JLabel("Port:");
		lblNewLabel.setBounds(10, 36, 46, 14);
		getContentPane().add(lblNewLabel);
		
		txtImagesDirectory = new JTextField();
		txtImagesDirectory.setBounds(144, 8, 174, 20);
		getContentPane().add(txtImagesDirectory);
		txtImagesDirectory.setColumns(10);
		
		txtPort = new JTextField();
		txtPort.setBounds(144, 33, 86, 20);
		getContentPane().add(txtPort);
		txtPort.setColumns(10);
	}
	private static final long serialVersionUID = 1L;
	private JTextField txtImagesDirectory;
	private JTextField txtPort;
}
