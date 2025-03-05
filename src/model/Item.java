/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author USER
 */
public class Item {
    private int itemId;
    private String name;
    private int categoryId;
    private Double rentalPrice;
    private int stock;
    
    // Getters
    public int getId(){
        return this.itemId;
    }

    public String getItem(){
        return this.name;
    }

    public int getCatId(){
        return this.categoryId;
    }

    public Double getRentPrice(){
        return this.rentalPrice;
    }

    public int getStock(){
        return this.stock;
    }

    // Setters
    public void setId(int id) {
        this.itemId = id;
    }

    public void setItem(String name) {
        this.name = name;
    }

    public void setCatId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setRentPrice(Double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

