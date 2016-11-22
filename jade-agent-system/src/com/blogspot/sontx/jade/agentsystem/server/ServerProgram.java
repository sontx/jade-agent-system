package com.blogspot.sontx.jade.agentsystem.server;

import jade.wrapper.StaleProxyException;

public class ServerProgram {
	public static void main(String[] args) throws StaleProxyException {
		jade.Boot.main(new String[] { "-agents",
				"sontx:com.blogspot.sontx.jade.agentsystem.server.agent.ServerAgent(somewhere)" });
	}
}
