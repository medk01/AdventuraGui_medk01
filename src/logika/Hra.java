package logika;

import UI.Mapa;

/**
 * Třída Hra - třída představující logiku adventury.
 * 
 * Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan,
 * která inicializuje místnosti hry a vytváří seznam platných příkazů a instance tříd
 * provádějící jednotlivé příkazy. Vypisuje uvítací a ukončovací text hry. Také
 * vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 * @author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha
 * @version    ZS 2016/2017
 */

public class Hra implements IHra {
    public SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    public HerniPlan herniPlan;
    private boolean konecHry = false;
    public IZadavaniDotazu zadDotazu;
    private Batoh batoh;
    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     *  
     *  @param zadDotazu tireda IZadavaniDotazu pre zadanie a odpoved
     */
    public Hra() {
        herniPlan = new HerniPlan(this);
        batoh = new Batoh();
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazVezmi(herniPlan,batoh));
        platnePrikazy.vlozPrikaz(new PrikazUkazBatoh(batoh));
        platnePrikazy.vlozPrikaz(new PrikazPouzi(this));
        platnePrikazy.vlozPrikaz(new PrikazProzkoumej(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazVylozVecZBatohu(herniPlan,batoh));
        platnePrikazy.vlozPrikaz(new PrikazVylozBatoh(herniPlan,batoh));
        platnePrikazy.vlozPrikaz(new PrikazKdeJsem(herniPlan));
        //zadDotazu = new zadDotazu();
        //this.zadDotazu = zadDotazu;
    }

    

    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return "Ahoj!\n" +
        "Práve si vstúpil do čarodejníckeho sveta, ktorý potrebuje tvoju pomoc.\n" +
        "Ak si tu náhodou, napíš 'napoveda' a možno ťa to tu zaujme\n" +
        "\n" +
        herniPlan.getAktualniProstor().dlouhyPopis();
    }

    public Batoh getBatoh() {
        return batoh;
    }

    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        return "Dúfam, že sa ti tu páčilo.  Ahoj.";
    }

    /** 
     * Vrací true, pokud hra skončila.
     */
    public boolean konecHry() {
        return konecHry;
    }

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
    public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
            parametry[i]= slova[i+1];   
        }
        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.proved(parametry);

            /*if (herniPlan.hracVyhral()) {
            konecHry = true;
            }*/
        }
        else {
            textKVypsani="Tento príkaz neexistuje ";
        }
        return textKVypsani;
    }

    /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní IPrikaz.
     *  
     *  @param  konecHry  hodnota false= konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }

    /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
    public HerniPlan getHerniPlan(){
        return herniPlan;
    }


    @Override
    public void registerObserver(Mapa aThis) {
    }

    @Override
    public void registerObserverZmenaPtostoru(Mapa aThis) {
    }
    
    @Override
    public void registerObserver() {
    }

    @Override
    public void zaregistrujPozorovatele(Mapa aThis) {
        
    }

    @Override
    public void registerObserverZmenaProstoru(Mapa aThis) {
        
    }

    @Override
    public Object getAktualniProstor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
