package fr.uvsq.cprog.collex;

public class CommandeExit implements Commande {
    
    public CommandeExit() {}

    @Override
    public void execute() {
        System.exit(0);
    }
}
