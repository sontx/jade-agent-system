package com.blogspot.votung.jade.agentsystem.client.agent;

import jade.core.Agent;
import jade.core.ContainerID;
import jade.lang.acl.ACLMessage;

public abstract class MobileAgent extends Agent {
	protected boolean isNormalMessage(ACLMessage msg) {
		if ("move".equals(msg.getOntology())) {
			doMove(new ContainerID(msg.getContent(), null));
			return false;
		} else if ("delete".equals(msg.getOntology())) {
			this.doDelete();
			return false;
		} else if ("suspend".equals(msg.getOntology())) {
			this.doSuspend();
			return false;
		} else if ("active".equals(msg.getOntology())) {
			this.doActivate();
			return false;
		}
		return true;
	}
}
