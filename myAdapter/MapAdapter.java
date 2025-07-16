// Giacomo Simonetto - 2109923 - Secondo appello 2025 di Elementi di Ingegneria del Software

package myAdapter;

// importazioni
import java.util.Enumeration; // classe enumeration disponibile con CLDC 1.1
import myCompatibilityLayer.MockHashtable; // classe hashtable con metodi compliant con J2ME CLDC 1.1

/* ================================================================================================================= *\
 * =============================================== Classe MapAdapter =============================================== *
\* ================================================================================================================= */

/**
 * Adapter che utilizza una Hashtable di J2ME CLDC 1.1 come sottostruttura per implementare i metodi dell'interfaccia
 * Map di J2SE 1.4.2 definita in {@link HMap}.
 * 
 * <p>
 * Siccome la hashtable di CLDC 1.1 non supporta chiavi o valori {@code null}, è stato scelto che nemmeno l'adapter
 * supporta chiavi o valori {@code null}. Nel caso in cui si utilizzano chiavi o valori {@code null}, viene lanciata
 * una {@code NullPointerException}.
 * 
 * <p>
 * La mappa possiede due costruttori:
 * <ul>
 *   <li>un costruttore vuoto che crea una mappa vuota</li>
 *   <li>un costruttore di copia che crea una nuova mappa e copia le mappature chiave-valore da un'altra mappa</li>
 * </ul>
 * Le mappe, seppur utilizzando istanze diverse della sottostruttura hashtable, contengono i riferimenti agli stessi
 * oggetti chiave e valore. Se si modifica la chiave (non raccomandato) o valore di una mappatura chiave-valore da
 * una mappa, l'altra mappa ne vedrà il cambiamento. Questo è dovuto alla gestione di Java degli oggetti come riferimenti.
 * 
 * <p>
 * La mappa fornisce tre viste backend per accedere alle chiavi, ai valori e alle mappature contenute nella mappa:
 * <ul>
 *   <li>vista delle chiavi o {@code keySet} attraverso un oggetto di tipo {@code HSet} restituito dal metodo
 *   {@link #keySet()}</li>
 *   <li>vista dei valori o {@code values} attraverso un oggetto di tipo {@code HCollection} restituito dal metodo
 *   {@link #values()}</li>
 *   <li>vista delle entry (o mappature chiave-valore) o {@code entrySet} attraverso un oggetto di tipo {@code HSet}
 *   restituito dal metodo {@link #entrySet()}</li>
 * </ul>
 * Gli oggetti restituiti sono classi annidate all'interno della classe {@code MapAdapter} su cui è possibile iterare
 * e rimuovere elementi, ma non aggiungerne di nuovi. Le viste si appoggiano alla stessa struttura (hashtable) a cui
 * si appoggia l'adapter, per cui quando si modifica la mappa, le viste si aggiornano automaticamente e viceversa.
 * Se la mappa viene modificata mentre è visitata da un iteratore di una vista, il comportamento dell'iteratore non
 * è definito.
 *
 * <p>
 * Bisogna fare attenzione ad usare oggetti mutabili come chiavi della mappa. Il comportamento della mappa non è definito
 * se il valore di un oggetto viene modificato. Un caso speciale di questo divieto è che non è consentito che una mappa
 * contenga se stessa come chiave. Se si prova a inserire, in una mappa, sé stessa come chiave, viene lanciata una
 * {@code IllegalArgumentException}. È invece consentito inserire sé stessa come valore o inserire una delle view come
 * chiave o valore di una mappatura, anche se è fortemente sconsigliato in quanto i metodi {@code equals} e {@code hashcode}
 * danno origine ad una ricorsione infinita che termina con uno {@code StackOverflow}.
 *
 * <p>
 * Siccome in CLDC 1.1 non sono supportati i generics, la maggior parte dei metodi della mappa non possono controllare
 * il tipo degli oggetti chiave e valore e di conseguenza non potranno lanciare una {@code ClassCastException}.
 * 
 * @see HMap
 * @see HMap.HEntry
 * @see HCollection
 * @see HSet
 */
public class MapAdapter implements HMap {
	// ****************************************************************************************************************
	// ---------------------------------- Variabili d'istanza della classe MapAdapter ---------------------------------

	// sottostruttura hashtable di CLDC 1.1
	private MockHashtable hashtable;

	// ****************************************************************************************************************
	// -------------------------------------- Costruttori della classe MapAdapter -------------------------------------
	
	/**
	 * Costruttore vuoto, crea una nuova mappa vuota.
	 */
	public MapAdapter() {
		// creo ed inizializzo la sottostruttura hashtable di CLDC 1.1
		hashtable = new MockHashtable();
	}

	/**
	 * Costruttore di copia, crea una nuova mappa ed effettua la copia delle mappature chiave-valore della
	 * mappa passata come parametro. La mappa ottenuta, seppur utilizzando una sottostruttura hashtable diversa,
	 * contiene gli stessi riferimenti agli oggetti chiave e valore della mappa originale, per cui se si modifica
	 * la chiave (non raccomandato) o il valore di una mappatura chiave-valore da una mappa (attraverso i metodi propri
	 * della chiave o del valore), l'altra mappa ne vedrà il cambiamento.
	 *
	 * @param m mappa da cui copiare le mappature chiave-valore.
	 * @throws NullPointerException se la mappa passata come parametro è {@code null}, o se una delle entry nella
	 * mappa {@code m} ha chiave o valore {@code null}
	 * @throws IllegalArgumentException se la mappa passata come parametro, contiene una mappatura che ha come chiave
	 * la mappa su cui si sta creando l'adapter.
	 */
	public MapAdapter(HMap m) {
		// lacio l'eccezione se la mappa passata è null, altrimenti avrei un'invocazione di metodi su oggetti null
		if (m == null)
			throw new NullPointerException();

		// creo ed inizializzo la sottostruttura hashtable di CLDC 1.1
		hashtable = new MockHashtable();

		// copio tutti i valori dalla mappa passata come parametro alla mappa appena creata, la gestione delle
		// eccezioni NullPointerException (per chiave nulla) o IllegalArgumentException (per chiave uguale alla
		// mappa su cui si sta creando l'adapter) è delegata al metodo put() chiamato da putAll().
		this.putAll(m);
	}


	// ****************************************************************************************************************
	// ----------------------------------------- Metodi dell'interfaccia HMap -----------------------------------------

	/**
	 * Restituisce il numero di mappature chiave-valore in questa mappa. Se la mappa contiene più di
	 * {@code Integer.MAX_VALUE} elementi, restituisce {@code Integer.MAX_VALUE}.
	 *
	 * @return il numero di mappature chiave-valore in questa mappa.
	 */
	public int size() {
		// richiamo il corrispettivo metodo della hashtable e ne restituisco il risultato
		return hashtable.size();
	}

	/**
	 * Restituisce {@code true} se questa mappa non contiene mappature chiave-valore, {@code false} altrimenti.
	 *
	 * @return {@code true} se questa mappa non contiene mappature chiave-valore, {@code false} altrimenti.
	 */
	public boolean isEmpty() {
		// richiamo il corrispettivo metodo della hashtable e ne restituisco il risultato
		return hashtable.isEmpty();
	}

	/**
	 * Restituisce {@code true} se questa mappa contiene una mappatura per la chiave specificata. Se la chiave è
	 * {@code null}, il metodo lancia {@code NullPointerException}.
	 *
	 * @param key chiave la cui presenza in questa mappa deve essere testata.
	 * @return {@code true} se questa mappa contiene una mappatura per la chiave specificata, {@code false} altrimenti.
	 * @throws NullPointerException se la chiave passata è {@code null}.
	 */
	public boolean containsKey(Object key) {
		// containsKey() della hashtable non lancia NullPointerException per cui lo lancio manualmente
		if (key == null)
			throw new NullPointerException();

		// richiamo il corrispettivo metodo della hashtable e ne restituisco il risultato
		return hashtable.containsKey(key);
	}

	/**
	 * Restituisce {@code true} se la mappa contiene una o più chiavi che vengono mappate al valore passato come
	 * parametro. La ricerca impiega tempo lineare nella dimensione della mappa. Se il valore passato è {@code null},
	 * il metodo lancia l'eccezione {@code NullPointerException}.
	 * 
	 * @param value valore la cui presenza in questa mappa deve essere testata.
	 * @return {@code true} se la mappa ha almeno una mappatura che coinvolge il valore specificato, {@code false} altrimenti.
	 * @throws NullPointerException se il valore passato è {@code null}.
	 */
	public boolean containsValue(Object value) {
		// NullPointerException già lanciato dal metodo contains() della hashtable
		// richiamo il corrispettivo metodo della hashtable e ne restituisco il risultato
		return hashtable.contains(value);
	}

