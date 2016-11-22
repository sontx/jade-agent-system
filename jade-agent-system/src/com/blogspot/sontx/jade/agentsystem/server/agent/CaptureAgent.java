package com.blogspot.sontx.jade.agentsystem.server.agent;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class CaptureAgent extends Agent {
	@Override
	protected void setup() {
		System.out.println("CaptureAgent ready.");
		addBehaviour(new CaptureScreen());
	}
	
	private class CaptureScreen extends CyclicBehaviour {

		@Override
		public void action() {
			ACLMessage msg = myAgent.receive();
			System.out.println("capture-server received message...");
			if (msg != null) {
				if (msg.getPerformative() == ACLMessage.INFORM) {
					ACLMessage msg1 = new ACLMessage(ACLMessage.REQUEST);
					msg1.addReceiver(new AID("capture-client", AID.ISLOCALNAME));
					send(msg1);
					System.out.println("capture-server sent request");
				} else {
					String encoded = msg.getContent();
					showImage(encoded);
				}
			} else {
				block();
			}
		}

		private void showImage(String encoded) {
			byte[] decoded = DatatypeConverter.parseBase64Binary(encoded);
			
			String filePath = System.getProperty("imgdir") + "/" + System.currentTimeMillis() + ".png";
			saveImage(decoded, filePath);
		}

		private void saveImage(byte[] decoded, String filePath) {
			FileOutputStream fileOuputStream;
			try {
				fileOuputStream = new FileOutputStream(filePath);
			    fileOuputStream.write(decoded);
			    fileOuputStream.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
