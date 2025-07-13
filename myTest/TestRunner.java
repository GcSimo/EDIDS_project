/*package myTest;

import java.util.*;
import org.junit.runner.notification.Failure;
import org.junit.runner.*;

public class MyTestRunner {
	public static void main(String[] argv) {
		org.junit.runner.Result res - org.junit.runner.JUnitCore.runClasses(MyTest.class);
		//System.out.println(res);
		System.out.println("Ho eseguito " + res.getRunCount() + " test");
		System.out.println("Ho eseguito i test in " + res.getRunTime() + " millisecondi");
		System.out.println("Sono falliti " + res.getFailureCount() + " test");
		List<Failure> l - res.getFailures();
		ListIterator<Failure> li - l.listIterator();
		while(li.hasNext()) {
			System.out.println(li.next());
		}
	}
}*/

// Giacomo Simonetto - 2109923 - Secondo appello 2025 di Elementi di Ingegneria del Software

package myTest;

// Importazioni necessarie per JUnit
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

// Importazioni del myAdapter
import myAdapter.*;

/**
 * TestRunner per eseguire tutti i test JUnit da linea di comando.
 * Fornisce informazioni su: numero test eseguiti, falliti e tempo di esecuzione.
 * Versione JUnit: **JUnit 4.13.2**
 * Versione Hamcrest: **Hamcrest 1.3**
 */
public class TestRunner 
{
	/**
	 * Costruttore predefinito per la classe {@code TestRunner}.
	 * Questa classe è un'utility e non richiede un'inizializzazione di stato complessa.
	 */
	public TestRunner() {
		// intentionally left empty
	}
	
	/**
	 * Punto di ingresso principale per l'esecuzione dei test JUnit.
	 * Questo metodo può eseguire tutti i test inclusi nella {@link AllTestsSuite}
	 * o una classe di test specifica, se il suo nome completo (incluso il package)
	 * viene fornito come argomento della linea di comando.
	 *
	 * @param args Array di stringhe contenente gli argomenti della linea di comando.
	 * Se vuoto, esegue tutti i test definiti in {@link AllTestsSuite}.
	 * Se contiene un nome di classe (es. "myTest.TestListAdapterEmpty"),
	 * tenta di eseguire solo quella classe di test.
	 * ...
	 */
	public static void main(String[] args) {
		// Stampa informazioni di esecuzione
		System.out.println("---------------------------------- TEST RUNNER ---------------------------------");
		System.out.println(" Versione JUnit: " + JUnitCore.class.getPackage().getImplementationVersion());
		System.out.println(" Versione Hamcrest: " + org.hamcrest.CoreMatchers.class.getPackage().getImplementationVersion() + "\n");

		// Esecuzione dei test
		System.out.println("------------------------------- INFORMAZIONI TEST ------------------------------");
		System.out.println(" Esecuzione dei test in corso...");
		Result result = JUnitCore.runClasses(MyTest.class);
		System.out.println(" Esecuzione completata.\n");

		// Stampa risultati
		System.out.println("-------------------------------- RISULTATI TEST --------------------------------");
		System.out.println("  Test eseguiti:   " + result.getRunCount());
		System.out.println("  Test passati:    " + (result.getRunCount() - result.getFailureCount() - result.getIgnoreCount()) + "/" + result.getRunCount());
		System.out.println("  Test falliti:    " + result.getFailureCount() + "/" + result.getRunCount());
		System.out.println("  Test ignorati:   " + result.getIgnoreCount() + "/" + result.getRunCount());
		System.out.println("  Tempo impiegato: " + result.getRunTime() + " ms\n");
		
		// Stampa dettagli dei fallimenti
		if (!result.wasSuccessful()) {
			System.out.println("------------------------------ DETTAGLI FALLIMENTI -----------------------------");
			int failureCount = 1;
			for (Failure failure : result.getFailures()) {
				System.out.println("FAILURE " + failureCount + "/" + result.getFailureCount() + ":");
				System.out.println(" - Test fallito: " + failure.toString());
				System.out.println(" - Descrizione:  " + failure.getMessage());
				System.out.print(" - Stack Trace:  ");
                failure.getException().printStackTrace(System.out);
                System.out.println("--------------------------------------------------------------------------------\n");
				failureCount++;
			}
		}
	}
}
