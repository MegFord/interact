package dm.tests.filter;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import dm.filter.Filter;

public class TestFilter {

	public class AFilter extends Filter{

		public AFilter(String name, Properties properties) {
			super(name, properties);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void setMandatoryFields() {
			mandatoryFields = new String[3];
			mandatoryFields[0] = "papa";
			mandatoryFields[1] = "momma";
			mandatoryFields[2] = "bubba";
			
		}

		@Override
		public void init() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	AFilter testF;
	@Before
	public void setUp() throws Exception {
		Properties p =new Properties();
		p.setProperty("papa", "p");
		p.setProperty("momma","m");
		p.setProperty("bubba", "b");
		testF = new AFilter("lala", p);
	}

	@Test
	public void testGetFilterName(){
		assertEquals("lala", testF.getFilterName());
	}
	@Test
	public void testCheckMandatoryFields() {
		assertTrue(testF.checkMandatoryFields());
		
		Properties p = new Properties();
		p.setProperty("bubba", "d");
		testF = new AFilter("jj",p);
		assertFalse(testF.checkMandatoryFields());
	}

}
