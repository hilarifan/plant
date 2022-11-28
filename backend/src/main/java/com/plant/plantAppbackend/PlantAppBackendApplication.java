package com.plant.plantAppbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.plant.plantAppbackend.Model.AppUser;
import com.plant.plantAppbackend.Model.PlantModel;
import com.plant.plantAppbackend.Repository.PlantRepository;
import com.plant.plantAppbackend.Repository.UserRepository;

@SpringBootApplication

public class PlantAppBackendApplication implements CommandLineRunner{
	
	public static void main(String[] args) {
		SpringApplication.run(PlantAppBackendApplication.class, args);
		
	}
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PlantRepository plantRepository;
	
	@Override 
	public void run(String...args) throws Exception{
		//INSERT INTO DATABASE
		this.userRepository.save(new AppUser("Agupta", "Anika", "Gupta", "myPassword", "ejzhang@usc.edu"));
		AppUser newUser = new AppUser("AShen", "Andy", "Shen", "myPassword", "anikag@usc.edu");
		this.userRepository.save(newUser);
		this.plantRepository.save(new PlantModel("Green Onion", 5, 1, 10102022, newUser));
	}

}
