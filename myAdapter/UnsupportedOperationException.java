// Giacomo Simonetto - 2109923 - Secondo appello 2025 di Elementi di Ingegneria del Software

package myAdapter;

/* ----------------------------------------------------------------------------------------------------------------- *\
|* ------------------------------ UnsupportedOperationException Class - Java SE 1.4.2 ------------------------------ *|
\* ----------------------------------------------------------------------------------------------------------------- */

/**
 * Segnala che l'operazione richiesta non è supportata. Estende {@code java.lang.RuntimeException} rendendola
 * un'eccezione unchecked.
 *
 * <p>
 * Questa estensione è stata aggiunta manualmente in quanto è utilizzata nell'implementazione delle viste della classe
 * {@code MapAdapter}, ma non è presente nativamente in J2ME CLDC 1.1.
 *
 * @see Exception
 * @see RuntimeException
 * @see HCollection
 * @see HSet
 * @see HMap
 */
public class UnsupportedOperationException extends RuntimeException {
	/**
	 * Costruisce un {@code UnsupportedOperationException} senza messaggio di dettaglio.
	 */
	public UnsupportedOperationException() {
		super();
	}

	/**
	 * Costruisce un {@code UnsupportedOperationException} con il messaggio di dettaglio specificato.
	 *
	 * @param s la stringa che contiene un messaggio dettagliato
	 */
	public UnsupportedOperationException(String s) {
		super(s);
	}
}
