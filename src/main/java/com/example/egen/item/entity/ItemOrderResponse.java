package com.example.egen.item.entity;

public class ItemOrderResponse {
    
    private Integer itemId;
    private String itemName;
    private Integer itemQty;
    
    public ItemOrderResponse(){}

    public ItemOrderResponse( Integer itemId, String itemName, Integer itemQty) {
    
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemQty = itemQty;
    }

    

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemQty() {
        return itemQty;
    }

    public void setItemQty(Integer itemQty) {
        this.itemQty = itemQty;
    }
    
   
}
