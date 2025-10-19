package fr.uvsq.cprog.collex;

public class AdresseIP {
    private String ip;

    public AdresseIP(String ip) {
        if (!estValideIP(ip)) {
            throw new IllegalArgumentException("Adresse IP invalide");
        }
        this.ip = ip;
    }

    public boolean estValideIP(String ip) {
        String[] parts = ip.split("\\.");
        if (parts.length != 4) {
            return false;
        }
        for (String part : parts){
            int n;
            try {
                n = Integer.parseInt(part);
            } catch(NumberFormatException e) {return false;}
            if (n < 0 || n >255) {
                return false;
            }
            if (part.length() > 1 && part.startsWith("0")) {
                return false;
            }
        }
        return true;
    }

    public String getIP() {
        return ip;
    }

    @Override
    public String toString() {
        return ip;
    }
}
