// Giacomo Simonetto - 2109923 - Secondo appello 2025 di Elementi di Ingegneria del Software

package myTest;

// importazione componenti del pacchetto myAdapter da testare
import myAdapter.HMap;
import myAdapter.HCollection;
import myAdapter.HIterator;
import myAdapter.HSet;
import myAdapter.MapAdapter;

// importazioni necessarie per JUnit
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * <b>Summary:</b>
 * Classe contenente una suite di test per verificare il comportamento di {@link myAdapter.MapAdapter} quando è popolata.
 * Sono testati tutti i metodi implementati della mappa, assicurando che non lancino eccezioni inattese e restituiscano
 * risultati corretti.
 *
 * <p>
 * <b>Test Case Design:</b>
 * La motivazione di questa suite è garantire che {@link myAdapter.MapAdapter} gestisca correttamente i casi limite e
 * le operazioni su una mappa popolata, ovvero nell'utilizzo concreto di tale struttura.
 *
 * <p>
 * I test sono ordinati secondo l'ordinamento dei corrispettivi metodi testati nella documentazione della classe
 * {@link myAdapter.MapAdapter} e della relativa interfaccia {@link myAdapter.HMap}.
 *
 * <p>
 * Sono stati implementati 36 test in totale per la mappa popolata.
 *
 * <p>
 * <b>Dependencies:</b>
 * <ul>
 *   <li>File {@code JUnit}:    {@code ./JUnit/junit-4.13.jar}        - versione {@code 4.13}
 *   <li>File {@code Hamcrest}: {@code ./JUnit/hamcrest-core-1.3.jar} - versione {@code 1.3}
 * </ul>
 */
public class TestPopulatedMapAdapter {
	// variabile di istanza per la mappa da testare
	private MapAdapter map;

	/**
	 * Costruttore predefinito per la classe {@code TestPopulatedMapAdapter}.
	 * Questa classe è un'utility e non richiede un'inizializzazione di stato complessa.
	 */
	public TestPopulatedMapAdapter() {
		// intentionally left empty
	}

