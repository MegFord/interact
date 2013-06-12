package sys.dm;

import java.util.ArrayList;

import dm.filter.InformationStateFilter;
import dm.filter.MessageFilter;
import dm.goals.Goal;
import dm.loader.Loader;

public class DummyLoader extends Loader {

	public DummyLoader(String configFile) {
		super(configFile);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<MessageFilter> loadMessageFilters(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<InformationStateFilter> loadISFilters(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Goal> loadGoals() {
		// TODO Auto-generated method stub
		return null;
	}

}
