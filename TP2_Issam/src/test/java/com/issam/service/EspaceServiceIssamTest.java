package com.issam.service;

import com.issam.entity.EspaceIssam;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class EspaceServiceIssamTest {

    private EntityManagerFactory emf;
    private EspaceServiceIssam service;

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("campus-issam");
        service = new EspaceServiceIssam(emf);
    }

    @After
    public void tearDown() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    @Test
    public void testCrudOperations() {
        EspaceIssam espace = new EspaceIssam("Salle Test", 25, true);
        espace.setLocalisation("Bâtiment Test");

        EspaceIssam savedEspace = service.save(espace);
        assertNotNull(savedEspace.getId());

        Optional<EspaceIssam> foundEspace = service.findById(savedEspace.getId());
        assertTrue(foundEspace.isPresent());
        assertEquals("Salle Test", foundEspace.get().getNom());

        EspaceIssam toUpdate = foundEspace.get();
        toUpdate.setCapacite(30);
        service.update(toUpdate);

        Optional<EspaceIssam> updatedEspace = service.findById(savedEspace.getId());
        assertTrue(updatedEspace.isPresent());
        assertEquals(30, updatedEspace.get().getCapacite());

        service.delete(updatedEspace.get());
        Optional<EspaceIssam> deletedEspace = service.findById(savedEspace.getId());
        assertFalse(deletedEspace.isPresent());
    }

    @Test
    public void testFindByDisponibilite() {
        EspaceIssam e1 = new EspaceIssam("Espace Dispo", 20, true);
        EspaceIssam e2 = new EspaceIssam("Espace Indispo", 30, false);

        service.save(e1);
        service.save(e2);

        List<EspaceIssam> disponibles = service.rechercherParDisponibilite(true);
        assertTrue(disponibles.stream().anyMatch(e -> e.getNom().equals("Espace Dispo")));
        assertFalse(disponibles.stream().anyMatch(e -> e.getNom().equals("Espace Indispo")));

        List<EspaceIssam> indisponibles = service.rechercherParDisponibilite(false);
        assertTrue(indisponibles.stream().anyMatch(e -> e.getNom().equals("Espace Indispo")));

        service.delete(e1);
        service.delete(e2);
    }

    @Test
    public void testFindByCapaciteMin() {
        EspaceIssam e1 = new EspaceIssam("Petit", 10, true);
        EspaceIssam e2 = new EspaceIssam("Moyen", 50, true);
        EspaceIssam e3 = new EspaceIssam("Grand", 100, true);

        service.save(e1);
        service.save(e2);
        service.save(e3);

        List<EspaceIssam> min50 = service.rechercherParCapaciteMin(50);
        assertEquals(2, min50.size());

        List<EspaceIssam> min80 = service.rechercherParCapaciteMin(80);
        assertEquals(1, min80.size());
        assertEquals("Grand", min80.get(0).getNom());

        service.delete(e1);
        service.delete(e2);
        service.delete(e3);
    }
}