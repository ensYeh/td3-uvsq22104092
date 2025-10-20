package fr.uvsq.cprog.collex;

import java.io.IOException;

public class DnsApp {
    private Dns dns;
    private DnsTUI tui;
    
    public DnsApp() {
        this.dns = new Dns();
        this.tui = new DnsTUI(dns);
    }

    public void run() {
        boolean running = true;
        while (running) {
            try {
                Commande cmd = tui.nextCommande();
                cmd.execute();
                if (cmd instanceof CommandeExit) {
                    running = false;
                }
            } catch (IllegalArgumentException e) {
                tui.affiche("Erreur : " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
            DnsApp app = new DnsApp();
            app.run();
    }
}
