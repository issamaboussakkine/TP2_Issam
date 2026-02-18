package com.issam.app;

import com.issam.entity.EspaceIssam;
import com.issam.entity.MembreIssam;
import com.issam.service.EspaceServiceIssam;
import com.issam.service.MembreServiceIssam;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ApplicationCampus {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("campus-issam");

        MembreServiceIssam membreService = new MembreServiceIssam(emf);
        EspaceServiceIssam espaceService = new EspaceServiceIssam(emf);

        try {
            System.out.println("\n=== CRUD MEMBRES ===");
            testCrudMembres(membreService);

            System.out.println("\n=== CRUD ESPACES ===");
            testCrudEspaces(espaceService);

        } finally {
            emf.close();
        }
    }

    private static void testCrudMembres(MembreServiceIssam service) {
        System.out.println("Insertion des membres Aboussakkine...");

        MembreIssam m1 = new MembreIssam("Issam", "Aboussakkine", "issam.aboussakkine@campus.ma", "0612345678", true);
        MembreIssam m2 = new MembreIssam("Achraf", "Aboussakkine", "achraf.aboussakkine@campus.ma", "0623456789", true);
        MembreIssam m3 = new MembreIssam("Mehdi", "Aboussakkine", "mehdi.aboussakkine@campus.ma", "0634567890", false);

        service.save(m1);
        service.save(m2);
        service.save(m3);

        System.out.println(" 3 membres insérés !");

        System.out.println("\nListe des membres :");
        List<MembreIssam> membres = service.findAll();
        for (MembreIssam m : membres) {
            System.out.println("   " + m);
        }

        System.out.println("\nRecherche Issam par email :");
        service.rechercherParEmail("issam.aboussakkine@campus.ma").ifPresent(System.out::println);

        System.out.println("\nMembres actifs :");
        List<MembreIssam> actifs = service.rechercherActifs(true);
        for (MembreIssam m : actifs) {
            System.out.println("   " + m);
        }
    }

    private static void testCrudEspaces(EspaceServiceIssam service) {
        System.out.println("Insertion des espaces...");

        EspaceIssam e1 = new EspaceIssam("Bibliothèque", 200, true);
        e1.setLocalisation("Bâtiment A, RDC");

        EspaceIssam e2 = new EspaceIssam("Buvette", 50, true);
        e2.setLocalisation("Bâtiment B, 1er étage");

        EspaceIssam e3 = new EspaceIssam("Amphi Issam", 300, true);
        e3.setLocalisation("Bâtiment C, RDC");

        EspaceIssam e4 = new EspaceIssam("Salle Sport", 100, false);
        e4.setLocalisation("Bâtiment D");

        service.save(e1);
        service.save(e2);
        service.save(e3);
        service.save(e4);

        System.out.println(" 4 espaces insérés !");

        System.out.println("\nListe des espaces :");
        List<EspaceIssam> espaces = service.findAll();
        for (EspaceIssam e : espaces) {
            System.out.println("   " + e);
        }

        System.out.println("\nEspaces disponibles :");
        List<EspaceIssam> disponibles = service.rechercherParDisponibilite(true);
        for (EspaceIssam e : disponibles) {
            System.out.println("   " + e);
        }

        System.out.println("\nEspaces avec capacité >= 100 :");
        List<EspaceIssam> grands = service.rechercherParCapaciteMin(100);
        for (EspaceIssam e : grands) {
            System.out.println("   " + e);
        }
    }
}