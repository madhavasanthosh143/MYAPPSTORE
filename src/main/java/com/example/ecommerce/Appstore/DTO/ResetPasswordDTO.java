package com.example.ecommerce.Appstore.DTO;

public class ResetPasswordDTO {

	private String userName;
	private String newPassword;
	
	
	public ResetPasswordDTO(String userName, String newPassword) {
		super();
		this.userName = userName;
		this.newPassword = newPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	

}
