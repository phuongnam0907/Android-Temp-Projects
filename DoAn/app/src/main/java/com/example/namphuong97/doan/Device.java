package com.example.namphuong97.doan;

import java.util.ArrayList;

/**
 * Created by namphuong97 on 3/13/18.
 */

public class Device {
    String mssv;
    float temperature;
    float humid;
    long time;

    public Device(String mssv, float temperature, float humid, long time) {
        this.mssv = mssv;
        this.temperature = temperature;
        this.humid = humid;
        this.time = time;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
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
