package com.grca.games.soldiers.support.converter;

import org.springframework.stereotype.Service;

import com.grca.games.soldiers.model.User;
import com.grca.games.soldiers.model.dto.UserDto;

@Service
public class UserConverter {

	public User fromDto(UserDto dto) {
		if (dto == null)
			return null;
		return new User(null, dto.getUsername(), dto.getPassword());
	}

}
