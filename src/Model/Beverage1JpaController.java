/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Beverage1;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Model.Orders1;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author manfr
 */
public class Beverage1JpaController implements Serializable {

    public Beverage1JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

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

    public void edit(Beverage1 beverage1) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Beverage1 persistentBeverage1 = em.find(Beverage1.class, beverage1.getBeverageCode());
            Orders1 orderNameOld = persistentBeverage1.getOrderName();
            Orders1 orderNameNew = beverage1.getOrderName();
            if (orderNameNew != null) {
                orderNameNew = em.getReference(orderNameNew.getClass(), orderNameNew.getName());
                beverage1.setOrderName(orderNameNew);
            }
            beverage1 = em.merge(beverage1);
            if (orderNameOld != null && !orderNameOld.equals(orderNameNew)) {
                orderNameOld.getBeverage1Collection().remove(beverage1);
                orderNameOld = em.merge(orderNameOld);
            }
            if (orderNameNew != null && !orderNameNew.equals(orderNameOld)) {
                orderNameNew.getBeverage1Collection().add(beverage1);
                orderNameNew = em.merge(orderNameNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = beverage1.getBeverageCode();
                if (findBeverage1(id) == null) {
                    throw new NonexistentEntityException("The beverage1 with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

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

    public List<Beverage1> findBeverage1Entities() {
        return findBeverage1Entities(true, -1, -1);
    }

    public List<Beverage1> findBeverage1Entities(int maxResults, int firstResult) {
        return findBeverage1Entities(false, maxResults, firstResult);
    }

    private List<Beverage1> findBeverage1Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Beverage1.class));
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

    public Beverage1 findBeverage1(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Beverage1.class, id);
        } finally {
            em.close();
        }
    }

    public int getBeverage1Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Beverage1> rt = cq.from(Beverage1.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
