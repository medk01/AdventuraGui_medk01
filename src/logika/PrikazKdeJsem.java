/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/**
 *  Trieda PrikazKdeJsem implementuje prikaz kde_jsem.
 *  Trieda je sucastou jednoduchej textovej hry.
 *  
 * @author     Katarina Medovarska
 * @version    1.0
 */
public class PrikazKdeJsem implements IPrikaz
{
    private static final String NAZEV = "kde_jsem";
    private HerniPlan plan;
    //##########################################################################
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================

    /**
     *  Konstruktor triedy
     *  
     *  @param plan po ktorom sa pohybujeme
     */
    public PrikazKdeJsem(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Metoda na vykonanie prikazu so zadanymi parametrami
     * 
     * @param parametry volania prikazu
     * @return vracia retazec informujuci o vykonani prikazu
     */
    public String proved(String... parametry) {
        //nepotrebuje parametre, takze nemusime kontrolovat
        Prostor aktualniProstor = plan.getAktualniProstor();
        //zisti nazov aktualneho priestoru
        String vracenyText = "Nachadzas sa v priestore: " + plan.getAktualniProstor().getNazev() + "\n";
        //vrati vsetky vychody
        vracenyText += "východy: ";
        for (Prostor sousedni : aktualniProstor.vychody) {
            vracenyText += " " + sousedni.getNazev();
        }
        vracenyText += "veci: " + plan.getAktualniProstor().popisVeci();
        return vracenyText;
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

