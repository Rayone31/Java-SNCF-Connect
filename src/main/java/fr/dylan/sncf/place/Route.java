package fr.dylan.sncf.place;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Route {
    private City city;
    private List<Station> stations;
    private Hourly hourly;

    // Le constructeur accepte maintenant un objet City et une liste d'objets Station
    public Route(City city, List<Station> stations) {
        this.city = city;
        this.stations = stations;
        this.hourly = Hourly.generateHourly();
    }

    public City getCity() {
        return city;
    }

    public List<Station> getStations() {
        return stations;
    }

    public Hourly getHourly() {
        return hourly;
    }


    public static List<Route> fromJson() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<City>> typeReference = new TypeReference<List<City>>(){};
        InputStream inputStream = Route.class.getResourceAsStream("/fr/dylan/sncf/Data/city.json");
        try {
            List<City> cities = mapper.readValue(inputStream, typeReference);
            List<Route> routes = new ArrayList<>();
            for (City city : cities) {
                List<String> stationNames = city.getStations();
                List<Station> stations = new ArrayList<>();
                for (String name : stationNames) {
                    stations.add(new Station(name));
                }
                routes.add(new Route(city, stations));
            }
            return routes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Cette méthode peut rester inchangée si elle fonctionne comme prévu
    public static List<Route> updateRoutes(List<Route> routes, String selectedCity) {
        List<Route> updatedRoutes = new ArrayList<>();
        for (Route route : routes) {
            if (route.city.getName().equals(selectedCity)) {
                updatedRoutes.add(route);
            }
        }
        return updatedRoutes;
    }

    // Cette méthode peut rester inchangée si elle fonctionne comme prévu
    public List<String> getHourlyRoute() {
        List<String> hourlyAsString = new ArrayList<>();
        int[] startHours = this.hourly.getStartHours();
        int[] endHours = this.hourly.getEndHours();
        int[] durations = this.hourly.getDurations();

        for (int i = 0; i < startHours.length; i++) {
            hourlyAsString.add("Début : " + startHours[i] + "h, Fin : " + endHours[i] + "h, Durée : " + durations[i] + "h");
        }
        return hourlyAsString;
    }
}