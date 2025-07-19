// Giacomo Simonetto - 2109923 - Secondo appello 2025 di Elementi di Ingegneria del Software

package myTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * <b>Summary:</b>
 * Classe che raggruppa tutti i test della suite di test per la mappa, sia vuota che popolata, e per le relative viste.
 * 
 * <p>
 * <b>Test Case Design:</b>
 * Questa classe serve a raggruppare i test relativi alle viste di una mappa vuota e popolata, in modo da poterli eseguire
 * insieme tutti in un colpo.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestEmptyMapAdapter.class,
	TestEmptyKeySet.class,
	TestEmptyValuesCollection.class,
	TestEmptyEntrySet.class,
	TestPopulatedMapAdapter.class,
	TestPopulatedKeySet.class,
	TestPopulatedValuesCollection.class,
	TestPopulatedEntrySet.class,
	TestEntryAdapter.class
})
public class AllTestsSuite {
	/**
	 * Costruttore predefinito per la classe {@code AllTestsSuite}.
	 * Questa classe è un'utility e non richiede un'inizializzazione di stato complessa.
	 */
    public AllTestsSuite() {
        // intentionally left blank
    }
}
