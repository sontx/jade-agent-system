package com.blogspot.sontx.jade.agentsystem.client.utils;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author trongvn13
 *
 * Nov 22, 2016 - 4:03:52 PM
 */
public class ScreenCapture {
	public static void capture(String filePath) {
		capture(filePath, "png");
	}
	
	public static void capture(String filePath, String fileType) {
		Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		BufferedImage capture;
		try {
			capture = new Robot().createScreenCapture(screenRect);
			File file = new File(filePath);
			ImageIO.write(capture, fileType, file);
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
