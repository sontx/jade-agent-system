package com.blogspot.votung.jade.agentsystem.client.agent;

import com.blogspot.votung.jade.agentsystem.bo.IChatAgenBase;
import com.blogspot.votung.jade.agentsystem.ui.ChattingJFrame;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ChattingAgentClient extends Agent implements IChatAgenBase {
	private static final long serialVersionUID = 1L;
	private ChattingJFrame frame;

	public ChattingAgentClient() {
		frame = new ChattingJFrame(this);
	}

	@Override
	protected void setup() {
		addBehaviour(new ChatListener());
		System.out.println("chat-client sent response");
	}

	@Override
	public void appendText(String st) {
		frame.setVisible(true);
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

	class ChatListener extends CyclicBehaviour {
		private static final long serialVersionUID = 1L;

		public ChatListener() {
		}

		@Override
		public void action() {
			ACLMessage msg = myAgent.receive();
			if (msg != null) {
				who = msg.createReply();
				appendText(msg.getContent());
				System.out.println("chat-client sent response");
			} else {
				block();
			}
		}
	}

}
