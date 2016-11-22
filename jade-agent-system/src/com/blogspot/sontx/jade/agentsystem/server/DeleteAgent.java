package com.blogspot.sontx.jade.agentsystem.server;

import jade.core.AID;
import jade.domain.JADEAgentManagement.KillAgent;

public class DeleteAgent {
	public void killAgent(AID name) {

	    KillAgent ka = new KillAgent();

	    ka.setAgent(name);

	  }
}
