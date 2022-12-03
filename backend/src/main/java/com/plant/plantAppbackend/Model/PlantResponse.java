package com.plant.plantAppbackend.Model;

public class PlantResponse {
	private String plantType;
	private int quantity;
	private Boolean needsWatering;
	
	public String getPlantType() {
		return plantType;
	}
	public void setPlantType(String plantType) {
		this.plantType = plantType;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	public Boolean getNeedsWatering() {
		return needsWatering;
	}
	public void setNeedsWatering(Boolean needsWatering) {
		this.needsWatering = needsWatering;
	}
	public PlantResponse(String plantType, int quantity, Boolean needsWatering) {
		this.plantType = plantType;
		this.quantity = quantity;
		this.needsWatering = needsWatering;
	}
	@Override
	public String toString() {
		return "PlantResponse [plantType=" + plantType + ", quantity=" + quantity + ", needsWatering=" + needsWatering
				+ "]";
	}
	
	
	

}
