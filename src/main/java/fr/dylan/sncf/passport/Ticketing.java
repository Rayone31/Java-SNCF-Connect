package fr.dylan.sncf.passport;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dylan.sncf.place.City;
import fr.dylan.sncf.place.Station;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ticketing {

    private static final String TICKETS_FILE_PATH = "src/main/java/fr/dylan/sncf/Data/tickets.json";

    public static void enregistrerTicket(String departureCity, String departureStation, String arrivalCity, String arrivalStation, String chosenHour) {
        Ticket ticket = new Ticket(departureCity, departureStation, arrivalCity, arrivalStation, chosenHour);
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(TICKETS_FILE_PATH);
        List<Ticket> tickets = new ArrayList<>();
        if (file.exists()) {
            try {
                tickets = mapper.readValue(file, new TypeReference<List<Ticket>>() {});
            } catch (IOException e) {
                System.err.println("Error reading existing tickets. " + e.getMessage());
            }
        }
        tickets.add(ticket);
        try {
            mapper.writeValue(file, tickets);
            System.out.println("Ticket registered successfully.");
        } catch (IOException e) {
            System.err.println("Error saving ticket. " + e.getMessage());
        }
    }

    public static void afficherBillets() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(TICKETS_FILE_PATH);
        try {
            List<Ticket> tickets = mapper.readValue(file, new TypeReference<List<Ticket>>() {});
            int ticketNumber = 1; // Initialiser le compteur de tickets
            for (Ticket ticket : tickets) {
                // Utilisez directement les valeurs String retournées par les getters
                String departureCityName = ticket.getDepartureCity() != null ? ticket.getDepartureCity() : "Ville de départ inconnue";
                String departureStationName = ticket.getDepartureStation() != null ? ticket.getDepartureStation() : "Gare de départ inconnue";
                String arrivalCityName = ticket.getArrivalCity() != null ? ticket.getArrivalCity() : "Ville d'arrivée inconnue";
                String arrivalStationName = ticket.getArrivalStation() != null ? ticket.getArrivalStation() : "Gare d'arrivée inconnue";
                String chosenHour = ticket.getChosenHour() != null ? ticket.getChosenHour() : "Heure choisie inconnue";

                // Afficher le numéro du ticket avant les détails
                System.out.println("Ticket " + ticketNumber + " :");
                System.out.println("Ville de départ : " + departureCityName);
                System.out.println("Gare de départ : " + departureStationName);
                System.out.println("Ville d'arrivée : " + arrivalCityName);
                System.out.println("Gare d'arrivée : " + arrivalStationName);
                System.out.println("Heure choisie : " + chosenHour);
                System.out.println(); // Ajouter une ligne vide pour séparer les tickets

                ticketNumber++; // Incrémenter le compteur de tickets
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture des tickets. " + e.getMessage());
        }
    }

    public static void supprimerTicket(int ticketNumber) {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(TICKETS_FILE_PATH);
        try {
            List<Ticket> tickets = mapper.readValue(file, new TypeReference<List<Ticket>>() {});
            if (ticketNumber > 0 && ticketNumber <= tickets.size()) {
                tickets.remove(ticketNumber - 1); 
                mapper.writeValue(file, tickets);
            } else {
                System.out.println("Numéro de ticket invalide. Veuillez entrer un numéro de ticket valide.");
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture des tickets. " + e.getMessage());
        }
    }
}
