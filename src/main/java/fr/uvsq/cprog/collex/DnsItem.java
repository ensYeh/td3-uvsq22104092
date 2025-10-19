package fr.uvsq.cprog.collex;

public class DnsItem {
    private AdresseIP ip;
    private NomMachine nom;
    public DnsItem(AdresseIP ip, NomMachine nom) {
        this.ip = ip;
        this.nom = nom;
    }

    public AdresseIP getAdresseIP() {
        return ip;
    }

    public NomMachine getNomMachine() {
        return nom;
    }

    @Override
    public String toString() {
        return "NomMachine: " + nom + " IP: " + ip;
    }
}