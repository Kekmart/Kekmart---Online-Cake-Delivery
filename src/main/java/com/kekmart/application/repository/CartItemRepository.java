package com.kekmart.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kekmart.application.model.CartItem;
import com.kekmart.application.model.User;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	List<CartItem> findByUser(User user);
	void deleteByUser(User user);
}
