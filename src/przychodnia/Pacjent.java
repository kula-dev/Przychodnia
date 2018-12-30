package przychodnia;
// Generated 2018-12-30 17:11:20 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Pacjent generated by hbm2java
 */
public class Pacjent  implements java.io.Serializable {


     private Integer idPacjent;
     private String imie;
     private String nazwisko;
     private Date dataUr;
     private int pesel;
     private Set wizyties = new HashSet(0);
     private Set pacjentkartas = new HashSet(0);

    public Pacjent() {
    }

	
    public Pacjent(String imie, String nazwisko, Date dataUr, int pesel) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUr = dataUr;
        this.pesel = pesel;
    }
    public Pacjent(String imie, String nazwisko, Date dataUr, int pesel, Set wizyties, Set pacjentkartas) {
       this.imie = imie;
       this.nazwisko = nazwisko;
       this.dataUr = dataUr;
       this.pesel = pesel;
       this.wizyties = wizyties;
       this.pacjentkartas = pacjentkartas;
    }
   
    public Integer getIdPacjent() {
        return this.idPacjent;
    }
    
    public void setIdPacjent(Integer idPacjent) {
        this.idPacjent = idPacjent;
    }
    public String getImie() {
        return this.imie;
    }
    
    public void setImie(String imie) {
        this.imie = imie;
    }
    public String getNazwisko() {
        return this.nazwisko;
    }
    
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
    public Date getDataUr() {
        return this.dataUr;
    }
    
    public void setDataUr(Date dataUr) {
        this.dataUr = dataUr;
    }
    public int getPesel() {
        return this.pesel;
    }
    
    public void setPesel(int pesel) {
        this.pesel = pesel;
    }
    public Set getWizyties() {
        return this.wizyties;
    }
    
    public void setWizyties(Set wizyties) {
        this.wizyties = wizyties;
    }
    public Set getPacjentkartas() {
        return this.pacjentkartas;
    }
    
    public void setPacjentkartas(Set pacjentkartas) {
        this.pacjentkartas = pacjentkartas;
    }




}


