package com.blogspot.votung.jade.agentsystem.server.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AgentContrllerFrame extends JFrame {
	private ServerMonitorFrame agentController;
	private String state;
	public AgentContrllerFrame(final ServerMonitorFrame serverMonitorFrame) {
		this.agentController = serverMonitorFrame;
		setResizable(false);
		setSize(331, 227);
		getContentPane().setLayout(null);
		setTitle(serverMonitorFrame.getName());
		JButton btnSuspended = new JButton("SUSPENDED");
		btnSuspended.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serverMonitorFrame.suspend();
				JOptionPane.showMessageDialog(AgentContrllerFrame.this, "Suspend " + serverMonitorFrame.getName());
			}
		});
		btnSuspended.setBounds(118, 34, 103, 23);
		getContentPane().add(btnSuspended);

		JButton btnResume = new JButton("RESUME");
		btnResume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serverMonitorFrame.activate();
				JOptionPane.showMessageDialog(AgentContrllerFrame.this, "Resume " + serverMonitorFrame.getName());
			}
		});
		btnResume.setBounds(118, 65, 103, 23);
		getContentPane().add(btnResume);

		JButton btnKill = new JButton("KILL");
		btnKill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serverMonitorFrame.kill();
				JOptionPane.showMessageDialog(AgentContrllerFrame.this, "Kill " + serverMonitorFrame.getName());
			}
		});
		btnKill.setBounds(118, 99, 103, 23);
		getContentPane().add(btnKill);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private static final long serialVersionUID = 1L;

}
