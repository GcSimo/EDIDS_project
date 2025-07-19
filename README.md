# Adapter da HashTable J2ME CLDC 1.1 a Map J2SE 1.4.2

## To-Do List
- [x] implementare l'equals separatamente per le viste
- [x] usare un try catch nell'equals delle viste perché se la vista passata come parametro contiene null, containsAll lancerà una NullPointerException
- [x] implementare una mappa HMap che supporta chiave e valori nulli per il test della putAll
- [x] aggiungere javadoc dei package
- [x] aggiungere test ai containsAll/removeAll/retainAll nel caso di elementi nulli o non entry nelle collezioni
- [x] correggere removeAll e retainAll secondo le specifiche
- [x] aggiungere eventuali modifiche al test del costruttore di copia di MapAdapter
- [x] verificare il toString della Hashtable
- [x] verificare che la Iterator.remove non alteri gli enumerations
---
- [x] ignored - verificare l'implementazione della entry della mappa -> non si salva il valore ma se lo va a ripescare ogni volta dalla mappa
- [x] ignored - gemini suggerisce di rimuovere il controllo null nel costruttore di EntryAdapter siccome le entry vengono costruite solo all'interno della EntryView e saranno semrpe diverse da null.
- [x] ignored - gemini suggerisce di aggiungere la null-safe in equals e hashcode delle EntryAdapter per essere compliant con la documentazione
- [x] ignored - implementazioni costruttori di copia nei set e nelle collection
- [x] ignored - aggiungere metodo alla classe view astratta che restituisce la mappa o la hashtable in modo da poter escludere le keySet della mappa (ed eventualmente anche tutte le altre) quando si prova ad inserirle come chiave
- [x] ignored - aggiungere messaggi alle eccezioni
---
- [x] aggiungere test per valori doppi, correggere equals per valori doppi
  - [x] removeAll -> [1,2,2,3,4].removeAll([2,4]) = [1,3]
  - [x] retainAll -> [1,2,2,3,4].retainAll([2,4]) = [2,2,4]
  - [x] equals -> test con collection con valori null + test con collection con valori multipli [1,2,2] e [1,1,2]
- [ ] controllare le eccezioni silenti per utilizzo di contains e remove all'interno delle altre funzioni
