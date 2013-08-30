/**
 * 
 */
package dm.tests.dm;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import dm.goals.Goal;
import dm.infostate.InformationState;
import dm.nlp.Message;
import dm.tasks.Task;

/**
 * @author TimD
 *
 */
public class GoalTest {
	//Created to test in abstract class Belief
	class TempGoal extends Goal{
		public TempGoal(String name){
			super(name);
		}
		@Override
		public double getConfidence() {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public Message createMessage() {
			// TODO Auto-generated method stub
			return null;
		}
	}
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
	TempGoal testGoal = new TempGoal("test");
	Properties p = new Properties();
	InformationState tempIS = new InformationState("tempIS");

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testIsAchieved() {
		assertTrue(testGoal.isAchieved());
	}

	@Test
	public void testGoal() {
		assertEquals(testGoal.getName(), "test");
		testGoal = new TempGoal("test2");
		assertEquals(testGoal.getName(), "test2");
	}

	@Test
	public void testSetInformationState() {
		assertEquals(testGoal.getIs(), null);
		testGoal.setInformationState(tempIS);
		assertEquals(tempIS, testGoal.getIs());
	}

	@Test
	public void testAddTask() {
		TempTask tempTask = new TempTask("tempTask", p);
		testGoal.pushTask(tempTask);
		assertEquals(tempTask, testGoal.getTasks().peek());
	}

	@Test
	public void testAddSubGoal() {
		TempGoal test2 = new TempGoal("test2");
		testGoal.addSubGoal(test2);
		assertEquals(test2, testGoal.getSubgoals().peek());
	}

	@Test
	public void testExecute() {
		assertEquals(testGoal.execute(), null);
	}

	@Test
	public void testProcessTasks() {
		//not sure how to test
		fail("Not yet implemented");
	}

	@Test
	public void testProcessSubgoals() {
		//not sure how to test
		fail("Not yet implemented");
	}

	@Test
	public void testEndDialogue() {
		testGoal.setInformationState(tempIS);
		testGoal.endDialogue();
		assertEquals(testGoal.getIs().getISFieldAsString(InformationState.AGENT_BELIEFS+":status"), "end");
	}

	@Test
	public void testGetConfidence() {
		//fail("Not yet implemented");
	}

	@Test
	public void testCreateMessage() {
		//fail("Not yet implemented");
	}

}
