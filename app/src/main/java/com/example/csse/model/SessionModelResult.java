package com.example.csse.model;

public class SessionModelResult {

    private String session_id;
    private String bus_id;
    private String driver_id;
    private String route_id;
    private String start_location;
    private String destination;
    private String route_no;
    private String start_time;
    private String isStartSession;


    public String getSession_id() {
        return session_id;
    }

    public String getBus_id() {
        return bus_id;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public String getRoute_id() {
        return route_id;
    }

    public String getStart_location() {
        return start_location;
    }

    public String getDestination() {
        return destination;
    }

    public String getRoute_no() {
        return route_no;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getIsStartSession() {
        return isStartSession;
    }
}
