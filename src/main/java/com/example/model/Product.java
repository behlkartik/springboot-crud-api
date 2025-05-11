package com.example.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Product {
	@NotBlank(message = "Product name can't be blank")
	String name;
	@Min(message = "Price can't be less than 0", value = 0l)
	Double price;
	long id;
	static int numOfProducts = 0;
	
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public void setId(long id) {
		this.id = ++numOfProducts;
	}

	public Product(String name, Double price) {
		this.name = name;
		this.price = price;
		this.id = ++numOfProducts;
	}
	public String getName() {
		return name;
	}
	public Double getPrice() {
		return price;
	}
	public long getId() {
		return id;
	}
}
