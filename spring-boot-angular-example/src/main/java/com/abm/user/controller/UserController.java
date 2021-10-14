package com.abm.user.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.abm.user.dto.UserDto;
import com.abm.user.dto.UserDtoMapper;
import com.abm.user.repository.entity.Role;
import com.abm.user.repository.entity.User;
import com.abm.user.repository.entity.UserPicture;
import com.abm.user.service.RoleService;
import com.abm.user.service.UserPictureService;
import com.abm.user.service.UserService;
import com.abm.user.utils.CompressByteFile;

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
	private UserPictureService userPictureService;
	
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
		Set<Role> roles = new HashSet<Role>();	
		if(this.getUserByEmail(user.getEmail()) != null){
			throw new RuntimeException("Duplicate email.");
		}
		user.setPassword(encoder.encode(user.getPassword()));
		roles.add(roleService.getRoleByName(user.getRole()));
		user.setRoles(roles);
		return UserDtoMapper.convertToDto(userService.create(UserDtoMapper.convertToEntity(user, modelMapper)), modelMapper);
	}
	
	@PostMapping("/upload")
	public UserPicture uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
		String email = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("#") + 1);
		String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("#"));
		User modifiedUser = userService.getUserByEmail(email);
		UserPicture oldUserPicture = userPictureService.getUserPictureByUser(modifiedUser);
		UserPicture picture = new UserPicture();
		if(oldUserPicture != null){
			picture.setId(oldUserPicture.getId());
		}
		picture.setName(fileName);
		picture.setType(file.getContentType());
		picture.setPicByte(CompressByteFile.compressBytes(file.getBytes()));
		picture.setUtilisateur(modifiedUser);
		
		return userPictureService.update(picture);
	}
	
	@GetMapping(path = { "/get/{email}" })
	public UserPicture getImage(@PathVariable("email") String email) throws IOException {
		User user = userService.getUserByEmail(email);
		UserPicture userPicture = userPictureService.getUserPictureByUser(user);
		userPicture.setPicByte(CompressByteFile.decompressBytes(userPicture.getPicByte()));
		return userPicture;
	}

	@Secured({ROLE_ADMIN})
	@RequestMapping(method = RequestMethod.PUT)
	public UserDto updateUser(@RequestBody UserDto user) {
		User modifiedUser = userService.findById(user.getId());
		modifiedUser.setFirstName(user.getFirstName());
		modifiedUser.setLastName(user.getLastName());
		modifiedUser.setEmail(user.getEmail());
		modifiedUser.setPhoneNumber(user.getPhoneNumber());
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