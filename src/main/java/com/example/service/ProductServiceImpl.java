package com.example.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.model.Product;

@Component
public class ProductServiceImpl implements IProduct {
	List<Product> products = new ArrayList<>(Arrays.asList(
			new Product("iphone 17", 75000d), 
			new Product("s25 ultra", 150000d),
			new Product("machboook m4 256gb", 250000d),
			new Product("galaxy watch ultra", 80000d)
	));

	@Override
	public List<Product> allProducts() {
		return products;
	}
	

}
