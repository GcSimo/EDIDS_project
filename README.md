# Adapter da HashTable J2ME CLDC 1.1 a Map J2SE 1.4.2

## To-Do List
- [x] implementare l'equals separatamente per le viste
- [x] usare un try catch nell'equals delle viste perché se la vista passata come parametro contiene null, containsAll lancerà una NullPointerException
- [x] implementare una mappa HMap che supporta chiave e valori nulli per il test della putAll
- [x] aggiungere javadoc dei package
- [x] aggiungere test ai containsAll/removeAll/retainAll nel caso di elementi nulli o non entry nelle collezioni
- [x] correggere removeAll e retainAll secondo le specifiche
- [ ] verificare che la Iterator.remove non alteri gli enumerations
- [ ] aggiungere messaggi alle eccezioni
- [ ] verificare il toString della Hashtable
- [ ] aggiungere eventuali modifiche al test del costruttore di copia di MapAdapter
- [x] ignored - verificare l'implementazione della entry della mappa -> non si salva il valore ma se lo va a ripescare ogni volta dalla mappa
- [x] ignored - gemini suggerisce di rimuovere il controllo null nel costruttore di EntryAdapter siccome le entry vengono costruite solo all'interno della EntryView e saranno semrpe diverse da null.
- [x] ignored - gemini suggerisce di aggiungere la null-safe in equals e hashcode delle EntryAdapter per essere compliant con la documentazione
- [x] ignored - implementazioni costruttori di copia nei set e nelle collection
- [x] ignored - aggiungere metodo alla classe view astratta che restituisce la mappa o la hashtable in modo da poter escludere le keySet della mappa (ed eventualmente anche tutte le altre) quando si prova ad inserirle come chiave
