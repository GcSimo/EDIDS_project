# Adapter da HashTable J2ME CLDC 1.1 a Map J2SE 1.4.2

## Struttura

| nome_file | descrizione |
| :--- | :--- |
| ``myAdapter.HCollection`` | interfaccia J2SE 1.4.2 per tutte le strutture dati |
| ``myAdapter.HIterator`` | interfaccia J2SE 1.4.2 per gli iteratori delle strutture dati |
| ``myAdapter.HMap`` | interfaccia J2SE 1.4.2 per map |
| ``myAdapter.HSet`` | interfaccia J2SE 1.4.2 per set, estende ``myAdapter.HCollection`` |
| ``MapAdapter`` | classe CLDC1.1 che implementa``myAdapter.HIterator`` di J2SE 1.4.2 | 

## To-Do List
- [ ] interfaccia ``myAdapter.HCollection``
  - [ ] metodi da documentazione J2SE 1.4.2
  - [ ] documentazione metodi
- [ ] interfaccia ``myAdapter.HIterator``
  - [ ] metodi da documentazione J2SE 1.4.2
  - [ ] documentazione metodi
- [ ] interfaccia ``myAdapter.HMap``
  - [ ] metodi da documentazione J2SE 1.4.2
  - [ ] documentazione metodi
- [ ] interfaccia ``myAdapter.HSet``
  - [ ] metodi da documentazione J2SE 1.4.2
  - [ ] documentazione metodi
- [ ] classe ``myAdapter.MapAdapter``
  - [ ] metodi da interfaccia ``HMap``
  - [ ] documentazione metodi

- implementare la corrispondenza tra gli Enumeration di CLDC 1.1 e gli HSet, per sincronizzare le modifiche tra mappa e HSet ottenuti attraverso i metodi ``MapAdapter.keySet()`` e ``MapAdapter.entrySet()``
- implementare la corrispondenza tra gli Enumeration di CLDC 1.1 e gli HCollection per sincronizzare le modifiche tra mappa e Collection ottenuti dal metodo ``MapAdapter.values()``
- bloccare l'inserimento della mappa stessa come chiave di una entry

## Scelte progettuali
- hashtable CLDC 1.1 non accetta null key o value, le mappe di J2SE1.4.2 supportano null key e value (eccetto la TreeMap che supporta solo null value) per semplicità di implementazione ho scelto di non includere la possibilità di avere null key o value (maggiore coerenza con la sottostruttura e meno overhead/bugs).
- siccome non si utilizzano i generics, bensì si utilizza il polimorfismo dato dalla gerarchia delle classi, nella la maggior parte delle funzioni non è gestito il controllo sul tipo dei dati passati e di conseguenza non lanciano le ``ClassCastException``.
- sebbene la MapAdapter non supporti chiavi e valori null, è stato deciso di implementare la MapEntry in modo da supportarli per una maggiore portabilità, compatibilità e uniformità verso altre implementazioni della mappa che supportano chiavi o valori null.

## Tester
- verificare che se the map contains more than Integer.MAX_VALUE elements, size() returns Integer.MAX_VALUE
- verificare il corretto lancio delle eccezioni
- 