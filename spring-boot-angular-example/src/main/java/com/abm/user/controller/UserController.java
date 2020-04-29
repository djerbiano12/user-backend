package com.abm.user.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.abm.user.dto.UserDto;
import com.abm.user.dto.UserDtoMapper;
import com.abm.user.repository.entity.User;
import com.abm.user.service.RoleService;
import com.abm.user.service.UserService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/users")
public class UserController {

	private List<User> users = new ArrayList<User>();
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	 @Autowired
	private UserService userService;
	 
	 @Autowired
	private RoleService roleService;
	 
	@Autowired
	private ModelMapper modelMapper;

	UserController() {
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<?> getUsers() {
		return userService.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public UserDto getUser(@PathVariable("id") Long id) {
		return UserDtoMapper.convertToDto(userService.findById(id), modelMapper);
	}
	
	@RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
	public UserDto getUserByEmail(@PathVariable("email") String email) {
		return UserDtoMapper.convertToDto(userService.getUserByEmail(email), modelMapper);
	}

	@Secured({ROLE_ADMIN})
	@RequestMapping(method = RequestMethod.POST)
	public UserDto saveUser(@RequestBody UserDto user) throws ParseException {
		user.setPassword(encoder.encode(user.getPassword()));
		user.getRoles().add(roleService.getRoleByName(user.getRole()));
		return UserDtoMapper.convertToDto(userService.create(UserDtoMapper.convertToEntity(user, modelMapper)), modelMapper);
	}

	@Secured({ROLE_ADMIN})
	@RequestMapping(method = RequestMethod.PUT)
	public UserDto updateUser(@RequestBody UserDto user) {
		User modifiedUser = userService.findById(user.getId());
		modifiedUser.setFirstName(user.getFirstName());
		modifiedUser.setLastName(user.getLastName());
		modifiedUser.setEmail(user.getEmail());
		modifiedUser.setPassword(encoder.encode(user.getPassword()));
		modifiedUser.getRoles().add(roleService.getRoleByName(user.getRole()));
		return UserDtoMapper.convertToDto(userService.update(modifiedUser), modelMapper);
	}

	@Secured({ROLE_ADMIN})
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean deleteUser(@PathVariable Long id) {
		User deleteUser = userService.delete(id);
		if (deleteUser != null) {
			this.users.remove(deleteUser);
			return true;
		} else  {
			return false;
		}
	}
}