/**
 * 
 */
package dm.nlp.nlg;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import dm.data.DataLocations;
import dm.filter.MessageFilter;
import dm.filter.message.FormatMessageFilter;
import dm.nlp.Message;
import dm.nlp.nlu.NLU;

/**
 * @author TimD
 *
 */
public class NLGTest {
	ArrayList<MessageFilter> filters = new ArrayList<MessageFilter>();
	Properties p = new Properties();
	NLG testNLG = new NLG(filters);
	Message msg = new Message("I want to from Chicago to California tomorrow in the morning.");
	String locations = DataLocations.getLocations();

	@Before
	public void setUp() throws Exception {
		p.setProperty("inField","response");
		p.setProperty("outField", "text");
		filters.add(new FormatMessageFilter("Format Filter", p));
	}

	@Test
	public void testNLG() {
		assertEquals(testNLG.getFilters(), filters);
	}

	@Test
	public void testGetFilters() {
		assertEquals(testNLG.getFilters(), filters);
	}

	@Test
	public void testSetFilters() {
		testNLG = new NLG(null);
		assertEquals(testNLG.getFilters(), null);
		testNLG.setFilters(filters);
		assertEquals(testNLG.getFilters(), filters);
	}

	@Test
	public void testOutput() {
		//output method call System.out.println nothing to test
		fail("Not yet implemented");
	}

	@Test
	public void testGenerate() {
		//Not sure how to test this
		System.out.println(testNLG.generate(msg));
		fail("Not yet implemented");
	}

}
