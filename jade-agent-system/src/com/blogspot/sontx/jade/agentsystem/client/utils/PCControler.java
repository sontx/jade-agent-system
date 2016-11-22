package com.blogspot.sontx.jade.agentsystem.client;

import java.io.IOException;

public class PCControler {
	public static void shutdown() {
		shutdown(0);
	}

	public static void shutdown(int seconds) {
		Runtime runtime = Runtime.getRuntime();
	    try {
	    	String cmd = String.format("shutdown -s -t %d", seconds);
			Process proc = runtime.exec(cmd);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    System.exit(0);
	}
}
