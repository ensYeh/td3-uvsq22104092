package fr.uvsq.cprog.collex;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdresseIPTest {
    @Test
    public void valideIP() {
        AdresseIP ip = new AdresseIP("192.168.1.1");
        assertNotNull(ip);
        ip = new AdresseIP("127.0.0.1");
        assertNotNull(ip);
    }

    @Test
    public void TropDeParts() {
        assertThrows(IllegalArgumentException.class, () -> {
            new AdresseIP("192.168.1.1.1");
        });
    }

    @Test
    public void PasAssezDeParts() {
        assertThrows(IllegalArgumentException.class, () -> {
            new AdresseIP("192.168.1");
        });
    }

    @Test
    public void IntegerOoutOfRange() {
        assertThrows(IllegalArgumentException.class, () -> {
            new AdresseIP("256.168.1.1");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new AdresseIP("192.168.1.-1");
        });
    }

    @Test
    public void CommenceParZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new AdresseIP("192.168.01.1");
        });
    }

    @Test
    public void PasUnNombre() {
        assertThrows(IllegalArgumentException.class, () -> {
            new AdresseIP("192.168.a.1");
        });
    }
}
