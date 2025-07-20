// Giacomo Simonetto - 2109923 - Secondo appello 2025 di Elementi di Ingegneria del Software

package myTest;

// importazione componenti del pacchetto myAdapter da testare
import myAdapter.HCollection;
import myAdapter.HSet;
import myAdapter.HIterator;
import myAdapter.MapAdapter;
import myAdapter.UnsupportedOperationException;
import myAdapter.IllegalStateException;

// importazione della classe Arrays per testare le operazioni sugli array
import java.util.Arrays;

// importazioni necessarie per JUnit
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * <b>Summary:</b>
 * Classe contenente una suite di test per verificare il comportamento di {@link myAdapter.MapAdapter.ValuesCollectionAdapter},
 * quando proviene da una mappa vuota e non contiene elementi. Sono testati tutti i metodi implementati dalla vista,
 * assicurando che non lancino eccezioni inattese e restituiscano risultati corretti. Sono inoltre testati i metodi
 * dell'iteratore {@link myAdapter.MapAdapter.ValuesCollectionIterator} associato alla vista, per garantire che funzionino
 * correttamente anche su una vista vuota.
 *
 * <p>
 * <b>Test Case Design:</b>
 * La motivazione di questa suite è garantire che la vista e il relativo iteratore gestiscano correttamente i casi
 * limite e le operazioni collegate ad una mappa vuota, prima di procedere con test su mappe non vuote.
 *
 * <p>
 * I test sono divisi in due gruppi: prima i test eseguiti sui metodi della vista e successivamente quelli eseguiti
 * sui metodi dell'iteratore. Sono inoltre ordinati secondo l'ordinamento dei corrispettivi metodi testati nella
 * documentazione delle classi {@link myAdapter.MapAdapter.AbstractViewAdapter}, {@link myAdapter.MapAdapter.ValuesCollectionAdapter},
 * {@link myAdapter.MapAdapter.AbstractIterator} e {@link myAdapter.MapAdapter.ValuesCollectionIterator} e delle relative interfacce.
 *
 * <p>
 * Siccome le classi da testare non sono istanziabili direttamente, i test della vista sono eseguiti su un riferimento
 * {@code HCollection} ottenuto da una mappa vuota, tramite il metodo {@code values()} della mappa, mentre quelli dell'iteratore
 * sono eseguiti su un riferimento {@code HIterator} ottenuto dalla vista, tramite il metodo {@code iterator()} della vista.
 *
 * <p>
 * I costruttori delle viste e degli iteratori sono già stati testati negli opportuni test che ne restituiscono
 * le corrispettive istanze, come ad esempio {@link myAdapter.MapAdapter#values()} per la vista e
 * {@link myAdapter.MapAdapter.ValuesCollectionAdapter#iterator()} per l'iteratore.
 *
 * <p>
 * Sono stati implementati 36 test in totale, di cui:
 * <ul>
 *   <li> 33 test per i metodi della vista {@code ValuesCollectionAdapter} associata alla mappa vuota</li>
 *   <li> 3 test per i metodi dell'iteratore {@code ValuesCollectionIterator} associato alla vista</li>
 * </ul>
 *
 * <p>
 * <b>Dependencies:</b>
 * <ul>
 *   <li>File {@code JUnit}:    {@code ./JUnit/junit-4.13.jar}        - versione {@code 4.13}
 *   <li>File {@code Hamcrest}: {@code ./JUnit/hamcrest-core-1.3.jar} - versione {@code 1.3}
 * </ul>
 */
public class TestEmptyValuesCollection {
	// variabili di istanza per la mappa, la vista e l'iteratoreda testare
	private MapAdapter map;
	private HCollection values;
	private HIterator valuesIterator;

/* ================================================================================================================= *\
|* ======================================= Test dei metodi della vista values ====================================== *|
\* ================================================================================================================= */

	/**
	 * Costruttore predefinito per la classe {@code TestEmptyValuesCollection}.
	 * Questa classe è un'utility e non richiede un'inizializzazione di stato complessa.
	 */
	public TestEmptyValuesCollection() {
		// intentionally left empty
	}

	/**
	 * Metodo di setup che viene eseguito prima di ogni test.
	 * Inizializza un'istanza di {@code MapAdapter} che rappresenta una mappa vuota da cui ottiene un'istanza di
	 * {@code ValuesCollectionAdapter} che rappresenta la vista per valori (anch'essa vuota) e un'istanza di
	 * {@code ValuesCollectionIterator} per l'iteratore della vista.
	 */
	@Before
	public void setUp() {
		map = new MapAdapter();
		values = map.values();
		valuesIterator = values.iterator();
	}

	/**
	 * Metodo di teardown che viene eseguito dopo ogni test.
	 * Rilascia le risorse utilizzate dalla mappa.
	 */
	@After
	public void tearDown() {
		map = null;
		values = null;
		valuesIterator = null;
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionAdapter#size()} su una vista vuota.
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
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
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
	public void testSizeOnEmptyValuesCollection() {
		assertEquals("La vista dovrebbe avere 0 elementi", 0, values.size());
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionAdapter#isEmpty()} su una vista vuota.
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
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
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
	public void testIsEmptyOnEmptyValuesCollection() {
		assertTrue("La vista dovrebbe essere vuota", values.isEmpty());
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionAdapter#contains(Object o)} su una vista vuota con valore non {@code null}.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code contains(Object o)} verifica se l'oggetto specificato è contenuto nella vista (e di conseguenza
	 * come valore di una entry della mappa). Restituisce {@code true} se l'oggetto è presente, {@code false} altrimenti.
	 * Lancia {@code NullPointerException} se l'oggetto passato è {@code null}.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code contains(Object o)} restituisca {@code false} quando gli viene passato un generico
	 * oggetto che non è un valore della mappa (in questo caso, una stringa).
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
	 * collegata a una mappa vuota.
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
	public void testContainsOnEmptyValuesCollection() {
		assertFalse("contains dovrebbe restituire false se invocato su una vista vuota", values.contains("a_random_value"));
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionAdapter#contains(Object o)} su una vista vuota con valore {@code null}.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code contains(Object o)} verifica se l'oggetto specificato è contenuto nella vista (e di conseguenza
	 * come valore di una entry della mappa). Restituisce {@code true} se l'oggetto è presente, {@code false} altrimenti.
	 * Lancia {@code NullPointerException} se l'oggetto passato è {@code null}.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code contains(Object o)} lanci una {@code NullPointerException} quando gli viene passato
	 * un oggetto {@code null}.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
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
	public void testContainsNullValueOnEmptyValuesCollection() {
		values.contains(null);
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionAdapter#iterator()} su una vista vuota.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code iterator()} dovrebbe restituire un iteratore di tipo {@code ValuesCollectionIterator} su una
	 * vista vuota. I metodi degli iteratori sono testati in modo specifico in altri test, quindi qui ci si limita a
	 * verificare che l'iteratore restituito sia valido.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che l'iteratore restituito sia valido.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
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
	public void testIteratorOnEmptyValuesCollection() {
		HIterator iterator = values.iterator();
		assertNotNull("L'iteratore non dovrebbe essere null", iterator);
		assertTrue("L'iteratore dovrebbe essere un'istanza di HIterator", iterator instanceof myAdapter.HIterator);
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionAdapter#toArray()} su una vista vuota.
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
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
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
	public void testToArray1OnEmptyValuesCollection() {
		assertTrue("La vista dovrebbe restituire un array di Object", values.toArray() instanceof Object[]);
		assertEquals("La vista dovrebbe restituire un array di lunghezza 0", 0, values.toArray().length);
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionAdapter#toArray(Object[] a)} su una vista vuota con array sufficientemente grande.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code toArray(Object[] a)} restituisce un array contenente tutti gli elementi della vista. L'ordine
	 * degli elementi nell'array corrisponde all'ordine in cui gli elementi vengono restituiti dall'iteratore della
	 * vista. Se l'array passato come parametro è di dimensione inferiore alla dimensione della vista, viene creato un
	 * nuovo array di dimensione adeguata, altrimenti gli elementi della vista vengono copiati nell'array passato come
	 * parametro e l'elemento successivo all'ultimo elemento della vista viene impostato a {@code null}.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Siccome la vista non contiene elementi, nessun elemento dovrebbe essere presente nell'array, per cui ci si aspetta
	 * che il metodo {@code toArray(Object[] a)} dovrebbe restituire lo stesso array passato come parametro, con il primo
	 * elemento impostato a {@code null}.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
	 * collegata a una mappa vuota. Viene creato un nuovo array di dimensione 10, riempito con interi 1, da passare
	 * come parametro al metodo.
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
	public void testToArray2OnEmptyValuesCollection() {
		Object[] array = new Object[10];
		Arrays.fill(array, 1); // inizializza l'array con valori diversi da null
		assertTrue("L'array restituito dovrebbe essere lo stesso array passato come parametro", array == values.toArray(array));
		assertNull("Il primo elemento dell'array restituito dovrebbe essere null", array[0]);
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionAdapter#toArray(Object[] a)} su una vista vuota con array {@code null}.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code toArray(Object[] a)} restituisce un array contenente tutti gli elementi della vista. L'ordine
	 * degli elementi nell'array corrisponde all'ordine in cui gli elementi vengono restituiti dall'iteratore della
	 * vista. Se l'array passato come parametro è di dimensione inferiore alla dimensione della vista, viene creato un
	 * nuovo array di dimensione adeguata, altrimenti gli elementi della vista vengono copiati nell'array passato come
	 * parametro e l'elemento successivo all'ultimo elemento della vista viene impostato a {@code null}.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code toArray(Object[] a)} lanci una {@code NullPointerException} quando gli viene passato
	 * un array {@code null}.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
	 * collegata a una mappa vuota. Viene creato un nuovo array con riferimento {@code null} da passare come
	 * parametro al metodo.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code toArray(Object[] a)} è stato chiamato con un array {@code null} come parametro e ha lanciato
	 * una {@code NullPointerException}.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code toArray(Object[] a)} dovrebbe lanciare una {@code NullPointerException}.
	 */
	@Test(expected = NullPointerException.class)
	public void testToArray2NullArrayOnEmptyValuesCollection() {
		Object[] array = null;
		values.toArray(array); // lancia NullPointerException
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionAdapter#toArray(Object[] a)} su una vista vuota con array di dimensione 0.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code toArray(Object[] a)} restituisce un array contenente tutti gli elementi della vista. L'ordine
	 * degli elementi nell'array corrisponde all'ordine in cui gli elementi vengono restituiti dall'iteratore della
	 * vista. Se l'array passato come parametro è di dimensione inferiore alla dimensione della vista, viene creato un
	 * nuovo array di dimensione adeguata, altrimenti gli elementi della vista vengono copiati nell'array passato come
	 * parametro e l'elemento successivo all'ultimo elemento della vista viene impostato a {@code null}.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code toArray(Object[] a)} non crei un nuovo array quando si passa un array di dimensione
	 * nulla come parametro, siccome la vista è vuota.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
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
	public void testToArray2EmptyArrayOnEmptyValuesCollection() {
		Object[] array = new Object[0];
		assertTrue("L'array restituito dovrebbe essere lo stesso array passato come parametro", array == values.toArray(array));
		assertTrue("L'array restituito dovrebbe rimanere vuoto", values.toArray(array).length == 0);
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionAdapter#add(Object o)} su una vista vuota.
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
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
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
	public void testAddOnEmptyValuesCollection() {
		values.add(null); // lancia UnsupportedOperationException
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionAdapter#remove(Object o)} su una vista vuota con valore non presente.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code remove(Object o)} verifica se l'oggetto specificato è contenuto nella vista (e di conseguenza
	 * come valore di una entry della mappa). Se l'oggetto è presente, viene rimosso e il metodo restituisce {@code true}.
	 * Se l'oggetto non è presente, il metodo restituisce {@code false}. Se l'oggetto passato è {@code null}, viene
	 * lanciata una {@code NullPointerException}.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code remove(Object o)} restituisca {@code false} quando gli viene passato un generico
	 * {@code Object} che non è presente nella vista (essendo la vista vuota).
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
	 * collegata a una mappa vuota.
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
	public void testRemoveOnEmptyValuesCollection() {
		assertFalse("remove dovrebbe restituire false se invocato su una vista vuota", values.remove("a_random_value"));
		assertTrue("La vista dovrebbe rimanere vuota", values.isEmpty());
		assertEquals("La vista dovrebbe rimanere con 0 elementi", 0, values.size());
		assertTrue("La mappa associata alla vista dovrebbe rimanere vuota", map.isEmpty());
		assertEquals("La mappa associata alla vista dovrebbe rimanere con 0 elementi", 0, map.size());
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionAdapter#remove(Object o)} su una vista vuota con valore {@code null}.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code remove(Object o)} verifica se l'oggetto specificato è contenuto nella vista (e di conseguenza
	 * come valore di una entry della mappa). Se l'oggetto è presente, viene rimosso e il metodo restituisce {@code true}.
	 * Se l'oggetto non è presente, il metodo restituisce {@code false}. Se l'oggetto passato è {@code null}, viene
	 * lanciata una {@code NullPointerException}.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code remove(Object o)} lanci una {@code NullPointerException} quando gli viene passato
	 * un oggetto {@code null}.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
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
	public void testRemoveNullValueOnEmptyValuesCollection() {
		values.remove(null); // lancia NullPointerException
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionAdapter#containsAll(HCollection c)} su una vista vuota con collezione vuota.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code containsAll(HCollection c)} restituisce {@code true} se la vista contiene tutti gli elementi
	 * della collezione specificata, {@code false} altrimenti. Lancia {@code NullPointerException} se la collezione
	 * passata è {@code null}.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code containsAll(HCollection c)} funzioni correttamente quando è invocato su una vista
	 * senza elementi e gli viene passata una collezione vuota. Il metodo non dovrebbe lanciare eccezioni e dovrebbe
	 * restituire {@code true} in quanto non ci sono elementi nella collezione passata.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
	 * collegata a una mappa vuota. Viene creata una nuova mappa vuota per generare una collezione di valori vuota
	 * da passare come parametro al metodo {@code containsAll(HCollection c)}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code containsAll(HCollection c)} è stato chiamato con una collezione di valori vuota e ha
	 * restituito {@code true}, siccome la collezione è vuota e la vista non contiene elementi.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code containsAll(HCollection c)} dovrebbe restituire {@code true}.
	 */
	@Test
	public void testContainsAllEmptyCollectionOnEmptyValuesCollection() {
		MapAdapter newEmptyMap = new MapAdapter();
		HCollection emptyCollection = newEmptyMap.values();
		assertTrue("La vista dovrebbe contenere tutti gli elementi della collezione vuota", values.containsAll(emptyCollection));
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionAdapter#containsAll(HCollection c)} su una vista vuota con collezione {@code null}.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code containsAll(HCollection c)} restituisce {@code true} se la vista contiene tutti gli elementi
	 * della collezione specificata, {@code false} altrimenti. Lancia {@code NullPointerException} se la collezione
	 * passata è {@code null}.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code containsAll(HCollection c)} lanci una {@code NullPointerException} quando gli viene passata
	 * una collezione {@code null}.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
	 * collegata a una mappa vuota. Viene creato un nuovo riferimento di tipo {@code HCollection} e impostato a
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
	public void testContainsAllNullCollectionOnEmptyValuesCollection() {
		HCollection nullCollection = null;
		values.containsAll(nullCollection); // lancia NullPointerException
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionAdapter#containsAll(HCollection c)} su una vista vuota con collezione popolata.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code containsAll(HCollection c)} restituisce {@code true} se la vista contiene tutti gli elementi
	 * della collezione specificata, {@code false} altrimenti. Lancia {@code NullPointerException} se la collezione
	 * passata è {@code null}.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code containsAll(HCollection c)} restituisca {@code false} se gli viene passata una
	 * collezione contenente generici elementi e se è invocato su una vista senza elementi.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
	 * collegata a una mappa vuota. Viene creata una nuova mappa con una entry generica per generare una collezione
	 * di valori non vuota da passare come parametro al metodo {@code containsAll(HCollection c)}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code containsAll(HCollection c)} è stato chiamato con una collezione di valori non vuota e ha
	 * restituito {@code false}.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code containsAll(HCollection c)} dovrebbe restituire {@code false}.
	 */
	@Test
	public void testContainsAllPopulatedCollectionOnEmptyValuesCollection() {
		MapAdapter newEmptyMap = new MapAdapter(); // crea una nuova mappa vuota
		newEmptyMap.put("key1", "value1"); // aggiunge una entry alla mappa, in modo da popolare la collezione
		HCollection populatedCollection = newEmptyMap.values(); // ottiene una collezione con il valore appena aggiunto
		assertFalse("La vista non dovrebbe contenere tutti gli elementi della collezione non vuota", values.containsAll(populatedCollection));
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionAdapter#addAll(HCollection c)} su una vista vuota.
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
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
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
	public void testAddAllOnEmptyValuesCollection() {
		values.addAll(null); // lancia UnsupportedOperationException
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionAdapter#removeAll(HCollection c)} su una vista vuota con collezione vuota.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code removeAll(HCollection c)} rimuove dalla vista tutti gli elementi che sono presenti nella
	 * collezione specificata. Restituisce {@code true} se la vista (e di conseguenza la mappa) è stata modificata,
	 * {@code false} altrimenti. Lancia {@code NullPointerException} se la collezione passata è {@code null}.
	 * Lancia {@code ClassCastException} se la vista contiene elementi che non sono compatibili con la collezione
	 * passata come parametro.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code removeAll(HCollection c)} funzioni correttamente quando gli viene passata
	 * una collezione vuota su una vista vuota. Il metodo non dovrebbe lanciare eccezioni e dovrebbe
	 * restituire {@code false} in quanto non ci sono entry rimovibili dalla vista.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
	 * collegata a una mappa vuota. Viene creata una nuova mappa vuota per generare una collezione vuota
	 * da passare come parametro al metodo {@code removeAll(HCollection c)}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code removeAll(HCollection c)} è stato chiamato con una collezione vuota e non ha lanciato
	 * eccezioni.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code removeAll(HCollection c)} dovrebbe restituire {@code false}, inoltre la vista (e di conseguenza
	 * la mappa) dovrebbe rimanere vuota e con 0 elementi.
	 */
	@Test
	public void testRemoveAllEmptyCollectionOnEmptyValuesCollection() {
		HCollection emptyCollection = new MapAdapter().values();
		assertFalse("removeAll dovrebbe restituire false se la collezione è vuota", values.removeAll(emptyCollection));
		assertTrue("La vista dovrebbe rimanere vuota", values.isEmpty());
		assertEquals("La vista dovrebbe rimanere con 0 elementi", 0, values.size());
		assertTrue("La mappa associata alla vista dovrebbe rimanere vuota", map.isEmpty());
		assertEquals("La mappa associata alla vista dovrebbe rimanere con 0 elementi", 0, map.size());
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionAdapter#removeAll(HCollection c)} su una vista vuota con collezione {@code null}.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code removeAll(HCollection c)} rimuove dalla vista tutti gli elementi che sono presenti nella
	 * collezione specificata. Restituisce {@code true} se la vista (e di conseguenza la mappa) è stata modificata,
	 * {@code false} altrimenti. Lancia {@code NullPointerException} se la collezione passata è {@code null}.
	 * Lancia {@code ClassCastException} se la vista contiene elementi che non sono compatibili con la collezione
	 * passata come parametro.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code removeAll(HCollection c)} lanci una {@code NullPointerException} quando gli viene passata
	 * una collezione {@code null}.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
	 * collegata a una mappa vuota. Viene creato un nuovo riferimento di tipo {@code HCollection} per generare una
	 * collezione {@code null} da passare come parametro al metodo {@code removeAll(HCollection c)}.
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
	public void testRemoveAllNullCollectionOnEmptyValuesCollection() {
		HCollection nullCollection = null;
		values.removeAll(nullCollection); // lancia NullPointerException
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionAdapter#removeAll(HCollection c)} su una vista vuota con collezione popolata.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code removeAll(HCollection c)} rimuove dalla vista tutti gli elementi che sono presenti nella
	 * collezione specificata. Restituisce {@code true} se la vista (e di conseguenza la mappa) è stata modificata,
	 * {@code false} altrimenti. Lancia {@code NullPointerException} se la collezione passata è {@code null}.
	 * Lancia {@code ClassCastException} se la vista contiene elementi che non sono compatibili con la collezione
	 * passata come parametro.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code removeAll(HCollection c)} funzioni correttamente quando gli viene passata
	 * una collezione non vuota su una vista vuota. Il metodo non dovrebbe lanciare eccezioni e dovrebbe
	 * restituire {@code false} in quanto non ci sono entry rimovibili dalla vista.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
	 * collegata a una mappa vuota. Viene creata una nuova mappa a cui viene inserita una generica entry per
	 * ottenere una collezione non vuota da passare come parametro al metodo {@code removeAll(HCollection c)}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code removeAll(HCollection c)} è stato chiamato con una collezione non vuota e non ha lanciato
	 * eccezioni.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code removeAll(HCollection c)} dovrebbe restituire {@code false}, inoltre la vista (e di conseguenza
	 * la mappa) dovrebbe rimanere vuota e con 0 elementi.
	 */
	@Test
	public void testRemoveAllPopulatedCollectionOnEmptyValuesCollection() {
		MapAdapter newPopulatedMap = new MapAdapter();
		newPopulatedMap.put("key1", "value1"); // aggiunge una entry generica
		HCollection populatedCollection = newPopulatedMap.values();
		assertFalse("removeAll dovrebbe restituire false se la collezione è non vuota", values.removeAll(populatedCollection));
		assertTrue("La vista dovrebbe rimanere vuota", values.isEmpty());
		assertEquals("La vista dovrebbe rimanere con 0 elementi", 0, values.size());
		assertTrue("La mappa associata alla vista dovrebbe rimanere vuota", map.isEmpty());
		assertEquals("La mappa associata alla vista dovrebbe rimanere con 0 elementi", 0, map.size());
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionAdapter#removeAll(HCollection c)} su una vista vuota con collezione di tipo non compatibile.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code removeAll(HCollection c)} rimuove dalla vista tutti gli elementi che sono presenti nella
	 * collezione specificata. Restituisce {@code true} se la vista (e di conseguenza la mappa) è stata modificata,
	 * {@code false} altrimenti. Lancia {@code NullPointerException} se la collezione passata è {@code null}.
	 * Lancia {@code ClassCastException} se la vista contiene elementi che non sono compatibili con la collezione
	 * passata come parametro.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code removeAll(HCollection c)} non lanci {@code ClassCastException} se la collezione
	 * passata non supporta i tipi degli elementi della vista. In questo caso gli passo una {@code entrySet()}.
	 * Non lancia ClassCastException, siccome la vista è vuota e non ci sono elementi incompatibili con la collezione
	 * passata.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
	 * collegata a una mappa vuota. Viene creato un nuovo riferimento di tipo {@code HCollection} attraverso il
	 * metodo {@code entrySet()} per generare una collezione che non supporta i tipi degli elementi della vista.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code removeAll(HCollection c)} è stato chiamato con una collezione che non supporta i tipi degli
	 * elementi della vista e ha restituito {@code false}, senza lanciare eccezioni.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code removeAll(HCollection c)} dovrebbe restituire {@code false}.
	 */
	@Test
	public void testRemoveAllWithClassCastExceptionOnEmptyValuesCollection() {
		MapAdapter newPopulatedMap = new MapAdapter();
		newPopulatedMap.put("key1", "value1"); // aggiunge una entry generica
		HCollection entrySet = newPopulatedMap.entrySet(); // ottiene l'entrySet della mappa
		assertFalse("removeAll dovrebbe restituire false se la vista è vuota, anche se gli elementi della vista non sono compatibili con la collezione passata", values.removeAll(entrySet));
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionAdapter#retainAll(HCollection c)} su una vista vuota con collezione vuota.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code retainAll(HCollection c)} rimuove dalla vista tutti gli elementi che non sono presenti nella
	 * collezione specificata. Restituisce {@code true} se la vista (e di conseguenza la mappa) è stata modificata,
	 * {@code false} altrimenti. Lancia {@code NullPointerException} se la collezione passata è {@code null}.
	 * Lancia {@code ClassCastException} se la vista contiene elementi che non sono compatibili con la collezione
	 * passata come parametro.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code retainAll(HCollection c)} funzioni correttamente quando gli viene passata
	 * una collezione vuota su una vista vuota. Il metodo non dovrebbe lanciare eccezioni e dovrebbe
	 * restituire {@code false} in quanto non ci sono entry rimovibili dalla vista.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
	 * collegata a una mappa vuota. Viene creata una nuova mappa vuota per generare una collezione vuota
	 * da passare come parametro al metodo {@code retainAll(HCollection c)}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code retainAll(HCollection c)} è stato chiamato con una collezione vuota e non ha lanciato
	 * eccezioni.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code retainAll(HCollection c)} dovrebbe restituire {@code false}, inoltre la vista (e di conseguenza
	 * la mappa) dovrebbe rimanere vuota e con 0 elementi.
	 */
	@Test
	public void testRetainAllEmptyCollectionOnEmptyValuesCollection() {
		HCollection emptyCollection = new MapAdapter().values();
		assertFalse("retainAll dovrebbe restituire false se la collezione è vuota", values.retainAll(emptyCollection));
		assertTrue("La vista dovrebbe rimanere vuota", values.isEmpty());
		assertEquals("La vista dovrebbe rimanere con 0 elementi", 0, values.size());
		assertTrue("La mappa associata alla vista dovrebbe rimanere vuota", map.isEmpty());
		assertEquals("La mappa associata alla vista dovrebbe rimanere con 0 elementi", 0, map.size());
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionAdapter#retainAll(HCollection c)} su una vista vuota con collezione {@code null}.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code retainAll(HCollection c)} rimuove dalla vista tutti gli elementi che non sono presenti nella
	 * collezione specificata. Restituisce {@code true} se la vista (e di conseguenza la mappa) è stata modificata,
	 * {@code false} altrimenti. Lancia {@code NullPointerException} se la collezione passata è {@code null}.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code retainAll(HCollection c)} lanci una {@code NullPointerException} quando gli viene passata
	 * una collezione {@code null}.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
	 * collegata a una mappa vuota. Viene creato un nuovo riferimento di tipo {@code HCollection} per generare una
	 * collezione {@code null} da passare come parametro al metodo {@code retainAll(HCollection c)}.
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
	public void testRetainAllNullCollectionOnEmptyValuesCollection() {
		HCollection nullCollection = null;
		values.retainAll(nullCollection); // lancia NullPointerException
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionAdapter#retainAll(HCollection c)} su una vista vuota con collezione popolata.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code retainAll(HCollection c)} rimuove dalla vista tutti gli elementi che non sono presenti nella
	 * collezione specificata. Restituisce {@code true} se la vista (e di conseguenza la mappa) è stata modificata,
	 * {@code false} altrimenti. Lancia {@code NullPointerException} se la collezione passata è {@code null}.
	 * Lancia {@code ClassCastException} se la vista contiene elementi che non sono compatibili con la collezione
	 * passata come parametro.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code retainAll(HCollection c)} funzioni correttamente quando gli viene passata
	 * una collezione non vuota su una vista vuota. Il metodo non dovrebbe lanciare eccezioni e dovrebbe
	 * restituire {@code false} in quanto non ci sono entry rimovibili dalla vista.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
	 * collegata a una mappa vuota. Viene creata una nuova mappa a cui viene inserita una generica entry per
	 * ottenere una collezione non vuota da passare come parametro al metodo {@code retainAll(HCollection c)}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code retainAll(HCollection c)} è stato chiamato con una collezione non vuota e non ha lanciato
	 * eccezioni.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code retainAll(HCollection c)} dovrebbe restituire {@code false}, inoltre la vista (e di conseguenza
	 * la mappa) dovrebbe rimanere vuota e con 0 elementi.
	 */
	@Test
	public void testRetainAllPopulatedCollectionOnEmptyValuesCollection() {
		MapAdapter newPopulatedMap = new MapAdapter();
		newPopulatedMap.put("key1", "value1"); // aggiunge una entry generica
		HCollection populatedCollection = newPopulatedMap.values();
		assertFalse("retainAll dovrebbe restituire false se la collezione è non vuota", values.retainAll(populatedCollection));
		assertTrue("La vista dovrebbe rimanere vuota", values.isEmpty());
		assertEquals("La vista dovrebbe rimanere con 0 elementi", 0, values.size());
		assertTrue("La mappa associata alla vista dovrebbe rimanere vuota", map.isEmpty());
		assertEquals("La mappa associata alla vista dovrebbe rimanere con 0 elementi", 0, map.size());
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionAdapter#retainAll(HCollection c)} su una vista vuota con collezione di tipo non compatibile.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code retainAll(HCollection c)} rimuove dalla vista tutti gli elementi che non sono presenti nella
	 * collezione specificata. Restituisce {@code true} se la vista (e di conseguenza la mappa) è stata modificata,
	 * {@code false} altrimenti. Lancia {@code NullPointerException} se la collezione passata è {@code null}.
	 * Lancia {@code ClassCastException} se la vista contiene elementi che non sono compatibili con la collezione
	 * passata come parametro.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code retainAll(HCollection c)} non lanci {@code ClassCastException} se la collezione
	 * passata non supporta i tipi degli elementi della vista. In questo caso gli passo una {@code entrySet()}.
	 * Non lancia ClassCastException, siccome la vista è vuota e non ci sono elementi incompatibili con la collezione
	 * passata.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
	 * collegata a una mappa vuota. Viene creato un nuovo riferimento di tipo {@code HCollection} attraverso il
	 * metodo {@code entrySet()} per generare una collezione che non supporta i tipi degli elementi della vista.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code retainAll(HCollection c)} è stato chiamato con una collezione che non supporta i tipi degli
	 * elementi della vista e ha restituito {@code false}, senza lanciare eccezioni.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code retainAll(HCollection c)} dovrebbe restituire {@code false}.
	 */
	@Test
	public void testRetainAllWithClassCastExceptionOnEmptyValuesCollection() {
		MapAdapter newPopulatedMap = new MapAdapter();
		newPopulatedMap.put("key1", "value1"); // aggiunge una entry generica
		HCollection entrySet = newPopulatedMap.entrySet(); // ottiene l'entrySet della mappa
		assertFalse("retainAll dovrebbe restituire false se la vista è vuota, anche se gli elementi della vista non sono compatibili con la collezione passata", values.retainAll(entrySet));
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionAdapter#clear()} su una vista vuota.
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
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
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
	public void testClearOnEmptyValuesCollection() {
		values.clear();
		assertTrue("Clear dovrebbe rendere la vista vuota", values.isEmpty());
		assertEquals("Clear dovrebbe lasciare 0 elementi nella vista", 0, values.size());
		assertTrue("La mappa associata alla vista dovrebbe essere vuota", map.isEmpty());
		assertEquals("La mappa associata alla vista dovrebbe avere 0 elementi", 0, map.size());
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionAdapter#hashCode()} su una vista vuota.
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
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
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
		HCollection newEmptyValues = new MapAdapter().values();
		assertEquals("L'hash code della mappa vuota dovrebbe essere 0", 0, values.hashCode());
		assertTrue("L'hash code di due mappe vuote dovrebbe essere lo stesso", values.hashCode() == newEmptyValues.hashCode());
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionAdapter#equals(Object o)} su una vista vuota con oggetto {@code null}.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code equals(Object o)} verifica se l'oggetto passato come parametro è uguale alla vista
	 * {@code values} corrente. Due viste {@code values} sono considerate uguali se sono entrambe delle istanze della
	 * classe {@code ValuesCollectionAdapter} e contengono gli stessi elementi (stessi oggetti valore). Restituisce
	 * {@code false} se il parametro è {@code null}, se il parametro non è una {@code ValuesCollectionAdapter} o se
	 * contengono elementi diversi.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code equals(Object o)} restituisca {@code false} quando gli viene passato {@code null}.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
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
	public void testEqualsNullOnEmptyValuesCollection() {
		assertFalse("equals dovrebbe restituire false se gli viene passato null", values.equals(null));
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionAdapter#equals(Object o)} su una vista vuota con oggetto di tipo diverso.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code equals(Object o)} verifica se l'oggetto passato come parametro è uguale alla vista
	 * {@code values} corrente. Due viste {@code values} sono considerate uguali se sono entrambe delle istanze della
	 * classe {@code ValuesCollectionAdapter} e contengono gli stessi elementi (stessi oggetti valore). Restituisce
	 * {@code false} se il parametro è {@code null}, se il parametro non è una {@code ValuesCollectionAdapter} o se
	 * contengono elementi diversi.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code equals(Object o)} restituisca {@code false} quando gli viene passato un oggetto
	 * che non è una istanza di {@code ValuesCollectionAdapter}.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
	 * collegata a una mappa vuota. Viene creato un nuovo oggetto di tipo {@code String} (non {@code ValuesCollectionAdapter})
	 * da passare come parametro al metodo {@code equals(Object o)}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code equals(Object o)} è stato chiamato con un parametro non {@code ValuesCollectionAdapter}
	 * e ha restituito {@code false}.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code equals(Object o)} dovrebbe restituire {@code false}.
	 */
	@Test
	public void testEqualsNotValuesCollectionOnEmptyValuesCollection() {
		String notAValuesCollection = "This is not a ValuesCollection";
		assertFalse("equals dovrebbe restituire false se gli viene passato un oggetto che non è una ValuesCollection", values.equals(notAValuesCollection));
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionAdapter#equals(Object o)} su una vista vuota con altre viste.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code equals(Object o)} verifica se l'oggetto passato come parametro è uguale alla vista
	 * {@code values} corrente. Due viste {@code values} sono considerate uguali se sono entrambe delle istanze della
	 * classe {@code ValuesCollectionAdapter} e contengono gli stessi elementi (stessi oggetti valore). Restituisce
	 * {@code false} se il parametro è {@code null}, se il parametro non è una {@code ValuesCollectionAdapter} o se
	 * contengono elementi diversi.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code equals(Object o)} restituisca {@code false} quando gli viene passata un'altra
	 * view (di tipo {@code KeySetAdapter} o {@code EntrySetAdapter}) sempre collegata a una mappa vuota.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
	 * collegata a una mappa vuota. Vengono creati due nuovi oggetti di tipo {@code KeySetAdapter} e
	 * {@code EntrySetAdapter} da passare come parametro al metodo {@code equals(Object o)}, provenienti da una
	 * nuova mappa vuota.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code equals(Object o)} è stato chiamato con un'altra vista non {@code ValuesCollectionAdapter} come parametro
	 * e ha restituito {@code false}.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code equals(Object o)} dovrebbe restituire {@code false}.
	 */
	@Test
	public void testEqualsOtherViewsOnEmptyValuesCollection() {
		HSet keySetView = new MapAdapter().keySet();
		HSet entrySetView = new MapAdapter().entrySet();
		assertFalse("equals dovrebbe restituire false se gli viene passato un oggetto che non è una ValuesCollection", values.equals(keySetView));
		assertFalse("equals dovrebbe restituire false se gli viene passato un oggetto che non è una ValuesCollection", values.equals(entrySetView));
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionAdapter#equals(Object o)} su una vista vuota con vista vuota.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code equals(Object o)} verifica se l'oggetto passato come parametro è uguale alla vista
	 * {@code values} corrente. Due viste {@code values} sono considerate uguali se sono entrambe delle istanze della
	 * classe {@code ValuesCollectionAdapter} e contengono gli stessi elementi (stessi oggetti valore). Restituisce
	 * {@code false} se il parametro è {@code null}, se il parametro non è una {@code ValuesCollectionAdapter} o se
	 * contengono elementi diversi.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code equals(Object o)} restituisca {@code true} quando gli viene passata un'altra
	 * istanza di {@code ValuesCollectionAdapter} proveniente da una nuova mappa vuota.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
	 * collegata a una mappa vuota. Viene creata una nuova istanza di {@code ValuesCollectionAdapter} collegata a una
	 * mappa vuota da passare come parametro al metodo {@code equals(Object o)}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code equals(Object o)} è stato chiamato con una nuova istanza di {@code ValuesCollectionAdapter}
	 * collegata a una mappa vuota.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code equals(Object o)} dovrebbe restituire {@code true}.
	 */
	@Test
	public void testEqualsNewEmptyValuesCollectionOnEmptyValuesCollection() {
		HCollection newEmptyValuesCollection = new MapAdapter().values();
		assertTrue("equals dovrebbe restituire true se gli viene passato un'altro valuesCollection vuoto", values.equals(newEmptyValuesCollection));
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionAdapter#equals(Object o)} su una vista vuota con vista popolata.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code equals(Object o)} verifica se l'oggetto passato come parametro è uguale alla vista
	 * {@code values} corrente. Due viste {@code values} sono considerate uguali se sono entrambe delle istanze della
	 * classe {@code ValuesCollectionAdapter} e contengono gli stessi elementi (stessi oggetti valore). Restituisce
	 * {@code false} se il parametro è {@code null}, se il parametro non è una {@code ValuesCollectionAdapter} o se
	 * contengono elementi diversi.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code equals(Object o)} restituisca {@code true} quando gli viene passata un'altra
	 * istanza di {@code ValuesCollectionAdapter} proveniente da una nuova mappa non vuota.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
	 * collegata a una mappa vuota. Viene creata una nuova istanza di {@code ValuesCollectionAdapter} collegata a una
	 * mappa popolata con un valore generico da passare come parametro al metodo {@code equals(Object o)}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code equals(Object o)} è stato chiamato con una nuova istanza di {@code ValuesCollectionAdapter} collegata
	 * ad una mappa non vuota.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code equals(Object o)} dovrebbe restituire {@code false}.
	 */
	@Test
	public void testEqualsPopulatedValuesCollectionOnEmptyValuesCollection() {
		MapAdapter populatedMap = new MapAdapter();
		populatedMap.put("key", "value");
		HCollection populatedValues = populatedMap.values();
		assertFalse("equals dovrebbe restituire false se gli viene passata una ValuesCollection con entry diverse", values.equals(populatedValues));
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionAdapter#equals(Object o)} su una vista vuota con sé stessa.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code equals(Object o)} verifica se l'oggetto passato come parametro è uguale alla vista
	 * {@code values} corrente. Due viste {@code values} sono considerate uguali se sono entrambe delle istanze della
	 * classe {@code ValuesCollectionAdapter} e contengono gli stessi elementi (stessi oggetti valore). Restituisce
	 * {@code false} se il parametro è {@code null}, se il parametro non è una {@code ValuesCollectionAdapter} o se
	 * contengono elementi diversi.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code equals(Object o)} restituisca {@code true} quando gli viene passata la stessa
	 * istanza di {@code ValuesCollectionAdapter} su cui è stato invocato il metodo.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
	 * collegata a una mappa vuota.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code equals(Object o)} è stato chiamato con la stessa istanza di {@code ValuesCollectionAdapter} su cui è stato
	 * invocato il metodo.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code equals(Object o)} dovrebbe restituire {@code true}.
	 */
	@Test
	public void testEqualsSelfOnEmptyValuesCollection() {
		assertTrue("equals dovrebbe restituire true se gli viene passato sé stesso", values.equals(values));
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionAdapter#toString()} su una vista vuota.
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
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
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
		assertEquals("La rappresentazione in stringa di una mappa vuota dovrebbe essere []", "[]", values.toString());
	}


