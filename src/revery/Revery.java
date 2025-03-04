/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package revery;

import javax.swing.SwingUtilities;

import dao.daoItem;
import gui.GUIHomepage;
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

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Initialize and show your GUIHomepage
                new GUIHomepage(); // Assuming GUIHomepage is a JFrame or similar class
            }
        });
        
    }
    
}
