package myTest;

// importazione componenti del pacchetto myAdapter da testare
import myAdapter.HMap;
import myAdapter.HCollection;
import myAdapter.HSet;
import myAdapter.MapAdapter;

// importazioni necessarie per JUnit
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * <b>Summary:</b>
 * Classe contenente una suite di test per verificare il comportamento di {@link myAdapter.MapAdapter} quando è vuota.
 * Sono testati tutti i metodi implementati della mappa, assicurando che non lancino eccezioni inattese e restituiscano
 * risultati corretti.
 * 
 * <p>
 * <b>Test Case Design:</b>
 * 
 * <p>
 * La motivazione di questa suite è garantire che {@link myAdapter.MapAdapter} gestisca correttamente i casi limite e
 * le operazioni su una mappa vuota, prima di procedere con test su mappe non vuote.
 * 
 * <p>
 * Non esiste un ordinamento specifico dei test. Nella loro implementazione all'interno del file sorgente sono comunque
 * ordinati secondo l'ordinamento dei corrispettivi metodi testati nella documentazione della classe {@link myAdapter.MapAdapter}.
 *
 * <p>
 * <b>Dependencies:</b>
 * <ul>
 *   <li>File JUnit.jar: {@code ./JUnit/junit-4.13.jar} - versione {@code 4.13}
 *   <li>File Hamcrest.jar: {@code ./JUnit/hamcrest-core-1.3.jar} - versione {@code 1.3}
 * </ul>
 */
public class TestEmptyMapAdapter {
	// variabile di istanza per la mappa da testare
	private MapAdapter map;

	/**
	 * Costruttore predefinito per la classe {@code TestEmptyMapAdapter}.
	 * Questa classe è un'utility e non richiede un'inizializzazione di stato complessa.
	 */
	public TestEmptyMapAdapter() {
		// intentionally left empty
	}

	/**
	 * Metodo di setup che viene eseguito prima di ogni test.
	 * Inizializza un'istanza di MapAdapter vuota.
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
	 * <b>Summary:</b>
	 * Test del costruttore predefinito {@link myAdapter.MapAdapter#MapAdapter()}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il costruttore predefinito di {@code MapAdapter} dovrebbe creare una nuova mappa vuota. Essendo un costruttore
	 * di default, non ha argomenti. Il fatto che la nuova mappa sia vuota e che non contenga nessun elemento viene
	 * testato nei metodi {@code testIsEmptyOnNewMap()} e {@code testSizeOnNewMap()}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il costruttore predefinito di {@code MapAdapter} funzioni correttamente creando una nuova
	 * istanza diversa da {@code null}, della classe {@code MapAdapter}.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa vuota.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * È stata creata un'istanza valida della classe {@code MapAdapter}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * La nuova istanza creata è diversa da {@code null} ed è un'istanza di {@code MapAdapter}.
	 */
	@Test
	public void testDefaultConstructor() {
		assertNotNull("La nuova istanza di MapAdapter dovrebbe essere diversa da null", map);
		assertTrue("La nuova mappa dovrebbe essere una istanza di MapAdapter", map instanceof MapAdapter);
	}

	/**
	 * <b>Summary:</b>
	 * Test del costruttore di copia {@link myAdapter.MapAdapter#MapAdapter(HMap m)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il costruttore di copia di {@code MapAdapter} crea una nuova mappa uguale a quella indicata come parametro.
	 * Le due mappe sono indipendenti, per cui i cambiamenti che avvengono su una, non si applicano all'altra.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il costruttore di copia di {@code MapAdapter} funzioni correttamente creando una nuova istanza
	 * diversa da {@code null}, appartenente alla classe {@code MapAdapter} e che rappresenta una copia della mappa
	 * passata come parametro.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa vuota.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * È stata creata un'istanza valida della classe {@code MapAdapter} che è una copia della mappa passata come
	 * parametro.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * La copia creata è diversa da {@code null}, è un'istanza di {@code MapAdapter}, è vuota come la mappa di partenza,
	 * contiene 0 elementi come la mappa di partenza ed è uguale alla mappa di partenza.
	 */
	@Test
	public void testCopyConstructorOnEmptyMap() {
		MapAdapter copy = new MapAdapter(map);
		assertNotNull("La copia dovrebbe essere diversa da null", copy);
		assertTrue("La copia dovrebbe essere una istanza di MapAdapter", copy instanceof MapAdapter);
		assertEquals("La copia dovrebbe avere la stessa dimensione della mappa originale", map.size(), copy.size());
		assertEquals("La copia dovrebbe essere vuota come la mappa originale", map.isEmpty(), copy.isEmpty());
		assertTrue("La copia dovrebbe essere uguale alla mappa originale", map.equals(copy));
	}

