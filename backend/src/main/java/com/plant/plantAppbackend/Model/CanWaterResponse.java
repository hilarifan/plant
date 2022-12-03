package com.plant.plantAppbackend.Model;

public class CanWaterResponse {
	
	private String canWater;

	public String getCanWater() {
		return canWater;
	}

	public void setCanWater(String canWater) {
		this.canWater = canWater;
	}

	public CanWaterResponse(String canWater) {
		this.canWater = canWater;
	}

	@Override
	public String toString() {
		return "CanWaterResponse [canWater=" + canWater + "]";
	}

	
}
	