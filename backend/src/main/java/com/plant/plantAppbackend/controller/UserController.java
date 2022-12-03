package com.plant.plantAppbackend.controller;


import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.scheduling.annotation.Async;
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
import com.plant.plantAppbackend.Model.CanWaterResponse;
import com.plant.plantAppbackend.Model.LoginForm;
import com.plant.plantAppbackend.Model.LoginValidResponse;
import com.plant.plantAppbackend.Model.NeedsWateringForm;
import com.plant.plantAppbackend.Model.PlantModel;
import com.plant.plantAppbackend.Model.PlantResponse;
import com.plant.plantAppbackend.Model.RemovePlantForm;
import com.plant.plantAppbackend.Repository.UserRepository;

import net.bytebuddy.asm.Advice.Return;
import net.bytebuddy.implementation.bytecode.constant.MethodConstant.CanCache;



//------------PURPOSE: MAPPING ALL THE HTTP METHODS-------

@RestController
//@RequestMapping("")
@CrossOrigin("http://localhost:3000")
public class UserController {//corresponds to "users" in video
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EmailSenderService service;

		
	@PostMapping("/signup")
	@ResponseBody
	AppUser addUser(@RequestBody AppUser newUser) {
//		PlantAppBackendApplication.sendIntroEmail(newUser);
		System.out.println("Attempting to sign up new user");
		List<AppUser> users = this.userRepository.findByUsername(newUser.getUsername());
		
		//cannot find users of this username
		if (!users.isEmpty()) {
			newUser.setId((int)-1);
			return newUser;
		}
		else {
			service.sendIntroEmail(newUser);
		}
		
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
//	@GetMapping("/canWater/{id}/{plant}")
	public Boolean wateringPlantsNecessary(Long id, String plantType) {
		System.out.println("getting plants that need to be watered");
	
		//find user by ID
		List<AppUser> findUsers = userRepository.findByUserid(id);
		AppUser user = findUsers.get(0);
		//check which plants need watering
		
		List<PlantModel> needsWaterList = user.needsWatering();
		if (needsWaterList.size() == 0) {
			return false;
		}
		for (int i = 0; i < needsWaterList.size(); i++ ) {
			PlantModel currPlant = needsWaterList.get(i);
			if (currPlant.getPlantName().toLowerCase().equals(plantType.toLowerCase())) {
				return true;
			}
		}
		
		return false;
	}
	
	//PROFILE PAGE FUNCTION!! RETURN EVERYTHING ON SIGNUP PAGE
	//-----------------PROFILE PAGE FUNCTION------------------------
	@GetMapping("/profile/{id}")
	public AppUser getUserData(@PathVariable Long id) {
		System.out.println("Attempting to get User Data");
		List<AppUser> findUsers = userRepository.findByUserid(id);
		
		if (!findUsers.isEmpty()) {
			AppUser user = findUsers.get(0);
			return user;
		}
		else {
			return null;
		}
	}
	
	@GetMapping("/getPlants/{id}")
	@ResponseBody
	public List<PlantResponse> getAll(@PathVariable String id) {
		System.out.println("this is id" + id);
		Long idLong = Long.parseLong(id);
		
		System.out.println("getting plants that need to be watered");
		
		//find user by ID
		List<AppUser> findUsers = userRepository.findByUserid(idLong);
		if (findUsers.isEmpty()) {
			System.out.println("empty");
			return null;
		}
		else {
			AppUser user = findUsers.get(0);
			
			//create PlantResponse objects and add it to a list 
			System.out.println("plantlistjson" + user.getPlantListJson());
			JSONArray array = new JSONArray(user.getPlantListJson());  
			List<PlantResponse> plantList = new ArrayList <PlantResponse>();
			for(int j = 0; j < array.length(); j++)   
			{  
				JSONObject object = array.getJSONObject(j);  
				String plantName = object.getString("Name");  
				int quant = Integer.parseInt(object.getString("Quantity"));
				
				Boolean water = wateringPlantsNecessary(Long.parseLong(id), plantName);
				PlantResponse newPlantResponse;
				if (water==false) {
					newPlantResponse = new PlantResponse(plantName, quant, false);
				}
				else {
					newPlantResponse = new PlantResponse(plantName, quant, true);
				}
				
				plantList.add(newPlantResponse);
			}  
			System.out.println("plants in list: " + plantList);
			//return null;
			return plantList;
			
		}
	}
	@PostMapping("/login")
	public LoginValidResponse loginUser(@RequestBody LoginForm login){
		String username = login.getUsername();
		
		List<AppUser> users = this.userRepository.findByUsername(username);
		
		//cannot find users of this username
		if (users.isEmpty()) {
			long l = -1;
			return new LoginValidResponse("true", "this username doesn't exist", l);
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
	
	@PostMapping("/minusPlant") // ASK GRACE HOW SHE IS PASSING ME 
	public @ResponseBody String removePlantFromFrontend(@RequestBody RemovePlantForm removeRequest) {
		System.out.println("frontend starting to remove plant....");

		System.out.println("id is " + removeRequest.getUserId());
		System.out.println("user plant type " + removeRequest.getPlantType());
		List<AppUser> findUsers = userRepository.findByUserid(removeRequest.getUserId());
		
		AppUser currAppUser = findUsers.get(0);
		
		currAppUser.removePlant(removeRequest.getPlantType());
		userRepository.save(currAppUser);
		
		return "Successfully updated user";
		
	}
	
	//IF TIME PERMITS : USE SPRING BOOT VALIDATION!! 
	@PostMapping("/addPlant") // ASK GRACE HOW SHE IS PASSING ME 
	public @ResponseBody String addPlantFromFrontend(@RequestBody AddPlantForm addRequest) {
		System.out.println("frontend starting to add plant....");
		
		List<AppUser> findUsers = userRepository.findByUserid(addRequest.getUserId());
		System.out.println("user id " + addRequest.getUserId());
		System.out.println("user plant type " + addRequest.getPlantType());
		System.out.println(addRequest.toString());

		AppUser currAppUser;
		
		if (findUsers.isEmpty() == false) {
			currAppUser =findUsers.get(0);
			currAppUser.addPlantUpdateString(addRequest.getPlantType());
			userRepository.save(currAppUser);
			System.out.println("successfully added plant....");

			return "Successfully updated user!";
		}
		else {
			
			System.out.println("user could not be found by id");
			return "user could not be found by id";

		}
			
	}
	
	@Async
	@PostMapping("/waterPlant") // ASK GRACE HOW SHE IS PASSING ME 
	public @ResponseBody String doesPlantNeedWater(@RequestBody NeedsWateringForm wateringRequest) {
		System.out.println("frontend asking about water....");

		System.out.println("id is " + wateringRequest.getUserId());
		System.out.println("user plant type " + wateringRequest.getPlantType());
//		System.out.println("needs water " + wateringRequest.getNeedsWatering());
//		String needsWater = wateringRequest.getNeedsWatering();
		List<AppUser> findUsers = userRepository.findByUserid(wateringRequest.getUserId());
		
		AppUser currAppUser = findUsers.get(0);
		
		String plantType = wateringRequest.getPlantType();
		
		List<PlantModel> plantList = currAppUser.getPlantList();
		for (int i = 0; i < plantList.size(); i++) {
			PlantModel currPlant = plantList.get(i);
			String currPlantNameString=currPlant.getPlantName().toLowerCase();
			String pType = plantType.toLowerCase();
			System.out.println("DATAAAAAA");
			System.out.println(currPlantNameString);
			System.out.println(pType);
			System.out.println(currPlantNameString.equals(pType));
			if (currPlantNameString.equals(pType)) {
				System.out.println("water button was PRESSED");
				currPlant.setDaysSinceWatering((int)0);
				System.out.println("CURRPLANT DAYS " + currPlant.getDaysSinceWatering());

				
			}
		}
		
		currAppUser.listToJSONString(plantList);
//		System.out.println(currAppUser.);
		
		userRepository.save(currAppUser);
		
		return "Successfully updated user";
		
	}
	
	
	
	
}