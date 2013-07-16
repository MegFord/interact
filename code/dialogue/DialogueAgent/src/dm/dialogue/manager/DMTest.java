/**
 * 
 */
package dm.dialogue.manager;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import dm.filter.InformationStateFilter;
import dm.filter.MessageFilter;
import dm.goals.Goal;
import dm.loader.Loader;

/**
 * @author TimD
 *
 */
public class DMTest {
	class TempLoader extends Loader{
		public TempLoader(String configFile){
			super(configFile);
		}

		@Override
		public ArrayList<MessageFilter> loadMessageFilters(String key) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ArrayList<InformationStateFilter> loadISFilters(String key) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ArrayList<Goal> loadGoals() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	TempLoader tempTL = new TempLoader("temp");
	DM testDM = new DM(tempTL);

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testDM() {
		//all variables private
		fail("Not yet implemented");
	}

	@Test
	public void testIsOver() {
		System.out.println(testDM.isOver());
		//fail("Not yet implemented");
	}

	@Test
	public void testTakeTurn() {
		fail("Not yet implemented");
	}

	@Test
	public void testInitNlu() {
		fail("Not yet implemented");
	}

	@Test
	public void testInitGoals() {
		fail("Not yet implemented");
	}

	@Test
	public void testInitInfoState() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRules() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetRules() {
		fail("Not yet implemented");
	}

}
