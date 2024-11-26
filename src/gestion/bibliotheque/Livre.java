package gestion.bibliotheque;

public class Livre {
    private String titre;
    private String auteur;
    private int annee;
    private String categorie;
    private boolean emprunte;

    public Livre(String titre, String auteur, int annee, String categorie) {
        this.titre = titre;
        this.auteur = auteur;
        this.annee = annee;
        this.categorie = categorie;
        this.emprunte = false;
    }

    public String getTitre() {
        return titre;
    }

    public boolean isEmprunte() {
        return emprunte;
    }

    public void setEmprunte(boolean emprunte) {
        this.emprunte = emprunte;
    }

    @Override
    public String toString() {
        return "Titre: " + titre + ", Auteur: " + auteur + ", Année: " + annee + ", Catégorie: " + categorie + ", Emprunté: " + (emprunte ? "Oui" : "Non");
    }
}

