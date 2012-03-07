package com.smud.web.model.command;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
	
	@Override
	public boolean equals(Object obj) {
		boolean isEquals = false;
		if (obj instanceof CommandResponse) {
			CommandResponse other = (CommandResponse) obj;
			isEquals = new EqualsBuilder()
			.append(responses, other.responses)
			.isEquals();
		}
		return isEquals;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
		.append(responses)
		.toHashCode();
	}
}
