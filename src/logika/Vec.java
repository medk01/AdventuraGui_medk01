/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/**
 *  Trieda Vec implementuje vec pouzivanu v hre.
 *  Trieda je sucastou jednoduchej textovej hry.
 *  
 * @author     Katarina Medovarska
 * @version    1.0
 */

public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private String popis;
    private boolean prenositelna;
    private String obrazek;

    //== Konstruktory a tovární metody =============================================

    /**
     *  Konstruktor triedy
     *  
     *  @param nazev nazov veci
     *  @param popis popis veci
     *  @param prenositelna ci sa vec da preniest
     */
    public Vec(String nazev, String popis, boolean prenositelna,String obrazek)
    {
        this.nazev = nazev;
        this.popis = popis;
        this.prenositelna = prenositelna;
        this.obrazek = obrazek;
    }

    /**
     * Metoda vracia nazov veci
     * 
     * @return nazov veci
     */
    public String getNazev() {
        return nazev;
    }

    public String getObrazek() {
        return obrazek;
    }
    
    

    /**
     * Metoda vracia popis veci
     * 
     * @return popis veci
     */
    public String getPopis() {
        return popis;
    }

    /**
     * Metoda vracia prenositelnost veci
     * 
     * @return true ak je prenositelna, inak false
     */
    public boolean isPrenositelna() {
        return prenositelna;
    }
}
