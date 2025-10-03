package com.iotmonitoring.model;

public class Threshold {
    private double minValue;
    private double maxValue;
    
    public Threshold(double minValue, double maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }
    public boolean isBreached(double v) {
        return (v < minValue || v > maxValue);
    }
    
    @Override
    public String toString() {
        return "Threshold[min=" + minValue + ", max=" + maxValue + "]";
    }
}
