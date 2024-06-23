package com.solenix.dto;

public class SatelliteData {

    private String name;
    private String line1;
    private String line2;

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    @Override
    public String toString() {
        return "SatelliteData{" +
                "name='" + name + '\'' +
                ", line1='" + line1 + '\'' +
                ", line2='" + line2 + '\'' +
                '}';
    }
}
