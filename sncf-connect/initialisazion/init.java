package initialisazion;
import java.util.Scanner;

public class init {
    public static void main(String[] args) {
        System.out.println("Bienvenue sur SNCF Connect");

        System.out.println("Que voulez-vous faire ?");

        System.out.println("1. Voyager");

        System.out.println("2. Billets");

        System.out.println("3. Offres");

        System.out.println("4. Quitter");

        Scanner scanner = new Scanner(System.in);
        int choix = scanner.nextInt();

        switch (choix) {
            case 1:
                System.out.println("Vous avez choisi de voyager.");
                places.city.main(args);
                break;
            case 2:
                System.out.println("Vous avez choisi les billets.");
                break;
            case 3:
                System.out.println("Vous avez choisi les offres.");
                break;
            case 4:
                System.out.println("Vous avez choisi de quitter.");
                System.exit(0);
            default:
                System.out.println("Choix non valide. Veuillez choisir une option entre 1 et 4.");
        }

        scanner.close();
    }
}