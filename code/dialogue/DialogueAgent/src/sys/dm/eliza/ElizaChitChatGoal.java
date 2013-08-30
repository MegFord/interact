package sys.dm.eliza;

import java.util.Properties;

import dm.goals.Goal;
import dm.nlp.Message;
import dm.tasks.AskQuestionTask;
import dm.tasks.FindInfoTask;
import dm.tasks.GreetingTask;

public class ElizaChitChatGoal extends Goal {

	public ElizaChitChatGoal(String name) {
		super(name);
		init();
		// TODO Auto-generated constructor stub
	}

	public void init(){
		String cg=is.COMMON_GROUND;
		String conv=is.CONVERSATION_BELIEFS;
		
		// The tasks are all FindInfoTask objects.
		Properties greet = new Properties();
		
		greet.setProperty("question", "Hello. I am Eliza.");
		greet.setProperty("inFields", cg+":group"); // these must be <namespace>:<attribute>,<namespace2>:<attrib2>,...
		greet.setProperty("outField",conv+":response");
		
		AskQuestionTask testGreet = new AskQuestionTask("AskQuestion-Greet", greet);
		
		
		this.pushTask(testGreet);

	}

	@Override
	public Message createMessage() {
		String pattern = is.getISFieldAsString(is.CONVERSATION_BELIEFS+":response");
		System.out.println("creating message");
		Message m = null;
		if(pattern!=null && !pattern.isEmpty()){
			m = new Message("");
			m.setProperty("response", pattern);
			String matchingGroup = is.getCommonGround().getReference("group");
			if (matchingGroup != null)
				m.setProperty("group", matchingGroup);
		}
		return m;
	}

	@Override
	public double getConfidence() {
		// TODO Auto-generated method stub
		return 1.0;
	}

	@Override
	public void update() {
		// Not used.
		
	}
	

}
