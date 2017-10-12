/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/**
 *  Trieda Postava implementuje Postavu, pouzitu v hre.
 *  Trieda je sucastou jednoduchej textovej hry.
 *  
 * @author     Katarina Medovarska
 * @version    1.0
 */
public class Postava
{
    private String jmeno;
    public String popis;

    //##########################################################################
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================

    /***************************************************************************
     *
     */
    /**
     *  Konstruktor triedy
     *  
     *  @param jmeno meno postavy
     *  @param popis popis postavy
     */
    public Postava(String jmeno, String popis) {
        this.jmeno = jmeno;
        this.popis = popis;
    }

    /**
     * Metoda vracia meno postavy
     * 
     * @return retazec meno postavy
     */
    public String getJmeno() {
        return jmeno; 
    }

    /**
     * Metoda vracia popis postavy
     * 
     * @return popis postavy
     */
    public String getPopis() {
        return popis; 
    }

}
