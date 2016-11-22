package com.blogspot.sontx.jade.agentsystem.server.agent;

import com.blogspot.sontx.jade.agentsystem.server.ui.SendMessageServerFrame;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class SendMessageAgent extends Agent {
	private static final long serialVersionUID = 1L;
	private SendMessageServerFrame sendForm;

	public SendMessageAgent() {
		sendForm = new SendMessageServerFrame(this);
	}

	@Override
	protected void setup() {
		addBehaviour(new SendMessage());
	}

	public void sendText(String s) {
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		msg.addReceiver(new AID("send-message-client", AID.ISLOCALNAME));
		msg.setLanguage("vietnamese");
		msg.setOntology("well-well-well");
		msg.setContent(s);
		send(msg);
	}

	private class SendMessage extends CyclicBehaviour {
		@Override
		public void action() {
			ACLMessage msg = myAgent.receive();
			if (msg != null) {
				if (msg.getPerformative() == ACLMessage.INFORM) {
					sendForm.setVisible(true);
					System.out.println("send-message-server sent request");
				} else {

				}
			} else {
				block();
			}
		}
	}
}
