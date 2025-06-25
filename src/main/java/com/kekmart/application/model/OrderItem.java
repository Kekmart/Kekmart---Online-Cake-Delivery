package com.kekmart.application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private double price;
	private int quantity;
	@ManyToOne
	private Product product;
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double d) {
		this.price = d;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void setProduct(Product product) {
		this.product=product;
	}
	public Product getProduct() {
		return this.product;
	}
	
}
