/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/**
 *  Trieda PrikazVezmi implementuje prikaz vezmi.
 *  Trieda je sucastou jednoduchej textovej hry.
 *  
 * @author     Katarina Medovarska
 * @version    1.0
 */

public class PrikazVezmi implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "vezmi";
    private Batoh batoh;
    private HerniPlan herniPlan;

    //== Konstruktory a tovární metody =============================================
    /**
     *  Konstruktor triedy
     *  
     *  @param hPlan po ktorom sa pohybujeme
     *  @param batoh s ktorym budeme pracovat
     */
    public PrikazVezmi(HerniPlan hPlan, Batoh batoh)
    {
        this.herniPlan = hPlan;
        this.batoh = batoh;
    }

    /**
     * Metoda na vykonanie prikazu so zadanymi parametrami
     * 
     * @param parametry volania prikazu
     * @return vracia retazec informujuci o vykonani prikazu
     */
    public String proved(String... parametry) {
        if (parametry.length < 1) {
            return "neviem, čo chceš zobrať";
        }

        String nazevVeci = parametry[0];

        Vec vec = herniPlan.getAktualniProstor().najdiVecVProstoru(nazevVeci);

        if (vec == null) {
            return "vec '" + nazevVeci + "' tu nie je";
        }

        if (vec.isPrenositelna()) {
            if (batoh.vlozVecDoBatohu(vec)) {
                herniPlan.getAktualniProstor().odeberVec(nazevVeci);
                return "vec '" + nazevVeci + "' si úspešne vložil do batohu";
            }
            else {
                return "Mas plny batoh";
            }

            
        }
        else {
            herniPlan.getAktualniProstor().vlozVec(vec);
            return "vec '" + nazevVeci + "' fakt neodnesieš";
        }
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
