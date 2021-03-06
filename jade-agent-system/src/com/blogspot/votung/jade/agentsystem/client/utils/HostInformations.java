package com.blogspot.votung.jade.agentsystem.client.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class HostInformations {

	public static String getOSVersion() {
		return System.getProperty("os.version");
	}

	public static String getOSArchitecture() {
		return System.getProperty("os.arch");
	}

	public static String getOSName() {
		return System.getProperty("os.name");
	}

	public static String getHostname() {
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getIp() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}

}
