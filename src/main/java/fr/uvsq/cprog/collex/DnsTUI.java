package fr.uvsq.cprog.collex;

import java.util.Scanner;

public class DnsTUI {
    private Scanner scanner;

    public DnsTUI() {
        scanner = new Scanner(System.in);
    }

    public String nextCommande() {
        System.out.println("commande : ");
        return scanner.nextLine();
    }

    public void affiche(String message) {
        System.out.println(message);
    }
}