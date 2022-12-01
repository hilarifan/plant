package com.plant.plantAppbackend.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.plant.plantAppbackend.PlantAppBackendApplication;
import com.plant.plantAppbackend.Model.AddPlantForm;
import com.plant.plantAppbackend.Model.AppUser;
import com.plant.plantAppbackend.Model.LoginForm;
import com.plant.plantAppbackend.Repository.UserRepository;

import net.bytebuddy.asm.Advice.Return;



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
	
	@GetMapping("/login")
	@ResponseBody
	public String loginUser(@RequestBody LoginForm login){
		String username = login.getUserName();
		
		List<AppUser> users = this.userRepository.findByUsername(username);
		
		AppUser currAppUser= users.get(0);
		Long userIDLong = currAppUser.getId();
		if (currAppUser.psswdValidation(username) == true || users.isEmpty())  {
			return "{\"error\":true, \"errorMessage\":"
					+ "\"wrong username or password”, "
					+ "“id” :" + userIDLong.toString()
					+"}";

		}
	
		return "{\"error\":false, \"errorMessage\":"
		+ "\"successful login”, "
		+ "“id” :" + userIDLong.toString()
		+"}";


	}
	
	//IF TIME PERMITS : USE SPRING BOOT VALIDATION!! 
	@PutMapping("/addPlant/{id}")
	public @ResponseBody String addPlantFromFrontend(@RequestBody AddPlantForm addRequest) {
		
		List<AppUser> findUsers = userRepository.findByUserid(addRequest.getUserId());
		AppUser currAppUser =findUsers.get(0);
		
		currAppUser.addPlantUpdateString(addRequest.getPlantType());
		userRepository.save(currAppUser);
		
		return "Successfully updated user";
		
	}
	
	
	
	
}