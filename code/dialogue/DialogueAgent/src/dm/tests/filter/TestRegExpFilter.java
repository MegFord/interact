package dm.tests.filter;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import dm.data.DataLocations;
import dm.filter.message.RegExpFilter;
import dm.nlp.Message;

public class TestRegExpFilter {
	Message msg;
	Properties from = new Properties();
	@Before
	public void setUp() throws Exception {
		String locations = DataLocations.getLocations();
		
		msg = new Message("I want to go from Chicago to California on 06-29-2013.");
				
		from.setProperty("regexp", "from\\s("+locations+")");
		from.setProperty("tag", "from");
		from.setProperty("inputField", "text");
		from.setProperty("outputField", "tagFrom");
		from.setProperty("value", "from_city");
	}

	@Test
	public void test() {
		RegExpFilter ref = new RegExpFilter("FromFilter", from);
		ref.processFilter(msg);
		String value = msg.getProperty("from_city");
		assertEquals("FromFilter", "from Chicago", value);
	}

}
