/**
 * 
 */
package dm.filter.message;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import dm.nlp.Message;

/**
 * @author TimD
 *
 */
public class FormatMessageFilterTest {
	Properties p = new Properties();
	FormatMessageFilter testFMF;
	Message m;

	@Before
	public void setUp() throws Exception {
		p.setProperty("inField", "value1");
		p.setProperty("outField", "value2");
		testFMF = new FormatMessageFilter("TestFMF", p);
		m = new Message("I want to go to <to_city>");
	}

	@Test
	public void testSetMandatoryFields() {
		testFMF.setMandatoryFields();
		testFMF.init();
		assertTrue(testFMF.checkMandatoryFields());
		//fail("Not yet implemented");
	}

	@Test
	public void testInit() {
		testFMF.setMandatoryFields();
		testFMF.init();
		assertTrue(testFMF.checkMandatoryFields());
		//fail("Not yet implemented");
	}

	@Test
	public void testProcessFilter() {
		testFMF.setMandatoryFields();
		testFMF.init();
		System.out.println(testFMF.processFilter(m));
		fail("Not yet implemented");
	}

	@Test
	public void testFormatMessageFilter() {
		testFMF = new FormatMessageFilter("testFMF2", p);
		assertEquals(testFMF.getFilterName(), "testFMF2");
		assertTrue(testFMF.checkMandatoryFields());
		//fail("Not yet implemented");
	}

	@Test
	public void testExpandVariables() {
		System.out.println(testFMF.expandVariables("<to_city>", m));
		fail("Not yet implemented");
	}

}
