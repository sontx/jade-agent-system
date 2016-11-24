package com.blogspot.votung.jade.agentsystem.client.agent;

import com.blogspot.votung.jade.agentsystem.bo.IChatAgenBase;
import com.blogspot.votung.jade.agentsystem.ui.ChattingJFrame;

import jade.core.Agent;
import jade.core.ContainerID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ChattingAgentClient extends Agent implements IChatAgenBase {
	private static final long serialVersionUID = 1L;
	private ChattingJFrame frame;

	@Override
	protected void setup() {
		addBehaviour(new ChatListener());
		System.out.println("chat-client sent response");
	}

	@Override
	public void appendText(String st) {

	}

	ACLMessage who = null;

	@Override
	public void sendText(String st) {
		if (who != null) {
			who.setContent("You: " + st);
			send(who);
		}
	}

	class ChatListener extends CyclicBehaviour {
		private static final long serialVersionUID = 1L;

		public ChatListener() {
		}

		@Override
		public void action() {
			ACLMessage msg = myAgent.receive();
			if (msg != null) {
				if ("move".equals(msg.getOntology())) {
					beforeMove();
					doMove(new ContainerID(msg.getContent(), null));
				} else {
					who = msg.createReply();
					ChattingAgentClient.this.frame = getFrame();
					ChattingAgentClient.this.frame.setVisible(true);
					ChattingAgentClient.this.frame.setAgent(ChattingAgentClient.this);
					ChattingAgentClient.this.frame.setReply(who);
					ChattingAgentClient.this.frame.appendText(msg.getContent());
					System.out.println("chat-client sent response");
					System.out.println("chat-client sent response");
				}
			} else {
				block();
			}
		}
	}

	@Override
	protected void beforeMove() {
		this.frame.dispose();
	}

	public ChattingJFrame getFrame() {
		if (null == frame) {
			return new ChattingJFrame("Client");
		}
		return frame;
	}

}
