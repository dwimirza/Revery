/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package revery;

import dao.daoItem;
import model.Admin;
import model.Category;

/**
 *
 * @author Nanda
 */
public class Revery {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        daoItem objDB = new daoItem();
        System.out.println("Status Koneksi: "+objDB.setConnection());
        
    }
    
}
