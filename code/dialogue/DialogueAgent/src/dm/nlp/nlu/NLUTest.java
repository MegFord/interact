/**
 * 
 */
package dm.nlp.nlu;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import dm.data.DataLocations;
import dm.filter.MessageFilter;
import dm.filter.message.RegExpFilter;
import dm.nlp.Message;

/**
 * @author TimD
 *
 */
public class NLUTest {
	ArrayList<MessageFilter> filters = new ArrayList<MessageFilter>();
	Properties to = new Properties();
	Properties from = new Properties();
	NLU testNLU = new NLU(filters);
	Message msg = new Message("I want to from Chicago to California tomorrow in the morning.");
	String locations = DataLocations.getLocations();
	
	@Before
	public void setUp() throws Exception {
		to.setProperty("regexp", "to\\s("+locations+")");
		to.setProperty("tag", "to");
		to.setProperty("inputField", "text");
		to.setProperty("outputField", "tagTo");
		to.setProperty("value", "to_city");
		
		from.setProperty("regexp", "from\\s("+locations+")");
		from.setProperty("tag", "from");
		from.setProperty("inputField", "text");
		from.setProperty("outputField", "tagFrom");
		from.setProperty("value", "from_city");
		
		filters.add(new RegExpFilter("ToFilter",to));
		filters.add(new RegExpFilter("FromFilter",from));
	}
	
	@Test
	public void testMain() {
		//no need to test?
		fail("Not yet implemented");
	}

	@Test
	public void testNLU() {
		assertEquals(testNLU.getFilters(), filters);
	}

	@Test
	public void testParse() {
		testNLU.parse(msg);
		assertEquals(msg.getProperty("from_city"), "from Chicago");
		assertEquals(msg.getProperty("to_city"), "to California");
	}

	@Test
	public void testLoadFilters() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetFilters() {
		assertEquals(testNLU.getFilters(), filters);
	}

	@Test
	public void testSetFilters() {
		testNLU = new NLU(null);
		assertEquals(testNLU.getFilters(), null);
		testNLU.setFilters(filters);
		assertEquals(testNLU.getFilters(), filters);
	}

}
