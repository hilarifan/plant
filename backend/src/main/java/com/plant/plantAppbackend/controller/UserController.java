package com.plant.plantAppbackend.controller;


import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.plant.plantAppbackend.Model.LoginValidResponse;
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
	
	
//	@PostMapping("/signup")
//	public JSONObject addUser(@RequestBody AppUser newUser) {
////		PlantAppBackendApplication.sendIntroEmail(newUser);
//		System.out.println("Attempting to sign up new user");
//		service.sendIntroEmail(newUser);
//		//return userRepository.save(newUser);
//		return new JSONObject("{'aa':'bb'}");
////		try {
//			
//			userRepository.save(newUser);
//			return new JSONObject()"{"
//
//				+"\"error\": false,"
//				+"\"errorMessage\": \"hello\","
//				+"\"id\": 8"
//				+ "}"; 
//
//			
//		catch (Exception e){
//			return "{\"error\":true, "
//					+ "\"errorMessage\":\"signup had an error”,"
//					+ " “id” : " + newUser.getId() + "}";	
//			
//		}
//		}
//	} 

		
	@PostMapping("/signup")
	@ResponseBody
	AppUser addUser(@RequestBody AppUser newUser) {
//		PlantAppBackendApplication.sendIntroEmail(newUser);
		System.out.println("Attempting to sign up new user");
		service.sendIntroEmail(newUser);
		return userRepository.save(newUser);
//		try {
//			userRepository.save(newUser);
//			return "{\"error\":false, "
//					+ "\"errorMessage\":\"signup was successful”,"
//					+ " “id” : " + newUser.getId() + "}";		
//		}
//		catch (Exception e){
//			return "{\"error\":true, "
//					+ "\"errorMessage\":\"signup had an error”,"
//					+ " “id” : " + newUser.getId() + "}";	
			
	}

	
	
	@GetMapping("/getUsers")
	public List<AppUser> getUsers(){
		return this.userRepository.findAll();
	}
	
	//-------CONNECTS TO WATER.JS -------------------
	@GetMapping("/canWater/{id}/{plant}")
	public String wateringPlantsNecessary(@PathVariable Long id, @PathVariable String plantType) {
		System.out.println("getting plants that need to be watered");
	
		//find user by ID
		List<AppUser> findUsers = userRepository.findByUserid(id);
		AppUser user = findUsers.get(0);
		//check which plants need watering
		String watering = user.doesUserWaterPlants(plantType);
		return watering;
	}
	
	
//	@PostMapping("/login")
//	@ResponseBody
//	public String loginUser(@RequestBody LoginForm login){
//		System.out.println("Attempting to log in user");
//		System.out.println("loginForm: " + login.toString());
//		String username = login.getUsername();
//		System.out.println("Searching for username " + username);
////		List<AppUser> users = this.userRepository.findByUsername(username);
//		//FOR TESTING BELOW:
//		List<AppUser> users = this.userRepository.findByUsername("andyS");
//
//		if (users.isEmpty()) {
//			System.out.println("Login could not find user by Username");
//
//			return "{\"error\":true, "
//					+ "\"errorMessage\":"
//					+ "\"user not found”, "
//					+ "“id” :" + "-1"
//					+"}";
//		}
//		else {
//			AppUser currAppUser= users.get(0);
//			Long userIDLong = currAppUser.getId();
//			if (currAppUser.psswdValidation(username) == true)  {
//				System.out.println("user password does not match");
//
//				return "{\"error\":true, \"errorMessage\":"
//						+ "\"wrong username or password”, "
//						+ "“id” :" + userIDLong.toString()
//						+"}";
//
//			}
//			System.out.println("user successfully logged in");
//
//			return "{\"error\":false, \"errorMessage\":"
//			+ "\"successful login”, "
//			+ "“id” :" + userIDLong.toString()
//			+"}";
//			
//		}
//	}
	

//	@PostMapping("/login")
//	@ResponseBody
//	public JSONObject loginUser(@RequestBody LoginForm login){
//		System.out.println("Attempting to log in user");
//		System.out.println("loginForm: " + login.toString());
//		String username = login.getUsername();
//		System.out.println("Searching for username " + username);
////		List<AppUser> users = this.userRepository.findByUsername(username);
//		//FOR TESTING BELOW:
//		List<AppUser> users = this.userRepository.findByUsername(username);
//
//		if (users.isEmpty()) {
//			System.out.println("Login could not find user by Username");
////			return "{\"error\":true, \"errorMessage\":\"user not found\"}";
//			return new JSONObject("{'error':false, 'id':3}");
//		}
//		else {
//			AppUser currAppUser= users.get(0);
//			Long userIDLong = currAppUser.getId();
//			if (currAppUser.psswdValidation(username) == true)  {
//				System.out.println("user password does not match");
//
////				return "{\"error\":true, \"errorMessage\":\"password does not match\"}";
//				return new JSONObject("{'error':false, 'id':3}");
//			}
//			System.out.println("user successfully logged in");
//
////			return "{\"error\":false, \"errorMessage\":"
////			+ "\"successful login”, "
////			+ "“id” :" + userIDLong.toString()
////			+"}";
//			
////			return "{\"error\":false, \"id\":3}";
//			//TRY RETURNING A VALID JSON OBJECT
//			
//			return new JSONObject("{'error':false, 'id':3}");
//			
//		}
//		
//	}
	
	@PostMapping("/login")
	public LoginValidResponse loginUser(@RequestBody LoginForm login){
		String username = login.getUsername();
		
		List<AppUser> users = this.userRepository.findByUsername(username);
		
		//cannot find users of this username
		if (users.isEmpty()) {
			return new LoginValidResponse("true", "this username doesn't exist", (long) -1);
		}
		
		AppUser currAppUser= users.get(0);
		Long userIDLong = currAppUser.getId();
		//login was VALID
//		System.out.println("this is the password" + currAppUser.getPassword());
//		System.out.println("this is the inputted password" + login.getPassword());
		if (currAppUser.psswdValidation(login.getPassword()) == true)  {
			return new LoginValidResponse("false", "successful login", userIDLong);
			
		}
		//password is incorrect
		else {
			return new LoginValidResponse("true", "username or password wrong", userIDLong);
		}
	}
	
	//boolean needsWater for get plant list 
	
	
	//IF TIME PERMITS : USE SPRING BOOT VALIDATION!! 
	@PutMapping("/addPlant/") // ASK GRACE HOW SHE IS PASSING ME 
	public @ResponseBody String addPlantFromFrontend(@RequestBody AddPlantForm addRequest) {
		
		List<AppUser> findUsers = userRepository.findByUserid(addRequest.getUserId());
		AppUser currAppUser =findUsers.get(0);
		
		currAppUser.addPlantUpdateString(addRequest.getPlantType());
		userRepository.save(currAppUser);
		
		return "Successfully updated user";
		
	}
	
	
	
	
}