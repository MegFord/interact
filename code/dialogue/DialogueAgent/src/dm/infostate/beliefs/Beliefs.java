package dm.infostate.beliefs;

import java.util.HashMap;

public class Beliefs<T extends Belief> {
	HashMap<String, T> beliefs;
	
	public Beliefs(){
		beliefs = new HashMap<String, T>();
	}
	
	public void believe(T b){
		beliefs.put(b.getKey(), b);
	}
	
	/**
	 * This method gets a belief as an integer
	 */
	public String getBeliefString(String k){
		if (beliefs.containsKey(k))
			return beliefs.get(k).getValue().toString();
		else
			return null;
	}

	public Object getBeliefValue(String k){
		return beliefs.get(k).getValue();
	}
	
	public boolean isSet(String key){
		return (beliefs.containsKey(key) && beliefs.get(key)!=null && !beliefs.get(key).isEmpty());//Think that !beliefs.getKey(key) should not be false...
	}
}
