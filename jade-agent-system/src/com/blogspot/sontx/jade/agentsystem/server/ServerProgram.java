package com.blogspot.sontx.jade.agentsystem.server;

import com.blogspot.sontx.jade.agentsystem.server.ui.ConfigureServerFrame;

import jade.wrapper.StaleProxyException;

public class ServerProgram {
	public static void main(String[] args) throws StaleProxyException {
		ConfigureServerFrame configureServerFrame = new ConfigureServerFrame();
		configureServerFrame.setOnConfigurationChangedListener(new ConfigureServerFrame.OnConfigurationChangedListener() {
			
			@Override
			public void onConfigurationChanged(String savingImagesDirectory, int port) {
				jade.Boot.main(new String[] 
						{
							"-port", "" + port, 
							"-agents", String.format("sontx:com.blogspot.sontx.jade.agentsystem.server.agent.ServerAgent(%s)", savingImagesDirectory) 
						});
			}
		});
		configureServerFrame.setVisible(true);
	}
}
