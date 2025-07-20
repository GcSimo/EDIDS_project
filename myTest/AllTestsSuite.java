// Giacomo Simonetto - 2109923 - Secondo appello 2025 di Elementi di Ingegneria del Software

package myTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * <b>Summary:</b>
 * Classe che raggruppa tutti i test della classe {@link myAdapter.MapAdapter} con le relative viste e iteratori.
 *
 * <p>
 * <b>Test Case Design:</b>
 * Questa classe serve a raggruppare i test relativi alla classe {@link myAdapter.MapAdapter} e alle viste e iteratori
 * sia riguardanti la mappa vuota che quella popolata, per facilitare l'esecuzione dei test in un'unica suite.
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
