package com.plant.plantAppbackend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.plant.plantAppbackend.Model.AppUser;
import com.plant.plantAppbackend.Model.PlantModel;
import com.plant.plantAppbackend.Repository.PlantRepository;
import com.plant.plantAppbackend.Repository.UserRepository;
import com.plant.plantAppbackend.Service.EmailSenderService;

@SpringBootApplication

public class PlantAppBackendApplication implements CommandLineRunner{
	public static long startTime = System.currentTimeMillis();

	public static void main(String[] args) {
		
		SpringApplication.run(PlantAppBackendApplication.class, args);
			
		
	}
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PlantRepository plantRepository;
	@Autowired
	private EmailSenderService service;
	
	
	@Override 
	public void run(String...args) throws Exception {
	
//		userRepository.sendIntroEmail(newUser);
		while(true) {
			//TIMING WATERING PLANTS
			if (System.currentTimeMillis() >= startTime + 24 * 60 * 60 * 1000) {
	
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
					
					List<PlantModel> plantsToWater = currUser.needsWatering();
					String needingWater = "";
					for (int j = 0; j < plantsToWater.size(); j++) {
						PlantModel currPlant = userPlants.get(j);
						needingWater += currPlant.getPlantName() + "     ";
					}
					//NOTE : all email sending methods moved to EmailSenderService.java
					service.sendEmail(currUser, needingWater);
				}
				startTime = startTime - System.currentTimeMillis();
			}

		}
		
		//INSERT INTO DATABASE TEST CODE
//		this.userRepository.save(new AppUser("Agupta", "Anika", "Gupta", "myPassword", "ejzhang@usc.edu"));
//		AppUser newUser = new AppUser("AShen", "Andy", "Shen", "myPassword", "anikag@usc.edu");
//		this.userRepository.save(newUser);
//		this.plantRepository.save(new PlantModel("Green Onion", 5, 1, 10102022, newUser));
		
	}
	


		   

    

}	
