package com.iotmonitoring.main;

import com.iotmonitoring.model.*;
import java.util.*;

public class MainApp {
    private static Scanner sc = new Scanner(System.in);
    private static List<Device> devices = new ArrayList<>();
    private static List<Alert> alerts = new ArrayList<>();
    private static List<MaintenanceTicket> tickets = new ArrayList<>();
    
    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n==== IoT Device Monitoring System ====");
            System.out.println("1. Add Device");
            System.out.println("2. Add Sensor");
            System.out.println("3. Set Threshold");
            System.out.println("4. Ingest Reading");
            System.out.println("5. Generate Alert");
            System.out.println("6. Acknowledge Alert");
            System.out.println("7. Create Maintenance Ticket");
            System.out.println("8. Close Ticket");
            System.out.println("9. Show Alerts");
            System.out.println("10. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            
            switch(choice) {
                case 1: addDevice(); break;
                case 2: addSensor(); break;
                case 3: setThreshold(); break;
                case 4: ingestReading(); break;
                case 5: generateAlert(); break;
                case 6: acknowledgeAlert(); break;
                case 7: createTicket(); break;
                case 8: closeTicket(); break;
                case 9: showAlerts(); break;
                case 10: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice!");
            }
        } while(choice != 10);
    }
    
    private static void addDevice() {
        System.out.print("Enter Device ID: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Enter Device Name: ");
        String name = sc.nextLine();
        devices.add(new Device(id, name));
        System.out.println("Device added.");
    }
    
    private static void addSensor() {
        if (devices.isEmpty()) { System.out.println("No devices available."); return; }
        System.out.print("Enter Device ID: ");
        int did = sc.nextInt(); sc.nextLine();
        Device d = devices.stream().filter(dev -> dev.getId() == did).findFirst().orElse(null);
        if (d == null) { System.out.println("Device not found."); return; }
        System.out.print("Enter Sensor ID: ");
        int sid = sc.nextInt(); sc.nextLine();
        System.out.print("Enter Sensor Type: ");
        String type = sc.nextLine();
        d.addSensor(new Sensor(sid, type));
        System.out.println("Sensor added to device.");
    }
    
    private static void setThreshold() {
        System.out.print("Enter Device ID: ");
        int did = sc.nextInt(); sc.nextLine();
        Device d = devices.stream().filter(dev -> dev.getId() == did).findFirst().orElse(null);
        if (d == null) { System.out.println("Device not found."); return; }
        System.out.print("Enter Sensor ID: ");
        int sid = sc.nextInt(); sc.nextLine();
        Sensor s = d.getSensor(sid);
        if (s == null) { System.out.println("Sensor not found."); return; }
        System.out.print("Enter Min Value: ");
        double min = sc.nextDouble();
        System.out.print("Enter Max Value: ");
        double max = sc.nextDouble();
        s.setThreshold(new Threshold(min, max));
        System.out.println("Threshold set.");
    }
    
    private static void ingestReading() {
        System.out.print("Enter Device ID: ");
        int did = sc.nextInt(); sc.nextLine();
        Device d = devices.stream().filter(dev -> dev.getId() == did).findFirst().orElse(null);
        if (d == null) { System.out.println("Device not found."); return; }
        System.out.print("Enter Sensor ID: ");
        int sid = sc.nextInt(); sc.nextLine();
        Sensor s = d.getSensor(sid);
        if (s == null) { System.out.println("Sensor not found."); return; }
        System.out.print("Enter Reading Value: ");
        double val = sc.nextDouble();
        Reading r = new Reading(val, new Date());
        s.addReading(r);
        System.out.println("Reading added.");
    }
    
    private static void generateAlert() {
        for (Device d : devices) {
            for (Sensor s : d.getSensors()) {
                for (Reading r : s.getReadings()) {
                    if (s.getThreshold() != null && s.getThreshold().isBreached(r.getValue())) {
                        Alert a = new Alert(alerts.size()+1, "HIGH", "OPEN", r);
                        alerts.add(a);
                        System.out.println("ALERT GENERATED: " + a);
                    }
                }
            }
        }
    }
    
    private static void acknowledgeAlert() {
        System.out.print("Enter Alert ID: ");
        int aid = sc.nextInt(); sc.nextLine();
        for (Alert a : alerts) {
            if (a.getId() == aid) {
                a.setStatus("ACKNOWLEDGED");
                System.out.println("Alert acknowledged.");
                return;
            }
        }
        System.out.println("Alert not found.");
    }
    
    private static void createTicket() {
        System.out.print("Enter Alert ID: ");
        int aid = sc.nextInt(); sc.nextLine();
        Alert a = alerts.stream().filter(al -> al.getId() == aid).findFirst().orElse(null);
        if (a == null) { System.out.println("Alert not found."); return; }
        MaintenanceTicket t = new MaintenanceTicket(tickets.size()+1, "OPEN", a);
        tickets.add(t);
        System.out.println("Ticket created: " + t);
    }
    
    private static void closeTicket() {
        System.out.print("Enter Ticket ID: ");
        int tid = sc.nextInt(); sc.nextLine();
        MaintenanceTicket t = tickets.stream().filter(tc -> tc.getId() == tid).findFirst().orElse(null);
        if (t == null) { System.out.println("Ticket not found."); return; }
        t.setStatus("CLOSED");
        System.out.println("Ticket closed.");
    }
    
    private static void showAlerts() {
        if (alerts.isEmpty()) { System.out.println("No alerts."); return; }
        for (Alert a : alerts) System.out.println(a);
    }
}
