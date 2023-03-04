package com.user.service;

import com.user.entity.UserProfile;

public interface UserProfileService {

	public String addUserProfile(UserProfile user);

	public UserProfile getUserProfile(int id);

	public UserProfile updateUserProfile(int id, UserProfile user);

	public String deleteUserProfile(int id);
}
