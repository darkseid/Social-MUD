package com.smud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.smud.model.Room;
import com.smud.model.User;
import com.smud.model.character.AttributeGenerator;
import com.smud.model.character.PlayerClass;
import com.smud.model.character.Player;
import com.smud.service.data.PlayerRepository;
import com.smud.service.data.RedisRepository;

@Service
public class DefaultPlayerService implements PlayerService {
	
	@Autowired
	private RedisRepository repository;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	@Qualifier(value="room3000")
	// TODO remove this.
	private Room DEFAULT_ROOM;
	
	@Autowired
	private AttributeGenerator attributeGenerator;
	
	@Override
	public Player findPlayer(String playerName) {
		User user = repository.findUser(playerName);
		return user.getPlayer();
	}
	
	public Player createPlayerForUser(User user, PlayerClass characterClass) {
		Player player = createPlayer(user, characterClass);
		long playerId = playerRepository.addPlayer(user, player);
		player.setId(playerId);
		return player;
	}

	private Player createPlayer(User user, PlayerClass characterClass) {
		Player player = new Player();
		player.enters(DEFAULT_ROOM);
		player.setTitle("the " + characterClass.name().toLowerCase());
		player.setName(user.getName());
		player.setCharacterClass(characterClass);
		attributeGenerator.generateAttributes(player);
		return player;
	}
	
}
