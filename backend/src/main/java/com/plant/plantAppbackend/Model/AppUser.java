package com.plant.plantAppbackend.Model;

import java.util.Dictionary;
import java.util.*;

import java.util.List;

import javax.management.loading.PrivateClassLoader;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity; //@Entity annotation specifies that the class is an entity and is mapped to a database table.
import javax.persistence.ExcludeDefaultListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.plant.plantAppbackend.Repository.PlantRepository;
import com.plant.plantAppbackend.Repository.UserRepository;

/** 
 * \
 * 
 * 
 * INFORMATION ABOUT THIS FILE: in this file we define the appUser object which models a 
 * plant user. @Entity tage automatically creates a database (connected to plantData schema) 
 * where a table of "users" are generateed with the columns of id, username, firstName, LastName,
 * password, and email. ID is used as the primary key.
 *
 */

@Entity
@Table(name = "user_table")

public class AppUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long user_id; 
	
	@Column(name = "default_username")
	private String username;

	@Column(name = "default_firstname")
	private String firstName;

	@Column(name = "default_lastname")
	private String lastName;

	private String password; 

	private String email;
	
	private String plantList;
	
	@OneToMany(mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
	private Set<PlantModel> plants;
	
	
	//private List<PlantModel> plants;  // store in a json 
	
	
	public AppUser() {
		super();
	}
	
	public AppUser( String username, String firstName, String lastName, String password, String email) {
	
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.plantList = "[\n"
				+ "  {\n"
				+ "    \"Name\": \"Philodendron\",\n"
				+ "    \"Quantity\": \"0\",\n"
				+ "    \"Watering Frequency\": \"10\"\n"
				+ "    \"Day\": \"0\"\n"
				+ "  },\n"
				+ "  {\n"
				+ "    \"Name\": \"Aloe Vera\",\n"
				+ "    \"Quantity\": \"0\",\n"
				+ "    \"Watering Frequency\": \"21\"\n"
				+ "    \"Day\": \"0\"\n"
				+ "  }\n";
		//everyone starts with no plants --> hashMap with all types of plnats and list[1] = 0 (for numOwned of plantType)
		//---------------NEED TO SET DEFAULT PLANT---------------------------
		
		//this.plants = plantRepository.findAll(); 
		
		
	}

	public void jsonToMap(String json) {
		JSONObject obj = new JSONObject(json);
		//added dependency to pom.xml
	}
	//-----------GET SET METHODS----------------------------------------
	public long getId() {
		return user_id;
	}
	public void setId(long id) {
		this.user_id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
//	public List<PlantModel> getPlants() {
//		return plants;
//	}
//	
//	public boolean containsPlantType(String plantType) {
//	
//		for (PlantModel p: plants) {
//			if(p.getPlantName().equals(plantType)) {
//				return true;
//			}
//		}
//		return false;
//	}
//	
//	public boolean getPlantByType(String plantType) {
//		
//		for (PlantModel p: plants) {
//			if(p.getPlantName().equals(plantType)) {
//				return true;
//			}
//		}
//		return false;
//	}
//	
	public void addPlant(String plantType) {//private String plantName; private int wateringFrequency, numOwned, startDate
		//contains all types of plants: list[1] = 0 if the user does not have that type of plant
		
		//if plantType exists: increement the count
//		if (containsPlantType(plantType)) {
//			updateList.set(1, updateList.get(1) + 1); //increments index 1 in array by 1
//			
//			plants.put(plantType, updateList);
//		}
//		
		
	}
	
	public void removePlant(String plantType) {//private String plantName; private int wateringFrequency, numOwned, startDate
		// --------------to implement -----------------------------
		
	}
	
	public boolean needsWatering() {
		// --------------to implement====================
		//---> TRAVERSE THE LIST AND RETURN A LIST OF PLANTS THAT NEED WATERING
		return false;
		
	}
	
	@Override
	public String toString() {
		return "AppUser [id=" + user_id + ", "
				+ "username=" + username 
				+ ", firstName=" + firstName 
				+ ", lastName=" + lastName
				+ ", password=" + password 
				+ ", email=" + email + "]";
	}
	
	
	

}
