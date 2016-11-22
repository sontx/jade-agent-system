package com.blogspot.sontx.jade.agentsystem.server;

import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.*;
import jade.lang.acl.*;

public class ServerSenderMessage extends Agent {
	protected void setup() {
		// Setup answering behavior
		addBehaviour(new CyclicBehaviour(this) {
			public void action() {
				ACLMessage msg = receive();
				if (msg != null)
					System.out.println("Message: " + msg.getContent() + " ( " + msg.getSender().getName() + " )");
				block();
			}
		});
		// Send message to agent "client"
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		msg.setContent(" Hi from Sender ");
		msg.addReceiver(new AID("client", AID.ISLOCALNAME));
		send(msg);
	}
}
