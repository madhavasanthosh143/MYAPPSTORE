package com.example.ecommerce.Appstore.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
	 private String paymentMode;
	    private List<CartItem> items;	

}
