package com.osbg;

import com.univocity.parsers.annotations.Parsed;

public class Asset {
    @Parsed
    private String containerid;

    @Parsed
    private String createdOn;

    @Parsed
    private String make;

    @Parsed
    private String model;

    @Parsed
    private String serial;

    @Parsed
    private String ko;

    @Parsed
    private String assetcase;

    @Parsed
    private String audio;

    @Parsed
    private String odd;

    @Parsed
    private String kb;

    @Parsed
    private String motherboard;

    @Parsed
    private String memory;

    @Parsed
    private String wwan;

    @Parsed
    private String psu;

    @Parsed
    private String wifi;

    @Parsed
    private String fan;

    @Parsed
    private String cpu;

    @Parsed
    private String tft;

    @Parsed
    private String usb;

    @Parsed
    private String location;

    @Parsed
    private String modifiedBy;

    @Parsed
    private String modifiedDate;

    public String getSerial() {
        return serial;
    }

    public String getLocation() {
        return location;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }
}
