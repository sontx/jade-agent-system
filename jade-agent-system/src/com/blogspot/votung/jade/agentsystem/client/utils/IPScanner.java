package com.blogspot.votung.jade.agentsystem.client.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class IPScanner {
	private List<IPAndHostname> listIPs = new ArrayList<IPAndHostname>();

	private synchronized void add(String ip, String hostname) {
		listIPs.add(new IPAndHostname(ip, hostname));
	}
	
	public List<IPAndHostname> getListIps() {
		Thread mainThread = new Thread(new Runnable() {
			@Override
			public void run() {
				checkHosts();
			}
		});
		mainThread.start();
		try {
			mainThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return listIPs;
	}
	
	public static void main(String[] args) {
		System.out.println(new IPScanner().getListIps().toString());
	}
	
	private void checkHosts() {
		String myIp = getMyIP();
		String subnet = myIp.substring(0, myIp.lastIndexOf("."));
		List<Thread> listThread = new ArrayList<Thread>();
		for (int i = 2; i < 255; i++) {
			final String host = subnet + "." + i;
			if (!myIp.equals(host)) {
				listThread.add(new Thread(new Runnable() {
					@Override
					public void run() {
						checkIP(host);
					}
				}));
			}
		}
		
		for (Thread thread : listThread) {
			thread.start();
		}
		
		for (Thread thread : listThread) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void checkIP(String ip) {
		try {
			if (InetAddress.getByName(ip).isReachable(2000)) {
				InetAddress addr = InetAddress.getByName(ip);
				String hostname = addr.getHostName();
				add(ip, hostname);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String getMyIP() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
