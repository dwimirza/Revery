/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author USER
 */
public class Admin {
    private String username;
    private String password;
    
    public String getUsername(){
       return this.username;
    }
    public String getPassword(){
       return this.password;
    }
    
    public void setUsername(String name)
    {
        this.username = name;
    };
    
    public void setPassword(String pass)
    {
        this.password = pass;
    }
}
