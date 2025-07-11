package myAdapter;

/* ----------------------------------------------------------------------------------------------------------------- *\
|* ---------------------------------- IllegalStateException Class - Java SE 1.4.2 ---------------------------------- *|
\* ----------------------------------------------------------------------------------------------------------------- */

/**
 * Eccezione unchecked lanciata per indicare che l'operazione richiesta non è supportata dall'implementazione
 * corrente di un metodo o di una classe. Questa è un'implementazione personalizzata per ambienti CLDC 1.1,
 * dato che l'equivalente {@code java.lang.IllegalStateException} non è disponibile. Estende
 * {@code java.lang.RuntimeException} rendendola un'eccezione unchecked.
 *
 * Segnala che un metodo è stato invocato in un momento illegale o inappropriato. In altre parole, l'ambiente
 * Java o l'applicazione Java non si trova in uno stato appropriato per l'operazione richiesta.
 *
 * @since JDK1.1
 * @see java.lang.RuntimeException
 * @see java.lang.Throwable
 * @see java.lang.Exception
 */
public class IllegalStateException extends RuntimeException {
	/**
	 * Costruisce un {@code IllegalStateException} senza messaggio di dettaglio. Un messaggio di dettaglio
	 * è una stringa che descrive questa particolare eccezione.
	 */
	public IllegalStateException() {
		super();
	}

	/**
	 * Costruisce un {@code IllegalStateException} con il messaggio di dettaglio specificato. Un messaggio
	 * di dettaglio è una stringa che descrive questa particolare eccezione.
	 *
	 * @param s la stringa che contiene un messaggio dettagliato
	 */
	public IllegalStateException(String s) {
		super(s);
	}
}
