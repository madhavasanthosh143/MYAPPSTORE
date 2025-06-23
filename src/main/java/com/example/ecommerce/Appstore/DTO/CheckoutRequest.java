package com.example.ecommerce.Appstore.DTO;

import java.util.List;

import com.example.ecommerce.Appstore.model.CartItem;

public class CheckoutRequest {
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private String addressLine;
    private String city;
    private String pincode;
    private String paymentMode;
    private List<CartItem> items;
    
	public CheckoutRequest(String customerName, String customerEmail, String customerPhone, String addressLine,
			String city, String pincode, String paymentMode, List<CartItem> items) {
		super();
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerPhone = customerPhone;
		this.addressLine = addressLine;
		this.city = city;
		this.pincode = pincode;
		this.paymentMode = paymentMode;
		this.items = items;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getAddressLine() {
		return addressLine;
	}
	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public List<CartItem> getItems() {
		return items;
	}
	public void setItems(List<CartItem> items) {
		this.items = items;
	}

    // Getters and setters
}
