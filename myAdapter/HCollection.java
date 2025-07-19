// Giacomo Simonetto - 2109923 - Secondo appello 2025 di Elementi di Ingegneria del Software

package myAdapter;

/* ----------------------------------------------------------------------------------------------------------------- *\
|* ------------------------------------- HCollection Interface - Java SE 1.4.2 ------------------------------------- *|
\* ----------------------------------------------------------------------------------------------------------------- */

/**
 * L'interfaccia radice nella <i>gerarchia delle collezioni</i>. Una collezione rappresenta un gruppo di oggetti, noti
 * come suoi <i>elementi</i>. Alcune collezioni consentono elementi duplicati e altre no. Alcune sono ordinate e altre
 * non ordinate. L'SDK non fornisce alcuna implementazione <i>diretta</i> di questa interfaccia: fornisce
 * implementazioni di sottointerfacce più specifiche come {@code HSet} e {@code List}. Questa interfaccia è tipicamente
 * usata per passare collezioni e manipolarle dove è desiderata la massima generalità.
 *
 * <p>
 * I <i>Bags</i> o <i>multisets</i> (collezioni non ordinate che possono contenere elementi duplicati) dovrebbero
 * implementare direttamente questa interfaccia.
 *
 * <p>
 * Tutte le classi di implementazione {@code HCollection} di uso generale (che tipicamente implementano
 * {@code HCollection} indirettamente attraverso una delle sue sottointerfacce) dovrebbero fornire due costruttori
 * "standard": un costruttore vuoto (senza argomenti), che crea una collezione vuota, e un costruttore con un singolo
 * argomento di tipo {@code HCollection}, che crea una nuova collezione con gli stessi elementi del suo argomento. In
 * effetti, quest'ultimo costruttore consente all'utente di copiare qualsiasi collezione, producendo una collezione
 * equivalente del tipo di implementazione desiderato. Non c'è modo di imporre questa convenzione (poiché le interfacce
 * non possono contenere costruttori) ma tutte le implementazioni {@code HCollection} di uso generale nelle librerie
 * della piattaforma Java si conformano.
 * 
 *
 * <p>
 * I metodi "distruttivi" contenuti in questa interfaccia, cioè i metodi che modificano la collezione su cui operano,
 * sono specificati per lanciare {@code UnsupportedOperationException} se questa collezione non supporta l'operazione.
 * Se questo è il caso, questi metodi possono, ma non sono tenuti a, lanciare una {@code UnsupportedOperationException}
 * se l'invocazione non avrebbe alcun effetto sulla collezione. Ad esempio, invocare il metodo
 * {@link #addAll(HCollection)} su una collezione non modificabile può, ma non è tenuto a, lanciare l'eccezione se la
 * collezione da aggiungere è vuota.
 *
 * <p>
 * Alcune implementazioni di collezione hanno restrizioni sugli elementi che possono contenere. Ad esempio, alcune
 * implementazioni proibiscono elementi null, e alcune hanno restrizioni sui tipi dei loro elementi. Tentare di
 * aggiungere un elemento non idoneo lancia un'eccezione non controllata, tipicamente {@code NullPointerException} o
 * {@code ClassCastException}. Tentare di interrogare la presenza di un elemento non idoneo può lanciare un'eccezione,
 * o può semplicemente restituire false; alcune implementazioni mostreranno il primo comportamento e alcune mostreranno
 * il secondo. Più in generale, tentare un'operazione su un elemento non idoneo il cui completamento non risulterebbe
 * nell'inserimento di un elemento non idoneo nella collezione può lanciare un'eccezione o può avere successo, a
 * discrezione dell'implementazione. Tali eccezioni sono contrassegnate come "opzionali" nella specifica per questa
 * interfaccia.
 *
 * <p>
 * Questa interfaccia è un membro del
 * <a href="https://docs.oracle.com/javase/1.4.2/docs/guide/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @see HSet
 * @see HMap
 * @see HIterator
 */
public interface HCollection {
	/**
	 * Restituisce il numero di elementi in questa collezione. Se questa collezione contiene più di
	 * {@code Integer.MAX_VALUE} elementi, restituisce {@code Integer.MAX_VALUE}.
	 *
	 * @return il numero di elementi in questa collezione
	 */
	public int size();

	/**
	 * Restituisce {@code true} se questa collezione non contiene elementi.
	 *
	 * @return {@code true} se questa collezione non contiene elementi
	 */
	public boolean isEmpty();

