/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/**
 *  Trieda PrikazVylozVecZBatohu implementuje prikaz vyloz_vec_z_batohu.
 *  Trieda je sucastou jednoduchej textovej hry.
 *  
 * @author     Katarina Medovarska
 * @version    1.0
 */

public class PrikazVylozVecZBatohu implements IPrikaz
{
    private static final String NAZEV = "vyloz_vec_z_batohu";
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
    public PrikazVylozVecZBatohu(HerniPlan plan, Batoh batoh) {
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
        if (parametry.length == 0) {
            return "Zadaj vec, ktorú chceš z batoha vyložiť.";
        }

        if (parametry.length > 1) {
            return "Zadaj len 1 vec";
        }

        String jmenoVeci = parametry[0];

        Vec vec = batoh.vyberVecZBatohu(jmenoVeci);

        if(vec == null) {
            return "Túto vec v batohu nemáš.";
        }
        plan.getAktualniProstor().vlozVec(vec);

        return "Vec " +jmenoVeci+ " si vyložil z batoha.\n" + plan.getAktualniProstor().popisVeci() ;
    }

    /**
     * Metoda vracia nazov prikazu ako ho uzivatel moze pouzit
     * 
     * @return retazec nazov prikazu
     */
    public String getNazev() {
        return NAZEV;
    }
}
