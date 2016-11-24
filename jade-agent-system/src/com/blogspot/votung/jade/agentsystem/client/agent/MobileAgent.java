package com.blogspot.votung.jade.agentsystem.client.agent;

import jade.core.Agent;
import jade.core.ContainerID;
import jade.lang.acl.ACLMessage;

public abstract class MobileAgent extends Agent {
	protected boolean isMoved(ACLMessage msg) {
		if ("move".equals(msg.getOntology())) {
			doMove(new ContainerID(msg.getContent(), null));
			return true;
		}
		return false;
	}
}
