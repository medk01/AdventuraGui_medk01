package logika;

/**
 *  Trieda PrikazKonec implementuje prikaz konec.
 *  Trieda je sucastou jednoduchej textovej hry.
 *  
 * @author     Katarina Medovarska
 * @version    1.0
 */

class PrikazKonec implements IPrikaz {

    private static final String NAZEV = "konec";
    private Hra hra;

    /**
     *  Konstruktor triedy
     *  
     *  @param hra ktora sa prikazom ukonci
     */
    public PrikazKonec(Hra hra) {
        this.hra = hra;
    }

    /**
     * V případě, že příkaz má jen jedno slovo "konec" hra končí(volá se metoda setKonecHry(true))
     * jinak pokračuje např. při zadání "konec a".
     * 
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String proved(String... parametry) {
        if (parametry.length > 0) {
            return "Nezadávaj druhé slovo";
        }
        else {
            hra.setKonecHry(true);
            return "Hra ukončená";
        }
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
