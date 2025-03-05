/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Pajaar
 */
import dao.daoItem;
import dao.interfaceRevery;
import model.Admin;
import model.Category;
import model.Item;
import model.Payment;
import model.Rental;
import model.Returns;
import gui.GUIAdminpage;
import gui.GUIHomepage;
import gui.GUILoginpage;
import java.util.List;

public class controllerHome{
    GUIHomepage Home;
    interfaceRevery infcRevery;
    
    public controllerHome(GUIHomepage Home){
        this.Home = Home;
        infcRevery = new daoItem();
    }
    
    public List<Category> getCategories() {
        return infcRevery.getCategory();
    }
    
    public List<Item> getItemByCat(String category) {
        
        return infcRevery.getItemByCat(category);
    }
    public List<Item> getItem() {
        return infcRevery.getItem();
    }
    
    public void showRentalC(Category category1){
        Home.setOutCategory(category1.getId());
//        showSelectItemDialog(category1.getCatName());
    }
    
    public void showRentalI(Item item1){
        Home.setOutItem(item1.getId());
//        showSelectItemDialog(category1.getCatName());
    }
    
    public void showRentalR(Rental rental1){
        Home.setOutCategory(rental1.getRentalId());
    }
}