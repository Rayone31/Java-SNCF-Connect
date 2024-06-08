package fr.dylan.sncf.place;

import java.util.List;

public class City {
    private String name;
    private List<String> stations;

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
}