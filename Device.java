package com.iotmonitoring.model;
import java.util.*;

public class Device {
    private int id;
    private String name;
    private List<Sensor> sensors = new ArrayList<>();

    public Device(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public void addSensor(Sensor s) { sensors.add(s); }
    public Sensor getSensor(int sid) {
        return sensors.stream().filter(s -> s.getId() == sid).findFirst().orElse(null);
    }
    public List<Sensor> getSensors() { return sensors; }
}
