package com.plant.plantAppbackend.Model;

public class AddPlantForm {
	private String plantType;
	private Long userId;
	public String getPlantType() {
		return plantType;
	}
	public void setPlantType(String plantType) {
		this.plantType = plantType;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public AddPlantForm(String plantType, Long userId) {
		super();
		this.plantType = plantType;
		this.userId = userId;
	}
	
}
