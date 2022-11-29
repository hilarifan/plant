package com.plant.plantAppbackend.Model;

import java.util.Dictionary;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
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

//import org.json.simple.JSONObject;

import com.plant.plantAppbackend.Repository.PlantRepository;
import com.plant.plantAppbackend.Repository.UserRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;


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

public class AppUser  {
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
	
	@Column(name = "plantJSONList" , length = 2000)
	private String plantListJson;
//	private String philodendron;
//	private String fern;
//	private String aloevera;
//	private String greenonion;
//	public List<JSONObject> plantJsonList;
	
	@OneToMany(mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
	private Set<PlantModel> plants;
	
	
	//private List<PlantModel> plants;  // store in a json 
	
	
	public AppUser() {
		super();
		this.plantListJson = "[{\"Name\":\"Philodendron\",\"Quantity\":\"0\",\"WateringFrequency\":\"10\",\"DaysSinceWatering\":\"0\"},"
				+ "{\"Name\":\"AloeVera\",\"Quantity\":\"0\",\"WateringFrequency\":\"21\",\"DaysSinceWatering\":\"0\"},"
				+ "{\"Name\":\"Fern\",\"Quantity\":\"0\",\"WateringFrequency\":\"2\",\"DaysSinceWatering\":\"0\"},"
				+ "{\"Name\":\"GreenOnion\",\"Quantity\":\"0\",\"WateringFrequency\":\"14\",\"DaysSinceWatering\":\"0\"}]";
	}
	
	public AppUser( String username, String firstName, String lastName, String password, String email) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
//		this.philodendron = "{\"Name\":\"Philodendron\",\"Quantity\":\"0\",\"WateringFrequency\":\"10\",\"DaysSinceWatering\":\"0\"}"; //flag
//		this.aloevera = "{\"Name\":\"AloeVera\",\"Quantity\":\"0\",\"WateringFrequency\":\"21\",\"DaysSinceWatering\":\"0\"}";
//		this.fern = "{\"Name\":\"Fern\",\"Quantity\":\"0\",\"WateringFrequency\":\"2\",\"DaysSinceWatering\":\"0\"}";
//		this.greenonion = "{\"Name\":\"GreenOnion\",\"Quantity\":\"0\",\"WateringFrequency\":\"14\",\"DaysSinceWatering\":\"0\"}";
		this.plantListJson = "[{\"Name\":\"Philodendron\",\"Quantity\":\"0\",\"WateringFrequency\":\"10\",\"DaysSinceWatering\":\"0\"},"
				+ "{\"Name\":\"AloeVera\",\"Quantity\":\"0\",\"WateringFrequency\":\"21\",\"DaysSinceWatering\":\"0\"},"
				+ "{\"Name\":\"Fern\",\"Quantity\":\"0\",\"WateringFrequency\":\"2\",\"DaysSinceWatering\":\"0\"},"
				+ "{\"Name\":\"GreenOnion\",\"Quantity\":\"0\",\"WateringFrequency\":\"14\",\"DaysSinceWatering\":\"0\"}]";
				
		//everyone starts with no plants --> hashMap with all types of plnats and list[1] = 0 (for numOwned of plantType)
		//---------------NEED TO SET DEFAULT PLANT---------------------------

		//this.plants = plantRepository.findAll(); 
		//JSONObject plantJson = JSON.parse(plantList);
		
	}
	
	public List<PlantModel> getPlantList() {
		List<PlantModel> allPlants = new ArrayList<PlantModel>();
		for (int i = 0; i < plantListJson.length(); i++) {
			if (plantListJson.charAt(i) == '{') {
				String plantName = "";
				String plantQuantity = "";
				String wateringFreq = "";
				String daysSince = "";
				int currChar = i+9;
				while (plantListJson.charAt(currChar) != '"') { //first instance of name is 9 characters after the {
					plantName += plantListJson.charAt(currChar);
				}
				
				currChar = currChar + 14; //first instance of quantity is 14 characters after
				while (plantListJson.charAt(currChar) != '"') {
					plantQuantity += plantListJson.charAt(currChar);
				}
				
				currChar = currChar + 23;
				while (plantListJson.charAt(currChar) != '"') {
					wateringFreq += plantListJson.charAt(currChar);
				}
				
				currChar = currChar + 23;
				while (plantListJson.charAt(currChar) != '"') {
					daysSince += plantListJson.charAt(currChar);
				}
				
				PlantModel nextPlant = new PlantModel
						(plantName, Integer.parseInt(wateringFreq), Integer.parseInt(plantQuantity), Integer.parseInt(daysSince), this);
				allPlants.add(nextPlant);
			}
		}
		
		return allPlants;
	}
	
	public String jsonToString(JSONObject obj) {
		String res = obj.toString();
		return res;
	}

//	public void jsonToMap() {
//		plantJsonList.clear();
//		
//		JSONObject philo = JSON.parse(philodendron);
//		JSONObject aloe = JSON.parse(aloevera);
//		JSONObject fer = JSON.parse(fern);
//		JSONObject greeno = JSON.parse(greeno);
//		
//		plantJsonList.add(philo);
//		plantJsonList.add(aloe);
//		plantJsonList.add(fer);
//		plantJsonList.add(greeno);
//		
//		
//		//JsonObject jsonObject = (JsonObject) jsonParser.parse(jsonString)
//	}
	
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
	
//	public JSONObject getPlants() {
//		return stringToJson();
//	}
	
//	public void nextDay() {
//		
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
		List<PlantModel> allPlants = getPlantList();
//		
		for (int i = 0; i < allPlants.size(); i++) {
//			JSONObject obj = plantJson.get(i);
			if (allPlants.get(i).getPlantName() == "plantType") {
				int currNum = allPlants.get(i).getNumOwned();
				allPlants.get(i).setNumOwned(currNum + 1);
				break;
			}
		}
	}
	
	public void removePlant(String plantType) {//private String plantName; private int wateringFrequency, numOwned, startDate
		// --------------to implement -----------------------------
		
		List<PlantModel> allPlants = getPlantList();
//		
		for (int i = 0; i < allPlants.size(); i++) {
//			JSONObject obj = plantJson.get(i);
			if (allPlants.get(i).getPlantName() == "plantType") {
				int currNum = allPlants.get(i).getNumOwned();
				if (currNum == 0) {
					//some exception here?
				}
				allPlants.get(i).setNumOwned(currNum - 1);
				break;
			}
		}
		
	}
	
	public List<PlantModel> needsWatering() {
		// --------------to implement====================
		//---> TRAVERSE THE LIST AND RETURN A LIST OF PLANTS THAT NEED WATERING
		
		
		List<PlantModel> allPlants = getPlantList();
		List<PlantModel> plantsToWater = new ArrayList<PlantModel>();
//		
		for (int i = 0; i < allPlants.size(); i++) {
//			JSONObject obj = plantJson.get(i);
			if (allPlants.get(i).getDaysSinceWatering() >= allPlants.get(i).getWateringFrequency()) {
				plantsToWater.add(allPlants.get(i));
				//reset daysSinceWatering to 0 depending on whether the user waters their plant or not
			}
		}
		
		return plantsToWater;
		
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
