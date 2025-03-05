package controller;

import dao.daoItem;
import dao.interfaceRevery;
import gui.GUIAdminpage;
import model.Category;
import model.Item;

import java.util.List;

public class controllerAdmin {
    interfaceRevery infcRevery;

    public controllerAdmin(GUIAdminpage Admin){
        infcRevery = new daoItem();
    }

    // Kategori: GET, INSERT, UPDATE, DELETE
    public List<Category> getCategories() {
        return infcRevery.getCategory();
    }
    
    public void insertCategory(Category category) {
        infcRevery.insertCategory(category);  // Memanggil metode insertCategory pada DAO
    }

    public void updateCategory(Category category) {
        infcRevery.updateCategory(category);  // Memanggil metode updateCategory pada DAO
    }
    
    public void deleteCategory(int id) {
        infcRevery.deleteC(id); // Memanggil metode deleteCategory pada DAO
    }
    
    // Item: GET, INSERT, UPDATE, DELETE
    public List<Item> getItems() {
        return infcRevery.getItem();  // Memanggil metode getItems pada DAO
    }
    
    public void insertItem(Item item) {
        infcRevery.insertItem(item);  // Memanggil metode insertItem pada DAO
    }

    public void updateItem(Item item) {
        infcRevery.updateItem(item);  // Memanggil metode updateItem pada DAO
    }

    public void deleteItem(int id) {
        infcRevery.deleteItem(id);  // Memanggil metode deleteItem pada DAO
    }
}
