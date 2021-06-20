/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author manfr
 */
public class DAOforBeverage1 implements Serializable, DAObeverage1 {
    
     public DAOforBeverage1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    
    @Override
    public void create(Beverage1 beverage1) throws PreexistingEntityException, Exception {
     EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Orders1 orderName = beverage1.getOrderName();
            if (orderName != null) {
                orderName = em.getReference(orderName.getClass(), orderName.getName());
                beverage1.setOrderName(orderName);
            }
            em.persist(beverage1);
            if (orderName != null) {
                orderName.getBeverage1Collection().add(beverage1);
                orderName = em.merge(orderName);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBeverage1(beverage1.getBeverageCode()) != null) {
                throw new PreexistingEntityException("Beverage1 " + beverage1 + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }



@Override
    public void edit(Beverage1 beverage1) throws NonexistentEntityException, Exception {
       
         EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Orders1 orderName = beverage1.getOrderName();
            if (orderName != null) {
                orderName = em.getReference(orderName.getClass(), orderName.getName());
                beverage1.setOrderName(orderName);
            }
            em.persist(beverage1);
            if (orderName != null) {
                orderName.getBeverage1Collection().add(beverage1);
                orderName = em.merge(orderName);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBeverage1(beverage1.getBeverageCode()) != null) {
                throw new PreexistingEntityException("Beverage1 " + beverage1 + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


@Override
    public void destroy(Integer id) throws NonexistentEntityException {
         EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Beverage1 beverage1;
            try {
                beverage1 = em.getReference(Beverage1.class, id);
                beverage1.getBeverageCode();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The beverage1 with id " + id + " no longer exists.", enfe);
            }
            Orders1 orderName = beverage1.getOrderName();
            if (orderName != null) {
                orderName.getBeverage1Collection().remove(beverage1);
                orderName = em.merge(orderName);
            }
            em.remove(beverage1);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


 @Override
    public Beverage1 findBeverage1(Integer id) {
      EntityManager em = getEntityManager();
        try {
            return em.find(Beverage1.class, id);
        } finally {
            em.close();
        }
    }









}
