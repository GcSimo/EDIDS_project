// Giacomo Simonetto - 2109923 - Secondo appello 2025 di Elementi di Ingegneria del Software

package myTest;

// importazioni delle interfacce da implementare
import myAdapter.HCollection;
import myAdapter.HMap;
import myAdapter.HSet;
import myAdapter.HIterator;

// importazioni delle classi di JavaSE 21 necessarie per l'implementazione della classe
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Implementazione di una nuova classe ausiliaria che implementa l'interfaccia {@link HMap} e che supporta chiavi e valori
 * {@code null}. La classe è richiesta per testare il metodo {@code putAll()} della la classe {@link myAdapter.MapAdapter}
 * per verificare il corretto lancio delle eccezioni nella gestione dei valori {@code null}. Contiene inoltre una classe
 * interna ausiliaria che implementa l'interfaccia {@link HMap.HEntry} per permettere di istanziare agevolmente
 * le entry per testare i metodi della vista {@code entrySet()} e del relativo iteratore.
 *
 * <p>
 * Questa classe utilizza internamente una {@link HashMap} di JavaSE 21 per gestire le operazioni di mappatura.
 * Siccome questa classe ha un impiego molto limitato, non è necessario implementare metodi complessi o ottimizzazioni
 * avanzate. I metodi dell'interfaccia che sono stati implementati sono:
 * <ul>
 *   <li> il costruttore di default {@code SimpleHMapWithNulls()}</li>
 *   <li> il metodo {@code put(Object key, Object value)} per inserire una coppia chiave-valore nella mappa</li>
 *   <li> il metodo {@code entrySet()} e il relativo iteratore che restituisce un set di entry della mappa</li>
 * </ul>
 * Tutti gli altri metodi non implementati lanciano {@link UnsupportedOperationException} se invocati.
 */
public class SimpleHMapWithNulls implements HMap {
	// variabile di istanza che rappresenta la mappa interna che supporta valori null
	private HashMap<Object, Object> map;

	// ----------------------------------------------------------------------------------------------------------------
	// ------------------------- metodi richiesti per l'implementazione dell'interfaccia HMap -------------------------

	/** Costruttore predefinito per la classe {@code SimpleHMapWithNulls}. */
	public SimpleHMapWithNulls() { map = new HashMap<Object, Object>(); }

	/**
	 * Metodo per inserire una coppia chiave-valore nella mappa.
	 * @param key la chiave da inserire
	 * @param value il valore da inserire
	 * @return il valore precedente associato alla chiave, oppure null se non c'era alcuna associazione
	 */
	public Object put(Object key, Object value) { return map.put(key, value); }

	/**
	 * Metodo per ottenere un set di entry della mappa.
	 * @return un set di entry della mappa
	 */
	public HSet entrySet() { return new SimpleHEntrySet(); }

	// ----------------------------------------------------------------------------------------------------------------
	// ---------------------- metodi non implementati che lanciano UnsupportedOperationException ----------------------

	/**
	 * Metodo per ottenere il numero di entry nella mappa - non implementato.
	 * @throws UnsupportedOperationException funzione non supportata
	 */
	public int size() { throw new UnsupportedOperationException("size not supported for SimpleHMapWithNulls"); }

	/**
	 * Metodo per verificare se la mappa è vuota - non implementato.
	 * @throws UnsupportedOperationException funzione non supportata
	 */
	public boolean isEmpty() { throw new UnsupportedOperationException("isEmpty not supported for SimpleHMapWithNulls"); }

	/**
	 * Metodo per verificare se la mappa contiene una chiave - non implementato.
	 * @throws UnsupportedOperationException funzione non supportata
	 */
	public boolean containsKey(Object key) { throw new UnsupportedOperationException("containsKey not supported for SimpleHMapWithNulls"); }

	/**
	 * Metodo per verificare se la mappa contiene un valore - non implementato.
	 * @throws UnsupportedOperationException funzione non supportata
	 */
	public boolean containsValue(Object value) { throw new UnsupportedOperationException("containsValue not supported for SimpleHMapWithNulls"); }

	/**
	 * Metodo per ottenere un valore dalla mappa - non implementato.
	 * @throws UnsupportedOperationException funzione non supportata
	 */
	public Object get(Object key) { throw new UnsupportedOperationException("get not supported for SimpleHMapWithNulls"); }

	/**
	 * Metodo per rimuovere un valore dalla mappa - non implementato.
	 * @throws UnsupportedOperationException funzione non supportata
	 */
	public Object remove(Object key) { throw new UnsupportedOperationException("remove not supported for SimpleHMapWithNulls"); }

