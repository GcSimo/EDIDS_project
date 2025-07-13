// Giacomo Simonetto - 2109923 - Secondo appello 2025 di Elementi di Ingegneria del Software

package myAdapter;

/* ----------------------------------------------------------------------------------------------------------------- *\
|* ---------------------------------- IllegalStateException Class - Java SE 1.4.2 ---------------------------------- *|
\* ----------------------------------------------------------------------------------------------------------------- */

/**
 * Segnala che un metodo è stato invocato in un momento illegale o inappropriato. In altre parole, l'ambiente Java o
 * l'applicazione Java non si trova in uno stato appropriato per l'operazione richiesta. Estende
 * {@code java.lang.RuntimeException} rendendola un'eccezione unchecked.
 * 
 * <p>
 * Questa estensione è stata aggiunta manualmente in quanto è utilizzata nell'implementazione degli iteratori delle
 * viste della classe {@code MapAdapter}, ma non è presente nativamente in J2ME CLDC 1.1.
 *
 * @see Exception
 * @see RuntimeException
 * @see HIterator
 */
public class IllegalStateException extends RuntimeException {
	/**
	 * Costruisce un {@code IllegalStateException} senza messaggio di dettaglio.
	 */
	public IllegalStateException() {
		super();
	}

	/**
	 * Costruisce un {@code IllegalStateException} con il messaggio di dettaglio specificato.
	 *
	 * @param s la stringa che contiene un messaggio dettagliato
	 */
	public IllegalStateException(String s) {
		super(s);
	}
}
