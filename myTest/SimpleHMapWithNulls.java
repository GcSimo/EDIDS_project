package myTest;

// importazioni delle interfacce da implementare
import myAdapter.HCollection;
import myAdapter.HMap;
import myAdapter.HSet;
import myAdapter.HIterator;

// importazioni delle classi necessarie per l'implementazione
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Implementazione di una nuova classe che rappresenta una mappa, implementa l'interfaccia {@link HMap} e supporta
 * valori null. È richiesta per testare il metodo {@code putAll()} della la classe {@link myAdapter.MapAdapter} per
 * verificare il corretto lancio delle eccezioni nella gestione dei valori null. Contiene inoltre l'implementazione
 * della entry della mappa che permette di istanziare entry agevolmente per testare i metodi della vista
 * {@code entrySet()} e del relativo iteratore.
 * 
 * <p>
 * Questa classe utilizza internamente una {@link HashMap} di JavaSE21 per gestire le operazioni di mappatura.
 * Siccome questa classe ha un impiego molto limitato, non è necessario implementare metodi complessi o ottimizzazioni
 * avanzate. I metodi richiesti e implementati sono:
 * <ul>
 *   <li> il costruttore senza parametri che inizializza la mappa</li>
 *   <li> il metodo {@code put(Object key, Object value)} per inserire una coppia chiave-valore nella mappa</li>
 *   <li> il metodo {@code entrySet()} e il relativo iteratore che restituisce un set di entry della mappa</li>
 *   <li> il costruttore della classe {@code SimpleHEntry} che rappresenta un'entry della mappa</li>
 *   <li> il metodo {@code getKey()} per ottenere la chiave dell'entry</li>
 *   <li> il metodo {@code getValue()} per ottenere il valore dell'entry</li>
 *   <li> il metodo {@code iterator()} per ottenere un iteratore sulle entry della mappa</li>
 *   <li> il metodo {@code hasNext()} per verificare se ci sono altre entry nell'iteratore</li>
 *   <li> il metodo {@code next()} per ottenere la prossima entry dall'iteratore</li>
 * </ul>
 */
public class SimpleHMapWithNulls implements HMap {
	// variabile di istanza che rappresenta la mappa interna che supporta valori null
	private HashMap<Object, Object> map;

	// metodi richiesti per l'implementazione dell'interfaccia HMap
	public SimpleHMapWithNulls() { map = new HashMap<Object, Object>(); }
	public Object put(Object key, Object value) { return map.put(key, value); }
	public HSet entrySet() { return new SimpleHEntrySet(); }
	
	// metodi non implementati che lanciano UnsupportedOperationException
	public int size() { throw new UnsupportedOperationException("size not supported for SimpleHMapWithNulls"); }
	public boolean isEmpty() { throw new UnsupportedOperationException("isEmpty not supported for SimpleHMapWithNulls"); }
	public boolean containsKey(Object key) { throw new UnsupportedOperationException("containsKey not supported for SimpleHMapWithNulls"); }
	public boolean containsValue(Object value) { throw new UnsupportedOperationException("containsValue not supported for SimpleHMapWithNulls"); }
	public Object get(Object key) { throw new UnsupportedOperationException("get not supported for SimpleHMapWithNulls"); }
	public Object remove(Object key) { throw new UnsupportedOperationException("remove not supported for SimpleHMapWithNulls"); }
	public void clear() { throw new UnsupportedOperationException("clear not supported for SimpleHMapWithNulls"); }
	public void putAll(HMap m) { throw new UnsupportedOperationException("putAll not supported for SimpleHMapWithNulls"); }
	public HSet keySet() { throw new UnsupportedOperationException("keySet not supported for SimpleHMapWithNulls"); }
	public HCollection values() { throw new UnsupportedOperationException("values not supported for SimpleHMapWithNulls"); }
	
