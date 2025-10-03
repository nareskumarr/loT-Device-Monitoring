package com.iotmonitoring.model;
import java.util.Date;

public class Reading {
    private double value;
    private Date timestamp;
    
    public Reading(double value, Date timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }
    public double getValue() { return value; }
    public Date getTimestamp() { return timestamp; }
    
    @Override
    public String toString() {
        return "Reading[value=" + value + ", time=" + timestamp + "]";
    }
}
