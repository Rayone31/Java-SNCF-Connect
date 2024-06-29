package fr.dylan.sncf.place;

import java.util.ArrayList;
import java.util.List;

public class Station {
    private String name;
    private static List<Station> stations = new ArrayList<>();

    public Station() {
    }

    public Station(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static String getStationByName(String name) {
        for (Station station : stations) { 
            if (station.getName().equals(name)) {
                return station.getName();
            }
        }
        return null;
    }
}