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
public class FactTest {
	Fact testF = new Fact("field1", "value1");
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFact() {
		assertEquals(testF.getKey(), "field1");
		assertEquals(testF.getValue(), "value1");
	}

	@Test
	public void testGetKey() {
		assertEquals(testF.getKey(), "field1");
	}

	@Test
	public void testSetKey() {
		testF.setKey("field2");
		assertEquals(testF.getKey(), "field2");
	}

	@Test
	public void testGetValue() {
		assertEquals(testF.getValue(), "value1");
	}

	@Test
	public void testSetValue() {
		testF.setValue("value2");
		assertEquals(testF.getValue(), "value2");
	}

}
