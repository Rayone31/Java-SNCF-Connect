package fr.dylan.sncf.initialisazion;

import fr.dylan.sncf.passport.Ticketing;
import fr.dylan.sncf.place.City;
import fr.dylan.sncf.place.Route;
import fr.dylan.sncf.place.Station;
import fr.dylan.sncf.colorpanel.color;

import java.util.stream.Collectors;
import java.util.Scanner;
import java.util.List;

public class init {
    private Scanner scanner;
    

    // Constructor
    public init() {
        this.scanner = new Scanner(System.in);
    }

    // Start dialogue
    public void startDialogue() {
        message_accueil();

        // command loop
        while (true) {
            String userInput = scanner.nextLine();

            if ("Travel".equals(userInput)) {
                travel();
            }

            if ("Tickets".equals(userInput)) {
                ticket();
            }

            if ("quit".equals(userInput)) {
                color.printColor("Bye !", color.Red());
                break;
            }
        }

        scanner.close();
    }

    private void message_accueil() {
        color.printColor("Welcome to Sncf-connect.", color.Blue());
        color.printColor("Here are the available commands:", color.Green());
        color.printColor("Travel: to start looking for a route", color.Green());
        color.printColor("Tickets: to access the tickets", color.Green());
        color.printColor("quit: to exit the application", color.Green());
    }

    // Travel method to find a route
    private void travel() {
    color.printColor("You have chosen to travel", color.Blue());
        List<Route> routes = Route.fromJson();
        List<String> cities = routes.stream().map(route -> route.getCity().getName()).collect(Collectors.toList());

        // Departure city
        String departureCity = "";
        while (!cities.contains(departureCity)) {
            color.printColor("Here are the cities available:", color.Yellow());
            for (String city : cities) {
                color.printColor(city, color.Green());
            }
            color.printColor("Where are you located?", color.Blue());
            departureCity = scanner.nextLine();
            if (!cities.contains(departureCity)) {
                color.printColor("Unrecognized city. Try Again.", color.Red());
            }
        }

        List<Route> departureRoutes = Route.updateRoutes(routes, departureCity);
        List<String> departureStations = departureRoutes.stream()
            .flatMap(route -> route.getStations().stream())
            .map(station -> station.getName())
            .collect(Collectors.toList());
        // Departure station
        String departureStation = "";
        while (!departureStations.contains(departureStation)) {
           color.printColor("Here are the stations available in this city:", color.Yellow());
            for (String station : departureStations) {
                color.printColor(station, color.Green());            }
                color.printColor("What is your departure station?", color.Blue());
            departureStation = scanner.nextLine();
            if (!departureStations.contains(departureStation)) {
                color.printColor("Unrecognized station. Try Again.", color.Red());
            }
        }
        // Arrival city
        String arrivalCity = "";
        while (!cities.contains(arrivalCity) || arrivalCity.equals(departureCity)) {
            color.printColor("Here are the cities available:", color.Yellow());
            for (String city : cities) {
                color.printColor(city, color.Green());
            }
            color.printColor("Where do you want to go?", color.Blue());
            arrivalCity = scanner.nextLine();
            if (!cities.contains(arrivalCity)) {
               color.printColor("Unrecognized city. Try Again.", color.Red());
            } else if (arrivalCity.equals(departureCity)) {
                color.printColor("Arrival city cannot be the same as departure city. Please choose a different city.", color.Red());
                arrivalCity = ""; 
            }
        }

        List<Route> arrivalRoutes = Route.updateRoutes(routes, arrivalCity);
        List<String> arrivalStations = arrivalRoutes.stream()
            .flatMap(route -> route.getStations().stream())
            .map(station -> station.getName()) 
            .collect(Collectors.toList());

        // Arrival station
        String arrivalStation = "";
        while (!arrivalStations.contains(arrivalStation)) {
            color.printColor("Here are the available times:", color.Yellow());
            for (String station : arrivalStations) {
                color.printColor(station, color.Green());
            }
            color.printColor("What is your arrival station?", color.Blue());
            arrivalStation = scanner.nextLine();
            if (!arrivalStations.contains(arrivalStation)) {
                color.printColor("Unrecognized station. Try Again.", color.Red());
            }
        }

        color.printColor("Here are the available times:", color.Yellow());


        for (Route route : departureRoutes) {
            List<String> hourly = route.getHourlyRoute();


            for (String hour : hourly) {
                color.printColor("Hourly :" + hour, color.Green());
            }
        }
        // Chosen hour
        String chosenHour = "";

        while (chosenHour.isEmpty()) {
            color.printColor("Please choose a time from those available (cannot be empty):", color.Blue());
            chosenHour = scanner.nextLine();

            if (chosenHour.isEmpty()) {
                color.printColor("You must enter a time.", color.Red());
            }
        }
        // Confirmation
        color.printColor("You chose the time: " + chosenHour, color.Green());

        color.printColor("Here is the summary of your trip:", color.Yellow());
        color.printColor("Departure city :" + departureCity, color.Green());
        color.printColor("Departure station : " + departureStation, color.Green());
        color.printColor("Arrival city: " + arrivalCity, color.Green());
        color.printColor("Arrival station: " + arrivalStation, color.Green());
        color.printColor("Selected time: " + chosenHour, color.Green());

        color.printColor("Do you want to confirm this information? (yes/no)?", color.Blue());
        String confirmation = scanner.nextLine();
        String departureCityObj = City.getCityByName(departureCity);
        String departureStationObj = Station.getStationByName(departureStation);
        String arrivalCityObj = City.getCityByName(arrivalCity);
        String arrivalStationObj = Station.getStationByName(arrivalStation);
        
        if ("yes".equals(confirmation)) {
            color.printColor("Your trip has been confirmed. Enjoy your trip!", color.Green());
            Ticketing.enregistrerTicket(departureCity, departureStation, arrivalCity, arrivalStation, chosenHour);
            startDialogue();
        } else {
            color.printColor("Your trip has not been confirmed. Please start again.", color.Red());
            startDialogue();
        }
    }