	// --- Simple HEntry implementation for this test helper ---
	public static class SimpleHEntry implements HMap.HEntry {
		// variabili di istanza per la chiave e il valore dell'entry
		private Object key;
		private Object value;

		// metodi richiesti per l'implementazione della classe SimpleHEntry
		public SimpleHEntry(Object key, Object value) { this.key = key; this.value = value; }
		public Object getKey() { return key; }
		public Object getValue() { return value; }
		
		// metodi non implementati che lanciano UnsupportedOperationException
		public Object setValue(Object value) { throw new UnsupportedOperationException("setValue not supported for SimpleHEntry"); }
		public boolean equals(Object o) { throw new UnsupportedOperationException("equals not supported for SimpleHEntry"); }
		public int hashCode() { throw new UnsupportedOperationException("hashCode not supported for SimpleHEntry"); }
		public String toString() { throw new UnsupportedOperationException("toString not supported for SimpleHEntry"); }
	}

	// --- Simple HEntrySet implementation for this test helper ---
	private class SimpleHEntrySet implements HSet {
		// non servono variabili di istanza per l'entrySet, si utilizza direttamente l'iteratore della mappa

		// metodi richiesti per l'implementazione della classe SimpleHEntrySet
		private SimpleHEntrySet() { /* intentionally left blank */ }
		public HIterator iterator() { return new SimpleEntrySetHIterator(); }
		
		// metodi non implementati che lanciano UnsupportedOperationException
		public int size() { throw new UnsupportedOperationException("size not supported for SimpleHEntrySet view"); }
		public boolean isEmpty() { throw new UnsupportedOperationException("isEmpty not supported for SimpleHEntrySet view"); }
		public boolean contains(Object o) { throw new UnsupportedOperationException("contains not supported for SimpleHEntrySet view"); }
		public boolean add(Object e) { throw new UnsupportedOperationException("add not supported for SimpleHEntrySet view"); }
		public boolean remove(Object o) { throw new UnsupportedOperationException("remove not supported for SimpleHEntrySet view"); }
		public boolean containsAll(HCollection c) { throw new UnsupportedOperationException("containsAll not supported for SimpleHEntrySet view"); }
		public boolean addAll(HCollection c) { throw new UnsupportedOperationException("addAll not supported for SimpleHEntrySet view"); }
		public boolean retainAll(HCollection c) { throw new UnsupportedOperationException("retainAll not supported for SimpleHEntrySet view"); }
		public boolean removeAll(HCollection c) { throw new UnsupportedOperationException("removeAll not supported for SimpleHEntrySet view"); }
		public void clear() { throw new UnsupportedOperationException("clear not supported for SimpleHEntrySet view"); }
		public Object[] toArray() { throw new UnsupportedOperationException("toArray not supported for SimpleHEntrySet view"); }
		public Object[] toArray(Object[] a) { throw new UnsupportedOperationException("toArray(Object[]) not supported for SimpleHEntrySet view"); }
		public boolean equals(Object o) { throw new UnsupportedOperationException("equals not supported for SimpleHEntrySet view"); }
		public int hashCode() { throw new UnsupportedOperationException("hashCode not supported for SimpleHEntrySet view"); }
	}
	
	// --- Simple HIterator implementation for this test helper ---
	private class SimpleEntrySetHIterator implements HIterator {
		// variabile di istanza per l'iteratore interno che itera sulle entry della mappa
		private Iterator i;

		// metodi richiesti per l'implementazione della classe SimpleEntrySetHIterator
		private SimpleEntrySetHIterator() { i = map.entrySet().iterator(); }
		public boolean hasNext() { return i.hasNext(); }
		public Object next() {
			Map.Entry entry = (Map.Entry) i.next();
			return new SimpleHEntry(entry.getKey(), entry.getValue());
		}
		
		// metodi non implementati che lanciano UnsupportedOperationException
		public void remove() { throw new UnsupportedOperationException("remove not supported for SimpleHIterator"); }
	}
}
