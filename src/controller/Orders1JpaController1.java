/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Model.Beverage1;
import Model.Orders1;
import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author manfr
 */
public class Orders1JpaController1 implements Serializable {

    public Orders1JpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Orders1 orders1) throws PreexistingEntityException, Exception {
        if (orders1.getBeverage1Collection() == null) {
            orders1.setBeverage1Collection(new ArrayList<Beverage1>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Beverage1> attachedBeverage1Collection = new ArrayList<Beverage1>();
            for (Beverage1 beverage1CollectionBeverage1ToAttach : orders1.getBeverage1Collection()) {
                beverage1CollectionBeverage1ToAttach = em.getReference(beverage1CollectionBeverage1ToAttach.getClass(), beverage1CollectionBeverage1ToAttach.getBeverageCode());
                attachedBeverage1Collection.add(beverage1CollectionBeverage1ToAttach);
            }
            orders1.setBeverage1Collection(attachedBeverage1Collection);
            em.persist(orders1);
            for (Beverage1 beverage1CollectionBeverage1 : orders1.getBeverage1Collection()) {
                Orders1 oldOrderNameOfBeverage1CollectionBeverage1 = beverage1CollectionBeverage1.getOrderName();
                beverage1CollectionBeverage1.setOrderName(orders1);
                beverage1CollectionBeverage1 = em.merge(beverage1CollectionBeverage1);
                if (oldOrderNameOfBeverage1CollectionBeverage1 != null) {
                    oldOrderNameOfBeverage1CollectionBeverage1.getBeverage1Collection().remove(beverage1CollectionBeverage1);
                    oldOrderNameOfBeverage1CollectionBeverage1 = em.merge(oldOrderNameOfBeverage1CollectionBeverage1);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOrders1(orders1.getName()) != null) {
                throw new PreexistingEntityException("Orders1 " + orders1 + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Orders1 orders1) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Orders1 persistentOrders1 = em.find(Orders1.class, orders1.getName());
            Collection<Beverage1> beverage1CollectionOld = persistentOrders1.getBeverage1Collection();
            Collection<Beverage1> beverage1CollectionNew = orders1.getBeverage1Collection();
            List<String> illegalOrphanMessages = null;
            for (Beverage1 beverage1CollectionOldBeverage1 : beverage1CollectionOld) {
                if (!beverage1CollectionNew.contains(beverage1CollectionOldBeverage1)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Beverage1 " + beverage1CollectionOldBeverage1 + " since its orderName field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Beverage1> attachedBeverage1CollectionNew = new ArrayList<Beverage1>();
            for (Beverage1 beverage1CollectionNewBeverage1ToAttach : beverage1CollectionNew) {
                beverage1CollectionNewBeverage1ToAttach = em.getReference(beverage1CollectionNewBeverage1ToAttach.getClass(), beverage1CollectionNewBeverage1ToAttach.getBeverageCode());
                attachedBeverage1CollectionNew.add(beverage1CollectionNewBeverage1ToAttach);
            }
            beverage1CollectionNew = attachedBeverage1CollectionNew;
            orders1.setBeverage1Collection(beverage1CollectionNew);
            orders1 = em.merge(orders1);
            for (Beverage1 beverage1CollectionNewBeverage1 : beverage1CollectionNew) {
                if (!beverage1CollectionOld.contains(beverage1CollectionNewBeverage1)) {
                    Orders1 oldOrderNameOfBeverage1CollectionNewBeverage1 = beverage1CollectionNewBeverage1.getOrderName();
                    beverage1CollectionNewBeverage1.setOrderName(orders1);
                    beverage1CollectionNewBeverage1 = em.merge(beverage1CollectionNewBeverage1);
                    if (oldOrderNameOfBeverage1CollectionNewBeverage1 != null && !oldOrderNameOfBeverage1CollectionNewBeverage1.equals(orders1)) {
                        oldOrderNameOfBeverage1CollectionNewBeverage1.getBeverage1Collection().remove(beverage1CollectionNewBeverage1);
                        oldOrderNameOfBeverage1CollectionNewBeverage1 = em.merge(oldOrderNameOfBeverage1CollectionNewBeverage1);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = orders1.getName();
                if (findOrders1(id) == null) {
                    throw new NonexistentEntityException("The orders1 with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Orders1 orders1;
            try {
                orders1 = em.getReference(Orders1.class, id);
                orders1.getName();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The orders1 with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Beverage1> beverage1CollectionOrphanCheck = orders1.getBeverage1Collection();
            for (Beverage1 beverage1CollectionOrphanCheckBeverage1 : beverage1CollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Orders1 (" + orders1 + ") cannot be destroyed since the Beverage1 " + beverage1CollectionOrphanCheckBeverage1 + " in its beverage1Collection field has a non-nullable orderName field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(orders1);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Orders1> findOrders1Entities() {
        return findOrders1Entities(true, -1, -1);
    }

    public List<Orders1> findOrders1Entities(int maxResults, int firstResult) {
        return findOrders1Entities(false, maxResults, firstResult);
    }

    private List<Orders1> findOrders1Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Orders1.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Orders1 findOrders1(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Orders1.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrders1Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Orders1> rt = cq.from(Orders1.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
