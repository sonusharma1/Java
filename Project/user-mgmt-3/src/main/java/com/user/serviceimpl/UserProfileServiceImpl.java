package com.user.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.entity.UserProfile;
import com.user.repository.UserProfileRepository;
import com.user.service.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	private UserProfileRepository userProfileRepository;

	@Override
	public String addUserProfile(UserProfile userProfile) {
		if (userProfile != null) {
			userProfileRepository.save(userProfile);
			return "user added successfully";
		}
		return "user not added !!";
	}

	@Override
	public UserProfile getUserProfile(int id) {
		return userProfileRepository.findById(id).get();
	}

	@Override
	public UserProfile updateUserProfile(int id, UserProfile userProfile) {
		UserProfile foundUser = userProfileRepository.findById(id).get();
		if (foundUser != null) {
			foundUser.setFirstName(userProfile.getFirstName());
			foundUser.setLastName(userProfile.getLastName());
			userProfileRepository.save(foundUser);
			return foundUser;
		}
		return null;
	}

	@Override
	public String deleteUserProfile(int id) {
		UserProfile foundUser = userProfileRepository.findById(id).get();
		if (foundUser != null) {
			userProfileRepository.deleteById(id);
			return "deleted successfully";
		}
		return "Not deleted";
	}

}
