package fr.dylan.sncf.place;

import java.util.ArrayList;
import java.util.List;

// City class
public class City {
    // Attributes
    private String name;
    private List<String> stations;
    private static List<City> cities = new ArrayList<>();

    // Constructor Default
    public City() {
    }

    // Constructor
    public City(String name, List<String> stations) {
        this.name = name;
        this.stations = stations;
    }

    // Get city by name 
    public String getName() {
        return name;
    }

    // Get stations
    public List<String> getStations() {
        return stations;
    }

    public static String getCityByName(String name) {
        for (City city : cities) { 
            if (city.getName().equals(name)) {
                return city.getName(); 
            }
        }
        return null; 
    }
}