package com.plant.plantAppbackend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/** 
 * 
 * 
 * @author eveyzhang
 * Controller class: Controls overall workflow of RESTAPI requests for get/set and returns a reponse
 *https://stackabuse.com/controller-and-restcontroller-annotations-in-spring-boot/
 */
@RestController
public class SingupController {
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String getMessage() {
		return "Hey! it's working";
	}
	
	
	

}