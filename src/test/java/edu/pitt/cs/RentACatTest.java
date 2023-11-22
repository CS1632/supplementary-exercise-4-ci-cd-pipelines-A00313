package edu.pitt.cs;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.mockito.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RentACatTest {

	/**
	 * The test fixture for this JUnit test. Test fixture: a fixed state of a set of
	 * objects used as a baseline for running tests. The test fixture is initialized
	 * using the @Before setUp method which runs before every test case. The test
	 * fixture is removed using the @After tearDown method which runs after each
	 * test case.
	 */

	RentACat r; // Object to test
	Cat c1; // First cat object
	Cat c2; // Second cat object
	Cat c3; // Third cat object

	@Before
	public void setUp() throws Exception {
		// Turn on automatic bug injection in the Cat class, to emulate a buggy Cat.
		// Your unit tests should work regardless of these bugs.
		// Config.setBuggyRentACat(true);
		Cat.bugInjectionOn = true;

		// INITIALIZE THE TEST FIXTURE
		// 1. Create a new RentACat object and assign to r
		r = RentACat.createInstance();

		// 2. Create an unrented Cat with ID 1 and name "Jennyanydots", assign to c1
		c1 = mock(Cat.class);
		when(c1.getId()).thenReturn(1);
        when(c1.getName()).thenReturn("Jennyanydots");
        

		// 3. Create an unrented Cat with ID 2 and name "Old Deuteronomy", assign to c2
		c2 = mock(Cat.class);
		when(c2.getId()).thenReturn(2);
        when(c2.getName()).thenReturn("Old Deuteronomy");

		// 4. Create an unrented Cat with ID 3 and name "Mistoffelees", assign to c3
		c3 = mock(Cat.class);
		when(c3.getId()).thenReturn(3);
        when(c3.getName()).thenReturn("Mistoffelees");
	}

	@After
	public void tearDown() throws Exception {
		// Not necessary strictly speaking since the references will be overwritten in
		// the next setUp call anyway and Java has automatic garbage collection.
		r = null;
		c1 = null;
		c2 = null;
		c3 = null;
	}

	/**
	 * Test case for Cat getCat(int id).
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call getCat(2).
	 * Postconditions: Return value is null.
	 * </pre>
	 */

	@Test
	public void testGetCatNullNumCats0() {
		assertNull("Expected null for cat with ID 2, but got a non-null cat.", r.getCat(2));
	}

	/**
	 * Test case for Cat getCat(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call getCat(2).
	 * Postconditions: Return value is not null.
	 *                 Returned cat has an ID of 2.
	 * </pre>
	 */

	@Test
	public void testGetCatNumCats3() {
		// when(c1.getId()).thenReturn(1);
        // when(c2.getId()).thenReturn(2);
		// when(c3.getId()).thenReturn(3);
        r.addCat(c1);
        r.addCat(c2);
        r.addCat(c3);

        Cat cat = r.getCat(2);
		// cat.returnCat();

        assertNotNull(cat);
        assertEquals("Expected getId() to return 2",2, cat.getId());
	}

	/**
	 * Test case for boolean catAvailable(int id).
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call catAvailable(2).
	 * Postconditions: Return value is false.
	 * </pre>
	 */

	@Test
	public void testCatAvailableFalseNumCats0() {
		assertFalse("Expected false for cat with ID 2, but got true.", r.catAvailable(2));
	}

	/**
	 * Test case for boolean catAvailable(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 *                c3 is rented.
	 *                c1 and c2 are not rented.
	 * Execution steps: Call catAvailable(2).
	 * Postconditions: Return value is true.
	 * </pre>
	 */

	@Test
	public void testCatAvailableTrueNumCats3() {
		when(c1.getId()).thenReturn(1);
        when(c2.getId()).thenReturn(2);
        when(c3.getId()).thenReturn(3);
		when(c1.getRented()).thenReturn(false);
		when(c2.getRented()).thenReturn(false);
		when(c3.getRented()).thenReturn(true);
        r.addCat(c1);
        r.addCat(c2);
        r.addCat(c3);

        c3.rentCat();

        assertTrue("Expected cat with id = 2 to be available", r.catAvailable(2));
	}

	/**
	 * Test case for boolean catAvailable(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 *                c2 is rented.
	 *                c1 and c3 are not rented.
	 * Execution steps: Call catAvailable(2).
	 * Postconditions: Return value is false.
	 * </pre>
	 */

	@Test
	public void testCatAvailableFalseNumCats3() {
		when(c1.getId()).thenReturn(1);
        when(c2.getId()).thenReturn(2);
        when(c3.getId()).thenReturn(3);
		when(c1.getRented()).thenReturn(false);
		when(c2.getRented()).thenReturn(true);
		when(c3.getRented()).thenReturn(false);
        r.addCat(c1);
        r.addCat(c2);
        r.addCat(c3);
        c2.rentCat();

        assertFalse("Expected cat with id = 2 to not be available", r.catAvailable(2));
	}

	/**
	 * Test case for boolean catExists(int id).
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call catExists(2).
	 * Postconditions: Return value is false.
	 * </pre>
	 */

	@Test
	public void testCatExistsFalseNumCats0() {
		assertFalse("Expected false for cat with ID 2, but got true.", r.catExists(2));
	}

	/**
	 * Test case for boolean catExists(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call catExists(2).
	 * Postconditions: Return value is true.
	 * </pre>
	 */

	@Test
	public void testCatExistsTrueNumCats3() {
		when(c1.getId()).thenReturn(1);
        when(c2.getId()).thenReturn(2);
        when(c3.getId()).thenReturn(3);
        r.addCat(c1);
        r.addCat(c2);
        r.addCat(c3);

        assertTrue("Expected cat with id = 2 to exist", r.catExists(2));
	}

	/**
	 * Test case for String listCats().
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call listCats().
	 * Postconditions: Return value is "".
	 * </pre>
	 */

	@Test
	public void testListCatsNumCats0() {
		assertEquals("Expected an empty string for listing cats, but got a non-empty string.", "", r.listCats());
	}

	/**
	 * Test case for String listCats().
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call listCats().
	 * Postconditions: Return value is "ID 1. Jennyanydots\nID 2. Old
	 *                 Deuteronomy\nID 3. Mistoffelees\n".
	 * </pre>
	 */

	@Test
	public void testListCatsNumCats3() {
		when(c1.getId()).thenReturn(1);
        when(c2.getId()).thenReturn(2);
        when(c3.getId()).thenReturn(3);
        when(c1.getName()).thenReturn("Jennyanydots");
        when(c2.getName()).thenReturn("Old Deuteronomy");
        when(c3.getName()).thenReturn("Mistoffelees");

        r.addCat(c1);
        r.addCat(c2);
        r.addCat(c3);

        String expected = "ID 1. Jennyanydots\nID 2. Old Deuteronomy\nID 3. Mistoffelees\n";
		String output = r.listCats();
        assertEquals("output does not equal:\n" + expected, expected, output);
	}

	/**
	 * Test case for boolean rentCat(int id).
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call rentCat(2).
	 * Postconditions: Return value is false.
	 * </pre>
	 */

	@Test
	public void testRentCatFailureNumCats0() {
		assertFalse("Expected false for renting cat with ID 2, but got true.", r.rentCat(2));
	}

	/**
	 * Test case for boolean rentCat(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 *                c2 is rented.
	 * Execution steps: Call rentCat(2).
	 * Postconditions: Return value is false.
	 *                 c1.rentCat(), c2.rentCat(), c3.rentCat() are never called.
	 * </pre>
	 * 
	 * Hint: See sample_code/mockito_example/NoogieTest.java in the course
	 * repository for an example of behavior verification. Refer to the
	 * testBadgerPlayCalled method.
	 */

	@Test
	public void testRentCatFailureNumCats3() {
		when(c1.getId()).thenReturn(1);
        when(c2.getId()).thenReturn(2);
        when(c3.getId()).thenReturn(3);
		when(c2.getRented()).thenReturn(true);
        r.addCat(c1);
        r.addCat(c2);
        r.addCat(c3);

        assertFalse("Expected a return of false for rentCat for cat with id=2", r.rentCat(2));
	}

	/**
	 * Test case for boolean returnCat(int id).
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call returnCat(2).
	 * Postconditions: Return value is false.
	 * </pre>
	 */

	@Test
	public void testReturnCatFailureNumCats0() {
		assertFalse("Expected false for returning cat with ID 2, but got true.", r.returnCat(2));
	}

	/**
	 * Test case for boolean returnCat(int id).
	 * 
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 *                c2 is rented.
	 * Execution steps: Call returnCat(2).
	 * Postconditions: Return value is true.
	 *                 c2.returnCat() is called exactly once.
	 *                 c1.returnCat() and c3.returnCat are never called.
	 * </pre>
	 * 
	 * Hint: See sample_code/mockito_example/NoogieTest.java in the course
	 * repository for an example of behavior verification. Refer to the
	 * testBadgerPlayCalled method.
	 */

	@Test
	public void testReturnCatNumCats3() {
		when(c1.getId()).thenReturn(1);
        when(c2.getId()).thenReturn(2);
        when(c3.getId()).thenReturn(3);
		when(c2.getRented()).thenReturn(true);

        r.addCat(c1);
        r.addCat(c2);
        r.addCat(c3);

        assertTrue("Expected a return true for reutrnCat for cat with id = 2", r.returnCat(2));

		Mockito.verify(c2, Mockito.times(1)).returnCat();
		Mockito.verify(c1, Mockito.times(0)).returnCat();
		Mockito.verify(c3, Mockito.times(0)).returnCat();


	}
}
