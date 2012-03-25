package com.smud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smud.model.character.Player;

@Service
public class DefaultPlayerService implements PlayerService {

	@Autowired
	private Player mockPlayer;
	
	@Autowired
	private Player mockPlayer2;
	
	@Override
	public Player findPlayer(String playerName) {
		if (playerName.equals(mockPlayer.getName())) {
			return mockPlayer;
		} else if (playerName.equals(mockPlayer2.getName())) {
			return mockPlayer2;
		} 
		return null;
	}
	
}
