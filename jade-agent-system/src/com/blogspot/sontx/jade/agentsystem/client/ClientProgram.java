package com.blogspot.sontx.jade.agentsystem.client;

import com.blogspot.sontx.jade.agentsystem.client.ui.ConfigureClientFrame;

import jade.core.ProfileImpl;
import jade.wrapper.StaleProxyException;

public class ClientProgram {
	private static jade.wrapper.AgentContainer agentContainer;

	public static jade.wrapper.AgentContainer getAgentContainer() {
		return agentContainer;
	}

	public static void main(String[] args) throws StaleProxyException {
		ConfigureClientFrame configureClientFrame = new ConfigureClientFrame();
		configureClientFrame
				.setOnConfigurationChangedListener(new ConfigureClientFrame.OnConfigurationChangedListener() {

					@Override
					public void onConfigurationChanged(String address, int port) {
						// Get a hold on JADE runtime
						jade.core.Runtime rt = jade.core.Runtime.instance();

						// Exit the JVM when there are no more containers around
						rt.setCloseVM(true);
						System.out.print("runtime created\n");

						// now set the default Profile to start a container
						ProfileImpl pContainer = new ProfileImpl(address, port, null);
						System.out.println("Launching the agent container ..." + pContainer);

						agentContainer = rt.createAgentContainer(pContainer);
						System.out.println("Launching the agent container after ..." + pContainer);

						System.out.println("containers created");

						try {
							agentContainer.createNewAgent("disk-client", "com.blogspot.sontx.jade.agentsystem.client.agent.DriveInformationsAgent", new Object[] {}).start();
							agentContainer.createNewAgent("logout-client", "com.blogspot.sontx.jade.agentsystem.client.agent.LogoutPCAgent", new Object[] {}).start();
							agentContainer.createNewAgent("shutdown-client","com.blogspot.sontx.jade.agentsystem.client.agent.ShutdownPCAgent", new Object[] {}).start();
							agentContainer.createNewAgent("restart-client","com.blogspot.sontx.jade.agentsystem.client.agent.RestartPCAgent", new Object[] {}).start();
						} catch (StaleProxyException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
		configureClientFrame.setVisible(true);
	}

}
