package dm.tasks;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

public class ConfirmTaskTest {
	Properties testP = new Properties();
	ConfirmTask testCT = new ConfirmTask("test", testP);

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
	public void testConfirmTask() {
		assertEquals(testCT.properties, testP);
		assertEquals(testCT.name, "test");
		assertFalse(testCT.complete);
	}

	@Test
	public void testVerify() {
		assertTrue(testCT.verify());

	}

}
