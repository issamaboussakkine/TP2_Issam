package com.issam.service;

import com.issam.entity.EspaceIssam;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class EspaceServiceIssam extends OperationCrudIssamAbstrait<EspaceIssam, Long> {

    public EspaceServiceIssam(EntityManagerFactory emf) {
        super(emf);
    }

    public List<EspaceIssam> rechercherParDisponibilite(boolean disponible) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<EspaceIssam> query = em.createQuery(
                    "SELECT e FROM EspaceIssam e WHERE e.disponible = :dispo", EspaceIssam.class);
            query.setParameter("dispo", disponible);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<EspaceIssam> rechercherParCapaciteMin(int capacite) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<EspaceIssam> query = em.createQuery(
                    "SELECT e FROM EspaceIssam e WHERE e.capacite >= :cap", EspaceIssam.class);
            query.setParameter("cap", capacite);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<EspaceIssam> rechercherParNom(String nom) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<EspaceIssam> query = em.createQuery(
                    "SELECT e FROM EspaceIssam e WHERE e.nom LIKE :nom", EspaceIssam.class);
            query.setParameter("nom", "%" + nom + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}