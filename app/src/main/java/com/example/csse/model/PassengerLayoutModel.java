package com.example.csse.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PassengerLayoutModel implements Serializable {




    String _id;
    String bus_id;
    String session_id;
    String user_id;
    String email;
    String phoneNo;
    String noOfSeats;
    String totalFare;
    String paymentMethod;
    String isPay;

    public PassengerLayoutModel(String _id, String bus_id, String session_id, String user_id, String email, String phoneNo, String noOfSeats, String totalFare, String paymentMethod, String isPay) {
        this._id = _id;
        this.bus_id = bus_id;
        this.session_id = session_id;
        this.user_id = user_id;
        this.email = email;
        this.phoneNo = phoneNo;
        this.noOfSeats = noOfSeats;
        this.totalFare = totalFare;
        this.paymentMethod = paymentMethod;
        this.isPay = isPay;
    }

    public String get_id() {
        return _id;
    }

    public String getBus_id() {
        return bus_id;
    }

    public String getSession_id() {
        return session_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getNoOfSeats() {
        return noOfSeats;
    }

    public String getTotalFare() {
        return totalFare;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getIsPay() {
        return isPay;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setBus_id(String bus_id) {
        this.bus_id = bus_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setNoOfSeats(String noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public void setTotalFare(String totalFare) {
        this.totalFare = totalFare;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setIsPay(String isPay) {
        this.isPay = isPay;
    }
}










