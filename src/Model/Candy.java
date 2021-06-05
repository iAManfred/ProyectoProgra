/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;



/**
 *
 * @author manfr
 */
public class Candy extends CondimentDecorator {
	Beverage beverage;
      
      
	public Candy(Beverage beverage) {
		this.beverage = beverage;
	}
        
       
	public String getDescription() {
		return beverage.getDescription() + ", Candy";
	}

	public double cost() {
		return .35 + beverage.cost();
	}

   

  
}
