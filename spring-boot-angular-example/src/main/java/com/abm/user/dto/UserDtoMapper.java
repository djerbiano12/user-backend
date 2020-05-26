package com.abm.user.dto;

import java.text.ParseException;

import org.modelmapper.ModelMapper;

import com.abm.user.repository.entity.User;

public class UserDtoMapper {
	
	public static UserDto convertToDto(User user, ModelMapper modelMapper) {
		if(user != null){
		    UserDto userDto = modelMapper.map(user, UserDto.class);
		    return userDto;
		}
	    return null;
	}
	
	public static User convertToEntity(UserDto userDto, ModelMapper modelMapper) throws ParseException {
	    User user = modelMapper.map(userDto, User.class);
	    return user;
	}
}
