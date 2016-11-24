package com.blogspot.votung.jade.agentsystem.client.utils;

public class IPAndHostname {
	private String ip;
	private String hostname;
	
	public IPAndHostname(String ip, String hostname) {
		this.ip = ip;
		this.hostname = hostname;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

}
