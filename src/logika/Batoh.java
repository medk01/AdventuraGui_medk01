/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import java.util.Map;
import java.util.HashMap;

/*******************************************************************************
 * Trieda Batoh
 *
 * Trieda je sucastou jednoduchej textovej hry.
 *  
 * @author     Katarina Medovarska
 * @version    1.0
 */
public class Batoh
{
    private static final int KAPACITA = 4 ;
    public Map<String, Vec> seznamVeci ;

    //##########################################################################
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================

    /**
     *  Konstruktor triedy
     *  
     */
    public Batoh()
    {
        seznamVeci = new HashMap<>();
    }

    /**
     * Metoda vloží vec do batohu, ak sa tam ešte vojde
     * 
     * @param vec ktora sa ma vlozit do batohu
     * @return retazec nazov prikazu
     */
    public boolean vlozVecDoBatohu (Vec vec) {
        if ((seznamVeci.size() < KAPACITA) && (vec.isPrenositelna() )){
            seznamVeci.put(vec.getNazev(), vec);
            return true;
        }
        return false;
    }

    /**
     * Metoda zisti, ci sa nachadza vec v batohu
     * 
     * @param jmenoVeci ktoru hladame v batohu
     * @return ak sa vec v batohu nachadza - true, inak false
     */
    public boolean obsahujeVecVBatohu (String jmenoVeci) {
        return seznamVeci.containsKey(jmenoVeci);
    }

    /**
     * Metoda vyberie vec z batohu
     * 
     * @param jmenoVeci ktoru vyberame z batohu
     * @return vybrana vec
     */
    public Vec vyberVecZBatohu (String jmenoVeci) {
        Vec nalezenaVec;
        if (seznamVeci.containsKey(jmenoVeci)) {
            nalezenaVec = seznamVeci.get(jmenoVeci);
            seznamVeci.remove(jmenoVeci);
            return nalezenaVec;
        }
        return null;
    }

    /**
     * Metoda vypíše veci v batohu
     * 
     * @return zoznam veci v batohu
     */    
    public String nazvyVeciVBatohu() {
        String nazvy = "veci v batohu: ";
        for (String jmenoVeci : seznamVeci.keySet()){
            nazvy += jmenoVeci + " ";
        }
        return nazvy;

    }

    /**
     * Metoda vrati kapacitu batohu
     * 
     * @return kapacita batohu
     */    
    public int getKapacitaBatohu() {
        return KAPACITA;

    }

    /**
     * Metoda vrati pocet volnych miest v batohu
     * 
     * @return volne miesta v batohu
     */  
    public int jeVolneMisto() {
        return KAPACITA - seznamVeci.size();
    }
}
