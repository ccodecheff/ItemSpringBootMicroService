package com.example.egen.item.entity;

public class ItemOrderQty {
    private Integer orderId;
    private Integer itemId;
    private Integer itemQty;

    public ItemOrderQty(){}

    public ItemOrderQty(Integer orderId, Integer itemId, Integer itemQty) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.itemQty = itemQty;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getItemQty() {
        return itemQty;
    }

    public void setItemQty(Integer itemQty) {
        this.itemQty = itemQty;
    }

   
}
