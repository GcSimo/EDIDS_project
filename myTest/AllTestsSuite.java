package myTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * <b>Summary:</b>
 * Classe che raggruppa tutti i test della suite di test per le viste di una mappa vuota.
 * 
 * <p>
 * <b>Test Case Design:</b>
 * Questa classe serve a raggruppare i test relativi alle viste di una mappa vuota, in modo da poterli eseguire insieme.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ TestEmptyMapAdapter.class, TestKeySetOnEmptyMapAdapter.class, TestValuesCollectionOnEmptyMapAdapter.class, TestEntrySetOnEmptyMapAdapter.class })
public class AllTestsSuite {

	/**
	 * Costruttore predefinito per la classe {@code AllTestsSuite}.
	 * Questa classe è un'utility e non richiede un'inizializzazione di stato complessa.
	 */
    public AllTestsSuite() {
        // intentionally left blank
    }
}
