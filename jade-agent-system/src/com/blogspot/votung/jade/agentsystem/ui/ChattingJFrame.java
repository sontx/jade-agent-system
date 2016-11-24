package com.blogspot.votung.jade.agentsystem.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.blogspot.votung.jade.agentsystem.bo.IChatAgenBase;
import com.blogspot.votung.jade.agentsystem.client.agent.ChattingAgentClient;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;

public class ChattingJFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea textAreaEnterMessage;
	private JTextArea textAreaDislayMessage;
	private IChatAgenBase iChatAgenBase;
	private ACLMessage reply;
	private Agent agent;

	public ChattingJFrame(String title) {
		super(title);
		createPanelMain();
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				textAreaDislayMessage.setText("");
			}
		});
		/*
		 * this.iChatAgenBase = iChatAgenBase;
		 * setTitle(iChatAgenBase.getName());
		 */
	}

	private void createPanelMain() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 410, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButtonSend = new JButton("Send");
		btnNewButtonSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isWide()) {
					ChattingJFrame.this.reply.setContent("You: " + ChattingJFrame.this.textAreaEnterMessage.getText());
					ChattingJFrame.this.agent.send(ChattingJFrame.this.reply);
					// iChatAgenBase.sendText(getMessage());
					setTextDislay("Me: " + textAreaEnterMessage.getText());
					textAreaEnterMessage.setText("");
				} else {
					showMessage();
				}
			}
		});
		btnNewButtonSend.setBounds(314, 219, 75, 31);
		contentPane.add(btnNewButtonSend);
		textAreaEnterMessage = new JTextArea();
		JScrollPane scrollPaneTextEnterMessage = new JScrollPane(textAreaEnterMessage);
		scrollPaneTextEnterMessage.setBounds(10, 219, 294, 31);
		contentPane.add(scrollPaneTextEnterMessage);

		textAreaDislayMessage = new JTextArea();
		textAreaDislayMessage.setEditable(false);
		textAreaDislayMessage.setFont(new Font("Monospaced", Font.BOLD, 20));
		textAreaDislayMessage.setForeground(Color.BLUE);
		JScrollPane scrollPaneDislayMessage = new JScrollPane(textAreaDislayMessage);
		scrollPaneDislayMessage.setBounds(10, 11, 294, 184);
		contentPane.add(scrollPaneDislayMessage);
	}

	public void setTextDislay(String s) {
		textAreaDislayMessage.append(s + "\n");
	}

	public String getMessage() {
		return textAreaEnterMessage.getText();
	}

	private boolean isWide() {
		return textAreaEnterMessage.getText().equals("") ? true : false;
	}

	private void showMessage() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JOptionPane.showMessageDialog(null, "Enter again!");
			}
		});
	}

	public void appendText(String content) {
		textAreaDislayMessage.append(content + "\n");
	}

	public void setReply(ACLMessage who) {
		this.reply = who;
	}

	public void setAgent(Agent chattingAgentClient) {
		this.agent = chattingAgentClient;
	}
}
