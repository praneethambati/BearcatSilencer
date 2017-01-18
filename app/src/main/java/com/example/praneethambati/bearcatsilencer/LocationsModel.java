package com.example.praneethambati.bearcatsilencer;

/**
 * Created by Praneeth Ambati on 1/17/2017.
 */

public class LocationsModel {
    String locationName;
    double lat;
    double lng;
    boolean isSelected;

    public LocationsModel(String locationName, double lat, double lng, boolean isSelected) {
        this.locationName = locationName;
        this.lat = lat;
        this.lng = lng;
        this.isSelected = isSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
