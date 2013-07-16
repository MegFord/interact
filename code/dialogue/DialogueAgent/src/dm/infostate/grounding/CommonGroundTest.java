/**
 * 
 */
package dm.infostate.grounding;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author TimD
 *
 */
public class CommonGroundTest {
	CommonGround testCG = new CommonGround();
	Fact city = new Fact("field1", "value1"); 
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCommonGround() {
		//Not needed to test because constructor just creates empty hash map?
		fail("Not yet implemented");
	}

	@Test
	public void testGround() {
		testCG.ground(city);
		assertTrue(testCG.facts.containsKey("field1"));
	}

	@Test
	public void testGetReference() {
		testCG.ground(city);
		assertEquals(testCG.getReference("field1"), "value1");
		assertEquals(testCG.getReference("field2"), null);
	}

}
