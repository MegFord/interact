package dm.tests.filter;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import dm.infostate.InformationState;
import dm.tasks.Task;

public class TaskTest {
	class TempTask extends Task{
		public TempTask(String name, Properties p){
			super(name, p);
		}
		@Override
		public void setMandatoryFields() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void init() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public boolean verifyInformationState(InformationState is) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean process(InformationState is) {
			// TODO Auto-generated method stub
			return false;
		}
	}
	TempTask testT;
	Properties tempP;
	
	@Before
	public void setUp() throws Exception {
		tempP = new Properties();
		tempP.setProperty("field 1", "value 1");
		tempP.setProperty("field 2", "value 2");
		tempP.setProperty("field 3", "value 3");
		testT = new TempTask("temp", tempP);
	}

	@Test
	public void testTask() {
		assertEquals(testT.properties, tempP);
		assertEquals(testT.name, "temp");
		assertFalse(testT.isComplete());
		assertFalse(testT.checkMandatoryFields());
		//fail("Not yet implemented");
	}

	@Test
	public void testCheckMandatoryFields() {
		assertFalse(testT.checkMandatoryFields());
		//fail("Not yet implemented");
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
	public void testIsComplete() {
		assertFalse(testT.isComplete());
		//fail("Not yet implemented");
	}

	@Test
	public void testPerform() {
		InformationState is = null;
		assertFalse(testT.perform(is));
		//fail("Not yet implemented");
	}

	@Test
	public void testIsValidISField() {
		assertTrue(testT.isValidISField("agentBeliefs:Chicago"));
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

}
