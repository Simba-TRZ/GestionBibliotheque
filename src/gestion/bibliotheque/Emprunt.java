package gestion.bibliotheque;

import java.time.LocalDate;

public class Emprunt {
    private Livre livre;
    private Utilisateur utilisateur;
    private LocalDate dateEmprunt;

    public Emprunt(Livre livre, Utilisateur utilisateur, LocalDate dateEmprunt) {
        this.livre = livre;
        this.utilisateur = utilisateur;
        this.dateEmprunt = dateEmprunt;
    }

    public Livre getLivre() {
        return livre;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    @Override
    public String toString() {
        return "Emprunt{" +
                "Livre='" + livre.getTitre() + '\'' +
                ", Utilisateur='" + utilisateur.getNom() + '\'' +
                ", Date d'emprunt=" + dateEmprunt +
                '}';
    }
}
