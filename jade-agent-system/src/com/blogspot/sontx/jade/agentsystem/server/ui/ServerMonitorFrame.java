package com.blogspot.sontx.jade.agentsystem.server.ui;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;

import jade.domain.FIPAAgentManagement.AMSAgentDescription;

import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class ServerMonitorFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JLabel labAgentId;
	private JLabel labAgentName;
	private JLabel labAgentPosition;
	private JLabel labAgentServerMonitor;
	private JLabel labAgentCreatedTime;
	private JLabel labAgentStatus;
	private JLabel labWorkstationName;
	private JLabel labWorkstationIP;
	private JLabel labWorkstationOS;
	private JLabel labWorkstationArchitecture;
	private JLabel labWorkstationVersion;
	private JLabel labConnectionServerIP;
	private JLabel labConnectionServerPort;

	public ServerMonitorFrame() {
		setResizable(false);
		setTitle("Server Monitor: Agent Management System");
		setSize(834, 773);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnManageServer = new JMenu("Manage Server");
		menuBar.add(mnManageServer);

		JMenuItem mntmMoveServer = new JMenuItem("Move Server...");
		mntmMoveServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moveServer();
			}
		});
		mnManageServer.add(mntmMoveServer);

		JMenuItem mntmShutdownServer = new JMenuItem("Shutdown Server");
		mntmShutdownServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shutdownServer();
			}
		});
		mnManageServer.add(mntmShutdownServer);

		JMenu mnGeneralControl = new JMenu("General Control");
		menuBar.add(mnGeneralControl);

		JMenu mnInfo = new JMenu("Information");
		menuBar.add(mnInfo);

		JMenuItem mntmAbout = new JMenuItem("About...");
		mnInfo.add(mntmAbout);

		JMenuItem mntmHelp = new JMenuItem("Help...");
		mnInfo.add(mntmHelp);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 11, 798, 91);
		getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnCreateAgent = new JButton("Create Agent");
		btnCreateAgent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createAgent();
			}
		});
		btnCreateAgent.setBounds(10, 11, 102, 28);
		panel.add(btnCreateAgent);

		JButton btnDeleteAgent = new JButton("Delete Agent");
		btnDeleteAgent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteAgent();
			}
		});
		btnDeleteAgent.setBounds(122, 11, 102, 28);
		panel.add(btnDeleteAgent);

		JButton btnControlAgent = new JButton("Control Agent");
		btnControlAgent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlAgent();
			}
		});
		btnControlAgent.setBounds(234, 11, 102, 28);
		panel.add(btnControlAgent);

		JButton btnSendMessage = new JButton("Send Message");
		btnSendMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});
		btnSendMessage.setBounds(346, 11, 102, 28);
		panel.add(btnSendMessage);

		JButton btnChat = new JButton("Chat");
		btnChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chat();
			}
		});
		btnChat.setBounds(458, 11, 102, 28);
		panel.add(btnChat);

		JButton btnCaptureScreen = new JButton("Capture");
		btnCaptureScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				captureScreen();
			}
		});
		btnCaptureScreen.setBounds(570, 11, 102, 28);
		panel.add(btnCaptureScreen);

		JButton btnShutdown = new JButton("Shutdown");
		btnShutdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shutdownComputer();
			}
		});
		btnShutdown.setBounds(682, 11, 102, 28);
		panel.add(btnShutdown);

		JButton btnRestart = new JButton("Restart");
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restartComputer();
			}
		});
		btnRestart.setBounds(10, 50, 102, 28);
		panel.add(btnRestart);

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logoutComputer();
			}
		});
		btnLogout.setBounds(122, 50, 102, 28);
		panel.add(btnLogout);

		JButton btnDisk = new JButton("Disk");
		btnDisk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showDisk();
			}
		});
		btnDisk.setBounds(234, 50, 102, 28);
		panel.add(btnDisk);

		JLabel lblNewLabel = new JLabel("Agents List");
		lblNewLabel.setBounds(10, 115, 86, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblAgentInformation = new JLabel("Agent Information");
		lblAgentInformation.setBounds(454, 113, 104, 14);
		getContentPane().add(lblAgentInformation);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(457, 141, 351, 194);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 11, 46, 14);
		panel_1.add(lblId);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 36, 46, 14);
		panel_1.add(lblName);

		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setBounds(10, 61, 46, 14);
		panel_1.add(lblPosition);

		JLabel lblServerMonitor = new JLabel("Server Monitor:");
		lblServerMonitor.setBounds(10, 86, 75, 14);
		panel_1.add(lblServerMonitor);

		JLabel lblCreatedTime = new JLabel("Created Time:");
		lblCreatedTime.setBounds(10, 111, 75, 14);
		panel_1.add(lblCreatedTime);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(10, 136, 46, 14);
		panel_1.add(lblStatus);

		labAgentServerMonitor = new JLabel("...");
		labAgentServerMonitor.setBounds(93, 86, 248, 14);
		panel_1.add(labAgentServerMonitor);

		labAgentCreatedTime = new JLabel("...");
		labAgentCreatedTime.setBounds(93, 111, 248, 14);
		panel_1.add(labAgentCreatedTime);

		labAgentStatus = new JLabel("...");
		labAgentStatus.setBounds(93, 136, 248, 14);
		panel_1.add(labAgentStatus);

		labAgentPosition = new JLabel("...");
		labAgentPosition.setBounds(93, 61, 248, 14);
		panel_1.add(labAgentPosition);

		labAgentName = new JLabel("...");
		labAgentName.setBounds(93, 36, 248, 14);
		panel_1.add(labAgentName);

		labAgentId = new JLabel("...");
		labAgentId.setBounds(93, 11, 248, 14);
		panel_1.add(labAgentId);

		JLabel lblWorkstation = new JLabel("Workstation Information");
		lblWorkstation.setBounds(454, 346, 148, 14);
		getContentPane().add(lblWorkstation);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(457, 371, 354, 136);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JLabel lblName_1 = new JLabel("Name:");
		lblName_1.setBounds(10, 11, 46, 14);
		panel_2.add(lblName_1);

		JLabel lblIp = new JLabel("IP:");
		lblIp.setBounds(10, 36, 46, 14);
		panel_2.add(lblIp);

		JLabel lblOs = new JLabel("OS:");
		lblOs.setBounds(10, 61, 46, 14);
		panel_2.add(lblOs);

		JLabel lblArchitecture = new JLabel("Architecture:");
		lblArchitecture.setBounds(31, 86, 78, 14);
		panel_2.add(lblArchitecture);

		JLabel lblVersion = new JLabel("Version:");
		lblVersion.setBounds(30, 111, 46, 14);
		panel_2.add(lblVersion);

		labWorkstationArchitecture = new JLabel("...");
		labWorkstationArchitecture.setBounds(96, 86, 248, 14);
		panel_2.add(labWorkstationArchitecture);

		labWorkstationVersion = new JLabel("...");
		labWorkstationVersion.setBounds(96, 111, 248, 14);
		panel_2.add(labWorkstationVersion);

		labWorkstationOS = new JLabel("...");
		labWorkstationOS.setBounds(96, 61, 248, 14);
		panel_2.add(labWorkstationOS);

		labWorkstationIP = new JLabel("...");
		labWorkstationIP.setBounds(96, 36, 248, 14);
		panel_2.add(labWorkstationIP);

		labWorkstationName = new JLabel("...");
		labWorkstationName.setBounds(96, 11, 248, 14);
		panel_2.add(labWorkstationName);

		JLabel lblMoveAgent = new JLabel("Move Agent");
		lblMoveAgent.setBounds(454, 518, 80, 14);
		getContentPane().add(lblMoveAgent);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(457, 543, 354, 145);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);

		JList list = new JList();
		list.setBounds(10, 34, 235, 100);
		panel_3.add(list);

		JLabel lblPosition_1 = new JLabel("Position");
		lblPosition_1.setBounds(10, 11, 46, 14);
		panel_3.add(lblPosition_1);

		JButton btnMove = new JButton("Move");
		btnMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moveAgent();
			}
		});
		btnMove.setBounds(255, 31, 89, 23);
		panel_3.add(btnMove);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateAgentMovableList();
			}
		});
		btnUpdate.setBounds(255, 65, 89, 23);
		panel_3.add(btnUpdate);

		JLabel lblConnectionInformation = new JLabel("Connection Information");
		lblConnectionInformation.setBounds(10, 603, 205, 14);
		getContentPane().add(lblConnectionInformation);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(10, 628, 383, 60);
		getContentPane().add(panel_4);
		panel_4.setLayout(null);

		JLabel lblServerIp = new JLabel("Server IP:");
		lblServerIp.setBounds(10, 11, 76, 14);
		panel_4.add(lblServerIp);

		JLabel lblServerPort = new JLabel("Server Port:");
		lblServerPort.setBounds(10, 36, 76, 14);
		panel_4.add(lblServerPort);

		labConnectionServerPort = new JLabel("3393");
		labConnectionServerPort.setBounds(75, 36, 46, 14);
		panel_4.add(labConnectionServerPort);

		labConnectionServerIP = new JLabel("192.168.1.x");
		labConnectionServerIP.setBounds(75, 11, 121, 14);
		panel_4.add(labConnectionServerIP);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 141, 437, 451);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(table);
		table.setShowHorizontalLines(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	protected void shutdownServer() {
		if (JOptionPane.showConfirmDialog(this, "Do you want to shutdown server?") == JOptionPane.OK_OPTION) {
			dispose();
		}
	}

	protected void moveServer() {

	}

	protected void updateAgentMovableList() {
		// TODO Auto-generated method stub

	}

	protected void moveAgent() {
		// TODO Auto-generated method stub

	}

	protected void showDisk() {
		// TODO Auto-generated method stub

	}

	protected void logoutComputer() {
		// TODO Auto-generated method stub

	}

	protected void restartComputer() {
		// TODO Auto-generated method stub

	}

	protected void shutdownComputer() {
		// TODO Auto-generated method stub

	}

	protected void captureScreen() {
		// TODO Auto-generated method stub

	}

	protected void chat() {
		// TODO Auto-generated method stub

	}

	protected void sendMessage() {
		// TODO Auto-generated method stub

	}

	protected void controlAgent() {
		// TODO Auto-generated method stub

	}

	protected void deleteAgent() {
		// TODO Auto-generated method stub

	}

	protected void createAgent() {
		// TODO Auto-generated method stub

	}

	public JLabel getLabAgentId() {
		return labAgentId;
	}

	public JLabel getLabAgentName() {
		return labAgentName;
	}

	public JLabel getLabAgentPosition() {
		return labAgentPosition;
	}

	public JLabel getLabAgentServerMonitor() {
		return labAgentServerMonitor;
	}

	public JLabel getLabAgentCreatedTime() {
		return labAgentCreatedTime;
	}

	public JLabel getLabAgentStatus() {
		return labAgentStatus;
	}

	public JLabel getLabWorkstationName() {
		return labWorkstationName;
	}

	public JLabel getLabWorkstationIP() {
		return labWorkstationIP;
	}

	public JLabel getLabWorkstationOS() {
		return labWorkstationOS;
	}

	public JLabel getLabWorkstationArchitecture() {
		return labWorkstationArchitecture;
	}

	public JLabel getLabWorkstationVersion() {
		return labWorkstationVersion;
	}

	public JLabel getLabConnectionServerIP() {
		return labConnectionServerIP;
	}

	public JLabel getLabConnectionServerPort() {
		return labConnectionServerPort;
	}
	
	private String getIPAddressFromName(String name) {
		int index1 = name.indexOf('@');
		int index2 = name.indexOf(':');
		return name.substring(index1 + 1, index2);
	}

	public void loadAgentsList(AMSAgentDescription[] descs) {
		Object[][] data = new Object[descs.length][5];
		for (int i = 0; i < descs.length; i++) {
			AMSAgentDescription desc = descs[i];
			String[] addresses = desc.getName().getAddressesArray();
			String name = desc.getName().getName();
			data[i][0] = desc.getName().getLocalName();
			data[i][1] = desc.getState();
			data[i][2] = addresses != null && addresses.length > 0 ? addresses[0] : "";
			data[i][3] = getIPAddressFromName(name);
			data[i][4] = "";
		}
		table.setModel(new DefaultTableModel(data, new String[] { "Name", "Status", "Position", "IP", "Created" }));
	}
}