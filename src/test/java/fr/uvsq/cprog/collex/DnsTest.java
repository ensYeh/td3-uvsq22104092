package fr.uvsq.cprog.collex;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class DnsTest {
    private static Path TEMP_FILE = Paths.get("dnsFileTest.txt");
    private Dns dns;

    @BeforeEach
    public void setup() throws IOException {
        if (!Files.exists(TEMP_FILE)) {
        Files.createFile(TEMP_FILE);
        }
    }

    @AfterEach
    public void clean() throws IOException {
        Files.delete(TEMP_FILE);
    }

    @Test
    public void getItemByIpValid() {
        try {
            Files.write(TEMP_FILE, List.of("www.test.fr 192.168.1.1"));
            dns = new Dns(TEMP_FILE);
            AdresseIP ip = new AdresseIP("192.168.1.1");
            DnsItem item = dns.getItem(ip);
            assertEquals("www.test.fr", item.getNomMachine().getNom());
            assertEquals("192.168.1.1", item.getAdresseIP().getIP());
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void getItemByIpInvalid() {
        try {
            Files.write(TEMP_FILE, List.of("www.test.fr 192.168.1.1"));
            dns = new Dns(TEMP_FILE);
            AdresseIP ip = new AdresseIP("192.168.2.1");
            NoSuchElementException e = assertThrows(NoSuchElementException.class, () -> {
                dns.getItem(ip);
            });
            assertEquals("pas d'élément 192.168.2.1", e.getMessage());
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void getItemByMachineValid() {
        try {
            Files.write(TEMP_FILE, List.of("www.test.fr 192.168.1.1"));
            dns = new Dns(TEMP_FILE);
            NomMachine nom = new NomMachine("www.test.fr");
            DnsItem item = dns.getItem(nom);
            assertEquals("www.test.fr", item.getNomMachine().getNom());
            assertEquals("192.168.1.1", item.getAdresseIP().getIP());
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void getItemByMachineInvalid() {
        try {
            Files.write(TEMP_FILE, List.of("www.uvsq.fr 192.168.1.1"));
            dns = new Dns(TEMP_FILE);
            NomMachine nom = new NomMachine("www.test.fr");
            NoSuchElementException e = assertThrows(NoSuchElementException.class, () -> {
                dns.getItem(nom);
            });
            assertEquals("pas d'élément www.test.fr", e.getMessage());
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void getItemsValid() {
        try {
            Files.write(TEMP_FILE, List.of("www.test.fr 192.168.1.1", "www.test.com 192.168.1.2"));
            dns = new Dns(TEMP_FILE);
            List<DnsItem> items = dns.getItems("test");
            assertEquals(2, items.size());
            assertTrue(items.stream().allMatch(item -> item.getNomMachine().getNom().contains("test")));
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test 
    public void getItemsNoMatch() {
        try {
            Files.write(TEMP_FILE, List.of("www.test.fr 192.168.1.1", "www.test.com 192.168.1.2"));
            dns = new Dns(TEMP_FILE);
            List<DnsItem> items = dns.getItems("google");
            assertEquals(0, items.size());
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test 
    public void getItemsEmptyFile() {
        dns = new Dns(TEMP_FILE);
        List<DnsItem> items = dns.getItems("test");
        assertEquals(0, items.size());
    }

    @Test
    public void addItemValid() {
        dns = new Dns(TEMP_FILE);
        AdresseIP ip = new AdresseIP("192.168.1.1");
        NomMachine nom = new NomMachine("www.test.fr");
        dns.addItem(ip, nom);
        DnsItem item = dns.getItem(ip);
        assertEquals(ip, item.getAdresseIP());
        assertEquals(nom, item.getNomMachine());
        try {
            List<String> lines = Files.readAllLines(TEMP_FILE);
            assertTrue(lines.contains("www.test.fr 192.168.1.1"));
        } catch (IOException e) {
            fail("Erreur lors de la lecture du fichier");
        }
    }

    @Test void addItemDuplicate() {
        dns = new Dns(TEMP_FILE);
        AdresseIP ip1 = new AdresseIP("192.168.1.1");
        NomMachine nom1 = new NomMachine("www.test.fr");
        dns.addItem(ip1, nom1);

        AdresseIP ip2 = new AdresseIP("192.168.1.1");
        NomMachine nom2 = new NomMachine("www.test.com");
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {dns.addItem(ip2, nom2);});
        assertEquals("L'adresse IP 192.168.1.1 existe déjà", e.getMessage());
    }

}
