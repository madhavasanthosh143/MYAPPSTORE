package com.example.ecommerce.Appstore.DTO;

public class LoginRequestDTO {
    private String userName;
    private String password;

    // Default constructor
    public LoginRequestDTO() {
    }

    // Constructor with parameters
    public LoginRequestDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    // Getters and setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
