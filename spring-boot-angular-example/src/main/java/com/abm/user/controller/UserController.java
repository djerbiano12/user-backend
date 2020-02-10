package com.abm.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.abm.user.repository.entity.User;
import com.abm.user.service.UserService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/users")
public class UserController {

	private List<User> users = new ArrayList<User>();
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	 @Autowired
	private UserService userService;

	UserController() {
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<?> getUsers() {
		return userService.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable("id") Long id) {
		return userService.findById(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public User saveUser(@RequestBody User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return userService.create(user);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public User updateUser(@RequestBody User user) {
		User modifiedUser = this.getUser(user.getId());
		modifiedUser.setFirstName(user.getFirstName());
		modifiedUser.setLastName(user.getLastName());
		modifiedUser.setEmail(user.getEmail());
		modifiedUser.setPassword(encoder.encode(user.getPassword()));
		return userService.update(modifiedUser);
	}

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
	
	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	public Boolean canConnect(@RequestParam String email, @RequestParam String password){
		return userService.canUserConnect(email, password);
	}
}