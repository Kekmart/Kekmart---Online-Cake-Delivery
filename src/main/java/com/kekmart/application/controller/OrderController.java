package com.kekmart.application.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kekmart.application.model.CartItem;
import com.kekmart.application.model.Order;
import com.kekmart.application.model.OrderItem;
import com.kekmart.application.model.User;
import com.kekmart.application.repository.CartItemRepository;
import com.kekmart.application.repository.OrderRepository;
import com.kekmart.application.repository.ProductRepository;
import com.kekmart.application.repository.UserRepository;

@RestController
@CrossOrigin(origins="http://localhost://3000")
@RequestMapping("/api/orders")
public class OrderController {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private CartItemRepository cartRepo;
	
	@PostMapping("/place")
	public Order placeOrder(@RequestParam Long userId,
			@RequestParam String address) {
		User user=userRepo.findById(userId).orElseThrow();
		List<CartItem> cartItems= cartRepo.findByUser(user);
		
		 List<OrderItem> orderItems = cartItems.stream().map(cart -> {
	            OrderItem oi = new OrderItem();
	            oi.setProduct(cart.getProduct());
	            oi.setQuantity(cart.getQuantity());
	            oi.setPrice(cart.getProduct().getPrice());
	            return oi;
	        }).collect(Collectors.toList());

	        Order order = new Order();
	        order.setUser(user);
	        order.setItems(orderItems);
	        order.setCreatedAt(LocalDateTime.now());
	        order.setAddress(address);

	        cartRepo.deleteByUser(user); // clear cart after placing order
	        return orderRepo.save(order);
	}
	
	@GetMapping("/{userId}")
    public List<Order> getUserOrders(@PathVariable Long userId) {
        User user = userRepo.findById(userId).orElseThrow();
        return orderRepo.findByUser(user);
    }

}
