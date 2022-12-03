package com.plant.plantAppbackend.Model;

public class RemovePlantForm {
	private Long userId;
	private String plantType;

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
	public RemovePlantForm(String plantType, Long userId) {
		super();
		this.plantType = plantType;
		this.userId = userId;
	}
	
}