package sys.dm.travel.goals;

import java.util.Properties;

import dm.goals.Goal;
import dm.infostate.InformationState;
import dm.nlp.Message;
import dm.tasks.FindInfoTask;
import dm.tasks.GreetingTask;

public class BookTravelGoal extends Goal {

	public BookTravelGoal(String name) {
		super(name);
		init();
		// TODO Auto-generated constructor stub
	}

	public void init(){
		String cg=is.COMMON_GROUND;
		String conv=is.CONVERSATION_BELIEFS;
		Properties from = new Properties();
		Properties to = new Properties();
		Properties date = new Properties();
		Properties greet = new Properties();
		Properties bye = new Properties();
		
		// The tasks are all FindInfoTask objects.
		from.setProperty("question", "Where are you departing from?");
		from.setProperty("inFields", cg+":from_city"); // these must be <namespace>:<attribute>,<namespace2>:<attrib2>,...
		from.setProperty("outField",conv+":response");
		from.setProperty("responsePattern", "Ok, travelling from <from_city>, right?");
		
		to.setProperty("question", "Where are you going to?");
		to.setProperty("inFields", cg+":to_city"); // these must be <namespace>:<attribute>,<namespace2>:<attrib2>,...
		to.setProperty("outField",conv+":response");
		to.setProperty("responsePattern", "Ok, travelling to <to_city>, right?");
		
		date.setProperty("question", "When do you want to leave?");
		date.setProperty("inFields", cg+":travel_date"); // these must be <namespace>:<attribute>,<namespace2>:<attrib2>,...
		date.setProperty("outField",conv+":response");
		date.setProperty("responsePattern", "Ok, travelling on <travel_date>, right?");
		
		
		greet.setProperty("question", "Hi, how are you?");
		greet.setProperty("inFields", cg+":greeting_info"); // these must be <namespace>:<attribute>,<namespace2>:<attrib2>,...
		greet.setProperty("outField",conv+":response");
		greet.setProperty("mood", conv+"mood");
		greet.setProperty("responsePattern", "<greeting_info> Welcome to the travel system. How can I help you?");
		
		bye.setProperty("question", "Alright, bye!");
		bye.setProperty("inFields", cg+":greeting"); // these must be <namespace>:<attribute>,<namespace2>:<attrib2>,...
		bye.setProperty("outField",conv+":response");
		bye.setProperty("responsePattern", "Alright, bye!");
		
		
		FindInfoTask findFrom = new FindInfoTask("fromTask", from);
		FindInfoTask findTo = new FindInfoTask("toTask", to);
		FindInfoTask findDate = new FindInfoTask("dateTask",date);
		//FindInfoTask findGreeting = new FindInfoTask("greetTask",greet);
		FindInfoTask findBye = new FindInfoTask("byeTask",bye);
		
		//FindInfoTask findGreeting = new FindInfoTask("greetTask",greet);
		GreetingTask findGreeting = new GreetingTask("greetTask",greet);
		this.pushTask(findBye);
		this.pushTask(findFrom);
		this.pushTask(findTo);
		this.pushTask(findDate);
		this.pushTask(findGreeting);
	}

	@Override
	public Message createMessage() {
		String pattern = is.getISFieldAsString(is.CONVERSATION_BELIEFS+":response");
		Message m = null;
		if(pattern!=null && !pattern.isEmpty()){
			m = new Message("");
			m.setProperty("response", pattern);
			String travelDt = is.getCommonGround().getReference("travel_date");
			String toCity = is.getCommonGround().getReference("to_city");
			String fromCity = is.getCommonGround().getReference("from_city");
			String greetResponse = is.getCommonGround().getReference("greeting_info");
			if (travelDt!=null)
				m.setProperty("travel_date", travelDt);
			if (toCity!=null)
				m.setProperty("to_city",toCity);
			if (fromCity!=null)
				m.setProperty("from_city", fromCity);
			if (greetResponse != null)
				m.setProperty("greeting_info", greetResponse);
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
		// Not used
		
	}

}
