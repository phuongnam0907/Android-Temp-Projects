package com.example.phuon.ledblink;

import android.os.Build;

import com.google.android.things.pio.PeripheralManager;

import java.util.List;

public class BoardDefaults {
    private static final String DEVICE_EDISON_ARDUINO = "edison_arduino";
    private static final String DEVICE_EDISON = "edison";
    private static final String DEVICE_RPI3 = "rpi3";
    private static final String DEVICE_NXP =  "imx6ul";
    private static String sBoardVariant = "";

    private static String getBoardVariant () {
        if (!sBoardVariant.isEmpty()) {
            return sBoardVariant;
        }
        sBoardVariant = Build.DEVICE;
        if (sBoardVariant.equals(DEVICE_EDISON)) {
            PeripheralManager pioService = PeripheralManager.getInstance();
            List<String> gpioList = pioService.getGpioList();
            if (gpioList.size() != 0) {
                String pin = gpioList.get(0);
                if (pin.startsWith("IO")) {
                    sBoardVariant = DEVICE_EDISON_ARDUINO;
                }
            }
        }
        return sBoardVariant;
    }

    public static String getGPIOForLED() {
        switch (getBoardVariant()) {
            case DEVICE_EDISON_ARDUINO:
                return "IO13";
            case DEVICE_EDISON:
                return "GP45";
            case DEVICE_RPI3:
                return "BCM6";
            case DEVICE_NXP:
                return "GPIO4_IO20";
            default:
                throw new IllegalStateException("Unknown Build.DEVICE " + Build.DEVICE);
        }
    }
    public static String getGPIOForLED1() {
        return "BCM5";
    }
    public static String getGPIOForLED2() {
        return "BCM13";
    }
    public static String getGPIOForLED3() {
        return "BCM19";
    }
    public static String getGPIOForLED4() {
        return "BCM26";
    }
}
