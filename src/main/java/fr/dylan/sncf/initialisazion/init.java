package fr.dylan.sncf.initialisazion;

import fr.dylan.sncf.place.Route;
import java.util.stream.Collectors;
import java.util.Scanner;
import java.util.List;


public class init {
    private Scanner scanner;

    public init() {
        this.scanner = new Scanner(System.in);
    }

    public void startDialogue() {
        System.out.println("Bienvenue dans Sncf-connect.");

        System.out.println("Voici les commandes disponibles :");

        System.out.println("Voyager : pour commencer à chercher un trajet");

        System.out.println("Billets : pour acceder aux billets");

        System.out.println("quit : pour quitter l'application");

        while (true) {
            String userInput = scanner.nextLine();

            if ("Voyager".equals(userInput)) {
                voyage();
            }

            if ("quit".equals(userInput)) {
                System.out.println("Au revoir !");
                break;
            }

            System.out.println("Vous avez entré : " + userInput);
        }

        scanner.close();
    }

    private void voyage() {
        System.out.println("Vous avez choisi de voyager");

        List<Route> routes = Route.fromJson();
        List<String> cities = routes.stream().map(Route::getCity).collect(Collectors.toList());

        String departureCity = "";
        while (!cities.contains(departureCity)) {
            System.out.println("Voici les villes disponibles :");
            for (String city : cities) {
                System.out.println(city);
            }
            System.out.println("où vous situer vous ?");
            departureCity = scanner.nextLine();
            if (!cities.contains(departureCity)) {
                System.out.println("Ville non reconnue. Veuillez réessayer.");
            }
        }

        List<Route> departureRoutes = Route.updateRoutes(routes, departureCity);
        List<String> departureStations = departureRoutes.stream().flatMap(route -> route.getStations().stream()).collect(Collectors.toList());

        String departureStation = "";
        while (!departureStations.contains(departureStation)) {
            System.out.println("Voici les gares disponibles dans cette ville :");
            for (String station : departureStations) {
                System.out.println(station);
            }
            System.out.println("Quelle est votre gare de départ ?");
            departureStation = scanner.nextLine();
            if (!departureStations.contains(departureStation)) {
                System.out.println("Gare non reconnue. Veuillez réessayer.");
            }
        }

        String arrivalCity = "";
        while (!cities.contains(arrivalCity)) {
            System.out.println("Voici les villes disponibles :");
            for (String city : cities) {
                System.out.println(city);
            }
            System.out.println("Où voulez-vous aller ?");
            arrivalCity = scanner.nextLine();
            if (!cities.contains(arrivalCity)) {
                System.out.println("Ville non reconnue. Veuillez réessayer.");
            }
        }

        List<Route> arrivalRoutes = Route.updateRoutes(routes, arrivalCity);
        List<String> arrivalStations = arrivalRoutes.stream().flatMap(route -> route.getStations().stream()).collect(Collectors.toList());

        String arrivalStation = "";
        while (!arrivalStations.contains(arrivalStation)) {
            System.out.println("Voici les gares disponibles dans cette ville :");
            for (String station : arrivalStations) {
                System.out.println(station);
            }
            System.out.println("Quelle est votre gare d'arrivée ?");
            arrivalStation = scanner.nextLine();
            if (!arrivalStations.contains(arrivalStation)) {
                System.out.println("Gare non reconnue. Veuillez réessayer.");
            }
        }

        // system de choix d'heure 
        System.out.println("Voici les horaires disponibles :");

        // Pour chaque route de départ
        for (Route route : departureRoutes) {
            List<String> hourly = route.getHourlyRoute();

            // Pour chaque période
            for (String hour : hourly) {
                System.out.println("Horaire : " + hour);
            }
        }

        // Demander à l'utilisateur de choisir une heure
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez choisir une heure parmi celles disponibles :");
        String chosenHour = scanner.nextLine();

        System.out.println("Vous avez choisi l'heure : " + chosenHour);

        // Afficher un résumé des choix de l'utilisateur
        System.out.println("Voici le résumé de votre voyage :");
        System.out.println("Ville de départ : " + departureCity);
        System.out.println("Gare de départ : " + departureStation);
        System.out.println("Ville d'arrivée : " + arrivalCity);
        System.out.println("Gare d'arrivée : " + arrivalStation);
        System.out.println("Heure choisie : " + chosenHour);

        // Demander à l'utilisateur de confirmer
        System.out.println("Voulez-vous confirmer ces informations ? (yes/no)");
        String confirmation = scanner.nextLine();

        if ("yes".equals(confirmation)) {
            System.out.println("Votre voyage a été confirmé. Bon voyage !");
        } else {
            System.out.println("Votre voyage n'a pas été confirmé. Veuillez recommencer.");
            startDialogue();
        }
    }
}
