/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.daoItem;
import dao.interfaceRevery;
import gui.GUIAdminpage;
import gui.GUIHomepage;
import java.util.List;
import model.Category;
import model.Item;

/**
 *
 * @author USER
 */
public class ControllerAdmin {
    GUIAdminpage frame;
    interfaceRevery infcRevery;
    
    public ControllerAdmin(GUIAdminpage frame){
        this.frame = frame;
        infcRevery = new daoItem();
    }
    
     public void updateItem(String itemName, int categoryId, double rentPrice, int itemId){
        Item item = new Item();
        item.setItem(itemName);
        item.setCatId(categoryId);
        item.setRentPrice(rentPrice);
        item.setId(itemId);
        
        infcRevery.updateItem(itemName, categoryId, rentPrice, itemId);
    }
    
    public void addCategory(String categoryName){
        Category category = new Category();
        category.setCatName(categoryName);
        

        infcRevery.insertCategory(category);
    }
        
    public List<Item> getItems() {
        return infcRevery.getItem();  // Memanggil metode getItems pada DAO
    }
    
    public List<Category> getCategories() {
        return infcRevery.getCategory();  // Memanggil metode getItems pada DAO
    }
    
    public void addItem(Item item) {
    infcRevery.insertItem(item); // Pass the object to the DAO
    }
    
    public void updateCategory(String categoryName, int categoryId){
        Category category = new Category();
        category.setCatName(categoryName);
        category.setId(categoryId);
        
        infcRevery.updateCategory(categoryName, categoryId);
    }
    
    public void deleteItem(int id) {
        infcRevery.deleteItem(id);  // Memanggil metode deleteItem pada DAO
    }
    
    public void deleteCategory(int id) {
        infcRevery.deleteC(id); // Memanggil metode deleteCategory pada DAO
    }
}
