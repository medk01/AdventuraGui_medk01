/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/**
 *  Trieda PrikazProzkoumej implementuje prikaz prozkoumej.
 *  Trieda je sucastou jednoduchej textovej hry.
 *  
 * @author     Katarina Medovarska
 * @version    1.0
 */

public class PrikazProzkoumej implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "prozkoumej";
    private HerniPlan hPlan;

    //== Konstruktory a tovární metody =============================================
    /**
     *  Konstruktor triedy
     *  
     *  @param hPlan po ktorom sa pohybujeme
     */
    public PrikazProzkoumej(HerniPlan hPlan)
    {
        this.hPlan = hPlan;
    }

    /**
     * Metoda na vykonanie prikazu so zadanymi parametrami
     * 
     * @param parametry volania prikazu
     * @return vracia retazec informujuci o vykonani prikazu
     */
    public String proved(String... parametry) {
        if (parametry.length < 1) {
            return "nezadal si, čo chceš preskúmať";
        }

        if (parametry[0].equalsIgnoreCase("cudzinec") || (parametry[0].equalsIgnoreCase("nepriatel"))) {
            String nazevProstoru = hPlan.getAktualniProstor().nazev;
            boolean jeVProstore = hPlan.getAktualniProstor().osobaVProstore(parametry[0], nazevProstoru);
            if (jeVProstore) {
                return hPlan.getPostavaByName(parametry[0]).popis;
            }
        }

        String nazevVeci = parametry[0].toLowerCase();

        Vec vec = hPlan.getAktualniProstor().odeberVec(nazevVeci);

        if (vec == null) {
            return "vec '" + nazevVeci + "' tu nie je";
        }
        hPlan.getAktualniProstor().vlozVec(vec);

        return nazevVeci + ": " +  vec.getPopis();

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
