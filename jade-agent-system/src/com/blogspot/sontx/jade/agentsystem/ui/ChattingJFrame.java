package com.blogspot.sontx.jade.agentsystem.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.blogspot.sontx.jade.agentsystem.bo.IChatAgenBase;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;
import java.awt.Color;

public class ChattingJFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea textAreaEnterMessage;
	private JTextArea textAreaDislayMessage;
	private IChatAgenBase iChatAgenBase;

	public ChattingJFrame(IChatAgenBase iChatAgenBase) {
		createPanelMain();
		this.iChatAgenBase = iChatAgenBase;
		setTitle(iChatAgenBase.getName());
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
					iChatAgenBase.sendText(getMessage());
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
		textAreaDislayMessage.setForeground(Color.BLUE);
		textAreaDislayMessage.setEnabled(false);
		textAreaDislayMessage.setEditable(false);
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
}
