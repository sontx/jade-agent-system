package com.blogspot.votung.jade.agentsystem.client.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

public class DriveInformation {
	private String letter;
	private String typeDescription;
	private long capacityInGB;
	
	public DriveInformation(String letter, String typeDescription, long capacityInGB) {
		this.letter = letter;
		this.typeDescription = typeDescription;
		this.capacityInGB = capacityInGB;
	}
	
	public static List<DriveInformation> getAll() {
		List<DriveInformation> list = new ArrayList<DriveInformation>();
		File[] paths;
		FileSystemView fsv = FileSystemView.getFileSystemView();

		paths = File.listRoots();

		for (File path : paths) {
			String letter = path.toString().substring(0, 1);
			String typeDescription = fsv.getSystemTypeDescription(path);
			long capacityInGB = getCapacity(letter + ":/");
			DriveInformation driveInformation = new DriveInformation(letter, typeDescription, capacityInGB);
			list.add(driveInformation);
		}
		
		return list;
	}
	
	private static long getCapacity(String path) {
		File file = new File(path);
		long totalSpace = file.getTotalSpace();
		return totalSpace / 1024 / 1024 / 1024;
	}

	public String getLetter() {
		return letter;
	}

	public String getTypeDescription() {
		return typeDescription;
	}
	
	public long getCapacityInGB() {
		return capacityInGB;
	}

	@Override
	public String toString() {
		return letter + ": " + capacityInGB + "BG - " + typeDescription;
	}

}
