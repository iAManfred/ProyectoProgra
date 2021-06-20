/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;

/**
 *
 * @author manfr
 */
public interface DAObeverage1 {

public void create(Beverage1 beverage1)throws PreexistingEntityException, Exception;
public void edit(Beverage1 beverage1) throws NonexistentEntityException, Exception;


public void destroy(Integer id) throws NonexistentEntityException;
public Beverage1 findBeverage1(Integer id);


}
