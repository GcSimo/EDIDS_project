// Giacomo Simonetto - 2109923 - Secondo appello 2025 di Elementi di Ingegneria del Software

package myTest;

// importazione componenti del pacchetto myAdapter da testare
import myAdapter.HCollection;
import myAdapter.HSet;
import myAdapter.HIterator;
import myAdapter.HMap;
import myAdapter.MapAdapter;
import myAdapter.UnsupportedOperationException;
import myAdapter.HMap.HEntry;
import myAdapter.IllegalStateException;

// importazione della classe Arrays per testare le operazioni sugli array
import java.util.Arrays;

// importazioni necessarie per JUnit
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;

/**
 * <b>Summary:</b>
 * Classe contenente una suite di test per verificare il comportamento di {@link myAdapter.MapAdapter.EntrySetAdapter},
 * quando proviene da una mappa popolata. Sono testati tutti i metodi implementati dalla vista, assicurando che non
 * lancino eccezioni inattese e restituiscano risultati corretti. Sono inoltre testati i metodi dell'iteratore associato
 * alla vista, per garantire che funzionino correttamente anche su una vista popolata
 * 
 * <p>
 * <b>Test Case Design:</b>
 * La motivazione di questa suite è garantire che la classe {@link myAdapter.MapAdapter.EntrySetAdapter} e il relativo
 * iteratore {@link myAdapter.MapAdapter.EntrySetIterator} gestiscano correttamente i casi limite e le operazioni collegate
 * ad una mappa popolata, ovvero nell'utilizzo concreto di tale struttura.
 * 
 * <p>
 * Non esiste un ordinamento specifico dei test. Nella loro implementazione all'interno del file sorgente si trovano
 * prima i test eseguiti sui metodi della vista e successivamente quelli eseguiti sui metodi dell'iteratore.
 * I test sono comunque ordinati secondo l'ordinamento dei corrispettivi metodi testati nella documentazione
 * delle classi {@link myAdapter.MapAdapter.AbstractViewAdapter}, {@link myAdapter.MapAdapter.EntrySetAdapter},
 * {@link myAdapter.MapAdapter.AbstractIterator} e {@link myAdapter.MapAdapter.EntrySetIterator}.
 * 
 * <p>
 * Siccome le classi da testare non sono istanziabili direttamente, i test della vista sono eseguiti su un oggetto
 * {@code HSet} ottenuto da una mappa popolata, tramite il metodo {@code entrySet()} della mappa, mentre quelli dell'iteratore
 * sono eseguiti su un oggetto {@code HIterator} ottenuto dalla stessa mappa popolata, tramite il metodo
 * {@code entrySet().iterator()} della vista.
 * 
 * <p>
 * I costruttori delle viste e degli iteratori sono già stati testati negli opportuni test che ne restituiscono
 * le corrispettive istanze, come ad esempio {@link myAdapter.MapAdapter#entrySet()} per la vista e
 * {@link myAdapter.MapAdapter.EntrySetAdapter#iterator()} per l'iteratore. 
 * 
 * <p>
 * <b>Dependencies:</b>
 * <ul>
 *   <li>File JUnit.jar: {@code ./JUnit/junit-4.13.jar} - versione {@code 4.13}
 *   <li>File Hamcrest.jar: {@code ./JUnit/hamcrest-core-1.3.jar} - versione {@code 1.3}
 * </ul>
 */
public class TestPopulatedEntrySet {
	// variabili di istanza per la mappa, la vista e l'iteratoreda testare
	private MapAdapter map;
	private HSet entrySet;
	private HIterator entrySetIterator;

/* ================================================================================================================= *\
|* ====================================== Test dei metodi della vista entrySet ===================================== *|
\* ================================================================================================================= */

	/**
	 * Costruttore predefinito per la classe {@code TestPopulatedEntrySet}.
	 * Questa classe è un'utility e non richiede un'inizializzazione di stato complessa.
	 */
	public TestPopulatedEntrySet() {
		// intentionally left empty
	}

	/**
	 * Metodo di setup che viene eseguito prima di ogni test.
	 * Inizializza un'istanza di {@code MapAdapter} che rappresenta una mappa popolata con le seguenti 4 entry 
	 * {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}. da cui ottiene un'istanza di {@code EntrySetAdapter}
	 * che rappresenta la vista per chiavi e un'istanza di {@code EntrySetIterator} per l'iteratore della vista.
	 */
	@Before
	public void setUp() {
		map = new MapAdapter();
		map.put("key1", "value1");
		map.put("key2", 2);
		map.put(3, "value3");
		map.put(4, 4);
		entrySet = map.entrySet();
		entrySetIterator = entrySet.iterator();
	}

