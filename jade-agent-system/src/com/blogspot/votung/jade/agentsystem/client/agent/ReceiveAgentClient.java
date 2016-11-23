package com.blogspot.votung.jade.agentsystem.client.agent;

import javax.swing.JOptionPane;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ReceiveAgentClient extends Agent {
	@Override
	protected void setup() {
		addBehaviour(new ChatListener());
		System.out.println("receiver message");
	}

	class ChatListener extends CyclicBehaviour {

		@Override
		public void action() {
			ACLMessage msg = myAgent.receive();
			System.out.println("fuck yeahhh");
			if (msg != null) {
				JOptionPane.showMessageDialog(null, msg.getContent());
			} else {
				block();
			}
		}

	}
}
