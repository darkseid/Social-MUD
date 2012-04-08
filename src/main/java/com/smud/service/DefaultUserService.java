package com.smud.service;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smud.model.User;
import com.smud.model.character.Player;
import com.smud.model.character.PlayerClass;
import com.smud.service.data.RedisRepository;

@Service
public class DefaultUserService implements UserService {

	@Autowired
	private RedisRepository redisRepository;
	
	@Autowired
	private PlayerService playerService;
	
	private ConcurrentMap<String, User> loggedUsers = new ConcurrentHashMap<String, User>();
	
	@Override
	public User findUser(String userName) {
		User user = findLoggedUser(userName);
		if (user == null) {
			user = redisRepository.findUser(userName);
			loggedUsers.put(user.getName().toLowerCase(), user);
		}
		return user;
	}

	@Override
	public User findLoggedUser(String userName) {
		User user = loggedUsers.get(userName.toLowerCase());
		return user;
	}

	@Override
	public Collection<User> getAllLoggedUsers() {
		return loggedUsers.values();
	}
	
	@Override
	public User addUser(String userName, String password, PlayerClass characterClass) {
		User user = new User(userName, password);
		long userId = redisRepository.addUser(user);
		user.setId(userId);
		Player player = playerService.createPlayerForUser(user, characterClass);
		user.setPlayer(player);
		loggedUsers.put(user.getName(), user);
		return user;
	}
	
	public void setRedisRepository(RedisRepository redisRepository) {
		this.redisRepository = redisRepository;
	}
	
	public void setPlayerService(PlayerService playerService) {
		this.playerService = playerService;
	}

}
