package com.kekmart.application.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.kekmart.application.model.Order;
import com.kekmart.application.model.User;

public interface OrderRepository extends JpaRepository<Order, Long>{
	List<Order> findByUser(User user);
}