	/**
	 * Metodo di teardown che viene eseguito dopo ogni test.
	 * Rilascia le risorse utilizzate dalla mappa.
	 */
	@After
	public void tearDown() {
		map = null;
		entrySet = null;
		entrySetIterator = null;
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#size()}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code size()} restituisce il numero di elementi presenti nella vista.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code size()} restituisca 4, ovvero il numero di elementi presenti in una vista proveniente
	 * da una mappa popolata. Verifico che modificando la mappa, il numero di elementi nella vista si aggiorni correttamente.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code size()} è stato chiamato su una vista popolata e ha restituito 4. Successivamente, la mappa è
	 * stata modificata con una put e due remove e il metodo {@code size()} è stato chiamato nuovamente, restituendo
	 * prima 5 e poi 3.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code size()} dovrebbe restituire 4, poi 5 e infine 3 dopo le modifiche alla mappa.
	 */
	@Test
	public void testSizeOnPopulatedEntrySet() {
		assertEquals("La vista dovrebbe avere 4 elementi", 4, entrySet.size());
		map.put("new_key", "new_value");
		assertEquals("La vista dovrebbe avere 5 elementi", 5, entrySet.size());
		map.remove("key1");
		map.remove("key2");
		assertEquals("La vista dovrebbe avere 3 elementi", 3, entrySet.size());
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#isEmpty()}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code isEmpty()} restituisce {@code true} se la vista non contiene elementi, {@code false} altrimenti.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code isEmpty()} restituisca {@code false} per una vista popolata. Inoltre verifica che
	 * svuotando la mappa, il metodo {@code isEmpty()} restituisca {@code true} quando la vista diventa vuota.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code isEmpty()} è stato chiamato su una vista popolata e ha restituito {@code false}, dopo aver svuotato
	 * la mappa il metodo ha restituito {@code true}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code isEmpty()} dovrebbe restituire {@code false} e infine {@code true}.
	 */
	@Test
	public void testIsEmptyOnPopulatedEntrySet() {
		assertFalse("La vista dovrebbe essere popolata", entrySet.isEmpty());
		map.clear(); // svuota la mappa
		assertTrue("La vista dovrebbe essere vuota dopo aver svuotato la mappa", entrySet.isEmpty());
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#contains(Object o)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code contains(Object o)} verifica se l'oggetto specificato è contenuto nella vista (e di conseguenza
	 * come entry della mappa). Restituisce {@code true} se l'oggetto è presente, {@code false} altrimenti.
	 * Lancia {@code NullPointerException} se l'oggetto passato è {@code null} o se la entry ha chiave o valore {@code null}.
	 * Lancia {@code ClassCastException} se l'oggetto non implementa {@code HMap.HEntry} e quindi non è una entry della mappa.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code contains(Object o)} restituisca {@code false} quando si prova a cercare una generica
	 * entry diversa da {@code null} e non presente nella vista, verifica che il metodo restituisca {@code false} anche
	 * quando si cerca una entry con chiavi o valori di un tipo diverso da quelli presenti nella vista, come ad esempio
	 * {@code Double}. Verifica infine che il metodo restituisca {@code true} quando si cerca una entry presente nella vista.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 * Si creano quattro entry da passare come parametro al metodo {@code contains(Object o)}:
	 * <ul>
	 *    <li>una generica entry con chiave e valore generici di tipo {@code String} non presente nella mappa,</li>
	 *    <li>una entry {@code 3.1415="valueX"} con chiave {@code Double}</li>
	 *    <li>una entry {@code "keyX"=3.1415} con valore {@code Double}</li>
	 *    <li>una entry {@code "key1"="value1"} presente nella mappa.</li>
	 * </ul>
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code contains(Object o)} è stato chiamato su una vista popolata e ha restituito {@code false}
	 * quando si è cercato una entry non presente, ha restituito {@code false} quando si è cercato una entry con chiave
	 * o valore di tipo {@code Double} e ha restituito {@code true} quando si è cercato una entry presente nella vista.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code contains(Object o)} dovrebbe restituire {@code false}, poi {@code false} e infine {@code true}.
	 */
	@Test
	public void testContainsOnPopulatedEntrySet() {
		HEntry e1 = new SimpleHMapWithNulls.SimpleHEntry("a_random_key", "a_random_value");
		HEntry e2 = new SimpleHMapWithNulls.SimpleHEntry(3.1415, "valueX");
		HEntry e3 = new SimpleHMapWithNulls.SimpleHEntry("keyX", 3.1415);
		HEntry e4 = new SimpleHMapWithNulls.SimpleHEntry("key1", "value1");
		assertFalse("La vista non dovrebbe contenere la entry <\"a_random_key\"=\"a_random_value\">", entrySet.contains(e1));
		assertFalse("La vista non dovrebbe contenere la entry <3.1415=\"valueX\">", entrySet.contains(e2));
		assertFalse("La vista non dovrebbe contenere la entry <\"keyX\"=3.1415>", entrySet.contains(e3));
		assertTrue("La vista dovrebbe contenere la entry <\"key1\"=\"value1\">", entrySet.contains(e4));
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#contains(Object o)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code contains(Object o)} verifica se l'oggetto specificato è contenuto nella vista (e di conseguenza
	 * come entry della mappa). Restituisce {@code true} se l'oggetto è presente, {@code false} altrimenti. Lancia
	 * {@code NullPointerException} se l'oggetto passato è {@code null} o se la entry ha chiave o valore {@code null}.
	 * Lancia {@code ClassCastException} se l'oggetto non implementa {@code HMap.HEntry} e quindi non è una entry della mappa.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code contains(Object o)} lanci {@code NullPointerException} se l'oggetto passato è
	 * {@code null}.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa vuota.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code contains(Object o)} è stato chiamato con un oggetto {@code null} e ha lanciato una
	 * {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code contains(Object o)} dovrebbe lanciare una {@code NullPointerException}.
	 */
	@Test(expected = NullPointerException.class)
	public void testContainsNullEntryOnPopulatedEntrySet() {
		entrySet.contains(null);
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#contains(Object o)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code contains(Object o)} verifica se l'oggetto specificato è contenuto nella vista (e di conseguenza
	 * come entry della mappa). Restituisce {@code true} se l'oggetto è presente, {@code false} altrimenti. Lancia
	 * {@code NullPointerException} se l'oggetto passato è {@code null} o se la entry ha chiave o valore {@code null}.
	 * Lancia {@code ClassCastException} se l'oggetto non implementa {@code HMap.HEntry} e quindi non è una entry della mappa.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code contains(Object o)} lanci {@code NullPointerException} quando gli viene passata
	 * una generica entry con chiave {@code null} (in questo caso con valore di tipo {@code String}, istanziata
	 * attraverso la classe {@code SimpleHMapWithNulls.SimpleHEntry}).
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa vuota. Viene inoltre creata una nuova entry generica con chiave {@code null} (sfruttando
	 * l'implementazione {@code SimpleHMapWithNulls.SimpleHEntry}) da passare come parametro.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code contains(Object o)} è stato chiamato con una generica entry con chiave {@code null} e ha lanciato
	 * una {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code contains(Object o)} dovrebbe lanciare una {@code NullPointerException}.
	 */
	@Test(expected = NullPointerException.class)
	public void testContainsNullKeyOnPopulatedEntrySet() {
		HEntry e = new SimpleHMapWithNulls.SimpleHEntry(null, "a_random_value");
		entrySet.contains(e); // lancia NullPointerException
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#contains(Object o)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code contains(Object o)} verifica se l'oggetto specificato è contenuto nella vista (e di conseguenza
	 * come entry della mappa). Restituisce {@code true} se l'oggetto è presente, {@code false} altrimenti. Lancia
	 * {@code NullPointerException} se l'oggetto passato è {@code null} o se la entry ha chiave o valore {@code null}.
	 * Lancia {@code ClassCastException} se l'oggetto non implementa {@code HMap.HEntry} e quindi non è una entry della mappa.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code contains(Object o)} lanci {@code NullPointerException} quando gli viene passata
	 * una generica entry con valore {@code null} (in questo caso con chiave di tipo {@code String}, istanziata
	 * attraverso la classe {@code SimpleHMapWithNulls.SimpleHEntry}).
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa vuota. Viene inoltre creata una nuova entry generica con valore {@code null} (sfruttando
	 * l'implementazione {@code SimpleHMapWithNulls.SimpleHEntry}) da passare come parametro.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code contains(Object o)} è stato chiamato con una generica entry con valore {@code null} e ha
	 * lanciato una {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code contains(Object o)} dovrebbe lanciare una {@code NullPointerException}.
	 */
	@Test(expected = NullPointerException.class)
	public void testContainsNullValueOnPopulatedEntrySet() {
		HEntry e = new SimpleHMapWithNulls.SimpleHEntry("a_random_key", null);
		entrySet.contains(e); // lancia NullPointerException
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#contains(Object o)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code contains(Object o)} verifica se l'oggetto specificato è contenuto nella vista (e di conseguenza
	 * come entry della mappa). Restituisce {@code true} se l'oggetto è presente, {@code false} altrimenti. Lancia
	 * {@code NullPointerException} se l'oggetto passato è {@code null} o se la entry ha chiave o valore {@code null}.
	 * Lancia {@code ClassCastException} se l'oggetto non implementa {@code HMap.HEntry} e quindi non è una entry della mappa.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code contains(Object o)} lanci {@code ClassCastException} quando gli viene passato
	 * un oggetto che non è una entry (non implementa {@code HMap.HEntry}) (in questo caso una stringa).
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa vuota. Viene inoltre creata una stringa che banalmente non implementa {@code HMap.HEntry}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code contains(Object o)} è stato chiamato con un oggetto che non è una entry e ha lanciato una
	 * {@code ClassCastException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code contains(Object o)} dovrebbe lanciare una {@code ClassCastException}.
	 */
	@Test(expected = ClassCastException.class)
	public void testContainsNotEntryOnPopulatedEntrySet() {
		String notAnEntry = "This is not an Entry";
		entrySet.contains(notAnEntry); // lancia ClassCastException
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#iterator()}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code iterator()} dovrebbe restituire un iteratore di tipo {@code EntrySetIterator} su una vista popolata.
	 * I metodi degli iteratori sono testati in modo specifico in altri test, quindi qui ci si limita a verificare
	 * che l'iteratore restituito sia valido.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che l'iteratore restituito sia valido.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * L'iteratore è stato restituito e può essere utilizzato per iterare sulla vista.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * L'iteratore dovrebbe essere un'istanza di {@code HIterator} e dovrebbe essere diverso da null.
	 */
	@Test
	public void testIteratorOnPopulatedEntrySet() {
		HIterator iterator = entrySet.iterator();
		assertNotNull("L'iteratore non dovrebbe essere null", iterator);
		assertTrue("L'iteratore dovrebbe essere un'istanza di HIterator", iterator instanceof myAdapter.HIterator);
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#toArray()}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code toArray()} restituisce un array contenente tutti gli elementi della vista. L'ordine degli
	 * elementi nell'array corrisponde all'ordine in cui gli elementi vengono restituiti dall'iteratore della vista.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code toArray()} restituisca un array di lunghezza 4 per una vista proveniente
	 * da una mappa popolata con 4 entry.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code toArray()} è stato chiamato su una vista popolata e ha restituito un array di lunghezza 4,
	 * contenente gli oggetti presenti nella vista.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code toArray()} dovrebbe restituire un array di Object di lunghezza 4 e contenente gli oggetti
	 * presenti nella vista.
	 */
	@Test
	public void testToArray1OnPopulatedEntrySet() {
		// Creazione delle entry attese
		HEntry e1 = new SimpleHMapWithNulls.SimpleHEntry("key1", "value1");
		HEntry e2 = new SimpleHMapWithNulls.SimpleHEntry("key2", 2);
		HEntry e3 = new SimpleHMapWithNulls.SimpleHEntry(3, "value3");
		HEntry e4 = new SimpleHMapWithNulls.SimpleHEntry(4, 4);
		
		// Chiamata al metodo toArray
		Object[] array = entrySet.toArray();
		
		// Verifica i tipi degli elementi dell'array
		assertTrue("La vista dovrebbe restituire un array di Object", array instanceof Object[]);
		for (Object obj : array)
			assertTrue("Tutti gli elementi dell'array dovrebbero essere di tipo HMap.HEntry",
					obj instanceof HMap.HEntry);
		
		// Verifica che l'array contenga le entry attese
		assertTrue("L'array dovrebbe contenere <\"key1\"=\"value1\">", Arrays.asList(array).contains(e1));
		assertTrue("L'array dovrebbe contenere <\"key2\"=2>", Arrays.asList(array).contains(e2));
		assertTrue("L'array dovrebbe contenere <3=\"value3\">", Arrays.asList(array).contains(e3));
		assertTrue("L'array dovrebbe contenere <4=4>", Arrays.asList(array).contains(e4));
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#toArray()}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code toArray()} restituisce un array contenente tutti gli elementi della vista. L'ordine degli
	 * elementi nell'array corrisponde all'ordine in cui gli elementi vengono restituiti dall'iteratore della vista.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code toArray()} restituisca un array indipendente dalla vista e dalla mappa, per
	 * cui se la mappa viene modificata dopo aver chiamato {@code toArray()}, l'array restituito non deve riflettere
	 * tali modifiche e viceversa.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code toArray()} è stato chiamato su una vista popolata. Viene poi aggiunta una nuova entry alla mappa
	 * (e di conseguenza un nuovo elemento alla vista), mentre l'array restituito da {@code toArray()} non viene
	 * influenzato da tali modifiche.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code toArray()} dovrebbe restituire un array che non contiene il nuovo elemento aggiunto alla vista.
	 */
	@Test
	public void testToArray1IndependenceOnPopulatedEntrySet() {
		HEntry e = new SimpleHMapWithNulls.SimpleHEntry("new_key", "new_value");
		Object[] array = entrySet.toArray();
		map.put("new_key", "new_value"); // modifica la mappa
		assertTrue("La mappa dovrebbe contenere 'new_key'", map.containsKey("new_key")); // verifica che la mappa contenga la nuova chiave
		assertTrue("La vista dovrebbe contenere 'new_key'", entrySet.contains(e)); // verifica che la vista contenga la nuova entry
		assertFalse("L'array non dovrebbe contenere 'new_key'", Arrays.asList(array).contains(e)); // verifica che l'array non contenga la nuova entry
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#toArray(Object[] a)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code toArray(Object[] a)} restituisce un array contenente tutti gli elementi della vista. L'ordine
	 * degli elementi nell'array corrisponde all'ordine in cui gli elementi vengono restituiti dall'iteratore della
	 * vista. Se l'array passato come parametro è di dimensione inferiore alla dimensione della vista, viene creato un
	 * nuovo array di dimensione adeguata, altrimenti gli elementi della vista vengono copiati nell'array passato come
	 * parametro e l'elemento successivo alla fine della vista viene impostato a {@code null}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Nell'array finale dovrebbero esserci 4 elementi e dovrebbe essere lo stesso array passato come parametro,
	 * con il quinto elemento impostato a {@code null}.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}. Viene
	 * creato un nuovo array di 10 elementi passato come parametro e riempito con interi 1, per testare se effettivamente
	 * il quinto elemento è impostato a {@code null}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code toArray(Object[] a)} è stato chiamato su una vista popolata e ha restituito lo stesso array passato
	 * come parametro, con il quinto elemento impostato a {@code null}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code toArray(Object[] a)} dovrebbe restituire un array con lo stesso riferimento di quello passato
	 * come parametro, con il quinto elemento impostato a {@code null}.
	 */
	@Test
	public void testToArray2OnPopulatedEntrySet() {
		Object[] reference = entrySet.toArray();
		Object[] array = new Object[10];
		Arrays.fill(array, 1); // inizializza l'array con valori diversi da null
		assertTrue("L'array restituito dovrebbe essere lo stesso array passato come parametro", array == entrySet.toArray(array));
		assertNull("Il quinto elemento dell'array restituito dovrebbe essere null", array[4]);
		assertEquals("Il primo elemento dell'array restituito dovrebbe essere uguale a quello dell'array ottenuto dal metodo toArray()", reference[0], array[0]);
		assertEquals("Il secondo elemento dell'array restituito dovrebbe essere uguale a quello dell'array ottenuto dal metodo toArray()", reference[1], array[1]);
		assertEquals("Il terzo elemento dell'array restituito dovrebbe essere uguale a quello dell'array ottenuto dal metodo toArray()", reference[2], array[2]);
		assertEquals("Il quarto elemento dell'array restituito dovrebbe essere uguale a quello dell'array ottenuto dal metodo toArray()", reference[3], array[3]);
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#toArray(Object[] a)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code toArray(Object[] a)} restituisce un array contenente tutti gli elementi della vista. L'ordine
	 * degli elementi nell'array corrisponde all'ordine in cui gli elementi vengono restituiti dall'iteratore della
	 * vista. Se l'array passato come parametro è di dimensione inferiore alla dimensione della vista, viene creato un
	 * nuovo array di dimensione adeguata, altrimenti gli elementi della vista vengono copiati nell'array passato come
	 * parametro e l'elemento successivo alla fine della vista viene impostato a {@code null}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code toArray(Object[] a)} lanci una {@code NullPointerException} quando si prova a
	 * passare un array {@code null} come parametro.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}. Viene
	 * creato un nuovo array con riferimento {@code null} da passare come parametro al metodo.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code toArray(Object[] a)} è stato chiamato con un array {@code null} come parametro e ha lanciato una
	 * {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code toArray(Object[] a)} dovrebbe lanciare una {@code NullPointerException}.
	 */
	@Test(expected = NullPointerException.class)
	public void testToArray2NullArrayOnPopulatedEntrySet() {
		Object[] array = null;
		entrySet.toArray(array); // lancia NullPointerException
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#toArray(Object[] a)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code toArray(Object[] a)} restituisce un array contenente tutti gli elementi della vista. L'ordine
	 * degli elementi nell'array corrisponde all'ordine in cui gli elementi vengono restituiti dall'iteratore della
	 * vista. Se l'array passato come parametro è di dimensione inferiore alla dimensione della vista, viene creato un
	 * nuovo array di dimensione adeguata, altrimenti gli elementi della vista vengono copiati nell'array passato come
	 * parametro e l'elemento successivo alla fine della vista viene impostato a {@code null}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code toArray(Object[] a)} crei un nuovo array quando si passa un array di dimensione
	 * nulla come parametro, siccome la vista contiene elementi da inserire dentro.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}. Viene
	 * creato un nuovo array di dimensione nulla da passare come parametro al metodo.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code toArray(Object[] a)} è stato chiamato con un array di dimensione nulla come parametro e ha
	 * restituito un nuovo array contenente gli elementi della vista.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code toArray(Object[] a)} dovrebbe restituire un nuovo array contenente gli elementi della vista.
	 */
	@Test
	public void testToArray2EmptyArrayOnPopulatedEntrySet() {
		Object[] reference = entrySet.toArray();
		Object[] array = new Object[0];
		Object[] returnedArray = entrySet.toArray(array);
		assertTrue("L'array restituito dovrebbe essere un nuovo array", array != returnedArray);
		assertTrue("L'array restituito dovrebbe avere dimensione 4", returnedArray.length == 4);
		assertEquals("Il primo elemento dell'array restituito dovrebbe essere uguale a quello dell'array ottenuto dal metodo toArray()", reference[0], returnedArray[0]);
		assertEquals("Il secondo elemento dell'array restituito dovrebbe essere uguale a quello dell'array ottenuto dal metodo toArray()", reference[1], returnedArray[1]);
		assertEquals("Il terzo elemento dell'array restituito dovrebbe essere uguale a quello dell'array ottenuto dal metodo toArray()", reference[2], returnedArray[2]);
		assertEquals("Il quarto elemento dell'array restituito dovrebbe essere uguale a quello dell'array ottenuto dal metodo toArray()", reference[3], returnedArray[3]);
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#toArray(Object[] a)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code toArray(Object[] a)} restituisce un array contenente tutti gli elementi della vista. L'ordine
	 * degli elementi nell'array corrisponde all'ordine in cui gli elementi vengono restituiti dall'iteratore della
	 * vista. Se l'array passato come parametro è di dimensione inferiore alla dimensione della vista, viene creato un
	 * nuovo array di dimensione adeguata, altrimenti gli elementi della vista vengono copiati nell'array passato come
	 * parametro e l'elemento successivo alla fine della vista viene impostato a {@code null}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code toArray(Object[] a)} restituisca un array indipendente dalla vista e dalla mappa, per
	 * cui se la mappa viene modificata dopo aver chiamato {@code toArray(Object[] a)}, l'array restituito non deve riflettere
	 * tali modifiche e viceversa.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code toArray(Object[] a)} è stato chiamato su una vista popolata. Viene poi aggiunta una nuova entry
	 * alla mappa (e di conseguenza un nuovo elemento alla vista), mentre l'array restituito da {@code toArray(Object[] a)}
	 * non viene influenzato da tali modifiche.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code toArray(Object[] a)} dovrebbe restituire un array che non contiene il nuovo elemento aggiunto alla vista.
	 */
	@Test
	public void testToArray2IndependenceOnPopulatedEntrySet() {
		HEntry e = new SimpleHMapWithNulls.SimpleHEntry("new_key", "new_value");
		Object[] array = entrySet.toArray(new Object[0]);
		map.put("new_key", "new_value"); // modifica la mappa
		assertTrue("La mappa dovrebbe contenere 'new_key'", map.containsKey("new_key")); // verifica che la mappa contenga la nuova chiave
		assertTrue("La vista dovrebbe contenere 'new_key'", entrySet.contains(e)); // verifica che la vista contenga la nuova entry
		assertFalse("L'array non dovrebbe contenere 'new_key'", Arrays.asList(array).contains(e)); // verifica che l'array non contenga la nuova entry
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#add(Object o)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code add(Object o)} è disabilitato per le viste di una mappa e lancia una
	 * {@code UnsupportedOperationException} ogni volta che viene invocato.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code add(Object o)} lanci correttamente una {@code UnsupportedOperationException}.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code add(Object o)} è stato chiamato con un generico parametro {@code null} e ha lanciato una
	 * {@code UnsupportedOperationException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code add(Object o)} dovrebbe lanciare una {@code UnsupportedOperationException}.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testAddOnPopulatedEntrySet() {
		entrySet.add(null); // lancia UnsupportedOperationException
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#remove(Object o)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code remove(Object o)} verifica se l'oggetto specificato è contenuto nella vista (e di conseguenza
	 * come entry della mappa). Se l'oggetto è presente, viene rimosso e il metodo restituisce {@code true}.
	 * Se l'oggetto non è presente, il metodo restituisce {@code false}. Lancia {@code NullPointerException} se l'oggetto
	 * passato è {@code null} o se la entry ha chiave o valore {@code null}. Lancia {@code ClassCastException} se
	 * l'oggetto non implementa {@code HMap.HEntry} e quindi non è una entry della mappa.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code remove(Object o)} restituisca {@code false} quando si prova a rimuovere una generica
	 * entry diversa da {@code null} e non presente nella vista, verifica che il metodo restituisca {@code false} anche
	 * quando si prova a rimuovere una entry con chiavi o valori di un tipo diverso da quelli presenti nella vista, come
	 * ad esempio {@code Double}. Verifica infine che il metodo restituisca {@code true} quando si rimuove una entry
	 * presente nella vista.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 * Si creano quattro entry da passare come parametro al metodo {@code remove(Object o)}:
	 * <ul>
	 *    <li>una generica entry con chiave e valore generici di tipo {@code String} non presente nella mappa,</li>
	 *    <li>una entry {@code 3.1415="valueX"} con chiave {@code Double}</li>
	 *    <li>una entry {@code "keyX"=3.1415} con valore {@code Double}</li>
	 *    <li>una entry {@code "key1"="value1"} presente nella mappa.</li>
	 * </ul>
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code remove(Object o)} è stato chiamato su una vista popolata e ha restituito {@code false}
	 * quando si è cercato di rimuovere una entry non presente, ha restituito {@code false} quando si è cercato
	 * di rimuovere una entry con chiave o valore di tipo {@code Double} e ha restituito {@code true} quando si
	 * è cercato di rimuovere una entry presente nella vista.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code remove(Object o)} dovrebbe restituire inizialmente per due volte {@code false}, poi {@code true},
	 * la mappa non dovrebbe contenere la entry rimossa.
	 */
	@Test
	public void testRemoveOnPopulatedEntrySet() {
		HEntry e1 = new SimpleHMapWithNulls.SimpleHEntry("a_random_key", "a_random_value");
		HEntry e2 = new SimpleHMapWithNulls.SimpleHEntry(3.1415, "valueX");
		HEntry e3 = new SimpleHMapWithNulls.SimpleHEntry("keyX", 3.1415);
		HEntry e4 = new SimpleHMapWithNulls.SimpleHEntry("key1", "value1");
		assertFalse("La vista non dovrebbe contenere la entry generica <\"a_random_key\"=\"a_random_value\">", entrySet.remove(e1));
		assertFalse("La vista non dovrebbe contenere la entry <3.1415=\"valueX\">", entrySet.remove(e2));
		assertFalse("La vista non dovrebbe contenere la entry <\"keyX\"=3.1415>", entrySet.remove(e3));
		assertTrue("La vista dovrebbe contenere la entry <\"key1\"=\"value1\">", entrySet.remove(e4));
		assertEquals("La vista dovrebbe rimanere con 3 elementi", 3, entrySet.size());
		assertEquals("La mappa associata alla vista dovrebbe rimanere con 3 elementi", 3, map.size());
		assertFalse("La mappa non dovrebbe contenere la entry <\"key1\"=\"value1\">", map.containsKey("key1"));
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#remove(Object o)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code remove(Object o)} verifica se l'oggetto specificato è contenuto nella vista (e di conseguenza
	 * come entry della mappa). Se l'oggetto è presente, viene rimosso e il metodo restituisce {@code true}. Se
	 * l'oggetto non è presente, il metodo restituisce {@code false}. Lancia {@code NullPointerException} se l'oggetto
	 * passato è {@code null} o se la entry ha chiave o valore {@code null}. Lancia {@code ClassCastException} se
	 * l'oggetto non implementa {@code HMap.HEntry} e quindi non è una entry della mappa.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code remove(Object o)} lanci una {@code NullPointerException} quando gli viene passato
	 * un oggetto {@code null}.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa vuota.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code remove(Object o)} è stato chiamato con un parametro {@code null} e ha lanciato una
	 * {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code remove(Object o)} dovrebbe lanciare una {@code NullPointerException}.
	 */
	@Test(expected = NullPointerException.class)
	public void testRemoveNullEntryOnPopulatedEntrySet() {
		entrySet.remove(null); // lancia NullPointerException
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#remove(Object o)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code remove(Object o)} verifica se l'oggetto specificato è contenuto nella vista (e di conseguenza
	 * come entry della mappa). Se l'oggetto è presente, viene rimosso e il metodo restituisce {@code true}. Se
	 * l'oggetto non è presente, il metodo restituisce {@code false}. Lancia {@code NullPointerException} se l'oggetto
	 * passato è {@code null} o se la entry ha chiave o valore {@code null}. Lancia {@code ClassCastException} se
	 * l'oggetto non implementa {@code HMap.HEntry} e quindi non è una entry della mappa.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code remove(Object o)} lanci {@code NullPointerException} quando gli viene passata
	 * una generica entry con chiave {@code null} (in questo caso con valore di tipo {@code String}, istanziata
	 * attraverso la classe {@code SimpleHMapWithNulls.SimpleHEntry})
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa vuota. Viene inoltre creata una nuova entry generica con chiave {@code null} (sfruttando
	 * l'implementazione {@code SimpleHMapWithNulls.SimpleHEntry}) da passare come parametro.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code remove(Object o)} è stato chiamato con una generica entry con chiave {@code null} e ha lanciato
	 * una {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code remove(Object o)} dovrebbe lanciare una {@code NullPointerException}.
	 */
	@Test(expected = NullPointerException.class)
	public void testRemoveNullKeyOnPopulatedEntrySet() {
		HEntry e = new SimpleHMapWithNulls.SimpleHEntry(null, "a_random_value");
		entrySet.remove(e);
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#remove(Object o)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code remove(Object o)} verifica se l'oggetto specificato è contenuto nella vista (e di conseguenza
	 * come entry della mappa). Se l'oggetto è presente, viene rimosso e il metodo restituisce {@code true}. Se
	 * l'oggetto non è presente, il metodo restituisce {@code false}. Lancia {@code NullPointerException} se l'oggetto
	 * passato è {@code null} o se la entry ha chiave o valore {@code null}. Lancia {@code ClassCastException} se
	 * l'oggetto non implementa {@code HMap.HEntry} e quindi non è una entry della mappa.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code remove(Object o)} lanci {@code NullPointerException} quando gli viene passata
	 * una generica entry con valore {@code null} (in questo caso con chiave di tipo {@code String}, istanziata
	 * attraverso la classe {@code SimpleHMapWithNulls.SimpleHEntry})
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa vuota. Viene inoltre creata una nuova entry generica con valore {@code null} (sfruttando
	 * l'implementazione {@code SimpleHMapWithNulls.SimpleHEntry}) da passare come parametro.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code remove(Object o)} è stato chiamato con una generica entry con valore {@code null} e ha lanciato
	 * una {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code remove(Object o)} dovrebbe lanciare una {@code NullPointerException}.
	 */
	@Test(expected = NullPointerException.class)
	public void testRemoveNullValueOnPopulatedEntrySet() {
		HEntry e = new SimpleHMapWithNulls.SimpleHEntry("a_random_key", null);
		entrySet.remove(e);
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#contains(Object o)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code remove(Object o)} verifica se l'oggetto specificato è contenuto nella vista (e di conseguenza
	 * come entry della mappa). Se l'oggetto è presente, viene rimosso e il metodo restituisce {@code true}. Se
	 * l'oggetto non è presente, il metodo restituisce {@code false}. Lancia {@code NullPointerException} se l'oggetto
	 * passato è {@code null} o se la entry ha chiave o valore {@code null}. Lancia {@code ClassCastException} se
	 * l'oggetto non implementa {@code HMap.HEntry} e quindi non è una entry della mappa.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code remove(Object o)} lanci {@code ClassCastException} quando gli viene passato
	 * un oggetto che non è una entry (non implementa {@code HMap.HEntry}) (in questo caso una stringa).
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa vuota. Viene inoltre creata una stringa che banalmente non implementa {@code HMap.HEntry}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code remove(Object o)} è stato chiamato con un oggetto che non è una entry e ha lanciato una
	 * {@code ClassCastException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code remove(Object o)} dovrebbe lanciare una {@code ClassCastException}.
	 */
	@Test(expected = ClassCastException.class)
	public void testRemoveNotEntryOnPopulatedEntrySet() {
		String notAnEntry = "This is not an Entry";
		entrySet.remove(notAnEntry); // lancia ClassCastException
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#containsAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code containsAll(HCollection c)} restituisce {@code true} se la vista contiene tutti gli elementi
	 * della collezione specificata, {@code false} altrimenti. Lancia {@code NullPointerException} se la collezione
	 * passata è {@code null} o se una entry della collezione ha chiave o valore {@code null}. Lancia, inoltre,
	 * {@code ClassCastException} se un oggetto della collezione non implementa {@code HMap.HEntry} e quindi non
	 * è una entry della mappa.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code containsAll(HCollection c)} funzioni correttamente quando è invocato su una vista
	 * popolata e gli viene passata una collezione vuota. Il metodo non dovrebbe lanciare eccezioni e dovrebbe
	 * restituire {@code false} in quanto la vista contiene tutti gli elementi della collezione vuota.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 * Viene creata una nuova mappa vuota per generare una collezione di elementi vuota da passare come parametro al
	 * metodo {@code containsAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code containsAll(HCollection c)} è stato chiamato con una collezione di elementi vuota e ha
	 * restituito {@code true}, siccome la vista contiene tutti gli elementi della collezione vuota.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code containsAll(HCollection c)} dovrebbe restituire {@code true}.
	 */
	@Test
	public void testContainsAllEmptyCollectionOnPopulatedEntrySet() {
		MapAdapter newEmptyMap = new MapAdapter();
		HCollection emptyCollection = newEmptyMap.values();
		assertTrue("La vista dovrebbe contenere tutti gli elementi della collezione vuota", entrySet.containsAll(emptyCollection));
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#containsAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code containsAll(HCollection c)} restituisce {@code true} se la vista contiene tutti gli elementi
	 * della collezione specificata, {@code false} altrimenti. Lancia {@code NullPointerException} se la collezione
	 * passata è {@code null}o se una entry della collezione ha chiave o valore {@code null}. Lancia, inoltre,
	 * {@code ClassCastException} se un oggetto della collezione non implementa {@code HMap.HEntry} e quindi non
	 * è una entry della mappa.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code containsAll(HCollection c)} lanci {@code NullPointerException} se gli viene
	 * passata una collezione {@code null}.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}. Viene creato un nuovo riferimento di tipo {@code HCollection} e impostato a
	 * {@code null} per rappresentare una collezione {@code null} da passare come parametro al metodo
	 * {@code containsAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code containsAll(HCollection c)} è stato chiamato con una collezione {@code null} e ha
	 * lanciato {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code containsAll(HCollection c)} dovrebbe lanciare {@code NullPointerException}.
	 */
	@Test(expected = NullPointerException.class)
	public void testContainsAllNullCollectionOnPopulatedEntrySet() {
		HCollection nullCollection = null;
		entrySet.containsAll(nullCollection); // lancia NullPointerException
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#containsAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code containsAll(HCollection c)} restituisce {@code true} se la vista contiene tutti gli elementi
	 * della collezione specificata, {@code false} altrimenti. Lancia {@code NullPointerException} se la collezione
	 * passata è {@code null} o se una entry della collezione ha chiave o valore {@code null}. Lancia, inoltre,
	 * {@code ClassCastException} se un oggetto della collezione non implementa {@code HMap.HEntry} e quindi non
	 * è una entry della mappa.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code containsAll(HCollection c)} restituisca {@code false} se gli viene passata una
	 * collezione contenente generici elementi non tutti appartenenti alla vista. Inoltre si verifica che il metodo
	 * restituisca {@code true} se gli viene passata un'altra collezione contiene solo elementi della vista.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 * Viene creata una nuova mappa con alcuni elementi appartenenti e non alla vista per generare una collezione da
	 * passare come parametro al metodo {@code containsAll(HCollection c)}. Inoltre viene creata una nuova collezione
	 * contenente solo elementi della vista.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code containsAll(HCollection c)} è stato chiamato con una collezione di elementi non tutti appartenenti
	 * alla vista e ha restituito {@code false}. Inoltre, è stato chiamato con una collezione di elementi tutti appartenenti
	 * alla vista e ha restituito {@code true}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code containsAll(HCollection c)} dovrebbe restituire {@code false}.
	 */
	@Test
	public void testContainsAllPopulatedCollectionOnPopulatedEntrySet() {
		MapAdapter populatedMap = new MapAdapter();
		populatedMap.put("key1", "value1"); // aggiunge una entry presente nella vista
		populatedMap.put("key3", "value3"); // aggiunge una entry non presente nella vista
		HCollection populatedCollection = populatedMap.entrySet();
		assertFalse("La vista non dovrebbe contenere tutti gli elementi della collezione", entrySet.containsAll(populatedCollection));
		populatedMap.remove("key3"); // rimuove l'entry non presente nella vista
		populatedMap.put("key2", 2); // aggiunge un'altra entry presente nella vista
		populatedCollection = populatedMap.entrySet();
		assertTrue("La vista dovrebbe contenere tutti gli elementi della collezione", entrySet.containsAll(populatedCollection));
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#containsAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code containsAll(HCollection c)} restituisce {@code true} se la vista contiene tutti gli elementi
	 * della collezione specificata, {@code false} altrimenti. Lancia {@code NullPointerException} se la collezione
	 * passata è {@code null} o se una entry della collezione ha chiave o valore {@code null}. Lancia, inoltre,
	 * {@code ClassCastException} se un oggetto della collezione non implementa {@code HMap.HEntry} e quindi non
	 * è una entry della mappa.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code containsAll(HCollection c)} funzioni correttamente quando è invocato su una vista
	 * senza elementi e gli viene passata una collezione con una entry con chiave {@code null} e valore di tipo {@code String}.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter} collegata
	 * a una mappa vuota. Viene creata una nuova mappa con una entry con chiave {@code null} e valore di tipo {@code String}
	 * per generare una collezione di entry non vuota da passare come parametro al metodo {@code containsAll(HCollection c)}.
	 * Per istanziare una mappa che supporta chiavi e valori nulli, si utilizza la classe {@code SimpleHMapWithNulls}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code containsAll(HCollection c)} è stato chiamato con una collezione di entry vuota e ha
	 * lanciato {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code containsAll(HCollection c)} dovrebbe lanciare {@code NullPointerException}.
	 */
	@Test(expected = NullPointerException.class)
	public void testContainsAllPopulatedCollectionWithNullKeyOnPopulatedEntrySet() {
		SimpleHMapWithNulls populatedMap = new SimpleHMapWithNulls();
		populatedMap.put(null, "value");
		HCollection populatedCollection = populatedMap.entrySet();
		entrySet.containsAll(populatedCollection); // lancia NullPointerException
	}
	
	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#containsAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code containsAll(HCollection c)} restituisce {@code true} se la vista contiene tutti gli elementi
	 * della collezione specificata, {@code false} altrimenti. Lancia {@code NullPointerException} se la collezione
	 * passata è {@code null} o se una entry della collezione ha chiave o valore {@code null}. Lancia, inoltre,
	 * {@code ClassCastException} se un oggetto della collezione non implementa {@code HMap.HEntry} e quindi non
	 * è una entry della mappa.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code containsAll(HCollection c)} funzioni correttamente quando è invocato su una vista
	 * senza elementi e gli viene passata una collezione con una entry con chiave di tipo {@code String} e valore {@code null}.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter} collegata
	 * a una mappa vuota. Viene creata una nuova mappa con una entry con chiave di tipo {@code String} e valore {@code null}
	 * per generare una collezione di entry non vuota da passare come parametro al metodo {@code containsAll(HCollection c)}.
	 * Per istanziare una mappa che supporta chiavi e valori nulli, si utilizza la classe {@code SimpleHMapWithNulls}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code containsAll(HCollection c)} è stato chiamato con una collezione di entry vuota e ha
	 * lanciato {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code containsAll(HCollection c)} dovrebbe lanciare {@code NullPointerException}.
	 */
	@Test(expected = NullPointerException.class)
	public void testContainsAllPopulatedCollectionWithNullValueOnPopulatedEntrySet() {
		SimpleHMapWithNulls populatedMap = new SimpleHMapWithNulls();
		populatedMap.put("key", null);
		HCollection populatedCollection = populatedMap.entrySet();
		entrySet.containsAll(populatedCollection); // lancia NullPointerException
	}
	
	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#containsAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code containsAll(HCollection c)} restituisce {@code true} se la vista contiene tutti gli elementi
	 * della collezione specificata, {@code false} altrimenti. Lancia {@code NullPointerException} se la collezione
	 * passata è {@code null} o se una entry della collezione ha chiave o valore {@code null}. Lancia, inoltre,
	 * {@code ClassCastException} se un oggetto della collezione non implementa {@code HMap.HEntry} e quindi non
	 * è una entry della mappa.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code containsAll(HCollection c)} funzioni correttamente quando è invocato su una vista
	 * senza elementi e gli viene passata una collezione con un oggetto che non è una entry (non implementa {@code HMap.HEntry}).
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter} collegata
	 * a una mappa vuota. Viene creata una nuova mappa con una entry generica (con chiave e valore di tipo {@code String})
	 * per generare una collezione con un oggetto che non è una entry (non implementa {@code HMap.HEntry}) (attraverso
	 * la vista {@code values()}) da passare come parametro al metodo {@code containsAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code containsAll(HCollection c)} è stato chiamato con una collezione di entry vuota e ha
	 * lanciato {@code ClassCastException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code containsAll(HCollection c)} dovrebbe lanciare {@code ClassCastException}.
	 */
	@Test(expected = ClassCastException.class)
	public void testContainsAllPopulatedCollectionWithNotEntryOnPopulatedEntrySet() {
		MapAdapter populatedMap = new MapAdapter();
		populatedMap.put("key", "value");
		HCollection populatedEntrySet = populatedMap.values();
		entrySet.containsAll(populatedEntrySet); // lancia ClassCastException
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#addAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code addAll(HCollection c)} è disabilitato per le viste di una mappa e lancia una
	 * {@code UnsupportedOperationException} ogni volta che viene invocato, indipendentemente dal parametro passato.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code addAll(HCollection c)} lanci correttamente una {@code UnsupportedOperationException}.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code addAll(HCollection c)} è stato chiamato con un generico parametro {@code null} e ha lanciato una
	 * {@code UnsupportedOperationException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code addAll(HCollection c)} dovrebbe lanciare una {@code UnsupportedOperationException}.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testAddAllOnPopulatedEntrySet() {
		entrySet.addAll(null); // lancia UnsupportedOperationException
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#removeAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code removeAll(HCollection c)} rimuove dalla vista tutti gli elementi che sono presenti nella
	 * collezione specificata. Restituisce {@code true} se la vista (e di conseguenza la mappa) è stata modificata,
	 * {@code false} altrimenti. Lancia {@code NullPointerException} se la collezione passata è {@code null}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code removeAll(HCollection c)} funzioni correttamente quando gli viene passata
	 * una collezione vuota su una vista popolata. Il metodo non dovrebbe lanciare eccezioni e dovrebbe
	 * restituire {@code false} in quanto non ci sono entry rimovibili dalla vista.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 * Viene creata una nuova mappa vuota per generare una collezione vuota da passare come parametro al metodo
	 * {@code removeAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code removeAll(HCollection c)} è stato chiamato con una collezione vuota e non ha lanciato
	 * eccezioni.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code removeAll(HCollection c)} dovrebbe restituire {@code false}, inoltre la vista (e di conseguenza
	 * la mappa) dovrebbe rimanere con 4 elementi.
	 */
	@Test
	public void testRemoveAllEmptyCollectionOnPopulatedEntrySet() {
		HCollection emptyCollection = new MapAdapter().values();
		assertFalse("removeAll dovrebbe restituire false se la collezione è vuota", entrySet.removeAll(emptyCollection));
		assertEquals("La vista dovrebbe rimanere con 4 elementi", 4, entrySet.size());
		assertEquals("La mappa associata alla vista dovrebbe rimanere con 4 elementi", 4, map.size());
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#removeAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code removeAll(HCollection c)} rimuove dalla vista tutti gli elementi che sono presenti nella
	 * collezione specificata. Restituisce {@code true} se la vista (e di conseguenza la mappa) è stata modificata,
	 * {@code false} altrimenti. Lancia {@code NullPointerException} se la collezione passata è {@code null}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code removeAll(HCollection c)} lanci {@code NullPointerException} se la collezione
	 * è {@code null}.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 * Viene creato un nuovo riferimento di tipo {@code HCollection} per generare una collezione {@code null} da
	 * passare come parametro al metodo {@code removeAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code removeAll(HCollection c)} è stato chiamato con una collezione {@code null} e ha lanciato una
	 * {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code removeAll(HCollection c)} dovrebbe lanciare una {@code NullPointerException}.
	 */
	@Test(expected = NullPointerException.class)
	public void testRemoveAllNullCollectionOnPopulatedEntrySet() {
		HCollection nullCollection = null;
		entrySet.removeAll(nullCollection); // lancia NullPointerException
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#removeAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code removeAll(HCollection c)} rimuove dalla vista tutti gli elementi che sono presenti nella
	 * collezione specificata. Restituisce {@code true} se la vista (e di conseguenza la mappa) è stata modificata,
	 * {@code false} altrimenti. Lancia {@code NullPointerException} se la collezione passata è {@code null}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code removeAll(HCollection c)} funzioni correttamente quando gli viene passata
	 * una collezione popolata su una vista popolata. Il metodo non dovrebbe lanciare eccezioni e dovrebbe
	 * restituire {@code true} per aver rimosso gli elementi in comune tra la collezione e la vista.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 * Viene creata una nuova mappa con alcuni elementi appartenenti e non alla vista per generare una collezione da
	 * passare come parametro al metodo {@code containsAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code removeAll(HCollection c)} è stato chiamato con una collezione popolata e ha rimosso gli
	 * elementi presenti sia nella collezione che nella vista, restituendo {@code true}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code removeAll(HCollection c)} dovrebbe restituire {@code true}, inoltre la vista (e di conseguenza
	 * la mappa) dovrebbe rimanere con 3 elementi, senza l'elemento rimosso.
	 */
	@Test
	public void testRemoveAllPopulatedCollectionOnPopulatedEntrySet() {
		// creazione della entry rimossa che servirà per il test
		HEntry e = new SimpleHMapWithNulls.SimpleHEntry("key1", "value1"); // entry presente nella vista

		// creazione della mappa e della collezione popolata
		MapAdapter populatedMap = new MapAdapter();
		populatedMap.put("key1", "value1"); // aggiunge una entry presente nella vista
		populatedMap.put("key3", "value3"); // aggiunge una entry non presente nella vista
		HCollection populatedCollection = populatedMap.entrySet();
		
		// invocazione del metodo removeAll
		assertTrue("removeAll dovrebbe restituire true se la collezione e la vista hanno elementi in comune", entrySet.removeAll(populatedCollection));
		
		// verifica dello stato della vista e della mappa dopo la rimozione
		assertEquals("La vista dovrebbe rimanere con 3 elementi", 3, entrySet.size());
		assertEquals("La mappa associata alla vista dovrebbe rimanere con 3 elementi", 3, map.size());
		assertFalse("La vista non dovrebbe contenere l'elemento <\"key1\"=\"value1\"", entrySet.contains(e));
		assertFalse("La mappa non dovrebbe contenere la chiave 'key1'", map.containsKey("key1"));
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#removeAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code removeAll(HCollection c)} rimuove dalla vista tutti gli elementi che sono presenti nella
	 * collezione specificata. Restituisce {@code true} se la vista (e di conseguenza la mappa) è stata modificata,
	 * {@code false} altrimenti. Lancia {@code NullPointerException} se la collezione passata è {@code null}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code removeAll(HCollection c)} funzioni correttamente quando è invocato su una vista
	 * senza elementi e gli viene passata una collezione con una entry con chiave {@code null} e valore di tipo {@code String}.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter} collegata
	 * a una mappa vuota. Viene creata una nuova mappa con una entry con chiave {@code null} e valore di tipo {@code String}
	 * per generare una collezione di entry non vuota da passare come parametro al metodo {@code removeAll(HCollection c)}.
	 * Per istanziare una mappa che supporta chiavi e valori nulli, si utilizza la classe {@code SimpleHMapWithNulls}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code removeAll(HCollection c)}. è stato chiamato con una collezione di entry vuota e ha
	 * lanciato {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code removeAll(HCollection c)} dovrebbe lanciare {@code NullPointerException}.
	 */
	@Ignore("Invalid test: does not throw NullPointerException")
	@Test(expected = NullPointerException.class)
	public void testRemoveAllPopulatedCollectionWithNullKeyOnPopulatedEntrySet() {
		SimpleHMapWithNulls populatedMap = new SimpleHMapWithNulls();
		populatedMap.put(null, "value");
		HCollection populatedCollection = populatedMap.entrySet();
		entrySet.removeAll(populatedCollection); // lancia NullPointerException
	}
	
	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#removeAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code removeAll(HCollection c)} rimuove dalla vista tutti gli elementi che sono presenti nella
	 * collezione specificata. Restituisce {@code true} se la vista (e di conseguenza la mappa) è stata modificata,
	 * {@code false} altrimenti. Lancia {@code NullPointerException} se la collezione passata è {@code null}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code removeAll(HCollection c)} funzioni correttamente quando è invocato su una vista
	 * senza elementi e gli viene passata una collezione con una entry con chiave di tipo {@code String} e valore {@code null}.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter} collegata
	 * a una mappa vuota. Viene creata una nuova mappa con una entry con chiave di tipo {@code String} e valore {@code null}
	 * per generare una collezione di entry non vuota da passare come parametro al metodo {@code removeAll(HCollection c)}.
	 * Per istanziare una mappa che supporta chiavi e valori nulli, si utilizza la classe {@code SimpleHMapWithNulls}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code removeAll(HCollection c)}. è stato chiamato con una collezione di entry vuota e ha
	 * lanciato {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code removeAll(HCollection c)} dovrebbe lanciare {@code NullPointerException}.
	 */
	@Ignore("Invalid test: does not throw NullPointerException")
	@Test(expected = NullPointerException.class)
	public void testRemoveAllPopulatedCollectionWithNullValueOnPopulatedEntrySet() {
		SimpleHMapWithNulls populatedMap = new SimpleHMapWithNulls();
		populatedMap.put("key", null);
		HCollection populatedCollection = populatedMap.entrySet();
		entrySet.removeAll(populatedCollection); // lancia NullPointerException
	}
	
	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#removeAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code removeAll(HCollection c)} rimuove dalla vista tutti gli elementi che sono presenti nella
	 * collezione specificata. Restituisce {@code true} se la vista (e di conseguenza la mappa) è stata modificata,
	 * {@code false} altrimenti. Lancia {@code NullPointerException} se la collezione passata è {@code null}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code removeAll(HCollection c)} funzioni correttamente quando è invocato su una vista
	 * senza elementi e gli viene passata una collezione con un oggetto che non è una entry (non implementa {@code HMap.HEntry}).
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter} collegata
	 * a una mappa vuota. Viene creata una nuova mappa con una entry generica (con chiave e valore di tipo {@code String})
	 * per generare una collezione con un oggetto che non è una entry (non implementa {@code HMap.HEntry}) (attraverso
	 * la vista {@code values()}) da passare come parametro al metodo {@code removeAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code containsAll(HCollection c)} è stato chiamato con una collezione di entry vuota e ha
	 * lanciato {@code ClassCastException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code removeAll(HCollection c)} dovrebbe lanciare {@code ClassCastException}.
	 */
	@Ignore("Invalid test: does not throw ClassCastException")
	@Test(expected = ClassCastException.class)
	public void testRemoveAllPopulatedCollectionWithNotEmptyOnPopulatedEntrySet() {
		MapAdapter newEmptyMap = new MapAdapter();
		newEmptyMap.put("key1", "value1");
		HCollection populatedCollection = newEmptyMap.values();
		entrySet.removeAll(populatedCollection); // lancia ClassCastException
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#retainAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code retainAll(HCollection c)} rimuove dalla vista tutti gli elementi che non sono presenti nella
	 * collezione specificata. Restituisce {@code true} se la vista (e di conseguenza la mappa) è stata modificata,
	 * {@code false} altrimenti. Lancia {@code NullPointerException} se la collezione passata è {@code null}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code retainAll(HCollection c)} funzioni correttamente quando gli viene passata
	 * una collezione vuota su una vista popolata. Il metodo non dovrebbe lanciare eccezioni e dovrebbe restituire
	 * {@code true} dopo aver rimosso tutti gli elementi della vista.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 * Viene creata una nuova mappa vuota per generare una collezione vuota da passare come parametro al metodo
	 * {@code retainAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code retainAll(HCollection c)} è stato chiamato con una collezione vuota e non ha lanciato
	 * eccezioni, restituendo {@code true} dopo aver rimosso tutti gli elementi della vista.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code removeAll(HCollection c)} dovrebbe restituire {@code true}, inoltre la vista (e di conseguenza
	 * la mappa) dovrebbe diventare vuota con 0 elementi.
	 */
	@Test
	public void testRetainAllEmptyCollectionOnPopulatedEntrySet() {
		HCollection emptyCollection = new MapAdapter().values();
		assertTrue("retainAll dovrebbe restituire true se la collezione è vuota", entrySet.retainAll(emptyCollection));
		assertTrue("La vista dovrebbe essere vuota dopo retainAll con collezione vuota", entrySet.isEmpty());
		assertEquals("La vista dovrebbe rimanere con 0 elementi", 0, entrySet.size());
		assertTrue("La mappa associata alla vista dovrebbe essere vuota dopo retainAll con collezione vuota", map.isEmpty());
		assertEquals("La mappa associata alla vista dovrebbe rimanere con 0 elementi", 0, map.size());
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#retainAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code retainAll(HCollection c)} rimuove dalla vista tutti gli elementi che non sono presenti nella
	 * collezione specificata. Restituisce {@code true} se la vista (e di conseguenza la mappa) è stata modificata,
	 * {@code false} altrimenti. Lancia {@code NullPointerException} se la collezione passata è {@code null}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code retainAll(HCollection c)} lanci {@code NullPointerException} se la collezione
	 * è {@code null}.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 * Viene creato un nuovo riferimento di tipo {@code HCollection} per generare una collezione {@code null} da
	 * passare come parametro al metodo {@code retainAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code retainAll(HCollection c)} è stato chiamato con una collezione {@code null} e ha lanciato una
	 * {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code retainAll(HCollection c)} dovrebbe lanciare una {@code NullPointerException}.
	 */
	@Test(expected = NullPointerException.class)
	public void testRetainAllNullCollectionOnPopulatedEntrySet() {
		HCollection nullCollection = null;
		entrySet.retainAll(nullCollection); // lancia NullPointerException
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#retainAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code retainAll(HCollection c)} rimuove dalla vista tutti gli elementi che non sono presenti nella
	 * collezione specificata. Restituisce {@code true} se la vista (e di conseguenza la mappa) è stata modificata,
	 * {@code false} altrimenti. Lancia {@code NullPointerException} se la collezione passata è {@code null}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code retainAll(HCollection c)} funzioni correttamente quando gli viene passata
	 * una collezione popolata su una vista popolata. Il metodo non dovrebbe lanciare eccezioni e dovrebbe
	 * restituire {@code false} in quanto non ci sono entry rimovibili dalla vista.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 * Viene creata una nuova mappa a cui vengono inserite due entry una appartenente alla vista e una no, per
	 * generare una collezione popolata da passare come parametro al metodo {@code retainAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code retainAll(HCollection c)} è stato chiamato con una collezione popolata e non ha lanciato
	 * eccezioni. Ha restituito {@code true} dopo aver rimosso tutte le entry presenti sulla vista, ma non presenti
	 * nella collezione.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code removeAll(HCollection c)} dovrebbe restituire {@code false}, inoltre la vista (e di conseguenza
	 * la mappa) dovrebbe rimanere con 1 elemento in comune tra la vista e la collezione.
	 */
	@Test
	public void testRetainAllPopulatedCollectionOnPopulatedEntrySet() {
		// creazione della entry rimossa che servirà per il test
		HEntry e = new SimpleHMapWithNulls.SimpleHEntry("key1", "value1"); // entry presente nella vista

		// creazione della mappa e della collezione popolata
		MapAdapter populatedMap = new MapAdapter();
		populatedMap.put("key1", "value1"); // aggiunge una entry presente nella vista
		populatedMap.put("key3", "value3"); // aggiunge una entry non presente nella vista
		HCollection populatedCollection = populatedMap.entrySet();
		
		// invocazione del metodo retainAll
		assertTrue("retainAll dovrebbe restituire true se la collezione e la vista hanno elementi in comune", entrySet.retainAll(populatedCollection));
		
		// verifica dello stato della vista e della mappa dopo la rimozione
		assertEquals("La vista dovrebbe rimanere con 1 elemento", 1, entrySet.size());
		assertEquals("La mappa associata alla vista dovrebbe rimanere con 1 elemento", 1, map.size());
		assertTrue("La vista dovrebbe contenere l'elemento <\"key1\"=\"value1\">", entrySet.contains(e));
		assertTrue("La mappa dovrebbe contenere la chiave 'key1'", map.containsKey("key1"));
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#retainAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code retainAll(HCollection c)} rimuove dalla vista tutti gli elementi che non sono presenti nella
	 * collezione specificata. Restituisce {@code true} se la vista (e di conseguenza la mappa) è stata modificata,
	 * {@code false} altrimenti. Lancia {@code NullPointerException} se la collezione passata è {@code null}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code retainAll(HCollection c)} funzioni correttamente quando è invocato su una vista
	 * senza elementi e gli viene passata una collezione con una entry con chiave {@code null} e valore di tipo {@code String}.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter} collegata
	 * a una mappa vuota. Viene creata una nuova mappa con una entry con chiave {@code null} e valore di tipo {@code String}
	 * per generare una collezione di entry non vuota da passare come parametro al metodo {@code retainAll(HCollection c)}.
	 * Per istanziare una mappa che supporta chiavi e valori nulli, si utilizza la classe {@code SimpleHMapWithNulls}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code retainAll(HCollection c)} è stato chiamato con una collezione di entry vuota e ha
	 * lanciato {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code retainAll(HCollection c)} dovrebbe lanciare {@code NullPointerException}.
	 */
	@Ignore("Invalid test: does not throw NullPointerException")
	@Test(expected = NullPointerException.class)
	public void testRetainAllPopulatedCollectionWithNullKeyOnPopulatedEntrySet() {
		SimpleHMapWithNulls populatedMap = new SimpleHMapWithNulls();
		populatedMap.put(null, "value");
		HCollection populatedCollection = populatedMap.entrySet();
		entrySet.retainAll(populatedCollection); // lancia NullPointerException
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#retainAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code retainAll(HCollection c)} rimuove dalla vista tutti gli elementi che non sono presenti nella
	 * collezione specificata. Restituisce {@code true} se la vista (e di conseguenza la mappa) è stata modificata,
	 * {@code false} altrimenti. Lancia {@code NullPointerException} se la collezione passata è {@code null}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code retainAll(HCollection c)} funzioni correttamente quando è invocato su una vista
	 * senza elementi e gli viene passata una collezione con una entry con chiave di tipo {@code String} e valore {@code null}.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter} collegata
	 * a una mappa vuota. Viene creata una nuova mappa con una entry con chiave di tipo {@code String} e valore {@code null}
	 * per generare una collezione di entry non vuota da passare come parametro al metodo {@code retainAll(HCollection c)}.
	 * Per istanziare una mappa che supporta chiavi e valori nulli, si utilizza la classe {@code SimpleHMapWithNulls}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code retainAll(HCollection c)} è stato chiamato con una collezione di entry vuota e ha
	 * lanciato {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code retainAll(HCollection c)} dovrebbe lanciare {@code NullPointerException}.
	 */
	@Ignore("Invalid test: does not throw NullPointerException")
	@Test(expected = NullPointerException.class)
	public void testRetainAllPopulatedCollectionWithNullValueOnPopulatedEntrySet() {
		SimpleHMapWithNulls populatedMap = new SimpleHMapWithNulls();
		populatedMap.put("key", null);
		HCollection populatedCollection = populatedMap.entrySet();
		entrySet.retainAll(populatedCollection); // lancia NullPointerException
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#retainAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code retainAll(HCollection c)} rimuove dalla vista tutti gli elementi che non sono presenti nella
	 * collezione specificata. Restituisce {@code true} se la vista (e di conseguenza la mappa) è stata modificata,
	 * {@code false} altrimenti. Lancia {@code NullPointerException} se la collezione passata è {@code null}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code retainAll(HCollection c)} funzioni correttamente quando è invocato su una vista
	 * senza elementi e gli viene passata una collezione con un oggetto che non è una entry (non implementa {@code HMap.HEntry}).
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter} collegata
	 * a una mappa vuota. Viene creata una nuova mappa con una entry generica (con chiave e valore di tipo {@code String})
	 * per generare una collezione con un oggetto che non è una entry (non implementa {@code HMap.HEntry}) (attraverso
	 * la vista {@code values()}) da passare come parametro al metodo {@code retainAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code retainAll(HCollection c)} è stato chiamato con una collezione di entry vuota e ha
	 * lanciato {@code ClassCastException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code retainAll(HCollection c)} dovrebbe lanciare {@code ClassCastException}.
	 */
	@Ignore("Invalid test: does not throw ClassCastException")
	@Test(expected = ClassCastException.class)
	public void testRetainAllPopulatedCollectionWithNotEntryOnPopulatedEntrySet() {
		MapAdapter newEmptyMap = new MapAdapter();
		newEmptyMap.put("key1", "value1");
		HCollection populatedCollection = newEmptyMap.values();
		entrySet.retainAll(populatedCollection); // lancia ClassCastException
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#clear()}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code clear()} rimuove tutte le entry dalla vista (e di conseguenza dalla mappa), rendendola vuota. Dopo
	 * la chiamata a questo metodo, la vista (e di conseguenza anche la mappa) dovrebbe essere vuota.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code clear()} invocato su una vista vuota mantenga la vista vuota, senza
	 * lanciare eccezioni.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code clear()} è stato chiamato su una vista vuota e non ha lanciato eccezioni.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code clear()} dovrebbe lasciare la vista vuota e con 0 elementi, la mappa associata dovrebbe a sua
	 * volta essere vuota e con 0 elementi.
	 */
	@Test
	public void testClearOnPopulatedEntrySet() {
		entrySet.clear();
		assertTrue("Clear dovrebbe rendere la vista vuota", entrySet.isEmpty());
		assertEquals("Clear dovrebbe lasciare 0 elementi nella vista", 0, entrySet.size());
		assertTrue("La mappa associata alla vista dovrebbe essere vuota", map.isEmpty());
		assertEquals("La mappa associata alla vista dovrebbe avere 0 elementi", 0, map.size());
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#hashCode()}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code hashCode()} restituisce un valore intero che rappresenta l'hash code della vista. Una vista vuota
	 * dovrebbe restituire un hash code di 0, mentre una vista con degli elementi dovrebbe restituire un hash code ottenuto
	 * dalla somma degli hash code degli elementi.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code hashCode()} restituisca un numero quando invocato su una vista popolata, uguale a
	 * quello di una nuova vista con gli stessi elementi della vista su cui è invocato il metodo.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 * Viene creata una nuova vista con gli stessi elementi di quella instanziata dal {@code setUp()} per verificare che
	 * l'hash code di due mappe con le stesse entry sia lo stesso.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code hashCode()} è stato chiamato su una vista popolata e ha restituito un valore uguale a
	 * quello della nuova vista con le stesse entry di quella instanziata dal {@code setUp()}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code hashCode()} dovrebbe restituire un numero uguale a l'hash code della nuova vista con le stesse entry.
	 */
	@Test
	public void testHashOnEmptyMap() {
		MapAdapter newMap = new MapAdapter();
		newMap.put("key1", "value1");
		newMap.put("key2", 2);
		newMap.put(3, "value3");
		newMap.put(4, 4);
		assertTrue("L'hash code di due mappe con le stesse entry dovrebbe essere lo stesso", entrySet.hashCode() == newMap.entrySet().hashCode());
		map.remove(3);
		map.put(4,"value4");
		newMap.remove(3);
		newMap.put(4, "value4");
		assertTrue("L'hash code di due mappe con le stesse entry dovrebbe essere lo stesso", entrySet.hashCode() == newMap.entrySet().hashCode());
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#equals(Object o)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code equals(Object o)} verifica se l'oggetto passato come parametro è uguale alla vista {@code entrySet}
	 * corrente. Due viste {@code entrySet} sono considerate uguali se sono entrambe delle istanze della classe
	 * {@code EntrySetAdapter} e contengono gli stessi elementi (stessi oggetti chiave). Restituisce {@code false} se
	 * il parametro è {@code null}, se il parametro non è una {@code EntrySetAdapter} o se contengono elementi diversi.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code equals(Object o)} restituisca {@code false} quando gli viene passato {@code null}.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code equals(Object o)} è stato chiamato con un parametro {@code null} e ha restituito {@code false}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code equals(Object o)} dovrebbe restituire {@code false}.
	 */
	@Test
	public void testEqualsNullOnPopulatedEntrySet() {
		assertFalse("equals dovrebbe restituire false se gli viene passato null", entrySet.equals(null));
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#equals(Object o)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code equals(Object o)} verifica se l'oggetto passato come parametro è uguale alla vista {@code entrySet}
	 * corrente. Due viste {@code entrySet} sono considerate uguali se sono entrambe delle istanze della classe
	 * {@code EntrySetAdapter} e contengono gli stessi elementi (stessi oggetti entry). Restituisce {@code false} se
	 * il parametro è {@code null}, se il parametro non è una {@code EntrySetAdapter} o se contengono elementi diversi.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code equals(Object o)} restituisca {@code false} quando gli viene passato un oggetto
	 * che non è una istanza di {@code EntrySetAdapter}.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 * Viene creato un nuovo oggetto di tipo {@code String} (non {@code EntrySetAdapter}) da passare come parametro
	 * al metodo {@code equals(Object o)}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code equals(Object o)} è stato chiamato con un parametro non {@code EntrySetAdapter} e ha restituito
	 * {@code false}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code equals(Object o)} dovrebbe restituire {@code false}.
	 */
	@Test
	public void testEqualsNotEntrySetOnPopulatedEntrySet() {
		String notAEntrySet = "This is not a EntrySet";
		assertFalse("equals dovrebbe restituire false se gli viene passato un oggetto che non è una EntrySet", entrySet.equals(notAEntrySet));
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#equals(Object o)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code equals(Object o)} verifica se l'oggetto passato come parametro è uguale alla vista {@code entrySet}
	 * corrente. Due viste {@code entrySet} sono considerate uguali se sono entrambe delle istanze della classe
	 * {@code EntrySetAdapter} e contengono gli stessi elementi (stessi oggetti entry). Restituisce {@code false} se
	 * il parametro è {@code null}, se il parametro non è una {@code EntrySetAdapter} o se contengono elementi diversi.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code equals(Object o)} restituisca {@code false} quando gli viene passata un'altra
	 * view (di tipo {@code ValuesCollectionAdapter} o {@code EntrySetAdapter}) collegata a una mappa vuota.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 * Vengono creati due nuovi oggetti di tipo {@code KeySetAdapter} e {@code ValuesCollectionAdapter} da passare
	 * come parametro al metodo {@code equals(Object o)}, provenienti da una nuova mappa vuota.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code equals(Object o)} è stato chiamato con un parametro non {@code EntrySetAdapter} e ha restituito
	 * {@code false}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code equals(Object o)} dovrebbe restituire {@code false}.
	 */
	@Test
	public void testEqualsOtherViewsOnPopulatedEntrySet() {
		HSet keySetView = new MapAdapter().keySet();
		HCollection valuesView = new MapAdapter().values();
		assertFalse("equals dovrebbe restituire false se gli viene passato un oggetto che non è una EntrySet", entrySet.equals(keySetView));
		assertFalse("equals dovrebbe restituire false se gli viene passato un oggetto che non è una EntrySet", entrySet.equals(valuesView));
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#equals(Object o)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code equals(Object o)} verifica se l'oggetto passato come parametro è uguale alla vista {@code entrySet}
	 * corrente. Due viste {@code entrySet} sono considerate uguali se sono entrambe delle istanze della classe
	 * {@code EntrySetAdapter} e contengono gli stessi elementi (stessi oggetti entry). Restituisce {@code false} se
	 * il parametro è {@code null}, se il parametro non è una {@code EntrySetAdapter} o se contengono elementi diversi.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code equals(Object o)} restituisca {@code false} quando gli viene passata un'altra
	 * istanza di {@code EntrySetAdapter} proveniente da una nuova mappa vuota.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 * Viene creata una nuova istanza di {@code EntrySetAdapter} collegata a una mappa vuota da passare come parametro
	 * al metodo {@code equals(Object o)}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code equals(Object o)} è stato chiamato con una nuova istanza di {@code EntrySetAdapter} collegata a una
	 * mappa vuota.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code equals(Object o)} dovrebbe restituire {@code false}.
	 */
	@Test
	public void testEqualsNewEmptyEntrySetOnPopulatedEntrySet() {
		HSet newEmptyEntrySet = new MapAdapter().entrySet();
		assertFalse("equals dovrebbe restituire false se gli viene passato un'altro entrySet vuoto", entrySet.equals(newEmptyEntrySet));
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#equals(Object o)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code equals(Object o)} verifica se l'oggetto passato come parametro è uguale alla vista {@code entrySet}
	 * corrente. Due viste {@code entrySet} sono considerate uguali se sono entrambe delle istanze della classe
	 * {@code EntrySetAdapter} e contengono gli stessi elementi (stessi oggetti entry). Restituisce {@code false} se
	 * il parametro è {@code null}, se il parametro non è una {@code EntrySetAdapter} o se contengono elementi diversi.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code equals(Object o)} restituisca {@code true} quando gli viene passata un'altra
	 * istanza di {@code EntrySetAdapter} proveniente da una nuova mappa con le stesse entry della mappa da cui proviene
	 * la vista su cui è invocato il metodo.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 * Viene creata una nuova istanza di {@code EntrySetAdapter} collegata a una mappa popolata allo stesso modo di quella sopra
	 * da passare come parametro al metodo {@code equals(Object o)}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code equals(Object o)} è stato chiamato con una nuova istanza di {@code EntrySetAdapter} collegata
	 * ad una mappa popolata con le stesse entry e ha restituito {@code true}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code equals(Object o)} dovrebbe restituire {@code true}.
	 */
	@Test
	public void testEqualsSameElementsSetOnPopulatedEntrySet() {
		MapAdapter populatedMap = new MapAdapter();
		populatedMap.put("key1", "value1");
		populatedMap.put("key2", 2);
		populatedMap.put(3, "value3");
		populatedMap.put(4, 4);
		HSet populatedEntrySet = populatedMap.entrySet();
		assertTrue("equals dovrebbe restituire true se gli viene passata una entrySet con gli stessi elementi", entrySet.equals(populatedEntrySet));
	}
	
	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#equals(Object o)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code equals(Object o)} verifica se l'oggetto passato come parametro è uguale alla vista {@code entrySet}
	 * corrente. Due viste {@code entrySet} sono considerate uguali se sono entrambe delle istanze della classe
	 * {@code EntrySetAdapter} e contengono gli stessi elementi (stessi oggetti entry). Restituisce {@code false} se
	 * il parametro è {@code null}, se il parametro non è una {@code EntrySetAdapter} o se contengono elementi diversi.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code equals(Object o)} restituisca {@code false} quando gli viene passata una nuova vista
	 * contenente un numero di elementi diverso o con lo stesso numero di elementi ma con elementi diversi rispetto alla vista
	 * su cui è stato invocato il metodo.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 * Viene creata una nuova vista collegata a una mappa popolata prima con un elemento in meno, poi con lo stesso numero
	 * di elementi, ma con elementi diversi.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code equals(Object o)} è stato chiamato con viste diverse da quella su cui è stato invocato il metodo,
	 * e ha restituito {@code false} in tutti i casi.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code equals(Object o)} dovrebbe restituire {@code false} in tutti i casi descritti.
	 */
	@Test
	public void testEqualDifferentElementsSelfOnPopulatedEntrySet() {
		// numero di elementi diverso
		MapAdapter populatedMap = new MapAdapter();
		populatedMap.put("key1", "value1");
		populatedMap.put("key2", 2);
		populatedMap.put(3, "value3");
		assertFalse("equals dovrebbe restituire false se gli viene passata è una vista con un numero di elementi diverso", entrySet.equals(populatedMap.entrySet()));
		
		// stesso numero di elementi ma con elementi diversi
		populatedMap.remove(4);
		populatedMap.put("5", "sqrt{25}");
		assertFalse("equals dovrebbe restituire false se gli viene passata è una vista con lo stesso numero di elementi ma elementi diversi", entrySet.equals(populatedMap.entrySet()));
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#equals(Object o)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code equals(Object o)} verifica se l'oggetto passato come parametro è uguale alla vista {@code entrySet}
	 * corrente. Due viste {@code entrySet} sono considerate uguali se sono entrambe delle istanze della classe
	 * {@code EntrySetAdapter} e contengono gli stessi elementi (stessi oggetti entry). Restituisce {@code false} se
	 * il parametro è {@code null}, se il parametro non è una {@code EntrySetAdapter} o se contengono elementi diversi.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code equals(Object o)} restituisca {@code true} quando gli viene passata la stessa
	 * istanza di {@code EntrySetAdapter} su cui è stato invocato il metodo.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code equals(Object o)} è stato chiamato con la stessa istanza di {@code EntrySetAdapter} su cui è stato
	 * invocato il metodo.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code equals(Object o)} dovrebbe restituire {@code true}.
	 */
	@Test
	public void testEqualsSelfOnPopulatedEntrySet() {
		assertTrue("equals dovrebbe restituire true se gli viene passato se stesso", entrySet.equals(entrySet));
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#toString()}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code toString()} restituisce una rappresentazione in stringa della vista. Per una vista popolata,
	 * dovrebbe restituire la stringa {@code [elem1, elem2, ...]}. L'ordine degli elementi non è garantito in quanto
	 * dipende dalla mappa a cui si appoggia la vista.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code toString()} restituisca la rappresentazione corretta per una vista popolata.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}. Si
	 * istanzia una stringa per memorizzare la rappresentazione attesa della mappa.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code toString()} è stato chiamato su una vista vuota e ha restituito la stringa corretta.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code toString()} dovrebbe restituire la stringa attesa.
	 */
	@Test
	public void testToStringOnEmptyMap() {
		// creo un StringBuffer per costruire la stringa attesa
		StringBuffer sb = new StringBuffer("[");

		// inserisco gli elementi della mappa nello StringBuffer attraverso l'iteratore della entrySet
		HIterator i = entrySet.iterator();
		while (i.hasNext()) {
			sb.append(i.next().toString());
			if (i.hasNext())
				sb.append(", ");
		}
		sb.append("]");

		assertEquals("La rappresentazione in stringa di una mappa popolata non corrisponde con quella attesa", sb.toString(), entrySet.toString());
	}


