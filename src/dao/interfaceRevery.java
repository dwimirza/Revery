/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import model.Item;
import model.Category;
import model.Rental;
import model.Payment;
import model.Returns;
import java.util.List;


/**
 *
 * @author Pajaar
 */
public interface interfaceRevery {
    
    public void insertItem(Item item1);
    
    public void insertCategory(Category category1);
    
    public void insertPayment(Payment payment1);
        
    public void insertRental(Rental rental1);
            
    public void insertReturn(Returns returns1);

    public void updateItem(Item item1);
    
    public void updateCategory(Category category1);

    public void deleteItem(int itemId);
    
    public void deleteC(int categoryId);

    
    public List<Item> getItemByCat(String category);
    public List<Item> getItem();
    
    public List<Category> getCategory();
    
    public List<Returns> getReturns();
    public List<Returns> getOneReturn(int id);
    
}
