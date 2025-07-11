package myAdapter;

/* ----------------------------------------------------------------------------------------------------------------- *\
|* ----------------------------------------- HSet Interface - Java SE 1.4.2 ---------------------------------------- *|
\* ----------------------------------------------------------------------------------------------------------------- */

/**
 * Una collezione che non contiene elementi duplicati. Più formalmente, i set
 * non contengono alcuna coppia di elementi {@code e1} ed {@code e2} tale che
 * {@code e1.equals(e2)}, e al massimo un elemento null. Come implicato dal
 * suo nome, questa interfaccia modella l'astrazione matematica di <i>set</i>.
 * <p>
 *
 * L'interfaccia {@code HSet} impone stipulazioni aggiuntive, oltre a quelle
 * ereditate dall'interfaccia {@code HCollection}, sui contratti di tutti
 * i costruttori e sui contratti dei metodi {@code add}, {@code equals} e
 * {@code hashCode}. Le dichiarazioni per altri metodi ereditati sono
 * incluse qui per comodità. (Le specifiche che accompagnano queste
 * dichiarazioni sono state adattate all'interfaccia {@code HSet}, ma non
 * contengono alcuna stipulazione aggiuntiva.)
 * <p>
 *
 * La stipulazione aggiuntiva sui costruttori è, non sorprendentemente,
 * che tutti i costruttori devono creare un set che non contenga elementi duplicati
 * (come definito sopra).
 * <p>
 *
 * Nota: È necessario prestare molta attenzione se gli oggetti mutabili vengono utilizzati come elementi del set.
 * Il comportamento di un set non è specificato se il valore di un oggetto
 * viene modificato in un modo che influisce sui confronti di uguaglianza mentre l'oggetto è
 * un elemento nel set. Un caso speciale di questo divieto è che non è
 * consentito che un set contenga se stesso come elemento.
 *
 * <p>
 * Alcune implementazioni di set hanno restrizioni sugli elementi che
 * possono contenere. Ad esempio, alcune implementazioni proibiscono elementi null,
 * e alcune hanno restrizioni sui tipi dei loro elementi. Tentare di
 * aggiungere un elemento non idoneo lancia un'eccezione non controllata, tipicamente
 * {@code NullPointerException} o {@code ClassCastException}. Tentare
 * di interrogare la presenza di un elemento non idoneo può lanciare un'eccezione,
 * o può semplicemente restituire false; alcune implementazioni mostreranno il primo
 * comportamento e alcune mostreranno il secondo. Più in generale, tentare un'operazione
 * su un elemento non idoneo il cui completamento non risulterebbe
 * nell'inserimento di un elemento non idoneo nel set può lanciare un'eccezione
 * o può avere successo, a discrezione dell'implementazione.
 * Tali eccezioni sono contrassegnate come "opzionali" nella specifica per questa
 * interfaccia.
 *
 * <p>
 * Questa interfaccia è un membro del
 * <a href="https://docs.oracle.com/javase/1.4.2/docs/guide/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @since 1.2
 * @see HCollection
 * @see HList // Assuming you have an HList
 * @see HMap
 * @see HIterator
 * @see Object#equals(Object)
 * @see Object#hashCode()
 * @see java.lang.UnsupportedOperationException
 * @see java.lang.ClassCastException
 * @see java.lang.NullPointerException
 * @see java.lang.IllegalArgumentException
 */

public interface HSet extends HCollection {
	/**
	 * Restituisce il numero di elementi in questo set (la sua cardinalità). Se questo set contiene più di
	 * {@code Integer.MAX_VALUE} elementi, restituisce {@code Integer.MAX_VALUE}.
	 *
	 * @return il numero di elementi in questo set (la sua cardinalità).
	 */
	public int size();

	/**
	 * Restituisce {@code true} se questo set non contiene elementi.
	 *
	 * @return {@code true} se questo set non contiene elementi.
	 */
	public boolean isEmpty();

	/**
	 * Restituisce {@code true} se questo set contiene l'elemento specificato. Più formalmente, restituisce {@code true}
	 * se e solo se questo set contiene un elemento {@code e} tale che {@code (o==null ? e==null : o.equals(e))}.
	 *
	 * @param o elemento la cui presenza in questo set deve essere testata.
	 * @return {@code true} se questo set contiene l'elemento specificato.
	 * @throws ClassCastException se il tipo dell'elemento specificato è incompatibile con questo set (opzionale).
	 * @throws NullPointerException se l'elemento specificato è null e questo set non supporta elementi null
	 * (opzionale).
	 */
	public boolean contains(Object o);

	/**
	 * Restituisce un iteratore sugli elementi di questo set. Gli elementi sono restituiti senza un ordine particolare
	 * (a meno che questo set non sia un'istanza di qualche classe che fornisce una garanzia).
	 *
	 * @return un iteratore sugli elementi di questo set.
	 */
	public HIterator iterator();

