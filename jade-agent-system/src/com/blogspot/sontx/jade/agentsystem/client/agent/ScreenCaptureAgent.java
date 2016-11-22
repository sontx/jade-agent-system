package com.blogspot.sontx.jade.agentsystem.client.agent;

import com.blogspot.sontx.jade.agentsystem.client.utils.ScreenCapture;

import jade.core.Agent;

public class ScreenCaptureAgent extends Agent {
	private String filePath;

	public String getFilePath() {
		return filePath;
	}

	@Override
	protected void setup() {
		System.out.println(String.format("Hello! ScreenCaptureAgent %s is ready.", getAID().getName()));
		
		Object[] args = getArguments();
		if (args != null && args.length > 0) {
			filePath = (String) args[0];
			System.out.println("Trying to take screen capture.");
			ScreenCapture.capture(filePath);
		} else {
			System.out.println("No filePath.");
			doDelete();
		}
	}
}
