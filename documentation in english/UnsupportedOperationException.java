package myAdapter; // Or java.lang if truly replacing, but usually for adapters, it's in your package.

/**
 * Thrown to indicate that the requested operation is not supported.
 *
 * <p>This class is a member of the
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
     * Constructs an UnsupportedOperationException with no detail message.
     */
    public UnsupportedOperationException() {
        super();
    }

    /**
     * Constructs an UnsupportedOperationException with the specified
     * detail message.
     *
     * @param message the detail message
     */
    public UnsupportedOperationException(String message) {
        super(message);
    }
}
