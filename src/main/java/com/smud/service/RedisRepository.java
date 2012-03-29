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

import com.smud.model.Room;
import com.smud.model.User;
import com.smud.model.Zone;
import com.smud.model.character.Player;
import com.smud.service.data.KeyUtils;
import com.smud.service.data.PlayerRepository;

/**
 * @author rafael
 *
 */
@Service
public class RedisRepository {
	
//	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	private PlayerRepository playerRepository;
	
	private static final Logger LOGGER = Logger.getLogger(RedisRepository.class);
	
	private static final String GLOBAL_UID = "global:uid";
	private static final String ALL_USERS = "users";
	private static final String USER = "user";
	
	// global users
	private RedisList<String> users;
	
	private final RedisAtomicLong userIdCounter;
	
	private final ValueOperations<String, String> valueOps;
	
	@Autowired
	private Zone zone;
	
	@Autowired
	@Qualifier(value="room3000")
	// TODO remove this.
	private Room DEFAULT_ROOM;
	
	@Autowired
	public RedisRepository(RedisTemplate<String, String> redisTemplate, PlayerRepository playerRepository) {
		this.redisTemplate = redisTemplate;
		this.playerRepository = playerRepository;
		
		valueOps = redisTemplate.opsForValue();
		users = new DefaultRedisList<String>(ALL_USERS, redisTemplate);
		userIdCounter = new RedisAtomicLong(GLOBAL_UID, redisTemplate.getConnectionFactory());
		
	}
	
	public User addUser(String userName, String password) {
		
		long uid = getUid();
		String key = KeyUtils.USER.getKeyFor(uid);
		
		BoundHashOperations<String, String, String> boundHashOps = redisTemplate.boundHashOps(key);
		
		boundHashOps.put("name", userName);
		boundHashOps.put("password", password);	
		valueOps.set(getUserNameKey(userName), String.valueOf(uid));

		users.addFirst(userName);
		
		User user = new User(uid, userName, password);
		playerRepository.createPlayerForUser(user);
		
		LOGGER.info("Recorded user " + userName + " with id = " + uid);
		
		return user;
	}

	
	public User findUser(String userName) {
		
		Long uid = findUIDByUserName(userName);
		
		if (uid == null) {
			return null;
		}
		
		String uidKey = KeyUtils.USER.getKeyFor(uid);
		
		BoundHashOperations<String, String, String> userOps = redisTemplate.boundHashOps(uidKey);
		User user = new User(uid,  userOps.get("name"),  userOps.get("password"));
		LOGGER.info("Found user: " + user);
		
		// find his player
		String playerId = valueOps.get(uidKey + ":player");
		Player player = playerRepository.findPlayer(Long.valueOf(playerId));
		user.setPlayer(player);
		
		return user;
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
	
}
