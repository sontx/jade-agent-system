package com.blogspot.votung.jade.agentsystem.client.agent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.bind.DatatypeConverter;

import com.blogspot.votung.jade.agentsystem.client.utils.ScreenCapture;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ScreenCaptureAgent extends MobileAgent {
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
				if (isNormalMessage(msg)) {
					ACLMessage msg1 = msg.createReply();
					String base64Img = toBase64(filePath);
					msg1.setContent(base64Img);
					send(msg1);
					System.out.println("disk-client sent response");
				}
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
