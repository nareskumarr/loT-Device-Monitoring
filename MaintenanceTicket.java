package com.iotmonitoring.model;

public class MaintenanceTicket {
    private int id;
    private String status; // OPEN, CLOSED
    private String resolutionNotes;
    private Alert alert;
    
    public MaintenanceTicket(int id, String status, Alert alert) {
        this.id = id;
        this.status = status;
        this.alert = alert;
    }
    public int getId() { return id; }
    public String getStatus() { return status; }
    public void setStatus(String s) { this.status = s; }
    
    @Override
    public String toString() {
        return "Ticket[id=" + id + ", status=" + status + ", alertId=" + alert.getId() + "]";
    }
}
