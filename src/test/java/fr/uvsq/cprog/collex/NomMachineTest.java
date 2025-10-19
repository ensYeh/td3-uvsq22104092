package fr.uvsq.cprog.collex;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NomMachineTest {
    @Test
    public void NomValide() {
        NomMachine nom = new NomMachine("www.uvsq.fr");
    }

    @Test
    public void TropDeParts() {
        assertThrows(IllegalArgumentException.class, () -> {
            new NomMachine("www.ecampus.uvsq.fr");
        });
    }

    @Test
    public void PasAssezDeParts() {
        assertThrows(IllegalArgumentException.class, () -> {
            new NomMachine("uvsq.fr");
        });
    }
}
