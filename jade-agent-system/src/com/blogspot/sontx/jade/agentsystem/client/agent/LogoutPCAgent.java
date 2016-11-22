package com.blogspot.sontx.jade.agentsystem.client.agent;

import com.blogspot.sontx.jade.agentsystem.client.utils.SystemManager;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class LogoutPCAgent extends Agent {
	@Override
	protected void setup() {
		System.out.println(String.format("Hello! LogoutPCAgent %s is ready.", getAID().getName()));
		addBehaviour(new LogoutPC());
	}
	
	private class LogoutPC extends CyclicBehaviour {

		@Override
		public void action() {
			ACLMessage msg = myAgent.receive();
			System.out.println("logout-client recieved message");
			if (msg != null) {
				SystemManager.logout();
			} else {
				block();
			}
		}
		
	}
}
