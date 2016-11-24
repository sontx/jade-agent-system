package com.blogspot.votung.jade.agentsystem.server.ui;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateAgentFrame extends JFrame implements ActionListener {
	private JComboBox comboBoxListAgents;
	public static String[] AGENTS = {"Đăng xuất", "Tắt máy", "Khởi động lại", "Ổ đĩa", "Chụp ảnh", "Tán gẫu", "Gửi thông báo"};
	private static String CREATE_AGENT_COMMAND = "create-agent";
	private ServerMonitorFrame parentFrame;
	
	public CreateAgentFrame(ServerMonitorFrame serverMonitorFrame) {
		super("Create Agent");
		setSize(200, 200);
		getContentPane().setLayout(null);
		this.parentFrame = serverMonitorFrame;
		
		comboBoxListAgents = new JComboBox();
		comboBoxListAgents.setBounds(10, 48, 163, 20);
		addListAgents(comboBoxListAgents);
		getContentPane().add(comboBoxListAgents);
		
		JButton btnCreateAgent = new JButton("T\u1EA1o Agent");
		btnCreateAgent.setActionCommand(CREATE_AGENT_COMMAND);
		btnCreateAgent.addActionListener(this);
		btnCreateAgent.setBounds(10, 112, 163, 23);
		getContentPane().add(btnCreateAgent);
		
		JLabel lblChnAgent = new JLabel("Ch\u1ECDn Agent");
		lblChnAgent.setBounds(10, 10, 111, 27);
		getContentPane().add(lblChnAgent);
		
		setVisible(true);
	}

	private void addListAgents(JComboBox comboBoxListAgents) {
		for (int i = 0; i < AGENTS.length; ++i) {
			comboBoxListAgents.addItem(AGENTS[i]);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (CREATE_AGENT_COMMAND.equals(ae.getActionCommand())) {
			parentFrame.setSelectedAgent(String.valueOf(comboBoxListAgents.getSelectedItem()));
			parentFrame.createAgentCallback();
			this.setVisible(false);
			dispose();
		}
	}

}
