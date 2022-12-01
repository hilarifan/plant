package com.plant.plantAppbackend;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plant.plantAppbackend.Model.AddPlantForm;
import com.plant.plantAppbackend.Model.AppUser;
import com.plant.plantAppbackend.Model.PlantModel;
import com.plant.plantAppbackend.Repository.PlantRepository;
import com.plant.plantAppbackend.Repository.UserRepository;
import com.plant.plantAppbackend.controller.EmailSenderService;
import com.plant.plantAppbackend.controller.UserController;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class PlantAppBackendApplication implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PlantRepository plantRepository;
	@Autowired
	private EmailSenderService service;
	
//	private UserController usercontroller;
	public static long startTime = System.currentTimeMillis();
	
	@Bean("threadPoolTaskExecutor")
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(1000);
		executor.initialize();
		return executor;
	}

	public static void main(String[] args) {
//		AppUser newUser = new AppUser("agupta", "Anika", "Gupta", "pw", "ejzhang@usc.edu");
//		userRepository.save(newUser);
//		System.out.println("this is anika's new id" + newUser.getId());
		SpringApplication.run(PlantAppBackendApplication.class, args);
			
		
	}
	
	@Override 
	public void run(String...args) throws Exception {
	
//----FOR TESTING PURPOSES------------------------------		
//		AppUser newUser = new AppUser("ejzhang", "eve", "zhang", "pw", "ejzhang@usc.edu");
////		AppUser newUser2 = new AppUser("ejzhang", "anika", "zhang", "pw", "anikag@usc.edu");
////		this.userRepository.save(newUser);
////		this.userRepository.save(newUser2);
//		System.out.println("send intro email: ");
//		service.sendIntroEmail(newUser);
//
//
//		//System.out.println("this is anika's new id" + newUser.getId());
//		AddPlantForm plantToAdd = new AddPlantForm("fern", newUser.getId());
//		AddPlantForm plantToAdd2 = new AddPlantForm("philodendron", newUser2.getId());

		
//		this.plantRepository.save(new PlantModel("Green Onion", 5, 1, 10102022, newUser));
		int dayCounter = 1;
		while(true) {
			//TIMING WATERING PLANTS
//			if (System.currentTimeMillis() >= startTime + 24 * 60 * 60 * 1000) {
//			addPlantFromFrontendTest(plantToAdd);
//			addPlantFromFrontendTest(plantToAdd2); TESTING PURPOSES
			TimeUnit timeUnit = TimeUnit.DAYS;
			timeUnit.sleep(1);
			
			if (System.currentTimeMillis() >= startTime + 24 * 60 * 60 * 1000) {
//			if (System.currentTimeMillis() >= startTime + 10000*dayCounter) {
				dayCounter++;
				
				List<AppUser> allUsers = userRepository.findAll();
				for (int i = 0; i < allUsers.size(); i++) {
					AppUser currUser = allUsers.get(i);
					List<PlantModel> userPlants = currUser.getPlantList();
					for (int j = 0; j < userPlants.size(); j++) {
						PlantModel currPlant = userPlants.get(j);
						if (currPlant.getNumOwned() > 0) {
							currPlant.setDaysSinceWatering(currPlant.getDaysSinceWatering()+1);
						}
					}
					
//					List<PlantModel> plantsToWater = currUser.needsWatering();
//					String needingWater = "";
//					for (int j = 0; j < plantsToWater.size(); j++) {
//						PlantModel currPlant = userPlants.get(j);
//						needingWater += currPlant.getPlantName() + "     ";
//					}
					//NOTE : all email sending methods moved to EmailSenderService.java
					service.sendEmail(currUser);
				}
//				startTime = startTime - 10000;
			}

		}
		
	}
	//----------FOR TESTING PURPOSES: ORIGINAL FUNCTION IS IN USERCONTROLLER.JAVA-------------
	
	public String addPlantFromFrontendTest(AddPlantForm addRequest) {
			//FOR TESTING PURPOSES
			//Long idTesterLong = (long) 1; 
			addRequest.getUserId();
			List<AppUser> findUsers = userRepository.findByUserid(addRequest.getUserId());
			System.out.println("these are the users found" + findUsers);
			
			AppUser currAppUser;
			
			
			if (findUsers.isEmpty() == false) {
				currAppUser =findUsers.get(0);
				currAppUser.addPlantUpdateString(addRequest.getPlantType());
				userRepository.save(currAppUser);
				System.out.println("user could not be found by id");
				return "Successfully updated user";
			}
			else {
				
				System.out.println("user could not be found by id");
				return "user could not be found by id";
	
			}
			
		}

		   

    

}	