	/**
	 * Restituisce il valore associato alla chiave passata come parametro. Se la chiave non è presente all'interno
	 * della mappa, il metodo restituisce {@code null}. Siccome i valori {@code null} non sono supportati, se il metodo
	 * restituisce {@code null} significa che sicuramente la chiave passata non è presente all'interno della mappa.
	 *
	 * @param key chiave il cui valore associato deve essere restituito.
	 * @return valore associato alla chiave {@code key}, {@code null} se la chiave non è presente.
	 * @throws NullPointerException se la chiave passata è {@code null}.
	 */
	public Object get(Object key) {
		// get() della hashtable non lancia NullPointerException, per cui lo lancio manualmente
		if (key == null)
			throw new NullPointerException();

		// richiamo il corrispettivo metodo della hashtable e ne restituisco il risultato
		return hashtable.get(key);
	}

	/**
	 * Inserisce una nuova mappatura chiave-valore all'interno della mappa. Se la mappa contiene già la chiave
	 * specificata, il valore mappato viene sovrascritto e quello vecchio viene restituito dal metodo, altrimenti se
	 * la chiave non è presente il metodo restituisce {@code null}. Per scelta implementativa la mappa non supporta
	 * chiavi o valori {@code null}, per cui se la chiave o il valore sono {@code null}, il metodo lancia
	 * {@code NullPointerException}.
	 * 
	 * @param key chiave da associare al nuovo valore da inserire nella mappa.
	 * @param value nuovo valore da associare alla chiave specificata.
	 * @return valore precedentemente associato alla chiave specificata (se la chiave è già presente), altrimenti
	 * {@code null}.
	 * @throws NullPointerException se la chiave o il valore passati sono {@code null}.
	 * @throws IllegalArgumentException se la chiave corrisponde alla mappa.
	 */
	public Object put(Object key, Object value) {
		// per come è stata definita l'interfaccia, non si può avere la mappa stessa come chiave di qualche entry
		// per cui se la chiave passata corrisponde alla mappa, lancio una IllegalArgumentException
		if(key == this)
			throw new IllegalArgumentException();

		// NullPointerException già lanciato dal metodo put() della hashtable
		// richiamo il corrispettivo metodo della hashtable e ne restituisco il risultato
		return hashtable.put(key, value);
	}

	/**
	 * Rimuove dalla mappa la mappatura chiave-valore che coinvolge la chiave specificata e ne restituisce il valore
	 * precedentemente associato alla chiave. Se la chiave non è presente nella mappa, non viene eseguita nessuna
	 * operazione e viene restituito {@code null}. Siccome i valori {@code null} non sono supportati, se il metodo
	 * restituisce {@code null} significa che sicuramente la chiave passata non è presente all'interno della mappa.
	 *
	 * @param key chiave associata alla mappatura chiave-valore da rimuovere.
	 * @return valore della mappatura rimossa, {@code null} se la chiave non è presente nella mappa.
	 * @throws NullPointerException se la chiave passata è {@code null}.
	 */
	public Object remove(Object key) {
		// remove() della hashtable non lancia NullPointerException, per cui lo lancio manualmente
		if (key == null)
			throw new NullPointerException();

		// richiamo il corrispettivo metodo della hashtable e ne restituisco il risultato
		return hashtable.remove(key);
	}

	/**
	 * Copia tutte le mappature chiave-valore contenute nella mappa {@code m} passata come parametro all'interno
	 * della mappa su cui il metodo è invocato. L'effetto di questa chiamata è equivalente a quello di chiamare
	 * {@link #put(Object, Object) put(k, v)} su questa mappa una volta per ogni mappatura dalla chiave {@code k} al
	 * valore {@code v} nella mappa specificata.
	 * 
	 * @param m mappa da cui importare le mappature chiave-valore.
	 * @throws NullPointerException se la mappa {@code m} è {@code null}, oppure se una delle entry da inserire possiede
	 * chiave o valore {@code null}.
	 * @throws IllegalArgumentException se una delle entry da inserire ha come chiave la mappa su cui il metodo è invocato.
	 */
	public void putAll(HMap m) {
		// lacio l'eccezione se la mappa passata è null, altrimenti avrei un'invocazione di metodi su oggetti null
		if (m == null)
			throw new NullPointerException();

		// creo l'iteratore per la mappa da cui copiare i valori
		HIterator i = m.entrySet().iterator();

		// itero con l'iteratore su tutti gli elementi della mappa passata come parametro
		while (i.hasNext()) {
			// leggo la entry dall'iteratore ed effettuo un casting lecito e regolare (Object -> HEntry) perché
			// l'iteratore itera su un HSet costituito da oggetti HEntry (per come definito l'entrySet() della mappa)
			HEntry newEntry = (HEntry) i.next();

			// inserisco la nuova entry nella mappa
			this.put(newEntry.getKey(), newEntry.getValue());
		}
	}

	/**
	 * Rimuove tutte le mappature chiave-valore contenute nella mappa.
	 */
	public void clear() {
		// richiamo il corrispettivo metodo della hashtable
		hashtable.clear();
	}

	/**
	 * Restituisce una set view contenente tutte le chiavi delle mappature chiave-valore presenti all'interno della
	 * mappa. Il set restituito e la mappa agiscono sulla stessa sottostruttura quindi le modifiche che avvengono sulla
	 * mappa si riflettono sul set e viceversa. Se la mappa viene modificata mentre viene visitata da un iteratore,
	 * il comportamento dell'iteratore non è definito. Il set restituito supporta la rimozione delle chiavi tramite
	 * {@code Iterator.remove()}, {@code HSet.remove()}, {@code HSet.removeAll()}, {@code HSet.retainAll()}, ma non
	 * l'aggiunta di nuove chiavi, per cui i metodi {@code HSet.add()} e {@code Hset.addAll()} sono disabilitati.
	 * 
	 * @return un oggetto {@code Hset} view con le chiavi delle mappature chiave-valore contenute nella mappa
	 */
	public HSet keySet() {
		// creo e restituisco una nuova istanza di KeySetAdapter
		return new MapAdapter.KeySetAdapter();
	}

	/**
	 * Restituisce una collection view contenente tutti i valori (eventualmente duplicati) delle mappature
	 * chiave-valore presenti all'interno della mappa. La collection restituita e la mappa agiscono sulla stessa
	 * sottostruttura quindi le modifiche che avvengono sulla mappa si riflettono sulla collection e viceversa. Se la
	 * mappa viene modificata mentre viene visitata da un iteratore, il comportamento dell'iteratore non è definito.
	 * La collection restituita supporta la rimozione dei valori tramite {@code Iterator.remove()},
	 * {@code HCollection.remove()}, {@code HCollection.removeAll()}, {@code HCollection.retainAll()}, ma non l'aggiunta
	 * di nuove chiavi, per cui i metodi {@code HCollection.add()} e {@code HCollection.addAll()} sono disabilitati.
	 * 
	 * @return un oggetto {@code HCollection} view con i valori (eventualmente duplicati) delle mappature chiave-valore
	 * contenute nella mappa
	 */
	public HCollection values() {
		// creo e restituisco una nuova istanza di ValuesCollectionAdapter
		return new MapAdapter.ValuesCollectionAdapter();
	}

	/**
	 * Restituisce una set view contenente tutte le mappature chiave-valore presenti all'interno della mappa. Il set
	 * restituito e la mappa agiscono sulla stessa sottostruttura quindi le modifiche che avvengono sulla mappa si
	 * riflettono sul set e viceversa. Se la mappa viene modificata mentre viene visitata da un iteratore, il
	 * comportamento dell'iteratore non è definito. Il set restituito supporta la rimozione delle entry tramite
	 * {@code Iterator.remove()}, {@code HSet.remove()}, {@code HSet.removeAll()}, {@code HSet.retainAll()}, ma non
	 * l'aggiunta di nuove entry, per cui i metodi {@code HSet.add()} e {@code Hset.addAll()} sono disabilitati.
	 * 
	 * @return un oggetto {@code Hset} view con le mappature chiave-valore contenute nella mappa.
	 */
	public HSet entrySet() {
		// creo e restituisco una nuova istanza di EntrySetAdapter
		return new MapAdapter.EntrySetAdapter();
	}

	/**
	 * Confronta la mappa da cui è invocato con l'oggetto {@code o} passato come parametro. Se l'oggetto {@code o} è una
	 * mappa (implementa {@code HMap}) e contiene le stesse mappature chiave-valore della mappa da cui è invocato il
	 * metodo, allora il metodo restituisce {@code true}, altrimenti {@code false}. Per fare ciò si sfrutta il metodo
	 * {@code HSet.equals} invocato sul set restiutito dalla vista {@code entrySet()}:
	 * <pre>
	 *   (e1.entrySet().equals(e2.entrySet()))
	 * </pre>
	 * In questo modo il metodo è indipendente dal tipo di implementazione delle due mappe da confrontare.
	 * 
	 * @param o oggetto da confrontare con la mappa da cui è invocato il metodo.
	 * @return {@code true} se {@code o} è una mappa e contiene le stesse mappature chiave-valore della mappa da cui
	 * è invocato il metodo, {@code false} altrimenti.
	 */
	public boolean equals(Object o) {
		// verifico che l'oggetto passato non sia null
		if (o == null)
			return false;

		// verifico che l'oggetto passato sia una mappa
		if (!(o instanceof HMap))
			return false;

		// confronto le mappature tra le due mappe sfruttando la vista entrySet, si effettua un casting lecito
		// (Object -> HMap) perché ho effettuato sopra il controllo "if o is instanceof HMap"
		return this.entrySet().equals(((HMap)o).entrySet());
	}

