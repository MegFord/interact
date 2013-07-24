/**
 * 
 */
package dm.tests.is;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dm.infostate.InformationState;
import dm.infostate.beliefs.AgentBelief;
import dm.infostate.beliefs.Beliefs;
import dm.infostate.beliefs.ConversationBelief;
import dm.infostate.beliefs.UserBelief;
import dm.infostate.grounding.CommonGround;

/**
 * @author TimD
 *
 */
public class InformationStateTest {
	InformationState testIS = new InformationState("TestData");
	CommonGround tempCG;
	Beliefs<AgentBelief> tempAB;
	Beliefs<ConversationBelief> tempCB;
	Beliefs<UserBelief> tempUB;
	@Before
	public void setUp() throws Exception {
		tempCG = new CommonGround();
		tempAB = new Beliefs<AgentBelief>();
		tempCB = new Beliefs<ConversationBelief>();
		tempUB = new Beliefs<UserBelief>();
	}
	@Test
	public void testInformationState() {
		assertEquals(testIS.id, "TestData");
		testIS = new InformationState("TestData2");
		assertEquals(testIS.id, "TestData2");
	}

	@Test
	public void testUpdate() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetCommonGround() {
		testIS.setCommonGround(tempCG);
		assertEquals(tempCG, testIS.getCommonGround());
	}

	@Test
	public void testGetAgentBeliefs() {
		testIS.setAgentBeliefs(tempAB);
		assertEquals(tempAB, testIS.getAgentBeliefs());
	}

	@Test
	public void testSetAgentBeliefs() {
		testIS.setAgentBeliefs(tempAB);
		assertEquals(tempAB, testIS.getAgentBeliefs());
	}

	@Test
	public void testGetConversationBeliefs() {
		testIS.setConversationBeliefs(tempCB);
		assertEquals(tempCB, testIS.getConversationBeliefs());
	}

	@Test
	public void testSetConversationBeliefs() {
		testIS.setConversationBeliefs(tempCB);
		assertEquals(tempCB, testIS.getConversationBeliefs());
	}

	@Test
	public void testGetUserBeliefs() {
		testIS.setUserBeliefs(tempUB);
		assertEquals(tempUB, testIS.getUserBeliefs());
	}

	@Test
	public void testSetUserBeliefs() {
		testIS.setUserBeliefs(tempUB);
		assertEquals(tempUB, testIS.getUserBeliefs());
	}

	@Test
	public void testSetCommonGround() {
		testIS.setCommonGround(tempCG);
		assertEquals(tempCG, testIS.getCommonGround());
	}

	@Test
	public void testSetISField() {
		testIS.setISField("commonGround:from_city", "Chicago");
		testIS.setISField("conversationBeliefs:response", "I want to travel to Chicago");
		assertEquals(testIS.getISFieldAsString("commonGround:from_city"),"Chicago");
		assertEquals(testIS.getISFieldAsString("conversationBeliefs:response"),"I want to travel to Chicago");
	}

	@Test
	public void testGetISFieldAsString() {
		testIS.setISField("commonGround:from_city", "Chicago");
		testIS.setISField("conversationBeliefs:response", "I want to travel to Chicago");
		assertEquals(testIS.getISFieldAsString("commonGround:from_city"),"Chicago");
		assertEquals(testIS.getISFieldAsString("conversationBeliefs:response"),"I want to travel to Chicago");
	}

}
