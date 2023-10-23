package Model;

import Model.Role;

public class Utilisateur {
    protected Long id;
    protected String login,mdp,nom,prenom;
    protected Role role;


    public String nomComplet()
    {
        return nom + " " + prenom;
    }

    public Long getId() {
        return id;
    }

    public void setNomcomplet(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
}
