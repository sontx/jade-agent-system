package com.blogspot.votung.jade.agentsystem.client.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import com.blogspot.votung.jade.agentsystem.client.utils.IPAndHostname;
import com.blogspot.votung.jade.agentsystem.client.utils.IPScanner;

public class AutoScanIp extends JFrame {

	private List<IPAndHostname> list;
	private ConfigureClientFrame parentFrame;
	
	public AutoScanIp(ConfigureClientFrame configureClientFrame) {
		setTitle("Choose IP");
		getContentPane().setLayout(null);
		this.parentFrame = configureClientFrame;
		setSize(320, 120);
		
		JLabel lblChooseIp = new JLabel("Choose IP:");
		lblChooseIp.setBounds(10, 11, 60, 14);
		getContentPane().add(lblChooseIp);
		
		final JComboBox comboBoxListHosts = new JComboBox();
		comboBoxListHosts.setBounds(80, 8, 208, 20);
		list = new IPScanner().getListIps();
		addItems(comboBoxListHosts);
		getContentPane().add(comboBoxListHosts);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IPAndHostname selectedHost = list.get(comboBoxListHosts.getSelectedIndex());
				AutoScanIp.this.parentFrame.setIp(selectedHost.getIp());
				AutoScanIp.this.setVisible(false);
				AutoScanIp.this.dispose();
			}
		});
		btnOk.setBounds(107, 53, 89, 23);
		getContentPane().add(btnOk);
		setVisible(true);
	}

	private void addItems(JComboBox comboBoxListHosts) {
		for (IPAndHostname i : list) {
			comboBoxListHosts.addItem(i.getHostname() + " | " + i.getIp());
		}
	}

}