	/**
	 * Rimuove tutti gli elementi da questa collezione (operazione opzionale). Questa collezione sarà vuota dopo che
	 * questo metodo ritorna a meno che non lanci un'eccezione.
	 *
	 * @throws UnsupportedOperationException se il metodo {@code clear} non è supportato da questa collezione.
	 */
	public void clear();

	/**
	 * Restituisce {@code true} se questa collezione contiene l'elemento specificato. Più formalmente, restituisce
	 * {@code true} se e solo se questa collezione contiene almeno un elemento {@code e} tale che
	 * {@code (o==null ? e==null : o.equals(e))}.
	 *
	 * @param o elemento la cui presenza in questa collezione deve essere testata.
	 * @return {@code true} se questa collezione contiene l'elemento specificato
	 * @throws ClassCastException
	 * se il tipo dell'elemento specificato è incompatibile con questa collezione (opzionale).
	 * @throws NullPointerException
	 * se l'elemento specificato è null e questa collezione non supporta elementi null (opzionale).
	 */
	public boolean contains(Object o);

	/**
	 * Restituisce un iteratore sugli elementi di questa collezione. Non ci sono garanzie riguardo all'ordine in cui
	 * gli elementi vengono restituiti (a meno che questa collezione non sia un'istanza di qualche classe che fornisce
	 * una garanzia).
	 *
	 * @return un {@code Iterator} sugli elementi di questa collezione
	 */
	public HIterator iterator();

	/**
	 * Restituisce un array contenente tutti gli elementi di questa collezione. Se la collezione fornisce garanzie
	 * sull'ordine in cui i suoi elementi sono restituiti dal suo iteratore, questo metodo deve restituire gli elementi
	 * nello stesso ordine.
	 * <p>
	 *
	 * L'array restituito sarà "sicuro" in quanto nessun riferimento ad esso è mantenuto da questa collezione. (In altre
	 * parole, questo metodo deve allocare un nuovo array anche se questa collezione è supportata da un array). Il
	 * chiamante è quindi libero di modificare l'array restituito.
	 * <p>
	 *
	 * Questo metodo funge da ponte tra le API basate su array e quelle basate su collezione.
	 *
	 * @return un array contenente tutti gli elementi di questa collezione
	 */
	public Object[] toArray();

	/**
	 * Restituisce un array contenente tutti gli elementi di questa collezione; il tipo runtime dell'array restituito è
	 * quello dell'array specificato. Se la collezione si adatta all'array specificato, vi viene restituita. Altrimenti,
	 * viene allocato un nuovo array con il tipo runtime dell'array specificato e la dimensione di questa collezione.
	 * <p>
	 *
	 * Se questa collezione si adatta all'array specificato con spazio extra (cioè, l'array ha più elementi di questa
	 * collezione), l'elemento nell'array immediatamente successivo alla fine della collezione viene impostato su
	 * {@code null}. Questo è utile per determinare la lunghezza di questa collezione <i>solo</i> se il chiamante sa
	 * che questa collezione non contiene elementi {@code null}.)
	 * <p>
	 *
	 * Se questa collezione fornisce garanzie sull'ordine in cui i suoi elementi vengono restituiti dal suo iteratore,
	 * questo metodo deve restituire gli elementi nello stesso ordine.
	 * <p>
	 *
	 * Come il metodo {@code toArray}, questo metodo funge da ponte tra le API basate su array e quelle basate su
	 * collezione. Inoltre, questo metodo consente un controllo preciso sul tipo runtime dell'array di output e può,
	 * in determinate circostanze, essere utilizzato per risparmiare sui costi di allocazione.
	 * <p>
	 *
	 * Supponiamo che {@code l} sia una {@code List} nota per contenere solo stringhe. Il seguente codice può essere
	 * utilizzato per riversare la lista in un array di {@code String} appena allocato:
	 *
	 * <pre>
	 * String[] x = (String[]) v.toArray(new String[0]);
	 * </pre>
	 * <p>
	 *
	 * Si noti che {@code toArray(new Object[0])} è identico nella funzione a {@code toArray()}.
	 *
	 * @param a l'array in cui devono essere memorizzati gli elementi di questa collezione, se è abbastanza grande;
	 * altrimenti, un nuovo array dello stesso tipo runtime viene allocato per questo scopo.
	 * @return un array contenente gli elementi di questa collezione
	 * @throws ArrayStoreException il tipo runtime dell'array specificato non è un supertipo del tipo runtime di ogni
	 * elemento in questa collezione.
	 * @throws NullPointerException se l'array specificato è {@code null}.
	 */
	public Object[] toArray(Object[] a);

