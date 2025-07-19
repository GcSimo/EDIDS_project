/**
 * Pacchetto contenente le classi di test per tutti i metodi della classe {@link myAdapter.MapAdapter} e delle viste di tale classe.
 * 
 * <p>
 * Sono contenute le seguenti classi di test:
 * <ul>
 *   <li> la classe {@link myTest.TestEmptyMapAdapter} che testa i metodi della classe {@link myAdapter.MapAdapter} su una mappa vuota;</li>
 *   <li> la classe {@link myTest.TestEmptyKeySet} che testa i metodi della vista {@link myAdapter.MapAdapter.KeySetAdapter} di una mappa vuota;</li>
 *   <li> la classe {@link myTest.TestEmptyValuesCollection} che testa i metodi della vista {@link myAdapter.MapAdapter.ValuesCollectionAdapter} di una mappa vuota;</li>
 *   <li> la classe {@link myTest.TestEmptyEntrySet} che testa i metodi della vista {@link myAdapter.MapAdapter.EntrySetAdapter} di una mappa vuota;</li>
 *   <li> la classe {@link myTest.TestPopulatedMapAdapter} che testa i metodi della classe {@link myAdapter.MapAdapter} su una mappa popolata;</li>
 *   <li> la classe {@link myTest.TestPopulatedKeySet} che testa i metodi della vista {@link myAdapter.MapAdapter.KeySetAdapter} di una mappa popolata;</li>
 *   <li> la classe {@link myTest.TestPopulatedValuesCollection} che testa i metodi della vista {@link myAdapter.MapAdapter.ValuesCollectionAdapter} di una mappa popolata;</li>
 *   <li> la classe {@link myTest.TestPopulatedEntrySet} che testa i metodi della vista {@link myAdapter.MapAdapter.EntrySetAdapter} di una mappa popolata.</li>
 * </ul>
 * 
 * <p>
 * Sono inoltre contenute le seguenti classi per l'esecuzione di test specifici:
 * <ul>
 *   <li> la classe {@link myTest.AllTestsSuite} che raggruppa tutti i test in un'unica suite di test;</li>
 *   <li> la classe {@link myTest.TestRunner} che esegue i test definiti nella suite {@link myTest.AllTestsSuite}.</li>
 * </ul>
 * 
 * <p>
 * È stata inoltre implementata la classe {@link myTest.SimpleHMapWithNulls} che implementa l'interfaccia {@link myAdapter.HMap}
 * e supporta valori {@code null}. Questa classe è richiestaper testare il metodo {@link myAdapter.MapAdapter#putAll(HMap m)} per
 * verificare il corretto lancio delle eccezioni nella gestione dei valori {@code null} e per facilitare la creazione di entry
 * senza ricorrere a magheggi strani (siccome {@link myAdapter.MapAdapter.EntryAdapter} ha il costruttore non accessibile).
 * Siccome il suo impiego è molto limitato, tutti i metodi non necessari non sono implementati e lanciano
 * {@link myAdapter.UnsupportedOperationException} se invocati.
 */
package myTest;
