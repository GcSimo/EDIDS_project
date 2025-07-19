// Giacomo Simonetto - 2109923 - Secondo appello 2025 di Elementi di Ingegneria del Software

package myTest;

// importazione componenti del pacchetto myAdapter da testare
import myAdapter.HCollection;
import myAdapter.HSet;
import myAdapter.HIterator;
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
 * quando proviene da una mappa vuota e non contiene elementi. Sono testati tutti i metodi implementati dalla vista,
 * assicurando che non lancino eccezioni inattese e restituiscano risultati corretti. Sono inoltre testati i metodi
 * dell'iteratore associato alla vista, per garantire che funzionino correttamente anche su una vista vuota.
 * 
 * <p>
 * <b>Test Case Design:</b>
 * La motivazione di questa suite è garantire che la classe {@link myAdapter.MapAdapter.EntrySetAdapter} e il relativo
 * iteratore {@link myAdapter.MapAdapter.EntrySetIterator} gestiscano correttamente i casi limite e le operazioni collegate
 * ad una mappa vuota, prima di procedere con test su mappe non vuote.
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
 * {@code HSet} ottenuto da una mappa vuota, tramite il metodo {@code entrySet()} della mappa, mentre quelli dell'iteratore
 * sono eseguiti su un oggetto {@code HIterator} ottenuto dalla stessa mappa vuota, tramite il metodo
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
public class TestEmptyEntrySet {
	// variabili di istanza per la mappa, la vista e l'iteratoreda testare
	private MapAdapter map;
	private HSet entrySet;
	private HIterator entrySetIterator;

/* ================================================================================================================= *\
|* ====================================== Test dei metodi della vista entrySet ===================================== *|
\* ================================================================================================================= */

	/**
	 * Costruttore predefinito per la classe {@code TestEmptyEntrySet}.
	 * Questa classe è un'utility e non richiede un'inizializzazione di stato complessa.
	 */
	public TestEmptyEntrySet() {
		// intentionally left empty
	}

