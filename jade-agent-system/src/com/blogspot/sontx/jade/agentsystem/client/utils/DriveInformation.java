package com.blogspot.sontx.jade.agentsystem.client;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

public class DriveInformation {
	private String letter;
	private String typeDescription;
	
	public DriveInformation(String letter, String typeDescription) {
		this.letter = letter;
		this.typeDescription = typeDescription;
	}

	public static List<DriveInformation> getAll() {
		List<DriveInformation> list = new ArrayList<DriveInformation>();
		File[] paths;
		FileSystemView fsv = FileSystemView.getFileSystemView();

		paths = File.listRoots();

		for (File path : paths) {
			String letter = path.toString().substring(0, 1);
			String typeDescription = fsv.getSystemTypeDescription(path);
			DriveInformation driveInformation = new DriveInformation(letter, typeDescription);
			list.add(driveInformation);
		}
		
		return list;
	}

	public String getLetter() {
		return letter;
	}

	public String getTypeDescription() {
		return typeDescription;
	}

}
