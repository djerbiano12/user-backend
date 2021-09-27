package com.abm.user.service;

import com.abm.user.repository.entity.User;
import com.abm.user.repository.entity.UserPicture;

public interface UserPictureService {
	public UserPicture update(UserPicture userPicture);
	public UserPicture getUserPictureByUser(User user);
}
