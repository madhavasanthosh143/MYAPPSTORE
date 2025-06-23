package com.example.ecommerce.Appstore.DTO;

public class OtpVerifyDTO {
    private String userName;
    private String otp;

    // Default constructor
    public OtpVerifyDTO() {
    }

    // Constructor with parameters
    public OtpVerifyDTO(String userName, String otp) {
        this.userName = userName;
        this.otp = otp;
    }

    // Getters and setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
