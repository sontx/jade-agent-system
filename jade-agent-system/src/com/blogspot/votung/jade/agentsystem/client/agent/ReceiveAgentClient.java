package com.blogspot.votung.jade.agentsystem.client.agent;

import javax.swing.JOptionPane;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ReceiveAgentClient extends MobileAgent {
	private static final long serialVersionUID = 1L;
	@Override
	protected void setup() {
		addBehaviour(new ChatListener());
		System.out.println("receiver message");
	}

	class ChatListener extends CyclicBehaviour {

		@Override
		public void action() {
			ACLMessage msg = myAgent.receive();
			if (msg != null) {
				if (!isMoved(msg)) {
					JOptionPane.showMessageDialog(null, msg.getContent());
				}
			} else {
				block();
			}
		}

	}
}
