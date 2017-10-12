/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package main;

import logika.*;
import uiText.TextoveRozhrani;

/*******************************************************************************
 * Třída  Start je hlavní třídou projektu,
 * Trieda je sucastou jednoduchej textovej hry.
 *  
 * @author     Katarina Medovarska
 * @version    1.0
 */
public final class Start
{
    /***************************************************************************
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args)
    {
        IHra hra = new Hra(new TextoveDotazy());
        // vytvorenie hry, ktorá by sa poslala do triedy TextoveRozhranie
        TextoveRozhrani textoveRozhrani = new TextoveRozhrani(hra);

        if (args.length < 1) {
            textoveRozhrani.hraj();
        } else {
            textoveRozhrani.hrajZeSouboru(args[0]);
        }
    }

    private Start() {}
}
