package gestion.bibliotheque;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Bibliotheque {
    private List<Livre> livres;
    private List<Utilisateur> utilisateurs;
    private List<Emprunt> emprunts;

    // Constructeur
    public Bibliotheque() {
        this.livres = new ArrayList<>();
        this.utilisateurs = new ArrayList<>();
        this.emprunts = new ArrayList<>();
    }

    // Ajouter un livre
    public void ajouterLivre(Livre livre) {
        livres.add(livre);
    }

    // Afficher tous les livres
    public void afficherLivres() {
        if (livres.isEmpty()) {
            System.out.println("Aucun livre trouvé.");
        } else {
            for (Livre livre : livres) {
                System.out.println(livre);
            }
        }
    }

    // Rechercher un livre par titre
    public Livre rechercherLivre(String titre) {
        for (Livre livre : livres) {
            if (livre.getTitre().equalsIgnoreCase(titre)) {
                return livre;
            }
        }
        return null;
    }

    // Supprimer un livre par titre
    public boolean supprimerLivre(String titre) {
        Livre livre = rechercherLivre(titre);
        if (livre != null) {
            livres.remove(livre);
            return true;
        }
        return false;
    }

    // Ajouter un utilisateur
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        utilisateurs.add(utilisateur);
    }

    // Afficher tous les utilisateurs
    public void afficherUtilisateurs() {
        if (utilisateurs.isEmpty()) {
            System.out.println("Aucun utilisateur trouvé.");
        } else {
            for (Utilisateur utilisateur : utilisateurs) {
                System.out.println(utilisateur);
            }
        }
    }

    // Rechercher un utilisateur par nom
    public Utilisateur rechercherUtilisateur(String nom) {
        for (Utilisateur utilisateur : utilisateurs) {
            if (utilisateur.getNom().equalsIgnoreCase(nom)) {
                return utilisateur;
            }
        }
        return null;
    }

    // Emprunter un livre
    public boolean emprunterLivre(String nomUtilisateur, String titreLivre) {
        Utilisateur utilisateur = rechercherUtilisateur(nomUtilisateur);
        Livre livre = rechercherLivre(titreLivre);

        if (utilisateur != null && livre != null && !livre.isEmprunte()) {
            Emprunt emprunt = new Emprunt(livre, utilisateur, LocalDate.now());
            emprunts.add(emprunt);
            livre.setEmprunte(true);
            return true;
        }
        return false;
    }

    // Retourner un livre
    public boolean retournerLivre(String titreLivre) {
        Livre livre = rechercherLivre(titreLivre);

        if (livre != null && livre.isEmprunte()) {
            livre.setEmprunte(false);
            emprunts.removeIf(emprunt -> emprunt.getLivre().equals(livre));
            return true;
        }
        return false;
    }


    // Afficher tous les emprunts
    public void afficherEmprunts() {
        if (emprunts.isEmpty()) {
            System.out.println("Aucun emprunt trouvé.");
        } else {
            for (Emprunt emprunt : emprunts) {
                System.out.println(emprunt);
            }
        }
    }


    // Sauvegarder les données dans un fichier JSON
    public void sauvegarderDonnees() {
        try (Writer writer = new FileWriter("bibliotheque.json")) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .setPrettyPrinting()
                    .create();
            gson.toJson(this, writer);
            System.out.println("Données sauvegardées avec succès.");
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des données : " + e.getMessage());
        }
    }

    // Charger les données depuis un fichier JSON
    public static Bibliotheque chargerDonnees() {
        try (Reader reader = new FileReader("bibliotheque.json")) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .create();
            Bibliotheque bibliotheque = gson.fromJson(reader, Bibliotheque.class);
            System.out.println("Données chargées avec succès.");
            return bibliotheque != null ? bibliotheque : new Bibliotheque();
        } catch (FileNotFoundException e) {
            System.out.println("Aucun fichier de données trouvé. Une nouvelle bibliothèque sera créée.");
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement des données : " + e.getMessage());
        }
        return new Bibliotheque();
    }
}
