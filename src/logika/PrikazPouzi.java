/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/**
 *  Trieda PrikazPouzi implementuje prikaz pouzi.
 *  Trieda je sucastou jednoduchej textovej hry.
 *  
 * @author     Katarina Medovarska
 * @version    1.0
 */

public class PrikazPouzi implements IPrikaz
{
    private static final String NAZEV = "pouzi";
    private Hra hra;

    //##########################################################################
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================

    /**
     *  Konstruktor triedy
     *  
     *  @param hra v ktorej ideme pouzit predmety
     */
    public PrikazPouzi(Hra hra) {
        this.hra = hra;
    }

    /**
     * Metoda na vykonanie prikazu so zadanymi parametrami
     * 
     * @param parametry volania prikazu
     * @return vracia retazec informujuci o vykonani prikazu
     */
    public String proved(String... parametry) {
        Prostor aktualniProstor = hra.getHerniPlan().getAktualniProstor();

        if (!aktualniProstor.getNazev().equalsIgnoreCase("bojisko")) {
            return "Nie si v spravnom priestore";
        }
        //sme na bojisku
        //kontrola ci su vsetky veci na pouzitie okolo
        String vracenyText = "";
        //potrebujeme diamant, prútik, talizman, metla, sviečka, prsteň
        if (
        aktualniProstor.veci.containsKey("diamant") &&
        aktualniProstor.veci.containsKey("prutik") &&
        aktualniProstor.veci.containsKey("talizman") &&
        aktualniProstor.veci.containsKey("metla") &&
        aktualniProstor.veci.containsKey("sviecka") &&
        aktualniProstor.veci.containsKey("prsten")
        ) {
            hra.setKonecHry(true);
            return  "Vyhral si...\nGratulujem";
        }

        else {
            vracenyText = "Chybaju ti veci na vitazstvo (nemas ich v batohu?)\nVeci okolo Teba: ";

            for (String nazev : aktualniProstor.veci.keySet()) {
                vracenyText += " " + nazev;
            }
        }
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

