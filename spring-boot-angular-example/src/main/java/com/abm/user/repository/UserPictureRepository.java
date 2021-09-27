package com.abm.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abm.user.repository.entity.User;
import com.abm.user.repository.entity.UserPicture;

public interface UserPictureRepository extends JpaRepository<UserPicture, Long>{
	UserPicture getUserPictureByUtilisateur(User user);
}
