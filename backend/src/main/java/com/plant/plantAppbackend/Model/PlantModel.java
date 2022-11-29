package com.plant.plantAppbackend.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity; //@Entity annotation specifies that the class is an entity and is mapped to a database table.
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/** 
 * 
 * 
 * 
 * INFORMATION ABOUT THIS FILE: in this file we define the appUser object which models a 
 * plant user. @Entity tage automatically creates a database (connected to plantData schema) 
 * where a table of "users" are generateed with the columns of id, username, firstName, LastName,
 * password, and email. ID is used as the primary key.
 *
 */

@Entity
@Table(name = "PlantData")
public class PlantModel implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String plantName;
	
	@Column(name = "watering_Frequency")
	private int wateringFrequency;

	@Column(name = "plant_Index")
	private int numOwned;
	
	@Column(name = "daysSinceWatering")
	private int daysSinceWatering;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private AppUser appUser;
	
	public PlantModel() {
		super();
	}

	public PlantModel(String plantName, int wateringFrequency, int numOwned, int daysSinceWatering, AppUser ap) {
		super();
		this.plantName = plantName;
		this.wateringFrequency = wateringFrequency;
		this.numOwned = numOwned;
		this.daysSinceWatering = daysSinceWatering;
		this.appUser = ap;
	}
	//-----------GET SET METHODS----------------------------------------

	public String getPlantName() {
		return plantName;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}

	public int getWateringFrequency() {
		return wateringFrequency;
	}

	public void setWateringFrequency(int wateringFrequency) {
		this.wateringFrequency = wateringFrequency;
	}

	public int getNumOwned() {
		return numOwned;
	}

	public void setNumOwned(int numOwned) {
		this.numOwned = numOwned;
	}

	public int getDaysSinceWatering() {
		return daysSinceWatering;
	}

	public void setDaysSinceWatering(int daysSinceWatering) {
		this.daysSinceWatering = daysSinceWatering;
	}

	@Override
	public String toString() {
		return "PlantModel [plantName=" + plantName + ", wateringFrequency=" + wateringFrequency + ", numOwned="
				+ numOwned + ", daysSinceWatering=" + daysSinceWatering + "]";
	}
	

}
