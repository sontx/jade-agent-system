package com.blogspot.votung.jade.agentsystem.server.agent;

import com.blogspot.votung.jade.agentsystem.server.ui.ServerMonitorFrame;

import jade.content.lang.Codec;
import jade.content.lang.Codec.CodecException;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.AMSService;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.JADEAgentManagement.JADEManagementOntology;
import jade.domain.JADEAgentManagement.ShutdownPlatform;
import jade.domain.mobility.MobilityOntology;
import jade.lang.acl.ACLMessage;

public class ServerAgent extends Agent {
	private static final long serialVersionUID = 1L;
	private String savingImagseDirectory = null;
	ServerMonitorFrame serverMonitorFrame;

	public ServerAgent() {
	}

	@Override
	protected void setup() {
		Object[] arguments = getArguments();
		if (arguments != null && arguments.length > 0)
			savingImagseDirectory = arguments[0].toString();
		this.serverMonitorFrame = new ServerMonitorFrame(this);
		this.serverMonitorFrame.setVisible(true);
		getAllAgents();
	}

	private void getAllAgents() {
		AMSAgentDescription[] agents = null;
		try {
			SearchConstraints c = new SearchConstraints();
			c.setMaxResults(new Long(-1));
			agents = AMSService.search(this, new AMSAgentDescription(), c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		serverMonitorFrame.loadAgentsList(agents);
	}

	public void shutdownJade() throws CodecException, OntologyException {
		Codec codec = new SLCodec();
		Ontology jmo = JADEManagementOntology.getInstance();
		getContentManager().registerLanguage(codec);
		getContentManager().registerOntology(jmo);
		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
		msg.addReceiver(getAMS());
		msg.setLanguage(codec.getName());
		msg.setOntology(jmo.getName());
		try {
			getContentManager().fillContent(msg, new Action(getAID(), new ShutdownPlatform()));
			send(msg);
		} catch (Exception e) {
		}
	}

	public void updateMoveList() {
		// register the SL0 content language
		getContentManager().registerLanguage(new SLCodec(), FIPANames.ContentLanguage.FIPA_SL0);
		// register the mobility ontology
		getContentManager().registerOntology(MobilityOntology.getInstance());
		addBehaviour(new GetAvailableLocationsBehaviour(this));
	}

	@Override
	protected void takeDown() {
		serverMonitorFrame.dispose();
	}

	private void sendInternalRequset(String who) {
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		msg.addReceiver(new AID(who, AID.ISLOCALNAME));
		msg.setLanguage("vietnamese");
		msg.setOntology("well-well-well");
		msg.setContent("do it for me");
		send(msg);
	}

	public void showDisk() {
		sendInternalRequset("disk-server");
	}


	public void chat() {
		sendInternalRequset("chat-server");
	}
	
	public void orderedLogout() {
		sendInternalRequset("logout-server");
	}

	public void orderedShutdown() {
		sendInternalRequset("shutdown-server");
	}

	public void orderedRestart() {
		sendInternalRequset("restart-server");
	}

	public void refreshAgentsList() {
		getAllAgents();
	}

	public void sendMessage() {
		sendInternalRequset("send-message-server");
	}
	
	public void captureScreen() {
		sendInternalRequset("capture-server");
	}
}
