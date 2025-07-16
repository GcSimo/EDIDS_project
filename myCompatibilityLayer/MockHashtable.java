package myCompatibilityLayer;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Mock implementation of a HashTable for compatibility purposes.
 * This class is not intended for production use and does not implement
 * any real functionality. It serves as a placeholder to satisfy type requirements
 * in the codebase where a HashTable is expected.
 * 
 * It is used to limit the methods available in the MapAdapter class, ensuring
 * that only the methods defined can be used.
 */
public class MockHashtable {
	private Hashtable<Object, Object> hashtable;

	// Constructs a new, empty hashtable with a default capacity and load factor.
	public MockHashtable() { hashtable = new Hashtable<Object, Object>(); }

	// Constructs a new, empty hashtable with the specified initial capacity.
	public MockHashtable(int initialCapacity) { hashtable = new Hashtable<Object, Object>(initialCapacity); }

	// Clears this hashtable so that it contains no keys.
	public void clear() { hashtable.clear(); }

	// Tests if some key maps into the specified value in this hashtable.
	public boolean contains(Object value) { return hashtable.contains(value); }

	// Tests if the specified object is a key in this hashtable.
	public boolean containsKey(Object key) { return hashtable.containsKey(key); }

	// Returns an enumeration of the values in this hashtable.
	public Enumeration elements() { return hashtable.elements(); }

	// Returns the value to which the specified key is mapped in this hashtable.
	public Object get(Object key) { return hashtable.get(key); }

	// Tests if this hashtable maps no keys to values.
	public boolean isEmpty() { return hashtable.isEmpty(); }

	// Returns an enumeration of the keys in this hashtable.
	public Enumeration keys() { return hashtable.keys(); }

	// Maps the specified key to the specified value in this hashtable.
	public Object put(Object key, Object value) { return hashtable.put(key, value); }

	// Rehashes the contents of the hashtable into a hashtable with a larger capacity.
	protected void rehash() { throw new UnsupportedOperationException("Rehash not implemented in MockHashtable");}

	// Removes the key (and its corresponding value) from this hashtable.
	public Object remove(Object key) { return hashtable.remove(key); }

	// Returns the number of keys in this hashtable.
	public int size() { return hashtable.size(); }

	// Returns a rather long string representation of this hashtable.
	public String toString() { return hashtable.toString(); }
}
