/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Rsheep
 */
@Entity
public class ConvertData implements convertDTO, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String tp;
    private float rate;
    public ConvertData(){
        
    }
    public ConvertData(float rate,String fromCurrency,String toCurrency){
        this.rate=rate;

    }
    @Override
    public String getType(){
        return tp;
    }
    
    @Override
    public float getRate(){
        return rate;
    }
    
     @Override
    public int hashCode() {
        int hash = 0;
        return new Integer(tp).hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ConvertData)) {
            return false;
        }
        ConvertData other = (ConvertData) object;
        return (this.tp == null ? other.tp == null : this.tp.equals(other.tp));
    }

    @Override
    public String toString() {
        return "bank.model.convert[id=" + tp + "]";
    }
    
}
