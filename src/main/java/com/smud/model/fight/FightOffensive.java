package com.smud.model.fight;

import com.smud.model.character.Character;

public class FightOffensive {

	private Character offender;
	
	private Character victim;

	public FightOffensive(Character offender, Character victim) {
		this.offender = offender;
		this.victim = victim;
	}

	public Character getOffender() {
		return offender;
	}

	public void setOffender(Character offender) {
		this.offender = offender;
	}

	public Character getVictim() {
		return victim;
	}

	public void setVictim(Character victim) {
		this.victim = victim;
	}
	
	
	
}
