package com.blogspot.sontx.jade.agentsystem.client.agent;

import com.blogspot.sontx.jade.agentsystem.client.utils.SystemManager;

import jade.core.Agent;

public class LogoutPCAgent extends Agent {
	@Override
	protected void setup() {
		System.out.println(String.format("Hello! LogoutPCAgent %s is ready.", getAID().getName()));
		SystemManager.logout();
		doDelete();
	}
}
