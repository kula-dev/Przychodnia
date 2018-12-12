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
public class Pacjenci {
    ArrayList<Pacjent> pacjenci = new ArrayList<>();
    
    Pacjenci(){}
    
    Pacjenci(String a, String b, int c)
    {
        pacjenci.add(new Pacjent(a, b, c));
    }
    
    void addpacjenci(String a, String b, int c)
    {
        pacjenci.add(new Pacjent(a, b, c));
    }
    
    String writepacjenci()
    {
        String w = "";
        for(int i = 0; i < pacjenci.size(); i++)
        {
            w += pacjenci.get(i);
        }
        return w;
        //return pacjenci.toString().replace(",", "").replace("[", "").replace("]", "").trim();
    }
    
    @Override
    public String toString()
    {
        return pacjenci + " ";
    }
}
