package com.blogspot.votung.jade.agentsystem.client.agent;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import com.blogspot.votung.jade.agentsystem.client.utils.DriveInformation;
import com.blogspot.votung.jade.agentsystem.client.utils.ScreenCapture;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ScreenCaptureAgent extends Agent {
	private String filePath = "C:/Users/Public/xxx.png";

	public String getFilePath() {
		return filePath;
	}

	@Override
	protected void setup() {
		System.out.println(String.format("ScreenCaptureAgent %s is ready.", getAID().getName()));
		addBehaviour(new CaptureScreen());
	}
	
	private class CaptureScreen extends CyclicBehaviour {

		@Override
		public void action() {
			ScreenCapture.capture(filePath);
			
			ACLMessage msg = myAgent.receive();
			System.out.println("capture-client recieved message");
			if (msg != null) {
				ACLMessage msg1 = msg.createReply();
				String base64Img = toBase64(filePath);
				msg1.setContent(base64Img);
				send(msg1);
				System.out.println("disk-client sent response");
			} else {
				block();
			}
		}

		private String toBase64(String filePath) {
			try {
				byte[] byteArray = extractBytes(filePath);
				String encoded = DatatypeConverter.printBase64Binary(byteArray);
				return encoded;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		private byte[] extractBytes(String filePath) throws IOException {
			Path path = Paths.get(filePath);
			byte[] data = Files.readAllBytes(path);
			return data;
		}
		
	}
}
