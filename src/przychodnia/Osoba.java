/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package przychodnia;

/**
 *
 * @author Soprano
 */
public class Osoba {
    String imie;
    String nazwisko;
    int wiek;
    
    Osoba(String imie, String nazwisko, int wiek)
    {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.wiek = wiek;
    }
    
    @Override
    public String toString()
    {
        return "Imię: " + imie + " Nazwisko " + nazwisko + " Wiek: " + wiek;
    }
}
