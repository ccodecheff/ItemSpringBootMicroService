package com.example.egen.item.entity;

public class ItemAmountResponse {
    
    private Integer order_id;
    private Double total_price;
   
    
    public ItemAmountResponse(){}
    
    
    public ItemAmountResponse(Integer order_id, Double total_price) {
        this.order_id = order_id;
        this.total_price = total_price;
            }


    public Integer getOrder_id() {
        return order_id;
    }
    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }
    public Double getTotal_price() {
        return total_price;
    }
    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
    }

}
