package dm.tests.task;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import dm.tasks.StartingTask;
/**
 * @author TimD
 *
 */
public class StartingTaskTest {
	Properties testP = new Properties();
	StartingTask testST;
	
	@Before
	public void setUp() throws Exception {
		testP.setProperty("field1","value1");
		testP.setProperty("field2","value2");
	}

	@Test
	public void testSetMandatoryFields() {
		//fail("Not yet implemented");
	}

	@Test
	public void testInit() {
		//fail("Not yet implemented");
	}

	@Test
	public void testVerifyInformationState() {
		//fail("Not yet implemented");
	}

	@Test
	public void testProcess() {
		//fail("Not yet implemented");
	}

	@Test
	public void testStartingTask() {
		testST = new StartingTask("test", testP);
		assertEquals(testST.getProperties(), testP);
		assertEquals(testST.getName(), "test");
		assertFalse(testST.isComplete());
	}

}
