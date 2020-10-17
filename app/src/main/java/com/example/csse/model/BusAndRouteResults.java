package com.example.csse.model;

import com.google.gson.annotations.SerializedName;

public class BusAndRouteResults {

    //bus attribute
    @SerializedName("_id")
    private String Bus_id;
    private String route_id;
    private String driver_id;
    private String reg_no;
    private String type;
    private String no_of_seats;

    //Route Attribute
    private String start_location;
    private String destination;
    private String route_no;
    private String fare;
    private String start_time;
    private String session_id;


    public String getSession_id() {
        return session_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getFare() {
        return fare;
    }

    public String getBus_id() {
        return Bus_id;
    }

    public void setBus_id(String bus_id) {
        Bus_id = bus_id;
    }

    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getReg_no() {
        return reg_no;
    }

    public void setReg_no(String reg_no) {
        this.reg_no = reg_no;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNo_of_seats() {
        return no_of_seats;
    }

    public void setNo_of_seats(String no_of_seats) {
        this.no_of_seats = no_of_seats;
    }

    public String getStart_location() {
        return start_location;
    }

    public void setStart_location(String start_location) {
        this.start_location = start_location;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getRoute_no() {
        return route_no;
    }

    public void setRoute_no(String route_no) {
        this.route_no = route_no;
    }
}