	/**
	 * <b>Summary:</b>
	 * Test del costruttore di copia {@link myAdapter.MapAdapter#MapAdapter(HMap m)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il costruttore di copia di {@code MapAdapter} crea una nuova mappa uguale a quella indicata come parametro.
	 * Se il parametro passato è {@code null}, il costruttore dovrebbe lanciare una {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il costruttore di copia di {@code MapAdapter} gestisca correttamente il caso in cui la mappa passata
	 * come parametro sia {@code null} e non tenti di creare una nuova istanza di {@code MapAdapter}, ma lanci invece
	 * una {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Nessuna, le operazioni di inizializzazione del metodo {@code setUp()} non sono necessarie in questo test.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Non viene creata la copia della mappa passata come parametro.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * La chiamata al costruttore di copia lancia una {@code NullPointerException}.
	 */
	@Test(expected = NullPointerException.class)
	public void testCopyConstructorOnNullMap() {
		MapAdapter nullMap = null;
		MapAdapter copy = new MapAdapter(nullMap); // lancia NullPointerException
		assertNull("La copia non dovrebbe essere creata", copy); // questa riga non dovrebbe mai essere raggiunta
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter#size()}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code size()} restituisce il numero di elementi presenti nella mappa.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code size()} restituisca 0, ovvero il numero di elementi presenti in una mappa
	 * vuota.
	 * 
	 *<p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter}
	 * che rappresenta una mappa vuota.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code size()} è stato chiamato su una mappa vuota e ha restituito 0.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code size()} dovrebbe restituire 0.
	 */
	@Test
	public void testSizeOnEmptyMap() {
		assertEquals("La mappa dovrebbe avere 0 elementi", 0, map.size());
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter#isEmpty()}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code isEmpty()} restituisce {@code true} se la mappa è vuota, {@code false} altrimenti.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code isEmpty()} restituisca {@code true} per una mappa vuota.
	 * 
	 *<p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa vuota.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code isEmpty()} è stato chiamato su una mappa vuota e ha restituito {@code true}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code isEmpty()} dovrebbe restituire {@code true}.
	 */
	@Test
	public void testIsEmptyOnEmptyMap() {
		assertTrue("La mappa dovrebbe essere vuota", map.isEmpty());
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter#containsKey(Object key)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code containsKey(Object key)} restituisce {@code true} se la mappa contiene la chiave specificata,
	 * {@code false} altrimenti. Se la chiave passata è {@code null}, il metodo dovrebbe restituire {@code false}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code containsKey(Object key)} restituisca {@code false} quando si prova a cercare una
	 * generica chiave diversa da {@code null} in una mappa vuota.
	 * 
	 *<p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa vuota.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code containsKey(Object key)} è stato chiamato su una mappa vuota e ha restituito {@code false}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code containsKey(Object key)} dovrebbe restituire {@code false}.
	 */
	@Test
	public void testContainsKeyOnEmptyMap() {
		assertFalse("La mappa non dovrebbe contenere nessuna chiave", map.containsKey("a_random_key"));
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter#containsKey(Object key)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code containsKey(Object key)} restituisce {@code true} se la mappa contiene la chiave specificata,
	 * {@code false} altrimenti. Se la chiave passata è {@code null}, il metodo dovrebbe lanciare una
	 * {@code NullPointerException}.
	 * 
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code containsKey(Object key)} lanci una {@code NullPointerException} quando si
	 * prova a cercare una chiave {@code null} in una mappa vuota.
	 *
	 *<p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa vuota.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code containsKey(Object key)} è stato chiamato con {@code null} come chiave da cercare su una mappa
	 * vuota e ha lanciato una {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code containsKey(Object key)} dovrebbe lanciare una {@code NullPointerException}.
	 */
	@Test(expected = NullPointerException.class)
	public void testContainsNullKeyOnEmptyMap() {
		map.containsKey(null); // lancia NullPointerException
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter#containsValue(Object value)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code containsValue(Object value)} restituisce {@code true} se la mappa contiene il valore specificato,
	 * {@code false} altrimenti. Se il valore passato è {@code null}, il metodo dovrebbe dovrebbe lanciare una
	 * {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code containsValue(Object value)} restituisca {@code false} quando si prova a cercare un
	 * generico valore diverso da {@code null} in una mappa vuota.
	 * 
	 *<p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa vuota.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code containsValue(Object value)} è stato chiamato su una mappa vuota e ha restituito {@code false}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code containsValue(Object value)} dovrebbe restituire {@code false}.
	 */
	@Test
	public void testContainsValueOnEmptyMap() {
		assertFalse("La mappa non dovrebbe contenere nessun valore", map.containsValue("a_random_value"));
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter#containsValue(Object value)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code containsValue(Object value)} restituisce {@code true} se la mappa contiene il valore specificato,
	 * {@code false} altrimenti. Se il valore passato è {@code null}, il metodo dovrebbe dovrebbe lanciare una
	 * {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code containsValue(Object value)} lanci una {@code NullPointerException} quando si
	 * prova a cercare un valore {@code null} in una mappa vuota.
	 *
	 *<p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa vuota.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code containsValue(Object value)} è stato chiamato con {@code null} come valore da cercare su una mappa
	 * vuota e ha lanciato una {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code containsValue(Object value)} dovrebbe lanciare una {@code NullPointerException}.
	 */
	@Test(expected = NullPointerException.class)
	public void testContainsNullValueOnEmptyMap() {
		map.containsValue(null); // lancia NullPointerException
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter#get(Object key)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code get(Object key)} restituisce il valore associato alla chiave specificata, oppure {@code null}
	 * se la chiave non è presente nella mappa. Se la chiave passata è {@code null}, il metodo dovrebbe lanciare una
	 * {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code get(Object key)} restituisca {@code null} quando si prova a cercare una
	 * generica chiave diversa da {@code null} in una mappa vuota.
	 * 
	 *<p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa vuota.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code get(Object key)} è stato chiamato su una mappa vuota e ha restituito {@code null}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code get(Object key)} dovrebbe restituire {@code null}.
	 */
	@Test
	public void testGetOnEmptyMap() {
		assertNull("La mappa non dovrebbe contenere nessun valore", map.get("a_random_key"));
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter#get(Object key)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code get(Object key)} restituisce il valore associato alla chiave specificata, oppure {@code null}
	 * se la chiave non è presente nella mappa. Se la chiave passata è {@code null}, il metodo dovrebbe lanciare una
	 * {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code get(Object key)} lanci una {@code NullPointerException} quando si prova a cercare
	 * una chiave {@code null} in una mappa vuota.
	 *
	 *<p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa vuota.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code get(Object key)} è stato chiamato con {@code null} come chiave da cercare su una mappa vuota
	 * e ha lanciato una {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code get(Object key)} dovrebbe lanciare una {@code NullPointerException}.
	 */
	@Test(expected = NullPointerException.class)
	public void testGetNullKeyOnEmptyMap() {
		map.get(null); // lancia NullPointerException
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter#put(Object key, Object value)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code put(Object key, Object value)} inserisce una nuova entry contenente la chiave e il valore passati
	 * come parametro e restituisce il valore precedentemente associato alla chiave, oppure {@code null} se la chiave non
	 * era presente nella mappa. Se la chiave o il valore passati sono {@code null}, il metodo dovrebbe lanciare una
	 * {@code NullPointerException}. Se la chiave corrisponde alla mappa su cui è stato chiamato il metodo, il metodo
	 * dovrebbe lanciare una {@code IllegalArgumentException}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code put(Object key, Object value)} inserisca correttamente una nuova entry nella mappa
	 * e restituisca {@code null} come valore precedentemente associato alla chiave.
	 *
	 *<p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa vuota.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code put(Object key, Object value)} è stato chiamato con chiave e valore validi e ha inserito
	 * correttamente una nuova entry nella mappa.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * <ul>
	 * <li>il metodo {@code put(Object key, Object value)} dovrebbe restituire {@code null} come valore precedentemente
	 * associato alla chiave</li>
	 * <li>la mappa dovrebbe non essere vuota</li>
	 * <li>la mappa dovrebbe avere dimensione 1</li>
	 * <li>il metodo {@code get(Object key)} dovrebbe restituire il valore associato alla chiave appena inserita</li>
	 * <li>il metodo {@code containsKey(Object key)} dovrebbe restituire true per la chiave appena inserita</li>
	 * <li>il metodo {@code containsValue(Object value)} dovrebbe restituire true per il valore appena inserito</li>
	 * </ul>
	 */
	@Test
	public void testPutOnEmptyMap() {
		assertNull("La mappa non dovrebbe contenere nessun valore associato alla chiave", map.put("a_random_key", "a_random_value"));
		assertFalse("La mappa dovrebbe non essere vuota dopo l'inserimento di una entry", map.isEmpty());
		assertEquals("La mappa dovrebbe avere dimensione 1 dopo l'inserimento di una entry", 1, map.size());
		assertEquals("Il metodo get(Object key) dovrebbe restituire il valore associato alla chiave", "a_random_value", map.get("a_random_key"));
		assertTrue("Il metodo containsKey(Object key) dovrebbe restituire true per la chiave appena inserita", map.containsKey("a_random_key"));
		assertTrue("Il metodo containsValue(Object value) dovrebbe restituire true per il valore appena inserito", map.containsValue("a_random_value"));
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter#put(Object key, Object value)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code put(Object key, Object value)} inserisce una nuova entry contenente la chiave e il valore passati
	 * come parametro e restituisce il valore precedentemente associato alla chiave, oppure {@code null} se la chiave non
	 * era presente nella mappa. Se la chiave o il valore passati sono {@code null}, il metodo dovrebbe lanciare una
	 * {@code NullPointerException}. Se la chiave corrisponde alla mappa su cui è stato chiamato il metodo, il metodo
	 * dovrebbe lanciare una {@code IllegalArgumentException}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code put(Object key, Object value)} lanci una {@code NullPointerException} quando
	 * si passa {@code null} come chiave della entry da inserire.
	 *
	 *<p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa vuota.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code put(Object key, Object value)} è stato chiamato con chiave {@code null} e ha lanciato una
	 * {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code put(Object key, Object value)} dovrebbe lanciare una {@code NullPointerException}.
	 */
	@Test(expected = NullPointerException.class)
	public void testPutNullKeyOnEmptyMap() {
		map.put(null, "value"); // lancia NullPointerException
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter#put(Object key, Object value)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code put(Object key, Object value)} inserisce una nuova entry contenente la chiave e il valore passati
	 * come parametro e restituisce il valore precedentemente associato alla chiave, oppure {@code null} se la chiave non
	 * era presente nella mappa. Se la chiave o il valore passati sono {@code null}, il metodo dovrebbe lanciare una
	 * {@code NullPointerException}. Se la chiave corrisponde alla mappa su cui è stato chiamato il metodo, il metodo
	 * dovrebbe lanciare una {@code IllegalArgumentException}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code put(Object key, Object value)} lanci una {@code NullPointerException} quando
	 * si passa {@code null} come valore della entry da inserire.
	 *
	 *<p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa vuota.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code put(Object key, Object value)} è stato chiamato con valore {@code null} e ha lanciato una
	 * {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code put(Object key, Object value)} dovrebbe lanciare una {@code NullPointerException}.
	 */
	@Test(expected = NullPointerException.class)
	public void testPutNullValueOnEmptyMap() {
		map.put("key", null); // lancia NullPointerException
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter#put(Object key, Object value)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code put(Object key, Object value)} inserisce una nuova entry contenente la chiave e il valore passati
	 * come parametro e restituisce il valore precedentemente associato alla chiave, oppure {@code null} se la chiave non
	 * era presente nella mappa. Se la chiave o il valore passati sono {@code null}, il metodo dovrebbe lanciare una
	 * {@code NullPointerException}. Se la chiave corrisponde alla mappa su cui è stato chiamato il metodo, il metodo
	 * dovrebbe lanciare una {@code IllegalArgumentException}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code put(Object key, Object value)} lanci una {@code IllegalArgumentException} quando
	 * si prova ad inserire la mappa su cui si sta chiamando il metodo come chiave della entry da inserire.
	 *
	 *<p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa vuota.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code put(Object key, Object value)} è stato chiamato con la mappa su cui si sta invocando come
	 * chiave della entry da inserire e ha lanciato una {@code IllegalArgumentException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code put(Object key, Object value)} dovrebbe lanciare una {@code IllegalArgumentException}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPutMapAsKeyOnEmptyMap() {
		map.put(map, "value"); // lancia IllegalArgumentException
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter#remove(Object key)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code remove(Object key)} rimuove la entry con chiave specificata e restituisce il valore della entry
	 * rimossa, oppure {@code null} se la chiave non era presente nella mappa. Se la chiave passata è {@code null}, il
	 * metodo dovrebbe lanciare una {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code remove(Object key)} restituisca {@code null} quando si prova a rimuovere una
	 * entry con una generica chiave diversa da {@code null} in una mappa vuota.
	 * 
	 *<p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa vuota.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code remove(Object key)} è stato chiamato su una mappa vuota e ha restituito {@code null}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code remove(Object key)} dovrebbe restituire {@code null}, la dimensione della mappa dovrebbe
	 * rimanere 0 e la mappa dovrebbe rimanere vuota.
	 */
	@Test
	public void testRemoveOnEmptyMap() {
		assertNull("La mappa non dovrebbe contenere nessun valore", map.remove("a_random_key"));
		assertTrue("La mappa dovrebbe essere ancora vuota dopo il tentativo di rimozione", map.isEmpty());
		assertEquals("La mappa dovrebbe avere ancora 0 elementi dopo il tentativo di rimozione", 0, map.size());
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter#remove(Object key)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code remove(Object key)} rimuove la entry con chiave specificata e restituisce il valore della entry
	 * rimossa, oppure {@code null} se la chiave non era presente nella mappa. Se la chiave passata è {@code null}, il
	 * metodo dovrebbe lanciare una {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code remove(Object key)} lanci una {@code NullPointerException} quando si prova a
	 * rimuovere una entry con chiave {@code null} in una mappa vuota.
	 *
	 *<p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa vuota.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code remove(Object key)} è stato chiamato con {@code null} come chiave di entry da rimuovere su una
	 * mappa vuota e ha lanciato una {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code remove(Object key)} dovrebbe lanciare una {@code NullPointerException}.
	 */
	@Test(expected = NullPointerException.class)
	public void testRemoveNullKeyOnEmptyMap() {
		map.remove(null); // lancia NullPointerException
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter#putAll(HMap m)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code putAll(HMap m)} inserisce tutte le entry della mappa passata come parametro nella mappa corrente.
	 * Se la mappa passata è {@code null}, il metodo dovrebbe lanciare una {@code NullPointerException}. Se la mappa
	 * passata contiene entry con chiavi o valori {@code null}, il metodo dovrebbe lanciare una {@code NullPointerException}.
	 * Se la mappa passata contiene la mappa su cui è stato chiamato il metodo come chiave, il metodo dovrebbe lanciare
	 * una {@code IllegalArgumentException}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code putAll(HMap m)} mantenga la mappa invariata quando viene passata una mappa vuota
	 * come parametro.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa vuota. Viene creata una mappa vuota da passare al metodo {@code putAll(HMap m)}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * La mappa dovrebbe rimanere vuota dopo il tentativo di inserimento delle entry della mappa vuota.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code putAll(HMap m)} dovrebbe mantenere la mappa iniziale vuota e con 0 elementi; inoltre siccome la
	 * mappa su cui è stato chiamato il metodo era vuota, le due mappe dovrebbero essere uguali.
	 */
	@Test
	public void testPutAllEmptyMapOnEmptyMap() {
		MapAdapter emptyMap = new MapAdapter();
		map.putAll(emptyMap);
		assertTrue("La mappa dovrebbe rimanere vuota dopo il tentativo di inserimento", map.isEmpty());
		assertEquals("La mappa dovrebbe avere ancora 0 elementi dopo il tentativo di inserimento", 0, map.size());
		assertTrue("La mappa dovrebbe essere uguale alla mappa vuota passata come parametro", map.equals(emptyMap));
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter#putAll(HMap m)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code putAll(HMap m)} inserisce tutte le entry della mappa passata come parametro nella mappa corrente.
	 * Se la mappa passata è {@code null}, il metodo dovrebbe lanciare una {@code NullPointerException}. Se la mappa
	 * passata contiene entry con chiavi o valori {@code null}, il metodo dovrebbe lanciare una {@code NullPointerException}.
	 * Se la mappa passata contiene la mappa su cui è stato chiamato il metodo come chiave, il metodo dovrebbe lanciare
	 * una {@code IllegalArgumentException}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code putAll(HMap m)} mantenga la mappa invariata quando viene passata la stessa mappa su
	 * cui è stato chiamato il metodo, ovvero che la mappa continua ad essere vuota.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa vuota.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * La mappa dovrebbe rimanere vuota dopo il tentativo di inserimento delle entry della stessa mappa su cui è invocato
	 * il metodo, senza lanciare eccezioni.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code putAll(HMap m)} dovrebbe mantenere la mappa iniziale vuota e con 0 elementi, senza lanciare eccezioni.
	 */
	@Test
	public void testSelfPutAllOnEmptyMap() {
		map.putAll(map);
		assertTrue("La mappa dovrebbe rimanere vuota dopo il tentativo di inserimento", map.isEmpty());
		assertEquals("La mappa dovrebbe avere ancora 0 elementi dopo il tentativo di inserimento", 0, map.size());
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter#putAll(HMap m)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code putAll(HMap m)} inserisce tutte le entry della mappa passata come parametro nella mappa corrente.
	 * Se la mappa passata è {@code null}, il metodo dovrebbe lanciare una {@code NullPointerException}. Se la mappa
	 * passata contiene entry con chiavi o valori {@code null}, il metodo dovrebbe lanciare una {@code NullPointerException}.
	 * Se la mappa passata contiene la mappa su cui è stato chiamato il metodo come chiave, il metodo dovrebbe lanciare
	 * una {@code IllegalArgumentException}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code putAll(HMap m)} inserisca correttamente le entry di una mappa non vuota passata come
	 * parametro.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa vuota. Viene creata una nuova mappa da passare al metodo {@code putAll(HMap m)} a cui
	 * vengono aggiunte tre entry con il metodo {@code put(Object key, Object value)}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * La mappa dovrebbe contenere le entry della mappa passata come parametro.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * <ul>
	 * <li>la mappa dovrebbe non essere vuota dopo il tentativo di inserimento</li>
	 * <li>la mappa dovrebbe avere dimensione 3 dopo il tentativo di inserimento</li>
	 * <li>la mappa dovrebbe essere uguale alla mappa non vuota passata come parametro, che contiene 3 entry</li>
	 * <li>la mappa dovrebbe contenere le entry della mappa passata come parametro</li>
	 * </ul>
	 */
	@Test
	public void testPutAllNotEmptyOnEmptyMap() {
		MapAdapter notEmptyMap = new MapAdapter();
		notEmptyMap.put("key1", "value1");
		notEmptyMap.put("key2", "value2");
		notEmptyMap.put("key3", "value3");
		map.putAll(notEmptyMap);
		assertFalse("La mappa non dovrebbe essere vuota dopo il tentativo di inserimento", map.isEmpty());
		assertEquals("La mappa dovrebbe avere dimensione 3 dopo il tentativo di inserimento", 3, map.size());
		assertTrue("La mappa dovrebbe essere uguale alla mappa non vuota passata come parametro", map.equals(notEmptyMap));
		assertEquals("La mappa dovrebbe contenere la entry 'key1-value1'", "value1", map.get("key1"));
		assertEquals("La mappa dovrebbe contenere la entry 'key2-value2'", "value2", map.get("key2"));
		assertEquals("La mappa dovrebbe contenere la entry 'key3-value3'", "value3", map.get("key3"));
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter#putAll(HMap m)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code putAll(HMap m)} inserisce tutte le entry della mappa passata come parametro nella mappa corrente.
	 * Se la mappa passata è {@code null}, il metodo dovrebbe lanciare una {@code NullPointerException}. Se la mappa
	 * passata contiene entry con chiavi o valori {@code null}, il metodo dovrebbe lanciare una {@code NullPointerException}.
	 * Se la mappa passata contiene la mappa su cui è stato chiamato il metodo come chiave, il metodo dovrebbe lanciare
	 * una {@code IllegalArgumentException}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code putAll(HMap m)} lanci una {@code NullPointerException} quando la mappa passata come
	 * parametro è {@code null}.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che rappresenta
	 * una mappa vuota. Viene creata una nuova mappa con riferimento {@code null} da passare al metodo {@code putAll(HMap m)}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code putAll(HMap m)} è stato chiamato con {@code null} come mappa da inserire e ha lanciato una
	 * {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code putAll(HMap m)} dovrebbe lanciare una {@code NullPointerException}.
	 */
	@Test(expected = NullPointerException.class)
	public void testPutAllNullMapOnEmptyMap() {
		MapAdapter nullMap = null;
		map.putAll(nullMap); // lancia NullPointerException
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter#putAll(HMap m)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code putAll(HMap m)} inserisce tutte le entry della mappa passata come parametro nella mappa corrente.
	 * Se la mappa passata è {@code null}, il metodo dovrebbe lanciare una {@code NullPointerException}. Se la mappa
	 * passata contiene entry con chiavi o valori {@code null}, il metodo dovrebbe lanciare una {@code NullPointerException}.
	 * Se la mappa passata contiene la mappa su cui è stato chiamato il metodo come chiave, il metodo dovrebbe lanciare
	 * una {@code IllegalArgumentException}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code putAll(HMap m)} lanci una {@code NullPointerException} quando la mappa passata come
	 * parametro contiene una entry con chiave {@code null}.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa vuota. Viene creata una nuova mappa utilizzando la classe {@code myTest.SimpleHMapWithNulls}
	 * di appositamente creata per questo test da passare al metodo {@code putAll(HMap m)} a cui viene aggiunta una entry
	 * con chiave {@code null}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code putAll(HMap m)} è stato chiamato con una mappa che contiene una entry con chiave {@code null} e ha
	 * lanciato una {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code putAll(HMap m)} dovrebbe lanciare una {@code NullPointerException}.
	 */
	@Test (expected = NullPointerException.class)
	public void testPutAllNullKeyOnEmptyMap() {
		SimpleHMapWithNulls hashmap = new SimpleHMapWithNulls();
		hashmap.put(null, "value"); // aggiunge una entry con chiave null
		map.putAll((HMap) hashmap); // lancia NullPointerException
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter#putAll(HMap m)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code putAll(HMap m)} inserisce tutte le entry della mappa passata come parametro nella mappa corrente.
	 * Se la mappa passata è {@code null}, il metodo dovrebbe lanciare una {@code NullPointerException}. Se la mappa
	 * passata contiene entry con chiavi o valori {@code null}, il metodo dovrebbe lanciare una {@code NullPointerException}.
	 * Se la mappa passata contiene la mappa su cui è stato chiamato il metodo come chiave, il metodo dovrebbe lanciare
	 * una {@code IllegalArgumentException}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code putAll(HMap m)} lanci una {@code NullPointerException} quando la mappa passata come
	 * parametro contiene una entry con valore {@code null}.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa vuota. Viene creata una nuova mappa utilizzando la classe {@code myTest.SimpleHMapWithNulls}
	 * di appositamente creata per questo test da passare al metodo {@code putAll(HMap m)} a cui viene aggiunta una entry
	 * con valore {@code null}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code putAll(HMap m)} è stato chiamato con una mappa che contiene una entry con valore {@code null} e ha
	 * lanciato una {@code NullPointerException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code putAll(HMap m)} dovrebbe lanciare una {@code NullPointerException}.
	 */
	@Test (expected = NullPointerException.class)
	public void testPutAllNullValueOnEmptyMap() {
		SimpleHMapWithNulls hashmap = new SimpleHMapWithNulls();
		hashmap.put("key", null); // aggiunge una entry con valore null
		map.putAll((HMap)hashmap); // lancia NullPointerException
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter#putAll(HMap m)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code putAll(HMap m)} inserisce tutte le entry della mappa passata come parametro nella mappa corrente.
	 * Se la mappa passata è {@code null}, il metodo dovrebbe lanciare una {@code NullPointerException}. Se la mappa
	 * passata contiene entry con chiavi o valori {@code null}, il metodo dovrebbe lanciare una {@code NullPointerException}.
	 * Se la mappa passata contiene la mappa su cui è stato chiamato il metodo come chiave, il metodo dovrebbe lanciare
	 * una {@code IllegalArgumentException}.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code putAll(HMap m)} lanci una {@code IllegalArgumentException}.} quando la mappa passata come
	 * parametro contiene una entry con la mappa su cui è stato chiamato il metodo come chiave.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa vuota. Viene creata una nuova mappa utilizzando la classe {@code myTest.SimpleHMapWithNulls}
	 * di appositamente creata per questo test da passare al metodo {@code putAll(HMap m)} a cui viene aggiunta una entry
	 * con la mappa su cui è invocato il metodo come chiave.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code putAll(HMap m)} è stato chiamato con una mappa che contiene una entry con la mappa su cui è stato
	 * chiamato il metodo come chiave e ha lanciato una {@code IllegalArgumentException}.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code putAll(HMap m)} dovrebbe lanciare una {@code IllegalArgumentException}.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testPutAllSelfContainingMapOnEmptyMap() {
		SimpleHMapWithNulls hashmap = new SimpleHMapWithNulls();
		hashmap.put(map, "value"); // aggiunge una entry con la mappa come chiave
		map.putAll((HMap)hashmap); // lancia IllegalArgumentException
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter#clear()}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code clear()} rimuove tutte le entry dalla mappa, rendendola vuota. Dopo la chiamata a questo metodo,
	 * la mappa dovrebbe essere vuota.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code clear()} invocato su una mappa vuota mantenga la mappa vuota, senza lanciare eccezioni.
	 * 
	 *<p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa vuota.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code clear()} è stato chiamato su una mappa vuota e non ha lanciato eccezioni.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code clear()} dovrebbe mantenere la mappa vuota e con 0 elementi
	 */
	@Test
	public void testClearOnEmptyMap() {
		map.clear();
		assertEquals("Clear dovrebbe lasciare 0 elementi nella mappa", 0, map.size());
		assertTrue("Clear dovrebbe rendere la mappa vuota", map.isEmpty());
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter#keySet()}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code keySet()} restituisce un insieme delle chiavi presenti nella mappa. Per una mappa vuota,
	 * dovrebbe restituire un insieme vuoto. I metodi dell'insieme e dei relativi iterator sono testati in altri
	 * test separati.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code keySet()} restituisca un insieme vuoto quando invocato su una mappa vuota.
	 * 
	 *<p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa vuota.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code keySet()} è stato chiamato su una mappa vuota e ha restituito un insieme vuoto.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * L'oggetto restituito dal metodo {@code keySet()} dovrebbe essere
	 * <ul>
	 * <li>non {@code null}</li>
	 * <li>un'istanza di {@code HSet}, non dovrebbe essere</li>
	 * <li>un insieme vuoto</li>
	 * </ul>
	 */
	@Test
	public void testKeySetOnEmptyMap() {
		assertNotNull("La keySet di una mappa vuota non dovrebbe essere null", map.keySet());
		assertTrue("La keySet di una mappa vuota dovrebbe essere un insieme", map.keySet() instanceof HSet);
		assertTrue("La keySet di una mappa vuota dovrebbe essere vuota", map.keySet().isEmpty());
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter#values()}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code values()} restituisce una collezione dei valori presenti nella mappa. Per una mappa vuota,
	 * dovrebbe restituire una collezione vuota. I metodi della collezione e dei relativi iterator sono testati in altri
	 * test separati.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code values()} restituisca una collezione vuota quando invocato su una mappa vuota.
	 * 
	 *<p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa vuota.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code values()} è stato chiamato su una mappa vuota e ha restituito una collezione vuota.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * L'oggetto restituito dal metodo {@code values()} dovrebbe essere
	 * <ul>
	 * <li>non {@code null}</li>
	 * <li>un'istanza di {@code HCollection}, non dovrebbe essere</li>
	 * <li>una collezione vuota</li>
	 * </ul>
	 */
	@Test
	public void testValuesCollectionOnEmptyMap() {
		assertNotNull("La valuesCollection di una mappa vuota non dovrebbe essere null", map.values());
		assertTrue("La valuesCollection di una mappa vuota dovrebbe essere un insieme",
				map.values() instanceof HCollection);
		assertTrue("La valuesCollection di una mappa vuota dovrebbe essere vuota", map.values().isEmpty());
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter#entrySet()}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code entrySet()} restituisce un insieme delle coppie chiave-valore presenti nella mappa. Per una mappa vuota,
	 * dovrebbe restituire un insieme vuoto. I metodi dell'insieme e dei relativi iterator sono testati in altri
	 * test separati.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code entrySet()} restituisca un insieme vuoto quando invocato su una mappa vuota.
	 * 
	 *<p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa vuota.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code entrySet()} è stato chiamato su una mappa vuota e ha restituito un insieme vuoto.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * L'oggetto restituito dal metodo {@code entrySet()} dovrebbe essere
	 * <ul>
	 * <li>non {@code null}</li>
	 * <li>un'istanza di {@code HSet}, non dovrebbe essere</li>
	 * <li>un insieme vuoto</li>
	 * </ul>
	 */
	@Test
	public void testEntrySetOnEmptyMap() {
		assertNotNull("La entrySet di una mappa vuota non dovrebbe essere null", map.entrySet());
		assertTrue("La entrySet di una mappa vuota dovrebbe essere un insieme", map.entrySet() instanceof HCollection);
		assertTrue("La entrySet di una mappa vuota dovrebbe essere vuota", map.entrySet().isEmpty());
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter#equals(Object o)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code equals(Object o)} confronta la mappa su cui è invocato con un altro oggetto. Se l'oggetto passato
	 * è {@code null} o non è una mappa, il metodo restituisce {@code false}. Se l'oggetto passato è una mappa, il metodo
	 * restituisce {@code true} se e solo se le due mappe contengono le stesse mappature chiave-valore.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code equals(Object o)} restituisca {@code false} quando l'oggetto passato è {@code null}.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa vuota.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code equals(Object o)} è stato chiamato con un oggetto {@code null} e non ha lanciato eccezioni.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code equals(Object o)} dovrebbe restituire {@code false}.
	 */
	@Test
	public void testEqualsNullOnEmptyMap() {
		assertFalse("equals dovrebbe restituire false se gli viene passato null", map.equals(null));
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter#equals(Object o)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code equals(Object o)} confronta la mappa su cui è invocato con un altro oggetto. Se l'oggetto passato
	 * è {@code null} o non è una mappa, il metodo restituisce {@code false}. Se l'oggetto passato è una mappa, il metodo
	 * restituisce {@code true} se e solo se le due mappe contengono le stesse mappature chiave-valore.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code equals(Object o)} restituisca {@code false} quando l'oggetto passato non è una istanza
	 * di {@code MapAdapter}.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa vuota. È stato creato un oggetto di tipo {@code String} da passare al metodo
	 * {@code equals(Object o)}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code equals(Object o)} è stato chiamato con un oggetto di classe {@code String} (diverso da
	 * {@code MapAdapter}) e non ha lanciato eccezioni.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code equals(Object o)} dovrebbe restituire {@code false}.
	 */
	@Test
	public void testEqualsNotMapOnEmptyMap() {
		String notAMap = "This is not a map";
		assertFalse("equals dovrebbe restituire false se gli viene passato un oggetto che non è una mappa",
				map.equals(notAMap));
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter#equals(Object o)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code equals(Object o)} confronta la mappa su cui è invocato con un altro oggetto. Se l'oggetto passato
	 * è {@code null} o non è una mappa, il metodo restituisce {@code false}. Se l'oggetto passato è una mappa, il metodo
	 * restituisce {@code true} se e solo se le due mappe contengono le stesse mappature chiave-valore.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code equals(Object o)} restituisca {@code true} quando l'oggetto passato è una nuova mappa
	 * vuota, ovvero senza entry come quella su cui è invocato il metodo.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa vuota. È stata creata una nuova mappa vuota da passare al metodo {@code equals(Object o)}.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code equals(Object o)} è stato chiamato con una mappa vuota e non ha lanciato eccezioni.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code equals(Object o)} dovrebbe restituire {@code true}.
	 */
	@Test
	public void testEqualsNewEmptyMapOnEmptyMap() {
		MapAdapter newEmptyMap = new MapAdapter();
		assertTrue("equals dovrebbe restituire true se gli viene passato un'altra mappa vuota",
				map.equals(newEmptyMap));
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter#equals(Object o)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code equals(Object o)} confronta la mappa su cui è invocato con un altro oggetto. Se l'oggetto passato
	 * è {@code null} o non è una mappa, il metodo restituisce {@code false}. Se l'oggetto passato è una mappa, il metodo
	 * restituisce {@code true} se e solo se le due mappe contengono le stesse mappature chiave-valore.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code equals(Object o)} restituisca {@code false} quando l'oggetto passato non è una istanza
	 * di {@code MapAdapter}.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa vuota. È stata creata una nuova istanza di {@code MapAdapter} da passare al metodo
	 * {@code equals(Object o)} a cui è stata aggiunta una entry.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code equals(Object o)} è stato chiamato con un'istanza di {@code MapAdapter} non uguale alla mappa su cui
	 * è stato invocato e non ha lanciato eccezioni.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code equals(Object o)} dovrebbe restituire {@code false}.
	 */
	@Test
	public void testEqualsNotEmptyMapOnEmptyMap() {
		MapAdapter populatedMap = new MapAdapter();
		populatedMap.put("key", "value");
		assertFalse("equals dovrebbe restituire false se gli viene passata è una mappa con entry diverse",
				map.equals(populatedMap));
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter#equals(Object o)}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code equals(Object o)} confronta la mappa su cui è invocato con un altro oggetto. Se l'oggetto passato
	 * è {@code null} o non è una mappa, il metodo restituisce {@code false}. Se l'oggetto passato è una mappa, il metodo
	 * restituisce {@code true} se e solo se le due mappe contengono le stesse mappature chiave-valore.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code equals(Object o)} restituisca {@code true} quando l'oggetto passato corrisponde
	 * all'oggetto su cui è invocato, ovvero la stessa mappa.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa vuota. 
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code equals(Object o)} è stato chiamato con lo stesso oggetto su cui è stato invocato e non ha
	 * lanciato eccezioni.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code equals(Object o)} dovrebbe restituire {@code true}.
	 */
	@Test
	public void testEqualsSelfOnEmptyMap() {
		assertTrue("equals dovrebbe restituire true se gli viene passato se stesso", map.equals(map));
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter#hashCode()}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code hashCode()} restituisce un valore intero che rappresenta l'hash code della mappa. Una mappa vuota
	 * dovrebbe restituire un hash code di 0, mentre una mappa con entry dovrebbe restituire un hash code ottenuto dalla
	 * somma degli hash code delle entry.
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code hashCode()} restituisca 0 quando invocato su una mappa vuota.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa vuota. Viene creata una nuova istanza di {@code MapAdapter} per verificare che l'hash code
	 * di due mappe vuote sia lo stesso.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code hashCode()} è stato chiamato su una mappa vuota e ha restituito 0.
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code hashCode()} dovrebbe restituire 0 per una mappa vuota e l'hash code di una nuova mappa vuota
	 * dovrebbe essere uguale a quello della mappa instanziata dal {@code setUp()}.
	 */
	@Test
	public void testHashOnEmptyMap() {
		MapAdapter newMap = new MapAdapter();
		assertEquals("L'hash code della mappa vuota dovrebbe essere 0", 0, map.hashCode());
		assertTrue("L'hash code di due mappe vuote dovrebbe essere lo stesso", map.hashCode() == newMap.hashCode());
	}

	/**
	 * <b>Summary:</b>
	 * Test del metodo {@link myAdapter.MapAdapter#toString()}.
	 * 
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code toString()} restituisce una rappresentazione in stringa della mappa. Per una mappa vuota,
	 * dovrebbe restituire la stringa "{}".
	 * 
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code toString()} restituisca la rappresentazione corretta per una mappa vuota.
	 * 
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa vuota.
	 * 
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code toString()} è stato chiamato su una mappa vuota e ha restituito la stringa "{}".
	 * 
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code toString()} dovrebbe restituire la stringa "{}".
	 */
	@Test
	public void testToStringOnEmptyMap() {
		assertEquals("La rappresentazione in stringa di una mappa vuota dovrebbe essere {}", "{}", map.toString());
	}
}
