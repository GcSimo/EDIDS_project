// Giacomo Simonetto - 2109923 - Secondo appello 2025 di Elementi di Ingegneria del Software

package myTest;

// Importazioni necessarie per JUnit
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * <b>Summary:</b>
 * Classe di utilità per eseguire i test JUnit definiti nella suite {@code AllTestsSuite}.
 * 
 * <p>
 * <b>Test Case Design:</b>
 * Questa classe è progettata per essere eseguita da linea di comando e stampa sempre su CLI un riepilogo dei risultati
 * dei test, inclusi i test passati, falliti e ignorati, insieme al tempo impiegato per l'esecuzione.
 * 
 * <p>
 * <b>Dependencies:</b>
 * <ul>
 *   <li>File JUnit.jar: {@code ./JUnit/junit-4.13.jar} - versione {@code 4.13}
 *   <li>File Hamcrest.jar: {@code ./JUnit/hamcrest-core-1.3.jar} - versione {@code 1.3}
 * </ul>
 */
public class TestRunner {
	/**
	 * Costruttore predefinito per la classe {@code TestRunner}.
	 * Questa classe è un'utility e non richiede un'inizializzazione di stato complessa.
	 */
	public TestRunner() {
		// intentionally left empty
	}
	
	/**
	 * Metodo principale per eseguire i test.
	 * Stampa informazioni di esecuzione, esegue i test definiti nella suite {@code AllTestsSuite}
	 * e stampa i risultati dei test, inclusi eventuali fallimenti.
	 * 
	 * @param args Argomenti della riga di comando (non utilizzati in questo contesto).
	 */
	public static void main(String[] args) {
		// Stampa informazioni di esecuzione
		System.out.println("---------------------------------- TEST RUNNER ---------------------------------");
		System.out.println(" Versione JUnit: " + JUnitCore.class.getPackage().getImplementationVersion());
		System.out.println(" Versione Hamcrest: " + org.hamcrest.CoreMatchers.class.getPackage().getImplementationVersion() + "\n");

		// Esecuzione dei test
		System.out.println("------------------------------- INFORMAZIONI TEST ------------------------------");
		System.out.println(" Esecuzione dei test in corso...");
		Result result = JUnitCore.runClasses(AllTestsSuite.class);
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
