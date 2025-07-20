# Secondo Appello 2025 di Elementi di Ingegneria del Software
Adapter che implementa l'interfaccia `Map` di `Java 2 Standard Edition 1.4.2` attraverso la classe `Hashtable` di `Java 2 Micro Edition CLDC 1.1` scritto da Giacomo Simonetto - n° matricola 2109923.

## Organizzazione delle directory del progetto
Il progetto è organizzato in due pacchetti principali: `myAdapter` e `myTest` che riflettono la struttura delle directory. Di seguito è riportata la struttura delle directory e tutti i file presenti in ciascun pacchetto.

```
- package myAdapter
  - HCollection.java -------------------- Interfaccia Collection di J2SE 1.4.2
  - HIterator.java ---------------------- Interfaccia Iterator di J2SE 1.4.2
  - HMap.java --------------------------- Interfaccia Map di J2SE 1.4.2
  - HSet.java --------------------------- Interfaccia Set di J2SE 1.4.2
  - IllegalStateException.java ---------- Eccezione non presente in J2ME CLDC 1.1
  - MapAdapter.java --------------------- Implementazione dell'adapter richiesto
  - package-info.java ------------------- Javadoc per il package myAdapter
  - UnsupportedOperationException.java -- Eccezione non presente in J2ME CLDC 1.1
```

```
- package myTest
  - AllTestsSuite.java ------------------ Classe che raggruppa tutti i test
  - package-info.java ------------------- Javadoc per il package myTest
  - SimpleHMapWithNulls.java ------------ Classe ausiliaria per i test
  - TestEmptyEntrySet.java -------------- Test per la vista EntrySet vuota
  - TestEmptyMapAdapter.java ------------ Test per la classe MapAdapter vuota
  - TestEmptyKeySet.java ---------------- Test per la vista KeySet vuota
  - TestEmptyValuesCollection.java ------ Test per la vista ValuesCollection vuota
  - TestEntryAdapter.java --------------- Test per l'adapter delle Entry
  - TestPopulatedEntrySet.java ---------- Test per la vista EntrySet popolata
  - TestPopulatedKeySet.java ------------ Test per la vista KeySet popolata
  - TestPopulatedMapAdapter.java -------- Test per la classe MapAdapter popolata
  - TestPopulatedValuesCollection.java -- Test per la vista ValuesCollection popolata
  - TestRunner.java --------------------- Classe per l'esecuzione dei test
```

È presente anche la cartella `JUnit` che contiene i file `.jar` necessari per l'esecuzione dei test con `JUnit 4.13` e `Hamcrest 1.3`.

Infine è presente la cartella `doc` che contiene la documentazione del progetto generata da `Javadoc` e la cartella `build` per contenere gli eventuali file `.class` generati dalla compilazione del progetto.

## Compilazione del progetto ed esecuzione dei test
Per una migliore organizzazione dei file di output, è stato scelto di indicare al comando `javac` di salvare i file `.class` nella cartella `build` per evitare di mescolare i file sorgente con i file compilati. I seguenti comandi terranno conto di questa organizzazione.

Per compilare i pacchetti `myAdapter` e `myTest` in un'unica volta, è possibile utilizzare il comando:
```
javac -d build -cp .:JUnit/junit-4.13.jar:JUnit/hamcrest-core-1.3.jar myAdapter/*.java myTest/*.java
```

Per eseguire i test definiti nel pacchetto `myTest`, è possibile utilizzare il comando:
```
java -cp .:build:JUnit/junit-4.13.jar:JUnit/hamcrest-core-1.3.jar myTest.TestRunner
```

Per generare la documentazione del progetto, è possibile utilizzare il comando:
```
javadoc -d doc -cp .:JUnit/junit-4.13.jar:JUnit/hamcrest-core-1.3-javadoc.jar:JUnit/junit-4.13-javadoc.jar:JUnit/hamcrest-core-1.3-javadoc.jar myAdapter/*.java myTest/*.java
```
