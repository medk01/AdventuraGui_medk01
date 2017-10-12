package logika;

/**
 * Trieda PrikazNapoveda implementuje prikaz jdi.
 * Trieda je sucastou jednoduchej textovej hry.
 *  
 * @author     Katarina Medovarska
 * @version    1.0
 */

class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;

    /**
     *  Konstruktor třídy
     *  
     *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
     */    
    public PrikazJdi(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Ak chceš niekam ísť, musíš zadať meno východu.";
        }

        String smer = parametry[0];

        // zkoušíme přejít do sousedního prostoru
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);
        String vypis = ""; // ak neprejde ani jedna podmienka tak vypíše prázdny retazec

        if (sousedniProstor == null) {
            vypis = "Tam sa odtiaľto nedá ísť";
        }
        else {
            if (sousedniProstor.jeZamceno()) {

                // vytvorenie triedy textoveDotazy (nedostupná iná inštancia), rýchlejšie
                TextoveDotazy textDotazy = new TextoveDotazy();
                //String tmp= sousedniProstor.getOtazka();
                String odpoved = textDotazy.odpovez(sousedniProstor.getOtazka()); //otázka, napíšem odpoved, enter = koniec príkazu

                if (odpoved.trim().equalsIgnoreCase(sousedniProstor.getOdpoved().toLowerCase())) {
                    vypis="\nUhádol si! \n";
                    sousedniProstor.nastavZamknutie(false); //odomknutie
                    plan.setAktualniProstor(sousedniProstor); //vojde do predtým zamknutej miestnosti
                    vypis += sousedniProstor.dlouhyPopis(); //vypíše aj obsah miestnosti
                }

                else {
                    vypis="\nNeuhádol si.Opakuj príkaz pre vstup do miestnosti.\n";
                } //koniec if správna odpoveď

            }
            //nie je zamknuté
            else {
                plan.setAktualniProstor(sousedniProstor);
                vypis = sousedniProstor.dlouhyPopis(); //vypíše obsah miestnosti
            }
        }  

        return vypis;
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
