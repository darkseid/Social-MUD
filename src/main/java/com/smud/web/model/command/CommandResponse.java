package com.smud.web.model.command;

import java.util.ArrayList;
import java.util.List;

public class CommandResponse {

	private List<Response> responses = new ArrayList<Response>();
	
	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}
	
	public List<Response> getResponses() {
		return responses;
	}
	
	public void addResponse(Response response) {
		this.responses.add(response);
	}

	@Override
	public String toString() {
		return "CommandResponse [responses=" + responses + "]";
	}
}
