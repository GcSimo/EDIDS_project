// Giacomo Simonetto - 2109923 - Secondo appello 2025 di Elementi di Ingegneria del Software

package myTest;

// importazione componenti del pacchetto myAdapter da testare
import myAdapter.HMap;
import myAdapter.MapAdapter;
import myAdapter.HMap.HEntry;
import myAdapter.HIterator;

// importazioni necessarie per JUnit
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * <b>Summary:</b>
 * Classe contenente una suite di test per verificare il comportamento di {@link myAdapter.MapAdapter.EntryAdapter}.
 * Sono testati tutti i metodi implementati della singola entry, assicurando che non lancino eccezioni inattese e
 * restituiscano risultati corretti.
 *
 * <p>
 * <b>Test Case Design:</b>
 * La motivazione di questa suite è garantire che la classe {@link myAdapter.MapAdapter.EntryAdapter} gestisca
 * correttamente i casi limite e le operazioni di base su una entry di una mappa, sia che la entry faccia parte
 * di una mappa o che sia stata rimossa, ma l'utente ne mantiene il riferimento.
 *
 * <p>
 * I test sono ordinati secondo l'ordinamento dei corrispettivi metodi testati nella documentazione della classe
 * {@link myAdapter.MapAdapter.EntryAdapter} e della relativa interfaccia {@link myAdapter.HMap.HEntry}.
 *
 * <p>
 * Siccome la classe da testare non è istanziabile direttamente, i test sono eseguiti su un riferimento di tipo
 * {@code HMap.HEntry}. Siccome la classe {@code HMap.HEntry} è sempre in stretto contatto con la mappa a cui
 * appartiene, il metodo {@code setUp()} si limita a creare una nuova mappa {@code MapAdapter} vuota e ogni test
 * sarà responsabile di crearsi la propria entry all'interno della mappa e ad ottenerne il riferimento. Per
 * facilitare l'ottenimento del riferimento di una entry, è stato creato un metodo di utilità
 * {@code getMapEntry(HMap m, int i)} che restituisce la i-esima entry della mappa {@code m} utilizzando l'iteratore
 * della vista {@code entrySet}.
 *
 * <p>
 * Sono stati implementati 9 test in totale per le operazioni eseguite sulle entry della mappa.
 *
 * <p>
 * <b>Dependencies:</b>
 * <ul>
 *   <li>File {@code JUnit}:    {@code ./JUnit/junit-4.13.jar}        - versione {@code 4.13}
 *   <li>File {@code Hamcrest}: {@code ./JUnit/hamcrest-core-1.3.jar} - versione {@code 1.3}
 * </ul>
 */
public class TestEntryAdapter {
	// variabile di istanza per la mappa da testare
	private HMap map;

	/**
	 * Costruttore predefinito per la classe {@code TestEntryAdapter}.
	 * Questa classe è un'utility e non richiede un'inizializzazione di stato complessa.
	 */
	public TestEntryAdapter() {
		// intentionally left empty
	}

	/**
	 * Metodo di setup che viene eseguito prima di ogni test.
	 * Inizializza un'istanza di {@code MapAdapter} che rappresenta una mappa vuota. Ogni test sarà responsabile
	 * di creare la propria entry all'interno di questa mappa e di ottenerne il riferimento tramite il metodo
	 * {@code buildEntry(Object key, Object value)}.
	 */
	@Before
	public void setUp() {
		map = new MapAdapter();
	}

	/**
	 * Metodo di teardown che viene eseguito dopo ogni test.
	 * Rilascia le risorse utilizzate dalla mappa.
	 */
	@After
	public void tearDown() {
		map = null;
	}

