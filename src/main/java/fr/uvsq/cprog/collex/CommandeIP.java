package fr.uvsq.cprog.collex;

public class CommandeIP implements Commande {
    private final Dns dns;
    private final NomMachine nom;

    public CommandeIP(Dns dns, String nom) {
        this.dns = dns;
        this.nom = new NomMachine(nom);
    }

    @Override
    public void execute() {
        DnsItem item = dns.getItem(nom);
        if (dns != null) {
            System.out.println(item.getAdresseIP());
        }
        else {
            System.out.println("La machine n'est pas dans le dns");
        }
    }
}
