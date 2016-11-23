package com.blogspot.votung.jade.agentsystem.server.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.blogspot.votung.jade.agentsystem.server.agent.SendMessageAgent;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class SendMessageServerFrame extends JFrame {
	private SendMessageAgent sendMessageAgent;

	public SendMessageServerFrame(SendMessageAgent sendMessageAgent) {
		setTitle("Send Message");
		setSize(300, 221);
		getContentPane().setLayout(null);
		this.sendMessageAgent = sendMessageAgent;

		JButton btnSendMessage = new JButton("Send Message");
		btnSendMessage.setBounds(92, 143, 106, 23);
		getContentPane().add(btnSendMessage);
		btnSendMessage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				sendMessageAgent.sendText(textArea.getText());
				dispose();
			}
		});
		textArea = new JTextArea();
		textArea.setBounds(48, 28, 191, 104);
		getContentPane().add(textArea);
	}

	private JTextArea textArea;

	public String getMsg() {
		return textArea.getText();
	}

	public void setMsg(String s) {
		textArea.setText(s);
	}
}