	/**
	 * Metodo di setup che viene eseguito prima di ogni test.
	 * Inizializza un'istanza di {@code MapAdapter} che rappresenta una mappa vuota da cui ottiene un'istanza di {@code EntrySetAdapter}
	 * che rappresenta la vista per entry (anch'essa vuota) e un'istanza di {@code EntrySetIterator} per l'iteratore della vista.
	 */
	@Before
	public void setUp() {
		map = new MapAdapter();
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
	 * Verifica che il metodo {@code size()} restituisca 0, ovvero il numero di elementi presenti in una vista proveniente
	 * da una mappa vuota.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa vuota.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code size()} è stato chiamato su una vista vuota e ha restituito 0.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code size()} dovrebbe restituire 0.
	 */
	@Test
	public void testSizeOnEmptyEntrySet() {
		assertEquals("La vista dovrebbe avere 0 elementi", 0, entrySet.size());
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
	 * Verifica che il metodo {@code isEmpty()} restituisca {@code true} per una vista senza elementi.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa vuota.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code isEmpty()} è stato chiamato su una vista senza elementi e ha restituito {@code true}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code isEmpty()} dovrebbe restituire {@code true}.
	 */
	@Test
	public void testIsEmptyOnEmptyEntrySet() {
		assertTrue("La vista dovrebbe essere vuota", entrySet.isEmpty());
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
	 * Verifica che il metodo {@code contains(Object o)} restituisca {@code false} quando gli viene passata una entry
	 * che non appartiene alla mappa (in questo caso, una entry con chiavi e valori di tipo {@code String} istanziata
	 * attraverso la classe {@code SimpleHMapWithNulls.SimpleHEntry}).
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa vuota. Viene inoltre creata una nuova entry generica (sfruttando l'implementazione
	 * {@code SimpleHMapWithNulls.SimpleHEntry}) da passare come parametro.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code contains(Object o)} è stato chiamato su una vista vuota e ha restituito {@code false}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code contains(Object o)} dovrebbe restituire {@code false} per una vista vuota.
	 */
	@Test
	public void testContainsOnEmptyEntrySet() {
		HEntry e = new SimpleHMapWithNulls.SimpleHEntry("a_random_key", "a_random_value");
		assertFalse("contains dovrebbe restituire false se invocato su una vista vuota", entrySet.contains(e));
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
	public void testContainsNullEntryOnEmptyEntrySet() {
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
	public void testContainsNullKeyOnEmptyEntrySet() {
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
	public void testContainsNullValueOnEmptyEntrySet() {
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
	public void testContainsNotEntryOnEmptyEntrySet() {
		String notAnEntry = "This is not an Entry";
		entrySet.contains(notAnEntry); // lancia ClassCastException
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#iterator()}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code iterator()} dovrebbe restituire un iteratore di tipo {@code EntrySetIterator} su una vista vuota.
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
	 * collegata a una mappa vuota.
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
	public void testIteratorOnEmptyEntrySet() {
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
	 * Verifica che il metodo {@code toArray()} restituisca un array di lunghezza 0 per una vista proveniente
	 * da una mappa vuota.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa vuota.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code toArray()} è stato chiamato su una vista vuota e ha restituito un array di lunghezza 0.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code toArray()} dovrebbe restituire un array di Object di lunghezza 0.
	 */
	@Test
	public void testToArray1OnEmptyEntrySet() {
		assertTrue("La vista dovrebbe restituire un array di Object", entrySet.toArray() instanceof Object[]);
		assertEquals("La vista dovrebbe restituire un array di lunghezza 0", 0, entrySet.toArray().length);
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
	 * Siccome la vista non contiene elementi, nessun elemento dovrebbe essere presente nell'array, per cui ci si aspetta
	 * che il metodo {@code toArray(Object[] a)} dovrebbe restituire lo stesso array passato come parametro, con il primo
	 * elemento impostato a {@code null}.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa vuota. Viene creato un nuovo array passato come parametro e riempito con interi 1, per testare se
	 * effettivamente il primo elemento è impostato a {@code null}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code toArray(Object[] a)} è stato chiamato su una vista vuota e ha restituito lo stesso array passato
	 * come parametro, con il primo elemento impostato a {@code null}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code toArray(Object[] a)} dovrebbe restituire un array con lo stesso riferimento di quello passato
	 * come parametro, con il primo elemento impostato a {@code null}.
	 */
	@Test
	public void testToArray2OnEmptyEntrySet() {
		Object[] array = new Object[10];
		Arrays.fill(array, 1); // inizializza l'array con valori diversi da null
		assertTrue("L'array restituito dovrebbe essere lo stesso array passato come parametro", array == entrySet.toArray(array));
		assertNull("Il primo elemento dell'array restituito dovrebbe essere null", array[0]);
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
	 * collegata a una mappa vuota. Viene creato un nuovo array con riferimento {@code null} da passare come parametro al metodo.
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
	public void testToArray2NullArrayOnEmptyEntrySet() {
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
	 * Verifica che il metodo {@code toArray(Object[] a)} non crei un nuovo array quando si passa un array di dimensione
	 * nulla come parametro, siccome la vista è vuota.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa vuota. Viene creato un nuovo array di dimensione nulla da passare come parametro al metodo.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code toArray(Object[] a)} è stato chiamato con un array di dimensione nulla come parametro e ha
	 * restituito lo stesso array passato, senza alterarne il contenuto.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code toArray(Object[] a)} dovrebbe restituire lo stesso array passato come parametro, senza
	 * modificarne il contenuto.
	 */
	@Test
	public void testToArray2EmptyArrayOnEmptyEntrySet() {
		Object[] array = new Object[0];
		assertTrue("L'array restituito dovrebbe essere lo stesso array passato come parametro", array == entrySet.toArray(array));
		assertTrue("L'array restituito dovrebbe rimanere vuoto", entrySet.toArray(array).length == 0);
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
	 * collegata a una mappa vuota.
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
	public void testAddOnEmptyEntrySet() {
		entrySet.add(null); // lancia UnsupportedOperationException
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
	 * Verifica che il metodo {@code remove(Object o)} restituisca {@code false} quando gli viene passata una entry
	 * che non appartiene alla vista siccome non contiene elementi (in questo caso, una entry con chiavi e valori di tipo {@code String} istanziata
	 * attraverso la classe {@code SimpleHMapWithNulls.SimpleHEntry}).
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa vuota. Viene inoltre creata una nuova entry generica (sfruttando l'implementazione
	 * {@code SimpleHMapWithNulls.SimpleHEntry}) da passare come parametro.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code remove(Object o)} è stato chiamato su una vista vuota e ha restituito {@code false}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code remove(Object o)} dovrebbe restituire {@code false}, inoltre la vista (e di conseguenza la mappa)
	 * dovrebbe rimanere vuota e con 0 elementi.
	 */
	@Test
	public void testRemoveOnEmptyEntrySet() {
		HEntry e = new SimpleHMapWithNulls.SimpleHEntry("a_random_key", "a_random_value");
		assertFalse("remove dovrebbe restituire false se invocato su una vista vuota", entrySet.remove(e));
		assertTrue("La vista dovrebbe rimanere vuota", entrySet.isEmpty());
		assertEquals("La vista dovrebbe rimanere con 0 elementi", 0, entrySet.size());
		assertTrue("La mappa associata alla vista dovrebbe rimanere vuota", map.isEmpty());
		assertEquals("La mappa associata alla vista dovrebbe rimanere con 0 elementi", 0, map.size());
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
	public void testRemoveNullEntryOnEmptyEntrySet() {
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
	public void testRemoveNullKeyOnEmptyEntrySet() {
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
	public void testRemoveNullValueOnEmptyEntrySet() {
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
	public void testRemoveNotEntryOnEmptyEntrySet() {
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
	 * senza elementi e gli viene passata una collezione vuota. Il metodo non dovrebbe lanciare eccezioni e dovrebbe
	 * restituire {@code true} in quanto non ci sono elementi nella vista.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa vuota. Viene creata una nuova mappa vuota per generare una collezione di entry vuota
	 * da passare come parametro al metodo {@code containsAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code containsAll(HCollection c)} è stato chiamato con una collezione di entry vuota e ha
	 * restituito {@code true}, siccome la collezione è vuota e la vista non contiene elementi.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code containsAll(HCollection c)} dovrebbe restituire {@code true}.
	 */
	@Test
	public void testContainsAllEmptyCollectionOnEmptyEntrySet() {
		MapAdapter newEmptyMap = new MapAdapter();
		HCollection emptyCollection = newEmptyMap.entrySet();
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
	 * passata è {@code null} o se una entry della collezione ha chiave o valore {@code null}. Lancia, inoltre,
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
	 * collegata a una mappa vuota. Viene creato un nuovo riferimento di tipo {@code HCollection} per generare una
	 * collezione {@code null} da passare come parametro al metodo {@code containsAll(HCollection c)}.
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
	public void testContainsAllNullCollectionOnEmptyEntrySet() {
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
	 * Verifica che il metodo {@code containsAll(HCollection c)} funzioni correttamente quando è invocato su una vista
	 * senza elementi e gli viene passata una collezione non vuota. Il metodo non dovrebbe lanciare eccezioni e dovrebbe
	 * restituire {@code false} in quanto non ci sono elementi nella vista.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter} collegata
	 * a una mappa vuota. Viene creata una nuova mappa con una entry generica (con chiave e valore di tipo {@code String})
	 * per generare una collezione di entry non vuota da passare come parametro al metodo {@code containsAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code containsAll(HCollection c)} è stato chiamato con una collezione di entry vuota e ha
	 * restituito {@code false}, siccome la collezione non è vuota e la vista non contiene elementi.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code containsAll(HCollection c)} dovrebbe restituire {@code false}.
	 */
	@Test
	public void testContainsAllPopulatedCollectionOnEmptyEntrySet() {
		MapAdapter newEmptyMap = new MapAdapter(); // crea una nuova mappa vuota
		newEmptyMap.put("key1", "value1"); // aggiunge una entry alla mappa, in modo da popolare la collezione
		HCollection populatedCollection = newEmptyMap.entrySet(); // ottiene una collezione con la entry appena aggiunta
		assertFalse("La vista non dovrebbe contenere tutti gli elementi della collezione non vuota", entrySet.containsAll(populatedCollection));
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
	public void testContainsAllPopulatedCollectionWithNullKeyOnEmptyEntrySet() {
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
	public void testContainsAllPopulatedCollectionWithNullValueOnEmptyEntrySet() {
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
	public void testContainsAllPopulatedCollectionWithNotEntryOnEmptyEntrySet() {
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
	 * collegata a una mappa vuota.
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
	public void testAddAllOnEmptyEntrySet() {
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
	 * Verifica che il metodo {@code removeAll(HCollection c)}funzioni correttamente quando è invocato su una vista
	 * senza elementi e gli viene passata una collezione vuota. Il metodo non dovrebbe lanciare eccezioni e dovrebbe
	 * restituire {@code false} in quanto non ci sono elementi nella vista.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa vuota. Viene creata una nuova mappa vuota per generare una collezione di entry vuota
	 * da passare come parametro al metodo {@code removeAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code removeAll(HCollection c)} è stato chiamato con una collezione di entry vuota e ha
	 * restituito {@code false}, siccome la collezione è vuota e la vista non contiene elementi rimovibili.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code removeAll(HCollection c)} dovrebbe restituire {@code false}, inoltre la vista (e di conseguenza
	 * la mappa) dovrebbe rimanere vuota e con 0 elementi.
	 */
	@Test
	public void testRemoveAllEmptyCollectionOnEmptyEntrySet() {
		HCollection emptyCollection = new MapAdapter().values();
		assertFalse("removeAll dovrebbe restituire false se la collezione è vuota", entrySet.removeAll(emptyCollection));
		assertTrue("La vista dovrebbe rimanere vuota", entrySet.isEmpty());
		assertEquals("La vista dovrebbe rimanere con 0 elementi", 0, entrySet.size());
		assertTrue("La mappa associata alla vista dovrebbe rimanere vuota", map.isEmpty());
		assertEquals("La mappa associata alla vista dovrebbe rimanere con 0 elementi", 0, map.size());
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
	 * Verifica che il metodo {@code removeAll(HCollection c)} lanci {@code NullPointerException} se gli viene
	 * passata una collezione {@code null}.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa vuota. Viene creato un nuovo riferimento di tipo {@code HCollection} per generare una
	 * collezione {@code null} da passare come parametro al metodo {@code removeAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code removeAll(HCollection c)} è stato chiamato con una collezione {@code null} e ha
	 * lanciato una {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code removeAll(HCollection c)} dovrebbe lanciare una {@code NullPointerException}.
	 */
	@Test(expected = NullPointerException.class)
	public void testRemoveAllNullCollectionOnEmptyEntrySet() {
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
	 * Verifica che il metodo {@code removeAll(HCollection c)} funzioni correttamente quando è invocato su una vista
	 * senza elementi e gli viene passata una collezione non vuota. Il metodo non dovrebbe lanciare eccezioni e dovrebbe
	 * restituire {@code false} in quanto non ci sono elementi nella vista.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter} collegata
	 * a una mappa vuota. Viene creata una nuova mappa con una entry generica per generare una collezione di entry non
	 * vuota da passare come parametro al metodo {@code containsAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code containsAll(HCollection c)} è stato chiamato con una collezione di entry vuota e ha
	 * restituito {@code false}, siccome la collezione non è vuota e la vista non contiene elementi rimovibili.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code removeAll(HCollection c)} dovrebbe restituire {@code false}, inoltre la vista (e di conseguenza
	 * la mappa) dovrebbe rimanere vuota e con 0 elementi.
	 */
	@Test
	public void testRemoveAllPopulatedCollectionOnEmptyEntrySet() {
		MapAdapter newEmptyMap = new MapAdapter(); // crea una nuova mappa vuota
		newEmptyMap.put("key1", "value1"); // aggiunge una entry alla mappa, in modo da popolare la collezione
		HCollection populatedCollection = newEmptyMap.entrySet(); // ottiene una collezione con la entry appena aggiunta
		assertFalse("removeAll dovrebbe restituire false se la collezione è non vuota",
				entrySet.removeAll(populatedCollection));
		assertTrue("La vista dovrebbe rimanere vuota", entrySet.isEmpty());
		assertEquals("La vista dovrebbe rimanere con 0 elementi", 0, entrySet.size());
		assertTrue("La mappa associata alla vista dovrebbe rimanere vuota", map.isEmpty());
		assertEquals("La mappa associata alla vista dovrebbe rimanere con 0 elementi", 0, map.size());
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
	 * Verifica che il metodo {@code removeAll(HCollection c)} non lanci {@code ClassCastException} se la collezione
	 * passata non supporta i tipi degli elementi della vista. In questo caso gli passo una {@code keySet()} e una
	 * {@code values()}. Non lancia ClassCastException, siccome la vista è vuota e non ci sono elementi incompatibili
	 * con la collezione passata.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa vuota. Vengono creati due nuovi riferimenti di tipo {@code HCollection} attraverso i metodi
	 * {@code keySet()} e {@code values()} per generare le due altre collezioni e verificare che non lancino
	 * {@code ClassCastException}, siccome supportano i tipi degli elementi della vista.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code removeAll(HCollection c)} è stato chiamato con le altre due collezioni che supportano i tipi degli
	 * elementi della vista e ha restituito {@code false}, senza lanciare eccezioni.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code removeAll(HCollection c)} dovrebbe restituire {@code false}, inoltre la vista (e di conseguenza
	 * la mappa) dovrebbe rimanere vuota e con 0 elementi.
	 */
	@Test
	public void testRemoveAllWithClassCastExceptionOnEmptyKeySet() {
		MapAdapter newPopulatedMap = new MapAdapter();
		newPopulatedMap.put("key1", "value1"); // aggiunge una entry generica
		HCollection keySet = newPopulatedMap.keySet(); // ottiene la keySet della mappa
		HCollection values = newPopulatedMap.values(); // ottiene la values della mappa
		assertFalse("removeAll dovrebbe restituire false se la vista è vuota, anche se gli elementi della vista non sono compatibili con la keySet", entrySet.removeAll(keySet));
		assertFalse("removeAll dovrebbe restituire false se la vista è vuota, anche se gli elementi della vista non sono compatibili con la values", entrySet.removeAll(values));
		assertTrue("La vista dovrebbe rimanere vuota", entrySet.isEmpty());
		assertEquals("La vista dovrebbe rimanere con 0 elementi", 0, entrySet.size());
		assertTrue("La mappa associata alla vista dovrebbe rimanere vuota", map.isEmpty());
		assertEquals("La mappa associata alla vista dovrebbe rimanere con 0 elementi", 0, map.size());
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
	public void testRemoveAllPopulatedCollectionWithNullKeyOnEmptyEntrySet() {
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
	public void testRemoveAllPopulatedCollectionWithNullValueOnEmptyEntrySet() {
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
	public void testRemoveAllPopulatedCollectionWithNotEmptyOnEmptyEntrySet() {
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
	 * Verifica che il metodo {@code retainAll(HCollection c)} funzioni correttamente quando è invocato su una vista
	 * senza elementi e gli viene passata una collezione vuota. Il metodo non dovrebbe lanciare eccezioni e dovrebbe
	 * restituire {@code false} in quanto non ci sono elementi nella vista.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa vuota. Viene creata una nuova mappa vuota per generare una collezione di entry vuota
	 * da passare come parametro al metodo {@code retainAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code retainAll(HCollection c)} è stato chiamato con una collezione di entry vuota e ha
	 * restituito {@code false}, siccome la collezione è vuota e la vista non contiene elementi rimovibili.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code retainAll(HCollection c)} dovrebbe restituire {@code false}, inoltre la vista (e di conseguenza
	 * la mappa) dovrebbe rimanere vuota e con 0 elementi.
	 */
	@Test
	public void testRetainAllEmptyCollectionOnEmptyEntrySet() {
		HCollection emptyCollection = new MapAdapter().entrySet();
		assertFalse("retainAll dovrebbe restituire false se la collezione è vuota", entrySet.retainAll(emptyCollection));
		assertTrue("La vista dovrebbe rimanere vuota", entrySet.isEmpty());
		assertEquals("La vista dovrebbe rimanere con 0 elementi", 0, entrySet.size());
		assertTrue("La mappa associata alla vista dovrebbe rimanere vuota", map.isEmpty());
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
	 * collegata a una mappa vuota. Viene creato un nuovo riferimento di tipo {@code HCollection} per generare una
	 * collezione {@code null} da passare come parametro al metodo {@code retainAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code retainAll(HCollection c)} è stato chiamato con una collezione {@code null} e ha
	 * lanciato una {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code retainAll(HCollection c)} dovrebbe lanciare una {@code NullPointerException}.
	 */
	@Test(expected = NullPointerException.class)
	public void testRetainAllNullCollectionOnEmptyEntrySet() {
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
	 * Verifica che il metodo {@code retainAll(HCollection c)} funzioni correttamente quando è invocato su una vista
	 * senza elementi e gli viene passata una collezione non vuota. Il metodo non dovrebbe lanciare eccezioni e dovrebbe
	 * restituire {@code false} in quanto non ci sono elementi nella vista.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa vuota. Viene creata una nuova mappa con una entry generica per generare una collezione di entry non
	 * vuota da passare come parametro al metodo {@code retainAll(HCollection c)}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code retainAll(HCollection c)} è stato chiamato con una collezione di entry vuota e ha
	 * restituito {@code false}, siccome la collezione non è vuota e la vista non contiene elementi rimovibili.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code retainAll(HCollection c)} dovrebbe restituire {@code false}, inoltre la vista (e di conseguenza
	 * la mappa) dovrebbe rimanere vuota e con 0 elementi.
	 */
	@Test
	public void testRetainAllPopulatedCollectionOnEmptyEntrySet() {
		MapAdapter newEmptyMap = new MapAdapter(); // crea una nuova mappa vuota
		newEmptyMap.put("key1", "value1"); // aggiunge una entry alla mappa, in modo da popolare la collezione
		HCollection populatedCollection = newEmptyMap.entrySet(); // ottiene una collezione con la entry appena aggiunta
		assertFalse("retainAll dovrebbe restituire false se la collezione è non vuota", entrySet.retainAll(populatedCollection));
		assertTrue("La vista dovrebbe rimanere vuota", entrySet.isEmpty());
		assertEquals("La vista dovrebbe rimanere con 0 elementi", 0, entrySet.size());
		assertTrue("La mappa associata alla vista dovrebbe rimanere vuota", map.isEmpty());
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
	 * Verifica che il metodo {@code retainAll(HCollection c)} non lanci {@code ClassCastException} se la collezione
	 * passata non supporta i tipi degli elementi della vista. In questo caso gli passo una {@code keySet()} e una
	 * {@code values()}. Non lancia ClassCastException, siccome la vista è vuota e non ci sono elementi incompatibili
	 * con la collezione passata.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter} collegata
	 * a una mappa vuota. Vengono creati due nuovi riferimenti di tipo {@code HCollection} attraverso i metodi
	 * {@code keySet()} e {@code values()} per generare le due altre collezioni e verificare che non lancino
	 * {@code ClassCastException}, siccome supportano i tipi degli elementi della vista.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code retainAll(HCollection c)} è stato chiamato con le altre due collezioni che supportano i tipi degli
	 * elementi della vista e ha restituito {@code false}, senza lanciare eccezioni.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code retainAll(HCollection c)} dovrebbe restituire {@code false}, inoltre la vista (e di conseguenza
	 * la mappa) dovrebbe rimanere vuota e con 0 elementi.
	 */
	@Test
	public void testRetainAllWithClassCastExceptionOnEmptyKeySet() {
		MapAdapter newPopulatedMap = new MapAdapter();
		newPopulatedMap.put("key1", "value1"); // aggiunge una entry generica
		HCollection keySet = newPopulatedMap.keySet(); // ottiene la keySet della mappa
		HCollection values = newPopulatedMap.values(); // ottiene la values della mappa
		assertFalse("retainAll dovrebbe restituire false se la vista è vuota, anche se gli elementi della vista non sono compatibili con la keySet", entrySet.retainAll(keySet));
		assertFalse("retainAll dovrebbe restituire false se la vista è vuota, anche se gli elementi della vista non sono compatibili con la values", entrySet.retainAll(values));
		assertTrue("La vista dovrebbe rimanere vuota", entrySet.isEmpty());
		assertEquals("La vista dovrebbe rimanere con 0 elementi", 0, entrySet.size());
		assertTrue("La mappa associata alla vista dovrebbe rimanere vuota", map.isEmpty());
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
	public void testRetainAllPopulatedCollectionWithNullKeyOnEmptyEntrySet() {
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
	public void testRetainAllPopulatedCollectionWithNullValueOnEmptyEntrySet() {
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
	public void testRetainAllPopulatedCollectionWithNotEntryOnEmptyEntrySet() {
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
	 * Verifica che il metodo {@code clear()} invocato su una vista senza elementi mantenga la vista vuota, senza
	 * lanciare eccezioni.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa vuota.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code clear()} è stato chiamato su una vista vuota e non ha lanciato eccezioni.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code clear()} dovrebbe mantenere la vista vuota e con 0 elementi, la mappa associata dovrebbe a sua
	 * volta essere vuota e con 0 elementi.
	 */
	@Test
	public void testClearOnEmptyEntrySet() {
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
	 * Verifica che il metodo {@code hashCode()} restituisca 0 quando invocato su una vista vuota.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa vuota. Viene creata una nuova istanza di {@code EntrySetAdapter} collegata a una mappa vuota
	 * su cui invocare il metodo {@code hashCode(Object o)}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code hashCode()} è stato chiamato su una vista vuota e ha restituito 0.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code hashCode()} dovrebbe restituire 0 per una vista vuota e l'hash code di una nuova vista vuota
	 * dovrebbe essere uguale a quello della vista instanziata dal {@code setUp()}.
	 */
	@Test
	public void testHashOnEmptyMap() {
		HSet newEmptyEntrySet = new MapAdapter().entrySet();
		assertEquals("L'hash code della mappa vuota dovrebbe essere 0", 0, entrySet.hashCode());
		assertTrue("L'hash code di due mappe vuote dovrebbe essere lo stesso", entrySet.hashCode() == newEmptyEntrySet.hashCode());
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
	 * Verifica che il metodo {@code equals(Object o)} restituisca {@code false} quando gli viene passato {@code null}.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa vuota.
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
	public void testEqualsNullOnEmptyEntrySet() {
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
	 * collegata a una mappa vuota. Viene creato un nuovo oggetto di tipo {@code String} (non {@code EntrySetAdapter}) da passare
	 * come parametro al metodo {@code equals(Object o)}.
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
	public void testEqualsNotEntrySetOnEmptyEntrySet() {
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
	 * view (di tipo {@code ValuesCollectionAdapter} o {@code EntrySetAdapter}) sempre collegata a una mappa vuota.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa vuota. Vengono creati due nuovi oggetti di tipo {@code ValuesCollectionAdapter} e
	 * {@code EntrySetAdapter} da passare come parametro al metodo {@code equals(Object o)}, provenienti da una
	 * nuova mappa vuota.
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
	public void testEqualsOtherViewsOnEmptyEntrySet() {
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
	 * Verifica che il metodo {@code equals(Object o)} restituisca {@code true} quando gli viene passata un'altra
	 * istanza di {@code EntrySetAdapter} proveniente da una nuova mappa vuota.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa vuota. Viene creata una nuova istanza di {@code EntrySetAdapter} collegata a una mappa vuota da passare
	 * come parametro al metodo {@code equals(Object o)}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code equals(Object o)} è stato chiamato con una nuova istanza di {@code EntrySetAdapter} collegata a una
	 * mappa vuota.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code equals(Object o)} dovrebbe restituire {@code true}.
	 */
	@Test
	public void testEqualsNewEmptyEntrySetOnEmptyEntrySet() {
		HSet newEmptyEntrySet = new MapAdapter().entrySet();
		assertTrue("equals dovrebbe restituire true se gli viene passato un'altro entrySet vuoto", entrySet.equals(newEmptyEntrySet));
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
	 * istanza di {@code EntrySetAdapter} proveniente da una nuova mappa non vuota.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa vuota. Vengono create altre nuove istanze di {@code EntrySetAdapter} da passare come
	 * parametro al metodo {@code equals(Object o)}, collegate ad altettante mappe popolate:
	 * <ul>
	 * <li>con una entry con chiave e valore di tipo {@code String}</li>
	 * <li>con una entry con chiave di tipo {@code String} e valore {@code null}</li>
	 * <li>con una entry con chiave {@code null} e valore di tipo {@code String}</li>
	 * <li>con una entry con chiave e valore {@code null}</li>
	 * </ul>
	 * Per accettare chiavi e valori {@code null} si utilizza l'implementazione {@code SimpleHMapWithNulls} della mappa.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code equals(Object o)} è stato chiamato con tutte le nuove istanze di {@code EntrySetAdapter} collegate
	 * ad una mappa non vuota.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code equals(Object o)} dovrebbe restituire {@code false} in tutte e tre i casi.
	 */
	@Test
	public void testEqualsPopulatedEntrySetOnEmptyEntrySet() {
		// Mappa con una entry con chiave e valore di tipo String
		MapAdapter populatedMap1 = new MapAdapter();
		populatedMap1.put("key", "value");
		HSet populatedEntrySet1 = populatedMap1.entrySet();
		assertFalse("equals dovrebbe restituire false se gli viene passata una EntrySet con entry con chiave e valore di tipo string", entrySet.equals(populatedEntrySet1));

		// Mappa con una entry con chiave di tipo String e valore null
		SimpleHMapWithNulls populatedMap2 = new SimpleHMapWithNulls();
		populatedMap2.put("key", null);
		HSet populatedEntrySet2 = populatedMap2.entrySet();
		assertFalse("equals dovrebbe restituire false se gli viene passata una EntrySet con entry con chiave di tipo string e valore null", entrySet.equals(populatedEntrySet2));
		
		// Mappa con una entry con chiave null e valore di tipo String
		SimpleHMapWithNulls populatedMap3 = new SimpleHMapWithNulls();
		populatedMap3.put(null, "value");
		HSet populatedEntrySet3 = populatedMap3.entrySet();
		assertFalse("equals dovrebbe restituire false se gli viene passata una EntrySet con entry con chiave null e valore di tipo string", entrySet.equals(populatedEntrySet3));

		// Mappa con una entry con chiave e valore null
		SimpleHMapWithNulls populatedMap4 = new SimpleHMapWithNulls();
		populatedMap4.put(null, null);
		HSet populatedEntrySet4 = populatedMap4.entrySet();
		assertFalse("equals dovrebbe restituire false se gli viene passata una EntrySet con entry con chiave e valore null", entrySet.equals(populatedEntrySet4));
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
	 * collegata a una mappa vuota.
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
	public void testEqualsSelfOnEmptyEntrySet() {
		assertTrue("equals dovrebbe restituire true se gli viene passato se stesso", entrySet.equals(entrySet));
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetAdapter#toString()}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code toString()} restituisce una rappresentazione in stringa della vista. Per una vista vuota,
	 * dovrebbe restituire la stringa "[]".
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code toString()} restituisca la rappresentazione corretta per una vista vuota.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa vuota.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code toString()} è stato chiamato su una vista vuota e ha restituito la stringa "[]".
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code toString()} dovrebbe restituire la stringa "[]".
	 */
	@Test
	public void testToStringOnEmptyMap() {
		assertEquals("La rappresentazione in stringa di una mappa vuota dovrebbe essere []", "[]", entrySet.toString());
	}


/* ================================================================================================================= *\
|* ============================== Test dei metodi dell'iteratore della vista entrySet ============================== *|
\* ================================================================================================================= */

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetIterator#hasNext()}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code hasNext()} verifica se ci sono elementi successivi nell'iteratore. Per un iteratore
	 * associato a una vista vuota, dovrebbe restituire {@code false}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code hasNext()} restituisca {@code false} per un iteratore associato a una vista vuota.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa vuota e dell'iteratore {@code EntrySetIterator} associato alla vista.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code hasNext()} è stato chiamato su un iteratore associato a una vista vuota e ha restituito
	 * {@code false}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code hasNext()} dovrebbe restituire {@code false}.
	 */
	@Test
	public void testHasNextOnEmptyEntrySetIterator() {
		assertFalse("hasNext dovrebbe restituire false per un iteratore associato a una vista vuota", entrySetIterator.hasNext());
	}
	
	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetIterator#next()}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code next()} restituisce l'elemento successivo nell'iteratore. Per un iteratore associato a una
	 * vista vuota, dovrebbe lanciare un'eccezione {@code java.util.NoSuchElementException} in quanto non c'è nessun
	 * elemento da estrarre e il corrispettivo {@code hasNext()} dovrebbe restituire {@code false} (come verificato
	 * nel test {@link #testHasNextOnEmptyEntrySetIterator()}).
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code next()} generi un'eccezione {@code java.util.NoSuchElementException} per un
	 * iteratore associato a una vista vuota.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa vuota e dell'iteratore {@code EntrySetIterator} associato alla vista.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code next()} è stato chiamato su un iteratore associato a una vista vuota e ha lanciato
	 * un'eccezione {@code java.util.NoSuchElementException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code next()} dovrebbe lanciare {@code java.util.NoSuchElementException}.
	 */
	@Test(expected = java.util.NoSuchElementException.class)
	public void testNextOnEmptyEntrySetIterator() {
		entrySetIterator.next(); // lancia java.util.NoSuchElementException
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter.EntrySetIterator#remove()}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code remove()} rimuove l'elemento appena restituito dal metodo {@code next()} dell'iteratore. Per
	 * un iteratore associato a una vista vuota, dovrebbe lanciare un'eccezione {@code myAdapter.IllegalStateException}
	 * in quanto non c'è nessun elemento da rimuovere e il corrispettivo {@code next()} non si può chiamare perché
	 * lancia un'eccezione (come verificato nel test {@link #testNextOnEmptyEntrySetIterator()}).
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code remove()} generi un'eccezione {@code myAdapter.IllegalStateException} per un
	 * iteratore associato a una vista vuota.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code EntrySetAdapter}
	 * collegata a una mappa vuota e dell'iteratore {@code EntrySetIterator} associato alla vista.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code remove()} è stato chiamato su un iteratore associato a una vista vuota e ha lanciato
	 * un'eccezione {@code myAdapter.IllegalStateException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code remove()} dovrebbe lanciare {@code myAdapter.IllegalStateException}.
	 */
	@Test(expected = myAdapter.IllegalStateException.class)
	public void testRemoveOnEmptyEntrySetIterator() {
		entrySetIterator.remove(); // lancia myAdapter.IllegalStateException
	}
}
