package com.example.egen.item.entity;

public class OrderAmountEntity {
    private Integer amountId;
    private Integer orderId; 
    private Double subTotal;
    private Double orderTax;
    private Double shippingCharges;
    private Double total;

    public OrderAmountEntity(){}

    public OrderAmountEntity(Integer amountId, Integer orderId, Double subTotal, Double orderTax,
            Double shippingCharges, Double total) {
        this.amountId = amountId;
        this.orderId = orderId;
        this.subTotal = subTotal;
        this.orderTax = orderTax;
        this.shippingCharges = shippingCharges;
        this.total = total;
    }

    public Integer getAmountId() {
        return amountId;
    }

    public void setAmountId(Integer amountId) {
        this.amountId = amountId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getOrderTax() {
        return orderTax;
    }

    public void setOrderTax(Double orderTax) {
        this.orderTax = orderTax;
    }

    public Double getShippingCharges() {
        return shippingCharges;
    }

    public void setShippingCharges(Double shippingCharges) {
        this.shippingCharges = shippingCharges;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    
}
