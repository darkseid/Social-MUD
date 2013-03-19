package com.smud.service;

import com.smud.model.character.Character;

public interface FightService {

	void startFight(Character offender, Character victim);
	
	void executeRound();
}
