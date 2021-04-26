package com.example.egen.item.repositatory;

import com.example.egen.item.entity.OrderAmount;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderAmountRepository extends JpaRepository<OrderAmount,Integer> {
    public OrderAmount findByOrderId(Integer orderId);
}
