/**
 * Pacchetto contenente le classi di test per tutti i metodi della classe {@link myAdapter.MapAdapter} e delle viste di tale classe.
 * 
 * <p>
 * Sono contenute le seguenti classi di test:
 * <ul>
 *   <li> la classe {@link myTest.TestEmptyMapAdapter} che testa i metodi della classe {@link myAdapter.MapAdapter} su una mappa vuota;</li>
 *   <li> la classe {@link myTest.TestKeySetOnEmptyMapAdapter} che testa i metodi della vista {@link myAdapter.MapAdapter.KeySetAdapter} di una mappa vuota;</li>
 *   <li> la classe {@link myTest.TestValuesCollectionOnEmptyMapAdapter} che testa i metodi della vista {@link myAdapter.MapAdapter.ValuesCollectionAdapter} di una mappa vuota;</li>
 *   <li> la classe {@link myTest.TestEntrySetOnEmptyMapAdapter} che testa i metodi della vista {@link myAdapter.MapAdapter.EntrySetAdapter} di una mappa vuota;</li>
 *   <li> la classe {@link myTest.TestPopulatedMapAdapter} che testa i metodi della classe {@link myAdapter.MapAdapter} su una mappa popolata;</li>
 *   <li> la classe {@link myTest.TestKeySetOnPopulatedMapAdapter} che testa i metodi della vista {@link myAdapter.MapAdapter.KeySetAdapter} di una mappa popolata;</li>
 *   <li> la classe {@link myTest.TestValuesCollectionOnPopulatedMapAdapter} che testa i metodi della vista {@link myAdapter.MapAdapter.ValuesCollectionAdapter} di una mappa popolata;</li>
 *   <li> la classe {@link myTest.TestEntrySetOnPopulatedMapAdapter} che testa i metodi della vista {@link myAdapter.MapAdapter.EntrySetAdapter} di una mappa popolata.</li>
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
 * e supporta valori {@code null}. Questa classe è richiesta per testare il metodo {@link myAdapter.MapAdapter#putAll(HMap m)} per
 * verificare il corretto lancio delle eccezioni nella gestione dei valori {@code null}. Siccome il suo impiego è molto limitato,
 * tutti i metodi non necessari non sono implementati e lanciano {@link myAdapter.UnsupportedOperationException}.
 */
package myTest;
