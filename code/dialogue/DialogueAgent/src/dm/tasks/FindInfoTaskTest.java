package dm.tasks;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import dm.infostate.InformationState;
/**
 * @author TimD
 *
 */
public class FindInfoTaskTest {
	FindInfoTask testFIT;
	InformationState testIS;
	Properties p;
	@Before
	public void setUp() throws Exception {
		p = new Properties();
		p.setProperty("question", "Field 1");
		p.setProperty("inFields", "Field 2");
		p.setProperty("outField", "Field 3");
		p.setProperty("responsePattern", "Field 4");
		testFIT = new FindInfoTask("testFIT", p);
		testIS = new InformationState("testIS");
	}
	@Test
	public void testSetMandatoryFields() {
		assertTrue(testFIT.checkMandatoryFields());
	}

	@Test
	public void testInit() {
		assertEquals(testFIT.question,"Field 1");
		assertEquals(testFIT.inFields,"Field 2");
		assertEquals(testFIT.outField,"Field 3");
		assertEquals(testFIT.responsePattern,"Field 4");
		//How do you test the for loop portion of the method?
	}

	@Test
	public void testVerifyInformationState() {
		assertTrue(testFIT.verifyInformationState(testIS));
	}

	@Test
	public void testProcess() {
		assertTrue(testFIT.process(testIS));
	}

	@Test
	public void testFindInfoTask() {
		assertEquals(testFIT.properties, p);
		assertEquals(testFIT.name, "testFIT");
		assertFalse(testFIT.isComplete());
		assertTrue(testFIT.checkMandatoryFields());
	}

}