	/**
	 * Restituisce il valore hash code della mappa. Il codice hash è definito come la somma dei codici hash di tutte le
	 * entry (mappature chiave-valore) nella mappa ottenute attraverso il set restituito da {@code entrySet()}. In
	 * questo modo si garantisce che due mappe {@code m1} e {@code m2} che soddisfano {@code m1.equals(m2)} abbiano lo
	 * stesso codice hash.
	 *
	 * @return il valore hash code per questa mappa.
	 */
	public int hashCode() {
		// inizializzo l'accumulatore dei codici hash
		int hash = 0;

		// creo un iteratore per visitare tutte le entry
		HIterator i = this.entrySet().iterator();

		// per ogni entry aggiungo il codice hash all'accumulatore
		while (i.hasNext())
			hash += i.next().hashCode();

		// resitutisco il codice hash
		return hash;
	}

	/**
	 * Restituisce una stringa che rappresenta la mappa. La stringa è formattata come una lista di mappature
	 * chiave-valore separate da virgole, racchiuse tra parentesi graffe. Ad esempio, se la mappa contiene le
	 * mappature {@code "a" -> 1}, {@code "b" -> 2} e {@code "c" -> 3}, la stringa restituita sarà:
	 * <pre>
	 *   "{a=1, b=2, c=3}"
	 * </pre>
	 *
	 * @return una stringa che rappresenta la mappa.
	 */
	public String toString() {
		return hashtable.toString();
	}


/* ================================================================================================================= *\
|* ========================================== CLASSE ANNIDATA EntryAdapter ========================================= *|
\* ================================================================================================================= */

	/**
	 * Classe annidata per la gestione delle mappature chiave-valore (chiamate anche entry) all'interno della
	 * classe {@link MapAdapter}.
	 * 
	 * <p>
	 * L'unico modo per ottenere un'istanza di questa classe è attraverso l'iteratore della vista {@code entrySet}
	 * ottenuta dal metodo {@link MapAdapter#entrySet()}. Gli oggetti {@code EntryAdapter} sono validi solo per la
	 * durata dell'iterazione e non riflettono eventuali modifiche alla mappa.
	 * 
	 * <p>
	 * Il comportamento dei metodi invocati su una istanza EntryAdapter non è definito se la mappa a cui appartiene
	 * viene modificata durante l'iterazione in qualsiasi modo diverso dall'operazione {@code setValue} sulla entry
	 * della mappa. Bisogna fare particolare attenzione al duplice comportamento del metodo {@code setValue} in base
	 * alla situazione in cui la entry è stata rimossa o meno dalla mappa.
	 * 
	 * <p>
	 * Siccome la {@code MapAdapter} non supporta chiavi o valori {@code null}, per coerenza implementativa, nemmeno
	 * la classe {@code EntryAdapter} permette chiavi o valori {@code null}. Se si prova a creare una entry con chiave
	 * o valore {@code null}, viene lanciata un'eccezione {@link NullPointerException}.
	 * 
	 * @see MapAdapter
	 * @see MapAdapter#entrySet()
	 * @see EntryAdapter#setValue(Object)
	 */
	protected final class EntryAdapter implements HMap.HEntry {
		// ************************************************************************************************************
		// ------------------------------- Variabili d'istanza della classe EntryAdapter ------------------------------

		// variabili che memorizzano rispettivamente la chiave e il valore di una mappatura key-value
		private final Object key;
		private Object value;

		// ************************************************************************************************************
		// ----------------------------------- Costruttori della classe EntryAdapter ----------------------------------

		/**
		 * Costruttore con due parametri, accessibile solo alle classi all'interno della classe {@code MapAdapter}.
		 * Costruisce un oggetto {@code EntryAdapter} inizializzando chiave e valore secondo i parametri passati al
		 * metodo. Se vengono passati chiavi o valori {@code null}, viene lanciata l'eccezione
		 * {@link NullPointerException}, in conformità con le restrizioni della classe {@code MapAdapter}.
		 * 
		 * @param key chiave da inserire nella entry.
		 * @param value valore da inserire nella entry.
		 * @throws NullPointerException se la chiave o il valore passati sono {@code null}.
		 */
		private EntryAdapter(Object key, Object value) {
			// controllo che chiave e valore non siano null
			if (key == null || value == null)
				throw new NullPointerException();
			
			// inizializzo le variabili d'istanza
			this.key = key;
			this.value = value;
		}

		// ************************************************************************************************************
		// ------------------------------------ Metodi dell'interfaccia HMap.HEntry -----------------------------------

		/**
		 * Restituisce la chiave contenuta in questa entry.
		 *
		 * @return la chiave contenuta in questa entry.
		 */
		public Object getKey() {
			return key;
		}

		/**
		 * Restituisce il valore contenuto in questa entry. Se la mappatura è stata rimossa dalla mappa di sostegno
		 * (esempio: attraverso il metodo {@code remove} dell'iteratore), il risultato della chiamata a questo metodo
		 * non è definito.
		 *
		 * @return il valore contenuto in questa entry.
		 */
		public Object getValue() {
			return value;
		}

		/**
		 * Sostituisce il valore contenuto nella entry con il nuovo valore passato come parametro. Il valore
		 * precedentemente memorizzato viene restituito. I valori {@code null} non sono supportati, per cui
		 * se il valore passato è {@code null}, viene lanciata un'eccezione {@link NullPointerException}.
		 *
		 * <p>
		 * Secondo le specifiche dell'interfaccia {@code HMap.HEntry}, il metodo {@code setValue} dovrebbe avere
		 * comportamento indefinito se avvengono modifiche alla mappa durante l'iterazione (ad esempio se la entry viene
		 * rimossa o modificata dalla mappa, ma l'utente ne mantiene il riferimento). Tuttavia, per evitare confusione
		 * e per limitare gli effetti di eventuali errori di uso scorretto da parte dei programmatori, è stato scelto
		 * di implementare il metodo come segue:
		 * <ul>
		 *   <li>se la entry è ancora presente nella mappa, il metodo modifica il valore sia nell'istanza della entry che
		 *   nella mappa, e restituisce il valore precedentemente memorizzato nella entry;</li>
		 *   <li>se la entry è stata rimossa dalla mappa, il metodo modifica il valore solo nell'istanza della entry, ma
		 *   non nella mappa, e restituisce il valore precedentemente memorizzato nella entry.</li>
		 * </ul>
		 * 
		 * @param value nuovo valore da memorizzare nella entry al posto di quello vecchio.
		 * @return valore precedentemente memorizzato nella entry.
		 * @throws NullPointerException se il valore passato è {@code null}.
		 */
		public Object setValue(Object value) {
			// verifico che il nuovo valore da inserire non sia null
			if (value == null)
				throw new NullPointerException();
			
			// se la entry è ancora all'interno della mappa
			if (hashtable.containsKey(key) && hashtable.get(key).equals(this.value)) {
				this.value = value;
				return hashtable.put(key, value);
			}

			// se la entry è stata rimossa dalla mappa, ma l'utente ne ha conservato il riferimento
			else {
				Object oldValue = this.value;
				this.value = value;
				return oldValue;
			}
		}

		/**
		 * Confronta la entry da cui è invocato con l'oggetto {@code o} passato come parametro. Se l'oggetto {@code o}
		 * è una entry (implementa {@code HMap.Hentry}) ed entrambe contengono la stessa mappatura chiave-valore,
		 * allora il metodo restituisce {@code true}, altrimenti {@code false}. Più formalmente, due entry {@code e1}
		 * ed {@code e2} rappresentano la stessa mappatura se:
		 * <pre>
		 * e2 != null &amp;&amp; e1.getKey().equals(e2.getKey()) &amp;&amp; e1.getValue().equals(e2.getValue())
		 * </pre>
		 * 
		 * <p>
		 * Si osserva che la condizione di uguaglianza è stata semplificata rispetto a quella prevista dall'interfaccia
		 * in quanto la entry su cui si invoca il metodo non può avere chiave o valore {@code null}.
		 * 
		 * <p>
		 * Si osserva che il metodo non lancia {@code NullPointerException} se l'oggetto passato come parametro è
		 * {@code null}, ma restituisce semplicemente {@code false}, in conformità con le specifiche dell'interfaccia.
		 *
		 * @param o oggetto da confrontare con la entry su cui è invocato il metodo.
		 * @return {@code true} se {@code o} è una entry e contiene la stessa mappatura chiave-valore della entry
		 * da cui è invocato il metodo, {@code false} altrimenti.
		 */
		public boolean equals(Object o) {
			// verifico che l'oggetto passato non sia null
			if (o == null)
				return false;

			// verifico che l'oggetto passato sia una entry
			if (!(o instanceof HEntry))
				return false;
				
			// si effettua un casting lecito (Object -> HEntry) perché il controllo è stato effettuato appena sopra
			HEntry e = (HEntry) o;

			// confronto la mappatura tra le due entry secondo la formula indicata nel commento javadoc del metodo
			return this.getKey().equals(e.getKey()) && this.getValue().equals(e.getValue());
		}

