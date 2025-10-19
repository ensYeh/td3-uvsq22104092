package fr.uvsq.cprog.collex;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Dns {
    private ArrayList<DnsItem> baseDeDonnees;
    private Path dnsFile;
    
    public Dns(Path path) {
        this.dnsFile = path;
        baseDeDonnees = new ArrayList<>();
        try {
            if (Files.exists(dnsFile)) {
                List<String> lines = Files.readAllLines(dnsFile);
                for (String line : lines) {
                    baseDeDonnees.add(parseLine(line));
                }
            }
        } catch (IOException e) {System.err.println("erreur lecture fichier dns: " + e.getMessage());}
    }

    private DnsItem parseLine(String line) {
        String[] parts = line.split(" ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("ligne mal formatée");
        }
        NomMachine nom = new NomMachine(parts[0]);
        AdresseIP ip = new AdresseIP(parts[1]);
        return new DnsItem(ip, nom);
    }

    public DnsItem getItem(AdresseIP ip) {
        return baseDeDonnees.stream()
            .filter(item -> item.getAdresseIP().equals(ip))
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException("pas d'élément " + ip));
    }

    public DnsItem getItem(NomMachine nom) {
        return baseDeDonnees.stream()
            .filter(item -> item.getNomMachine().equals(nom))
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException("pas d'élément " + nom));
    }

    public List<DnsItem> getItems(String domaine) {
        return baseDeDonnees.stream()
            .filter(item -> {
                String nomMachine = item.getNomMachine().getNom();
                String[] parts = nomMachine.split("\\.");
                return parts[1].equals(domaine);
            })
            .collect(Collectors.toList());
    }

    public void addItem(AdresseIP ip, NomMachine nom) {
        for (DnsItem item : baseDeDonnees) {
            if (item.getAdresseIP().equals(ip))
                throw new IllegalArgumentException("L'adresse IP " + ip.getIP() + " existe déjà");
        }
        try {
            DnsItem newItem = new DnsItem(ip, nom);
            baseDeDonnees.add(newItem);
            try {
                List<String> lines = baseDeDonnees.stream()
                    .map(item -> item.getNomMachine() + " " + item.getAdresseIP())
                    .collect(Collectors.toList());
                Files.write(dnsFile, lines);
            } catch (IOException e) {System.err.println("erreur ecriture dns" + e.getMessage());}
        } catch (Exception e) {
            throw new IllegalArgumentException("Erreur lors de l'ajout de l'élément : " + e.getMessage());
        }
    }
}
