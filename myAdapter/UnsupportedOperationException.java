package myAdapter;

/* ----------------------------------------------------------------------------------------------------------------- *\
|* ------------------------------ UnsupportedOperationException Class - Java SE 1.4.2 ------------------------------ *|
\* ----------------------------------------------------------------------------------------------------------------- */

/**
 * Eccezione unchecked lanciata per indicare che l'operazione richiesta non è supportata dall'implementazione
 * corrente di un metodo o di una classe. Questa è un'implementazione personalizzata per ambienti CLDC 1.1,
 * dato che l'equivalente {@code java.lang.UnsupportedOperationException} non è disponibile. Estende
 * {@code java.lang.RuntimeException} rendendola un'eccezione unchecked.
 *
 * Segnala che l'operazione richiesta non è supportata.
 *
 * <p>
 * Questa classe è un membro del
 * <a href="https://docs.oracle.com/javase/1.4.2/docs/guide/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @since 1.2
 * @see java.util.Collection
 * @see java.util.List
 * @see java.util.Set
 * @see java.util.Map
 * @see java.util.Iterator
 */
public class UnsupportedOperationException extends RuntimeException {
	/**
	 * Costruisce un {@code UnsupportedOperationException} senza messaggio di dettaglio. Un messaggio di dettaglio
	 * è una stringa che descrive questa particolare eccezione.
	 */
	public UnsupportedOperationException() {
		super();
	}

	/**
	 * Costruisce un {@code UnsupportedOperationException} con il messaggio di dettaglio specificato. Un messaggio
	 * di dettaglio è una Stringa che descrive questa particolare eccezione.
	 *
	 * @param s la stringa che contiene un messaggio dettagliato
	 */
	public UnsupportedOperationException(String s) {
		super(s);
	}
}
