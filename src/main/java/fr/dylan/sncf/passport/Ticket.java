package fr.dylan.sncf.passport;

import fr.dylan.sncf.place.City;
import fr.dylan.sncf.place.Station;



class Ticket {
    private String departureCity;
    private String departureStation;
    private String arrivalCity;
    private String arrivalStation;
    private String chosenHour;

    public Ticket(String departureCity, String departureStation, String arrivalCity, String arrivalStation, String chosenHour) {
        this.departureCity = departureCity;
        this.departureStation = departureStation;
        this.arrivalCity = arrivalCity;
        this.arrivalStation = arrivalStation;
        this.chosenHour = chosenHour;
    }

    public Ticket() {
    }

    public String getDepartureCity() { return departureCity; }
    public void setDepartureCity(String departureCity) { this.departureCity = departureCity; }

    public String getDepartureStation() { return departureStation; }
    public void setDepartureStation(String departureStation) { this.departureStation = departureStation; }

    public String getArrivalCity() { return arrivalCity; }
    public void setArrivalCity(String arrivalCity) { this.arrivalCity = arrivalCity; }

    public String getArrivalStation() { return arrivalStation; }
    public void setArrivalStation(String arrivalStation) { this.arrivalStation = arrivalStation; }

    public String getChosenHour() { return chosenHour; }
    public void setChosenHour(String chosenHour) { this.chosenHour = chosenHour; }
}