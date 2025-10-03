package com.iotmonitoring.model;
import java.util.*;

public class Sensor {
    private int id;
    private String type;
    private Threshold threshold;
    private List<Reading> readings = new ArrayList<>();
    
    public Sensor(int id, String type) {
        this.id = id;
        this.type = type;
    }
    public int getId() { return id; }
    public String getType() { return type; }
    
    public void setThreshold(Threshold t) { this.threshold = t; }
    public Threshold getThreshold() { return threshold; }
    
    public void addReading(Reading r) { readings.add(r); }
    public List<Reading> getReadings() { return readings; }
}
