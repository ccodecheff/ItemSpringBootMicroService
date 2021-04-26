package com.example.egen.item.service;

//import java.util.Iterator;
import java.util.List;

import com.example.egen.item.entity.Item;
import com.example.egen.item.repositatory.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
 
    @Autowired
    private ItemRepository repository;

    public Item saveItem(Item item){
        return repository.save(item);
    }

    public List <Item> saveItems(List <Item> items)
    {
        return  repository.saveAll(items);
    } 

    public List <Item> getItems()
    {
        return  repository.findAll();
    } 

    public List <Item>  getItemByIds(Iterable<Integer> iterator)
    {
        return  repository.findAllById(iterator);
        
    } 

    public Item  getItemById(Integer id)
    {
        return  repository.findById(id).orElse(null);
        
    } 

    public Integer deleteItemById(int id)
    {
        repository.deleteById(id);
        return id;
    }

    public Item updateItem(Item item)
    {
        Item existingItem= repository.findById(item.getItem_id()).orElse(null);
        existingItem.setItem_name(item.getItem_name());
        existingItem.setItem_price(item.getItem_price());
        return repository.save(existingItem);
    }

   


}
