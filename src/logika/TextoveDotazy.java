package logika;

import java.io.*;

/*******************************************************************************
 * Třída TextovyDotaz ...
 * Trieda je sucastou jednoduchej textovej hry.
 *  
 * @author     Katarina Medovarska
 * @version    1.0
 */
public class TextoveDotazy implements IZadavaniDotazu
{
    //== Datové atributy (statické i instancí)======================================

    //##############################################################################
    //== Konstruktory a tovární metody =============================================

    /**
     *konstruktor vymazaný, bol nepotrebný
     */

    /**
     * Metoda zobrazi otazku a precita odpoved a vrati ju ako retazec
     * 
     * @param otazka zobrazena otazka
     * @return retazec odpoved uzivatela
     */
    public String odpovez(String otazka) {
        return prectiString(otazka);
    }

    /**
     * Metoda přečte řetězec z konzole. Parametrem je prompt, který se nejdříve vypíše
     * na konzoli.
     *
     *@return    Vrací přečtený řetězec
     */
    private String prectiString(String prompt) {
        String vstupniRadek="";
        System.out.print("Na odomknutie tohto priestoru musíš ukázať svoje znalosti geografie.\nOdpovedz prosím:\n"+prompt+"\n");        // vypíše se prompt

        BufferedReader vstup =
            new BufferedReader(new InputStreamReader(System.in));
        try {
            vstupniRadek = vstup.readLine();
        }
        catch (java.io.IOException exc) {
            System.out.println("Vyskytla se chyba během čtení z konzole "
                + exc.getMessage());
        }
        return vstupniRadek;
    }
}