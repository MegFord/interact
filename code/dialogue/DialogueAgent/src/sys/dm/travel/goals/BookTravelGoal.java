package sys.dm.travel.goals;

import java.util.Properties;

import dm.goals.Goal;
import dm.infostate.InformationState;
import dm.nlp.Message;
import dm.tasks.FindInfoTask;

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
		greet.setProperty("inFields", cg+":greeting"); // these must be <namespace>:<attribute>,<namespace2>:<attrib2>,...
		greet.setProperty("outField",conv+":response");
		greet.setProperty("responsePattern", "Great, welcome to the travel system");
		
		
		FindInfoTask findFrom = new FindInfoTask("fromTask", from);
		FindInfoTask findTo = new FindInfoTask("toTask", to);
		FindInfoTask findDate = new FindInfoTask("dateTask",date);
		FindInfoTask findGreeting = new FindInfoTask("greetTask",greet);
		
		
		this.addTask(findFrom);
		this.addTask(findTo);
		this.addTask(findDate);
		this.addTask(findGreeting);
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
			if (travelDt!=null)
				m.setProperty("travel_date", travelDt);
			if (toCity!=null)
				m.setProperty("to_city",toCity);
			if (fromCity!=null)
				m.setProperty("from_city", fromCity);
		}
		return m;
	}

	@Override
	public double getConfidence() {
		// TODO Auto-generated method stub
		return 1.0;
	}

}
