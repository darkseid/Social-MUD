package com.smud.web.model.command;

import java.util.ArrayList;
import java.util.List;

public class CommandResponse {

	private List<String> responses = new ArrayList<String>();
	
	public void setResponses(List<String> responses) {
		this.responses = responses;
	}
	
	public List<String> getResponses() {
		return responses;
	}
	
	public void addResponse(String response) {
		this.responses.add(response);
	}
}
