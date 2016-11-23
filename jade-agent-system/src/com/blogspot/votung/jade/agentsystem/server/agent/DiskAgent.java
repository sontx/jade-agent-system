package com.blogspot.votung.jade.agentsystem.server.agent;

import com.blogspot.votung.jade.agentsystem.server.ui.DiskInfoJFrame;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class DiskAgent extends Agent {
	private static final long serialVersionUID = 1L;
	private DiskInfoJFrame disInfoFrame;

	@Override
	protected void setup() {
		disInfoFrame = new DiskInfoJFrame();
		addBehaviour(new RequestDiskInfo());
		System.out.println("disk-server is running...");
	}

	private class RequestDiskInfo extends CyclicBehaviour {
		private static final long serialVersionUID = 1L;

		@Override
		public void action() {
			ACLMessage msg = myAgent.receive();
			System.out.println("disk-server received message...");
			if (msg != null) {
				if (msg.getPerformative() == ACLMessage.INFORM) {
					ACLMessage msg1 = new ACLMessage(ACLMessage.REQUEST);
					msg1.addReceiver(new AID("disk-client", AID.ISLOCALNAME));
					msg1.setLanguage("vietnamese");
					msg1.setOntology("well-well-well");
					msg1.setContent("do it for me");
					send(msg1);
					System.out.println("disk-server sent request");
				} else {
					disInfoFrame.setDiskInfo(msg.getContent());
					disInfoFrame.setVisible(true);
				}
			} else {
				block();
			}

		}

	}
}
