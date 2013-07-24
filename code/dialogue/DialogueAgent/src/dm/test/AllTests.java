package dm.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({dm.tests.filter.TestFilter.class,
	dm.tests.filter.TestFormatMessageFilter.class,
	dm.tests.is.AgentBeliefTest.class,
	dm.tests.is.BeliefsTest.class,
	dm.tests.is.BeliefTest.class,
	dm.tests.is.CommonGroundTest.class,
	dm.tests.task.ConfirmTaskTest.class,
	dm.tests.is.ConversationBeliefTest.class,
	dm.tests.dm.DMTest.class,
	dm.tests.task.EndingTaskTest.class,
	dm.tests.is.FactTest.class,
	dm.tests.task.FindInfoTaskTest.class,
	dm.tests.filter.FormatInfoStateFilterTest.class,
	dm.tests.filter.FormatMessageFilterTest.class,
	dm.tests.dm.GoalTest.class,
	dm.tests.is.InformationStateTest.class,
	dm.tests.filter.MessageTest.class,
	dm.test.nlp.NLGTest.class,
	dm.test.nlp.NLUTest.class,
	dm.tests.filter.RegExpFilterTest.class,
	dm.tests.task.StartingTaskTest.class,
	dm.tests.task.TaskTest.class,
	dm.tests.is.UserBeliefTest.class})
public class AllTests {

}
