/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author USER
 */
public class Returns {
    private int returnId;
    private int paymentId;
    private int rentalId;
    private String borrowerName;
    private String itemName;    
    private LocalDate returnDate;
    private LocalDate rentalDate;
    private Double fee;
    private String status;
    
// Getters
    public int getReturnId() {
        return this.returnId;
    }

    public int getPaymentId() {
        return this.paymentId;
    }
    public int getRentalId() {
        return this.rentalId;
    }
    
    public String getBorrowerName() {
        return this.borrowerName;
    }
    
    public String getitemName() {
        return this.itemName;
    }

    public LocalDate getReturnDate() {
        return this.returnDate;
    }
    public LocalDate getRentalDate() {
        return this.rentalDate;
    }

    public Double getFee() {
        return this.fee;
    }
    
    public String getStatus() {
        return this.status;
    }

    // Setters
    public void setReturnId(int returnId) {
        this.returnId = returnId;
    }
    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}

