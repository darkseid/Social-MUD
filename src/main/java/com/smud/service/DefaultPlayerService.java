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
import com.smud.util.StringUtils;

@Service
public class DefaultPlayerService implements PlayerService {
	
	private static final int INITIAL_HIT_POINTS = 10;
	private static final int INITIAL_MANA_POINTS = 100;
	private static final int INITIAL_MOVEMENT_POINTS = 82;

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
		player.setTitle("the " + StringUtils.capitalizeFirstLetter(characterClass.name()));
		player.setName(StringUtils.capitalizeFirstLetter(user.getName()));
		player.setPlayerClass(characterClass);
		attributeGenerator.generateAttributes(player);
		player.setMaxHitPoints(INITIAL_HIT_POINTS);
		player.setMaxManaPoints(INITIAL_MANA_POINTS);
		player.setMaxMovementPoints(INITIAL_MOVEMENT_POINTS);
		player.setHitPoints(INITIAL_HIT_POINTS);
		player.setManaPoints(INITIAL_MANA_POINTS);
		player.setMovementPoints(INITIAL_MOVEMENT_POINTS);
		return player;
	}
	
}
