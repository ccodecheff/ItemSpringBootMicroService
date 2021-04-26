package com.example.egen.item.service;
import java.util.List;

import com.example.egen.item.entity.ItemOrderStore;
import com.example.egen.item.repositatory.ItemOrderStoreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemOrderStoreService {
    @Autowired
    private ItemOrderStoreRepository repository;

   

    public ItemOrderStore saveItem(ItemOrderStore item){
        return repository.save(item);
    }
    public ItemOrderStore getItemById(Integer id){
        return repository.findById(id).orElse(null);
    }
    public List <ItemOrderStore> saveItems(List <ItemOrderStore> items)
    {
        return  repository.saveAll(items);
    } 

    public List <ItemOrderStore> getItems()
    {
        return  repository.findAll();
    } 

    public List <ItemOrderStore>  getItemByIds(Iterable<Integer> iterator)
    {
        return  repository.findAllById(iterator);
        
    } 

    public List <ItemOrderStore>  getItemByOrderId(Integer id)
    {
        return  repository.findByOrderId(id);
        
    } 

    public ItemOrderStore updateItem(ItemOrderStore item)
    {
        ItemOrderStore existingItem= repository.findById(item.getId()).orElse(null);
        Integer OrderId= existingItem.getOrderId();
        System.out.println("UpdateOrder:"+OrderId);
        existingItem.setItemQty(item.getItemQty());
     //   existingItem.setId(item.getId());
        existingItem.setOrderId(item.getOrderId());
        existingItem.setItemQty(item.getItemQty());
        
        
        return repository.save(existingItem);
    }

}
