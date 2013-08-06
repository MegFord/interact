package dm.tests.filter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dm.nlp.Message;
/**
 * @author TimD
 *
 */
public class MessageTest {
	Message testA;
	Message testB;
	
	@Before
	public void setUp() throws Exception {
		testA = new Message("I want to go to Chicago");
		testB = new Message("I want to go to New York");
		testA.setProperty("city","Chicago");
	}
	
	@Test
	public void testGetProperties() {
		testA.setProperties(testB.getProperties());
		assertEquals(testA.getProperty("city","I want to go to New York"),"I want to go to New York");
	}

	@Test
	public void testSetProperties() {
		assertEquals(testB.getMessageText(), "I want to go to New York");
		testB.setProperties(testA.getProperties());
		assertEquals(testB.getProperties(), testA.getProperties());
	}

	@Test
	public void testMessage() {
		assertEquals(testA.getMessageText(), "I want to go to Chicago");
	}

	@Test
	public void testGetMessageText() {
		assertEquals(testA.getMessageText(), "I want to go to Chicago");
	}

	@Test
	public void testSetProperty() {
		testA.setProperty("city","Chicago");
		assertEquals(testA.getProperty("city"), "Chicago");
	}

	@Test
	public void testGetPropertyString() {
		assertEquals(testA.getProperty(Message.TEXT), "I want to go to Chicago");
	}

	@Test
	public void testGetPropertyStringString() {
		assertEquals(testB.getProperty("tex", "I want to test property"), "I want to test property");
	}
	
	@Test
	public void testGetIntProperty() {
		testA.setProperty("date", "123");
		assertTrue(testA.getIntProperty("date") == 123);
	}

	@Test
	public void testToString() {
		assertEquals(testB.toString(), "{text=I want to go to New York}");
	}

}
