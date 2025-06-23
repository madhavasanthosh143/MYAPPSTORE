package com.example.ecommerce.Appstore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
 
@Entity
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Username is required.")
    @Size(min = 1, max = 20, message = "Username must be between 1 and 20 characters.")
    private String userName;
    @NotBlank(message = "Password is required.")
    @Size(min = 7, max = 100, message = "Password must be between 7 and 100 characters.") // changed max to 100
    private String password;

    public boolean isVerified() {
		return Verified;
	}
	public void setVerified(boolean verified) {
		Verified = verified;
	}
	@Email(message = "Invalid email format.")
    private String email;
    private String phonenumber;
    private boolean Verified;
    @Column(name = "otp") 
    private String otp;
    
    


	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public User() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
	    this.userName = userName;
	}

	public String getPassword() {
		return password;
	}
	 
	public User(Long id, String userName, String password,String email) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email=email;
	}
	public void setPassword(String password) {
		this.password = password;
	}

    // getters and setters
}



