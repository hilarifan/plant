package com.plant.plantAppbackend.Model;

public class PlantResponse {
	private String plantType;
	private int quantity;
	private String needsWatering;
	
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
	
	
	public String getNeedsWatering() {
		return needsWatering;
	}
	public void setNeedsWatering(String needsWatering) {
		this.needsWatering = needsWatering;
	}
	public PlantResponse(String plantType, int quantity, String needsWatering) {
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
