/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author manfr
 */
@Entity
@Table(name = "beverage1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Beverage1.findAll", query = "SELECT b FROM Beverage1 b")
    , @NamedQuery(name = "Beverage1.findByDescription", query = "SELECT b FROM Beverage1 b WHERE b.description = :description")
    , @NamedQuery(name = "Beverage1.findByBeverageCode", query = "SELECT b FROM Beverage1 b WHERE b.beverageCode = :beverageCode")})
public class Beverage1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "description")
    protected String description;
    @Id
    @Basic(optional = false)
    @Column(name = "BeverageCode")
    private Integer beverageCode;
    @JoinColumn(name = "OrderName", referencedColumnName = "name")
    @ManyToOne(optional = false)
    private Orders1 orderName;

    public Beverage1() {
    }

    public Beverage1(Integer beverageCode) {
        this.beverageCode = beverageCode;
    }

    public Beverage1(Integer beverageCode, String description) {
        this.beverageCode = beverageCode;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getBeverageCode() {
        return beverageCode;
    }

    public void setBeverageCode(Integer beverageCode) {
        this.beverageCode = beverageCode;
    }

    public Orders1 getOrderName() {
        return orderName;
    }

    public void setOrderName(Orders1 orderName) {
        this.orderName = orderName;
    } 
    public double cost(){return 0;}
        
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (beverageCode != null ? beverageCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Beverage1)) {
            return false;
        }
        Beverage1 other = (Beverage1) object;
        if ((this.beverageCode == null && other.beverageCode != null) || (this.beverageCode != null && !this.beverageCode.equals(other.beverageCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Beverage1[ beverageCode=" + beverageCode + " ]";
    }
    
}
