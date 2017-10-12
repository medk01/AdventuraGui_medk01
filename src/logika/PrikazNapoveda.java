package logika;

/**
 *  Trieda PrikazNapoveda implementuje prikaz napoveda.
 *  Trieda je sucastou jednoduchej textovej hry.
 *  
 * @author     Katarina Medovarska
 * @version    1.0
 */

class PrikazNapoveda implements IPrikaz {

    private static final String NAZEV = "napoveda";
    private SeznamPrikazu platnePrikazy;

    /**
     *  Konstruktor triedy
     *  
     *  @param platnePrikazy seznam příkazů,
     *                       které je možné ve hře použít,
     *                       aby je nápověda mohla zobrazit uživateli. 
     */    
    public PrikazNapoveda(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }

    /**
     *  Vrati zakladnu napovedu k hraniu hry po zadani prikazu
     *  
     *  @return napoveda ke hre
     */
    @Override
    public String proved(String... parametry) {
        return "Tvojou úlohou je pozbierať všetky veci potrebné na boj\n"
        + "a použiť ich proti nepriateľovi.\n"
        + "\n"
        + "Môžeš zadať tieto príkazy:\n"
        + platnePrikazy.vratNazvyPrikazu();
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
