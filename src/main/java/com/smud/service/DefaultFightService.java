package com.smud.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.smud.model.Color;
import com.smud.model.Room;
import com.smud.model.character.Character;
import com.smud.model.command.Response;
import com.smud.model.fight.FightOffensive;

@Service("fightService")
public class DefaultFightService implements FightService {

	private List<FightOffensive> fights = new ArrayList<FightOffensive>();
	
	private Set<Character> offenderCharacters = new HashSet<Character>();
	
	@Override
	public void startFight(Character offender, Character victim) {
		//TODO check for char position == STANDING
		FightOffensive fightOffensive = createFightOffensive(offender, victim);
		hit(fightOffensive);
	}
	
	@Override
	public void executeRound(){
		for (FightOffensive fightOffensive : fights) {
			hit(fightOffensive);
		}
	}
	
	private synchronized FightOffensive createFightOffensive(Character offender, Character victim) {
		if (offenderCharacters.contains(offender)) {
			return null;
		}
		
		FightOffensive fightOffensive = new FightOffensive(offender, victim);
		fights.add(fightOffensive);
		offenderCharacters.add(offender);
		return fightOffensive;
	}
	

	private synchronized void stopFight(FightOffensive fightOffensive) {
		fights.remove(fightOffensive);
		offenderCharacters.remove(fightOffensive.getOffender());
	}
	
	private void hit(FightOffensive fightOffensive) {
		if (fightOffensive == null) {
			return;
		}
		Character offender = fightOffensive.getOffender();
		Character victim = fightOffensive.getVictim();
		Room currentRoom = offender.getCurrentRoom();
		if (!currentRoom.equals(victim.getCurrentRoom())) {
			stopFight(fightOffensive);
			return;
		}
		createFightOffensive(victim, offender);
		
		victim.decrementHitPoints(10);
		offender.addResponse(new Response("You hit " + victim.getName(), Color.DARK_YELLOW));
		victim.addResponse(new Response(offender.getName() + " hits you", Color.RED));
		currentRoom.sendToOtherCharacters(new Response(offender.getName() + " hits " + victim.getName(), Color.WHITE), offender, victim);
	}

}
