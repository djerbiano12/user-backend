package com.abm.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abm.user.repository.UserPictureRepository;
import com.abm.user.repository.entity.User;
import com.abm.user.repository.entity.UserPicture;

@Service
public class UserPictureServiceImpl implements UserPictureService{
	
	@Autowired
	private UserPictureRepository repository;

	@Override
	public UserPicture update(UserPicture userPicture) {
		return repository.save(userPicture);
	}

	@Override
	public UserPicture getUserPictureByUser(User user) {
		return repository.getUserPictureByUtilisateur(user);
	}

}
