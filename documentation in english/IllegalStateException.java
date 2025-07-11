package myAdapter; // Or java.lang if truly replacing, but usually for adapters, it's in your package.

/**
 * Signals that a method has been invoked at an illegal or
 * inappropriate time. In other words, the Java environment or
 * Java application is not in an appropriate state for the requested
 * operation.
 *
 * @since JDK1.1
 * @see java.lang.RuntimeException
 * @see java.lang.Throwable
 * @see java.lang.Exception
 */
public class IllegalStateException extends RuntimeException {

    /**
     * Constructs an IllegalStateException with no detail message.
     * A detail message is a String that describes this particular exception.
     */
    public IllegalStateException() {
        super();
    }

    /**
     * Constructs an IllegalStateException with the specified detail
     * message. A detail message is a String that describes this particular
     * exception.
     *
     * @param s the String that contains a detailed message
     */
    public IllegalStateException(String s) {
        super(s);
    }
}
