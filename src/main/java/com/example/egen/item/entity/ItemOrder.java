package com.example.egen.item.entity;

import java.util.List;

public class ItemOrder {
    
    private Integer order_id;
    private List <ItemIdQty> items;

    public ItemOrder(){ }

    public ItemOrder(Integer order_id, List<ItemIdQty> items) {
        this.order_id = order_id;
        this.items = items;
    }
    
    
    public Integer getOrder_id() {
        return order_id;
    }
    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }
    public List<ItemIdQty> getItems() {
        return items;
    }
    public void setItems(List<ItemIdQty> items) {
        this.items = items;
    }
}
