/**
 * 
 */
package com.smud.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.data.redis.support.collections.DefaultRedisList;
import org.springframework.data.redis.support.collections.RedisList;
import org.springframework.stereotype.Service;

import com.smud.model.User;

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
	
	// global users
	private RedisList<String> users;
	
	private final RedisAtomicLong userIdCounter;
	private final ValueOperations<String, String> valueOps;
	
	@Autowired
	public RedisRepository(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
		
		valueOps = redisTemplate.opsForValue();
		users = new DefaultRedisList<String>(ALL_USERS, redisTemplate);
		userIdCounter = new RedisAtomicLong(GLOBAL_UID, redisTemplate.getConnectionFactory());
	}
	
	public void addUser(String userName, String password) {
		
		long uid = getUid();
		String key = getUIDKey(uid);
		
		BoundHashOperations<String, String, String> boundHashOps = redisTemplate.boundHashOps(key);
		
		boundHashOps.put("name", userName);
		boundHashOps.put("password", password);	
		valueOps.set(getUserNameKey(userName), String.valueOf(uid));

		users.addFirst(userName);
		
		LOGGER.info("Recorded user " + userName + " with id = " + uid);
	}

	private String getUIDKey(long uid) {
		String key = "uid:" + uid;
		return key;
	}
	
	public User findUser(String userName) {
		
		Long uid = findUIDByUserName(userName);
		
		if (uid == null) {
			return null;
			// TODO consider to throw an exception here
		}
		
		String uidKey = getUIDKey(uid);
		
		BoundHashOperations<String, String, String> userOps = redisTemplate.boundHashOps(uidKey);
		
		User user = new User(uid,  userOps.get("name"),  userOps.get("password"));
		
		LOGGER.info("Found user: " + user);
		
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
