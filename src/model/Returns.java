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
    private LocalDate returnDate;
    private int fee;

    // Getters
    public int getReturnId() {
        return this.returnId;
    }

    public int getPaymentId() {
        return this.paymentId;
    }

    public LocalDate getReturnDate() {
        return this.returnDate;
    }

    public int getFee() {
        return this.fee;
    }

    // Setters
    public void setReturnId(int returnId) {
        this.returnId = returnId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }
}

