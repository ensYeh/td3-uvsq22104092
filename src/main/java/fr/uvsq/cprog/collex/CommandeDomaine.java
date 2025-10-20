package fr.uvsq.cprog.collex;

import java.util.Comparator;
import java.util.List;

public class CommandeDomaine implements Commande {
    private final Dns dns;
    private final String domaine;
    private final boolean trie;

    public CommandeDomaine(Dns dns, String domaine, boolean trie) {
        this.dns = dns;
        this.domaine = domaine;
        this.trie = trie;
    }

    @Override
    public void execute() {
        List<DnsItem> items = dns.getItems(domaine);
        if (trie) {
            //System.out.println("à faire : trier");
            items.sort(Comparator.comparing(item -> item.getAdresseIP().toString()));
        }
        for (DnsItem item : items) {
            System.out.println(item);
        }
    }
}
