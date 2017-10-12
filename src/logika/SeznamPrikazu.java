package logika;

import java.util.HashMap;
import java.util.Map;

/**
 *  Trieda SeznamPrikazu implementuje prikazy pouzite v hre.
 *  Trieda je sucastou jednoduchej textovej hry.
 *  
 * @author     Katarina Medovarska
 * @version    1.0
 */
class SeznamPrikazu {
    // mapa pro uložení přípustných příkazů
    private  Map<String,IPrikaz> mapaSPrikazy;

    /**
     *  Konstruktor triedy
     */
    public SeznamPrikazu() {
        mapaSPrikazy = new HashMap<>();
    }

    /**
     * Vkládá nový příkaz.
     *
     *@param  prikaz  Instance třídy implementující rozhraní IPrikaz
     */
    public void vlozPrikaz(IPrikaz prikaz) {
        mapaSPrikazy.put(prikaz.getNazev(), prikaz);
    }

    /**
     * Vrací odkaz na instanci třídy implementující rozhraní IPrikaz,
     * která provádí příkaz uvedený jako parametr.
     *
     *@param  retezec  klíčové slovo příkazu, který chce hráč zavolat
     *@return          instance třídy, která provede požadovaný příkaz
     */
    public IPrikaz vratPrikaz(String retezec) {
        if (mapaSPrikazy.containsKey(retezec.toLowerCase())) {
            return mapaSPrikazy.get(retezec.toLowerCase());
        }
        else {
            return null;
        }
    }

    /**
     * Kontroluje, zda zadaný řetězec je přípustný příkaz.
     *
     *@param  retezec  Řetězec, který se má otestovat, zda je přípustný příkaz
     *@return          Vrací hodnotu true, pokud je zadaný
     *                     řetězec přípustný příkaz
     */
    public boolean jePlatnyPrikaz(String retezec) {
        return mapaSPrikazy.containsKey(retezec.toLowerCase());
    }

    /**
     *  Vrací seznam přípustných příkazů, jednotlivé příkazy jsou odděleny mezerou.
     *  
     *  @return     Řetězec, který obsahuje seznam přípustných příkazů
     */
    public String vratNazvyPrikazu() {
        String seznam = "";
        for (String slovoPrikazu : mapaSPrikazy.keySet()){
            seznam += slovoPrikazu.toLowerCase() + " ";
        }
        return seznam;
    }

}

