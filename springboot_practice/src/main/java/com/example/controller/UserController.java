package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Service.UserService;
import com.example.entity.User;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping({"/saveUser"})
	public User newUser(@RequestBody User user) {
		System.out.println("bacd");
		System.out.println(user);
		return userService.saveUser(user);
	}
	
	@GetMapping("{id}")
	public Optional<User> getUser(@PathVariable("id") long id) {
		return userService.getUserId(id);
	}

	@GetMapping
	public List<User> getAllUser() {
		return userService.getAllUsers();
	}

	@DeleteMapping("{id}")
	public void deleteUser(@PathVariable("id") long id) {
		userService.deleteUser(id);
	}

	@PutMapping
	public User updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}

}
