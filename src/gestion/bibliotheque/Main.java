package gestion.bibliotheque;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bibliotheque bibliotheque = Bibliotheque.chargerDonnees();
        System.out.println("Données chargées depuis bibliotheque.json.\n");

        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            System.out.println("\nMenu :");
            System.out.println("1. Ajouter un livre");
            System.out.println("2. Afficher tous les livres");
            System.out.println("3. Rechercher un livre");
            System.out.println("4. Supprimer un livre");
            System.out.println("5. Ajouter un utilisateur");
            System.out.println("6. Afficher tous les utilisateurs");
            System.out.println("7. Emprunter un livre");
            System.out.println("8. Retourner un livre");
            System.out.println("9. Afficher tous les emprunts");
            System.out.println("10. Quitter");

            System.out.print("Entrez votre choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consomme la ligne restante

            switch (choix) {
                case 1 -> {
                    System.out.print("Titre : ");
                    String titre = scanner.nextLine();
                    System.out.print("Auteur : ");
                    String auteur = scanner.nextLine();
                    System.out.print("Année : ");
                    int annee = scanner.nextInt();
                    scanner.nextLine(); // Consomme la ligne restante
                    System.out.print("Catégorie : ");
                    String categorie = scanner.nextLine();
                    bibliotheque.ajouterLivre(new Livre(titre, auteur, annee, categorie));
                    System.out.println("Livre ajouté : " + titre);
                }
                case 2 -> {
                    System.out.println("Liste des livres :");
                    bibliotheque.afficherLivres();
                }
                case 3 -> {
                    System.out.print("Titre du livre à rechercher : ");
                    String titre = scanner.nextLine();
                    Livre livre = bibliotheque.rechercherLivre(titre);
                    if (livre != null) {
                        System.out.println("Livre trouvé : " + livre);
                    } else {
                        System.out.println("Aucun livre trouvé avec ce titre.");
                    }
                }
                case 4 -> {
                    System.out.print("Titre du livre à supprimer : ");
                    String titre = scanner.nextLine();
                    if (bibliotheque.supprimerLivre(titre)) {
                        System.out.println("Livre supprimé : " + titre);
                    } else {
                        System.out.println("Aucun livre trouvé avec ce titre.");
                    }
                }
                case 5 -> {
                    System.out.print("Nom de l'utilisateur : ");
                    String nom = scanner.nextLine();
                    System.out.print("Adresse e-mail : ");
                    String email = scanner.nextLine();
                    bibliotheque.ajouterUtilisateur(new Utilisateur(nom, email));
                    System.out.println("Utilisateur ajouté : " + nom);
                }
                case 6 -> {
                    System.out.println("Liste des utilisateurs :");
                    bibliotheque.afficherUtilisateurs();
                }
                case 7 -> {
                    System.out.print("Nom de l'utilisateur : ");
                    String nomUtilisateur = scanner.nextLine();
                    System.out.print("Titre du livre : ");
                    String titreLivre = scanner.nextLine();
                    if (bibliotheque.emprunterLivre(nomUtilisateur, titreLivre)) {
                        System.out.println("Livre emprunté avec succès.");
                    } else {
                        System.out.println("Échec de l'emprunt. Vérifiez que l'utilisateur et le livre existent et que le livre n'est pas déjà emprunté.");
                    }
                }
                case 8 -> {
                    System.out.print("Titre du livre à retourner : ");
                    String titreLivre = scanner.nextLine();
                    if (bibliotheque.retournerLivre(titreLivre)) {
                        System.out.println("Livre retourné avec succès.");
                    } else {
                        System.out.println("Échec du retour. Vérifiez que le livre est actuellement emprunté.");
                    }
                }
                case 9 -> {
                    System.out.println("Liste des emprunts :");
                    bibliotheque.afficherEmprunts();
                }
                case 10 -> {
                    System.out.println("Au revoir !");
                    bibliotheque.sauvegarderDonnees();
                    continuer = false;
                }
                default -> System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }

        scanner.close();
    }
}
