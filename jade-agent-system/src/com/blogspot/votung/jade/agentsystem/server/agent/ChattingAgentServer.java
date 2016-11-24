package com.blogspot.votung.jade.agentsystem.server.agent;

import com.blogspot.votung.jade.agentsystem.ui.ChattingJFrame;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ChattingAgentServer extends Agent {
	private static final long serialVersionUID = 1L;
	private ChattingJFrame frame = null;

	public ChattingAgentServer() {
	}

	@Override
	protected void setup() {
		addBehaviour(new ChattingRequest());
		System.out.println("chat-server is running...");
	}

	ACLMessage who = null;

	private class ChattingRequest extends CyclicBehaviour {
		private static final long serialVersionUID = 1L;

		@Override
		public void action() {
			ACLMessage msg = myAgent.receive();
			System.out.println("chat-server received message...");
			if (msg != null) {
				if (msg.getPerformative() == ACLMessage.INFORM) {
					ChattingAgentServer.this.frame = getFrame();
					who = new ACLMessage(ACLMessage.REQUEST);
					who.addReceiver(new AID("chat-client", AID.ISLOCALNAME));
					ChattingAgentServer.this.frame.setVisible(true);
					ChattingAgentServer.this.frame.setAgent(ChattingAgentServer.this);
					ChattingAgentServer.this.frame.setReply(who);
					System.out.println("chat-server sent request");
				} else {
					ChattingAgentServer.this.frame.appendText(msg.getContent());
				}
			} else {
				block();
			}
		}
	}

	public ChattingJFrame getFrame() {
		if (this.frame == null) {
			return new ChattingJFrame("Server");
		}
		return this.frame;
	}
}
