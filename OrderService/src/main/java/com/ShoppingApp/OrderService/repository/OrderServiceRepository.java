package com.ShoppingApp.OrderService.repository;

import com.ShoppingApp.OrderService.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderServiceRepository extends JpaRepository<Order,Long> {

}