	/**
	 * Restituisce un array contenente tutti gli elementi di questo set. Rispetta il contratto generale del metodo
	 * {@code HCollection.toArray}.
	 *
	 * @return un array contenente tutti gli elementi di questo set.
	 */
	public Object[] toArray();

	/**
	 * Restituisce un array contenente tutti gli elementi di questo set; il tipo runtime dell'array restituito è quello
	 * dell'array specificato. Rispetta il contratto generale del metodo {@code HCollection.toArray(Object[])}.
	 *
	 * @param a l'array in cui devono essere memorizzati gli elementi di questo set, se è abbastanza grande; altrimenti,
	 * un nuovo array dello stesso tipo runtime viene allocato per questo scopo.
	 * @return un array contenente gli elementi di questo set.
	 * @throws ArrayStoreException se il tipo runtime di a non è un supertipo del tipo runtime di ogni elemento in
	 * questo set.
	 * @throws NullPointerException se l'array specificato è {@code null}.
	 */
	public Object[] toArray(Object[] a);

	/**
	 * Aggiunge l'elemento specificato a questo set se non è già presente (operazione opzionale). Più formalmente,
	 * aggiunge l'elemento specificato, {@code o}, a questo set se questo set non contiene alcun elemento {@code e} tale
	 * che {@code (o==null ? e==null : o.equals(e))}. Se questo set contiene già l'elemento specificato, la chiamata
	 * lascia questo set invariato e restituisce {@code false}. In combinazione con la restrizione sui costruttori,
	 * questo assicura che i set non contengano mai elementi duplicati.
	 * <p>
	 *
	 * La stipulazione di cui sopra non implica che i set debbano accettare tutti gli elementi; i set possono rifiutarsi
	 * di aggiungere qualsiasi elemento particolare, incluso {@code null}, e lanciare un'eccezione, come descritto nella
	 * specifica per {@code HCollection.add}. Le singole implementazioni di set dovrebbero documentare chiaramente
	 * eventuali restrizioni sugli elementi che possono contenere.
	 *
	 * @param o elemento da aggiungere a questo set.
	 * @return {@code true} se questo set non conteneva già l'elemento specificato.
	 * @throws UnsupportedOperationException se il metodo {@code add} non è supportato da questo set.
	 * @throws ClassCastException se la classe dell'elemento specificato impedisce che venga aggiunto a questo set.
	 * @throws NullPointerException se l'elemento specificato è null e questo set non supporta elementi null.
	 * @throws IllegalArgumentException se qualche aspetto dell'elemento specificato impedisce che venga aggiunto a
	 * questo set.
	 */
	public boolean add(Object o);

	/**
	 * Rimuove l'elemento specificato da questo set se è presente (operazione opzionale). Più formalmente, rimuove un
	 * elemento {@code e} tale che {@code (o==null ? e==null : o.equals(e))}, se il set contiene tale elemento.
	 * Restituisce {@code true} se il set conteneva l'elemento specificato (o, equivalentemente, se il set è cambiato
	 * a seguito della chiamata). (Il set non conterrà l'elemento specificato una volta che la chiamata ritorna.)
	 *
	 * @param o oggetto da rimuovere da questo set, se presente.
	 * @return true se il set conteneva l'elemento specificato.
	 * @throws ClassCastException se il tipo dell'elemento specificato è incompatibile con questo set (opzionale).
	 * @throws NullPointerException se l'elemento specificato è null e questo set non supporta elementi null
	 * (opzionale).
	 * @throws UnsupportedOperationException se il metodo {@code remove} non è supportato da questo set.
	 */
	public boolean remove(Object o);

	/**
	 * Restituisce {@code true} se questo set contiene tutti gli elementi della collezione specificata. Se la collezione
	 * specificata è anch'essa un set, questo metodo restituisce {@code true} se è un <i>sottoinsieme</i> di questo set.
	 *
	 * @param c collezione da controllare per la presenza in questo set.
	 * @return {@code true} se questo set contiene tutti gli elementi della collezione specificata.
	 * @throws ClassCastException se i tipi di uno o più elementi nella collezione specificata sono incompatibili con
	 * questo set (opzionale).
	 * @throws NullPointerException se la collezione specificata contiene uno o più elementi null e questo set non
	 * supporta elementi null (opzionale).
	 * @throws NullPointerException se la collezione specificata è {@code null}.
	 * @see #contains(Object)
	 */
	public boolean containsAll(HCollection c);

