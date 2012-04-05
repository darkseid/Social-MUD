package com.smud.service;

import com.smud.model.User;
import com.smud.model.character.PlayerClass;
import com.smud.model.character.Player;

public interface PlayerService {

	Player findPlayer(String playerName);
	
	Player createPlayerForUser(User user, PlayerClass characterClass);
}
