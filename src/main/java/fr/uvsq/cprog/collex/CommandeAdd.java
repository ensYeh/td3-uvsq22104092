package fr.uvsq.cprog.collex;

import java.io.IOException;

public class CommandeAdd implements Commande {
    private final Dns dns;
    private final AdresseIP ip;
    private final NomMachine nom;

    public CommandeAdd(Dns dns, String ip, String nom) {
        this.dns = dns;
        this.ip = new AdresseIP(ip);
        this.nom = new NomMachine(nom);
    }

    @Override
    public void execute() {
        try {
            dns.addItem(ip, nom);
        } catch (Error e) {
            System.err.println(e.getMessage());
        }
    }
}
