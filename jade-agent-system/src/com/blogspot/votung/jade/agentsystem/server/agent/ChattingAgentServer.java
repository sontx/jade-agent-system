package com.blogspot.votung.jade.agentsystem.server.agent;

import com.blogspot.votung.jade.agentsystem.bo.IChatAgenBase;
import com.blogspot.votung.jade.agentsystem.ui.ChattingJFrame;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ChattingAgentServer extends Agent implements IChatAgenBase {
	private static final long serialVersionUID = 1L;
	private ChattingJFrame frame;

	public ChattingAgentServer() {
		frame = new ChattingJFrame(this);
	}

	@Override
	protected void setup() {
		addBehaviour(new ChattingRequest());
		System.out.println("chat-server is running...");
	}

	@Override
	public void appendText(String st) {
		frame.setTextDislay(st);
	}

	ACLMessage who = null;

	@Override
	public void sendText(String st) {
		if (who != null) {
			who.setContent(st);
			send(who);
		}
	}

	private class ChattingRequest extends CyclicBehaviour {
		private static final long serialVersionUID = 1L;

		@Override
		public void action() {
			ACLMessage msg = myAgent.receive();
			System.out.println("chat-server received message...");
			if (msg != null) {
				if (msg.getPerformative() == ACLMessage.INFORM) {
					who = new ACLMessage(ACLMessage.REQUEST);
					who.addReceiver(new AID("chat-client", AID.ISLOCALNAME));
					frame.setVisible(true);
					System.out.println("chat-server sent request");
				} else {
					appendText(msg.getContent());
				}
			} else {
				block();
			}
		}
	}
}
