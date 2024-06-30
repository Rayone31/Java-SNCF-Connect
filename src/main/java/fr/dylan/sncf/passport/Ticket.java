package fr.dylan.sncf.passport;

import fr.dylan.sncf.place.City;
import fr.dylan.sncf.place.Station;


// Ticket class
class Ticket {
    private String departureCity;
    private String departureStation;
    private String arrivalCity;
    private String arrivalStation;
    private String chosenHour;

    // Constructor
    public Ticket(String departureCity, String departureStation, String arrivalCity, String arrivalStation, String chosenHour) {
        this.departureCity = departureCity;
        this.departureStation = departureStation;
        this.arrivalCity = arrivalCity;
        this.arrivalStation = arrivalStation;
        this.chosenHour = chosenHour;
    }

    
    public Ticket() {
    }

    // Getters
    public String getDepartureCity() { return departureCity; }

    public String getDepartureStation() { return departureStation; }

    public String getArrivalCity() { return arrivalCity; }

    public String getArrivalStation() { return arrivalStation; }

    public String getChosenHour() { return chosenHour; }
}