		/**
		 * Restituisce il valore del codice hash della entry. Il codice hash è definito come la xor tra il codice hash della
		 * chiave e quello del valore contenuti nella entry. Più specificatamente:
		 * <pre>
		 * e.getKey().hashCode()) ^ e.getValue().hashCode())
		 * </pre>
		 * 
		 * <p>
		 * Si osserva che l'operazione di calcolo è stata semplificata rispetto a quella prevista dall'interfaccia
		 * in quanto la entry su cui si invoca il metodo non può avere chiave o valore {@code null}.
		 * 
		 * @return il valore hash code della entry.
		 */
		public int hashCode() {
			return this.getKey().hashCode() ^ this.getValue().hashCode();
		}

		public String toString() {
			// restituisco una stringa formattata con la chiave e il valore della entry
			return this.getKey().toString() + "=" + this.getValue().toString();
		}
	}


/* ================================================================================================================= *\
|* ================================== CLASSE ASTRATTA ANNIDATA AbstractViewAdapter ================================= *|
\* ================================================================================================================= */

	/**
	 * Classe astratta annidata contenente i metodi comuni delle tre viste della classe {@link MapAdapter} restituite
	 * dai metodi {@link MapAdapter#keySet()}, {@link MapAdapter#values()} e {@link MapAdapter#entrySet()}.
	 * 
	 * <p>
	 * Per una migliore organizzazione e manutenibilità del codice, i metodi comuni delle tre viste sono stati
	 * spostati in questa classe astratta. Gli unici metodi che devono essere implementati dalle classi concrete
	 * sono {@code contains(Object o)}, {@code iterator()} e {@code remove(Object o)}.
	 * 
	 * <p>
	 * Le tre viste della mappa (keySet, values, entrySet) sono backed views della mappa, ovvero sono viste che
	 * riflettono direttamente il contenuto della mappa sottostante. Pertanto, le modifiche apportate alla vista
	 * si riflettono nella mappa e viceversa. Da notare che si avrà un comportamento indefinito se la mappa viene
	 * modificata strutturalmente (ad esempio, se vengono aggiunti o rimossi elementi) mentre si sta iterando su
	 * una delle viste.
	 * 
	 * <p>
	 * Per come sono state definite le viste, i metodi {@code add(Object o)} e {@code addAll(HCollection c)} non
	 * sono supportati e lanciano un'eccezione {@code UnsupportedOperationException} se invocati.
	 *
	 * @see HMap
	 * @see HCollection
	 * @see MapAdapter
	 * @see MapAdapter#keySet()
	 * @see MapAdapter#values()
	 * @see MapAdapter#entrySet()
	 */
	protected abstract class AbstractViewAdapter implements HCollection {
		/**
		 * Costruttore di default della classe astratta per le viste dei metodi keySet, values, entrySet.
		 * È definito protected in quanto non può essere instanziato al di fuori della classe {@code MapAdapter}.
		 */
		protected AbstractViewAdapter() {
			// intentionally left blank
		}

		/**
		 * Restituisce il numero di elementi della vista, che è pari al numero di elementi contenuti nella mappa.
		 * Se la mappa contiene più di {@code Integer.MAX_VALUE} elementi, restituisce {@code Integer.MAX_VALUE}.
		 *
		 * @return il numero di elementi della vista.
		 */
		public int size() {
			// richiamo il corrispettivo metodo della hashtable e ne restituisco il risultato
			return hashtable.size();
		}

		/**
		 * Restituisce {@code true} se la vista non contiene elementi, ovvero se la mappa è vuota, {@code false} altrimenti.
		 *
		 * @return {@code true} se la vista non contiene elementi, {@code false} altrimenti.
		 */
		public boolean isEmpty() {
			// richiamo il corrispettivo metodo della hashtable e ne restituisco il risultato
			return hashtable.isEmpty();
		}

		/**
		 * Restituisce un array contenente tutti gli elementi della vista. L'ordine degli elementi nell'array corrisponde
		 * all'ordine in cui gli elementi vengono restituiti dall'iteratore della vista.
		 * 
		 * <p>
		 * L'array restituito è "sicuro" in quanto nessun riferimento di esso è mantenuto dalla vista (e di conseguenza
		 * dalla mappa). Il chiamante è quindi libero di modificare l'array restituito, ma non gli elementi contentuti.
		 * Infatti, per come sono gestiti gli oggetti in Java, se si modifica un elemento dell'array, si modifica
		 * anche l'elemento corrispondente nella mappa, provocando eventuali comportamenti indefiniti (es. modifica di
		 * chiavi in una vista keySet o dei valori di una entry in una vista entrySet).
		 *
		 * @return un array contenente tutti gli elementi della vista.
		 */
		public Object[] toArray() {
			Object[] array = new Object[size()];
			HIterator i = this.iterator();
			int index = 0;
			while (i.hasNext())
				array[index++] = i.next();
			return array;
		}

		/**
		 * Restituisce un array contenente tutti gli elementi della vista, come il metodo {@code toArray}. Se l'array
		 * passato come parametro è abbastanza grande, gli elementi della vista vengono copiati in esso e l'elemento
		 * immediatamente successivo all'ultimo viene impostato su {@code null}. In questo modo il chiamante può
		 * determinare la lunghezza dell'array, siccome {@code null} è un valore non valido per gli elementi delle viste.
		 * Se, invece, l'array passato è troppo piccolo, ne viene allocato uno nuovo di tipo {@code Object[]} con
		 * dimensione pari al numero di elementi contenuti nella vista e tali elementi vengono copiati in esso.
		 *
		 * <p>
		 * La documentazione afferma che il tipo runtime dell'array restituito dovrebbe essere identico dell'array
		 * specificato come parametro. Siccome le reflection di Java non sono disponibili in J2ME CLDC 1.1, non è
		 * possibile creare dinamicamente un array del tipo runtime dell'array passato come parametro. Per qesto motivo,
		 * se l'array passato come parametro non è abbastanza grande, viene allocato un nuovo array di {@code Object}.
		 * 
		 * <p>
		 * Come per il metodo {@code toArray}, l'ordine degli elementi nell'array corrisponde all'ordine in cui gli
		 * elementi vengono restituiti dall'iteratore della vista. Inoltre , l'array restituito è "sicuro" in quanto
		 * slegato dalle modifiche della vista (e di conseguenza della mappa).
		 *
		 * @param a l'array in cui devono essere memorizzati gli elementi della vista, se è abbastanza grande;
		 * altrimenti, un nuovo array di {@code Object} viene allocato per questo scopo.
		 * @return un array contenente tutti gli elementi della vista.
		 * @throws NullPointerException se l'array specificato è {@code null}.
		 */
		public Object[] toArray(Object[] a) {
			// verifica che l'array passato come parametro non sia null
			if (a == null)
				throw new NullPointerException();

			// se l'array passato è troppo piccolo, ne creiamo uno nuovo.
			if (a.length < this.size())
				a = new Object[this.size()];

			// popolo l'array con gli elementi della vista
			int i = 0;
			HIterator it = this.iterator();
			while (it.hasNext())
				a[i++] = it.next();

			// se l'array passato era abbastanza grande, l'elemento immediatamente successivo all'ultimo viene impostato
			// su null, in modo da permettere al chiamante di determinare la lunghezza dell'array
			if (a.length > this.size())
				a[this.size()] = null;

			return a;
		}

		/**
		 * Il metodo dovrebbe aggiungere un elemento alla vista. Siccome il metodo non è supportato da nessuna delle tre
		 * viste della mappa, alla fine non farà nulla e lancerà sempre l'eccezione {@code UnsupportedOperationException}.
		 *
		 * @param o elemento da aggiungere alla vista (e che non verrà mai aggiunto).
		 * @throws UnsupportedOperationException {@code add} non è supportato da nessuna delle tre viste della mappa.
		 */
		public boolean add(Object o) {
			// il metodo add non è supportato da nessuna delle tre viste della mappa
			throw new UnsupportedOperationException();
		}