/* ================================================================================================================= *\
|* =============================== Test dei metodi dell'iteratore della vista values =============================== *|
\* ================================================================================================================= */

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionIterator#hasNext()} su un iteratore di una vista vuota.
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
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
	 * collegata a una mappa vuota e dell'iteratore {@code ValuesCollectionIterator} associato alla vista.
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
	public void testHasNextOnEmptyValuesCollectionIterator() {
		assertFalse("hasNext dovrebbe restituire false per un iteratore associato a una vista vuota", valuesIterator.hasNext());
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionIterator#next()} su un iteratore di una vista vuota.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code next()} restituisce l'elemento successivo nell'iteratore. Per un iteratore associato a una
	 * vista vuota, dovrebbe lanciare un'eccezione {@code java.util.NoSuchElementException} in quanto non c'è nessun
	 * elemento da estrarre e il corrispettivo {@code hasNext()} dovrebbe restituire {@code false} (come verificato
	 * nel test {@link #testHasNextOnEmptyValuesCollectionIterator()}).
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code next()} generi un'eccezione {@code java.util.NoSuchElementException} per un
	 * iteratore associato a una vista vuota.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
	 * collegata a una mappa vuota e dell'iteratore {@code ValuesCollectionIterator} associato alla vista.
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
	public void testNextOnEmptyValuesCollectionIterator() {
		valuesIterator.next(); // lancia java.util.NoSuchElementException
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter.ValuesCollectionIterator#remove()} su un iteratore di una vista vuota.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code remove()} rimuove l'elemento appena restituito dal metodo {@code next()} dell'iteratore. Per
	 * un iteratore associato a una vista vuota, dovrebbe lanciare un'eccezione {@code myAdapter.IllegalStateException}
	 * in quanto non c'è nessun elemento da rimuovere e il corrispettivo {@code next()} non si può chiamare perché
	 * lancia un'eccezione (come verificato nel test {@link #testNextOnEmptyValuesCollectionIterator()}).
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code remove()} generi un'eccezione {@code myAdapter.IllegalStateException} per un
	 * iteratore associato a una vista vuota.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della vista {@code ValuesCollectionAdapter}
	 * collegata a una mappa vuota e dell'iteratore {@code ValuesCollectionIterator} associato alla vista.
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
	public void testRemoveOnEmptyValuesCollectionIterator() {
		valuesIterator.remove(); // lancia myAdapter.IllegalStateException
	}
}
