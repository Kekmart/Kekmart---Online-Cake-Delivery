package com.kekmart.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kekmart.application.model.CartItem;
import com.kekmart.application.model.Product;
import com.kekmart.application.model.User;
import com.kekmart.application.repository.CartItemRepository;
import com.kekmart.application.repository.ProductRepository;
import com.kekmart.application.repository.UserRepository;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins="http://localhost://3000")

public class CartController {
	
	@Autowired
	private CartItemRepository cartRepo;
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private UserRepository userRepo;
	
	@PostMapping("/new")
	public CartItem addToCart(@RequestParam Long userId,
			@RequestParam Long productId,
			@RequestParam int quantity) {
		User user=userRepo.findById(userId).orElseThrow();
		Product product=productRepo.findById(productId).orElseThrow();
		
		CartItem item=new CartItem(quantity, product, user);
		return cartRepo.save(item);
	}
	
	@GetMapping("/{userId}")
	public List<CartItem> getCartItem(@PathVariable Long userId)
	{
		User user=userRepo.findById(userId).orElseThrow();
		return cartRepo.findByUser(user);
	}
	
	@DeleteMapping("/clear/{userId}")
	public void clearCart(@PathVariable Long userId) {
		User user=userRepo.findById(userId).orElseThrow();
		cartRepo.deleteByUser(user);
	}
	

}
