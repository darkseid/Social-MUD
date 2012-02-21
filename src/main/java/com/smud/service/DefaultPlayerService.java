package com.smud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smud.model.Player;

@Service
public class DefaultPlayerService implements PlayerService {

	@Autowired
	private Player mockPlayer;
	
	@Override
	public Player findPlayer(String playerName) {
		if (playerName.equals(mockPlayer.getName())) {
			return mockPlayer;
		} 
		return null;
	}
	
}
