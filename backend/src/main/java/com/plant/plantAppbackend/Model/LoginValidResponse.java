package com.plant.plantAppbackend.Model;

public class LoginValidResponse {
	
	private String error;
	private String errorMsg;
	private Long id;
	
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public LoginValidResponse(String error, String errorMsg , Long id) {
		this.error = error;
		this.errorMsg = errorMsg;
		this.id = id;
		
	}
	@Override
	public String toString() {
		return "LoginValidResponse [error=" + error + ", id=" + id + "]";
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	} 
	
}
