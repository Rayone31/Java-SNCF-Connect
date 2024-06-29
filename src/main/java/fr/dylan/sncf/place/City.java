package fr.dylan.sncf.place;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class City {
    private String name;
    private List<String> stations;
    private static List<City> cities = new ArrayList<>();

    // Constructeur par défaut
    public City() {
    }

    public City(String name, List<String> stations) {
        this.name = name;
        this.stations = stations;
    }

    public String getName() {
        return name;
    }

    public List<String> getStations() {
        return stations;
    }

    public void setStations(List<String> stations) {
        this.stations = stations;
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