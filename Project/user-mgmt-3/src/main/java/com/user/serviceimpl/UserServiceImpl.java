package com.user.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.entity.User;
import com.user.entity.UserProfile;
import com.user.repository.UserProfileRepository;
import com.user.repository.UserRepository;
import com.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserProfileRepository userProfileRepository;

	@Override
	public String addUser(User user) {
		if (user != null) {
			userRepository.save(user);
			return "user added successfully";
		}
		return "user not added !!";
	}

	@Override
	public User getUser(int id) {
		return userRepository.findById(id).get();
	}

	@Override
	public User updateUser(int id, User user) {
		User foundUser = userRepository.findById(id).get();
		if (foundUser != null) {
			foundUser.setUserName(user.getUserName());
			foundUser.setPassword(user.getPassword());
			userRepository.save(foundUser);
			return foundUser;
		}
		return null;
	}

	@Override
	public String deleteUser(int id) {
		User foundUser = userRepository.findById(id).get();
		if (foundUser != null) {
			userRepository.deleteById(id);
			return "deleted successfully";
		}
		return "Not deleted";
	}

	@Override
	public String mapUserToProfile(int userId, int profileId) {
		User foundUser = userRepository.findById(userId).get();
		UserProfile foundProfile = userProfileRepository.findById(profileId).get();

		if (foundUser != null && foundProfile != null) {
			foundUser.setUserProfile(foundProfile);
			foundProfile.setUser(foundUser);

			userRepository.save(foundUser);
			userProfileRepository.save(foundProfile);
			return "mapped successfully";
		}
		return "not mapped";
	}

}
