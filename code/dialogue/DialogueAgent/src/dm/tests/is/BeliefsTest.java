/**
 * 
 */
package dm.tests.is;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dm.infostate.beliefs.Belief;
import dm.infostate.beliefs.Beliefs;

/**
 * @author TimD
 *
 */
public class BeliefsTest {
	//Created to test in abstract class Belief
	class TempBelief extends Belief{
		public TempBelief(String k, Object v) {
			super(k, v);
		}
	}
	TempBelief testTB = new TempBelief("field1", "value1");
	
	Beliefs testB = new Beliefs();
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testBeliefs() {
		//No need to test due to constructor finishes creating HashMap
		fail("Not yet implemented");
	}

	@Test
	public void testBelieve() {
		testB.believe(testTB);
		assertEquals(testB.getBeliefString("field1"),"value1");
	}

	@Test
	public void testGetBeliefString() {
		testB.believe(testTB);
		assertEquals(testB.getBeliefString("field1"),"value1");
		assertEquals(testB.getBeliefString("field2"),null);
	}

	@Test
	public void testGetBeliefValue() {
		testB.believe(testTB);
		assertEquals(testB.getBeliefValue("field1"),"value1");

	}

	@Test
	public void testIsSet() {
		System.out.println(testB.isSet("field1"));
		assertFalse(testB.isSet("field1"));//This is wrong see method for more information
		fail("Not yet implemented");
	}

}
