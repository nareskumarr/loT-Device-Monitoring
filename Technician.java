package com.iotmonitoring.model;

public class Technician {
    private int id;
    private String name;
    
    public Technician(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    
    public void logAction(String action) {
        System.out.println("Technician " + name + " performed: " + action);
    }
}
