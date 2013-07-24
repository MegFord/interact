/**
 * 
 */
package dm.tests.is;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dm.infostate.beliefs.AgentBelief;

/**
 * @author TimD
 *
 */
public class AgentBeliefTest {
	AgentBelief testAB = new AgentBelief("field1","value1");
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAgentBelief() {
		assertEquals(testAB.getKey(), "field1");
		assertEquals(testAB.getValue(), "value1");
	}

}