		/**
		 * Restituisce {@code true} se la vista contiene tutti gli elementi della collezione specificata.
		 *
		 * @param c collezione con gli elementi di cui verificare la presenza nella vista (e di conseguenza nella mappa).
		 * @return {@code true} se la vista contiene tutti gli elementi della collezione specificata.
		 * @throws NullPointerException se la collezione specificata è {@code null} o se contiene uno o più elementi
		 * {@code null} (la mappa non supporta chiavi, valori e tantomeno entry {@code null}).
		 * @throws ClassCastException se uno o più elementi della collezione non sono compatibili con la vista
		 * (ad esempio, se la vista è una entrySet e la collezione contiene un elemento che non è una entry).
		 */
		public boolean containsAll(HCollection c) {
			// verifico che la collezione passata come parametro non sia null
			if (c == null)
				throw new NullPointerException();

			// verifico che tutti gli elementi della collezione siano contenuti nella vista, se è presente un elemento
			// null, sarà il metodo contains() della vista a lanciare NullPointerException
			HIterator i = c.iterator();
			while (i.hasNext())
				if (!this.contains(i.next()))
					return false;
			return true;
		}

		/**
		 * Il metodo dovrebbe aggiungere tutti gli elementi contenuti nella collezione passata come parametro alla vista.
		 * Siccome il metodo non è supportato da nessuna delle tre viste della mappa, alla fine non farà nulla e lancerà
		 * sempre l'eccezione {@code UnsupportedOperationException}.
		 *
		 * @param c collezione con gli elementi da aggiungere alla vista (e che non verranno mai aggiunti).
		 * @throws UnsupportedOperationException {@code addAll} non è supportato da nessuna delle tre viste della mappa.
		 */
		public boolean addAll(HCollection c) {
			// il metodo addAll non è supportato da nessuna delle tre viste della mappa
			throw new UnsupportedOperationException();
		}

		/**
		 * Rimuove tutti gli elementi contenuti nella vista (e di conseguenza anche dalla mappa) che sono anche contenuti
		 * nella collezione passata come parametro.
		 *
		 * @param c collezione degli elementi da rimuovere dalla vista (e di conseguenza dalla mappa).
		 * @return {@code true} se la vista (e di conseguenza la mappa) è cambiata a seguito della chiamata.
		 * @throws NullPointerException se la collezione specificata è {@code null} o se contiene uno o più elementi
		 * {@code null} (la mappa non supporta chiavi, valori e tantomeno entry {@code null}).
		 * @throws ClassCastException se uno o più elementi della collezione non sono compatibili con la vista (ad esempio,
		 * se la vista è una entrySet e la collezione contiene un elemento che non è una entry).
		 */
		public boolean removeAll(HCollection c) {
			// verifico che la collezione passata come parametro non sia null
			if (c == null)
				throw new NullPointerException();

			// rimuovo tutti gli elementi della collezione dalla vista (e di conseguenza dalla mappa), se è presente
			// un elemento null, sarà il metodo remove() della vista a lanciare NullPointerException
			boolean change = false;
			HIterator i = c.iterator();
			while (i.hasNext())
				if (this.remove(i.next()))
					change = true;
			return change;
		}

		/**
		 * Mantiene solo gli elementi della vista che sono anche contenuti nella collezione passata come parametro. In
		 * altre parole, rimuove dalla vista (e di conseguenza dalla mappa) tutti i suoi elementi che non sono contenuti
		 * nella collezione specificata.
		 *
		 * @param c collezione con gli elementi da mantenere nella vista (e di conseguenza nella mappa).
		 * @return {@code true} se la vista (e di conseguenza la mappa) è cambiata a seguito della chiamata.
		 * @throws NullPointerException se la collezione specificata è {@code null} o se contiene uno o più elementi
		 * {@code null} (la mappa non supporta chiavi, valori e tantomeno entry {@code null}).
		 */
		public boolean retainAll(HCollection c) {
			// verifico che la collezione passata come parametro non sia null
			if (c == null)
				throw new NullPointerException();

			// rimuovo tutti gli elementi della vista che non sono contenuti nella collezione, se è presente un
			// elemento null, sarà il metodo contains() della vista a lanciare NullPointer
			boolean change = false;
			HIterator i = this.iterator();
			while (i.hasNext()) {
				if (!c.contains(i.next())) {
					i.remove();
					change = true;
				}
			}
			return change;
		}

		/**
		 * Rimuove tutti gli elementi della vista e di conseguenza tutte le mappature chiave-valore dalla mappa.
		 */
		public void clear() {
			// richiamo il corrispettivo metodo della hashtable
			hashtable.clear();
		}

		 /**
		 * Restituisce il valore hash code della vista. Il codice hash (analogamente a quello della mappa) è definito
		 * come la somma dei codici hash di tutti gli elementi nella vista. In questo modo si garantisce che due viste
		 * {@code v1} e {@code v2} che soddisfano {@code v1.equals(v2)} abbiano lo stesso codice hash.
		 *
		 * @return il valore hash code per questa vista.
		 */
		public int hashCode() {
			// inizializzo l'accumulatore dei codici hash
			int hash = 0;

			// sommo i codici hash di tutti gli elementi della vista, sfriuttando l'iteratore della vista
			// gli elementi saranno tutti non null, quindi non è necessario effettuare controlli
			HIterator i = this.iterator();
			while (i.hasNext())
				hash += i.next().hashCode();
			
			// restituisco il codice hash
			return hash;
		}
		
		/**
		 * Restituisce una stringa che rappresenta la vista. La stringa è formattata come una lista di elementi
		 * separati da virgole, racchiusi tra parentesi graffe. Ad esempio, se la vista contiene gli elementi
		 * {@code "a"}, {@code "b"} e {@code "c"}, la stringa restituita sarà:
		 * <pre>
		 *   "{a, b, c}"
		 * </pre>
		 *
		 * @return una stringa che rappresenta la vista.
		 */
		public String toString() {
			// creo un StringBuffer per costruire la stringa
			StringBuffer sb = new StringBuffer("[");

			// inserisco gli elementi della vista nello StringBuffer
			HIterator i = this.iterator();
			while (i.hasNext()) {
				sb.append(i.next().toString());
				if (i.hasNext())
					sb.append(", ");
			}
			sb.append("]");

			// restituisco la stringa
			return sb.toString();
		}
	}

/* ================================================================================================================= *\
|* ========================================= CLASSE ANNIDATA KeySetAdapter ========================================= *|
\* ================================================================================================================= */
	
	/**
	 * Classe astratta annidata contenente i metodi specifici della vista keySet della classe {@link MapAdapter}
	 * restituita dal metodo {@link MapAdapter#keySet()}.
	 * 
	 * <p>
	 * Gli unici metodi che devono essere implementati sono {@code contains(Object o)}, {@code iterator()} e
	 * {@code remove(Object o)}, gli altri metodi sono già implementati nella classe {@code AbstractViewAdapter}.
	 * 
	 * <p>
	 * La vista {@code keySet} è una backed view della mappa, ovvero riflette direttamente il contenuto della mappa
	 * sottostante. Pertanto, le modifiche apportate alla vista si riflettono nella mappa e viceversa. Da notare che
	 * si avrà un comportamento indefinito se la mappa viene modificata strutturalmente (ad esempio, se vengono
	 * aggiunti o rimossi elementi) mentre si sta iterando su una delle viste.
	 * 
	 * <p>
	 * Per come sono state definite le viste, i metodi {@code add(Object o)} e {@code addAll(HCollection c)} non
	 * sono supportati e lanciano un'eccezione {@code UnsupportedOperationException} se invocati.
	 *
	 * @see HMap
	 * @see HSet
	 * @see MapAdapter#keySet()
	 * @see AbstractViewAdapter
	 */
	protected final class KeySetAdapter extends AbstractViewAdapter implements HSet {
		/**
		 * Costruttore di default della classe {@code KeySetAdapter} per la vista del metodo {@code keySet}. È definito
		 * {@code protected} in quanto non può essere instanziato al di fuori della classe {@code MapAdapter}.
		 */
		protected KeySetAdapter() {
			// intentionally left blank
		}

		/**
		 * Restituisce {@code true} se la vista {@code keySet} contiene l'elemento specificato, ovvero se la mappa
		 * contiene una entry con chiave uguale all'elemento passato, {@code false} altrimenti. Se l'elemento
		 * specificato è {@code null}, viene lanciata un'eccezione {@link NullPointerException}, in conformità
		 * con le specifiche della classe {@code MapAdapter}.
		 *
		 * @param o elemento la cui presenza nella {@code keySet} deve essere testata.
		 * @return {@code true} se la vista  contiene l'elemento specificato, {@code false} altrimenti.
		 * @throws NullPointerException se l'elemento specificato è {@code null}
		 */
		public boolean contains(Object o) {
			// verifico che l'oggetto passato non sia null
			if (o == null)
				throw new NullPointerException();
			
			// richiamo il corrispettivo metodo della hashtable e ne restituisco il risultato
			return hashtable.containsKey(o);
		}

