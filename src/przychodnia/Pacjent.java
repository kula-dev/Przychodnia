/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package przychodnia;

import java.util.ArrayList;

/**
 *
 * @author Soprano
 */
public class Pacjent extends Osoba{
    //ArrayList<String> choroby = new ArrayList<>();
    boolean ubezpieczenie;

    public Pacjent(String imie, String nazwisko, int wiek) {
        super(imie, nazwisko, wiek);
        this.ubezpieczenie = true;
    }
    
    @Override
    public String toString()
    {
        String a = super.toString();
        return a + " Czy ubezpieczony: " + ubezpieczenie + System.lineSeparator();
    }
}
