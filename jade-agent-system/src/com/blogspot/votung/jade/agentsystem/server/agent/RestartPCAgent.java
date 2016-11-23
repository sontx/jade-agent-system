package com.blogspot.votung.jade.agentsystem.server.agent;

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
			if (msg != null) {
				if (msg.getPerformative() == ACLMessage.INFORM) {
					ACLMessage msg1 = new ACLMessage(ACLMessage.REQUEST);
					msg1.addReceiver(new AID("restart-client", AID.ISLOCALNAME));
					send(msg1);
					System.out.println("restart-server sent request");
				} else {
				}
			} else {
				block();
			}	
		}
	}
}
