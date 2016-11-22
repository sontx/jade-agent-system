package com.blogspot.sontx.jade.agentsystem.client.agent;

import java.util.List;

import com.blogspot.sontx.jade.agentsystem.client.utils.DriveInformation;

import jade.core.Agent;

public class DriveInformationsAgent extends Agent {
	private List<DriveInformation> listInformation = null;
	
	@Override
	protected void setup() {
		System.out.println(String.format("Hello! DriveInformations-agent %s is ready.", getAID().getName()));
		
		listInformation = DriveInformation.getAll();
		
		doDelete();
	}

	public List<DriveInformation> getListInformation() {
		return listInformation;
	}
	
}
