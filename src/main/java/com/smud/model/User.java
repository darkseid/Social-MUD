package com.smud.model;

public class User {

	private long id;
	private String name;
	private String password;
	private Player player;
	
	public User(long id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		
		//TODO remove it
		player = new Player();
	}
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
	public Player getPlayer() {
		return player;
	}
	
}
