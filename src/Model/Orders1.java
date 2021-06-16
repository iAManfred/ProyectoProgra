/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author manfr
 */
@Entity
@Table(name = "orders1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orders1.findAll", query = "SELECT o FROM Orders1 o")
    , @NamedQuery(name = "Orders1.findByName", query = "SELECT o FROM Orders1 o WHERE o.name = :name")
    , @NamedQuery(name = "Orders1.findByConditioon", query = "SELECT o FROM Orders1 o WHERE o.conditioon = :conditioon")})
public class Orders1 implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderName")
    private Collection<Beverage1> beverage1Collection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "conditioon")
    private String conditioon="Pending";
    
    @OneToMany
    private List<Beverage1> listDrinks=new ArrayList<>();

    public List<Beverage1> getListDrinks() {
        return listDrinks;
    }

    public void setListDrinks(List<Beverage1> listDrinks) {
        this.listDrinks = listDrinks;
    }
    
    
   
    
    public Orders1() {
    }

    public Orders1(String name) {
        this.name = name;
    }

    public Orders1(String name, String conditioon) {
        this.name = name;
        this.conditioon = conditioon;
        
      
    
    }
      
   

   public String revisaOrden(){
  String f="";

   for(int i=0;i<listDrinks.size();i++){
  f=f+listDrinks.get(i).getDescription()+" "+",Order detail: "+i+"\n";
 
    }
  return f;}

    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConditioon() {
        return conditioon;
    }
     
    

 
   
    public void setConditioon(String conditioon) {
        this.conditioon = conditioon;
    }

    public String orders(){

      
      return " Condition: "+ getConditioon()+", ";}

   
    
    

 
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders1)) {
            return false;
        }
        Orders1 other = (Orders1) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Orders1[ name=" + name + " ]";
    }

    @XmlTransient
    public Collection<Beverage1> getBeverage1Collection() {
        return beverage1Collection;
    }

    public void setBeverage1Collection(Collection<Beverage1> beverage1Collection) {
        this.beverage1Collection = beverage1Collection;
    }
    
}
