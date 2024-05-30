package places;

import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class city {
    public static void main(String[] args) {
        System.out.println("Ou vous vous trouvez ?");

        try {
            String content = new String(Files.readAllBytes(Paths.get("Sncf-connect/Data/city.json")));
            JSONArray jsonArray = new JSONArray(content);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String ville = jsonObject.getString("name");
                System.out.println((i + 1) + ". " + ville);
            }

            System.out.println("Veuillez entrer le numéro de la ville :");
            Scanner scanner = new Scanner(System.in);
            int villeIndex = scanner.nextInt() - 1;

            if (villeIndex >= 0 && villeIndex < jsonArray.length()) {
                JSONObject selectedCity = jsonArray.getJSONObject(villeIndex);
                System.out.println("Vous avez sélectionné : " + selectedCity.getString("name"));

                JSONArray gares = selectedCity.getJSONArray("stations");
                for (int i = 0; i < gares.length(); i++) {
                    System.out.println((i + 1) + ". " + gares.getString(i));
                }

                System.out.println("Veuillez entrer le numéro de la gare :");
                int gareIndex = scanner.nextInt() - 1;

                if (gareIndex >= 0 && gareIndex < gares.length()) {
                    String selectedStation = gares.getString(gareIndex);
                    System.out.println("Vous avez sélectionné : " + selectedStation);

                    System.out.println("Vous avez choisi la ville " + selectedCity.getString("name") + " et la gare " + selectedStation + ". Est-ce correct ? (oui/non)");
                    String confirmation = scanner.next();

                    if (confirmation.equalsIgnoreCase("oui")) {
                        System.out.println("Merci pour votre confirmation.");

                        System.out.println("Ou souhaitez-vous aller ?");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            if (i != villeIndex) { // Ne pas afficher la ville actuelle
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String ville = jsonObject.getString("name");
                                System.out.println((i + 1) + ". " + ville);
                            }
                        }

                        System.out.println("Veuillez entrer le numéro de la ville de destination :");
                        int destinationIndex = scanner.nextInt() - 1;

                        if (destinationIndex >= 0 && destinationIndex < jsonArray.length() && destinationIndex != villeIndex) {
                            JSONObject destinationCity = jsonArray.getJSONObject(destinationIndex);
                            System.out.println("Vous avez choisi d'aller à : " + destinationCity.getString("name"));

                            JSONArray destinationGares = destinationCity.getJSONArray("stations");
                            for (int i = 0; i < destinationGares.length(); i++) {
                                System.out.println((i + 1) + ". " + destinationGares.getString(i));
                            }

                            System.out.println("Veuillez entrer le numéro de la gare de destination :");
                            int destinationGareIndex = scanner.nextInt() - 1;

                            if (destinationGareIndex >= 0 && destinationGareIndex < destinationGares.length()) {
                                String destinationStation = destinationGares.getString(destinationGareIndex);
                                System.out.println("Vous avez choisi d'aller à la gare : " + destinationStation);

                                System.out.println("Vous avez choisi de voyager de " + selectedCity.getString("name") + " à " + destinationCity.getString("name") + " de la gare " + selectedStation + " à la gare " + destinationStation + ". Est-ce correct ? (oui/non)");
                                confirmation = scanner.next();
                                
                                if (confirmation.equalsIgnoreCase("oui")) {
                                    System.out.println("Merci pour votre confirmation.");
                                } else {
                                    System.out.println("Veuillez recommencer la sélection.");
                                }
                            } else {
                                System.out.println("Numéro de gare de destination invalide.");
                            }

                        } else {
                            System.out.println("Numéro de ville de destination invalide.");
                        }

                    } else {
                        System.out.println("Veuillez recommencer la sélection.");
                    }

                } else {
                    System.out.println("Numéro de gare invalide.");
                }

            } else {
                System.out.println("Numéro de ville invalide.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}