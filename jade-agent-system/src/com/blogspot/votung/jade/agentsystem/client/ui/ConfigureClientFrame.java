package com.blogspot.votung.jade.agentsystem.client.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConfigureClientFrame extends JFrame {
	public ConfigureClientFrame() {
		setTitle("Configure Client");
		setSize(347, 180);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(71, 86, 200, 45);
		getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acceptConfigure();
			}
		});
		btnOk.setBounds(10, 11, 89, 23);
		panel.add(btnOk);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(109, 11, 89, 23);
		panel.add(btnExit);

		JLabel lblSaveImagesDirectory = new JLabel("Server IP:");
		lblSaveImagesDirectory.setBounds(10, 11, 124, 14);
		getContentPane().add(lblSaveImagesDirectory);

		JLabel lblNewLabel = new JLabel("Port:");
		lblNewLabel.setBounds(10, 36, 46, 14);
		getContentPane().add(lblNewLabel);

		txtIP = new JTextField();
		txtIP.setBounds(90, 8, 141, 20);
		getContentPane().add(txtIP);
		txtIP.setColumns(10);

		txtPort = new JTextField();
		txtPort.setText("3393");
		txtPort.setBounds(90, 33, 231, 20);
		getContentPane().add(txtPort);
		txtPort.setColumns(10);

		JButton btnAuto = new JButton("Auto");
		btnAuto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						new AutoScanIp(ConfigureClientFrame.this);
					}
				}).start();

			}
		});
		btnAuto.setBounds(241, 7, 80, 23);
		getContentPane().add(btnAuto);
	}

	private void updateClientConfigure(int port, String address) {
		if (onConfigurationChangedListener != null)
			onConfigurationChangedListener
					.onConfigurationChanged(address, port);
		dispose();
	}

	protected void acceptConfigure() {
		String address = txtIP.getText();
		String sPort = txtPort.getText();
		int port = Integer.valueOf(sPort);
		updateClientConfigure(port, address);
	}

	private static final long serialVersionUID = 1L;
	private JTextField txtIP;
	private JTextField txtPort;

	private OnConfigurationChangedListener onConfigurationChangedListener = null;

	public void setOnConfigurationChangedListener(
			OnConfigurationChangedListener listener) {
		onConfigurationChangedListener = listener;
	}

	public interface OnConfigurationChangedListener {
		void onConfigurationChanged(String address, int port);
	}

	public void setIp(String ip) {
		txtIP.setText(ip);
	}
}
