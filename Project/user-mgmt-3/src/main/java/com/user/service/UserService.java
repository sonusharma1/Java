package com.user.service;

import com.user.entity.User;

public interface UserService {

	public String addUser(User user);

	public User getUser(int id);

	public User updateUser(int id, User user);

	public String deleteUser(int id);

	public String mapUserToProfile(int userId, int profileId);
}
