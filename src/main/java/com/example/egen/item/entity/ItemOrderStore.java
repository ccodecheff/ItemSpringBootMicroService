package com.example.egen.item.entity;
import javax.persistence.*;

@Entity
@Table(name="Item_order_TBL")
public class ItemOrderStore {
   
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;
    private Integer orderId;
    private Integer itemId;
    private Integer itemQty;

    public ItemOrderStore(){}

    public ItemOrderStore(Integer orderId, Integer itemId, Integer itemQty) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.itemQty = itemQty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