	/**
	 * Metodo di utilità per ottenere il riferimento della i-esima entry della mappa {@code m} passata come parametro.
	 * Se la mappa {@code m} è vuota o se contiene meno di {@code i+1} entry, il metodo restituirà {@code null}, per
	 * cui è ogni metodo che si deve occupare di popolare la mappa con le entry necessarie per i test.
	 *
	 * @param m la mappa da cui ottenere l'entry.
	 * @param i indice della entry da ottenere.
	 * @return il riferimento della i-esima entry della mappa {@code m}.
	 */
	protected HMap.HEntry getMapEntry(HMap m, int i) {
		HIterator it = m.entrySet().iterator();
		HMap.HEntry entry = null;
		for (int j = 0; j <= i && it.hasNext(); j++)
			entry =  (HMap.HEntry)it.next();
		return entry;
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntryAdapter#getKey()}.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code getKey()} restituisce la chiave della entry.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code getKey()} restituisca la chiave corretta della entry.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della mappa {@code MapAdapter} vuota, il
	 * metodo di test ha creato una entry {@code "a_random_key"="a_random_value"} nella mappa e ha ottenuto il
	 * riferimento della entry tramite il metodo {@code getMapEntry(HMap m, int i)}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * La mappa conterrà una entry con chiave {@code "a_random_key"} e valore {@code "a_random_value"} a cui si accederà
	 * tramite il riferimento {@code entry} ottenuto dal metodo {@code getMapEntry(HMap m, int i)}. Il metodo
	 * {@code getKey()} è stato chiamato sulla entry e il risultato è confrontato con la chiave attesa.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code getKey()} dovrebbe restituire la chiave della entry, che è {@code "a_random_key"}.
	 */
	@Test
	public void testGetKeyOnEntryAdapter() {
		map.put("a_random_key", "a_random_value");
		HEntry entry = getMapEntry(map, 0);
		assertEquals("a_random_key", entry.getKey());
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntryAdapter#getValue()}.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code getValue()} restituisce il valore della entry.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code getValue()} restituisca il valore corretto della entry.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della mappa {@code MapAdapter} vuota, il
	 * metodo di test ha creato una entry {@code "a_random_key"="a_random_value"} nella mappa e ha ottenuto il
	 * riferimento della entry tramite il metodo {@code getMapEntry(HMap m, int i)}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * La mappa conterrà una entry con chiave {@code "a_random_key"} e valore {@code "a_random_value"} a cui si accederà
	 * tramite il riferimento {@code entry} ottenuto dal metodo {@code getMapEntry(HMap m, int i)}. Il metodo
	 * {@code getValue()} è stato chiamato sulla entry e il risultato è confrontato con il valore atteso.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code getValue()} dovrebbe restituire il valore della entry, che è {@code "a_random_value"}.
	 */
	@Test
	public void testGetValueOnEntryAdapter() {
		map.put("a_random_key", "a_random_value");
		HEntry entry = getMapEntry(map, 0);
		assertEquals("a_random_value", entry.getValue());
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntryAdapter#setValue(Object value)}.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code setValue(Object value)} modifica il valore della entry sia sul riferimento della entry su cui
	 * è stato chiamato che nella mappa e di conseguenza anche nelle viste. Viene restituito il vecchio valore della
	 * entry. Se la entry non fa parte della mappa, il metodo funziona allo stesso modo, solo che non modifica la mappa.
	 * Se il valore della entry viene modificato dalla mappa, il cambiamento non si riflette sul riferimento.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code setValue(Object value)} modifichi correttamente il valore di una entry sia sul
	 * riferimento della entry su cui è stato chiamato che nella mappa e restituisca correttamente il vecchio valore.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della mappa {@code MapAdapter} vuota, il
	 * metodo di test ha creato una entry {@code "a_random_key"="a_random_value"} nella mappa e ha ottenuto il
	 * riferimento della entry tramite il metodo {@code getMapEntry(HMap m, int i)}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * La mappa conterrà una entry con chiave {@code "a_random_key"} e valore {@code "a_random_value"} a cui si accederà
	 * tramite il riferimento {@code entry} ottenuto dal metodo {@code getMapEntry(HMap m, int i)}. Il metodo
	 * {@code setValue(Object value)} è stato chiamato sulla entry e il valore della entry è stato modificato a
	 * {@code "new_value"}. Il metodo ha restituito il vecchio valore della entry, che è {@code "a_random_value"}.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code setValue(Object value)} dovrebbe restituire il vecchio valore della entry, che è
	 * {@code "a_random_value"}. Il nuovo valore della entry dovrebbe essere stato aggiornato a {@code "new_value"}
	 * sia sul riferimento della entry che nella mappa.
	 */
	@Test
	public void testSetValueWithValidEntryInsideTheMapOnEntryAdapter() {
		map.put("a_random_key", "a_random_value");
		HEntry entry = getMapEntry(map, 0);
		assertEquals("Il metodo setValue(Object value) dovrebbe restituire il vecchio valore della entry", "a_random_value", entry.setValue("new_value"));
		assertEquals("Il nuovo valore della entry dovrebbe essere stato aggiornato nel riferimento", "new_value", entry.getValue());
		assertEquals("Il nuovo valore della entry dovrebbe essere stato aggiornato nella mappa", "new_value", map.get("a_random_key"));
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntryAdapter#setValue(Object value)}.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code setValue(Object value)} modifica il valore della entry sia sul riferimento della entry su cui
	 * è stato chiamato che nella mappa e di conseguenza anche nelle viste. Viene restituito il vecchio valore della
	 * entry. Se la entry non fa parte della mappa, il metodo funziona allo stesso modo, solo che non modifica la mappa.
	 * Se il valore della entry viene modificato dalla mappa, il cambiamento non si riflette sul riferimento.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code setValue(Object value)} modifichi correttamente il valore di una entry che non fa
	 * parte della mappa, ma a cui si ha ancora il riferimento. Il metodo dovrebbe restituire il vecchio valore della
	 * entry e aggiornare il valore della entry solo sul riferimento della entry, non nella mappa.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della mappa {@code MapAdapter} vuota, il
	 * metodo di test ha creato una entry {@code "a_random_key"="a_random_value"} nella mappa e ha ottenuto il
	 * riferimento della entry tramite il metodo {@code getMapEntry(HMap m, int i)}. La entry è stata poi rimossa
	 * dalla mappa.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * La mappa non conterrà più la entry con chiave {@code "a_random_key"} e valore {@code "a_random_value"}. Il
	 * riferimento {@code entry} ottenuto dal metodo {@code getMapEntry(HMap m, int i)} è ancora valido, ma non
	 * rispecchia più il valore della entry nella mappa. Il metodo {@code setValue(Object value)} è stato chiamato
	 * sulla entry e il valore della entry è stato modificato a {@code "new_value"} solo nel riferimento. Il metodo
	 * ha restituito il vecchio valore della entry, che è {@code "a_random_value"}.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code setValue(Object value)} dovrebbe restituire il vecchio valore della entry, che è
	 * {@code "a_random_value"}. Il nuovo valore della entry dovrebbe essere stato aggiornato a {@code "new_value"}
	 * solo sul riferimento della entry. La mappa non dovrebbe contenere la entry {@code "a_random_key"="new_value"}.
	 */
	@Test
	public void testSetValueWithoutEntryInsideTheMapOnEntryAdapter() {
		map.put("a_random_key", "a_random_value");
		HEntry entry = getMapEntry(map, 0);
		map.remove("a_random_key"); // rimuove la entry dalla mappa
		assertEquals("Il metodo setValue(Object value) dovrebbe restituire il vecchio valore della entry", "a_random_value", entry.setValue("new_value"));
		assertEquals("Il nuovo valore della entry dovrebbe essere stato aggiornato nel riferimento", "new_value", entry.getValue());
		assertNull("La entry dovrebbe non essere più contenuta nella mappa", map.get("a_random_key"));
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntryAdapter#setValue(Object value)}.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code setValue(Object value)} modifica il valore della entry sia sul riferimento della entry su cui
	 * è stato chiamato che nella mappa e di conseguenza anche nelle viste. Viene restituito il vecchio valore della
	 * entry. Se la entry non fa parte della mappa, il metodo funziona allo stesso modo, solo che non modifica la mappa.
	 * Se il valore della entry viene modificato dalla mappa, il cambiamento non si riflette sul riferimento.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code setValue(Object value)} modifichi correttamente il valore di una entry che non fa
	 * parte della mappa, ma a cui si ha ancora il riferimento. Il metodo dovrebbe restituire il vecchio valore della
	 * entry e aggiornare il valore della entry solo sul riferimento della entry, non nella mappa.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della mappa {@code MapAdapter} vuota, il
	 * metodo di test ha creato una entry {@code "a_random_key"="a_random_value"} nella mappa e ha ottenuto il
	 * riferimento della entry tramite il metodo {@code getMapEntry(HMap m, int i)}. La entry è stata poi rimossa
	 * dalla mappa.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * La mappa non conterrà più la entry con chiave {@code "a_random_key"} e valore {@code "a_random_value"}. Il
	 * riferimento {@code entry} ottenuto dal metodo {@code getMapEntry(HMap m, int i)} è ancora valido, ma non
	 * rispecchia più il valore della entry nella mappa. Il metodo {@code setValue(Object value)} è stato chiamato
	 * sulla entry e il valore della entry è stato modificato a {@code "new_value"} solo nel riferimento. Il metodo
	 * ha restituito il vecchio valore della entry, che è {@code "a_random_value"}.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code setValue(Object value)} dovrebbe restituire il vecchio valore della entry, che è
	 * {@code "a_random_value"}. Il nuovo valore della entry dovrebbe essere stato aggiornato a {@code "new_value"}
	 * solo sul riferimento della entry. La mappa non dovrebbe contenere la entry {@code "a_random_key"="new_value"}.
	 */
	@Test
	public void testSetValueWithModifiedEntryInsideTheMapOnEntryAdapter() {
		map.put("a_random_key", "a_random_value");
		HEntry entry = getMapEntry(map, 0);
		map.put("a_random_key", "altered_value"); // modifica il valore della entry nella mappa
		assertEquals("Il metodo setValue(Object value) dovrebbe restituire il vecchio valore della entry", "a_random_value", entry.setValue("new_value"));
		assertEquals("Il nuovo valore della entry dovrebbe essere stato aggiornato nel riferimento", "new_value", entry.getValue());
		assertEquals("Il nuovo valore della entry non dovrebbe essere stato aggiornato nella mappa", "altered_value", map.get("a_random_key"));
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntryAdapter#equals(Object o)}.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code equals(Object o)} restituisce {@code true} se l'oggetto passato come parametro è uguale alla entry
	 * corrente, {@code false} altrimenti. Due entry sono considerate uguali se hanno la stessa chiave e lo stesso valore.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code equals(Object o)} restituisca {@code false} se gli viene passato un oggetto {@code null}.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della mappa {@code MapAdapter} vuota, il
	 * metodo di test ha creato una entry {@code "a_random_key"="a_random_value"} nella mappa e ha ottenuto il
	 * riferimento della entry tramite il metodo {@code getMapEntry(HMap m, int i)}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * La mappa conterrà una entry con chiave {@code "a_random_key"} e valore {@code "a_random_value"} a cui si
	 * accederà tramite il riferimento {@code entry} ottenuto dal metodo {@code getMapEntry(HMap m, int i)}. Il metodo
	 * {@code equals(Object o)} è stato chiamato sulla entry e dovrebbe restituire {@code false} se gli viene passato
	 * un oggetto {@code null}.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code equals(Object o)} dovrebbe restituire {@code false}.
	 */
	@Test
	public void testEqualsNullOnEntryAdapter() {
		map.put("a_random_key", "a_random_value");
		HEntry entry = getMapEntry(map, 0);
		assertFalse("Una entry dovrebbe essere diversa da un oggetto null", entry.equals(null));
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntryAdapter#equals(Object o)}.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code equals(Object o)} restituisce {@code true} se l'oggetto passato come parametro è uguale alla entry
	 * corrente, {@code false} altrimenti. Due entry sono considerate uguali se hanno la stessa chiave e lo stesso valore.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code equals(Object o)} restituisca {@code false} se gli viene passato un oggetto che
	 * non è un'istanza di {@code EntryAdapter}, in questo caso è di tipo {@code String}.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della mappa {@code MapAdapter} vuota, il
	 * metodo di test ha creato una entry {@code "a_random_key"="a_random_value"} nella mappa e ha ottenuto il
	 * riferimento della entry tramite il metodo {@code getMapEntry(HMap m, int i)}. Viene creato un oggetto di tipo
	 * {@code String} per simulare un oggetto non valido.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * La mappa conterrà una entry con chiave {@code "a_random_key"} e valore {@code "a_random_value"} a cui si
	 * accederà tramite il riferimento {@code entry} ottenuto dal metodo {@code getMapEntry(HMap m, int i)}. Il metodo
	 * {@code equals(Object o)} è stato chiamato sulla entry e dovrebbe restituire {@code false} se gli viene passato
	 * un oggetto che non è un'istanza di {@code EntryAdapter}, in questo caso è di tipo {@code String}.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code equals(Object o)} dovrebbe restituire {@code false}.
	 */
	@Test
	public void testEqualsNotEntryAdapterOnEntryAdapter() {
		map.put("a_random_key", "a_random_value");
		HEntry entry = getMapEntry(map, 0);
		String notAnEntryAdapter = "This is not an EntryAdapter";
		assertFalse("Una entry dovrebbe essere diversa da un oggetto che non è un EntryAdapter", entry.equals(notAnEntryAdapter));
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntryAdapter#equals(Object o)}.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code equals(Object o)} restituisce {@code true} se l'oggetto passato come parametro è uguale alla entry
	 * corrente, {@code false} altrimenti. Due entry sono considerate uguali se hanno la stessa chiave e lo stesso valore.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code equals(Object o)} restituisca {@code false} se gli viene passata una entry con
	 * stessa chiave, ma con valore diverso.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della mappa {@code MapAdapter} vuota, il
	 * metodo di test ha creato una entry {@code "a_random_key"="a_random_value"} nella mappa e ha ottenuto il
	 * riferimento della entry tramite il metodo {@code getMapEntry(HMap m, int i)}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * La mappa conterrà una entry con chiave {@code "a_random_key"} e valore {@code "a_random_value"} a cui si
	 * accederà tramite il riferimento {@code entry} ottenuto dal metodo {@code getMapEntry(HMap m, int i)}. Viene poi
	 * modificata la entry sulla mappa ed è stato richiamato il metodo {@code getMapEntry(HMap m, int i)} per ottenere
	 * una nuova entry con stessa chiave, ma diverso valore. Il metodo {@code equals(Object o)} è stato chiamato sulla
	 * entry e dovrebbe restituire {@code false} se gli viene passata una entry con stessa chiave, ma con valore diverso.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code equals(Object o)} dovrebbe restituire {@code false}.
	 */
	@Test
	public void testEqualsSameKeyOnEntryAdapter() {
		map.put("a_random_key", "a_random_value");
		HEntry entry = getMapEntry(map, 0);
		HMap newMap = new MapAdapter();
		newMap.put("a_new_key", "a_random_value");
		HEntry anotherEntry = getMapEntry(newMap, 0);
		assertFalse("Due entry con stessa chiave, ma diverso valore dovrebbero essere diverse", entry.equals(anotherEntry));
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntryAdapter#equals(Object o)}.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code equals(Object o)} restituisce {@code true} se l'oggetto passato come parametro è uguale alla entry
	 * corrente, {@code false} altrimenti. Due entry sono considerate uguali se hanno la stessa chiave e lo stesso valore.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code equals(Object o)} restituisca {@code false} se gli viene passata una entry con
	 * stesso valore, ma con chiave diversa.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della mappa {@code MapAdapter} vuota, il
	 * metodo di test ha creato una entry {@code "a_random_key"="a_random_value"} nella mappa e ha ottenuto il
	 * riferimento della entry tramite il metodo {@code getMapEntry(HMap m, int i)}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * La mappa conterrà una entry con chiave {@code "a_random_key"} e valore {@code "a_random_value"} a cui si
	 * accederà tramite il riferimento {@code entry} ottenuto dal metodo {@code getMapEntry(HMap m, int i)}. Viene poi
	 * inserita una nuova entry sulla mappa ed è stato richiamato il metodo {@code getMapEntry(HMap m, int i)} per ottenere
	 * una nuova entry con stesso valore, ma diversa chiave. Il metodo {@code equals(Object o)} è stato chiamato sulla
	 * entry e dovrebbe restituire {@code false} se gli viene passata una entry con stesso valore, ma con chiave diversa.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code equals(Object o)} dovrebbe restituire {@code false}.
	 */
	public void testEqualsSameValueOnEntryAdapter() {
		map.put("a_random_key", "a_random_value");
		map.put("a_new_key", "a_random_value");
		HEntry entry1 = getMapEntry(map, 0);
		HEntry entry2 = getMapEntry(map, 1);
		assertFalse("Due entry con stesso valore, ma diverso chiave dovrebbero essere diverse", entry1.equals(entry2));
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntryAdapter#equals(Object o)}.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code equals(Object o)} restituisce {@code true} se l'oggetto passato come parametro è uguale alla entry
	 * corrente, {@code false} altrimenti. Due entry sono considerate uguali se hanno la stessa chiave e lo stesso valore.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code equals(Object o)} restituisca {@code true} se gli viene passata una entry con
	 * stessa chiave e stesso valore.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della mappa {@code MapAdapter} vuota, il
	 * metodo di test ha creato una entry {@code "a_random_key"="a_random_value"} nella mappa e ha ottenuto il
	 * riferimento della entry tramite il metodo {@code getMapEntry(HMap m, int i)}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * La mappa conterrà una entry con chiave {@code "a_random_key"} e valore {@code "a_random_value"} a cui si
	 * accederà tramite il riferimento {@code entry} ottenuto dal metodo {@code getMapEntry(HMap m, int i)}. Viene
	 * richiamato una seconda volta il metodo {@code getMapEntry(HMap m, int i)} per ottenere una nuova entry con
	 * stessa chiave e valore. Il metodo {@code equals(Object o)} è stato chiamato sulla entry e dovrebbe restituire
	 * {@code true} se gli viene passato un oggetto con stessa chiave e stesso valore.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code equals(Object o)} dovrebbe restituire {@code true}.
	 */
	public void testEqualsSameEntryAdapterOnEntryAdapter() {
		map.put("a_random_key", "a_random_value");
		HEntry entry = getMapEntry(map, 0);
		HMap newMap = new MapAdapter();
		newMap.put("a_random_key", "a_random_value");
		HEntry anotherEntry = getMapEntry(newMap, 0);
		assertTrue("Due entry con stessa chiave, ma diverso valore dovrebbero essere diverse", entry.equals(anotherEntry));
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntryAdapter#equals(Object o)}.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code equals(Object o)} restituisce {@code true} se l'oggetto passato come parametro è uguale alla entry
	 * corrente, {@code false} altrimenti. Due entry sono considerate uguali se hanno la stessa chiave e lo stesso valore.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code equals(Object o)} restituisca {@code true} se gli viene passata la stessa entry
	 * su cui è stato chiamato il metodo {@code equals(Object o)}.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della mappa {@code MapAdapter} vuota, il
	 * metodo di test ha creato una entry {@code "a_random_key"="a_random_value"} nella mappa e ha ottenuto il
	 * riferimento della entry tramite il metodo {@code getMapEntry(HMap m, int i)}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * La mappa conterrà una entry con chiave {@code "a_random_key"} e valore {@code "a_random_value"} a cui si
	 * accederà tramite il riferimento {@code entry} ottenuto dal metodo {@code getMapEntry(HMap m, int i)}. Il metodo
	 * {@code equals(Object o)} è stato chiamato sulla entry e dovrebbe restituire {@code true} se gli viene passata
	 * la stessa entry su cui è stato chiamato il metodo {@code equals(Object o)}.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code equals(Object o)} dovrebbe restituire {@code true}.
	 */
	public void testSelfEqualsOnEntryAdapter() {
		map.put("a_random_key", "a_random_value");
		HEntry entry = getMapEntry(map, 0);
		assertTrue("Due entry con stessa chiave, ma diverso valore dovrebbero essere diverse", entry.equals(entry));
	}


	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntryAdapter#hashCode()}.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code hashCode()} restituisce lo stesso hash code per due entry che hanno la stessa chiave e lo
	 * stesso valore.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code hashCode()} restituisca lo stesso valore per due entry con la stessa chiave e
	 * lo stesso valore.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della mappa {@code MapAdapter} vuota,
	 * il metodo di test ha creato una entry {@code "a_random_key"="a_random_value"} nella mappa e ha ottenuto il
	 * riferimento della entry tramite il metodo {@code getMapEntry(HMap m, int i)}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * La mappa conterrà una entry con chiave {@code "a_random_key"} e valore {@code "a_random_value"} a cui si accederà
	 * tramite il riferimento {@code entry} ottenuto dal metodo {@code getMapEntry(HMap m, int i)}. Viene poi creata
	 * una seconda entry con la stessa chiave e valore. Il metodo {@code hashCode()} è stato chiamato sulla entry e
	 * dovrebbe restituire lo stesso valore per la seconda entry.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code hashCode()} dovrebbe restituire lo stesso valore per due entry con la stessa chiave e lo stesso
	 * valore.
	 */
	@Test
	public void testHashCodeOnEntryAdapter() {
		map.put("a_random_key", "a_random_value");
		HEntry entry = getMapEntry(map, 0);
		HMap newMap = new MapAdapter();
		newMap.put("a_random_key", "a_random_value");
		HEntry anotherEntry = getMapEntry(newMap, 0);
		assertEquals("Gli hash code delle entry dovrebbero essere uguali", entry.hashCode(), anotherEntry.hashCode());
	}
}
