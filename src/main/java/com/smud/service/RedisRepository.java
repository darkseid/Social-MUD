/**
 * 
 */
package com.smud.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.data.redis.support.collections.DefaultRedisList;
import org.springframework.data.redis.support.collections.RedisList;
import org.springframework.stereotype.Service;

import com.smud.model.Inventory;
import com.smud.model.Room;
import com.smud.model.User;
import com.smud.model.Zone;
import com.smud.model.character.Player;

/**
 * @author rafael
 *
 */
@Service
public class RedisRepository {
	
//	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	private static final Logger LOGGER = Logger.getLogger(RedisRepository.class);
	
	private static final String GLOBAL_UID = "global:uid";
	private static final String ALL_USERS = "users";
	private static final String USER = "user";
	
	
	private static final String GLOBAL_PLAYER_ID = "global:pid";
	
	// global users
	private RedisList<String> users;
	
	private final RedisAtomicLong userIdCounter;
	private final RedisAtomicLong playerIdCounter;
	
	private final ValueOperations<String, String> valueOps;
	
	@Autowired
	private Zone zone;
	
	@Autowired
	@Qualifier(value="room3000")
	// TODO remove this.
	private Room DEFAULT_ROOM;
	
	@Autowired
	public RedisRepository(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
		
		valueOps = redisTemplate.opsForValue();
		users = new DefaultRedisList<String>(ALL_USERS, redisTemplate);
		userIdCounter = new RedisAtomicLong(GLOBAL_UID, redisTemplate.getConnectionFactory());
		playerIdCounter = new RedisAtomicLong(GLOBAL_PLAYER_ID, redisTemplate.getConnectionFactory());
		
	}
	
	public User addUser(String userName, String password) {
		
		long uid = getUid();
		String key = getUIDKey(uid);
		
		BoundHashOperations<String, String, String> boundHashOps = redisTemplate.boundHashOps(key);
		
		boundHashOps.put("name", userName);
		boundHashOps.put("password", password);	
		valueOps.set(getUserNameKey(userName), String.valueOf(uid));

		users.addFirst(userName);
		
		User user = new User(uid, userName, password);
		createPlayerForUser(user);
		
		LOGGER.info("Recorded user " + userName + " with id = " + uid);
		
		return user;
	}

	private String getUIDKey(long uid) {
		String key = "uid:" + uid;
		return key;
	}
	
	public User findUser(String userName) {
		
		Long uid = findUIDByUserName(userName);
		
		if (uid == null) {
			return null;
		}
		
		String uidKey = getUIDKey(uid);
		
		BoundHashOperations<String, String, String> userOps = redisTemplate.boundHashOps(uidKey);
		User user = new User(uid,  userOps.get("name"),  userOps.get("password"));
		LOGGER.info("Found user: " + user);
		
		// find his player
		String playerId = valueOps.get(uidKey + ":player");
		Player player = findPlayer(Long.valueOf(playerId));
		user.setPlayer(player);
		
		return user;
	}

	private Player findPlayer(Long playerId) {

		String playerKey = getPlayerKey(playerId);
		
		BoundHashOperations<String, String, String> playerOps = redisTemplate.boundHashOps(playerKey);
		
		String title = playerOps.get("title");
		String currentRoomId = playerOps.get("current_room");
		
		Player player = new Player();
		player.setTitle(title);
		//TODO recover persisted inventory
		player.setInventory(new Inventory());
		
		Room room = getRoom(Integer.valueOf(currentRoomId));
		player.enters(room);
		
		return player;
	}

	private Long findUIDByUserName(String userName) {
		String key = getUserNameKey(userName);
		String uid = valueOps.get(key);
		
		if (uid == null) {
			return null;
		}
		
		return Long.parseLong(uid);
	}

	private String getUserNameKey(String userName) {
		return USER + ":" + userName;
	}

	private long getUid() {
		long id = userIdCounter.incrementAndGet();
		return id;
	}

	
	public void createPlayerForUser(User user) {
		
		long playerId = playerIdCounter.incrementAndGet();
		String playerIdKey = getPlayerKey(playerId);
		
		Player player = new Player();
		player.setId(playerId);
		player.enters(DEFAULT_ROOM);
		player.setTitle("Sir. Default");

		user.setPlayer(player);
		
		BoundHashOperations<String, String, String> playerOps = redisTemplate.boundHashOps(playerIdKey);
		
		playerOps.put("user_id", String.valueOf(player.getId()));
		playerOps.put("title", player.getTitle());
		playerOps.put("current_room", String.valueOf(player.getCurrentRoom().getId()));
		
		//TODO persist player inventory
		
		// stores the player_id
		valueOps.set(getUIDKey(user.getId()) + ":player", String.valueOf(playerId));
	}

	private String getPlayerKey(long playerId) {
		String playerIdKey = "player:" + playerId;
		return playerIdKey;
	}
	
	public void updates(Player player) {
		
		String playerKey = getPlayerKey(player.getId());
		
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
