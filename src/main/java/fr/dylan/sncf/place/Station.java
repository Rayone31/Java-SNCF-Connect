package fr.dylan.sncf.place;

import java.util.ArrayList;
import java.util.List;


// Station class
public class Station {
    private String name;
    private static List<Station> stations = new ArrayList<>();
    
    // Constructor Default
    public Station() {
    }
    
    // Constructor
    public Station(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Get stations
    public static String getStationByName(String name) {
        for (Station station : stations) { 
            if (station.getName().equals(name)) {
                return station.getName();
            }
        }
        return null;
    }
}