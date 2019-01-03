package przychodnia;
// Generated 2019-01-03 14:28:49 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Pracownik generated by hbm2java
 */
public class Pracownik  implements java.io.Serializable {


     private Integer idPracownik;
     private String imie;
     private String nazwisko;
     private String rola;
     private String login;
     private String haslo;
     private Set wizyties = new HashSet(0);

    public Pracownik() {
    }

	
    public Pracownik(String imie, String nazwisko, String rola, String login, String haslo) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.rola = rola;
        this.login = login;
        this.haslo = haslo;
    }
    public Pracownik(String imie, String nazwisko, String rola, String login, String haslo, Set wizyties) {
       this.imie = imie;
       this.nazwisko = nazwisko;
       this.rola = rola;
       this.login = login;
       this.haslo = haslo;
       this.wizyties = wizyties;
    }
   
    public Integer getIdPracownik() {
        return this.idPracownik;
    }
    
    public void setIdPracownik(Integer idPracownik) {
        this.idPracownik = idPracownik;
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
    public String getRola() {
        return this.rola;
    }
    
    public void setRola(String rola) {
        this.rola = rola;
    }
    public String getLogin() {
        return this.login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    public String getHaslo() {
        return this.haslo;
    }
    
    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }
    public Set getWizyties() {
        return this.wizyties;
    }
    
    public void setWizyties(Set wizyties) {
        this.wizyties = wizyties;
    }



    @Override
    public String toString(){
        return imie + " " + nazwisko;
    }

}


