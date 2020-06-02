package com.shoppingcart.consumer.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
    public class CartItem {
    @Override
    public String toString() {
        return "CartItem{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", userID='" + userID + '\'' +
                ", itemQuantitys=" + itemQuantity +
                '}';
    }

    public CartItem() {
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int itemId;
        private String itemName;
        private String userID;

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    private int itemQuantity;


    }

