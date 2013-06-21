package dm.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({dm.tests.filter.TestFilter.class,dm.tests.filter.TestFormatMessageFilter.class})
public class AllTests {

}
