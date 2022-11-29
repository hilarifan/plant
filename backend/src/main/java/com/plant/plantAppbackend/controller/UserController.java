package com.plant.plantAppbackend.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plant.plantAppbackend.PlantAppBackendApplication;
import com.plant.plantAppbackend.Model.AppUser;
import com.plant.plantAppbackend.Repository.UserRepository;
import com.plant.plantAppbackend.Service.EmailSenderService;



//------------PURPOSE: MAPPING ALL THE HTTP METHODS-------

@RestController
//@RequestMapping("")
@CrossOrigin("http://localhost:3000")
public class UserController {//corresponds to "users" in video
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EmailSenderService service;
	
	
	@PostMapping("/addUser")
	AppUser addUser(@RequestBody AppUser newUser) {
//		PlantAppBackendApplication.sendIntroEmail(newUser);
		service.sendIntroEmail(newUser);
	
		return userRepository.save(newUser);
	}
	
	@GetMapping("/getUsers")
	public List<AppUser> getUsers(){
		return this.userRepository.findAll();
	}
	public UserController() {
		// TODO Auto-generated constructor stub
	}
	

	

}