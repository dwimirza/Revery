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
    
    public daoItem(){
        this.connection = setConnection();
    }
    
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
        insert = "INSERT INTO item (itemId, name, categoryId, rentalPrice) VALUES (?, ?, ?, ?);";
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1, String.valueOf(item1.getId()));
            statement.setString(2, item1.getItem());
            statement.setInt(3, item1.getCatId());
            statement.setDouble(4, item1.getRentPrice());
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
    public void insertCategory(Category category1){
        PreparedStatement statement = null;
        insert = "INSERT INTO category (categoryName) VALUES (?);";
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1, category1.getCatName());
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
        insert = "INSERT INTO payments (rentalId,paymentMethod,paymentStatus) VALUES (?, ?, ?);";
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1, String.valueOf(payment1.getRentalId()));
            statement.setString(2, payment1.getPaymentMethod());
            statement.setInt(3, payment1.getPaymentStatus());
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
    
    public int insertRental(Rental rental1) {
    PreparedStatement statement = null;
    ResultSet generatedKeys = null;
    String insert = "INSERT INTO rentals (borrowerName, itemId, rentalDate, returnDate, rentalStatus) VALUES (?, ?, ?, ?, ?);";

    try {
        statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, rental1.getBorrowerName());
        statement.setInt(2, rental1.getItemId());
        statement.setDate(3, Date.valueOf(rental1.getRentalDate())); 
        statement.setDate(4, Date.valueOf(rental1.getReturnDate()));
        statement.setInt(5, rental1.getRentalStatus());
        
        int affectedRows = statement.executeUpdate();

        if (affectedRows > 0) {
            generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1); // Return the generated rentalId
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        try {
            if (generatedKeys != null) generatedKeys.close();
            if (statement != null) statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    return -1; // Return -1 if insertion fails
}
    public List<Returns> getReturnsByPaymentId(int paymentId) {
    List<Returns> returnList = new ArrayList<>();
    String query = "SELECT r.borrowerName, i.name AS itemName, r.rentalDate, r.returnDate, r.rentalStatus, i.rentalPrice, r.rentalId " +
                   "FROM payments p " +
                   "JOIN rentals r ON r.rentalId = p.rentalId " +
                   "JOIN item i ON i.itemId = r.itemId " +
                   "WHERE p.paymentId = ? and p.paymentStatus = 1";

    try (PreparedStatement st = connection.prepareStatement(query)) {
        st.setInt(1, paymentId);
        ResultSet rs = st.executeQuery();
        
        while (rs.next()) {
            Returns returnData = new Returns();
            returnData.setBorrowerName(rs.getString("borrowerName"));
            returnData.setItemName(rs.getString("itemName"));
            returnData.setRentalId(rs.getInt("rentalId"));
            returnData.setRentalDate(rs.getDate("rentalDate").toLocalDate());
            returnData.setReturnDate(rs.getDate("returnDate").toLocalDate());
            returnData.setStatus(rs.getString("rentalStatus"));
            returnData.setFee(rs.getDouble("rentalPrice"));
            
            returnList.add(returnData);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return returnList;
    }
    
    public void insertReturn(Returns return1) {
        PreparedStatement statement = null;
        insert = "INSERT INTO returns ( paymentId, returnDate, fee) VALUES ( ?, ?, ?);";
        try {
            statement = connection.prepareStatement(insert);
            statement.setInt(1, return1.getPaymentId());
            statement.setDate(2, Date.valueOf(return1.getReturnDate())); // Convert LocalDate to SQL Date
            statement.setDouble(3, return1.getFee());
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
    public void updateItem(String itemName, int categoryId, double rentPrice, int itemId) {
    String sql = "UPDATE item SET name=?, categoryId=?, rentalPrice=? WHERE itemId=?";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, itemName);
        statement.setInt(2, categoryId);
        statement.setDouble(3, rentPrice);
        statement.setInt(4, itemId);

        statement.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

    @Override
    public void updateCategory(String categoryName, int categoryId) {
        PreparedStatement statement = null;
        update = "UPDATE category SET categoryName=? WHERE categoryId=? ;";
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, categoryName);
            statement.setInt(2, categoryId);
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
        delete = "DELETE FROM item where itemId = ?";
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
    public void updateRentalStatus(int rentalId, String status) {
        String updateQuery = "UPDATE rentals SET rentalStatus = ? WHERE rentalId = ?";

        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setString(1, status); // ENUM values should be stored as strings
            statement.setInt(2, rentalId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Update payment status (ENUM)
    
    @Override
    public void updatePaymentStatus(int paymentId, String status) {
        String updateQuery = "UPDATE payments SET paymentStatus = ? WHERE paymentId = ?";

        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setString(1, status); // ENUM values should be stored as strings
            statement.setInt(2, paymentId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void deleteC(int id) {
        PreparedStatement statement = null;
        delete = "DELETE FROM category where categoryId = ?";
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
                item1.setId(rs.getInt("itemId"));
                item1.setItem(rs.getString("name"));
                item1.setCatId(rs.getInt("categoryId"));
                item1.setRentPrice(rs.getDouble("rentalPrice"));
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
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Item item1 = new Item();
                item1.setId(rs.getInt("itemId"));
                item1.setItem(rs.getString("name"));
                item1.setCatId(rs.getInt("categoryId"));
                item1.setRentPrice(rs.getDouble("rentalPrice"));
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
                return1.setFee(rs.getDouble("fee"));
                listReturns.add(return1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(daoItem.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listReturns;
    }
    
    
    @Override
    public List<Returns> getOneReturn(int id) {
        setConnection();
        List<Returns> listReturns = null;
        select = "select r.borrowerName, i.name as itemName, r.rentalDate, r.rentalStatus from payments p \n" +
                 "join rentals r on r.rentalId = p.rentalId \n" +
                 "join item i on i.itemId = r.itemId\n" +
                 "where p.paymentId = ? and p.paymentStatus = 1";
        
        try {
            listReturns = new ArrayList<Returns>();
            PreparedStatement st = connection.prepareStatement(select);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Returns return1 = new Returns();
                return1.setBorrowerName(rs.getString("borrowerName"));
                return1.setItemName(rs.getString("itemName"));
                return1.setRentalDate(rs.getDate("rentalDate").toLocalDate()); // Convert SQL Date to LocalDate
                return1.setStatus(rs.getString("rentalStatus"));
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