	/**
	 * Metodo per inserire tutte le entry di una mappa in questa mappa - non implementato.
	 * @throws UnsupportedOperationException funzione non supportata
	 */
	public void putAll(HMap m) { throw new UnsupportedOperationException("putAll not supported for SimpleHMapWithNulls"); }

	/**
	 * Metodo per rimuovere tutte le entry dalla mappa - non implementato.
	 * @throws UnsupportedOperationException funzione non supportata
	 */
	public void clear() { throw new UnsupportedOperationException("clear not supported for SimpleHMapWithNulls"); }

	/**
	 * Metodo per ottenere un set di chiavi della mappa - non implementato.
	 * @throws UnsupportedOperationException funzione non supportata
	 */
	public HSet keySet() { throw new UnsupportedOperationException("keySet not supported for SimpleHMapWithNulls"); }

	/**
	 * Metodo per ottenere una collezione di valori della mappa - non implementato.
	 * @throws UnsupportedOperationException funzione non supportata
	 */
	public HCollection values() { throw new UnsupportedOperationException("values not supported for SimpleHMapWithNulls"); }

	/**
	 * Metodo per verificare se due mappe sono uguali - non implementato.
	 * @throws UnsupportedOperationException funzione non supportata
	 */
	public boolean equals(Object o) { throw new UnsupportedOperationException("equals not supported for SimpleHMapWithNulls"); }

	/**
	 * Metodo per ottenere il codice hash della mappa - non implementato.
	 * @throws UnsupportedOperationException funzione non supportata
	 */
	public int hashCode() { throw new UnsupportedOperationException("hashCode not supported for SimpleHMapWithNulls"); }

	/**
	 * Metodo per ottenere una rappresentazione testuale della mappa - non implementato.
	 * @throws UnsupportedOperationException funzione non supportata
	 */
	public String toString() { throw new UnsupportedOperationException("toString not supported for SimpleHMapWithNulls"); }

	// ----------------------------------------------------------------------------------------------------------------
	// ------------------------------ Classe interna che rappresenta un'entry della mappa -----------------------------

	/**
	 * Classe interna che rappresenta un'entry della mappa. Implementa l'interfaccia {@link HMap.HEntry} e fornisce
	 * i metodi richiesti per gestire le entry della mappa.
	 *
	 * I metodi implementati sono:
	 * <ul>
	 *   <li> il costruttore {@code SimpleHEntry(Object key, Object value)}</li>
	 *   <li> il metodo {@code getKey()} per ottenere la chiave dell'entry</li>
	 *   <li> il metodo {@code getValue()} per ottenere il valore dell'entry</li>
	 *   <li> il metodo {@code equals(Object o)} per confrontare due entry</li>
	 * </ul>
	 * Tutti gli altri metodi non implementati lanciano {@link UnsupportedOperationException} se invocati.
	 */
	public static class SimpleHEntry implements HMap.HEntry {
		// ------------------------------------------------------------------------------------------------------------
		// ------------------------- variabili di istanza per la chiave e il valore dell'entry ------------------------
		private Object key;
		private Object value;

		// ------------------------------------------------------------------------------------------------------------
		// --------------------- metodi richiesti per l'implementazione della classe SimpleHEntry ---------------------

		/**
		 * Costruttore per la classe {@code SimpleHEntry}.
		 * @param key la chiave dell'entry
		 * @param value il valore dell'entry
		 */
		public SimpleHEntry(Object key, Object value) { this.key = key; this.value = value; }

		/**
		 * Metodo per ottenere la chiave dell'entry.
		 * @return la chiave dell'entry
		 */
		public Object getKey() { return key; }

		/**
		 * Metodo per ottenere il valore dell'entry.
		 * @return il valore dell'entry
		 */
		public Object getValue() { return value; }

