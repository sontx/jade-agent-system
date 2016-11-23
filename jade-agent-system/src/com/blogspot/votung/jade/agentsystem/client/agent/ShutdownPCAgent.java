package com.blogspot.votung.jade.agentsystem.client.agent;

import com.blogspot.votung.jade.agentsystem.client.utils.SystemManager;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class ShutdownPCAgent extends Agent {
	@Override
	protected void setup() {
		System.out.println(String.format("Hello! ShutdownPCAgent %s is ready.", getAID().getName()));
		addBehaviour(new ShutdownPC());
	}
	
	private class ShutdownPC extends CyclicBehaviour{

		@Override
		public void action() {
			ACLMessage msg = myAgent.receive();
			if(msg!= null){
				SystemManager.shutdown();
			}else{
				block();
			}
		}
		
	}
}
