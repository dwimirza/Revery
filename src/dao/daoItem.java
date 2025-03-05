/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Admin;
import model.Category;
import model.Item;
import model.Payment;
import model.Rental;
import model.Returns;

/**
 *
 * @author USER
 */
public class daoItem implements interfaceRevery{
        
    private Connection connection = null;
    private String insert;
    private String update;
    private String delete;
    private String select;

    
    public Connection setConnection() {
        try{
            String url = "jdbc:mysql://localhost:3306/dbrevery";
            String user = "root";
            String pass = "";
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(url, user, pass);
        } catch(Exception e){
            System.out.println("Error "+e.getMessage());
        }
            return connection; // mengembalikan koneksi
    }
    public boolean loginAdmin(Admin admin1) {
        PreparedStatement statement= null;
        ResultSet rs = null;
        select = "SELECT * FROM admin WHERE username = ? AND password = ?";

        try {
            setConnection();
            statement = connection.prepareStatement(select);
            statement.setString(1, admin1.getUsername());
            statement.setString(2, admin1.getPassword()); // ⚠️ Use hashed passwords in a real app
            rs = statement.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful!");
                return true;
            } else {
                System.out.println("Invalid username or password.");
                return false; // Return false when login fails
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false; // Return false if a SQL error occurs
        } finally {
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

        
    @Override
    public void insertItem(Item item1) {
        PreparedStatement statement = null;
        insert = "INSERT INTO item (itemId, name, categoryId, rentLPrice, stock) VALUES (?, ?, ?, ?, ?);";
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1, String.valueOf(item1.getId()));
            statement.setString(2, item1.getItem());
            statement.setInt(3, item1.getCatId());
            statement.setInt(4, item1.getRentPrice());
            statement.setInt(5, item1.getStock());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void insertCategory(Category category1){
        PreparedStatement statement = null;
        insert = "INSERT INTO category (categoryId,categoryName) VALUES (?, ?);";
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1, String.valueOf(category1.getId()));
            statement.setString(2, category1.getCatName());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void insertPayment(Payment payment1){
        PreparedStatement statement = null;
        insert = "INSERT INTO payments (paymentId,rentalId,paymentMethod,paymentStatus) VALUES (?, ?, ?, ?);";
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1, String.valueOf(payment1.getPaymentId()));
            statement.setString(2, String.valueOf(payment1.getRentalId()));
            statement.setString(3, payment1.getPaymentMethod());
            statement.setString(4, String.valueOf(payment1.getPaymentStatus()));
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void insertRental(Rental rental1) {
        PreparedStatement statement = null;
        insert = "INSERT INTO rentals (rentalId, borrowerName, itemId, rentalDate, returnDate, totalPrice, rentalStatus) VALUES (?, ?, ?, ?, ?, ?, ?);";
        try {
            statement = connection.prepareStatement(insert);
            statement.setInt(1, rental1.getRentalId());
            statement.setString(2, rental1.getBorrowerName());
            statement.setString(3, String.valueOf(rental1.getItemId()));  // Convert String itemId to int
            statement.setDate(4, Date.valueOf(rental1.getRentalDate())); // Convert LocalDate to SQL Date
            statement.setDate(5, Date.valueOf(rental1.getReturnDate())); // Convert LocalDate to SQL Date
            statement.setInt(6, rental1.getTotalPrice());
            statement.setInt(7, rental1.getRentalStatus());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void insertReturn(Returns return1) {
        PreparedStatement statement = null;
        insert = "INSERT INTO returns (returnId, paymentId, returnDate, fee) VALUES (?, ?, ?, ?);";
        try {
            statement = connection.prepareStatement(insert);
            statement.setInt(1, return1.getReturnId());
            statement.setInt(2, return1.getPaymentId());
            statement.setDate(3, Date.valueOf(return1.getReturnDate())); // Convert LocalDate to SQL Date
            statement.setInt(4, return1.getFee());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    
    
    @Override
    public void updateItem(Item item1) {
        PreparedStatement statement = null;
        update = "UPDATE item SET name=?, categoryId=?, rentalPrice=?, stock=? WHERE itemId=? ;";
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, item1.getItem());
            statement.setInt(2, item1.getCatId());
            statement.setInt(3, item1.getRentPrice());
            statement.setInt(4, item1.getStock());
            statement.setInt(5, item1.getId());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }         
    }
    @Override
    public void updateCategory(Category category1) {
        PreparedStatement statement = null;
        update = "UPDATE category SET categoryName=? WHERE categoryId=? ;";
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, category1.getCatName());
            statement.setInt(2, category1.getId());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }         
    }

    
    @Override
    public void deleteItem(int id) {
        PreparedStatement statement = null;
        delete = "DELETE * FROM item where itemId = ?";
        try {
            statement = connection.prepareStatement(delete);
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    
    @Override
    public void deleteC(int id) {
        PreparedStatement statement = null;
        delete = "DELETE * FROM category where categoryId = ?";
        try {
            statement = connection.prepareStatement(delete);
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public List<Item> getItem(){
      List<Item> listItem = null;
        select = "SELECT * FROM item";
        try {
            listItem = new ArrayList<Item>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                Item item1 = new Item();
                item1.setId(rs.getInt("id"));
                item1.setItem(rs.getString("name"));
                item1.setRentPrice(rs.getInt("rentalPrice"));
                item1.setStock(rs.getInt("stock"));
                listItem.add(item1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(daoItem.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listItem;  
    };
    
    public List<Item> getItemByCat(String category) {
        setConnection();
        List<Item> listItem = null;
        select = "SELECT * FROM item where categoryId = ? ";
        try {
            listItem = new ArrayList<Item>();
            PreparedStatement st = connection.prepareStatement(select);
            st.setString(1, category);
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                Item item1 = new Item();
                item1.setId(rs.getInt("itemId"));
                item1.setItem(rs.getString("name"));
                item1.setCatId(rs.getInt("categoryId"));
                item1.setRentPrice(rs.getInt("rentalPrice"));
                item1.setStock(rs.getInt("stock"));
                listItem.add(item1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(daoItem.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listItem;
    }
    public List<Category> getCategory() {
        List<Category> listCategory = null;
        select = "SELECT * FROM category";
        try {
            listCategory = new ArrayList<Category>();
            setConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                Category category1 = new Category();
                category1.setId(rs.getInt("categoryId"));
                category1.setCatName(rs.getString("categoryName"));
                listCategory.add(category1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(daoItem.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listCategory;
    }
    public List<Rental> getRental() {
        List<Rental> listRents = null;
        select = "SELECT rentalId,borrowerName,itemId,rentalDate,returnDate,totalPrice FROM rentals";
        try {
            listRents = new ArrayList<Rental>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                Rental rent1 = new Rental();
                rent1.setRentalId(rs.getInt("rentalId"));
                rent1.setBorrowerName(rs.getString("borrowerName"));
                rent1.setItemId(rs.getInt("itemId"));
                rent1.setRentalDate(rs.getDate("rentalDate").toLocalDate());
                rent1.setReturnDate(rs.getDate("returnDate").toLocalDate());
                rent1.setTotalPrice(rs.getInt("totalPrice"));
                listRents.add(rent1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(daoItem.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listRents;
    }
    public List<Returns> getReturns() {
        List<Returns> listReturns = null;
        select = "SELECT returnId, paymentId, returnDate, fee FROM returns";
        try {
            listReturns = new ArrayList<Returns>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                Returns return1 = new Returns();
                return1.setReturnId(rs.getInt("returnId"));
                return1.setPaymentId(rs.getInt("paymentId"));
                return1.setReturnDate(rs.getDate("returnDate").toLocalDate()); // Convert SQL Date to LocalDate
                return1.setFee(rs.getInt("fee"));
                listReturns.add(return1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(daoItem.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listReturns;
    }

    public void insert(Item item1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void update(Item item1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   

    
}
