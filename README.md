# Adapter da HashTable J2ME CLDC 1.1 a Map J2SE 1.4.2

## To-Do List
- [x] ignored - implementazioni costruttori di copia nei set e nelle collection
- [x] implementare l'equals separatamente per le viste
- [x] usare un try catch nell'equals delle viste perché se la vista passata come parametro contiene null, containsAll lancerà una NullPointerException
- [x] implementare una mappa HMap che supporta chiave e valori nulli per il test della putAll
- [x] aggiungere javadoc dei package
- [ ] aggiungere metodo alla classe view astratta che restituisce la mappa o la hashtable in modo da poter escludere le keySet della mappa (ed eventualmente anche tutte le altre) quando si prova ad inserirle come chiave
- [ ] verificare l'implementazione della entry della mappa -> non si salva il valore ma se lo va a ripescare ogni volta dalla mappa
- [ ] verificare che la Iterator.remove non alteri gli enumerations
- [ ] aggiungere messaggi alle eccezioni
- [ ] verificare il toString della Hashtable
- [ ] aggiungere eventuali modifiche al test del costruttore di copia di MapAdapter
- [ ] gemini suggerisce di rimuovere il controllo null nel costruttore di EntryAdapter siccome le entry vengono costruite solo all'interno della EntryView e saranno semrpe diverse da null.
- [ ] gemini suggerisce di aggiungere la null-safe in equals e hashcode delle EntryAdapter per essere compliant con la documentazione

## Scelte progettuali
- hashtable CLDC 1.1 non accetta null key o value, le mappe di J2SE1.4.2 supportano null key e value (eccetto la TreeMap che supporta solo null value) per semplicità di implementazione ho scelto di non includere la possibilità di avere null key o value (maggiore coerenza con la sottostruttura e meno overhead/bugs).
- siccome non si utilizzano i generics, bensì si utilizza il polimorfismo dato dalla gerarchia delle classi, nella la maggior parte delle funzioni non è gestito il controllo sul tipo dei dati passati e di conseguenza non lanciano le ``ClassCastException``.
- per ottenere gli effetti delle viste backed, le classi dedicate (EntrySet, KeySet, ValuesCollection) sono state implementate basandosi sulla stessa sottostruttura hashtable della mappa

## Tester
- verificare che se the map contains more than Integer.MAX_VALUE elements, size() returns Integer.MAX_VALUE
- verificare il corretto lancio delle eccezioni
- ...
