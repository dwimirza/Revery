/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.daoItem;
import dao.interfaceRevery;
import gui.GUIAdminpage;
import gui.GUIHomepage;
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
    
    public void addCategory(String categoryName){
        Category category = new Category();
        category.setCatName(categoryName);
        

        infcRevery.insertCategory(category);
//        JOptionPane.showMessageDialog(frame, "Berhasil menambahkan data baru");
//        
//        kembali();
    }
    
    public void addItem(Item item) {
    infcRevery.insertItem(item); // Pass the object to the DAO
    }
}
