/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/**
 *  Trieda PrikazUkazBatoh implementuje prikaz ukaz_batoh.
 *  Trieda je sucastou jednoduchej textovej hry.
 *  
 * @author     Katarina Medovarska
 * @version    1.0
 */

public class PrikazUkazBatoh implements IPrikaz
{
    private static final String NAZEV = "ukaz_batoh";
    public Batoh batoh;
    //##########################################################################
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================

    /**
     *  Konstruktor triedy
     *  
     *  @param batoh s ktorym budeme pracovat
     */
    public PrikazUkazBatoh(Batoh batoh) {
        this.batoh = batoh;
    }

    /**
     *  metoda na vykonanie prikazu
     *  
     *  @param parametry zoznam parametrov prikazu
     *  @return retazec veci v batohu
     */
    @Override
    public String proved(String... parametry) {
        return batoh.nazvyVeciVBatohu();
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
