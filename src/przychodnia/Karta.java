package przychodnia;
// Generated 2019-01-04 14:24:14 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Karta generated by hbm2java
 */
public class Karta  implements java.io.Serializable {


     private Integer idKarta;
     private String choroba;
     private String opis;
     private Set pacjentkartas = new HashSet(0);

    public Karta() {
    }

	
    public Karta(String choroba, String opis) {
        this.choroba = choroba;
        this.opis = opis;
    }
    public Karta(String choroba, String opis, Set pacjentkartas) {
       this.choroba = choroba;
       this.opis = opis;
       this.pacjentkartas = pacjentkartas;
    }
   
    public Integer getIdKarta() {
        return this.idKarta;
    }
    
    public void setIdKarta(Integer idKarta) {
        this.idKarta = idKarta;
    }
    public String getChoroba() {
        return this.choroba;
    }
    
    public void setChoroba(String choroba) {
        this.choroba = choroba;
    }
    public String getOpis() {
        return this.opis;
    }
    
    public void setOpis(String opis) {
        this.opis = opis;
    }
    public Set getPacjentkartas() {
        return this.pacjentkartas;
    }
    
    public void setPacjentkartas(Set pacjentkartas) {
        this.pacjentkartas = pacjentkartas;
    }




}


