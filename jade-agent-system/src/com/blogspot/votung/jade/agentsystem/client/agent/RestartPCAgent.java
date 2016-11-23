package com.blogspot.votung.jade.agentsystem.client.agent;

import com.blogspot.votung.jade.agentsystem.client.utils.SystemManager;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class RestartPCAgent extends Agent {
	@Override
	protected void setup() {
		addBehaviour(new RestartPC());
	}
	
	private class RestartPC extends CyclicBehaviour{
		@Override
		public void action() {
			ACLMessage msg = myAgent.receive();
			if(msg!= null){
				SystemManager.restart();;
			}else{
				block();
			}
		}
	}
}
