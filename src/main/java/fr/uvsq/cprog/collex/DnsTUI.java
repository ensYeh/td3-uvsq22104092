package fr.uvsq.cprog.collex;

import java.util.List;
import java.util.Scanner;

public class DnsTUI {
    private Scanner scanner =new Scanner(System.in);
    private Dns dns;

    public DnsTUI(Dns dns) {
        this. dns = dns;
    }

    public Commande nextCommande() {
        System.out.println("commande : ");
        String[] parts = scanner.nextLine().toLowerCase().split(" ");

        switch (parts[0]) {
            case "exit":
            case "quit":
                return new CommandeExit();
            case "ls":
                if (parts.length == 3 && parts[1].equals("-a")) {
                    return new CommandeDomaine(dns, parts[2], true);
                }
                if (parts.length == 2) {
                    return new CommandeDomaine(dns, parts[1], false);
                }
                throw new IllegalArgumentException("forme: ls (-a) domaine");
            case "add":
                if (parts.length == 3) {
                    return new CommandeAdd(dns, parts[1], parts[2]);
                }
                throw new IllegalArgumentException("forme : add ip nom");
            default:
                if (parts.length == 1) {
                    if (AdresseIP.estValideIP(parts[0])) {
                        return new CommandeNom(dns, parts[0]);
                    }
                    if (NomMachine.estValideNom(parts[0])) {
                        return new CommandeIP(dns, parts[0]);
                    }
                }
                throw new IllegalArgumentException("forme : AdresseIP/NomMachine");
        }
    }

    public void affiche(String message) {
        System.out.println(message);
    }

    public void affiche(List<DnsItem> items) {
        for (DnsItem item : items) {
            System.out.println(item);
        }
    }
}