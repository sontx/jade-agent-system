package com.blogspot.sontx.jade.agentsystem.bo;

public interface IChatAgenBase {
	void appendText(String s);
	
	void sendText(String s);
	
	String getName();
}
