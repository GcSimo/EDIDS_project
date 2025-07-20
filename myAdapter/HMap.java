// Giacomo Simonetto - 2109923 - Secondo appello 2025 di Elementi di Ingegneria del Software

package myAdapter;

/* ================================================================================================================= *\
|* ========================================= HMap Interface - Java SE 1.4.2 ======================================== *|
\* ================================================================================================================= */

/**
 * Un oggetto che mappa chiavi a valori. Una mappa non può contenere chiavi duplicate; ogni chiave può mappare al
 * massimo un valore.
 *
 * <p>
 * Questa interfaccia sostituisce la classe {@code Dictionary}, che era una classe totalmente astratta piuttosto che
 * un'interfaccia.
 *
 * <p>
 * L'interfaccia {@code HMap} fornisce tre <i>viste di collezione</i>, che consentono di visualizzare il contenuto di
 * una mappa come un set di chiavi, una collezione di valori, o un set di mappature chiave-valore. L'<i>ordine</i> di
 * una mappa è definito come l'ordine in cui gli iteratori sulle viste di collezione della mappa restituiscono i loro
 * elementi. Alcune implementazioni di mappa, come la classe {@code TreeMap}, forniscono garanzie specifiche sul loro
 * ordine; altre, come la classe {@code HashMap}, non lo fanno.
 *
 * <p>
 * Nota: è necessario prestare molta attenzione se gli oggetti mutabili vengono utilizzati come chiavi della mappa. Il
 * comportamento di una mappa non è specificato se il valore di un oggetto viene modificato in un modo che influisce sui
 * confronti di uguaglianza mentre l'oggetto è una chiave nella mappa. Un caso speciale di questo divieto è che non è
 * consentito che una mappa contenga se stessa come chiave. Sebbene sia consentito che una mappa contenga se stessa come
 * valore, si consiglia estrema cautela: i metodi equals e hashCode non sono più ben definiti su una tale mappa.
 *
 * <p>
 * Tutte le classi di implementazione di mappa di uso generale dovrebbero fornire due costruttori "standard": un
 * costruttore vuoto (senza argomenti) che crea una mappa vuota, e un costruttore con un singolo argomento di tipo
 * {@code HMap}, che crea una nuova mappa con le stesse mappature chiave-valore del suo argomento. In effetti,
 * quest'ultimo costruttore consente all'utente di copiare qualsiasi mappa, producendo una mappa equivalente della
 * classe desiderata. Non c'è modo di imporre questa raccomandazione (poiché le interfacce non possono contenere
 * costruttori) ma tutte le implementazioni di mappa di uso generale nell'SDK si conformano.
 *
 * <p>
 * I metodi "distruttivi" contenuti in questa interfaccia, cioè i metodi che modificano la mappa su cui operano, sono
 * specificati per lanciare {@code UnsupportedOperationException} se questa mappa non supporta l'operazione. Se questo
 * è il caso, questi metodi possono, ma non sono tenuti a, lanciare una {@code UnsupportedOperationException} se
 * l'invocazione non avrebbe alcun effetto sulla mappa. Ad esempio, invocare il metodo {@link #putAll(HMap)} su una
 * mappa non modificabile può, ma non è tenuto a, lanciare l'eccezione se la mappa le cui mappature devono essere
 * "sovrapposte" è vuota.
 *
 * <p>
 * Alcune implementazioni di mappa hanno restrizioni sulle chiavi e sui valori che possono contenere. Ad esempio, alcune
 * implementazioni proibiscono chiavi e valori null, e alcune hanno restrizioni sui tipi delle loro chiavi. Tentare di
 * inserire una chiave o un valore non idoneo lancia un'eccezione non controllata, tipicamente
 * {@code NullPointerException} o {@code ClassCastException}. Tentare di interrogare la presenza di una chiave o un
 * valore non idoneo può lanciare un'eccezione, o può semplicemente restituire false; alcune implementazioni mostreranno
 * il primo comportamento e alcune mostreranno il secondo. Più in generale, tentare un'operazione su una chiave o un
 * valore non idoneo il cui completamento non risulterebbe nell'inserimento di un elemento non idoneo nella mappa può
 * lanciare un'eccezione o può avere successo, a discrezione dell'implementazione. Tali eccezioni sono contrassegnate
 * come "opzionali" nella specifica per questa interfaccia.
 *
 * <p>
 * Questa interfaccia è un membro del
 * <a href="https://docs.oracle.com/javase/1.4.2/docs/guide/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @see HMap.HEntry
 * @see HCollection
 * @see HSet
 */
