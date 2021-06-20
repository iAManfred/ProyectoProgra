/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;

/**
 *
 * @author manfr
 */
public interface DAOorders1 {
   
    public void create(Orders1 orders1)throws PreexistingEntityException, Exception;
    
    
    public void edit(Orders1 orders1) throws IllegalOrphanException, NonexistentEntityException, Exception;
    
    
    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException;
    
    
    public Orders1 findOrders1(String id);
    
     
     
     
    
}
