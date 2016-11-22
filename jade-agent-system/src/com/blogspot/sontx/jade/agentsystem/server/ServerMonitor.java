package com.blogspot.sontx.jade.agentsystem.server;

public final class ServerMonitor {
	private static ServerMonitor instance = new ServerMonitor();
	private int serverPort;
	private String savingImageDirectory;

	public static ServerMonitor getInstance() {
		return instance;
	}

	private ServerMonitor() {
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public String getSavingImageDirectory() {
		return savingImageDirectory;
	}

	public void setSavingImageDirectory(String savingImageDirectory) {
		this.savingImageDirectory = savingImageDirectory;
	}

	public void notifyConfigureChanged() {
		
	}
}