		/**
		 * Restituisce un iteratore sugli elementi della vista {@code keySet}. Gli elementi sono restituiti senza
		 * un ordine particolare.
		 *
		 * @return un iteratore sugli elementi della vista {@code keySet}.
		 */
		public HIterator iterator() {
			// restituisco un nuovo iteratore per la vista keySet
			return new MapAdapter.KeySetIterator();
		}

		/**
		 * Rimuove l'elemento passato dalla vista {@code keySet} se è presente, ovvero rimuove dalla mappa la entry con
		 * chiave uguale all'elemento passato. Restituisce {@code true} se l'elemento specificato è stato rimosso,
		 * {@code false} altrimenti.
		 *
		 * @param o oggetto da rimuovere dalla vista (e di conseguenza dalla mappa), se presente.
		 * @return {@code true} se l'elemento specificato è stato rimosso, {@code false} altrimenti.
		 * @throws NullPointerException se l'elemento specificato è {@code null}
		 */
		public boolean remove(Object o) {
			// verifico che l'oggetto passato non sia null
			if (o == null)
				throw new NullPointerException();

			// richiamo il corrispettivo metodo della hashtable e restituisco l'esito della rimozione
			return hashtable.remove(o) != null;
		}

		/**
		 * Confronta la vista da cui è invocato il metodo con l'oggetto {@code o} passato come parametro. Se l'oggetto
		 * {@code o} è una keySet (implementa {@code KeySetAdapter}) ed entrambe le viste contengono gli stessi elementi,
		 * allora il metodo restituisce {@code true}, altrimenti {@code false}. 
		 * 
		 * <p>
		 * Si osserva che la condizione di uguaglianza è basata sul numero di elementi e sulla presenza di tutti gli
		 * elementi della vista {@code o} nella vista da cui è invocato il metodo. Il metodo non è stato implementato
		 * nella classe {@code AbstractViewAdapter} in modo da evitare che restituisca {@code true} se le due viste
		 * contengono gli stessi elementi, ma provengano da contesti diversi (es, {@code keySet()} e {@code values()}).
		 * 
		 * <p>
		 * Si osserva che il metodo non lancia {@code NullPointerException} se l'oggetto passato come parametro è
		 * {@code null}, ma restituisce semplicemente {@code false}, in conformità con le specifiche dell'interfaccia.
		 *
		 * @param o oggetto da confrontare con la vista su cui è invocato il metodo.
		 * @return {@code true} se {@code o} è una vista e contiene gli stessi elementi della vista su cui è invocato il
		 * metodo, {@code false} altrimenti.
		 */
		public boolean equals(Object o) {
			// se l'oggetto passato è lo stesso della vista da cui è invocato il metodo, allora sono uguali, in questo
			// caso non è necessario effettuare ulteriori controlli
			if (o == this)
				return true;

			// verifico che l'oggetto passato non sia null
			if (o == null)
				return false;

			// verifico che l'oggetto passato sia una vista
			if (!(o instanceof KeySetAdapter))
				return false;

			// si effettua un casting lecito (Object -> KeySetAdapter) perché il controllo è stato effettuato sopra
			KeySetAdapter v = (KeySetAdapter) o;

			// confronto il numero di elementi e la presenza di tutti gli elementi della vista, se la vista contiene
			// un elemento null, il metodo containsAll lancia NullPointerException o ClassCastException che è necessario
			// catturare e gestire per restituire false
			boolean areEqual = true;
			try {
				areEqual = this.size() == v.size() && this.containsAll(v);
			} catch (NullPointerException | ClassCastException e) {
				return false; 
			}
			return areEqual;
		}
	}

/* ================================================================================================================= *\
|* ==================================== CLASSE ANNIDATA ValuesCollectionAdapter ==================================== *|
\* ================================================================================================================= */
	
	/**
	 * Classe astratta annidata contenente i metodi specifici della vista values della classe {@link MapAdapter}
	 * restituita dal metodo {@link MapAdapter#values()}.
	 *
	 * 
	 * <p>
	 * Gli unici metodi che devono essere implementati sono {@code contains(Object o)}, {@code iterator()} e
	 * {@code remove(Object o)}, gli altri metodi sono già implementati nella classe {@code AbstractViewAdapter}.
	 * 
	 * <p>
	 * La vista {@code values} è una backed view della mappa, ovvero riflette direttamente il contenuto della mappa
	 * sottostante. Pertanto, le modifiche apportate alla vista si riflettono nella mappa e viceversa. Da notare che
	 * si avrà un comportamento indefinito se la mappa viene modificata strutturalmente (ad esempio, se vengono
	 * aggiunti o rimossi elementi) mentre si sta iterando su una delle viste.
	 * 
	 * <p>
	 * Per come sono state definite le viste, i metodi {@code add(Object o)} e {@code addAll(HCollection c)} non
	 * sono supportati e lanciano un'eccezione {@code UnsupportedOperationException} se invocati.
	 *
	 * @see HMap
	 * @see HSet
	 * @see MapAdapter#values()
	 * @see AbstractViewAdapter
	 */
	protected final class ValuesCollectionAdapter extends AbstractViewAdapter implements HCollection {
		/**
		 * Costruttore di default della classe {@code ValuesCollectionAdapter} per la vista del metodo {@code values}.
		 * È definito {@code protected} in quanto non può essere instanziato al di fuori della classe {@code MapAdapter}.
		 */
		protected ValuesCollectionAdapter() {
			// intentionally left blank
		}

		/**
		 * Restituisce {@code true} se la vista {@code values} contiene l'elemento specificato, ovvero se la
		 * mappa contiene una entry con valore uguale all'elemento passato, {@code false} altrimenti. Se l'elemento
		 * specificato è {@code null}, viene lanciata un'eccezione {@link NullPointerException}, in conformità con le
		 * specifiche della classe {@code MapAdapter}.
		 *
		 * @param o elemento la cui presenza nella {@code valuesCollection} deve essere testata.
		 * @return {@code true} se la vista  contiene l'elemento specificato, {@code false} altrimenti.
		 * @throws NullPointerException se l'elemento specificato è {@code null}
		 */
		public boolean contains(Object o) {
			// verifico che l'oggetto passato non sia null
			if (o == null)
				throw new NullPointerException();

			// richiamo il corrispettivo metodo della hashtable e ne restituisco il risultato
			return hashtable.contains(o);
		}

		/**
		 * Restituisce un iteratore sugli elementi della vista {@code values}. Gli elementi sono restituiti senza
		 * un ordine particolare.
		 *
		 * @return un iteratore sugli elementi della vista {@code values}.
		 */
		public HIterator iterator() {
			// restituisco un nuovo iteratore per la vista values
			return new MapAdapter.ValuesCollectionIterator();
		}

		/**
		 * Rimuove l'elemento passato dalla vista {@code values} se è presente, ovvero rimuove dalla mappa la entry con
		 * valore uguale all'elemento passato). Restituisce {@code true} se l'elemento specificato è stato rimosso,
				 * {@code false} altrimenti.
		 *
		 * @param o oggetto da rimuovere dalla vista (e di conseguenza dalla mappa), se presente.
		 * @return {@code true} se l'elemento specificato è stato rimosso, {@code false} altrimenti.
		 * @throws NullPointerException se l'elemento specificato è {@code null}
		 */
		public boolean remove(Object o) {
			// verifico che l'oggetto passato non sia null
			if (o == null)
				throw new NullPointerException();

			// uso l'iteratore della vista per individuare e rimuovere l'elemento passato come parametro
			HIterator it = this.iterator();
			while (it.hasNext()) {
				if (it.next().equals(o)) {
					it.remove();
					return true; // se l'elemento è stato trovato e rimosso, restituisco true
				}
			}

			// se l'elemento non è stato trovato, restituisco false
			return false;
		}

