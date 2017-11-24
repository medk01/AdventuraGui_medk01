package logika;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 * Trieda je sucastou jednoduchej textovej hry.
 *  
 * @author     Katarina Medovarska
 * @version    1.0
 */
public class Prostor {

    public String nazev;
    private String popis;
    public Set<Prostor> vychody;   // obsahuje sousední místnosti
    public Map<String, Vec> veci;
    private Map<String, Postava> postavy;
    private boolean zamceno;
    // premenné pre hádanku
    private String otazka;
    private String odpoved;
    
    private double posX;
    private double posY;

    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     *                              víceslovný název bez mezer.
     * @param popis Popis prostoru.
     * @param zamceno urcuje, ci je priestor uzamknuty.
     */
    public Prostor(String nazev, String popis, boolean zamceno, double posX, double posY) {
        this.nazev = nazev;
        this.popis = popis;
        vychody = new HashSet<>();
        veci = new HashMap<>();
        postavy = new HashMap<>();
        this.zamceno = zamceno;
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
    @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

        return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Práve 
     * sa nachádzaš v priestore luka vychody: hrad les
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return "Práve sa nachádzaš v priestore " + nazev + " - " + popis + ".\n"
        + seznamVychodu() + "\n"
        + popisVeci() + "\n"
        + zoznamPostavy();
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor>hledaneProstory = 
            vychody.stream()
            .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
            .collect(Collectors.toList());
        if(hledaneProstory.isEmpty()){
            return null;
        }
        else {
            return hledaneProstory.get(0);
        }
    }
    
    public String seznamVychoduProPanely() 
    {
        String vracenyText = "vychody:";
        for (Prostor sousedni : vychody) {
             vracenyText += " " + sousedni.getNazev();
        }
        return vracenyText;
    }
    

    /**
     * Vrací zoznam vychodov z priestoru
     *
     * @return zoznam vychodov z priestoru
     */
    public String seznamVychodu() {
        String vracenyText = "východy: ";
        for (Prostor sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev();
            if (sousedni.jeZamceno()) {
                vracenyText += " - zamknuté";
            }
        }
        return vracenyText;
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }

    /**
     * Vrací popis veci v priestore
     *
     * @return popis veci v priestore
     */
    public String popisVeci() {
        String vracenyText = "veci: ";
        for (String nazev : veci.keySet()) {
            vracenyText += " " + nazev;
        }

        return vracenyText;
    }

    /**
     *  vloží vec do prostrou
     *  @param vec ktoru vlozi do priestoru
     */
    public void vlozVec(Vec vec) {
        veci.put(vec.getNazev(), vec);
    }

    /**
     *  najde vec v priestore
     *  @param nazev veci hladanej v priestore
     *  @return     najdena vec
     */
    public Vec najdiVecVProstoru(String nazev) {
        return veci.get(nazev);
    } 
     
     public Map<String,Vec> getVeci()
     {
         return this.veci;
     }
    

    /**
     *  odoberie vec z priestoru
     *  @param nazev odoberanej veci
     *  @return     odobrata vec
     */
    public Vec odeberVec(String nazev) {
        return veci.remove(nazev);
    }

    /**
     *  vloží postavu do prostrou
     *  @param postava vkladana do priestoru
     */
    public void vlozPostavu(Postava postava) {
        postavy.put(postava.getJmeno(), postava);
    }

    /**
     *  vrati zoznam postav v priestore
     *  @return zoznam postav
     */
    public String zoznamPostavy () {
        String nazov = "postavy: ";
        for (String jmenoPostavy : postavy.keySet()){
            nazov += jmenoPostavy + " ";
        }
        return nazov;
    }

    /**
     *  zisti, ci sa nachadza osoba v priestore
     *
     *  @param jmeno osoby, ktoru ideme kontrolovat
     *  @param miestnost v ktorej ideme hladat osobu
     *  @return true - ak sa nachadza, inak false
     */
    public boolean osobaVProstore (String jmeno, String miestnost) {
        if (jmeno.toLowerCase().equals("cudzinec") && (miestnost.toLowerCase().equals("les"))) {
            return true;
        }
        if (jmeno.toLowerCase().equals("nepriatel") && (miestnost.toLowerCase().equals("bojisko"))) {
            return true;
        }

        return false;
    }

    /**
     *  nastavi zamknutie priestoru
     *
     *  @param zamceno - zamknuty - true, inak false
     */
    public void nastavZamknutie(boolean zamceno) {
        this.zamceno = zamceno;
    }

    /**
     *  zisti, ci je priestor zakmnuty
     *
     *  @return  ak je zamknuty - true, inak false
     */
    public boolean jeZamceno() {
        return zamceno;
    }

    /**
     *  nastavi otazku priestoru
     *
     *  @param otazka zobrazena otazka
     *  @param odpoved ocakavana odpoved
     */
    public void nastavOtazku(String otazka, String odpoved) {
        this.otazka = otazka;
        this.odpoved = odpoved;
    }

    /**
     *  vracia otazku priestoru
     *
     *  @return otazka priestoru
     */
    public String getOtazka() {
        return otazka;
    }

    /**
     *  vracia odpoved priestoru
     *
     *  @return odpoved priestoru
     */
    public String getOdpoved() {
        return odpoved;
    }

    /**
     *  zisti ci je priestor s otazkou
     *
     *  @return ak je s otazkou - true, inak false
     */
    public boolean sOtazkou() {
        return otazka != null;
    }

    /**
     * @return the posX
     */
    public double getPosX() {
        return posX;
    }

    /**
     * @return the posY
     */
    public double getPosY() {
        return posY;
    }

    public Object getAktualniProstor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void registerObserver() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
