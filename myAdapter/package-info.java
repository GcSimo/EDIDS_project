/**
 * Pacchetto contenente le interfacce e le implementazioni necessarie per poter utilizzare le mappe di J2SE 1.4.2 in J2ME CLDC 1.1.
 * 
 * <p>
 * Sono contenute le seguenti interfacce:
 * <ul>
 *   <li> l'interfaccia {@link myAdapter.HCollection} per gestire collezioni di elementi, compliant con J2SE 1.4.2;</li>
 *   <li> l'interfaccia {@link myAdapter.HSet} per gestire insiemi di elementi, compliant con J2SE 1.4.2;</li>
 *   <li> l'interfaccia {@link myAdapter.HMap} per gestire mappe di coppie chiave-valore, compliant con J2SE 1.4.2;</li>
 *   <li> l'interfaccia {@link myAdapter.HMap.HEntry} per rappresentare le entry di una mappa, compliant con J2SE 1.4.2;</li>
 *   <li> l'interfaccia {@link myAdapter.HIterator} per fornire un iteratore su collezioni, compliant con J2SE 1.4.2.</li>
 * </ul>
 * 
 * <p>
 * Sono contenute le seguenti classi che implementano le interfacce sopra elencate:
 * <ul>
 *   <li> la classe {@link myAdapter.MapAdapter} che implementa l'interfaccia {@link myAdapter.HMap} e fornisce un adattatore delle mappe di J2SE 1.4.2, a J2ME CLDC 1.1;</li>
 *   <li> la classe {@link myAdapter.MapAdapter.EntryAdapter} che implementa l'interfaccia {@link myAdapter.HMap.HEntry} e gestisce le funzionalità associate alle entry della mappa;</li>
 *   <li> la classe {@link myAdapter.MapAdapter.AbstractViewAdapter} che implementa l'interfaccia {@link myAdapter.HCollection} e fornisce i metdodi comuni per le viste backed della mappa;</li>
 *   <li> la classe {@link myAdapter.MapAdapter.KeySetAdapter} che implementa l'interfaccia {@link myAdapter.HSet} ed estende {@link myAdapter.MapAdapter.AbstractViewAdapter}, gestisce la vista per chiavi degli elementi della mappa;</li>
 *   <li> la classe {@link myAdapter.MapAdapter.ValuesCollectionAdapter} che implementa l'interfaccia {@link myAdapter.HCollection} ed estende {@link myAdapter.MapAdapter.AbstractViewAdapter}, gestisce la vista per valori degli elementi della mappa;</li>
 *   <li> la classe {@link myAdapter.MapAdapter.EntrySetAdapter} che implementa l'interfaccia {@link myAdapter.HSet} ed estende {@link myAdapter.MapAdapter.AbstractViewAdapter}, gestisce la vista per entry degli elementi della mappa;</li>
 *   <li> la classe {@link myAdapter.MapAdapter.AbstractIterator} che implementa l'interfaccia {@link myAdapter.HIterator} e fornisce i metodi comuni degli iteratori per le viste della mappa;</li>
 *   <li> la classe {@link myAdapter.MapAdapter.KeySetIterator} che implementa l'interfaccia {@link myAdapter.HIterator} ed estende {@link myAdapter.MapAdapter.AbstractIterator}, gestisce l'iteratore per la vista per chiavi della mappa;</li>
 *   <li> la classe {@link myAdapter.MapAdapter.ValuesCollectionIterator} che implementa l'interfaccia {@link myAdapter.HIterator} ed estende {@link myAdapter.MapAdapter.AbstractIterator}, gestisce l'iteratore per la vista per valori della mappa;</li>
 *   <li> la classe {@link myAdapter.MapAdapter.EntrySetIterator} che implementa l'interfaccia {@link myAdapter.HIterator} ed estende {@link myAdapter.MapAdapter.AbstractIterator}, gestisce l'iteratore per la vista per entry della mappa.</li>
 * </ul>
 * 
 * Per il corretto funzionamento di queste classi in CLDC 1.1 sono state implementate le seguenti classi di eccezione:
 * <ul>
 *   <li> la classe {@link myAdapter.UnsupportedOperationException} che estende {@link java.lang.RuntimeException} e rappresenta un'eccezione per operazioni non supportate ({@code add(Object o)} e {@code addAll(HCollection c)} delle viste);</li>
 *   <li> la classe {@link myAdapter.IllegalStateException} che estende {@link java.lang.RuntimeException} e rappresenta un'eccezione per operazioni non eseguibili su oggetti per stato illegale ({@code next()} degli iteratori).</li>
 * </ul>
 */
package myAdapter;
