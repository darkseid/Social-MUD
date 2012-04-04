package com.smud.model;

import com.smud.model.character.Player;

public class User {

	private long id;
	private String name;
	private String password;
	private Player player;
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	
	public User(Long uid, String name, String password) {
		this(name, password);
		setId(uid);
	}
	
	public void setId(long id) {
		this.id = id;
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