		/**
		 * Confronta la vista da cui è invocato il metodo con l'oggetto {@code o} passato come parametro. Se l'oggetto
		 * {@code o} è una values (implementa {@code ValuesCollectionAdapter}) ed entrambe le viste contengono gli stessi elementi,
		 * allora il metodo restituisce {@code true}, altrimenti {@code false}. 
		 * 
		 * <p>
		 * Si osserva che la condizione di uguaglianza è basata sul numero di elementi e sulla presenza di tutti gli
		 * elementi della vista {@code o} nella vista da cui è invocato il metodo. Il metodo non è stato implementato
		 * nella classe {@code AbstractViewAdapter} in modo da evitare che restituisca {@code true} se le due viste
		 * contengono gli stessi elementi, ma provengano da contesti diversi (es, {@code keySet()} e {@code values()}).
		 * 
		 * <p>
		 * Si osserva che il metodo non lancia {@code NullPointerException} se l'oggetto passato come parametro è
		 * {@code null}, ma restituisce semplicemente {@code false}, in conformità con le specifiche dell'interfaccia.
		 *
		 * @param o oggetto da confrontare con la vista su cui è invocato il metodo.
		 * @return {@code true} se {@code o} è una vista e contiene gli stessi elementi della vista su cui è invocato il
		 * metodo, {@code false} altrimenti.
		 */
		public boolean equals(Object o) {
			// se l'oggetto passato è lo stesso della vista da cui è invocato il metodo, allora sono uguali, in questo
			// caso non è necessario effettuare ulteriori controlli
			if (o == this)
				return true;

			// verifico che l'oggetto passato non sia null
			if (o == null)
				return false;

			// verifico che l'oggetto passato sia una vista
			if (!(o instanceof ValuesCollectionAdapter))
				return false;

			// si effettua un casting lecito (Object -> ValuesCollectionAdapter))) perché il controllo è stato effettuato sopra
			ValuesCollectionAdapter v = (ValuesCollectionAdapter) o;

			// confronto il numero di elementi e la presenza di tutti gli elementi della vista, se la vista contiene
			// un elemento null, il metodo containsAll lancia NullPointerException o ClassCastException che è necessario
			// catturare e gestire per restituire false
			boolean areEqual = true;
			try {
				areEqual = this.size() == v.size() && this.containsAll(v);
			} catch (NullPointerException | ClassCastException e) {
				return false; 
			}
			return areEqual;
		}
	}

/* ================================================================================================================= *\
|* ======================================== CLASSE ANNIDATA EntrySetAdapter ======================================== *|
\* ================================================================================================================= */

	/**
	 * Classe astratta annidata contenente i metodi specifici della vista entrySet della classe {@link MapAdapter}
	 * restituita dal metodo {@link MapAdapter#entrySet()}.
	 * 
	 * <p>
	 * Gli unici metodi che devono essere implementati sono {@code contains(Object o)}, {@code iterator()} e
	 * {@code remove(Object o)}, gli altri metodi sono già implementati nella classe {@code AbstractViewAdapter}.
	 * 
	 * <p>
	 * La vista {@code entrySet} è una backed view della mappa, ovvero riflette direttamente il contenuto della mappa
	 * sottostante. Pertanto, le modifiche apportate alla vista si riflettono nella mappa e viceversa. Da notare che
	 * si avrà un comportamento indefinito se la mappa viene modificata strutturalmente (ad esempio, se vengono
	 * aggiunti o rimossi elementi) mentre si sta iterando su una delle viste.
	 * 
	 * <p>
	 * Per come sono state definite le viste, i metodi {@code add(Object o)} e {@code addAll(HCollection c)} non
	 * sono supportati e lanciano un'eccezione {@code UnsupportedOperationException} se invocati.
	 *
	 * @see HMap
	 * @see HSet
	 * @see MapAdapter#entrySet()
	 * @see AbstractViewAdapter
	 */
	protected final class EntrySetAdapter extends AbstractViewAdapter implements HSet {
		/**
		 * Costruttore di default della classe {@code EntrySetAdapter} per la vista del metodo {@code entrySet}. È
		 * definito {@code protected} in quanto non può essere instanziato al di fuori della classe {@code MapAdapter}.
		 */
		protected EntrySetAdapter() {
			// intentionally left blank
		}

		/**
		 * Restituisce {@code true} se la vista {@code entrySet} contiene l'elemento specificato, ovvero se la mappa
		 * contiene una entry con stessa chiave e valore della entry passata, {@code false} altrimenti. Se l'elemento
		 * specificato è {@code null}, viene lanciata un'eccezione {@link NullPointerException}, in conformità con le
		 * specifiche della classe {@code MapAdapter}. Se l'elemento specificato non è una entry, viene lanciata un'
		 * eccezione {@link ClassCastException}.
		 *
		 * @param o elemento la cui presenza nella view deve essere testata.
		 * @return {@code true} se la vista contiene l'elemento specificato, {@code false} altrimenti.
		 * @throws NullPointerException se l'elemento specificato è {@code null}
		 * @throws ClassCastException se l'elemento specificato non è una entry
		 */
		public boolean contains(Object o) {
			// verifico che l'oggetto passato non sia null
			if (o == null)
				throw new NullPointerException();

			// verifico che l'oggetto passato sia una entry
			if (!(o instanceof HEntry))
				throw new ClassCastException();

			// si effettua un casting lecito (Object -> HEntry) perché il controllo è stato effettuato appena sopra
			HEntry e = (HEntry) o;

			// controllo se la chiave della entry passata è presente nella hashtable e se il valore associato corrisponde
			// a quello della entry passata
			return hashtable.containsKey(e.getKey()) && hashtable.get(e.getKey()).equals(e.getValue());
		}

		/**
		 * Restituisce un iteratore sugli elementi della vista {@code entrySet}. Gli elementi sono restituiti senza un
		 * ordine particolare.
		 *
		 * @return un iteratore sugli elementi della vista {@code entrySet}.
		 */
		public HIterator iterator() {
			// restituisco un nuovo iteratore per la vista entrySet
			return new MapAdapter.EntrySetIterator();
		}

		/**
		 * Rimuove l'elemento specificato dalla vista {@code entrySet} se è presente, ovvero rimuove dalla mappa la entry
		 * con stessa chiave e valore della entry passata. Restituisce {@code true} se l'elemento specificato è stato
		 * rimosso, {@code false} altrimenti.
		 *
		 * @param o oggetto da rimuovere dalla vista (e di conseguenza dalla mappa), se presente.
		 * @return {@code true} se l'elemento specificato è stato rimosso, {@code false} altrimenti.
		 * @throws NullPointerException se l'elemento specificato è {@code null}
		 * @throws ClassCastException se l'elemento specificato non è una entry
		 */
		public boolean remove(Object o) {
			// verifico che l'oggetto passato non sia null
			if (o == null)
				throw new NullPointerException();

			// verifico che l'oggetto passato sia una entry
			if (!(o instanceof HEntry))
				throw new ClassCastException();

			// se la entry è contenuta nella vista, la rimuovo dalla hashtable e restituisco true
			if (this.contains(o))
				return hashtable.remove(((HEntry) o).getKey()) != null; // se la entry è contenuta, la condizione sarà sempre vera

			// se la entry non è contenuta nella vista, restituisco false
			else
				return false;
		}

		/**
		 * Confronta la vista da cui è invocato il metodo con l'oggetto {@code o} passato come parametro. Se l'oggetto
		 * {@code o} è una entrySet (implementa {@code EntrySetAdapter}) ed entrambe le viste contengono gli stessi elementi,
		 * allora il metodo restituisce {@code true}, altrimenti {@code false}. 
		 * 
		 * <p>
		 * Si osserva che la condizione di uguaglianza è basata sul numero di elementi e sulla presenza di tutti gli
		 * elementi della vista {@code o} nella vista da cui è invocato il metodo. Il metodo non è stato implementato
		 * nella classe {@code AbstractViewAdapter} in modo da evitare che restituisca {@code true} se le due viste
		 * contengono gli stessi elementi, ma provengano da contesti diversi (es, {@code keySet()} e {@code values()}).
		 * 
		 * <p>
		 * Si osserva che il metodo non lancia {@code NullPointerException} se l'oggetto passato come parametro è
		 * {@code null}, ma restituisce semplicemente {@code false}, in conformità con le specifiche dell'interfaccia.
		 *
		 * @param o oggetto da confrontare con la vista su cui è invocato il metodo.
		 * @return {@code true} se {@code o} è una vista e contiene gli stessi elementi della vista su cui è invocato il
		 * metodo, {@code false} altrimenti.
		 */
		public boolean equals(Object o) {
			// se l'oggetto passato è lo stesso della vista da cui è invocato il metodo, allora sono uguali, in questo
			// caso non è necessario effettuare ulteriori controlli
			if (o == this)
				return true;

			// verifico che l'oggetto passato non sia null
			if (o == null)
				return false;

			// verifico che l'oggetto passato sia una vista
			if (!(o instanceof EntrySetAdapter))
				return false;

			// si effettua un casting lecito (Object -> EntrySetAdapter) perché il controllo è stato effettuato sopra
			EntrySetAdapter v = (EntrySetAdapter) o;

			// confronto il numero di elementi e la presenza di tutti gli elementi della vista, se la vista contiene
			// un elemento null, il metodo containsAll lancia NullPointerException o ClassCastException che è necessario
			// catturare e gestire per restituire false
			boolean areEqual = true;
			try {
				areEqual = this.size() == v.size() && this.containsAll(v);
			} catch (NullPointerException | ClassCastException e) {
				return false; 
			}
			return areEqual;
		}
	}


/* ================================================================================================================= *\
|* =================================== CLASSE ASTRATTA ANNIDATA AbstractIterator =================================== *|
\* ================================================================================================================= */
	