public interface HMap {
	/**
	 * Restituisce il numero di mappature chiave-valore in questa mappa. Se la mappa contiene più di
	 * {@code Integer.MAX_VALUE} elementi, restituisce {@code Integer.MAX_VALUE}.
	 *
	 * @return il numero di mappature chiave-valore in questa mappa.
	 */
	public int size();

	/**
	 * Restituisce {@code true} se questa mappa non contiene mappature chiave-valore.
	 *
	 * @return {@code true} se questa mappa non contiene mappature chiave-valore.
	 */
	public boolean isEmpty();

	/**
	 * Restituisce {@code true} se questa mappa contiene una mappatura per la chiave specificata. Più formalmente,
	 * restituisce {@code true} se e solo se questa mappa contiene una mappatura per una chiave {@code k} tale che
	 * {@code (key==null ? k==null : key.equals(k))}. (Può esserci al massimo una tale mappatura.)
	 *
	 * @param key chiave la cui presenza in questa mappa deve essere testata.
	 * @return {@code true} se questa mappa contiene una mappatura per la chiave specificata.
	 * @throws ClassCastException se la chiave è di un tipo inappropriato per questa mappa (opzionale).
	 * @throws NullPointerException se la chiave è {@code null} e questa mappa non consente chiavi {@code null}
	 * (opzionale).
	 */
	public boolean containsKey(Object key);

	/**
	 * Restituisce {@code true} se questa mappa mappa una o più chiavi al valore specificato. Più formalmente,
	 * restituisce {@code true} se e solo se questa mappa contiene almeno una mappatura a un valore {@code v} tale che
	 * {@code (value==null ? v==null : value.equals(v))}. Questa operazione richiederà probabilmente un tempo lineare
	 * rispetto alla dimensione della mappa per la maggior parte delle implementazioni dell'interfaccia {@code HMap}.
	 *
	 * @param value valore la cui presenza in questa mappa deve essere testata.
	 * @return {@code true} se questa mappa mappa una o più chiavi al valore specificato.
	 * @throws ClassCastException se il valore è di un tipo inappropriato per questa mappa (opzionale).
	 * @throws NullPointerException se il valore è {@code null} e questa mappa non consente valori {@code null}
	 * (opzionale).
	 */
	public boolean containsValue(Object value);

	/**
	 * Restituisce il valore a cui questa mappa mappa la chiave specificata. Restituisce {@code null} se la mappa non
	 * contiene alcuna mappatura per questa chiave. Un valore di ritorno {@code null} non indica <i>necessariamente</i>
	 * che la mappa non contiene alcuna mappatura per la chiave; è anche possibile che la mappa mappi esplicitamente la
	 * chiave a {@code null}. L'operazione {@code containsKey} può essere utilizzata per distinguere questi due casi.
	 *
	 * <p>
	 * Più formalmente, se questa mappa contiene una mappatura da una chiave {@code k} a un valore {@code v} tale che
	 * {@code (key==null ? k==null : key.equals(k))}, allora questo metodo restituisce {@code v}; altrimenti restituisce
	 * {@code null}. (Ci può essere al massimo una tale mappatura.)
	 *
	 * @param key chiave il cui valore associato deve essere restituito.
	 * @return il valore a cui questa mappa mappa la chiave specificata, o {@code null} se la mappa non contiene alcuna
	 * mappatura per questa chiave.
	 * @throws ClassCastException se la chiave è di un tipo inappropriato per questa mappa (opzionale).
	 * @throws NullPointerException se la chiave è {@code null} e questa mappa non consente chiavi {@code null}
	 * (opzionale).
	 * @see #containsKey(Object)
	 */
	public Object get(Object key);

