package dm.loader;

import java.util.ArrayList;

import dm.filter.Filter;
import dm.filter.InformationStateFilter;
import dm.filter.MessageFilter;
import dm.goals.Goal;

public abstract class Loader {

	public Loader(String configFile) {
		// TODO Auto-generated constructor stub
	}
	
	public abstract ArrayList<MessageFilter> loadMessageFilters(String key);
	
	public abstract ArrayList<InformationStateFilter> loadISFilters(String key);
	
	public abstract ArrayList<Goal> loadGoals();

}
