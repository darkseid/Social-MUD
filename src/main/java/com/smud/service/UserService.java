package com.smud.service;

import com.smud.model.User;
import com.smud.model.character.CharacterClass;

public interface UserService {

	User findUser(String userName);

	User addUser(String userName, String password, CharacterClass characterClass);

}
