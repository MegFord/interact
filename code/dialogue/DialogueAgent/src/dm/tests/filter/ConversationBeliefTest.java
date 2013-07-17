/**
 * 
 */
package dm.tests.filter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dm.infostate.beliefs.ConversationBelief;

/**
 * @author TimD
 *
 */
public class ConversationBeliefTest {
	ConversationBelief testCB = new ConversationBelief("field1", "value1");

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testConversationBelief() {
		assertEquals(testCB.getKey(), "field1");
		assertEquals(testCB.getValue(), "value1");
	}

}
