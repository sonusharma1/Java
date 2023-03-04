package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.entity.UserProfile;
import com.user.service.UserProfileService;

@RestController
public class UserProfileController {

	@Autowired
	private UserProfileService userProfileService;

	@PostMapping("/addUserProfile")
	public String addUserProfile(@RequestBody UserProfile userProfile) {
		return userProfileService.addUserProfile(userProfile);
	}

	@GetMapping("/getUserProfile/{id}")
	public UserProfile getUserProfile(@PathVariable("id") int id) {
		return userProfileService.getUserProfile(id);
	}

	@PutMapping("/updateUserProfile/{id}")
	public UserProfile updateUserProfile(@PathVariable int id, @RequestBody UserProfile user) {
		return userProfileService.updateUserProfile(id, user);
	}

	@DeleteMapping("/deleteUserProfile/{id}")
	public String deleteUserProfile(@PathVariable int id) {
		return userProfileService.deleteUserProfile(id);
	}
}
