package dm.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({dm.tests.filter.TestFilter.class,
	dm.tests.filter.TestFormatMessageFilter.class,
	dm.tests.filter.AgentBeliefTest.class,
	dm.tests.filter.BeliefsTest.class,
	dm.tests.filter.BeliefTest.class,
	dm.tests.filter.CommonGroundTest.class,
	dm.tests.filter.ConfirmTaskTest.class,
	dm.tests.filter.ConversationBeliefTest.class,
	dm.tests.filter.DMTest.class,
	dm.tests.filter.EndingTaskTest.class,
	dm.tests.filter.FactTest.class,
	dm.tests.filter.FindInfoTaskTest.class,
	dm.tests.filter.FormatInfoStateFilterTest.class,
	dm.tests.filter.FormatMessageFilterTest.class,
	dm.tests.filter.GoalTest.class,
	dm.tests.filter.InformationStateTest.class,
	dm.tests.filter.MessageTest.class,
	dm.tests.filter.NLGTest.class,
	dm.tests.filter.NLUTest.class,
	dm.tests.filter.RegExpFilterTest.class,
	dm.tests.filter.StartingTaskTest.class,
	dm.tests.filter.TaskTest.class,
	dm.tests.filter.UserBeliefTest.class})
public class AllTests {

}
