/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import java.util.*;

/**
 *  Trieda PrikazVylozBatoh implementuje prikaz vyloz_batoh.
 *  Trieda je sucastou jednoduchej textovej hry.
 *  
 * @author     Katarina Medovarska
 * @version    1.0
 */

public class PrikazVylozBatoh implements IPrikaz
{
    private static final String NAZEV = "vyloz_batoh";
    private HerniPlan plan;
    private Batoh batoh;
    //##########################################################################
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================

    /**
     *  Konstruktor triedy
     *  
     *  @param plan po ktorom sa pohybujeme
     *  @param batoh s ktorym budeme pracovat
     */
    public PrikazVylozBatoh(HerniPlan plan, Batoh batoh) {
        this.plan = plan;
        this.batoh = batoh;
    }

    /**
     * Metoda na vykonanie prikazu so zadanymi parametrami
     * 
     * @param parametry volania prikazu
     * @return vracia retazec informujuci o vykonani prikazu
     */
    @Override
    public String proved(String... parametry) {
        Set keyset = batoh.seznamVeci.keySet();
        Iterator iterator = keyset.iterator();
        // cyklus pre batoh kde neviem kolko a ako ma objekty, jednoduchsie ako if podmienky + for cykly
        while (iterator.hasNext()) {
            String jmenoVeci = iterator.next().toString();
            Vec nalezenaVec = batoh.seznamVeci.get(jmenoVeci);
            //vylozenie veci do priestoru
            plan.getAktualniProstor().vlozVec(nalezenaVec);
        }
        //po vyhodeni veci do prestoru, vymaže prvky v poli
        batoh.seznamVeci.clear();
        //popis veci v priestore
        return plan.getAktualniProstor().popisVeci() ;

    }        

    /**
     * Metoda vracia nazov prikazu ako ho uzivatel moze pouzit
     * 
     * @return retazec nazov prikazu
     */    
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
