package com.blogspot.sontx.jade.agentsystem.server.agent;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class ShutdownPCAgent extends Agent {
	@Override
	protected void setup() {
		addBehaviour(new ShutdownPC());
	}
	
	private class ShutdownPC extends OneShotBehaviour{
		@Override
		public void action() {
			ACLMessage msg = myAgent.receive();
			if (msg != null) {
				if (msg.getPerformative() == ACLMessage.INFORM) {
					ACLMessage msg1 = new ACLMessage(ACLMessage.REQUEST);
					msg1.addReceiver(new AID("shutdown-client", AID.ISLOCALNAME));
					send(msg1);
					System.out.println("shutdown-server sent request");
				} else {
				}
			} else {
				block();
			}	
		}
	}
}
