/**
 * 
 */
package dm.tests.is;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dm.infostate.beliefs.Belief;

/**
 * @author TimD
 *
 */
public class BeliefTest {
	//Created to test in abstract class Belief
	class TempBelief extends Belief{
		public TempBelief(String k, Object v) {
			super(k, v);
		}
	}
	TempBelief testTB = new TempBelief("field1", "value1");
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testBelief() {
		assertEquals(testTB.getKey(), "field1");
		assertEquals(testTB.getValue(), "value1");
	}

	@Test
	public void testGetKey() {
		assertEquals(testTB.getKey(), "field1");
	}

	@Test
	public void testSetKey() {
		testTB.setKey("field2");
		assertEquals(testTB.getKey(), "field2");
	}

	@Test
	public void testGetValue() {
		assertEquals(testTB.getValue(), "value1");
	}

	@Test
	public void testSetValue() {
		testTB.setValue("value2");
		assertEquals(testTB.getValue(), "value2");
	}

	@Test
	public void testIsEmpty() {
		assertTrue(testTB.isEmpty());
	}

}