/* ================================================================================================================= *\
|* =============================== Test dei metodi dell'iteratore della vista entrySet =============================== *|
\* ================================================================================================================= */

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetIterator#hasNext()}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code hasNext()} verifica se ci sono elementi successivi nell'iteratore. Per un iteratore
	 * associato a una vista con 4 elementi, dovrebbe restituire {@code true} per le prime 4 invocazioni e
	 * {@code false} per la quinta.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code hasNext()} restituisca {@code true} per le prime 4 invocazioni su un iteratore
	 * associato a una vista con 4 elementi e {@code false} per la quinta. (le invocazioni successive sono alternate
	 * tra {@code hasNext()} e {@code next()}).
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata e dell'iteratore {@code EntrySetIterator} associato alla vista.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code hasNext()} è stato chiamato su un iteratore associato a una vista popolata e ha restituito
	 * {@code true} le prime 4 volte, {@code false} per la quinta.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code hasNext()} dovrebbe restituire {@code true} le prime 4 volte, {@code false} per la quinta.
	 */
	@Test
	public void testHasNextOnPopulatedEntrySetIterator() {
		for(int i = 0; i < 4; i++) {
			assertTrue("hasNext dovrebbe restituire true per un iteratore associato a una vista con 4 elementi", entrySetIterator.hasNext());
			entrySetIterator.next(); // chiama next() per avanzare l'iteratore
		}
		assertFalse("hasNext dovrebbe restituire false quando non ci sono più elementi da visitare", entrySetIterator.hasNext());
	}
	
	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetIterator#next()}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code next()} restituisce l'elemento successivo nell'iteratore. Se non ci sono più elementi da estrarre,
	 * dovrebbe lanciare un'eccezione {@code java.util.NoSuchElementException}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code next()} restituisca tutti gli elementi della vista associata all'iteratore,
	 * chiamando il metodo 4 volte consecutivamente.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata e dell'iteratore {@code EntrySetIterator} associato alla vista.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code next()} è stato chiamato su un iteratore 4 volte consecutive e ha restituito tutti gli elementi
	 * della vista associata all'iteratore.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code next()} dovrebbe restituire tutti gli elementi della vista associata all'iteratore.
	 */
	@Test
	public void testNextOnPopulatedEntrySetIterator() {
		Object[] array = entrySet.toArray();
		for (int i = 0; i < 4; i++) {
			assertTrue("next() dovrebbe restituire un elemento della vista associata all'iteratore", entrySetIterator.next().equals(array[i]));
		}
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetIterator#next()}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code next()} restituisce l'elemento successivo nell'iteratore. Se non ci sono più elementi da estrarre,
	 * dovrebbe lanciare un'eccezione {@code java.util.NoSuchElementException}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code next()} generi un'eccezione {@code java.util.NoSuchElementException} quando si
	 * chiama il metodo su un iteratore senza più elementi da estrarre.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata e dell'iteratore {@code EntrySetIterator} associato alla vista.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code next()} è stato chiamato su un iteratore associato a una vista popolata e ha lanciato
	 * un'eccezione {@code java.util.NoSuchElementException} alla sua 5 invocazione.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code next()} dovrebbe lanciare {@code java.util.NoSuchElementException}.
	 */
	@Test(expected = java.util.NoSuchElementException.class)
	public void testNextExceptionOnPopulatedEntrySetIterator() {
		for (int i = 0; i < 4; i++) {
			entrySetIterator.next(); // chiama next() per avanzare l'iteratore fino all'ultimo elemento
		}
		assertFalse("hasNext dovrebbe restituire false quando non ci sono più elementi da visitare", entrySetIterator.hasNext());
		entrySetIterator.next(); // lancia java.util.NoSuchElementException
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetIterator#remove()}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code remove()} rimuove l'elemento appena restituito dal metodo {@code next()} dell'iteratore. 
	 * Lancia {@code myAdapter.IllegalStateException} se il metodo {@code next()} non è stato chiamato con successo
	 * o se il metodo {@code remove()} è stato già chiamato dopo l'ultima chiamata a {@code next()}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code remove()} generi un'eccezione {@code myAdapter.IllegalStateException} se il
	 * metodo {@code next()} non è ancora stato chiamato.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata e dell'iteratore {@code EntrySetIterator} associato alla vista.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code remove()} è stato chiamato su un iteratore senza prima aver chiamato {@code next()} e ha
	 * lanciato {@code myAdapter.IllegalStateException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code remove()} dovrebbe lanciare {@code myAdapter.IllegalStateException}.
	 */
	@Test(expected = myAdapter.IllegalStateException.class)
	public void testRemoveBeforeNextOnPopulatedEntrySetIterator() {
		entrySetIterator.remove(); // lancia myAdapter.IllegalStateException
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetIterator#remove()}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code remove()} rimuove l'elemento appena restituito dal metodo {@code next()} dell'iteratore. 
	 * Lancia {@code myAdapter.IllegalStateException} se il metodo {@code next()} non è stato chiamato con successo
	 * o se il metodo {@code remove()} è stato già chiamato dopo l'ultima chiamata a {@code next()}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code remove()} generi un'eccezione {@code myAdapter.IllegalStateException} se il
	 * metodo {@code remove()} è chiamato due volte consecutivamente senza una chiamata a {@code next()} tra le due.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata e dell'iteratore {@code EntrySetIterator} associato alla vista.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code remove()} è stato chiamato su un iteratore due volte consecutive dopo una chiamata a {@code next()}
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code remove()} dovrebbe lanciare {@code myAdapter.IllegalStateException}.
	 */
	@Test(expected = myAdapter.IllegalStateException.class)
	public void testRemoveRepeatedOnPopulatedEntrySetIterator() {
		entrySetIterator.next(); // chiama next() per avanzare l'iteratore
		entrySetIterator.remove(); // rimuove l'elemento corrente
		entrySetIterator.remove(); // lancia myAdapter.IllegalStateException
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetIterator#remove()}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code remove()} rimuove l'elemento appena restituito dal metodo {@code next()} dell'iteratore. 
	 * Lancia {@code myAdapter.IllegalStateException} se il metodo {@code next()} non è stato chiamato con successo
	 * o se il metodo {@code remove()} è stato già chiamato dopo l'ultima chiamata a {@code next()}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code remove()} rimuova correttamente il primo elemento della vista associata senza
	 * alterare l'avanzamento dell'iteratore. Per fare ciò, si chiama il metodo {@code next()} 4 volte, rimuovendo
	 * il primo elemento tra la seconda e la terza chiamata a {@code next()}.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata e dell'iteratore {@code EntrySetIterator} associato alla vista.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * La vista e la mappa associata sono state modificate rimuovendo il primo elemento della vista associata.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * La vista e la relativa mappa associata dovrebbero essere modificate rimuovendo il primo elemento della vista associata,
	 * inolte l'iteratore dovrebbe essere avanzato correttamente per tutti gli altri elementi.
	 */
	@Test
	public void testRemoveFirstOnPopulatedEntrySetIterator() {
		Object[] array = entrySet.toArray();
		assertTrue("next() dovrebbe restituire il primo elemento della vista associata all'iteratore", entrySetIterator.next().equals(array[0]));
		entrySetIterator.remove(); // rimuove il primo elemento della vista associata all'iteratore
		assertTrue("next() dovrebbe restituire il secondo elemento della vista associata all'iteratore", entrySetIterator.next().equals(array[1]));
		assertTrue("next() dovrebbe restituire il terzo elemento della vista associata all'iteratore", entrySetIterator.next().equals(array[2]));
		assertTrue("next() dovrebbe restituire il quarto elemento della vista associata all'iteratore", entrySetIterator.next().equals(array[3]));
		assertFalse("hasNext() dovrebbe restituire false quando non ci sono più elementi da visitare", entrySetIterator.hasNext());

		// verifico che il primo elemento sia stato rimosso dalla vista associata all'iteratore
		assertFalse("La vista associata all'iteratore non dovrebbe contenere il primo elemento rimosso", entrySet.contains(array[0]));
		assertFalse("La mappa associata all'iteratore non dovrebbe contenere il primo elemento rimosso", map.containsKey(array[0]));

		// verifico che la vista associata all'iteratore e la mappa associata abbiano ora 3 elementi
		assertEquals("La vista associata all'iteratore dovrebbe avere 3 elementi dopo la rimozione del primo elemento", 3, entrySet.size());
		assertEquals("La mappa associata all'iteratore dovrebbe avere 3 elementi dopo la rimozione del primo elemento", 3, map.size());
	}
	
	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetIterator#remove()}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code remove()} rimuove l'elemento appena restituito dal metodo {@code next()} dell'iteratore. 
	 * Lancia {@code myAdapter.IllegalStateException} se il metodo {@code next()} non è stato chiamato con successo
	 * o se il metodo {@code remove()} è stato già chiamato dopo l'ultima chiamata a {@code next()}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code remove()} rimuova correttamente il secondo elemento della vista associata senza
	 * alterare l'avanzamento dell'iteratore. Per fare ciò, si chiama il metodo {@code next()} 4 volte, rimuovendo
	 * il secondo elemento tra la seconda e la terza chiamata a {@code next()}.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata e dell'iteratore {@code EntrySetIterator} associato alla vista.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * La vista e la mappa associata sono state modificate rimuovendo il secondo elemento della vista associata.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * La vista e la relativa mappa associata dovrebbero essere modificate rimuovendo il secondo elemento della vista associata,
	 * inolte l'iteratore dovrebbe essere avanzato correttamente per tutti gli altri elementi.
	 */
	@Test
	public void testRemoveSecondOnPopulatedEntrySetIterator() {
		Object[] array = entrySet.toArray();
		assertTrue("next() dovrebbe restituire il primo elemento della vista associata all'iteratore", entrySetIterator.next().equals(array[0]));
		assertTrue("next() dovrebbe restituire il secondo elemento della vista associata all'iteratore", entrySetIterator.next().equals(array[1]));
		entrySetIterator.remove(); // rimuove il secondo elemento della vista associata all'iteratore
		assertTrue("next() dovrebbe restituire il terzo elemento della vista associata all'iteratore", entrySetIterator.next().equals(array[2]));
		assertTrue("next() dovrebbe restituire il quarto elemento della vista associata all'iteratore", entrySetIterator.next().equals(array[3]));
		assertFalse("hasNext() dovrebbe restituire false quando non ci sono più elementi da visitare", entrySetIterator.hasNext());

		// verifico che il secondo elemento sia stato rimosso dalla vista associata all'iteratore
		assertFalse("La vista associata all'iteratore non dovrebbe contenere il secondo elemento rimosso", entrySet.contains(array[1]));
		assertFalse("La mappa associata all'iteratore non dovrebbe contenere il secondo elemento rimosso", map.containsKey(array[1]));

		// verifico che la vista associata all'iteratore e la mappa associata abbiano ora 3 elementi
		assertEquals("La vista associata all'iteratore dovrebbe avere 3 elementi dopo la rimozione del secondo elemento", 3, entrySet.size());
		assertEquals("La mappa associata all'iteratore dovrebbe avere 3 elementi dopo la rimozione del secondo elemento", 3, map.size());
	}
	
	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetIterator#remove()}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code remove()} rimuove l'elemento appena restituito dal metodo {@code next()} dell'iteratore. 
	 * Lancia {@code myAdapter.IllegalStateException} se il metodo {@code next()} non è stato chiamato con successo
	 * o se il metodo {@code remove()} è stato già chiamato dopo l'ultima chiamata a {@code next()}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code remove()} rimuova correttamente il terzo elemento della vista associata senza
	 * alterare l'avanzamento dell'iteratore. Per fare ciò, si chiama il metodo {@code next()} 4 volte, rimuovendo
	 * il terzo elemento tra la seconda e la terza chiamata a {@code next()}.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata e dell'iteratore {@code EntrySetIterator} associato alla vista.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * La vista e la mappa associata sono state modificate rimuovendo il terzo elemento della vista associata.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * La vista e la relativa mappa associata dovrebbero essere modificate rimuovendo il terzo elemento della vista associata,
	 * inolte l'iteratore dovrebbe essere avanzato correttamente per tutti gli altri elementi.
	 */
	@Test
	public void testRemoveThirdOnPopulatedEntrySetIterator() {
		Object[] array = entrySet.toArray();
		assertTrue("next() dovrebbe restituire il primo elemento della vista associata all'iteratore", entrySetIterator.next().equals(array[0]));
		assertTrue("next() dovrebbe restituire il secondo elemento della vista associata all'iteratore", entrySetIterator.next().equals(array[1]));
		assertTrue("next() dovrebbe restituire il terzo elemento della vista associata all'iteratore", entrySetIterator.next().equals(array[2]));
		entrySetIterator.remove(); // rimuove il terzo elemento della vista associata all'iteratore
		assertTrue("next() dovrebbe restituire il quarto elemento della vista associata all'iteratore", entrySetIterator.next().equals(array[3]));
		assertFalse("hasNext() dovrebbe restituire false quando non ci sono più elementi da visitare", entrySetIterator.hasNext());

		// verifico che il terzo elemento sia stato rimosso dalla vista associata all'iteratore
		assertFalse("La vista associata all'iteratore non dovrebbe contenere il terzo elemento rimosso", entrySet.contains(array[2]));
		assertFalse("La mappa associata all'iteratore non dovrebbe contenere il terzo elemento rimosso", map.containsKey(array[2]));

		// verifico che la vista associata all'iteratore e la mappa associata abbiano ora 3 elementi
		assertEquals("La vista associata all'iteratore dovrebbe avere 3 elementi dopo la rimozione del terzo elemento", 3, entrySet.size());
		assertEquals("La mappa associata all'iteratore dovrebbe avere 3 elementi dopo la rimozione del terzo elemento", 3, map.size());
	}
	
	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetIterator#remove()}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code remove()} rimuove l'elemento appena restituito dal metodo {@code next()} dell'iteratore. 
	 * Lancia {@code myAdapter.IllegalStateException} se il metodo {@code next()} non è stato chiamato con successo
	 * o se il metodo {@code remove()} è stato già chiamato dopo l'ultima chiamata a {@code next()}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code remove()} rimuova correttamente il quarto elemento della vista associata senza
	 * alterare l'avanzamento dell'iteratore. Per fare ciò, si chiama il metodo {@code next()} 4 volte, rimuovendo
	 * il quarto elemento tra la seconda e la terza chiamata a {@code next()}.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa popolata e dell'iteratore {@code EntrySetIterator} associato alla vista.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * La vista e la mappa associata sono state modificate rimuovendo il quarto elemento della vista associata.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * La vista e la relativa mappa associata dovrebbero essere modificate rimuovendo il quarto elemento della vista associata,
	 * inolte l'iteratore dovrebbe essere avanzato correttamente per tutti gli altri elementi.
	 */
	@Test
	public void testRemoveFourthOnPopulatedEntrySetIterator() {
		Object[] array = entrySet.toArray();
		assertTrue("next() dovrebbe restituire il primo elemento della vista associata all'iteratore", entrySetIterator.next().equals(array[0]));
		assertTrue("next() dovrebbe restituire il secondo elemento della vista associata all'iteratore", entrySetIterator.next().equals(array[1]));
		assertTrue("next() dovrebbe restituire il terzo elemento della vista associata all'iteratore", entrySetIterator.next().equals(array[2]));
		assertTrue("next() dovrebbe restituire il quarto elemento della vista associata all'iteratore", entrySetIterator.next().equals(array[3]));
		entrySetIterator.remove(); // rimuove il quarto elemento della vista associata all'iteratore
		assertFalse("hasNext() dovrebbe restituire false quando non ci sono più elementi da visitare", entrySetIterator.hasNext());

		// verifico che il quarto elemento sia stato rimosso dalla vista associata all'iteratore
		assertFalse("La vista associata all'iteratore non dovrebbe contenere il quarto elemento rimosso", entrySet.contains(array[3]));
		assertFalse("La mappa associata all'iteratore non dovrebbe contenere il quarto elemento rimosso", map.containsKey(array[3]));

		// verifico che la vista associata all'iteratore e la mappa associata abbiano ora 3 elementi
		assertEquals("La vista associata all'iteratore dovrebbe avere 3 elementi dopo la rimozione del quarto elemento", 3, entrySet.size());
		assertEquals("La mappa associata all'iteratore dovrebbe avere 3 elementi dopo la rimozione del quarto elemento", 3, map.size());
	}
}
