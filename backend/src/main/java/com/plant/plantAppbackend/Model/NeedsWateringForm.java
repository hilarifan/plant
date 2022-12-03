package com.plant.plantAppbackend.Model;

public class NeedsWateringForm {

	private Long userId;
	private String plantType;
	private String needsWatering;

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
	public String getNeedsWatering() {
		return needsWatering;
	}
	public void setNeedsWatering(String needsWatering) {
		this.needsWatering = needsWatering;
	}
	
	public NeedsWateringForm(String plantType, Long userId) {
		super();
		this.plantType = plantType;
		this.userId = userId;
		this.needsWatering = needsWatering;
	}

		
}