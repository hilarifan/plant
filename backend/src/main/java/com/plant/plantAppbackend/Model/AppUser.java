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
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;


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
@Table(name = "user_table", schema = "plantData")
@EnableAsync
@EnableAutoConfiguration
public class AppUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Userid", nullable = true, unique = true)
	private long userid; 
	
	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "firstname", nullable = false)
	private String firstName;

	@Column(name = "lastname", nullable = false)
	private String lastName;

	@Column(name = "password", nullable = false)
	private String password; 

	@Column(name = "email", nullable = false)
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
//		this.plantListJson = "{\"plants\""
//				+ ": [{\"Name\":\"Philodendron\",\"Quantity\":\"0\",\"WateringFrequency\":\"10\",\"DaysSinceWatering\":\"0\"},"
//				+ "{\"Name\":\"Aloe Vera\",\"Quantity\":\"0\",\"WateringFrequency\":\"21\",\"DaysSinceWatering\":\"0\"},"
//				+ "{\"Name\":\"Fern\",\"Quantity\":\"0\",\"WateringFrequency\":\"2\",\"DaysSinceWatering\":\"0\"},"
//				+ "{\"Name\":\"Green Onion\",\"Quantity\":\"0\",\"WateringFrequency\":\"14\",\"DaysSinceWatering\":\"0\"}]}";
		this.plantListJson = "[{\"Name\":\"Philodendron\",\"Quantity\":\"0\",\"WateringFrequency\":\"10\",\"DaysSinceWatering\":\"0\"},"
				+ "{\"Name\":\"Aloe Vera\",\"Quantity\":\"0\",\"WateringFrequency\":\"21\",\"DaysSinceWatering\":\"0\"},"
				+ "{\"Name\":\"Fern\",\"Quantity\":\"0\",\"WateringFrequency\":\"2\",\"DaysSinceWatering\":\"0\"},"
				+ "{\"Name\":\"Green Onion\",\"Quantity\":\"0\",\"WateringFrequency\":\"14\",\"DaysSinceWatering\":\"0\"}]";
				
	}
	
	//@Async("threadPoolTaskExecutor")
	public AppUser( String username, String firstName, String lastName, String password, String email) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;

		this.plantListJson = "[{\"Name\":\"Philodendron\",\"Quantity\":\"0\",\"WateringFrequency\":\"10\",\"DaysSinceWatering\":\"0\"},"
				+ "{\"Name\":\"Aloe Vera\",\"Quantity\":\"0\",\"WateringFrequency\":\"21\",\"DaysSinceWatering\":\"0\"},"
				+ "{\"Name\":\"Fern\",\"Quantity\":\"0\",\"WateringFrequency\":\"2\",\"DaysSinceWatering\":\"0\"},"
				+ "{\"Name\":\"Green Onion\",\"Quantity\":\"0\",\"WateringFrequency\":\"14\",\"DaysSinceWatering\":\"0\"}]";
				
		//everyone starts with no plants --> hashMap with all types of plnats and list[1] = 0 (for numOwned of plantType)
		//---------------NEED TO SET DEFAULT PLANT---------------------------
		
	}
	
	@Async
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
				
					currChar++;
				}
				
				currChar = currChar + 14; //first instance of quantity is 14 characters after
				while (plantListJson.charAt(currChar) != '"') {
					plantQuantity += plantListJson.charAt(currChar);
					currChar++;
				}
				
				currChar = currChar + 23;
				while (plantListJson.charAt(currChar) != '"') {
					wateringFreq += plantListJson.charAt(currChar);
					currChar++;
				}
				
				currChar = currChar + 23;
				while (plantListJson.charAt(currChar) != '"') {
					daysSince += plantListJson.charAt(currChar);
					currChar++;
				}
				
				PlantModel nextPlant = new PlantModel
						(plantName, Integer.parseInt(wateringFreq), Integer.parseInt(plantQuantity), Integer.parseInt(daysSince), this);
				allPlants.add(nextPlant);
			}
		}
		
		return allPlants;
	}
	
	

	
	//-----------GET SET METHODS----------------------------------------
	public long getId() {
		return userid;
	}
	public void setId(long id) {
		this.userid = id;
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
	
	public String getPlantListJson() {
		return plantListJson;
	}
	public void setPlantListJson(String plantListJson) {
		this.plantListJson = plantListJson;
	}

	//--------------LOGIN INFORMATION ------------------------
	

	public Boolean psswdValidation(String pRequest) {
//		System.out.println("this is the password " + getPassword());
//		System.out.println("this is the inputted password " + pRequest);
		if (getPassword().equals(pRequest)){
			return true;
		}
		return false;
	}
	@Async
	public void listToJSONString(List<PlantModel> allPlants) {
		String newJson = "[";
		for (int i = 0; i < allPlants.size(); i++) {
			PlantModel curr = allPlants.get(i);
			String newElement = "{" + '"' + "Name" + '"' + ":" + '"' + curr.getPlantName() + '"' + "," 
					+ '"' + "Quantity" + '"' + ":" + '"' + curr.getNumOwned() + '"' + "," 
					+ '"' + "WateringFrequency" + '"' + ":" + '"' + curr.getWateringFrequency() + '"' + "," 
					+ '"' + "DaysSinceWatering" + '"' + ":" + '"' + curr.getDaysSinceWatering() + '"' + '}';

			if (i < 3) {
				newElement += ",";
			}
			newJson += newElement;
			
		}
		newJson += "]";
		
		this.setPlantListJson(newJson);
		System.out.println("This should be the udpated plantlistjson! " + this.getPlantListJson());
//		plantListJson = newJson;
	}
	//------------------CONNECTS TO WATER.JS-------------
	//--> GOES THORUGH LIST AND AND CHECKS IF PLANTTYPE NEEDS TO BE WATERED-----
	
	@Async
	public CanWaterResponse doesUserWaterPlants(String plant) {
		List<PlantModel> plantsToWater = needsWatering();
				
		if (plantsToWater.size() == 0) {
			return new CanWaterResponse("no");
		}
		
		for (int i = 0; i < plantsToWater.size(); i++) {
			PlantModel currPlant = plantsToWater.get(i);
			if (currPlant.getPlantName().toLowerCase().equals(plant.toLowerCase())) {
				return new CanWaterResponse("yes");
			}
		}
		
		return new CanWaterResponse("no");
	}
	
	
	@Async
	public void addPlantUpdateString(String plantType) {//private String plantName; private int wateringFrequency, numOwned, startDate
		//contains all types of plants: list[1] = 0 if the user does not have that type of plant
		
		System.out.println("starting to add Plant of " + plantType + " type");
		List<PlantModel> allPlants = getPlantList();
		System.out.println("this is \"allPlants: \""+ allPlants);
		for (int i = 0; i < allPlants.size(); i++) {
//			JSONObject obj = plantJson.get(i);
			System.out.println("The plantName is " + allPlants.get(i).getPlantName());
			
			System.out.println("numowned plants: " + allPlants.get(i).getNumOwned());
			String plant1 = allPlants.get(i).getPlantName().toLowerCase();
			String plant2 = plantType.toLowerCase();
			System.out.println("Plant 1: " + plant1);
			System.out.println("Plant 2: " + plant2);

			if (plant1.equals(plant2)) { //lowercase flag
				int currNum = allPlants.get(i).getNumOwned() + 1;
				
				System.out.println("this is curr: " + currNum);
				allPlants.get(i).setNumOwned(currNum);
				
				System.out.println(allPlants.get(i).toString());
				System.out.println("The currentNumber is " +allPlants.get(i).getNumOwned());
				break;
			}
		}
		listToJSONString(allPlants);
		System.out.println("Plant should be added now!" );

	}
	
	@Async
	public void removePlant(String plantType) {//private String plantName; private int wateringFrequency, numOwned, startDate
		// --------------to implement -----------------------------
		
		List<PlantModel> allPlants = getPlantList();
//		
		for (int i = 0; i < allPlants.size(); i++) {
//			JSONObject obj = plantJson.get(i);
			if (allPlants.get(i).getPlantName().toLowerCase().equals(plantType.toLowerCase())) {
				int currNum = allPlants.get(i).getNumOwned();
				if (currNum == (int)0) {
					return;
				}
				int newNum = (int)currNum - (int)1;
				allPlants.get(i).setNumOwned((int)newNum);
				break;
			}
		}
		listToJSONString(allPlants);
		
	}
	
	@Async
	public List<PlantModel> needsWatering() {
		// --------------to implement====================
		//---> TRAVERSE THE LIST AND RETURN A LIST OF PLANTS THAT NEED WATERING
		
		
		List<PlantModel> allPlants = getPlantList();
		List<PlantModel> plantsToWater = new ArrayList<PlantModel>();
//		
		for (int i = 0; i < allPlants.size(); i++) {
//			JSONObject obj = plantJson.get(i);
			PlantModel curr = allPlants.get(i);
			System.out.println("GET DAYS SINCE WATERING " + curr.getDaysSinceWatering());
			System.out.println("GET WATERING FREQUENCY " + curr.getWateringFrequency());
			if (curr.getDaysSinceWatering() >= curr.getWateringFrequency()) {
				plantsToWater.add(curr);
				curr.setDaysSinceWatering(0);
			}
		}
		
		return plantsToWater;
		
	}
	
	@Override
	public String toString() {
		return "AppUser [id=" + userid + ", "
				+ "username=" + username 
				+ ", firstName=" + firstName 
				+ ", lastName=" + lastName
				+ ", password=" + password 
				+ ", email=" + email + "]";
	}
	
}
