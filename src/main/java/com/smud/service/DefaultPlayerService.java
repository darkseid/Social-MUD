package com.smud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smud.model.User;
import com.smud.model.character.Player;
import com.smud.service.data.RedisRepository;



@Service
public class DefaultPlayerService implements PlayerService {
	
	@Autowired
	private RedisRepository repository;
	
	@Override
	public Player findPlayer(String playerName) {
		
		
		User user = repository.findUser(playerName);
		Player player = new Player();
		player.setId((int)user.getId());
		player.setName(user.getName());
		
		return player;
	}
	
}
