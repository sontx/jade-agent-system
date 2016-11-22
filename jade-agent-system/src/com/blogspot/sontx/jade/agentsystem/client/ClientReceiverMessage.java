package com.blogspot.sontx.jade.agentsystem.client;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.*;

public class ClientReceiverMessage extends Agent {
	protected void setup() {
		// create behaviour for receive and send message to Sender
		addBehaviour(new CyclicBehaviour(this) {
			public void action() {

				ACLMessage msg = receive(); // receive sent message
				if (msg != null) {
					System.out.println(
							" Message to " + myAgent.getLocalName() + " received. Message is : " + msg.getContent());
				}
				block();
			}
		});
	}
}
