package com.smud.web.model.command;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.smud.web.model.Color;

public class Response {

	private String text;
	
	private Color color;
	
	private List<String> arguments;

	public Response(String text, Color color) {
		this.text = text;
		this.color = color;
	}
	
	public Response(String text, Color color, List<String> arguments) {
		this.text = text;
		this.color = color;
		this.arguments = arguments;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public List<String> getArguments() {
		return arguments;
	}

	public void setArguments(List<String> arguments) {
		this.arguments = arguments;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.append("text", text)
		.append("color", color)
		.append("arguments", arguments)
		.build();
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean isEquals = false;
		if (obj instanceof Response) {
			Response other = (Response) obj;
			isEquals = new EqualsBuilder()
			.append(text, other.text)
			.append(color, other.color)
			.append(arguments, other.arguments)
			.isEquals();
		}
		return isEquals;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
		.append(text)
		.append(color)
		.append(arguments)
		.toHashCode();
	}
}
