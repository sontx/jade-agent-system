package com.blogspot.votung.jade.agentsystem.server.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextPane;

public class DiskInfoJFrame extends JFrame {
	JTextPane textPane;
	public DiskInfoJFrame() {
		setTitle("Disk Information");
		setSize(239, 281);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		getContentPane().add(textPane, BorderLayout.CENTER);
	}

	private static final long serialVersionUID = 1L;

	public void setDiskInfo(String st) {
		textPane.setText(st);
	}

}
