package com.example.smartpantry.model;

public class PantryItem {
    private String name;
    private int quantity;
    private String expiryDate;

    public PantryItem(String name, int quantity, String expiryDate) {
        this.name = name;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getExpiryDate() {
        return expiryDate;
    }
}
