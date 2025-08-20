package com.deepak.precize.model;

public class Item {
    private String itemId;
    private int quantity;

    public Item(String itemId, int quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

    // Getters and setters
    public String getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
