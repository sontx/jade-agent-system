package com.blogspot.votung.jade.agentsystem.client.agent;

import com.blogspot.votung.jade.agentsystem.client.utils.SystemManager;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class LogoutPCAgent extends MobileAgent {
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
				if (!isMoved(msg)) {
					SystemManager.logout();
				}
			} else {
				block();
			}
		}
		
	}
}
