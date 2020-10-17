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
    String name;
    String phoneNo;
    String noOfSeats;
    String totalFare;
    String paymentMethod;
    String isPay;

    public PassengerLayoutModel(String _id, String bus_id, String session_id, String user_id, String email, String name, String phoneNo, String noOfSeats, String totalFare, String paymentMethod, String isPay) {
        this._id = _id;
        this.bus_id = bus_id;
        this.session_id = session_id;
        this.user_id = user_id;
        this.email = email;
        this.name = name;
        this.phoneNo = phoneNo;
        this.noOfSeats = noOfSeats;
        this.totalFare = totalFare;
        this.paymentMethod = paymentMethod;
        this.isPay = isPay;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getBus_id() {
        return bus_id;
    }

    public void setBus_id(String bus_id) {
        this.bus_id = bus_id;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(String noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public String getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(String totalFare) {
        this.totalFare = totalFare;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getIsPay() {
        return isPay;
    }

    public void setIsPay(String isPay) {
        this.isPay = isPay;
    }
}