	/**
	 * Associa il valore specificato alla chiave specificata in questa mappa (operazione opzionale). Se la mappa
	 * conteneva precedentemente una mappatura per questa chiave, il vecchio valore viene sostituito dal valore
	 * specificato. (Una mappa {@code m} si dice che contenga una mappatura per una chiave {@code k} se e solo se
	 * {@link #containsKey(Object) m.containsKey(k)} restituirebbe {@code true}.)
	 *
	 * @param key chiave con cui il valore specificato deve essere associato.
	 * @param value valore da associare alla chiave specificata.
	 * @return valore precedente associato alla chiave specificata, o {@code null} se non c'era alcuna mappatura per la
	 * chiave. Un ritorno {@code null} può anche indicare che la mappa in precedenza associava {@code null} alla chiave
	 * specificata, se l'implementazione supporta valori {@code null}.
	 * @throws UnsupportedOperationException se l'operazione {@code put} non è supportata da questa mappa.
	 * @throws ClassCastException se la classe della chiave o del valore specificato impedisce che venga memorizzato in
	 * questa mappa.
	 * @throws IllegalArgumentException se qualche aspetto di questa chiave o valore impedisce che venga memorizzato in
	 * questa mappa.
	 * @throws NullPointerException se questa mappa non consente chiavi o valori {@code null}, e la chiave o il valore
	 * specificato è {@code null}.
	 */
	public Object put(Object key, Object value);

	/**
	 * Rimuove la mappatura per questa chiave da questa mappa se è presente (operazione opzionale). Più formalmente, se
	 * questa mappa contiene una mappatura dalla chiave {@code k} al valore {@code v} tale che
	 * {@code (key==null ? k==null : key.equals(k))}, quella mappatura viene rimossa. (La mappa può contenere al massimo
	 * una tale mappatura.)
	 *
	 * <p>
	 * Restituisce il valore a cui la mappa in precedenza associava la chiave, o {@code null} se la mappa non conteneva
	 * alcuna mappatura per questa chiave. (Un ritorno {@code null} può anche indicare che la mappa in precedenza
	 * associava {@code null} alla chiave specificata se l'implementazione supporta valori {@code null}.) La mappa non
	 * conterrà una mappatura per la chiave specificata una volta che la chiamata ritorna.
	 *
	 * @param key chiave la cui mappatura deve essere rimossa dalla mappa.
	 * @return valore precedente associato alla chiave specificata, o {@code null} se non c'era alcuna mappatura per la
	 * chiave.
	 * @throws ClassCastException se la chiave è di un tipo inappropriato per questa mappa (opzionale).
	 * @throws NullPointerException se la chiave è {@code null} e questa mappa non consente chiavi {@code null}
	 * (opzionale).
	 * @throws UnsupportedOperationException se il metodo {@code remove} non è supportato da questa mappa.
	 */
	public Object remove(Object key);

	/**
	 * Copia tutte le mappature dalla mappa specificata a questa mappa (operazione opzionale). L'effetto di questa
	 * chiamata è equivalente a quello di chiamare {@link #put(Object, Object) put(k, v)} su questa mappa una volta per
	 * ogni mappatura dalla chiave {@code k} al valore {@code v} nella mappa specificata. Il comportamento di questa
	 * operazione non è specificato se la mappa specificata viene modificata mentre l'operazione è in corso.
	 *
	 * @param m Mappature da memorizzare in questa mappa.
	 * @throws UnsupportedOperationException se il metodo {@code putAll} non è supportato da questa mappa.
	 * @throws ClassCastException se la classe di una chiave o di un valore nella mappa specificata impedisce che venga
	 * memorizzato in questa mappa.
	 * @throws IllegalArgumentException se qualche aspetto di una chiave o di un valore nella mappa specificata
	 * impedisce che venga memorizzato in questa mappa.
	 * @throws NullPointerException se la mappa specificata è {@code null}, o se questa mappa non consente chiavi o
	 * valori {@code null}, e la mappa specificata contiene chiavi o valori {@code null}.
	 */
	public void putAll(HMap m);