		/**
		 * Metodo per confrontare due entry - implementato secondo le specifichedell'interfaccia {@link myAdapter.HMap.HEntry}.
		 * @param o l'oggetto da confrontare
		 * @return true se le entry sono uguali, false altrimenti
		 */
		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof HMap.HEntry)) return false;
			HMap.HEntry that = (HMap.HEntry) o;
			return (key == null ? that.getKey() == null : key.equals(that.getKey())) && (value == null ? that.getValue() == null : value.equals(that.getValue()));
		}

		// ------------------------------------------------------------------------------------------------------------
		// -------------------- metodi non implementati che lanciano UnsupportedOperationException --------------------

		/**
		 * Metodo per impostare il valore dell'entry - non implementato.
		 * @throws UnsupportedOperationException funzione non supportata
		 */
		public Object setValue(Object value) { throw new UnsupportedOperationException("setValue not supported for SimpleHEntry"); }

		/**
		 * Metodo per ottenere il codice hash dell'entry - non implementato.
		 * @throws UnsupportedOperationException funzione non supportata
		 */
		public int hashCode() { throw new UnsupportedOperationException("hashCode not supported for SimpleHEntry"); }

		/**
		 * Metodo per ottenere una rappresentazione testuale dell'entry - non implementato.
		 * @throws UnsupportedOperationException funzione non supportata
		 */
		public String toString() { throw new UnsupportedOperationException("toString not supported for SimpleHEntry"); }
	}


	// ----------------------------------------------------------------------------------------------------------------
	// ------------------------ Classe interna che rappresenta la vista delle entry della mappa -----------------------

	/**
	 * Classe interna che rappresenta un set di entry della mappa. Implementa l'interfaccia {@link HSet} e fornisce
	 * i metodi richiesti per gestire la vista delle entry della mappa.
	 *
	 * I metodi implementati sono:
	 * <ul>
	 *   <li> il costruttore {@code SimpleHEntrySet()} privato, non accessibile dall'esterno</li>
	 *   <li> il metodo {@code iterator()} per ottenere un iteratore sulle entry della mappa</li>
	 * </ul>
	 * Tutti gli altri metodi non implementati lanciano {@link UnsupportedOperationException} se invocati.
	 */
	private class SimpleHEntrySet implements HSet {
		// ------------------------------------------------------------------------------------------------------------
		// ------------ non servono variabili di istanza, si utilizza direttamente l'iteratore della mappa ------------

		// ------------------------------------------------------------------------------------------------------------
		// -------------------- metodi richiesti per l'implementazione della classe SimpleHEntrySet -------------------

		/** Costruttore per la classe {@code SimpleHEntrySet}. */
		private SimpleHEntrySet() { /* intentionally left blank */ }

		/**
		 * Metodo per ottenere un iteratore sulle entry della mappa.
		 * @return un iteratore sulle entry della mappa
		*/
		public HIterator iterator() { return new SimpleEntrySetHIterator(); }

		// ------------------------------------------------------------------------------------------------------------
		// -------------------- metodi non implementati che lanciano UnsupportedOperationException --------------------

		/**
		 * Metodo per ottenere il numero di entry nel set - non implementato.
		 * @throws UnsupportedOperationException funzione non supportata.
		 */
		public int size() { throw new UnsupportedOperationException("size not supported for SimpleHEntrySet view"); }

		/**
		 * Metodo per verificare se il set è vuoto - non implementato.
		 * @throws UnsupportedOperationException funzione non supportata.
		 */
		public boolean isEmpty() { throw new UnsupportedOperationException("isEmpty not supported for SimpleHEntrySet view"); }

		/**
		 * Metodo per verificare se il set contiene un'entry - non implementato.
		 * @throws UnsupportedOperationException funzione non supportata.
		 */
		public boolean contains(Object o) { throw new UnsupportedOperationException("contains not supported for SimpleHEntrySet view"); }

		/**
		 * Metodo per ottenere un array di entry del set - non implementato.
		 * @throws UnsupportedOperationException funzione non supportata.
		 */
		public Object[] toArray() { throw new UnsupportedOperationException("toArray not supported for SimpleHEntrySet view"); }

		/**
		 * Metodo per ottenere un array di entry del set, con un array specificato - non implementato.
		 * @throws UnsupportedOperationException funzione non supportata.
		 */
		public Object[] toArray(Object[] a) { throw new UnsupportedOperationException("toArray(Object[]) not supported for SimpleHEntrySet view"); }

		/**
		 * Metodo per rimuovere un'entry dal set - non implementato.
		 * @throws UnsupportedOperationException funzione non supportata.
		 */
		public boolean add(Object e) { throw new UnsupportedOperationException("add not supported for SimpleHEntrySet view"); }

		/**
		 * Metodo per rimuovere un'entry dal set - non implementato.
		 * @throws UnsupportedOperationException funzione non supportata.
		 */
		public boolean remove(Object o) { throw new UnsupportedOperationException("remove not supported for SimpleHEntrySet view"); }

		/**
		 * Metodo per verificare se il set contiene tutte le entry di una collezione - non implementato.
		 * @throws UnsupportedOperationException funzione non supportata.
		 */
		public boolean containsAll(HCollection c) { throw new UnsupportedOperationException("containsAll not supported for SimpleHEntrySet view"); }

		/**
		 * Metodo per aggiungere tutte le entry di una collezione al set - non implementato.
		 * @throws UnsupportedOperationException funzione non supportata.
		 */
		public boolean addAll(HCollection c) { throw new UnsupportedOperationException("addAll not supported for SimpleHEntrySet view"); }

		/**
		 * Metodo per rimuovere tutte le entry presenti in una collezione dal set - non implementato.
		 * @throws UnsupportedOperationException funzione non supportata.
		 */
		public boolean removeAll(HCollection c) { throw new UnsupportedOperationException("removeAll not supported for SimpleHEntrySet view"); }

		/**
		 * Metodo per mantenere solo le entry presenti in una collezione - non implementato.
		 * @throws UnsupportedOperationException funzione non supportata.
		 */
		public boolean retainAll(HCollection c) { throw new UnsupportedOperationException("retainAll not supported for SimpleHEntrySet view"); }

		/**
		 * Metodo per rimuovere tutte le entry dal set - non implementato.
		 * @throws UnsupportedOperationException funzione non supportata.
		 */
		public void clear() { throw new UnsupportedOperationException("clear not supported for SimpleHEntrySet view"); }

		/**
		 * Metodo per ottenere una rappresentazione testuale del set - non implementato.
		 * @throws UnsupportedOperationException funzione non supportata.
		 */
		public boolean equals(Object o) { throw new UnsupportedOperationException("equals not supported for SimpleHEntrySet view"); }

		/**
		 * Metodo per ottenere il codice hash del set - non implementato.
		 * @throws UnsupportedOperationException funzione non supportata.
		 */
		public int hashCode() { throw new UnsupportedOperationException("hashCode not supported for SimpleHEntrySet view"); }

		/**
		 * Metodo per ottenere una rappresentazione testuale del set - non implementato.
		 * @throws UnsupportedOperationException funzione non supportata.
		 */
		public String toString() { throw new UnsupportedOperationException("toString not supported for SimpleHEntrySet view"); }
	}


	// ----------------------------------------------------------------------------------------------------------------
	// ----------------------- Classe interna che rappresenta l'iteratore delle entry della mappa ---------------------

	/**
	 * Classe interna che implementa un iteratore per le entry della mappa. Implementa l'interfaccia {@link HIterator}
	 * e fornisce i metodi richiesti per iterare sulle entry della mappa.
	 *
	 * I metodi implementati sono:
	 * <ul>
	 *   <li> il costruttore {@code SimpleEntrySetHIterator()} privato, non accessibile dall'esterno</li>
	 *   <li> il metodo {@code hasNext()} per verificare se ci sono altre entry nell'iteratore</li>
	 *   <li> il metodo {@code next()} per ottenere la prossima entry dall'iteratore</li>
	 * </ul>
	 * che non lanciano {@code UnsupportedOperationException}
	 */
	protected class SimpleEntrySetHIterator implements HIterator {
		// ------------------------------------------------------------------------------------------------------------
		// -------------- variabile di istanza per l'iteratore interno che itera sulle entry della mappa --------------
		private Iterator<Map.Entry<Object, Object>> i;

		// ------------------------------------------------------------------------------------------------------------
		// ---------------- metodi richiesti per l'implementazione della classe SimpleEntrySetHIterator ---------------

		/** Costruttore per la classe {@code SimpleEntrySetHIterator}. */
		private SimpleEntrySetHIterator() { i = map.entrySet().iterator(); }

		/**
		 * Metodo per verificare se ci sono altre entry nell'iteratore.
		 * @return true se ci sono altre entry, false altrimenti
		 */
		public boolean hasNext() { return i.hasNext(); }

		/**
		 * Metodo per ottenere la prossima entry dall'iteratore.
		 * @return la prossima entry dall'iteratore
		 */
		public Object next() {
			Map.Entry<Object, Object> entry = i.next();
			return new SimpleHEntry(entry.getKey(), entry.getValue());
		}

		// ------------------------------------------------------------------------------------------------------------
		// -------------------- metodi non implementati che lanciano UnsupportedOperationException --------------------

		/**
		 * Metodo per rimuovere l'ultima entry restituita dall'iteratore - non implementato.
		 * @throws UnsupportedOperationException funzione non supportata.
		 */
		public void remove() { throw new UnsupportedOperationException("remove not supported for SimpleHIterator"); }
	}
}