    // Ticket method to access the tickets
    private void ticket() {
        color.printColor("You have chosen to access the tickets", color.Green());
        Ticketing.afficherBillets();
        message_ticket();
        
        while (true) {
            String userInput = scanner.nextLine();
            // Return to the main menu
            if ("return".equals(userInput)) {
               color.printColor("Do you really want to return to the main menu? (yes/no)", color.Blue());
                String confirmation = scanner.nextLine();
                if ("yes".equals(confirmation)) {
                    startDialogue();
                    break; 
                } else if ("no".equals(confirmation)) {
                    message_ticket();
                } else {
                    color.printColor("Invalid input. Please answer 'yes' or 'no'.", color.Red());
                }
            }
            // Delete ticket
            if (userInput.startsWith("delete")) {
                String[] parts = userInput.split(" ");
                if (parts.length == 2) {
                    try {
                        int ticketNumber = Integer.parseInt(parts[1]);
                        Ticketing.supprimerTicket(ticketNumber);
                        color.printColor("Ticket number " + ticketNumber + " has been deleted.", color.Green());
                    } catch (NumberFormatException e) {
                       color.printColor("Invalid ticket number. Please enter a valid number.", color.Red());
                    }
                } else {
                    color.printColor("Invalid input. Please enter 'delete' followed by the ticket number.", color.Red());
                }
                ticket();
            }

            color.printColor("You entered:" + userInput, color.Blue());
        }

        scanner.close();

       
    }

    private void message_ticket() {
        color.printColor("Here are the available commands:", color.Yellow());
        color.printColor("delete (number ticket): to delete a ticket", color.Green());
        color.printColor("return: to return to the main menu", color.Green());
    }
    
}