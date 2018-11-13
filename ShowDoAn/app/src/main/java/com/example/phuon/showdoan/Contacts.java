package com.example.phuon.showdoan;

public class Contacts {
    private String id;
    private float temperature;
    private float humid;
    private long time;

    public Contacts(String id, float temperature, float humid, long time) {
        this.id = id;
        this.temperature = temperature;
        this.humid = humid;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumid() {
        return humid;
    }

    public void setHumid(float humid) {
        this.humid = humid;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
