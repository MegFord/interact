/**
 * 
 */
package dm.tests.filter;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import dm.filter.message.RegExpFilter;
import dm.nlp.Message;

/**
 * @author TimD
 *
 */
public class RegExpFilterTest {
	RegExpFilter testREF;
	Properties p = new Properties();
	Message tempM = new Message("I want to go to Chicago");
	
	@Before
	public void setUp() throws Exception {
		p.setProperty("regexp", "value1");
		p.setProperty("tag", "value2");
		p.setProperty("outputField", "value3");
		p.setProperty("inputField", "value4");
		testREF = new RegExpFilter("test", p);
	}

	@Test
	public void testSetMandatoryFields() {
		assertEquals(p.getProperty("regexp"), "value1");
		assertEquals(p.getProperty("tag"), "value2");
		assertEquals(p.getProperty("outputField"), "value3");
		assertEquals(p.getProperty("inputField"), "value4");
		//fail("Not yet implemented");
	}

	@Test
	public void testInit() {
		testREF.init();
		assertEquals(p.getProperty("regexp"), testREF.getRegex());
		assertEquals(p.getProperty("tag"), testREF.getTag());
		assertEquals(p.getProperty("outputField"), testREF.getOutField());
		assertEquals(p.getProperty("inputField"), testREF.getInField());
		//fail("Not yet implemented");
	}

	@Test
	public void testProcessFilter() {
		//can not get this method to work correctly msg.getProperty("inField")??? where does that come from?
		System.out.println(testREF.processFilter(tempM).getProperty("test"));
	}

	@Test
	public void testRegExpFilter() {
		assertEquals(testREF.getFilterName(), "test");
		assertEquals(p.getProperty("regexp"), "value1");
		assertEquals(p.getProperty("tag"), "value2");
		assertEquals(p.getProperty("outputField"), "value3");
		assertEquals(p.getProperty("inputField"), "value4");
		//fail("Not yet implemented");
	}

}
