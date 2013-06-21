package sys.dm.travel;

import java.util.ArrayList;
import java.util.Properties;

import sys.dm.travel.goals.BookTravelGoal;

import dm.data.DataLocations;
import dm.dialogue.manager.DM;
import dm.filter.InformationStateFilter;
import dm.filter.MessageFilter;
import dm.filter.info_state.FormatInfoStateFilter;
import dm.filter.message.FormatMessageFilter;
import dm.filter.message.GreetingMessageFilter;
import dm.filter.message.RegExpFilter;
import dm.goals.Goal;
import dm.infostate.InformationState;
import dm.loader.Loader;

public class TravelAgentLoader extends Loader {

	public TravelAgentLoader(String configFile) {
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
		Properties to= new Properties();
		Properties from= new Properties();
		Properties date= new Properties();
		Properties time= new Properties();
		Properties greet = new Properties();
		
		String locations = DataLocations.getLocations();
		to.setProperty("regexp", "to\\s("+locations+")");
		to.setProperty("tag", "to");
		to.setProperty("inputField", "text");
		to.setProperty("outputField", "tagTo");
		to.setProperty("value", "to_city");
		
		from.setProperty("regexp", "from\\s("+locations+")");
		from.setProperty("tag", "from");
		from.setProperty("inputField", "text");
		from.setProperty("outputField", "tagFrom");
		from.setProperty("value", "from_city");
		
		time.setProperty("regexp", "(1[012]|[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)|([01][0-9]|2[0-3]):([0-5][0-9])");
		time.setProperty("tag", "time");
		time.setProperty("inputField", "text");
		time.setProperty("outputField", "tagTime");
		time.setProperty("value", "travel_time");
		
		date.setProperty("regexp", "((19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01]))|((0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d\\d)");
		date.setProperty("tag", "date");
		date.setProperty("inputField", "text");
		date.setProperty("outputField", "tagDate");
		date.setProperty("value", "travel_date");
		
		greet.setProperty("input", "text");
		greet.setProperty("output", "greeting");
		
		filters.add(new RegExpFilter("ToFilter",to));
		filters.add(new RegExpFilter("FromFilter",from));
		filters.add(new RegExpFilter("TimeFilter",time));
		filters.add(new RegExpFilter("DateFilter",date));	
		filters.add(new GreetingMessageFilter("greetDetect", greet));
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
		g.add(new BookTravelGoal("BookTravel"));
		return g;
	}

}
