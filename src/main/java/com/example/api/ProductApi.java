package com.example.api;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.ProductNotFoundException;
import com.example.model.Product;
import com.example.model.Status;
import com.example.service.ProductServiceImpl;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;


/*
 * 
 * @RestController = @Controller + @ResponseBody i.e. if used you don't need to mention @ResponseBody
 * on every response. 
 * 
 * 
 * */

@Controller
@RequestMapping("/products")
public class ProductApi {
	
	@Autowired
	ProductServiceImpl service;
	
	@GetMapping("/status")
	@ResponseBody
	public Status status() {
		Status apiStatus = new Status();
		apiStatus.setInfo("running");
		return apiStatus;
				
	}
	
	
	@GetMapping(value = "/sample", produces = {"application/json", "application/xml"})
	@ResponseBody
	public Product sampleProduct() { 
		return new Product("Iphone 16", 75000.0d);
	}
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Product>> getProducts() {
		return new ResponseEntity<List<Product>>(service.allProducts(), HttpStatus.OK) ;
	}
	
	@GetMapping("/{prodId}")
	@ResponseBody
	public ResponseEntity<Product> getProduct(@PathVariable("prodId") long id) {
		Optional<Product> optionProduct = service.allProducts().stream().filter(obj -> obj.getId() == id).findFirst();
		if(optionProduct.isPresent()) {
			return new ResponseEntity<Product>(optionProduct.get(), HttpStatus.OK);
		}
		throw new ProductNotFoundException(String.format("Product with id %d not found!!!", id));
	}
	
	@PostMapping
	public ResponseEntity<Product> addNewProduct( @RequestBody @Valid  Product p) {
		List<Product> products=service.allProducts();
		products.add(p);
		return new ResponseEntity<Product>(p, HttpStatus.CREATED);
	}
}
