package com.kekmart.application.controller;

import com.kekmart.application.model.Product;
import com.kekmart.application.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    
    @GetMapping
    public List<Product> getProduct(){
    	return productRepository.findAll();
    }

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Product getDataByID(@PathVariable Long id) {
    	return productRepository.findById(id).orElseThrow(()->new RuntimeException("Product not found with id " + id ));
    }
    
    @PostMapping("/bulk")
    public List<Product> BulkAdd(@RequestBody List<Product> products){
    	return productRepository.saveAll(products);
    }
    
    
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
    	return productRepository.save(product);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from ProductController!";
    }
}