	/**
	 * Assicura che questa collezione contenga l'elemento specificato (operazione opzionale). Restituisce {@code true}
	 * se questa collezione è cambiata a seguito della chiamata. (Restituisce {@code false} se questa collezione non
	 * consente duplicati e contiene già l'elemento specificato.)
	 * <p>
	 *
	 * Le collezioni che supportano questa operazione possono imporre limitazioni su quali elementi possono essere
	 * aggiunti a questa collezione. In particolare, alcune collezioni si rifiuteranno di aggiungere elementi
	 * {@code null}, e altre imporranno restrizioni sul tipo di elementi che possono essere aggiunti. Le classi di
	 * collezione dovrebbero specificare chiaramente nella loro documentazione eventuali restrizioni sugli elementi che
	 * possono essere aggiunti.
	 * <p>
	 *
	 * Se una collezione si rifiuta di aggiungere un particolare elemento per qualsiasi motivo diverso dal fatto che
	 * contiene già l'elemento, essa <i>deve</i> lanciare un'eccezione (piuttosto che restituire {@code false}). Questo
	 * preserva l'invariante che una collezione contiene sempre l'elemento specificato dopo che questa chiamata ritorna.
	 *
	 * @param o elemento la cui presenza in questa collezione deve essere assicurata.
	 * @return {@code true} se questa collezione è cambiata a seguito della chiamata
	 * @throws UnsupportedOperationException {@code add} non è supportato da questa collezione.
	 * @throws ClassCastException la classe dell'elemento specificato impedisce che venga aggiunto a questa collezione.
	 * @throws NullPointerException se l'elemento specificato è null e questa collezione non supporta elementi null.
	 * @throws IllegalArgumentException qualche aspetto di questo elemento impedisce che venga aggiunto a questa
	 * collezione.
	 */
	public boolean add(Object o);

	/**
	 * Rimuove una singola istanza dell'elemento specificato da questa collezione, se presente (operazione opzionale).
	 * Più formalmente, rimuove un elemento {@code e} tale che {@code (o==null ? e==null : o.equals(e))}, se questa
	 * collezione contiene uno o più tali elementi. Restituisce true se questa collezione conteneva l'elemento
	 * specificato (o, equivalentemente, se questa collezione è cambiata a seguito della chiamata).
	 *
	 * @param o elemento da rimuovere da questa collezione, se presente.
	 * @return {@code true} se questa collezione è cambiata a seguito della chiamata
	 * @throws ClassCastException se il tipo dell'elemento specificato è incompatibile con questa collezione
	 * (opzionale).
	 * @throws NullPointerException se l'elemento specificato è null e questa collezione non supporta elementi null
	 * (opzionale).
	 * @throws UnsupportedOperationException remove non è supportato da questa collezione.
	 */
	public boolean remove(Object o);

	/**
	 * Restituisce {@code true} se questa collezione contiene tutti gli elementi della collezione specificata.
	 *
	 * @param c collezione da controllare per la presenza in questa collezione.
	 * @return {@code true} se questa collezione contiene tutti gli elementi della collezione specificata
	 * @throws ClassCastException se i tipi di uno o più elementi nella collezione specificata sono incompatibili con
	 * questa collezione (opzionale).
	 * @throws NullPointerException se la collezione specificata contiene uno o più elementi null e questa collezione
	 * non supporta elementi null (opzionale).
	 * @throws NullPointerException se la collezione specificata è {@code null}.
	 * @see #contains(Object)
	 */
	public boolean containsAll(HCollection c);

	/**
	 * Aggiunge tutti gli elementi della collezione specificata a questa collezione (operazione opzionale). Il
	 * comportamento di questa operazione non è definito se la collezione specificata viene modificata mentre
	 * l'operazione è in corso. (Ciò implica che il comportamento di questa chiamata non è definito se la collezione
	 * specificata è questa collezione, e questa collezione è non vuota.)
	 *
	 * @param c elementi da inserire in questa collezione.
	 * @return {@code true} se questa collezione è cambiata a seguito della chiamata
	 * @throws UnsupportedOperationException se questa collezione non supporta il metodo {@code addAll}.
	 * @throws ClassCastException se la classe di un elemento della collezione specificata impedisce che venga aggiunto
	 * a questa collezione.
	 * @throws NullPointerException se la collezione specificata contiene uno o più elementi null e questa collezione
	 * non supporta elementi null, o se la collezione specificata è {@code null}.
	 * @throws IllegalArgumentException qualche aspetto di un elemento della collezione specificata impedisce che venga
	 * aggiunto a questa collezione.
	 * @see #add(Object)
	 */
	public boolean addAll(HCollection c);