	/**
	 * Classe astratta annidata contenente i metodi comuni dei tre iteratori delle tre viste {@code keySet},
	 * {@code values} e {@code entrySet} della classe {@link MapAdapter}.
	 * 
	 * <p>
	 * Per una migliore organizzazione e manutenibilità del codice, i metodi comuni dei tre iteratori sono stati
	 * spostati in questa classe astratta. L'unico metodo che deve essere implementato dalle classi concrete
	 * è {@code next()}.
	 *
	 * <p>
	 * Se la mappa viene modificata strutturalmente (ad esempio, se vengono aggiunti o rimossi elementi) mentre si sta
	 * iterando su una delle viste, il comportamento dell'iteratore non è specificato e potrebbe portare a risultati
	 * imprevisti.
	 *
	 * @see HIterator
	 * @see MapAdapter
	 * @see KeySetAdapter
	 * @see ValuesCollectionAdapter
	 * @see EntrySetAdapter
	 */
	protected abstract class AbstractIterator implements HIterator {
		/** iteratore sulle chiavi della mappa di tipo {@code Enumeration} di CLDC 1.1 */
		protected Enumeration elements;
		 /** ultimo elemento estratto dall'iteratore, inizialmente {@code null} */
		protected Object lastExtracted;

		/**
		 * Costruttore di default per l'iteratore astratto. È definito {@code protected} in quanto non può essere
		 * instanziato al di fuori della classe {@code MapAdapter}. Inizializza l'enumerazione degli elementi con
		 * le chiavi della mappa e inizializza l'ultimo elemento estratto a {@code null}.
		 */
		protected AbstractIterator() {
			elements = hashtable.keys();
			lastExtracted = null;
		}

		/**
		 * Restituisce {@code true} se l'iterazione ha ancora degli elementi da visitare, {@code false} altrimenti. In
		 * altre parole, restituisce {@code true} se {@code next} restituirebbe un elemento anziché lanciare un'eccezione.
		 *
		 * @return {@code true} se l'iteratore ha ancora elementi da visitare, {@code false} altrimenti.
		 */
		public boolean hasNext() {
			// controllo se ci sono ancora elementi da visitare
			return elements.hasMoreElements();
		}

		/**
		 * Rimuove dalla collezione sottostante l'ultimo elemento restituito dall'iteratore. Questo metodo può essere
		 * chiamato solo una volta per ogni chiamata a {@code next}. Il comportamento di un iteratore non è specificato se
		 * la collezione sottostante viene modificata durante l'iterazione in qualsiasi modo diverso dalla chiamata di
		 * questo metodo.
		 * 
		 * @throws IllegalStateException se il metodo {@code next} non è ancora stato chiamato, o il metodo {@code remove}
		 * è già stato chiamato dopo l'ultima chiamata al metodo {@code next}.
		 */
		public void remove() {
			// se non è stato ancora chiamato next() o se è già stato chiamato remove() dopo l'ultimo next(),
			// lancio IllegalStateException
			if (lastExtracted == null)
				throw new IllegalStateException();
			
			// rimuovo l'ultimo elemento estratto dalla mappa, se non è null
			hashtable.remove(lastExtracted);

			// resetto l'ultimo elemento estratto a null, in modo da poter chiamare remove() solo dopo una nuova chiamata a next()
			// (se non lo facessi, l'iteratore potrebbe rimuovere lo stesso elemento più volte)
			lastExtracted = null;
		}
	}

/* ================================================================================================================= *\
|* ======================================== CLASSE ANNIDATA KeySetIterator ========================================= *|
\* ================================================================================================================= */
	
	/**
	 * Classe annidata contenente i metodi specifici dell'iteratore per la vista {@code keySet} della classe
	 * {@link MapAdapter}.
	 * 
	 * <p>
	 * L'unico metodo che deve essere implementato dalla classe concreta è {@code next()}, gli altri sono già
	 * stati implementati nella classe {@code AbstractIterator}.
	 *
	 * <p>
	 * Se la mappa viene modificata strutturalmente (ad esempio, se vengono aggiunti o rimossi elementi) mentre si sta
	 * iterando su una delle viste, il comportamento dell'iteratore non è specificato e potrebbe portare a risultati
	 * imprevisti.
	 *
	 * @see HIterator
	 * @see AbstractIterator
	 * @see MapAdapter
	 * @see KeySetAdapter
	 */
	protected final class KeySetIterator extends AbstractIterator {
		/**
		 * Costruttore di default dell'iteratore {@code KeySetIterator} per la vista del metodo {@code keySet}. È definito
		 * {@code protected} in quanto non può essere instanziato al di fuori della classe {@code MapAdapter}.
		 */
		protected KeySetIterator() {
			// intentionally left blank
		}

		/**
		 * Restituisce l'elemento successivo nell'iterazione, ovvero una chiave della vista {@code keySet}.
		 *
		 * @return l'elemento successivo nell'iterazione.
		 * @throws java.util.NoSuchElementException se l'iterazione non ha più elementi da visitare.
		 */
		public Object next() {
			// lancia già NoSuchElementException se necessario
			lastExtracted = elements.nextElement();
			return lastExtracted;
		}
	}

/* ================================================================================================================= *\
|* =================================== CLASSE ANNIDATA ValuesCollectionIterator ==================================== *|
\* ================================================================================================================= */
	
	/**
	 * Classe annidata contenente i metodi specifici dell'iteratore per la vista {@code values} della classe
	 * {@link MapAdapter}.
	 *
	 * <p>
	 * L'unico metodo che deve essere implementato dalla classe concreta è {@code next()}, gli altri sono già
	 * stati implementati nella classe {@code AbstractIterator}.
	 *
	 * <p>
	 * Se la mappa viene modificata strutturalmente (ad esempio, se vengono aggiunti o rimossi elementi) mentre si sta
	 * iterando su una delle viste, il comportamento dell'iteratore non è specificato e potrebbe portare a risultati
	 * imprevisti.
	 *
	 * @see HIterator
	 * @see AbstractIterator
	 * @see MapAdapter
	 * @see ValuesCollectionAdapter
	 */
	protected final class ValuesCollectionIterator extends AbstractIterator {
		/**
		 * Costruttore di default dell'iteratore {@code ValuesCollectionIterator} per la vista del metodo {@code values}.
		 * È definito {@code protected} in quanto non può essere instanziato al di fuori della classe {@code MapAdapter}.
		 */
		protected ValuesCollectionIterator() {
			// intentionally left blank
		}

		/**
		 * Restituisce l'elemento successivo nell'iterazione, ovvero un valore della vista {@code values}
		 *
		 * @return l'elemento successivo nell'iterazione.
		 * @throws java.util.NoSuchElementException se l'iterazione non ha più elementi da visitare.
		 */
		public Object next() {
			// lancia già NoSuchElementException se necessario
			lastExtracted = elements.nextElement();
			return hashtable.get(lastExtracted);
		}
	}

/* ================================================================================================================= *\
|* ======================================= CLASSE ANNIDATA EntrySetIterator ======================================== *|
\* ================================================================================================================= */
	
	/**
	 * Classe annidata contenente i metodi specifici dell'iteratore per la vista {@code entrySet} della classe
	 * {@link MapAdapter}.
	 *
	 * <p>
	 * L'unico metodo che deve essere implementato dalla classe concreta è {@code next()}, gli altri sono già
	 * stati implementati nella classe {@code AbstractIterator}.
	 *
	 * <p>
	 * Se la mappa viene modificata strutturalmente (ad esempio, se vengono aggiunti o rimossi elementi) mentre si sta
	 * iterando su una delle viste, il comportamento dell'iteratore non è specificato e potrebbe portare a risultati
	 * imprevisti.
	 *
	 * @see HIterator
	 * @see AbstractIterator
	 * @see MapAdapter
	 * @see EntryAdapter
	 * @see EntrySetAdapter
	 */
	protected final class EntrySetIterator extends AbstractIterator {
		/**
		 * Costruttore di default dell'iteratore {@code EntrySetIterator} per la vista del metodo {@code entrySet}. È
		 * definito {@code protected} in quanto non può essere instanziato al di fuori della classe {@code MapAdapter}.
		 */
		protected EntrySetIterator() {
			// intentionally left blank
		}

		/**
		 * Restituisce l'elemento successivo nell'iterazione, ovvero una entry della vista {@code entrySet}.
		 *
		 * @return l'elemento successivo nell'iterazione.
		 * @throws java.util.NoSuchElementException se l'iterazione non ha più elementi da visitare.
		 */
		public Object next() {
			// lancia già NoSuchElementException se necessario
			lastExtracted = elements.nextElement();
			return new EntryAdapter(lastExtracted, hashtable.get(lastExtracted));
		}
	}
}
