package com.iotmonitoring.model;

public class Alert {
    private int id;
    private String severity;
    private String status; // OPEN, ACKNOWLEDGED, RESOLVED
    private Reading reading;
    
    public Alert(int id, String severity, String status, Reading reading) {
        this.id = id;
        this.severity = severity;
        this.status = status;
        this.reading = reading;
    }
    public int getId() { return id; }
    public String getStatus() { return status; }
    public void setStatus(String s) { this.status = s; }
    
    @Override
    public String toString() {
        return "Alert[id=" + id + ", severity=" + severity + ", status=" + status + ", reading=" + reading + "]";
    }
}
