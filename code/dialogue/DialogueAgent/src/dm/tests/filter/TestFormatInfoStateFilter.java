package dm.tests.filter;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import dm.filter.info_state.FormatInfoStateFilter;
import dm.infostate.InformationState;
import dm.infostate.beliefs.AgentBelief;
import dm.infostate.grounding.Fact;

public class TestFormatInfoStateFilter {

	InformationState is;
	@Before
	public void setUp() throws Exception {
		is = new InformationState("1234");
		Fact f1 = new Fact("from_city","Santiago");
		Fact f2 = new Fact("to_city", "Chicago");
		Fact f3 = new Fact("day",new Integer(28));
		is.getCommonGround().ground(f1);
		is.getCommonGround().ground(f2);
		is.getCommonGround().ground(f3);
		AgentBelief b1 = new AgentBelief("response1", "You are travelling from <from_city> to <to_city> on the <day>.");
		is.getAgentBeliefs().believe(b1);
	}

	@Test
	public void test() {
		Properties p = new Properties();
		p.setProperty("inField","response1");
		p.setProperty("outField", "final_response");
		FormatInfoStateFilter ff= new FormatInfoStateFilter("Format Filter", p);
		ff.processFilter(is);
		assertEquals(is.getAgentBeliefs().getBeliefString("final_response"),"You are travelling from Santiago to Chicago on the 28.");
	}

}
