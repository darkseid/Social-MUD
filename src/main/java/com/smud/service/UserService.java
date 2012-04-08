package com.smud.service;

import java.util.Collection;

import com.smud.model.User;
import com.smud.model.character.PlayerClass;

public interface UserService {

	User findUser(String userName);
	
	User findLoggedUser(String userName);

	User addUser(String userName, String password, PlayerClass characterClass);

	Collection<User> getAllLoggedUsers();

}