	/**
	 * Metodo di setup che viene eseguito prima di ogni test.
	 * Inizializza un'istanza di MapAdapter e inserisce le seguenti 4 entry: {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 */
	@Before
	public void setUp() {
		map = new MapAdapter();
		map.put("key1", "value1");
		map.put("key2", 2);
		map.put(3, "value3");
		map.put(4, 4);
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
	 * Test del costruttore di copia {@link myAdapter.MapAdapter#MapAdapter(HMap m)}.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il costruttore di copia di {@code MapAdapter} crea una nuova mappa uguale a quella indicata come parametro.
	 * Se il parametro passato è {@code null}, il costruttore dovrebbe lanciare una {@code NullPointerException}.
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
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * È stata creata un'istanza valida della classe {@code MapAdapter} che è una copia della mappa passata come
	 * parametro.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * La copia creata è diversa da {@code null}, è un'istanza di {@code MapAdapter}, non è vuota come la mappa di
	 * partenza, contiene 4 elementi come la mappa di partenza ed è uguale alla mappa di partenza.
	 */
	@Test
	public void testCopyConstructorOnPopulatedMap() {
		MapAdapter copy = new MapAdapter(map);
		assertNotNull("La copia dovrebbe essere diversa da null", copy);
		assertTrue("La copia dovrebbe essere una istanza di MapAdapter", copy instanceof MapAdapter);
		assertEquals("La copia dovrebbe avere la stessa dimensione della mappa originale", map.size(), copy.size());
		assertEquals("La copia dovrebbe essere non vuota come la mappa originale", map.isEmpty(), copy.isEmpty());
		assertTrue("La copia dovrebbe essere uguale alla mappa originale", map.equals(copy));
	}

	/**
	 * <b>Summary:</b>
	 * Test del costruttore di copia {@link myAdapter.MapAdapter#MapAdapter(HMap m)} per verificare che le
	 * modifiche sulla mappa originale non si riflettano sulla copia.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il costruttore di copia di {@code MapAdapter} crea una nuova mappa uguale a quella indicata come parametro.
	 * Se il parametro passato è {@code null}, il costruttore dovrebbe lanciare una {@code NullPointerException}.
	 * Le due mappe sono indipendenti, per cui i cambiamenti che avvengono su una, non si applicano all'altra.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il costruttore di copia di {@code MapAdapter} funzioni correttamente creando una nuova istanza
	 * indipendente dalla mappa di origine. Per fare ciò, sulla mappa di origine inserisco una nuova entry
	 * {@code <1729="value1729">} e rimuovo la entry {@code <3="value3">} e verifico che le modifiche non
	 * si riflettano sulla copia.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * È stata creata un'istanza valida della classe {@code MapAdapter} che è una copia della mappa passata come
	 * parametro. Nella mappa originale è presente la nuova entry {@code <1729="value1729">} e non è più presente
	 * la entry {@code <3="value3">}. La copia non ha subito modifiche.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Le due mappe non sono uguali, la mappa di origine contiene la nuova entry {@code <1729="value1729">} e non
	 * è più presente la entry {@code <3="value3">}. La copia non contiene la nuova entry {@code <1729="value1729">} e
	 * la entry {@code <3="value3">} è ancora presente.
	 */
	@Test
	public void testCopyConstructorIndependence1OnPopulatedMap() {
		MapAdapter copy = new MapAdapter(map);
		map.put(1729, "value1729");
		map.remove(3);
		assertFalse("La copia dovrebbe essere diversa dalla mappa originale", map.equals(copy));
		assertFalse("La copia non dovrebbe contenere la nuova entry", copy.containsKey(1729));
		assertTrue("La copia dovrebbe contenere la entry originale", copy.containsKey(3));
		assertTrue("La mappa originale dovrebbe contenere la nuova entry", map.containsKey(1729));
		assertFalse("La mappa originale non dovrebbe contenere la entry originale", map.containsKey(3));
	}

	/**
	 * <b>Summary:</b>
	 * Test del costruttore di copia {@link myAdapter.MapAdapter#MapAdapter(HMap m)} per verificare che le
	 * modifiche sulla copia non si riflettano sulla mappa originale.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il costruttore di copia di {@code MapAdapter} crea una nuova mappa uguale a quella indicata come parametro.
	 * Se il parametro passato è {@code null}, il costruttore dovrebbe lanciare una {@code NullPointerException}.
	 * Le due mappe sono indipendenti, per cui i cambiamenti che avvengono su una, non si applicano all'altra.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il costruttore di copia di {@code MapAdapter} funzioni correttamente creando una nuova istanza
	 * indipendente dalla mappa di origine. Per fare ciò, sulla mappa copiata, inserisco una nuova entry
	 * {@code <1729="value1729">} e rimuovo la entry {@code <3="value3">} e verifico che le modifiche non
	 * si riflettano sulla mappa originale.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * È stata creata un'istanza valida della classe {@code MapAdapter} che è una copia della mappa passata come
	 * parametro. Nella mappa copiata è presente la nuova entry {@code <1729="value1729">} e non è più presente
	 * la entry {@code <3="value3">}. La mappa originale non ha subito modifiche.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Le due mappe non sono uguali, la mappa copiata contiene la nuova entry {@code <1729="value1729">} e non
	 * è più presente la entry {@code <3="value3">}. La mappa originale non contiene la nuova entry
	 * {@code <1729="value1729">} e la entry {@code <3="value3">} è ancora presente.
	 */
	@Test
	public void testCopyConstructorIndependence2OnPopulatedMap() {
		MapAdapter copy = new MapAdapter(map);
		copy.put(1729, "value1729");
		copy.remove(3);
		assertFalse("La copia dovrebbe essere diversa dalla mappa originale", map.equals(copy));
		assertTrue("La copia dovrebbe contenere la nuova entry", copy.containsKey(1729));
		assertFalse("La copia non dovrebbe contenere la entry originale", copy.containsKey(3));
		assertFalse("La mappa originale non dovrebbe contenere la nuova entry", map.containsKey(1729));
		assertTrue("La mappa originale dovrebbe contenere la entry originale", map.containsKey(3));
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter#size()} su una mappa popolata.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code size()} restituisce il numero di elementi presenti nella mappa.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code size()} restituisca 4, ovvero il numero di elementi presenti nella mappa instanziata
	 * dal metodo {@code setUp()}. Verifica che aggiungendo una nuova entry alla mappa, il metodo {@code size()}
	 * restituisca 5 e che rimuovendo due entry, il metodo {@code size()} restituisca 3.
	 *
	 *<p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code size()} è stato chiamato sulla mappa e ha restituito 4. Viene aggiunta una nuova entry
	 * e il metodo {@code size()} restituisce 5. Vengono rimosse due entry e il metodo {@code size()} restituisce 3.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code size()} dovrebbe restituire 4, poi 5 e infine 3.
	 */
	@Test
	public void testSizeOnPopulatedMap() {
		assertEquals("La mappa dovrebbe avere 4 elementi", 4, map.size());
		map.put("new_key", "new_value");
		assertEquals("La mappa dovrebbe avere 5 elementi", 5, map.size());
		map.remove("key1");
		map.remove("key2");
		assertEquals("La mappa dovrebbe avere 3 elementi", 3, map.size());
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter#isEmpty()} su una mappa popolata.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code isEmpty()} restituisce {@code true} se la mappa è vuota, {@code false} altrimenti.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code isEmpty()} restituisca {@code false} per una mappa popolata, verifica che una volta
	 * rimosse tutte le entry dalla mappa (con {@code clear()}), il metodo {@code isEmpty()} restituisca {@code true}.
	 *
	 *<p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code isEmpty()} è stato chiamato su una mappa popolata e ha restituito {@code false}, vengono rimosse
	 * tutte le entry dalla mappa e il metodo {@code isEmpty()} ha restituito {@code true}.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code isEmpty()} dovrebbe restituire {@code false}, poi {@code true}.
	 */
	@Test
	public void testIsEmptyOnPopulatedMap() {
		assertFalse("La mappa dovrebbe essere non vuota", map.isEmpty());
		map.clear();
		assertTrue("La mappa dovrebbe essere vuota", map.isEmpty());
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter#containsKey(Object key)} su una mappa popolata con chiave non {@code null}.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code containsKey(Object key)} restituisce {@code true} se la mappa contiene la chiave specificata,
	 * {@code false} altrimenti. Se la chiave passata è {@code null}, il metodo dovrebbe restituire {@code false}.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code containsKey(Object key)} restituisca {@code false} quando si prova a cercare una
	 * generica chiave diversa da {@code null} e non presente nella mappa, verifica che il metodo restituisca {@code false}
	 * anche quando si cerca una chiave di un tipo diverso da {@code String} o {@code Integer}, come ad esempio {@code Double}.
	 * Verifica infine che il metodo restituisca {@code true} quando si cerca una chiave presente nella mappa.
	 *
	 *<p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code containsKey(Object key)} è stato chiamato su una mappa popolata e ha restituito {@code false}
	 * quando si è cercata una chiave non presente, ha restituito {@code false} quando si è cercata una chiave di tipo
	 * diverso da {@code String} o {@code Integer} e ha restituito {@code true} quando si è cercata una chiave
	 * presente nella mappa.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code containsKey(Object key)} dovrebbe restituire {@code false}, poi {@code false} e infine {@code true}.
	 */
	@Test
	public void testContainsKeyOnPopulatedMap() {
		assertFalse("La mappa non dovrebbe contenere la chiave generica 'a_random_key'", map.containsKey("a_random_key"));
		assertFalse("La mappa non dovrebbe contenere la chiave '3.1415'", map.containsKey(3.1415));
		assertTrue("La mappa dovrebbe contenere la chiave 'key1'", map.containsKey("key1"));
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter#containsKey(Object key)} su una mappa popolata con chiave {@code null}.
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
	 * prova a cercare una chiave {@code null} in una mappa popolata.
	 *
	 *<p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code containsKey(Object key)} è stato chiamato con {@code null} come chiave da cercare su una mappa
	 * popolata e ha lanciato una {@code NullPointerException}.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code containsKey(Object key)} dovrebbe lanciare una {@code NullPointerException}.
	 */
	@Test(expected = NullPointerException.class)
	public void testContainsNullKeyOnPopulatedMap() {
		map.containsKey(null); // lancia NullPointerException
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter#containsValue(Object value)} su una mappa popolata con valore non {@code null}.
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
	 * generico valore diversa da {@code null} e non presente nella mappa, verifica che il metodo restituisca {@code false}
	 * anche quando si cerca un valore di un tipo diverso da {@code String} o {@code Integer}, come ad esempio {@code Double}.
	 * Verifica infine che il metodo restituisca {@code true} quando si cerca un valore presente nella mappa.
	 *
	 *<p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code containsValue(Object value)} è stato chiamato su una mappa popolata e ha restituito {@code false}
	 * quando si è cercato un valore non presente, ha restituito {@code false} quando si è cercato un valore di tipo
	 * diverso da {@code String} o {@code Integer} e ha restituito {@code true} quando si è cercato un valore
	 * presente nella mappa.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code containsValue(Object value)} dovrebbe restituire {@code false}, poi {@code false} e infine {@code true}.
	 */
	@Test
	public void testContainsValueOnPopulatedMap() {
		assertFalse("La mappa non dovrebbe contenere il valore 'a_random_value'", map.containsValue("a_random_value"));
		assertFalse("La mappa non dovrebbe contenere il valore '3.1415'", map.containsValue(3.1415));
		assertTrue("La mappa dovrebbe contenere il valore 'value1'", map.containsValue("value1"));
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter#containsValue(Object value)} su una mappa popolata con valore {@code null}.
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
	 * prova a cercare un valore {@code null} in una mappa popolata.
	 *
	 *<p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code containsValue(Object value)} è stato chiamato con {@code null} come valore da cercare su una mappa
	 * popolata e ha lanciato una {@code NullPointerException}.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code containsValue(Object value)} dovrebbe lanciare una {@code NullPointerException}.
	 */
	@Test(expected = NullPointerException.class)
	public void testContainsNullValueOnPopulatedMap() {
		map.containsValue(null); // lancia NullPointerException
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter#get(Object key)} su una mappa popolata con chiave non {@code null}.
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
	 * generica chiave non presente nella mappa, verifica che il metodo restituisca {@code null} anche quando si
	 * cerca una chiave di tipo diverso da {@code String} o {@code Integer}, come ad esempio {@code Double}.
	 * Verifica infine che il metodo restituisca il valore corretto quando si cerca una chiave presente nella mappa.
	 *
	 *<p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code get(Object key)} è stato chiamato con una chiave non presente nella mappa e ha restituito
	 * {@code null}, viene cercata una chiave di tipo diverso da {@code String} o {@code Integer} e il metodo
	 * ha restituito {@code null}, viene cercata una chiave presente nella mappa e il metodo ha restituito il
	 * valore associato a tale chiave.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code get(Object key)} dovrebbe restituire per due volte {@code null} e infine il valore
	 * associato alla chiave presente nella mappa.
	 */
	@Test
	public void testGetOnPopulatedMap() {
		assertNull("La mappa non dovrebbe contenere nessun valore associato a 'a_random_key'", map.get("a_random_key"));
		assertNull("La mappa non dovrebbe contenere nessun valore associato a '3.1415'", map.get(3.1415));
		assertEquals("Il metodo get(Object key) dovrebbe restituire il valore associato alla chiave 'key1'", "value1", map.get("key1"));
		assertEquals("Il metodo get(Object key) dovrebbe restituire il valore associato alla chiave 'key2'", 2, map.get("key2"));
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter#get(Object key)} su una mappa popolata con chiave {@code null}.
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
	 * una chiave {@code null} in una mappa popolata.
	 *
	 *<p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code get(Object key)} è stato chiamato con {@code null} come chiave da cercare su una mappa popolata
	 * e ha lanciato una {@code NullPointerException}.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code get(Object key)} dovrebbe lanciare una {@code NullPointerException}.
	 */
	@Test(expected = NullPointerException.class)
	public void testGetNullKeyOnPopulatedMap() {
		map.get(null); // lancia NullPointerException
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter#put(Object key, Object value)} su una mappa popolata con chiave e valore non {@code null}.
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
	 * Verifica che il metodo {@code put(Object key, Object value)} inserisca correttamente una nuova entry con una
	 * chiave non ancora presente nella mappa e restituisca {@code null} come valore precedentemente associato alla
	 * chiave. Verifica che lo stesso metodo aggiorni il valore associato a una chiave già presente nella mappa quando
	 * si effettua un inserimento con una chiave già esistente, restituendo il valore precedentemente associato a tale chiave.
	 *
	 *<p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code put(Object key, Object value)} è stato chiamato con chiave non presente nella mappa e ha inserito
	 * correttamente una nuova entry nella mappa, restituendo {@code null}. Verifica che il metodo abbia aggiornato
	 * il valore associato a una chiave già presente nella mappa quando si effettua un inserimento con una chiave già
	 * esistente e abbia restituito il valore precedentemente associato a tale chiave.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Dalla prima invocazione di {@code put(Object key, Object value)} si dovrebbe ottenere che:
	 * <ul>
	 *   <li>il metodo {@code put(Object key, Object value)} dovrebbe restituire {@code null} come valore precedentemente
	 *   associato alla chiave</li>
	 *   <li>la mappa dovrebbe avere dimensione 5</li>
	 *   <li>il metodo {@code get(Object key)} dovrebbe restituire il valore associato alla chiave appena inserita</li>
	 *   <li>il metodo {@code containsKey(Object key)} dovrebbe restituire true per la chiave appena inserita</li>
	 *   <li>il metodo {@code containsValue(Object value)} dovrebbe restituire true per il valore appena inserito</li>
	 * </ul>
	 * Dalla seconda invocazione di {@code put(Object key, Object value)} si dovrebbe ottenere che:
	 * <ul>
	 *   <li>il metodo {@code put(Object key, Object value)} dovrebbe restituire il valore precedentemente associato alla
	 *   chiave della entry appena inserita</li>
	 *   <li>la mappa dovrebbe mantenere dimensione 5</li>
	 *   <li>il metodo {@code get(Object key)} dovrebbe restituire il valore associato alla chiave appena inserita</li>
	 *   <li>il metodo {@code containsKey(Object key)} dovrebbe restituire true per la chiave appena inserita</li>
	 *   <li>il metodo {@code containsValue(Object value)} dovrebbe restituire true per il valore appena inserito</li>
	 * </ul>
	 */
	@Test
	public void testPutOnPopulatedMap() {
		// primo inserimento con chiave non presente
		assertNull("La mappa non dovrebbe contenere nessun valore associato alla chiave", map.put("a_random_key", "a_random_value"));
		assertEquals("La mappa dovrebbe avere dimensione 5 dopo l'inserimento di una entry", 5, map.size());
		assertEquals("Il metodo get(Object key) dovrebbe restituire il valore associato alla chiave", "a_random_value", map.get("a_random_key"));
		assertTrue("Il metodo containsKey(Object key) dovrebbe restituire true per la chiave appena inserita", map.containsKey("a_random_key"));
		assertTrue("Il metodo containsValue(Object value) dovrebbe restituire true per il valore appena inserito",map.containsValue("a_random_value"));

		// secondo inserimento con chiave già esistente
		assertEquals("La mappa dovrebbe contenere un valore associato alla chiave", "a_random_value", map.put("a_random_key", "a_new_random_value"));
		assertEquals("La mappa dovrebbe mantenere dimensione 5 dopo l'aggiornamento di una entry", 5, map.size());
		assertEquals("Il metodo get(Object key) dovrebbe restituire il valore associato alla chiave", "a_new_random_value", map.get("a_random_key"));
		assertTrue("Il metodo containsKey(Object key) dovrebbe restituire true per la chiave appena inserita", map.containsKey("a_random_key"));
		assertTrue("Il metodo containsValue(Object value) dovrebbe restituire true per il valore appena inserito", map.containsValue("a_new_random_value"));
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter#put(Object key, Object value)} su una mappa popolata con chiave {@code null} e valore non {@code null}.
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
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
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
	public void testPutNullKeyOnPopulatedMap() {
		map.put(null, "value"); // lancia NullPointerException
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter#put(Object key, Object value)} su una mappa popolata con chiave non {@code null} e valore {@code null}.
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
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
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
	public void testPutNullValueOnPopulatedMap() {
		map.put("key", null); // lancia NullPointerException
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter#put(Object key, Object value)} su una mappa popolata con la mappa stessa come chiave.
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
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code put(Object key, Object value)} è stato chiamato con la mappa su cui si sta invocando il metodo
	 * come chiave della entry da inserire e ha lanciato una {@code IllegalArgumentException}.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code put(Object key, Object value)} dovrebbe lanciare una {@code IllegalArgumentException}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPutMapAsKeyOnPopulatedMap() {
		map.put(map, "value"); // lancia IllegalArgumentException
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter#remove(Object key)} su una mappa popolata con chiave non {@code null}.
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
	 * entry con una generica chiave diversa da {@code null} non presente nella mappa. Verifica che il metodo
	 * restituisca {@code null} anche quando si prova a rimuovere una chiave di un tipo diverso da {@code String} o
	 * {@code Integer}. Verifica infine che il metodo restituisca il valore precedentemente associato alla chiave
	 * quando si prova a rimuovere una chiave presente nella mappa.
	 *
	 *<p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code remove(Object key)} è stato chiamato con una chiave non presente nella mappa e ha restituito
	 * {@code null}. Viene rimossa una chiave di tipo diverso da {@code String} o {@code Integer} e il metodo
	 * ha restituito {@code null}. Viene rimossa una chiave presente nella mappa e il metodo ha restituito il valore
	 * precedentemente associato alla chiave rimossa.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code remove(Object key)} dovrebbe restituire per due volte {@code null}, senza alterare la dimensione
	 * della mappa, ed infine restituire il valore precedentemente associato alla chiave rimossa quando la chiave
	 * è presente nella mappa, decrementando la dimensione della mappa.
	 */
	@Test
	public void testRemoveOnPopulatedMap() {
		assertNull("La mappa non dovrebbe contenere nessun valore associato alla chiave 'a_random_key'", map.remove("a_random_key"));
		assertNull("La mappa non dovrebbe contenere nessun valore associato alla chiave '3.1415'", map.remove(3.1415));
		assertEquals("La mappa dovrebbe avere ancora 4 elementi dopo il tentativo di rimozione", 4, map.size());
		assertEquals("La mappa dovrebbe restituire il valore associato alla chiave 'key1'", "value1", map.remove("key1"));
		assertEquals("La mappa dovrebbe avere ancora 3 elementi dopo la rimozione di 'key1'", 3, map.size());
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter#remove(Object key)} su una mappa popolata con chiave {@code null}.
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
	 * rimuovere una entry con chiave {@code null} in una mappa popolata.
	 *
	 *<p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code remove(Object key)} è stato chiamato con {@code null} come chiave di entry da rimuovere su una
	 * mappa popolata e ha lanciato una {@code NullPointerException}.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code remove(Object key)} dovrebbe lanciare una {@code NullPointerException}.
	 */
	@Test(expected = NullPointerException.class)
	public void testRemoveNullKeyOnPopulatedMap() {
		map.remove(null); // lancia NullPointerException
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter#putAll(HMap m)} su una mappa popolata con mappa vuota.
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
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}. Viene
	 * creata una mappa vuota da passare al metodo {@code putAll(HMap m)}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * La mappa dovrebbe rimanere con 4 elementi dopo il tentativo di inserimento delle entry della mappa vuota.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code putAll(HMap m)} dovrebbe mantenere la mappa iniziale inalterata con 4 elementi.
	 */
	@Test
	public void testPutAllEmptyMapOnPopulatedMap() {
		MapAdapter emptyMap = new MapAdapter();
		map.putAll(emptyMap);
		assertEquals("La mappa dovrebbe rimanere con 4 elementi dopo il tentativo di inserimento", 4, map.size());
		assertEquals("La mappa dovrebbe contenere il valore associato alla chiave 'key1'", "value1", map.get("key1"));
		assertEquals("La mappa dovrebbe contenere il valore associato alla chiave 'key2'", 2, map.get("key2"));
		assertEquals("La mappa dovrebbe contenere il valore associato alla chiave '3'", "value3", map.get(3));
		assertEquals("La mappa dovrebbe contenere il valore associato alla chiave '4'", 4, map.get(4));
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter#putAll(HMap m)} su una mappa popolata con la mappa stessa.
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
	 * Verifica che il metodo {@code putAll(HMap m)} mantenga la mappa invariata quando gli viene passata la stessa
	 * mappa su cui è stato chiamato il metodo.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * La mappa dovrebbe rimanere inalterata dopo il tentativo di inserimento delle entry della stessa mappa su cui è invocato
	 * il metodo, senza lanciare eccezioni.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code putAll(HMap m)} dovrebbe mantenere la mappa inalterata con 4 elementi, senza lanciare eccezioni.
	 */
	@Test
	public void testSelfPutAllOnPopulatedMap() {
		map.putAll(map);
		assertEquals("La mappa dovrebbe rimanere con 4 elementi dopo il tentativo di inserimento", 4, map.size());
		assertEquals("La mappa dovrebbe contenere il valore associato alla chiave 'key1'", "value1", map.get("key1"));
		assertEquals("La mappa dovrebbe contenere il valore associato alla chiave 'key2'", 2, map.get("key2"));
		assertEquals("La mappa dovrebbe contenere il valore associato alla chiave '3'", "value3", map.get(3));
		assertEquals("La mappa dovrebbe contenere il valore associato alla chiave '4'", 4, map.get(4));
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter#putAll(HMap m)} su una mappa popolata con mappa popolata.
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
	 * Verifica che il metodo {@code putAll(HMap m)} inserisca correttamente le entry di una mappa popolata passata come
	 * parametro. Le entry della mappa passata sono strutturate per coprire vari casi d'uso del metodo {@code put(Object key, Object value)}:
	 * <ul>
	 *   <li>una chiave non presente nella mappa corrente tipo {@code <"value5"="newValue">}</li>
	 *   <li>una chiave presente nella mappa corrente con un valore diverso tipo {@code <"key2"="1234-1232">}</li>
	 *   <li>una chiave presente nella mappa corrente con un valore uguale tipo {@code <"key1"="value1">}</li>
	 * </ul>
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}. Viene
	 * creata una nuova mappa da passare al metodo {@code putAll(HMap m)} a cui vengono aggiunte tre entry con il
	 * metodo {@code put(Object key, Object value)} come definito sopra.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * La mappa dovrebbe contenere le entry della mappa passata come parametro, con le chiavi e i valori aggiornati.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * <ul>
	 *   <li>la mappa dovrebbe avere dimensione 5 dopo il tentativo di inserimento</li>
	 *   <li>la mappa dovrebbe contenere tutte le entry originali (al netto di quelle modificate)</li>
	 *   <li>la mappa dovrebbe contenere tutte le entry della mappa passata come parametro</li>
	 * </ul>
	 */
	@Test
	public void testPutAllNotEmptyOnPopulatedMap() {
		MapAdapter notEmptyMap = new MapAdapter();
		notEmptyMap.put("key5", "value5");
		notEmptyMap.put("key2", "1234-1232");
		notEmptyMap.put("key1", "value1");
		map.putAll(notEmptyMap);
		assertEquals("La mappa dovrebbe avere dimensione 5 dopo il tentativo di inserimento", 5, map.size());
		assertEquals("La mappa dovrebbe contenere la entry <\"key1\"=\"value1\">", "value1", map.get("key1"));
		assertEquals("La mappa dovrebbe contenere la entry <\"key2\"=\"1234-1232\">", "1234-1232", map.get("key2"));
		assertEquals("La mappa dovrebbe contenere la entry <\"3\"=\"value3\">", "value3", map.get(3));
		assertEquals("La mappa dovrebbe contenere la entry <\"4\"=\"4\">", 4, map.get(4));
		assertEquals("La mappa dovrebbe contenere la entry <\"key5\"=\"value5\">", "value5", map.get("key5"));
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter#putAll(HMap m)} su una mappa popolata con mappa {@code null}.
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
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}. Viene
	 * creata una nuova mappa con riferimento {@code null} da passare al metodo {@code putAll(HMap m)}.
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
	public void testPutAllNullMapOnPopulatedMap() {
		MapAdapter nullMap = null;
		map.putAll(nullMap); // lancia NullPointerException
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter#putAll(HMap m)} su una mappa popolata con mappa con entry con chiave {@code null}.
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
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}. Viene creata
	 * una nuova mappa utilizzando la classe {@code myTest.SimpleHMapWithNulls} di appositamente creata per questo test
	 * da passare al metodo {@code putAll(HMap m)} a cui viene aggiunta una entry con chiave {@code null}.
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
	@Test(expected = NullPointerException.class)
	public void testPutAllNullKeyOnPopulatedMap() {
		SimpleHMapWithNulls hashmap = new SimpleHMapWithNulls();
		hashmap.put(null, "value"); // aggiunge una entry con chiave null
		map.putAll((HMap) hashmap); // lancia NullPointerException
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter#putAll(HMap m)} su una mappa popolata con mappa con entry con chiave {@code null}.
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
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}. Viene creata
	 * una nuova mappa utilizzando la classe {@code myTest.SimpleHMapWithNulls} di appositamente creata per questo test
	 * da passare al metodo {@code putAll(HMap m)} a cui viene aggiunta una entry con valore {@code null}.
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
	@Test(expected = NullPointerException.class)
	public void testPutAllNullValueOnPopulatedMap() {
		SimpleHMapWithNulls hashmap = new SimpleHMapWithNulls();
		hashmap.put("key", null); // aggiunge una entry con valore null
		map.putAll((HMap)hashmap); // lancia NullPointerException
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter#putAll(HMap m)} su una mappa popolata con mappa con entry con la mappa stessa come chiave.
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
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}. Viene creata
	 * una nuova mappa utilizzando la classe {@code myTest.SimpleHMapWithNulls} di appositamente creata per questo test
	 * da passare al metodo {@code putAll(HMap m)} a cui viene aggiunta una entry con la mappa su cui è invocato il
	 * metodo come chiave.
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
	@Test(expected = IllegalArgumentException.class)
	public void testPutAllSelfContainingMapOnPopulatedMap() {
		SimpleHMapWithNulls hashmap = new SimpleHMapWithNulls();
		hashmap.put(map, "value"); // aggiunge una entry con la mappa come chiave
		map.putAll((HMap)hashmap); // lancia IllegalArgumentException
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter#clear()} su una mappa popolata.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code clear()} rimuove tutte le entry dalla mappa, rendendola vuota. Dopo la chiamata a questo metodo,
	 * la mappa dovrebbe essere vuota.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code clear()} invocato su una mappa popolata provochi l'eliminazione di tutte le entry,
	 * rendendo la mappa vuota, senza lanciare eccezioni.
	 *
	 *<p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code clear()} ha eliminato tutte le entry dalla mappa, rendendola vuota.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code clear()} dovrebbe rendere la mappa vuota e con 0 elementi
	 */
	@Test
	public void testClearOnPopulatedMap() {
		map.clear();
		assertEquals("Clear dovrebbe lasciare 0 elementi nella mappa", 0, map.size());
		assertTrue("Clear dovrebbe rendere la mappa vuota", map.isEmpty());
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter#keySet()} su una mappa popolata.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code keySet()} restituisce un insieme delle chiavi presenti nella mappa. Per una mappa popolata,
	 * dovrebbe restituire un insieme popolato. I metodi dell'insieme e dei relativi iterator sono testati in altri
	 * test separati.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code keySet()} restituisca un insieme popolato con le chiavi quando invocato su una
	 * mappa popolata.
	 *
	 *<p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code keySet()} è stato chiamato su una mappa popolata e ha restituito un insieme popolato.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * L'oggetto restituito dal metodo {@code keySet()} dovrebbe essere
	 * <ul>
	 * <li>non {@code null}</li>
	 * <li>un'istanza di {@code HSet}, non dovrebbe essere</li>
	 * <li>un insieme con 4 elementi</li>
	 * </ul>
	 */
	@Test
	public void testKeySetOnPopulatedMap() {
		assertNotNull("La keySet di una mappa popolata non dovrebbe essere null", map.keySet());
		assertTrue("La keySet di una mappa popolata dovrebbe essere un insieme", map.keySet() instanceof HSet);
		assertTrue("La keySet di una mappa popolata dovrebbe avere 4 elementi", map.keySet().size() == 4);
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter#values()} su una mappa popolata.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code values()} restituisce una collezione dei valori presenti nella mappa. Per una mappa popolata,
	 * dovrebbe restituire una collezione popolata. I metodi della collezione e dei relativi iterator sono testati in altri
	 * test separati.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code values()} restituisca una collezione popolata quando invocato su una mappa popolata.
	 *
	 *<p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code values()} è stato chiamato su una mappa popolata e ha restituito una collezione popolata.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * L'oggetto restituito dal metodo {@code values()} dovrebbe essere
	 * <ul>
	 * <li>non {@code null}</li>
	 * <li>un'istanza di {@code HCollection}, non dovrebbe essere</li>
	 * <li>una collezione con 4 elementi</li>
	 * </ul>
	 */
	@Test
	public void testValuesCollectionOnPopulatedMap() {
		assertNotNull("La valuesCollection di una mappa popolata non dovrebbe essere null", map.values());
		assertTrue("La valuesCollection di una mappa popolata dovrebbe essere un insieme", map.values() instanceof HCollection);
		assertTrue("La valuesCollection di una mappa popolata dovrebbe avere 4 elementi", map.values().size() == 4);
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter#entrySet()} su una mappa popolata.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code entrySet()} restituisce un insieme delle coppie chiave-valore presenti nella mappa. Per una mappa
	 * popolata dovrebbe restituire un insieme popolato. I metodi dell'insieme e dei relativi iterator sono testati in altri
	 * test separati.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code entrySet()} restituisca un insieme popolato quando invocato su una mappa popolata.
	 *
	 *<p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code entrySet()} è stato chiamato su una mappa popolata e ha restituito un insieme popolato.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * L'oggetto restituito dal metodo {@code entrySet()} dovrebbe essere
	 * <ul>
	 *   <li>non {@code null}</li>
	 *   <li>un'istanza di {@code HSet}, non dovrebbe essere</li>
	 *   <li>un insieme con 4 elementi</li>
	 *   <li>ogni elemento dell'insieme dovrebbe essere un'istanza di {@code HMap.Entry} (si usa l'iteratore)</li>
	 * </ul>
	 */
	@Test
	public void testEntrySetOnPopulatedMap() {
		assertNotNull("La entrySet di una mappa popolata non dovrebbe essere null", map.entrySet());
		assertTrue("La entrySet di una mappa popolata dovrebbe essere un insieme", map.entrySet() instanceof HCollection);
		assertTrue("La entrySet di una mappa popolata dovrebbe avere 4 elementi", map.entrySet().size() == 4);
		HIterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			assertTrue("Ogni elemento della entrySet dovrebbe essere un'istanza di HMap.Entry", it.next() instanceof HMap.HEntry);
		}
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter#equals(Object o)} su una mappa popolata con oggetto {@code null}.
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
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
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
	public void testEqualsNullOnPopulatedMap() {
		assertFalse("equals dovrebbe restituire false se gli viene passato null", map.equals(null));
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter#equals(Object o)} su una mappa popolata con oggetto non-mappa.
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
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}. È stato
	 * creato un oggetto di tipo {@code String} da passare al metodo {@code equals(Object o)}.
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
	public void testEqualsNotMapOnPopulatedMap() {
		String notAMap = "This is not a map";
		assertFalse("equals dovrebbe restituire false se gli viene passato un oggetto che non è una mappa", map.equals(notAMap));
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter#equals(Object o)} su una mappa popolata con un'altra mappa vuota.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code equals(Object o)} confronta la mappa su cui è invocato con un altro oggetto. Se l'oggetto passato
	 * è {@code null} o non è una mappa, il metodo restituisce {@code false}. Se l'oggetto passato è una mappa, il metodo
	 * restituisce {@code true} se e solo se le due mappe contengono le stesse mappature chiave-valore.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code equals(Object o)} restituisca {@code false} quando l'oggetto passato è una nuova mappa
	 * vuota, ovvero senza entry come quella su cui è invocato il metodo.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}. È stata
	 * creata una nuova mappa vuota da passare al metodo {@code equals(Object o)}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code equals(Object o)} è stato chiamato con una mappa vuota e non ha lanciato eccezioni.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code equals(Object o)} dovrebbe restituire {@code false}.
	 */
	@Test
	public void testEqualsNewEmptyMapOnPopulatedMap() {
		MapAdapter newEmptyMap = new MapAdapter();
		assertFalse("equals dovrebbe restituire false se gli viene passato un'altra mappa vuota", map.equals(newEmptyMap));
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter#equals(Object o)} su una mappa popolata con una mappa popolata con le stesse entry.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code equals(Object o)} confronta la mappa su cui è invocato con un altro oggetto. Se l'oggetto passato
	 * è {@code null} o non è una mappa, il metodo restituisce {@code false}. Se l'oggetto passato è una mappa, il metodo
	 * restituisce {@code true} se e solo se le due mappe contengono le stesse mappature chiave-valore.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code equals(Object o)} restituisca {@code true} quando l'oggetto passato è una mappa
	 * popolata con le stesse entry della mappa su cui è invocato il metodo.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}. È stata
	 * creata una nuova istanza di {@code MapAdapter} da passare al metodo {@code equals(Object o)} a cui sono state
	 * aggiunte le stesse entry della mappa su cui è invocato il metodo.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code equals(Object o)} è stato chiamato con un'istanza di {@code MapAdapter} non uguale alla mappa su cui
	 * è stato invocato e non ha lanciato eccezioni.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code equals(Object o)} dovrebbe restituire {@code true}.
	 */
	@Test
	public void testEqualsSameEntryMapOnPopulatedMap() {
		MapAdapter populatedMap = new MapAdapter();
		populatedMap.put("key1", "value1");
		populatedMap.put("key2", 2);
		populatedMap.put(3, "value3");
		populatedMap.put(4, 4);
		assertTrue("equals dovrebbe restituire true se gli viene passata è una mappa con le stesse entry", map.equals(populatedMap));
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter#equals(Object o)} su una mappa popolata con una mappa popolata con entry diverse.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code equals(Object o)} confronta la mappa su cui è invocato con un altro oggetto. Se l'oggetto passato
	 * è {@code null} o non è una mappa, il metodo restituisce {@code false}. Se l'oggetto passato è una mappa, il metodo
	 * restituisce {@code true} se e solo se le due mappe contengono le stesse mappature chiave-valore.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code equals(Object o)} restituisca {@code true} quando l'oggetto passato è una mappa
	 * popolata con un numero di entry diverso della mappa su cui è invocato il metodo, oppure con lo stesso numero di entry
	 * ma con valori diversi, oppure con lo stesso numero di entry ma con chiavi e valori diversi.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}. È stata creata
	 * una nuova istanza di {@code MapAdapter} da passare al metodo {@code equals(Object o)} a cui sono state aggiunte
	 * le stesse entry della mappa su cui è invocato il metodo eccetto l'ultima {@code <4=4>}. Per la seconda verifica
	 * verrà aggiunta la entry {@code <4="value4">} e per la terza verifica verrà rimossa la entry {@code <4="value4">}
	 * per aggiungere la entry {@code <5="sqrt{25}">}.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code equals(Object o)} è stato chiamato con un'istanza di {@code MapAdapter} non uguale alla mappa su cui
	 * è stato invocato e ha restituito {@code false} in tutti i casi, senza lanciare eccezioni.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code equals(Object o)} dovrebbe restituire {@code false} in tutti i casi descritti.
	 */
	@Test
	public void testEqualsDifferentEntryMapOnPopulatedMap() {
		// numero di entry diverso dalla mappa su cui è invocato il metodo
		MapAdapter populatedMap = new MapAdapter();
		populatedMap.put("key1", "value1");
		populatedMap.put("key2", 2);
		populatedMap.put(3, "value3");
		assertFalse("equals dovrebbe restituire false se gli viene passata è una mappa con un numero di entry diverso", map.equals(populatedMap));

		// stesso numero di entry ma con valori diversi
		populatedMap.put(4, "value4");
		assertFalse("equals dovrebbe restituire false se gli viene passata è una mappa con lo stesso numero di entry ma valori diversi", map.equals(populatedMap));

		// stesso numero di entry ma con chiavi e valori diversi
		populatedMap.remove(4);
		populatedMap.put("5", "sqrt{25}");
		assertFalse("equals dovrebbe restituire false se gli viene passata è una mappa con lo stesso numero di entry ma chiavi e valori diversi", map.equals(populatedMap));
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter#equals(Object o)} su una mappa popolata con sé stessa.
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
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}.
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
	public void testEqualsSelfOnPopulatedMap() {
		assertTrue("equals dovrebbe restituire true se gli viene passato sé stesso", map.equals(map));
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter#hashCode()} su una mappa popolata.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code hashCode()} restituisce un valore intero che rappresenta l'hash code della mappa. Una mappa vuota
	 * dovrebbe restituire un hash code di 0, mentre una mappa con entry dovrebbe restituire un hash code ottenuto dalla
	 * somma degli hash code delle entry.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code hashCode()} restituisca un numero quando invocato su una mappa popolata, uguale a
	 * quello di una nuova mappa con le stesse entry della mappa su cui è invocato il metodo.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}. Viene creata
	 * una nuova istanza di {@code MapAdapter} per verificare che l'hash code di due mappe con le stesse entry sia lo stesso.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code hashCode()} è stato chiamato su una mappa popolata e ha restituito un valore uguale a
	 * quello della nuova mappa con le stesse entry di quella instanziata dal {@code setUp()}.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code hashCode()} dovrebbe restituire un numero uguale a l'hash code della nuova mappa con le stesse entry.
	 */
	@Test
	public void testHashOnPopulatedMap() {
		MapAdapter newMap = new MapAdapter();
		newMap.put("key1", "value1");
		newMap.put("key2", 2);
		newMap.put(3, "value3");
		newMap.put(4, 4);
		assertTrue("L'hash code di due mappe con le stesse entry dovrebbe essere lo stesso", map.hashCode() == newMap.hashCode());
		map.remove(3);
		map.put(4,"value4");
		newMap.remove(3);
		newMap.put(4, "value4");
		assertTrue("L'hash code di due mappe con le stesse entry dovrebbe essere lo stesso", map.hashCode() == newMap.hashCode());
	}

	/**
	 * <b>Summary:</b>
	 * Test di {@link myAdapter.MapAdapter#toString()} su una mappa popolata.
	 *
	 * <p>
	 * <b>Test Case Design:</b>
	 * Il metodo {@code toString()} restituisce una rappresentazione in stringa della mappa. Per una mappa popolata,
	 * dovrebbe restituire la stringa {@code {entry1, entry2, ...}} dove le entry sono nella forma {@code key=value}.
	 * L'ordine delle entry non è garantito in quanto dipende dall'hash code delle chiavi.
	 *
	 * <p>
	 * <b>Test Description:</b>
	 * Verifica che il metodo {@code toString()} restituisca la rappresentazione corretta per una mappa popolata.
	 * Siccome non è possibile sapere in quale ordine le entry saranno restituite, si costruisce la stringa attesa
	 * secondo le specifiche della mappa e si verifica che la rappresentazione in stringa della mappa corrisponda
	 * a quella attesa.
	 *
	 * <p>
	 * <b>Pre-Condition:</b>
	 * Il metodo {@code setUp()} è stato eseguito, creando una nuova istanza della classe {@code MapAdapter} che
	 * rappresenta una mappa con le seguenti 4 entry {@code {"key1"="value1", "key2"=2, 3="value3", 4=4}}. Si
	 * istanzia una stringa per memorizzare la rappresentazione attesa della mappa.
	 *
	 * <p>
	 * <b>Post-Condition:</b>
	 * Il metodo {@code toString()} è stato chiamato su una mappa popolata e ha restituito la stringa corretta.
	 *
	 * <p>
	 * <b>Expected Results:</b>
	 * Il metodo {@code toString()} dovrebbe restituire la stringa attesa.
	 */
	@Test
	public void testToStringOnPopulatedMap() {
		// creo un StringBuffer per costruire la stringa attesa
		StringBuffer sb = new StringBuffer("{");

		// inserisco gli elementi della mappa nello StringBuffer attraverso l'iteratore della entrySet
		HIterator i = map.entrySet().iterator();
		while (i.hasNext()) {
			sb.append(i.next().toString());
			if (i.hasNext())
				sb.append(", ");
		}
		sb.append("}");

		// verifico che la rappresentazione in stringa della mappa sia quella attesa
		assertEquals("La rappresentazione in stringa di una mappa popolata non corrisponde con quella attesa", sb.toString(), map.toString());
	}
}
