package com.blogspot.sontx.jade.agentsystem.client.utils;

import java.io.IOException;

public final class SystemManager {
	private SystemManager() {
	}

	public static void shutdown() {
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
	
	public static void logout(){
		try
		{
			 Runtime rt=Runtime.getRuntime();
			 Process pr1 = rt.exec("cmd /c shutdown -l"); // for log off
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void restart(){
		try
		{
			 Runtime rt=Runtime.getRuntime();
			 Process pr2 = rt.exec("cmd /c shutdown -r"); // for log off
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
