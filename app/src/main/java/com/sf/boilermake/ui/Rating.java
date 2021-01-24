package com.sf.boilermake.ui;

import java.io.Serializable;


public class Rating implements Serializable {
    private boolean isMaskRequired;
    private boolean isLocationSafe;
    private boolean isRestaurantLocation;
    private boolean isIndoorSeating;
    private boolean isOutdoorSeating;
    private boolean isHandSanitizer;
    private String address;
    private String name;
    private String id;
    public Rating(String id, String address, String name, boolean isMaskRequired, boolean isLocationSafe, boolean isRestaurantLocation,
                  boolean isIndoorSeating, boolean isOutdoorSeating, boolean isHandSanitizer) {
        this.isMaskRequired = isMaskRequired;
        this.isLocationSafe = isLocationSafe;
        this.isRestaurantLocation = isRestaurantLocation;
        this.isIndoorSeating = isIndoorSeating;
        this.isOutdoorSeating = isOutdoorSeating;
        this.isHandSanitizer = isHandSanitizer;
        this.address = address;
        this.name = name;
    }
    public boolean isMaskRequired() {
        return isMaskRequired;
    }
    public boolean isLocationSafe() {
        return isLocationSafe;
    }
    public boolean isRestaurantLocation() {
        return isRestaurantLocation;
    }
    public boolean isIndoorSeating() {
        return isIndoorSeating;
    }
    public boolean isOutdoorSeating() {
        return isOutdoorSeating;
    }
    public boolean isHandSanitizer() {
        return isHandSanitizer;
    }
    public String getId() {
        return id;
    }
    public String getAddress() {
        return address;
    }
    public String getName() {
        return name;
    }
    public void setMaskRequired(boolean maskRequired) {
        isMaskRequired = maskRequired;
    }
    public void setLocationSafe(boolean locationSafe) {
        isLocationSafe = locationSafe;
    }
    public void setRestaurantLocation(boolean restaurantLocation) {
        isRestaurantLocation = restaurantLocation;
    }
    public void setIndoorSeating(boolean indoorSeating) {
        isIndoorSeating = indoorSeating;
    }
    public void setOutdoorSeating(boolean outdoorSeating) {
        isOutdoorSeating = outdoorSeating;
    }
    public void setHandSanitizer(boolean handSanitizer) {
        isHandSanitizer = handSanitizer;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setId(String id) {
        this.id = id;
    }
}