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
    
    public void showRentalC(Category category1){
        Home.setOutCategory(category1.getId());
//        showSelectItemDialog(category1.getCatName());
    }
    
    public void showRentalI(Rental rental1){
        Home.setOutCategory(rental1.getRentalId());
    }
}
