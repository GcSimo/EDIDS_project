package myAdapter; // Or java.lang if you are truly replacing it, but usually for adapters, it's in your package.
                   // For this exercise, we will place it in myAdapter to avoid conflicts.

/**
 * Segnala che un metodo è stato invocato in un momento illegale o
 * inappropriato. In altre parole, l'ambiente Java o
 * l'applicazione Java non si trova in uno stato appropriato per l'operazione richiesta.
 *
 * @since JDK1.1
 * @see java.lang.RuntimeException
 * @see java.lang.Throwable
 * @see java.lang.Exception
 */
public class IllegalStateException extends RuntimeException {

    /**
     * Costruisce un {@code IllegalStateException} senza messaggio di dettaglio.
     * Un messaggio di dettaglio è una Stringa che descrive questa particolare eccezione.
     */
    public IllegalStateException() {
        super();
    }

    /**
     * Costruisce un {@code IllegalStateException} con il messaggio di dettaglio
     * specificato. Un messaggio di dettaglio è una Stringa che descrive questa particolare
     * eccezione.
     *
     * @param s la Stringa che contiene un messaggio dettagliato
     */
    public IllegalStateException(String s) {
        super(s);
    }
}
