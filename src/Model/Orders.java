/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author manfr
 */
public class Orders {
    
private List<Beverage> listDrinks=new ArrayList<Beverage>(); 


private String condition= "Pending";

   
public void setListDrinks(List<Beverage> listDrinks) {
        this.listDrinks = listDrinks;
    }

   

    
    public List<Beverage>getListDrinks() {
        return listDrinks;
    }

   

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
     


  public String orders(){

      
      return " Condition: "+ getCondition()+", ";}

   public String revisaOrden(){
  String f="";

   for(int i=0;i<listDrinks.size();i++){
  f=f+listDrinks.get(i).getDescription()+" "+",Order detail: "+i+"\n";
 
    }
  return f;}
   

}
