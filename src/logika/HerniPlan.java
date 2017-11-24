package logika;

import java.util.ArrayList;
import java.util.List;
import utils.Subject;
import utils.Observer;

/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 * Trieda je sucastou jednoduchej textovej hry.
 *  
 * @author     Katarina Medovarska
 * @version    1.0
 */
public class HerniPlan  implements Subject {
    private static final String strNeuzitocne = "Neužitočné";
    private static final String strUzitocne = "Užitočné";
    private static final String CILOVY_PROSTOR = "bojisko";
    private Prostor aktualniProstor;
    private Hra hra;
    
    private List<Observer> listObservru = new ArrayList<Observer>();

    /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví luku.
     */
    public HerniPlan(Hra hra) {
        this.hra = hra;
        zalozProstoryHry();
    }

    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví luku.
     */
    private void zalozProstoryHry() {
        // vytváranie jednotlivých priestorov
        Prostor luka = new Prostor("luka","Bezpečná čistinka, začiatok hry", false, 255,200);
        Prostor bojisko = new Prostor(CILOVY_PROSTOR, "Miesto, kde treba prekonať Nepriateľa", false, 470,122);
        Prostor jaskyna = new Prostor("jaskyna","Jaskyňa plná vzácnych kameňov", false, 85,200);
        Prostor les = new Prostor("les","Zakázaný les", false, 255,285);
        Prostor hrad = new Prostor("hrad","Vždy bezpečné miesto", false, 255,122);
        Prostor zahrada = new Prostor("zahrada","Raj oddychu na čerstvom vzduchu", false, 250,40);
        Prostor dom = new Prostor("dom","Nikto nevie, kto alebo čo sa v ňom nachádza", true, 95,285);

        //nastavenie otázky
        dom.nastavOtazku("Aké je hlavné mesto Slovenska?", "bratislava");

        // priradzovanie východov 
        luka.setVychod(les);
        luka.setVychod(jaskyna);
        luka.setVychod(hrad);
        les.setVychod(dom);
        dom.setVychod(les);
        les.setVychod(luka);
        jaskyna.setVychod(luka);
        hrad.setVychod(luka);
        hrad.setVychod(zahrada);
        zahrada.setVychod(hrad);
        hrad.setVychod(bojisko);
        bojisko.setVychod(hrad);

        // vytváranie vecí
        Vec diamant = new Vec("diamant", strUzitocne, true,"diamant.jpg");
        Vec prutik = new Vec("prutik", strUzitocne, true,"prutik.jpg");
        Vec talizman = new Vec("talizman", strUzitocne, true,"talitman.jpg");
        Vec metla = new Vec("metla", strUzitocne, true,"metla.jpg");
        Vec sviecka = new Vec("sviecka", strUzitocne, true,"sviecka.png");
        Vec prsten = new Vec("prsten", strUzitocne, true,"prsten.jpg");
        Vec truhlica = new Vec("truhlica", strUzitocne, false,"truhlica.jpg");
        Vec drak = new Vec("drak", strUzitocne, false,"drak.jpg");
        Vec balvan = new Vec("balvan", strUzitocne, false,"balvan.jpg");
        Vec postel = new Vec("postel", strNeuzitocne, false,"postel.jpg");
        Vec mapa = new Vec("mapa", strNeuzitocne, true,"mapa.jpg");
        Vec ucebnica = new Vec("ucebnica", strNeuzitocne, true,"ucebnica.jpg");
        Vec skala = new Vec("skala", strNeuzitocne, false,"skala.jpg");
        Vec noviny = new Vec("noviny", strNeuzitocne, true,"noviny.jpg");
        /*
        //testovanie zbierania veci
        Vec test1 = new Vec("test", "test", true);
        Vec test2 = new Vec("test1", "test", true);
        Vec test3 = new Vec("test2", "test", true);
        Vec test4 = new Vec("test3", "test", true);
        Vec test5 = new Vec("test4", "test", true);
        luka.vlozVec(test1);
        luka.vlozVec(test2);
        luka.vlozVec(test3);
        luka.vlozVec(test4);
        luka.vlozVec(test5);
         */

        // umiestnenie vecí do priestorov
        luka.vlozVec(metla);
        jaskyna.vlozVec(diamant);
        jaskyna.vlozVec(balvan);
        les.vlozVec(drak);
        dom.vlozVec(truhlica);
        dom.vlozVec(noviny);
        dom.vlozVec(talizman);
        dom.vlozVec(prsten);
        hrad.vlozVec(mapa);
        hrad.vlozVec(ucebnica);
        hrad.vlozVec(postel);
        hrad.vlozVec(prutik);
        hrad.vlozVec(sviecka);
        zahrada.vlozVec(skala);

        //umiestnenie postáv do priestorov
        les.vlozPostavu(new Postava("Cudzinec", "Stráži zamknutý dom. Na jeho odomknutie musíš niečo urobiť."));
        bojisko.vlozPostavu(new Postava("Nepriatel", "Proti tejto osobe bojuješ."));

        aktualniProstor = luka;  // hra začína na lúke
    }

    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */

    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    public Hra getHra() {
        return hra;
    }
    
    

    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
        notifyAllObservers();
    }

    /**
     *  Metoda vrati premennu typu postava, podla zadaneho mena osoby
     *
     *@param  jmeno meno hladanej osoby
     *@return       vracia premennu typu Postava
     */
    public Postava getPostavaByName(String jmeno) {
        if (jmeno.equalsIgnoreCase("cudzinec")) {
            return new Postava("Cudzinec", "Stráži zamknutý dom. Na jeho odomknutie musíš niečo urobiť.");
        }
        if (jmeno.equalsIgnoreCase("nepriatel")) {
            return new Postava("Nepriatel", "Proti tejto osobe bojuješ.");
        }

        return null;
    }

    @Override
    public void registerObserver(Observer observer) {
        listObservru.add(observer);
    }

    @Override
    public void deleteObserver(Observer observer) {
        listObservru.remove(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (Observer listObservruItem : listObservru)
            listObservruItem.update();
    }
   

}
