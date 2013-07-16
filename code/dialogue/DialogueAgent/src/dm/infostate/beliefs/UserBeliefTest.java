/**
 * 
 */
package dm.infostate.beliefs;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author TimD
 *
 */
public class UserBeliefTest {
	UserBelief testUB = new UserBelief("field1", "value1");
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testUserBelief() {
		assertEquals(testUB.getKey(), "field1");
		assertEquals(testUB.getValue(), "value1");
	}

}
