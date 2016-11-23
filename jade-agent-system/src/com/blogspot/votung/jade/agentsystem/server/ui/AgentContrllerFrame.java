package com.blogspot.votung.jade.agentsystem.server.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AgentContrllerFrame extends JFrame {
	private AgentController agentController;
	
	public AgentContrllerFrame(final AgentController agentController) {
		this.agentController = agentController;
		setResizable(false);
		setSize(331, 227);
		getContentPane().setLayout(null);
		try {
			setTitle(agentController.getName());
		} catch (StaleProxyException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		JButton btnSuspended = new JButton("SUSPENDED");
		btnSuspended.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					agentController.suspend();
					JOptionPane.showMessageDialog(AgentContrllerFrame.this, "Suspend " + agentController.getName());
				} catch (StaleProxyException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(AgentContrllerFrame.this, e1.getMessage());
				}
			}
		});
		btnSuspended.setBounds(118, 34, 103, 23);
		getContentPane().add(btnSuspended);

		JButton btnResume = new JButton("RESUME");
		btnResume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					agentController.activate();
					JOptionPane.showMessageDialog(AgentContrllerFrame.this, "Resume " + agentController.getName());
				} catch (StaleProxyException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(AgentContrllerFrame.this, e1.getMessage());
				}
			}
		});
		btnResume.setBounds(118, 65, 103, 23);
		getContentPane().add(btnResume);

		JButton btnKill = new JButton("KILL");
		btnKill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					agentController.kill();
					JOptionPane.showMessageDialog(AgentContrllerFrame.this, "Kill " + agentController.getName());
				} catch (StaleProxyException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(AgentContrllerFrame.this, e1.getMessage());
				}
			}
		});
		btnKill.setBounds(118, 99, 103, 23);
		getContentPane().add(btnKill);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private static final long serialVersionUID = 1L;

}