	/**
	 * Aggiunge tutti gli elementi della collezione specificata a questo set se non sono già presenti (operazione
	 * opzionale). Se la collezione specificata è anch'essa un set, l'operazione {@code addAll} modifica efficacemente
	 * questo set in modo che il suo valore sia l'<i>unione</i> dei due set. Il comportamento di questa operazione non
	 * è specificato se la collezione specificata viene modificata mentre l'operazione è in corso.
	 *
	 * @param c collezione i cui elementi devono essere aggiunti a questo set.
	 * @return {@code true} se questo set è cambiato a seguito della chiamata.
	 * @throws UnsupportedOperationException se il metodo {@code addAll} non è supportato da questo set.
	 * @throws ClassCastException se la classe di qualche elemento della collezione specificata impedisce che venga
	 * aggiunto a questo set.
	 * @throws NullPointerException se la collezione specificata contiene uno o più elementi null e questo set non
	 * supporta elementi null, o se la collezione specificata è {@code null}.
	 * @throws IllegalArgumentException se qualche aspetto di qualche elemento della collezione specificata impedisce
	 * che venga aggiunto a questo set.
	 * @see #add(Object)
	 */
	public boolean addAll(HCollection c);

	/**
	 * Mantiene solo gli elementi in questo set che sono contenuti nella collezione specificata (operazione opzionale).
	 * In altre parole, rimuove da questo set tutti i suoi elementi che non sono contenuti nella collezione specificata.
	 * Se la collezione specificata è anch'essa un set, questa operazione modifica efficacemente questo set in modo che
	 * il suo valore sia l'<i>intersezione</i> dei due set.
	 *
	 * @param c collezione che definisce quali elementi questo set manterrà.
	 * @return {@code true} se questa collezione è cambiata a seguito della chiamata.
	 * @throws UnsupportedOperationException se il metodo {@code retainAll} non è supportato da questa Collezione.
	 * @throws ClassCastException se i tipi di uno o più elementi in questo set sono incompatibili con la collezione
	 * specificata (opzionale).
	 * @throws NullPointerException se questo set contiene un elemento null e la collezione specificata non supporta
	 * elementi null (opzionale).
	 * @throws NullPointerException se la collezione specificata è {@code null}.
	 * @see #remove(Object)
	 */
	public boolean retainAll(HCollection c);

	/**
	 * Rimuove da questo set tutti i suoi elementi che sono contenuti nella collezione specificata (operazione
	 * opzionale). Se la collezione specificata è anch'essa un set, questa operazione modifica efficacemente questo set
	 * in modo che il suo valore sia la <i>differenza asimmetrica</i> dei due set.
	 *
	 * @param c collezione che definisce quali elementi verranno rimossi da questo set.
	 * @return {@code true} se questo set è cambiato a seguito della chiamata.
	 * @throws UnsupportedOperationException se il metodo {@code removeAll} non è supportato da questa Collezione.
	 * @throws ClassCastException se i tipi di uno o più elementi in questo set sono incompatibili con la collezione
	 * specificata (opzionale).
	 * @throws NullPointerException se questo set contiene un elemento null e la collezione specificata non supporta
	 * elementi null (opzionale).
	 * @throws NullPointerException se la collezione specificata è {@code null}.
	 * @see #remove(Object)
	 */
	public boolean removeAll(HCollection c);

	/**
	 * Rimuove tutti gli elementi da questo set (operazione opzionale). Questo set sarà vuoto dopo che questa chiamata
	 * ritorna (a meno che non lanci un'eccezione).
	 *
	 * @throws UnsupportedOperationException se il metodo {@code clear} non è supportato da questo set.
	 */
	public void clear();

	/**
	 * Confronta l'oggetto specificato con questo set per l'uguaglianza. Restituisce {@code true} se l'oggetto
	 * specificato è anch'esso un set, i due set hanno la stessa dimensione, e ogni membro del set specificato è
	 * contenuto in questo set (o, equivalentemente, ogni membro di questo set è contenuto nel set specificato). Questa
	 * definizione assicura che il metodo equals funzioni correttamente tra diverse implementazioni dell'interfaccia
	 * set.
	 *
	 * @param o Oggetto da confrontare per l'uguaglianza con questo set.
	 * @return {@code true} se l'Oggetto specificato è uguale a questo set.
	 * @see Object#hashCode()
	 * @see HMap // Assuming HMap exists and is relevant
	 */
	public boolean equals(Object o);

	/**
	 * Restituisce il valore hash code per questo set. L'hash code di un set è definito come la somma degli hash code
	 * degli elementi nel set, dove l'hashcode di un elemento {@code null} è definito come zero. Questo assicura che
	 * {@code s1.equals(s2)} implichi che {@code s1.hashCode()==s2.hashCode()} per qualsiasi due set {@code s1} ed
	 * {@code s2}, come richiesto dal contratto generale del metodo {@code Object.hashCode}.
	 *
	 * @return il valore hash code per questo set.
	 * @see Object#hashCode()
	 * @see Object#equals(Object)
	 * @see #equals(Object)
	 */
	public int hashCode();
}
