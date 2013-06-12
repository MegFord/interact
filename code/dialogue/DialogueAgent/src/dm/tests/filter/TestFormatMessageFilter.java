package dm.tests.filter;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import dm.filter.info_state.FormatInfoStateFilter;
import dm.filter.message.FormatMessageFilter;
import dm.infostate.InformationState;
import dm.infostate.beliefs.AgentBelief;
import dm.infostate.grounding.Fact;
import dm.nlp.Message;

public class TestFormatMessageFilter {

	Message msg;
	@Before
	public void setUp() throws Exception {
		msg = new Message("");
		msg.setProperty("from_city","Santiago");
		msg.setProperty("to_city", "Chicago");
		msg.setProperty("day","28 of April");
		msg.setProperty("pattern","You are travelling from <from_city> to <to_city> on the <day>.");
	}

	@Test
	public void test() {
		Properties p = new Properties();
		p.setProperty("inField","pattern");
		p.setProperty("outField", "final_response");
		FormatMessageFilter ff= new FormatMessageFilter("Format Filter", p);
		ff.processFilter(msg);
		assertEquals(msg.getProperty("final_response"),"You are travelling from Santiago to Chicago on the 28 of April.");

	}

}
