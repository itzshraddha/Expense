package com.sp.app.expense.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sp.app.expense.entity.UserEntity;
import com.sp.app.expense.repositories.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/userDetails")
	public ResponseEntity<List<UserEntity>> getAllUsers() {
		try {
			List<UserEntity> users = new ArrayList<>();
			users = userRepository.findAll();
			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/users")
	public ResponseEntity<UserEntity> saveUserDetails(@RequestBody UserEntity userDetails) {
		try {
			UserEntity user = userRepository.save(new UserEntity(userDetails.getName(), userDetails.getContactNumber(), userDetails.getEmail()));
			return new ResponseEntity<UserEntity>(user, HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
