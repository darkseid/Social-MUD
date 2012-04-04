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
import com.smud.model.character.CharacterClass;
import com.smud.model.character.Player;

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
		
		Player player = new Player();
		player.setTitle(title);
		player.setName(name);
		
		Room room = getRoom(Integer.valueOf(currentRoomId));
		player.enters(room);
		
		CharacterClass characterClass = CharacterClass.valueOf(characterClassName);
		player.setCharacterClass(characterClass);
		
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
		playerOps.put("class", player.getCharacterClass().name());
		
		// stores the player_id
		valueOps.set(KeyUtils.USER.getKeyFor(user.getId()) + ":player", String.valueOf(playerId));
		return playerId;
	}
	
	public void updatePlayer(Player player) {
		
		String playerKey = KeyUtils.PLAYER.getKeyFor(player.getId());
		
		BoundHashOperations<String, String, String> playerOps = redisTemplate.boundHashOps(playerKey);
		playerOps.put("current_room", String.valueOf(player.getCurrentRoom().getId()));
		
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
