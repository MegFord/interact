package sys.dm.eliza;

import java.util.ArrayList;
import java.util.Properties;

import dm.data.DataLocations;
import dm.dialogue.manager.DM;
import dm.filter.InformationStateFilter;
import dm.filter.MessageFilter;
import dm.filter.message.FormatMessageFilter;
import dm.filter.message.GreetingMessageFilter;
import dm.filter.message.RegExpFilter;
import dm.goals.Goal;
import sys.dm.DummyLoader;

public class ElizaAgentLoader extends DummyLoader {

	public ElizaAgentLoader(String configFile) {
		super(configFile);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<MessageFilter> loadMessageFilters(String key) {
		ArrayList<MessageFilter> filters = new ArrayList<MessageFilter>();
		if(key.equals(DM.NLU))
			loadNLUFilters(filters);
		else
			loadNLGFilters(filters);
		return filters;
	}

	public void loadNLUFilters(ArrayList<MessageFilter> filters){
		Properties catchAll = new Properties();
		Properties greet= new Properties();
		Properties feelings = new Properties();
		//Properties topic = new Properties();
		
		catchAll.setProperty("regexp", ".+");
		catchAll.setProperty("inputField", "text");
		catchAll.setProperty("outputField", "question");
		catchAll.setProperty("value", "group");
		catchAll.setProperty("responseExp", "Tell me more.");		
		
		greet.setProperty("regexp", "(?i)\\b(hello|hey|hi|what\'s up|sup|salutations)\\b");
		greet.setProperty("inputField", "text");
		greet.setProperty("outputField", "question");
		greet.setProperty("value", "group");
		greet.setProperty("responseExp", "How are you doing?");
	
		feelings.setProperty("regexp", "(?i)(?<=(I\'m|am|doin|doin\'|doing|feeling)\\s)?((not\\s)?\\b(fine|good|well|terrific|wonderful|great|fantastic|phenomenal|bad|terrib|horribl))\\b");
		feelings.setProperty("inputField", "text");
		feelings.setProperty("outputField", "question");
		feelings.setProperty("value", "group");
		feelings.setProperty("responseExp", "Do you want to talk about why you're feeling <group>? Or, feel free to talk about something else.");
		
		/*topic.setProperty("regexp", "(i|I)\\slike\\s.*");
		topic.setProperty("inputField", "text");
		topic.setProperty("outputField", "question");
		topic.setProperty("value", "group");
		topic.setProperty("responseExp", "What do you think of <group>?");*/
	
		
		filters.add(new ElizaRegExpFilter("catchAll",catchAll));
		filters.add(new ElizaRegExpFilter("greet",greet));
		filters.add(new ElizaRegExpFilter("feelings",feelings));

		

	}
	
	public void loadNLGFilters(ArrayList<MessageFilter> filters){
		Properties p = new Properties();
		p.setProperty("inField","response");
		p.setProperty("outField", "text");
		FormatMessageFilter ff= new FormatMessageFilter("Format Filter", p);
		
		filters.add(ff);

	}
	@Override
	public ArrayList<InformationStateFilter> loadISFilters(String key) {
		// TODO Auto-generated method stub
		return new ArrayList<InformationStateFilter>();
	}

	@Override
	public ArrayList<Goal> loadGoals() {
		ArrayList<Goal> g = new ArrayList<Goal>();
		//g.add(new ElizaAdviceGoal("AdviceGoal"));
		g.add(new ElizaChitChatGoal("ChitChat"));
		return g;
	}

}
