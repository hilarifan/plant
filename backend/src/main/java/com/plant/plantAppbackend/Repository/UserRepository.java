package com.plant.plantAppbackend.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plant.plantAppbackend.Model.AppUser;
import com.plant.plantAppbackend.controller.EmailSenderService;

/** 
 * 
 * INFORMATION ABOUT THIS FILE: extending JPARepository gives ability 
 * 
 * **note: a lot of documentation online does "extend crudRepository" instead for the CRUD functions. 
 * but extending JPARepository includes crudRepository and other additional functions
 * this repostiory has pre-defined common CRUD methods that correspond with methods like Post, Get, etc.
 * Common methods: save(), findbyID(), etc. Using this repository, we don't have to write the rudimentary methods
 * and we only have to define the ones specific to our project
 * 
 * Allows to get and set from our database
 * 
 * HOW TO USE: this repository will define our project specific methods. Then, these methods will further 
 * be defined in the "service" class (look at example 1-B in link below) The Controller then calls 
 * the methods in service. 
 * 
 * Overall: Controller --> service --> repostiory
 * 
 * https://www.geeksforgeeks.org/spring-boot-crudrepository-with-example/
 */



@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

	
	
	List<AppUser> findAll();
	
	List<AppUser> findByUsername(String username);
	List<AppUser> findByUserid(Long id);
	


}
