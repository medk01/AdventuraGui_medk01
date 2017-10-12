package logika;

/**
 *  Rozhranie IPrikaz pouzivany v prikazov pre hru
 *  Rozhranie je sucastou jednoduchej textovej hry.
 *  
 * @author     Katarina Medovarska
 * @version    1.0
 */

interface IPrikaz {

    /**
     *  Metoda pro provedení příkazu ve hře.
     *  Počet parametrů je závislý na konkrétním příkazu,
     *  např. příkazy konec a napoveda nemají parametry
     *  příkazy jdi, seber, polož mají jeden parametr
     *  příkaz pouzij může mít dva parametry.
     *  
     *  @param parametry počet parametrů závisí na konkrétním příkazu.
     *  @return vracia retazec informujuci o vykonani prikazu
     */
    public String proved(String... parametry);

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return nazev prikazu
     */
    public String getNazev();

}
