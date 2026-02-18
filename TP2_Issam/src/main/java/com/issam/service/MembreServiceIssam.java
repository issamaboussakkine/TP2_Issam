package com.issam.service;

import com.issam.entity.MembreIssam;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class MembreServiceIssam extends OperationCrudIssamAbstrait<MembreIssam, Long> {

    public MembreServiceIssam(EntityManagerFactory emf) {
        super(emf);
    }

    public Optional<MembreIssam> rechercherParEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<MembreIssam> query = em.createQuery(
                    "SELECT m FROM MembreIssam m WHERE m.email = :email", MembreIssam.class);
            query.setParameter("email", email);
            List<MembreIssam> resultats = query.getResultList();
            return resultats.isEmpty() ? Optional.empty() : Optional.of(resultats.get(0));
        } finally {
            em.close();
        }
    }

    public List<MembreIssam> rechercherParNom(String nom) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<MembreIssam> query = em.createQuery(
                    "SELECT m FROM MembreIssam m WHERE m.nom = :nom", MembreIssam.class);
            query.setParameter("nom", nom);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<MembreIssam> rechercherActifs(boolean actif) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<MembreIssam> query = em.createQuery(
                    "SELECT m FROM MembreIssam m WHERE m.actif = :actif", MembreIssam.class);
            query.setParameter("actif", actif);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}