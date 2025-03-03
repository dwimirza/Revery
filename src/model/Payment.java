/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author USER
 */
    public class Payment {
    private int paymentId;
    private int rentalId;
    private String paymentMethod;
    private int paymentStatus;

    // Getters
    public int getPaymentId() {
        return this.paymentId;
    }

    public int getRentalId() {
        return this.rentalId;
    }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public int getPaymentStatus() {
        return this.paymentStatus;
    }

    // Setters
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPaymentStatus(int paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}

