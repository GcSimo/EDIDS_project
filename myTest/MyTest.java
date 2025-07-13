package myTest;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Example test class to be run by the TestRunner.
 * Demonstrates basic JUnit 4.13 test methods.
 */
public class MyTest {

	/**
	 * A simple test method that asserts true.
	 */
	@Test
	public void testAddition() {
		int a = 5;
		int b = 3;
		assertEquals("5 + 3 should be 8", 8, a + b);
	}

	/**
	 * Another test method.
	 */
	@Test
	public void testBooleanCondition() {
		boolean condition = false;
		assertTrue("Condition should be true", condition);
	}

	/**
	 * A test method that is expected to fail for demonstration purposes.
	 */
	@Test
	public void testFailureExample() {
		// This test is intentionally made to fail to show how TestRunner reports failures.
		assertFalse("This assertion is expected to fail", true);
	}

	/**
	 * A test method that asserts for string equality.
	 */
	@Test
	public void testStringComparison() {
		String expected = "Hello";
		String actual = "Hello";
		assertEquals("Strings should be equal", expected, actual);
	}
}
