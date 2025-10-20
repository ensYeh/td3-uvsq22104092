package fr.uvsq.cprog.collex;

public class CommandeNom implements Commande {
    private final Dns dns;
    private final AdresseIP ip;

    public CommandeNom(Dns dns, String ip) {
        this.dns = dns;
        this.ip = new AdresseIP(ip);
    }

    @Override
    public void execute() {
        DnsItem item = dns.getItem(ip);
        if (item != null) {
            System.out.println(item.getNomMachine());
        }
        else {
            System.out.println("l'IP n'est pas dans le dns");
        }
    }
}