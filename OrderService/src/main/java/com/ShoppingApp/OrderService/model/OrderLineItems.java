package com.ShoppingApp.OrderService.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Order_Line_Items")
public class OrderLineItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skuCode;
    private Long price;
    private Integer quantity;

    public Long getId() {
        return id;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
