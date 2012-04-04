package com.smud.service;

import com.smud.model.User;
import com.smud.model.character.CharacterClass;
import com.smud.model.character.Player;

public interface PlayerService {

	Player findPlayer(String playerName);
	
	Player createPlayerForUser(User user, CharacterClass characterClass);
}