	/**
	 * Rimuove tutte le mappature da questa mappa (operazione opzionale).
	 *
	 * @throws UnsupportedOperationException se clear non è supportato da questa mappa.
	 */
	public void clear();

	/**
	 * Restituisce una vista set delle chiavi contenute in questa mappa. Il set è supportato dalla mappa, quindi i
	 * cambiamenti alla mappa si riflettono nel set, e viceversa. Se la mappa viene modificata mentre un'iterazione sul
	 * set è in corso, i risultati dell'iterazione non sono definiti. Il set supporta la rimozione di elementi, che
	 * rimuove la mappatura corrispondente dalla mappa, tramite le operazioni {@code HIterator.remove},
	 * {@code HSet.remove}, {@code removeAll}, {@code retainAll} e {@code clear}.Non supporta le operazioni {@code add} o
	 * {@code addAll}.
	 *
	 * @return una vista set delle chiavi contenute in questa mappa.
	 */
	public HSet keySet();

	/**
	 * Restituisce una vista collezione dei valori contenuti in questa mappa. La collezione è supportata dalla mappa,
	 * quindi i cambiamenti alla mappa si riflettono nella collezione, e viceversa. Se la mappa viene modificata mentre
	 * un'iterazione sulla collezione è in corso, i risultati dell'iterazione non sono definiti. La collezione supporta
	 * la rimozione di elementi, che rimuove la mappatura corrispondente dalla mappa, tramite le operazioni
	 * {@code HIterator.remove}, {@code HCollection.remove}, {@code removeAll}, {@code retainAll} e {@code clear}. Non
	 * supporta le operazioni {@code add} o {@code addAll}.
	 *
	 * @return una vista collezione dei valori contenuti in questa mappa.
	 */
	public HCollection values();

	/**
	 * Restituisce una vista set delle mappature contenute in questa mappa. Ogni elemento nel set restituito è un
	 * {@link HMap.HEntry}. Il set è supportato dalla mappa, quindi i cambiamenti alla mappa si riflettono nel set, e
	 * viceversa. Se la mappa viene modificata mentre un'iterazione sul set è in corso, i risultati dell'iterazione non
	 * sono definiti. Il set supporta la rimozione di elementi, che rimuove la mappatura corrispondente dalla mappa,
	 * tramite le operazioni {@code HIterator.remove}, {@code HSet.remove}, {@code removeAll}, {@code retainAll} e
	 * {@code clear}. Non supporta le operazioni {@code add} o {@code addAll}.
	 *
	 * @return una vista set delle mappature contenute in questa mappa.
	 */
	public HSet entrySet();

	/**
	 * Confronta l'oggetto specificato con questa mappa per l'uguaglianza. Restituisce {@code true} se l'oggetto dato è
	 * anch'esso una mappa e le due Mappe rappresentano le stesse mappature. Più formalmente, due mappe {@code t1} e
	 * {@code t2} rappresentano le stesse mappature se {@code t1.entrySet().equals(t2.entrySet())}. Questo assicura che
	 * il metodo {@code equals} funzioni correttamente tra diverse implementazioni dell'interfaccia {@code HMap}.
	 *
	 * @param o oggetto da confrontare per l'uguaglianza con questa mappa.
	 * @return {@code true} se l'oggetto specificato è uguale a questa mappa.
	 * @see Object#hashCode()
	 */
	public boolean equals(Object o);

	/**
	 * Restituisce il valore hash code per questa mappa. L'hash code di una mappa è definito come la somma degli hash
	 * code di ogni entry nella vista entrySet della mappa. Questo assicura che {@code t1.equals(t2)} implichi che
	 * {@code t1.hashCode()==t2.hashCode()} per qualsiasi due mappe {@code t1} e {@code t2}, come richiesto dal
	 * contratto generale di Object.hashCode.
	 *
	 * @return il valore hash code per questa mappa.
	 * @see HMap.HEntry#hashCode()
	 * @see Object#hashCode()
	 * @see Object#equals(Object)
	 * @see #equals(Object)
	 */
	public int hashCode();


	/* ============================================================================================================= *\
	|* =================================== HMap.HEntry Interface - Java SE 1.4.2 =================================== *|
	\* ============================================================================================================= */

