/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author USER
 */
public class Rental {
    private int rentalId;
    private String borrowerName;
    private int itemId;
    private LocalDate rentalDate;
    private LocalDate returnDate;
    private int rentalStatus;

    // Getters
    public int getRentalId() {
        return this.rentalId;
    }

    public String getBorrowerName() {
        return this.borrowerName;
    }

    public int getItemId() {
        return this.itemId;
    }

    public LocalDate getRentalDate() {
        return this.rentalDate;
    }

    public LocalDate getReturnDate() {
        return this.returnDate;
    }

    public int getRentalStatus() {
        return this.rentalStatus;
    }

    // Setters
    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setRentalStatus(int rentalStatus) {
        this.rentalStatus = rentalStatus;
    }
}

