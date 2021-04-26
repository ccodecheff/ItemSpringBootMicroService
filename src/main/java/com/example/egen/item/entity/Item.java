package com.example.egen.item.entity;

import javax.persistence.*;
@Entity
@Table(name="Item_TBL")
public class Item {
    
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int item_id;
    
   
    private String item_name;
    private Double item_price;

    public Item(){}

    public Item(String item_name, Double item_price) {
        this.item_name = item_name;
        this.item_price = item_price;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public Double getItem_price() {
        return item_price;
    }

    public void setItem_price(Double item_price) {
        this.item_price = item_price;
    }

    
}
