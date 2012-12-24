/**
 * 
 */
package com.smud.service.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

import com.smud.model.Room;
import com.smud.model.User;
import com.smud.model.Zone;
import com.smud.model.character.Player;
import com.smud.model.character.PlayerClass;

/**
 * @author rafael
 *
 */
@Service
public class PlayerRepository {
	
	private RedisTemplate<String, String> redisTemplate;
	
	private static final String GLOBAL_PLAYER_ID = "global:pid";
	
	private final RedisAtomicLong playerIdCounter;
	
	private final ValueOperations<String, String> valueOps;
	
	@Autowired
	private Zone zone;
	
	@Autowired
	public PlayerRepository(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
		
		valueOps = redisTemplate.opsForValue();
		playerIdCounter = new RedisAtomicLong(GLOBAL_PLAYER_ID, redisTemplate.getConnectionFactory());
		
	}
	
	public Player findPlayer(Long playerId) {

		String playerKey = KeyUtils.PLAYER.getKeyFor(playerId.toString());
				
				
		BoundHashOperations<String, String, String> playerOps = redisTemplate.boundHashOps(playerKey);
		
		String title = playerOps.get("title");
		String name = playerOps.get("name");
		String currentRoomId = playerOps.get("current_room");
		String characterClassName = playerOps.get("class");
		String level = playerOps.get("level");
		
		String strength = playerOps.get("strength");
		String dexteriry = playerOps.get("dexterity");
		String constitution = playerOps.get("constitution");
		String intelligence = playerOps.get("intelligence");
		String wisdom = playerOps.get("wisdom");
		String charisma = playerOps.get("charisma");
		
		String maxHitPoints = playerOps.get("max_hit_points");
		String maxManaPoints = playerOps.get("max_mana_points");
		String maxMovementPoints = playerOps.get("max_movement_points");
		String hitPoints = playerOps.get("hit_points");
		String manaPoints = playerOps.get("mana_points");
		String movementPoints = playerOps.get("movement_points");
		
		Player player = new Player();
		player.setTitle(title);
		player.setName(name);
		
		Room room = getRoom(Integer.valueOf(currentRoomId));
		player.enters(room);
		
		PlayerClass characterClass = PlayerClass.valueOf(characterClassName);
		player.setPlayerClass(characterClass);
		player.setLevel(Integer.parseInt(level));
		
		player.setStrength(Integer.parseInt(strength));
		player.setBaseDexterity(Integer.parseInt(dexteriry));
		player.setConstitution(Integer.parseInt(constitution));
		player.setIntelligence(Integer.parseInt(intelligence));
		player.setWisdom(Integer.parseInt(wisdom));
		player.setCharisma(Integer.parseInt(charisma));
		
		player.setMaxHitPoints(Integer.valueOf(maxHitPoints));
		player.setMaxManaPoints(Integer.parseInt(maxManaPoints));
		player.setMaxMovementPoints(Integer.parseInt(maxMovementPoints));
		player.setHitPoints(Integer.parseInt(hitPoints));
		player.setManaPoints(Integer.parseInt(manaPoints));
		player.setMovementPoints(Integer.parseInt(movementPoints));
		
		return player;
	}

	
	public long addPlayer(User user, Player player) {
		
		long playerId = playerIdCounter.incrementAndGet();
		String playerIdKey = KeyUtils.PLAYER.getKeyFor(playerId);
		
		BoundHashOperations<String, String, String> playerOps = redisTemplate.boundHashOps(playerIdKey);

		playerOps.put("user_id", String.valueOf(player.getId()));
		playerOps.put("title", player.getTitle());
		playerOps.put("name", player.getName());
		playerOps.put("current_room", String.valueOf(player.getCurrentRoom().getId()));
		playerOps.put("class", player.getPlayerClass().name());
		playerOps.put("level", String.valueOf(player.getLevel()));
		
		playerOps.put("strength", String.valueOf(player.getStrength()));
		playerOps.put("dexterity", String.valueOf(player.getBaseDexterity()));
		playerOps.put("constitution", String.valueOf(player.getConstitution()));
		playerOps.put("intelligence", String.valueOf(player.getIntelligence()));
		playerOps.put("wisdom", String.valueOf(player.getWisdom()));
		playerOps.put("charisma", String.valueOf(player.getCharisma()));
		
		playerOps.put("max_hit_points", String.valueOf(player.getMaxHitPoints()));
		playerOps.put("max_mana_points", String.valueOf(player.getMaxManaPoints()));
		playerOps.put("max_movement_points", String.valueOf(player.getMaxMovementPoints()));
		playerOps.put("hit_points", String.valueOf(player.getHitPoints()));
		playerOps.put("mana_points", String.valueOf(player.getManaPoints()));
		playerOps.put("movement_points", String.valueOf(player.getMovementPoints()));
		
		// stores the player_id
		valueOps.set(KeyUtils.USER.getKeyFor(user.getId()) + ":player", String.valueOf(playerId));
		return playerId;
	}
	
	public void updatePlayer(Player player) {
		
		String playerKey = KeyUtils.PLAYER.getKeyFor(player.getId());
		
		BoundHashOperations<String, String, String> playerOps = redisTemplate.boundHashOps(playerKey);
		playerOps.put("title", player.getTitle());
		playerOps.put("current_room", String.valueOf(player.getCurrentRoom().getId()));
		playerOps.put("level", String.valueOf(player.getLevel()));
		
		playerOps.put("strength", String.valueOf(player.getStrength()));
		playerOps.put("dexterity", String.valueOf(player.getBaseDexterity()));
		playerOps.put("constitution", String.valueOf(player.getConstitution()));
		playerOps.put("intelligence", String.valueOf(player.getIntelligence()));
		playerOps.put("wisdom", String.valueOf(player.getWisdom()));
		playerOps.put("charisma", String.valueOf(player.getCharisma()));
		
		playerOps.put("max_hit_points", String.valueOf(player.getMaxHitPoints()));
		playerOps.put("max_mana_points", String.valueOf(player.getMaxManaPoints()));
		playerOps.put("max_movement_points", String.valueOf(player.getMaxMovementPoints()));
		playerOps.put("hit_points", String.valueOf(player.getHitPoints()));
		playerOps.put("mana_points", String.valueOf(player.getManaPoints()));
		playerOps.put("movement_points", String.valueOf(player.getMovementPoints()));
		// TODO Other fields
		
	}

	private Room getRoom(int roomId) {
		for ( Room room : zone.getRooms() ) {
			if (room.getId() == roomId) {
				return room;
			}
		}
		throw new RuntimeException("Quarto nao encontrado");
	}
}
