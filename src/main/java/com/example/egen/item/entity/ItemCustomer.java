package com.example.egen.item.entity;

import java.util.ArrayList;
import java.util.List;

public class ItemCustomer {
   private  String customer_id;
   List<ItemIdQty> item_qty= new ArrayList<ItemIdQty>();

public ItemCustomer(){}

public ItemCustomer(String customer_id, List<ItemIdQty> item_qty) {
    this.customer_id = customer_id;
    this.item_qty = item_qty;
}

public String getCustomer_id() {
    return customer_id;
}

public void setCustomer_id(String customer_id) {
    this.customer_id = customer_id;
}

public List<ItemIdQty> getItem_qty() {
    return item_qty;
}

public void setItem_qty(List<ItemIdQty> item_qty) {
    this.item_qty = item_qty;
}

  
}
