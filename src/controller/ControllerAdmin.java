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
    
    public void addCategory(String categoryName, int categoryId){
        Category category = new Category();
        category.setCatName(categoryName);
        category.setId(categoryId);

        infcRevery.insertCategory(category);
//        JOptionPane.showMessageDialog(frame, "Berhasil menambahkan data baru");
//        
//        kembali();
    }
}
