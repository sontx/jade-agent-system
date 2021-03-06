package com.blogspot.votung.jade.agentsystem.server.ui;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;

import com.blogspot.votung.jade.agentsystem.client.ClientProgram;
import com.blogspot.votung.jade.agentsystem.server.ServerProgram;
import com.blogspot.votung.jade.agentsystem.server.agent.ServerAgent;

import jade.content.lang.Codec.CodecException;
import jade.content.onto.OntologyException;
import jade.core.AID;
import jade.core.Location;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.lang.acl.ACLMessage;
import jade.util.leap.Iterator;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;

import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ServerMonitorFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private ServerAgent serverAgent;
	private JTable table;
	private JLabel labAgentId;
	private JLabel labAgentName;
	private JLabel labAgentPosition;
	private JLabel labAgentServerMonitor;
	private JLabel labAgentStatus;
	private JLabel labWorkstationName;
	private JLabel labWorkstationIP;
	private JLabel labWorkstationOS;
	private JLabel labWorkstationArchitecture;
	private JLabel labWorkstationVersion;
	private JLabel labConnectionServerIP;
	private JLabel labConnectionServerPort;
	private JList<Location> lstLocation;
	private JLabel labelHostname;

	public ServerMonitorFrame(ServerAgent serverAgent) {
		this.serverAgent = serverAgent;
		setResizable(false);
		setTitle("Server Monitor: Agent Management System");
		setSize(834, 773);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

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

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshAgentsList();
			}
		});
		btnRefresh.setBounds(346, 50, 102, 28);
		panel.add(btnRefresh);

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
		lblName.setBounds(10, 36, 75, 14);
		panel_1.add(lblName);

		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setBounds(10, 61, 75, 14);
		panel_1.add(lblPosition);

		JLabel lblServerMonitor = new JLabel("Server Monitor:");
		lblServerMonitor.setBounds(10, 86, 75, 14);
		panel_1.add(lblServerMonitor);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(10, 111, 46, 14);
		panel_1.add(lblStatus);

		labAgentServerMonitor = new JLabel("Server Monitor");
		labAgentServerMonitor.setBounds(114, 86, 227, 14);
		panel_1.add(labAgentServerMonitor);

		labAgentStatus = new JLabel("...");
		labAgentStatus.setBounds(114, 111, 227, 14);
		panel_1.add(labAgentStatus);

		labAgentPosition = new JLabel("...");
		labAgentPosition.setBounds(114, 61, 227, 14);
		panel_1.add(labAgentPosition);

		labAgentName = new JLabel("...");
		labAgentName.setBounds(114, 36, 227, 14);
		panel_1.add(labAgentName);

		labAgentId = new JLabel("...");
		labAgentId.setBounds(114, 11, 227, 14);
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
		lblName_1.setBounds(10, 11, 66, 14);
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
		lblVersion.setBounds(30, 111, 79, 14);
		panel_2.add(lblVersion);

		labWorkstationArchitecture = new JLabel("...");
		labWorkstationArchitecture.setBounds(126, 86, 218, 14);
		panel_2.add(labWorkstationArchitecture);

		labWorkstationVersion = new JLabel("...");
		labWorkstationVersion.setBounds(126, 111, 218, 14);
		panel_2.add(labWorkstationVersion);

		labWorkstationOS = new JLabel("...");
		labWorkstationOS.setBounds(126, 61, 218, 14);
		panel_2.add(labWorkstationOS);

		labWorkstationIP = new JLabel("...");
		labWorkstationIP.setBounds(126, 36, 218, 14);
		panel_2.add(labWorkstationIP);

		labWorkstationName = new JLabel("...");
		labWorkstationName.setBounds(126, 11, 218, 14);
		panel_2.add(labWorkstationName);

		JLabel lblMoveAgent = new JLabel("Move Agent");
		lblMoveAgent.setBounds(454, 518, 80, 14);
		getContentPane().add(lblMoveAgent);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(457, 543, 354, 170);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);

		JLabel lblPosition_1 = new JLabel("Position");
		lblPosition_1.setBounds(10, 11, 46, 14);
		panel_3.add(lblPosition_1);

		JButton btnMove = new JButton("Move");
		btnMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						moveAgent();
					}
				}).start();

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

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 31, 236, 103);
		panel_3.add(scrollPane_1);

		labelHostname = new JLabel("Selected Hostname");
		labelHostname.setBounds(10, 142, 172, 17);
		panel_3.add(labelHostname);

		lstLocation = new JList<Location>();
		lstLocation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						Location location = lstLocation.getSelectedValue();
						try {
							ServerMonitorFrame.this.labelHostname
									.setText(InetAddress.getByName(location.getAddress()).getHostName());
						} catch (UnknownHostException e) {
							e.printStackTrace();
						}
					}
				}).start();

			}
		});
		lstLocation.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(lstLocation);

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
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				focusTableRow();
			}
		});

	}

	private void loadServerInformation() {
		labConnectionServerIP.setText(System.getProperty("address"));
		labConnectionServerPort.setText(System.getProperty("port"));

		try {
			labWorkstationName.setText(InetAddress.getLocalHost().getHostName());
		} catch (Exception e1) {
			labWorkstationName.setText(System.getProperty("sun.desktop"));
		}

		labWorkstationIP.setText(System.getProperty("address"));
		labWorkstationOS.setText(System.getProperty("os.name"));
		labWorkstationArchitecture.setText(System.getProperty("os.arch"));
		labWorkstationVersion.setText(System.getProperty("os.version"));
	}

	private AMSAgentDescriptionModel getSelectedAgent() {
		if (table.getSelectedRow() > -1)
			return ((AMSAgentDescriptionModel) table.getValueAt(table.getSelectedRow(), 0));
		return null;
	}

	protected void focusTableRow() {
		AMSAgentDescriptionModel model = getSelectedAgent();
		if (model != null) {
			String agentName = model.desc.getName().getName();
			labAgentId.setText(agentName);
			labAgentName.setText(model.toString());
			labAgentPosition.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
			labAgentStatus.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
		}
	}

	protected void refreshAgentsList() {
		serverAgent.refreshAgentsList();
	}

	protected void shutdownServer() {
		if (JOptionPane.showConfirmDialog(this, "Do you want to shutdown server?") == JOptionPane.OK_OPTION) {
			try {
				serverAgent.shutdownJade();
			} catch (CodecException | OntologyException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, e.getMessage());
				return;
			}
			dispose();
		}
	}

	protected void moveServer() {

	}

	protected void updateAgentMovableList() {
		serverAgent.updateMoveList();
	}

	protected void moveAgent() {
		Location location = lstLocation.getSelectedValue();
		if (location != null) {
			AMSAgentDescriptionModel model = getSelectedAgent();
			if (model != null) {
				try {
					AgentController agentController = ServerProgram.getMainContainer()
							.getAgent(model.desc.getName().getLocalName());
					agentController.move(location);
					JOptionPane.showMessageDialog(this, "Moved " + agentController.getName() + " to " + location);
				} catch (ControllerException e) {
					ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
					String who = model.desc.getName().getLocalName();
					msg.addReceiver(new AID(who, AID.ISLOCALNAME));
					msg.setOntology("move");
					msg.setContent(location.getName());
					serverAgent.send(msg);
					JOptionPane.showMessageDialog(this, "Moved " + who + " to " + location);
				}
			}
		}
	}

	protected void showDisk() {
		serverAgent.showDisk();
	}

	protected void logoutComputer() {
		serverAgent.orderedLogout();
	}

	protected void restartComputer() {
		serverAgent.orderedRestart();

	}

	protected void shutdownComputer() {
		serverAgent.orderedShutdown();

	}

	protected void captureScreen() {
		serverAgent.captureScreen();
	}

	protected void chat() {
		serverAgent.chat();
	}

	protected void sendMessage() {
		serverAgent.sendMessage();
	}

	protected void controlAgent() {
		AMSAgentDescriptionModel model = getSelectedAgent();
		if (model != null) {
			/*AgentController agentController = ServerProgram.getMainContainer()
					.getAgent(model.desc.getName().getLocalName());
			AgentContrllerFrame agentContrllerFrame = new AgentContrllerFrame(agentController);
			agentContrllerFrame.setVisible(true);*/
			new AgentContrllerFrame(ServerMonitorFrame.this);
		}
	}

	protected void deleteAgent() {
		AMSAgentDescriptionModel model = getSelectedAgent();
		if (model != null) {
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			String who = model.desc.getName().getLocalName();
			msg.addReceiver(new AID(who, AID.ISLOCALNAME));
			msg.setOntology("delete");
			serverAgent.send(msg);
			JOptionPane.showMessageDialog(this, "Deleted" + who);
		}
	}

	protected void createAgent() {
		try {
			/*
			 * String name = JOptionPane.showInputDialog(this,
			 * "Enter agent name");
			 */

			CreateAgentFrame createFrame = new CreateAgentFrame(this);

			/*
			 * if (name != null && name.equals(name)) { AgentController
			 * agentController =
			 * ServerProgram.getMainContainer().createNewAgent(name,
			 * "com.blogspot.votung.jade.agentsystem.server.agent.ExampleAgent",
			 * new Object[] {}); agentController.start();
			 * JOptionPane.showMessageDialog(this, "Created " +
			 * agentController.getName());
			 * 
			 * refreshAgentsList(); }
			 */
		} /*
			 * catch (StaleProxyException e) { // TODO Auto-generated catch
			 * block e.printStackTrace(); }
			 */catch (Exception e) {
			// TODO: handle exception
		}
	}

	private String selectedAgent;

	public void setSelectedAgent(String s) {
		this.selectedAgent = s;
	}

	public void createAgentCallback() {
		try {
			if ("Logout".equals(selectedAgent)) {
				ServerProgram.getMainContainer()
						.createNewAgent("logout-client",
								"com.blogspot.votung.jade.agentsystem.client.agent.LogoutPCAgent", new Object[] {})
						.start();
			} else if ("Shutdown".equals(selectedAgent)) {
				ServerProgram.getMainContainer()
						.createNewAgent("shutdown-client",
								"com.blogspot.votung.jade.agentsystem.client.agent.ShutdownPCAgent", new Object[] {})
						.start();
			} else if ("Restart".equals(selectedAgent)) {
				ServerProgram.getMainContainer()
						.createNewAgent("restart-client",
								"com.blogspot.votung.jade.agentsystem.client.agent.RestartPCAgent", new Object[] {})
						.start();
			} else if ("Disk".equals(selectedAgent)) {
				ServerProgram.getMainContainer()
						.createNewAgent("disk-client",
								"com.blogspot.votung.jade.agentsystem.client.agent.DriveInformationsAgent",
								new Object[] {})
						.start();
			} else if ("Capture".equals(selectedAgent)) {
				ServerProgram.getMainContainer()
						.createNewAgent("capture-client",
								"com.blogspot.votung.jade.agentsystem.client.agent.ScreenCaptureAgent", new Object[] {})
						.start();
			} else if ("Chat".equals(selectedAgent)) {
				ServerProgram.getMainContainer()
						.createNewAgent("chat-client",
								"com.blogspot.votung.jade.agentsystem.client.agent.ChattingAgentClient",
								new Object[] {})
						.start();
			} else if ("Message".equals(selectedAgent)) {
				ServerProgram.getMainContainer()
						.createNewAgent("send-message-client",
								"com.blogspot.votung.jade.agentsystem.client.agent.ReceiveAgentClient", new Object[] {})
						.start();
			}
			refreshAgentsList();
		} catch (StaleProxyException e) {

		}
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
		Object[][] data = new Object[descs.length][4];
		for (int i = 0; i < descs.length; i++) {
			AMSAgentDescription desc = descs[i];
			String[] addresses = desc.getName().getAddressesArray();
			String name = desc.getName().getName();
			data[i][0] = new AMSAgentDescriptionModel(desc);
			data[i][1] = desc.getState();
			data[i][2] = addresses != null && addresses.length > 0 ? addresses[0] : "";
			data[i][3] = getIPAddressFromName(name);
			if (data[i][0].toString().equals("ams")) {
				System.setProperty("address", data[i][3].toString());
			}
		}
		table.setModel(new DefaultTableModel(data, new String[] { "Name", "Status", "Position", "IP" }));
		loadServerInformation();
	}

	private class AMSAgentDescriptionModel {
		private AMSAgentDescription desc;

		public AMSAgentDescriptionModel(AMSAgentDescription desc) {
			this.desc = desc;
		}

		@Override
		public String toString() {
			return desc.getName().getLocalName();
		}
	}

	public void updateLocations(Iterator iterator) {
		DefaultListModel<Location> listModel = new DefaultListModel<Location>();
		while (iterator.hasNext()) {
			Location location = (Location) iterator.next();
			listModel.addElement(location);
		}
		lstLocation.setModel(listModel);
	}

	public void suspend() {
		AMSAgentDescriptionModel model = getSelectedAgent();
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		String who = model.desc.getName().getLocalName();
		msg.addReceiver(new AID(who, AID.ISLOCALNAME));
		msg.setOntology("suspend");
		serverAgent.send(msg);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JOptionPane.showMessageDialog(null, "Suspended");
			}
		});
	}

	public void activate() {
		AMSAgentDescriptionModel model = getSelectedAgent();
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		String who = model.desc.getName().getLocalName();
		msg.addReceiver(new AID(who, AID.ISLOCALNAME));
		msg.setOntology("active");
		serverAgent.send(msg);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JOptionPane.showMessageDialog(null, "Actived");
			}
		});
	}

	public void kill() {
		AMSAgentDescriptionModel model = getSelectedAgent();
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		String who = model.desc.getName().getLocalName();
		msg.addReceiver(new AID(who, AID.ISLOCALNAME));
		msg.setOntology("delete");
		serverAgent.send(msg);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JOptionPane.showMessageDialog(null, "Deleted");
			}
		});
	}
}
