package com.blogspot.sontx.jade.agentsystem.client.utils;

import java.io.IOException;

public final class SystemManager {
	private SystemManager() {
	}

	public void shutdown() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Runtime runtime = Runtime.getRuntime();
				Process process = null;
				try {
					process = runtime.exec("shutdown -s -t 30");
					System.exit(0);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					process.destroy();
				}
			}
		}).start();
	}
}