	/**
	 * Una entry di mappa (coppia chiave-valore). Il metodo {@code HMap.entrySet} restituisce una vista-collezione della
	 * mappa, i cui elementi sono di questa classe. L'unico modo per ottenere un riferimento a una entry di mappa è
	 * dall'iteratore di questa vista-collezione. Questi oggetti {@code HMap.Entry} sono validi solo per la durata
	 * dell'iterazione; il comportamento di una entry di mappa non è definito se la mappa sottostante è stata modificata
	 * durante l'iterazione in qualsiasi modo diverso dall'operazione {@code setValue} sulla entry di mappa.
	 *
	 * @see HMap
	 * @see HMap#entrySet()
	 */
	public static interface HEntry {
		/**
		 * Restituisce la chiave corrispondente a questa entry.
		 *
		 * @return la chiave corrispondente a questa entry.
		 */
		public Object getKey();

		/**
		 * Restituisce il valore corrispondente a questa entry. Se la mappatura è stata rimossa dalla mappa di sostegno
		 * (attraverso il metodo {@code remove} dell'iteratore), il risultato della chiamata a questo metodo non è definito.
		 *
		 * @return il valore corrispondente a questa entry.
		 */
		public Object getValue();

		/**
		 * Sostituisce il valore corrispondente a questa entry con il valore specificato (optional operation). (Scrive
		 * direttamente sulla mappa.) Il comportamento della chiamata a questo metodo non è definito se la mappatura
		 * è già stata rimossa dalla mappa (attraverso metodo {@code remove} dell'iteratore).
		 *
		 * @param value nuovo valore da memorizzare in questa entry.
		 * @return il vecchio valore corrispondente alla entry.
		 * @throws UnsupportedOperationException se l'operazione {@code put} non è supportata dalla mappa sottostante.
		 * @throws ClassCastException se la classe del valore specificato impedisce che venga memorizzato in questa
		 * mappa.
		 * @throws IllegalArgumentException se qualche proprietà di questo valore impedisce che venga memorizzato in
		 * questa mappa.
		 * @throws NullPointerException se il valore specificato è null e questa mappa non consente valori null.
		 */
		public Object setValue(Object value);

		/**
		 * Confronta l'oggetto specificato con questa entry per l'uguaglianza. Restituisce {@code true} se l'oggetto
		 * dato è anch'esso una entry di mappa e le due entry rappresentano la stessa mappatura. Più formalmente, due
		 * entry {@code e1} ed {@code e2} rappresentano la stessa mappatura se
		 *
		 * <pre>
		 *  (e1.getKey() == null ? e2.getKey() == null : e1.getKey().equals(e2.getKey())) &amp;&amp;
		 * (e1.getValue() == null ? e2.getValue() == null : e1.getValue().equals(e2.getValue()))
		 * </pre>
		 *
		 * Questo assicura che il metodo {@code equals} funzioni correttamente tra diverse implementazioni
		 * dell'interfaccia {@code HMap.HEntry}.
		 *
		 * @param o oggetto da confrontare per l'uguaglianza con questa entry di mappa.
		 * @return {@code true} se l'oggetto specificato è uguale a questa entry di mappa.
		 * @see Object#hashCode()
		 */
		public boolean equals(Object o);

		/**
		 * Restituisce il valore hash code per questa entry di mappa. L'hash code di una entry di mappa {@code e}
		 * è definito come:
		 *
		 * <pre>
		 *  (e.getKey() == null ? 0 : e.getKey().hashCode()) ^
		 * (e.getValue() == null ? 0 : e.getValue().hashCode())
		 * </pre>
		 *
		 * Questo assicura che {@code e1.equals(e2)} implichi che {@code e1.hashCode()==e2.hashCode()} per qualsiasi
		 * due entry {@code e1} ed {@code e2}.
		 *
		 * @return il valore hash code per questa entry di mappa.
		 * @see Object#hashCode()
		 * @see Object#equals(Object)
		 * @see HMap.HEntry#equals(Object)
		 */
		public int hashCode();
	}
}
