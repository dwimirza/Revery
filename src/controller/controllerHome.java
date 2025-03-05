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
import static java.lang.Integer.parseInt;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

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
    
    public List<Item> getItemByCat(String category) {
         String[] parts = category.split(" - "); // Pisahkan berdasarkan " - "
    if (parts.length > 1) {
        String categoryId = parts[0]; // Ambil ID kategori
        return infcRevery.getItemByCat(categoryId);
    }
    return new ArrayList<>(); // Jika format salah, kembalikan list kosong
    }
    
    public List<Item> getItem() {
        return infcRevery.getItem();
    }
    public List<Returns> getOneReturn(String id){
        int idInt = parseInt(id);
        return infcRevery.getOneReturn(idInt);
    }
    
    public void showRentalC(Category category1){
        Home.setOutCategory(category1.getId());
//        showSelectItemDialog(category1.getCatName());
    }
    
    public void showRentalI(Item item1){
        Home.setOutItem(item1.getId());
//        showSelectItemDialog(category1.getCatName());
    }
 
    public void showRentalR(Rental rental1){
        Home.setOutCategory(rental1.getRentalId());
    }
    
    public void addData(String namaPeminjam,String tanggalMeminjam,String tanggalPengembalian,String selectedItem,String paymentMethod){
        String id = null;
        LocalDate localDate = LocalDate.parse(tanggalMeminjam);
        LocalDate localDate1 = LocalDate.parse(tanggalPengembalian);
         String[] parts = selectedItem.split(" - "); // Pisahkan berdasarkan " - "
    if (parts.length > 1) {
         id = parts[0]; // Ambil ID kategori
    }
        Rental rental = new Rental();
        rental.setBorrowerName(namaPeminjam);
        rental.setRentalDate(localDate);
        rental.setReturnDate(localDate);
        rental.setItemId(Integer.parseInt(id));
        rental.setRentalStatus(1);
        
        
        int rentalId = infcRevery.insertRental(rental);
        if (rentalId == -1) {
            System.out.println("Failed to insert rental.");
            return;
        }
        Payment payment = new Payment();
        payment.setPaymentMethod(paymentMethod);
        payment.setRentalId(rentalId);
        payment.setPaymentStatus(1);
        infcRevery.insertPayment(payment);
    }
    public void insertReturn(int paymentId){
        List<Returns> returnDataList = infcRevery.getReturnsByPaymentId(paymentId);

    if (!returnDataList.isEmpty()) {
        LocalDate currentDate = LocalDate.now();
        Returns returnInfo = returnDataList.get(0); // Assuming only one record per payment

        // Calculate Fee
        long rentalDays = ChronoUnit.DAYS.between(returnInfo.getRentalDate(), currentDate);
         double totalFee ;
        if(rentalDays <= 0){
            totalFee = returnInfo.getFee();
        }else{
            totalFee = rentalDays * returnInfo.getFee();
        }
        // Check if past due date
        if (currentDate.isAfter(returnInfo.getReturnDate())) {
            totalFee += 250000; // Add penalty if overdue
        }

        // Insert return record
        Returns returnEntry = new Returns();
        returnEntry.setPaymentId(paymentId);
        returnEntry.setReturnDate(currentDate);
        returnEntry.setFee(totalFee);
        infcRevery.insertReturn(returnEntry);
         
        infcRevery.updateRentalStatus(returnInfo.getRentalId(), "2");

        infcRevery.updatePaymentStatus(paymentId, "2");
    } else {
        JOptionPane.showMessageDialog(null, "No records found for Payment ID: " + paymentId);
    }
    }

    
}