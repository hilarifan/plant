package com.plant.plantAppbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plant.plantAppbackend.Model.AppUser;
import com.plant.plantAppbackend.Model.PlantModel;
import com.plant.plantAppbackend.Repository.PlantRepository;
import com.plant.plantAppbackend.Repository.UserRepository;



//------------PURPOSE: MAPPING ALL THE HTTP METHODS-------

@RestController
//@RequestMapping("")
@CrossOrigin("http://localhost:3000")
public class PlantController {
	@Autowired
	private PlantRepository plantRepository;
	
	//@PostMapping("/addPlant")
	PlantModel addPlant(@RequestBody PlantModel newPlant) {
		return plantRepository.save(newPlant);
	}
	
	//@GetMapping("/getPlant")
	public List<PlantModel> getPlant(){
		return this.plantRepository.findAll();
	}
	public PlantController() {
		// TODO Auto-generated constructor stub
	}
	
	
	

}