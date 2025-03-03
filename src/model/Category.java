/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author USER
 */
public class Category {
    private int categoryId;
    private String categoryName;
    
    public int getId(){
        return this.categoryId;
    }
    public String getCatName(){
        return this.categoryName;
    }
    
    public void setId(int id)
    {
        this.categoryId = id;
    };
    
    public void setCatName(String name)
    {
        this.categoryName = name;
    }
}
