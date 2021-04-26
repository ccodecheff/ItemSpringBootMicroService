package com.example.egen.item.service;

import org.springframework.stereotype.Service;

import java.util.List;

import com.example.egen.item.entity.OrderAmount;
import com.example.egen.item.entity.OrderAmountEntity;
import com.example.egen.item.repositatory.OrderAmountRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class OrderAmountService {
    @Autowired
    private OrderAmountRepository repository;

    public OrderAmount saveItem(OrderAmount item){
        return repository.save(item);
    }

    public List <OrderAmount> saveItems(List <OrderAmount> items)
    {
        return  repository.saveAll(items);
    } 

    public List <OrderAmount> getItems()
    {
        return  repository.findAll();
    } 

    public List <OrderAmount>  getItemByIds(Iterable<Integer> iterator)
    {
        return  repository.findAllById(iterator);
        
    }

    public OrderAmount findAmountByOrderId(Integer orderId){
        return repository.findByOrderId(orderId);
    }

    public OrderAmount updateItem(OrderAmountEntity item)
    {
        OrderAmount existingItem= repository.findById(item.getAmountId()).orElse(null);
       existingItem.setOrderId(item.getOrderId());
       existingItem.setOrderTax(item.getOrderTax());
       existingItem.setShippingCharges(item.getShippingCharges());
       existingItem.setSubTotal(item.getSubTotal());
       existingItem.setTotal(item.getTotal());
       
        return repository.save(existingItem);
    }

}
