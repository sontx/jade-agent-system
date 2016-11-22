package com.blogspot.sontx.jade.agentsystem.server.agent;

import com.blogspot.sontx.jade.agentsystem.server.ui.ServerMonitorFrame;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;

public class ServerAgent extends Agent {
	private static final long serialVersionUID = 1L;
	private String savingImagseDirectory = null;
	private ServerMonitorFrame serverMonitorFrame;

	public ServerAgent() {
	}

	@Override
	protected void setup() {
		Object[] arguments = getArguments();
		if (arguments != null && arguments.length > 0)
			savingImagseDirectory = arguments[0].toString();
		this.serverMonitorFrame = new ServerMonitorFrame();
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

	@Override
	protected void takeDown() {
		serverMonitorFrame.dispose();
	}
}