	/**
	 * Rimuove tutti gli elementi di questa collezione che sono anche contenuti nella collezione specificata (operazione
	 * opzionale). Dopo che questa chiamata ritorna, questa collezione non conterrà elementi in comune con la collezione
	 * specificata.
	 *
	 * @param c elementi da rimuovere da questa collezione.
	 * @return {@code true} se questa collezione è cambiata a seguito della chiamata
	 * @throws UnsupportedOperationException se il metodo {@code removeAll} non è supportato da questa collezione.
	 * @throws ClassCastException se i tipi di uno o più elementi in questa collezione sono incompatibili con la
	 * collezione specificata (opzionale).
	 * @throws NullPointerException se questa collezione contiene uno o più elementi null e la collezione specificata
	 * non supporta elementi null (opzionale).
	 * @throws NullPointerException se la collezione specificata è {@code null}.
	 * @see #remove(Object)
	 * @see #contains(Object)
	 */
	public boolean removeAll(HCollection c);

	/**
	 * Mantiene solo gli elementi in questa collezione che sono contenuti nella collezione specificata (operazione
	 * opzionale). In altre parole, rimuove da questa collezione tutti i suoi elementi che non sono contenuti nella
	 * collezione specificata.
	 *
	 * @param c elementi da mantenere in questa collezione.
	 * @return {@code true} se questa collezione è cambiata a seguito della chiamata
	 * @throws UnsupportedOperationException se il metodo {@code retainAll} non è supportato da questa Collezione.
	 * @throws ClassCastException se i tipi di uno o più elementi in questa collezione sono incompatibili con la
	 * collezione specificata (opzionale).
	 * @throws NullPointerException se questa collezione contiene uno o più elementi null e la collezione specificata
	 * non supporta elementi null (opzionale).
	 * @throws NullPointerException se la collezione specificata è {@code null}.
	 * @see #remove(Object)
	 * @see #contains(Object)
	 */
	public boolean retainAll(HCollection c);

	/**
	 * Confronta l'oggetto specificato con questa collezione per l'uguaglianza.
	 * <p>
	 *
	 * Sebbene l'interfaccia {@code HCollection} non aggiunga stipulazioni al contratto generale per
	 * {@code Object.equals}, i programmatori che implementano l'interfaccia {@code HCollection} "direttamente" (in
	 * altre parole, creano una classe che è una {@code HCollection} ma non è un {@code HSet} o una {@code List})
	 * devono prestare attenzione se scelgono di sovrascrivere il {@code Object.equals}. Non è necessario farlo, e il
	 * corso d'azione più semplice è affidarsi all'implementazione di {@code Object}, ma l'implementatore potrebbe
	 * desiderare di implementare un "confronto di valori" al posto del "confronto di riferimenti" predefinito. (Le
	 * interfacce {@code List} e {@code HSet} impongono tali confronti di valori.)
	 * <p>
	 *
	 * Il contratto generale per il metodo {@code Object.equals} afferma che equals deve essere simmetrico (in altre
	 * parole, {@code a.equals(b)} se e solo se {@code b.equals(a)}). I contratti per {@code List.equals} e
	 * {@code HSet.equals} affermano che le liste sono uguali solo ad altre liste, e i set ad altri set. Pertanto, un
	 * metodo {@code equals} personalizzato per una classe di collezione che non implementa né l'interfaccia
	 * {@code List} né {@code HSet} deve restituire {@code false} quando questa collezione viene confrontata con
	 * qualsiasi lista o set. (Con la stessa logica, non è possibile scrivere una classe che implementi correttamente
	 * entrambe le interfacce {@code HSet} e {@code List}.)
	 *
	 * @param o Oggetto da confrontare per l'uguaglianza con questa collezione.
	 * @return {@code true} se l'oggetto specificato è uguale a questa collezione
	 * @see Object#equals(Object)
	 * @see HSet#equals(Object)
	 * @see HMap#equals(Object)
	 */
	public boolean equals(Object o);

	/**
	 * Restituisce il valore hash code per questa collezione. Sebbene l'interfaccia {@code HCollection} non aggiunga
	 * stipulazioni al contratto generale per il metodo {@code Object.hashCode}, i programmatori dovrebbero notare che
	 * qualsiasi classe che sovrascrive il metodo {@code Object.equals} deve anche sovrascrivere il metodo
	 * {@code Object.hashCode} al fine di soddisfare il contratto generale per il metodo {@code Object.hashCode}. In
	 * particolare, {@code c1.equals(c2)} implica che {@code c1.hashCode()==c2.hashCode()}.
	 *
	 * @return il valore hash code per questa collezione
	 * @see Object#hashCode()
	 * @see Object#equals(Object)
	 */
	public int hashCode();
}
