package fr.uvsq.cprog.collex;

public class NomMachine {
    private String nom;

    public NomMachine(String nom) {
        if (!estValideNom(nom)) {
            throw new IllegalArgumentException("nom de machine invalide");
        }
        this.nom = nom;
    }    

    public static boolean estValideNom(String nom) {
        String[] parts = nom.split("\\.");
        if (parts.length!=3)
        {
            return false;
        }
        return true;
    }

    public String getNom() {
        return nom;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof NomMachine) {
            return nom.equals(((NomMachine) o).nom);
        }
        return false;
    }

    @Override
    public String toString() {
        return nom;
    }
}
