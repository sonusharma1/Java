package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.entity.User;
import com.user.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/addUser")
	public String addUser(@RequestBody User user) {
		System.out.println(user.getUserName() + " " + user.getPassword());
		return userService.addUser(user);
	}

	@GetMapping("/getUser/{id}")
	public User getUser(@PathVariable("id") int id) {
		System.out.println(id);
		return userService.getUser(id);
	}

	@PutMapping("/updateUser/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User user) {
		return userService.updateUser(id, user);
	}

	@DeleteMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable int id) {
		return userService.deleteUser(id);
	}

	@PutMapping("/mapUserToProfile/{userId}/{profileId}")
	public String mapUserToProfile(@PathVariable("userId") int userId, @PathVariable("profileId") int profileId) {
		System.out.println(userId + " " + profileId);
		return userService.mapUserToProfile(userId, profileId);
	}
}
