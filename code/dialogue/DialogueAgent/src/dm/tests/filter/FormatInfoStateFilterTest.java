/**
 * 
 */
package dm.tests.filter;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import dm.filter.info_state.FormatInfoStateFilter;

/**
 * @author TimD
 *
 */
public class FormatInfoStateFilterTest {
	FormatInfoStateFilter testF;
	Properties p = new Properties();
	
	@Before
	public void setUp() throws Exception {
		p.setProperty("inField", "value1");
		p.setProperty("outField", "value2");
		testF = new FormatInfoStateFilter("test", p);
	}

	@Test
	public void testSetMandatoryFields() {
		assertTrue(testF.checkMandatoryFields());
	}

	@Test
	public void testInit() {
		//Variables to check are private
		fail("Not yet implemented");
	}

	@Test
	public void testProcessFilter() {
		//Not sure how to test this, do you create a fully developed InformationState???
		fail("Not yet implemented");
	}

	@Test
	public void testFormatInfoStateFilter() {
		testF = new FormatInfoStateFilter("test2", p);
		assertEquals(testF.getFilterName(), "test2");
		assertTrue(testF.checkMandatoryFields());
	}

	@Test
	public void testExpandVariables() {
		//Not sure how to test this
		fail("Not yet implemented");
	}

}
