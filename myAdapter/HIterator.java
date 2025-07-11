package myAdapter;

/* ----------------------------------------------------------------------------------------------------------------- *\
|* -------------------------------------- HIterator Interface - Java SE 1.4.2 -------------------------------------- *|
\* ----------------------------------------------------------------------------------------------------------------- */

/**
 * Un iteratore su una collezione. {@code HIterator} prende il posto di {@code Enumeration} nel framework delle
 * collezioni Java. Gli iteratori differiscono dalle enumerazioni in due modi:
 * <ul>
 * <li>Gli iteratori consentono al chiamante di rimuovere elementi dalla collezione sottostante durante l'iterazione
 * con semantica ben definita.
 * <li>I nomi dei metodi sono stati migliorati.
 * </ul>
 * <p>
 *
 * Questa interfaccia è un membro del
 * <a href="https://docs.oracle.com/javase/1.4.2/docs/guide/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @since 1.2
 * @see HCollection
 * @see HMap
 * @see java.util.Enumeration
 * @see java.util.NoSuchElementException
 * @see java.lang.IllegalStateException
 * @see java.lang.UnsupportedOperationException
 */
public interface HIterator {
	/**
	 * Restituisce {@code true} se l'iterazione ha più elementi. (In altre parole, restituisce {@code true} se
	 * {@code next} restituirebbe un elemento anziché lanciare un'eccezione.)
	 *
	 * @return {@code true} se l'iteratore ha più elementi.
	 */
	public boolean hasNext();

	/**
	 * Restituisce l'elemento successivo nell'iterazione.
	 *
	 * @return l'elemento successivo nell'iterazione.
	 * @throws java.util.NoSuchElementException se l'iterazione non ha più elementi.
	 */
	public Object next();

	/**
	 * Rimuove dalla collezione sottostante l'ultimo elemento restituito dall' iteratore (operazione opzionale). Questo
	 * metodo può essere chiamato solo una volta per ogni chiamata a {@code next}. Il comportamento di un iteratore non
	 * è specificato se la collezione sottostante viene modificata durante l'iterazione in qualsiasi modo diverso dalla
	 * chiamata di questo metodo.
	 *
	 * @throws UnsupportedOperationException se l'operazione {@code remove} non è supportata da questo HIterator.
	 * @throws IllegalStateException se il metodo {@code next} non è ancora stato chiamato, o il metodo {@code remove}
	 * è già stato chiamato dopo l'ultima chiamata al metodo {@code next}.
	 */
	public void remove();
}
