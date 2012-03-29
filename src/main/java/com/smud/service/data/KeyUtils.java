package com.smud.service.data;

import org.apache.commons.lang.StringUtils;


public enum KeyUtils {

	
	PLAYER("player"),
	USER("user");

	private static final String SEPARATOR = ":";
	
	private String namespace;
	
	private KeyUtils(String namespace) {
		this.namespace = namespace;
	}
	
	public String getKeyFor(long id) {
		String s = String.valueOf(id);
		return this.getKeyFor(s);
	}
	
	public String getKeyFor(String string) {
		if ( StringUtils.isBlank(string) ) {
			throw new IllegalArgumentException("you must pass a valid value");
		}
		
		String key = String.format("%s:%s", this.namespace, string);
		return key;
	}
	
}
