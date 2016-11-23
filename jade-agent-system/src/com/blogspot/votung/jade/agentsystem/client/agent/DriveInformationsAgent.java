package com.blogspot.votung.jade.agentsystem.client.agent;

import java.util.List;

import com.blogspot.votung.jade.agentsystem.client.utils.DriveInformation;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class DriveInformationsAgent extends Agent {
	private List<DriveInformation> listInformation = null;

	@Override
	protected void setup() {
		System.out.println(String.format("Hello! DriveInformations-agent %s is ready.", getAID().getName()));
		addBehaviour(new ResponseDiskInfo());
		System.out.println("disk-client is running...");
	}

	public List<DriveInformation> getListInformation() {
		return listInformation;
	}

	private class ResponseDiskInfo extends CyclicBehaviour {
		private static final long serialVersionUID = 1L;

		@Override
		public void action() {
			ACLMessage msg = myAgent.receive();
			System.out.println("disk-client recieved message");
			if (msg != null) {
				ACLMessage msg1 = msg.createReply();
				listInformation = DriveInformation.getAll();
				StringBuilder builder = new StringBuilder();
				for (DriveInformation driveInformation : listInformation) {
					builder.append(driveInformation.toString());
					builder.append("\n");
				}

				msg1.setContent(builder.toString());
				send(msg1);
				System.out.println("disk-client sent response");
			} else {
				block();
			}
		}
	}
}
