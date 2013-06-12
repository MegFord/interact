package dm.infostate.grounding;

import java.util.HashMap;

public class CommonGround {

	HashMap<String,Fact> facts;
	
	public CommonGround(){
		facts = new HashMap<String, Fact>();
	}
	
	public void ground(Fact f){
		facts.put(f.getKey(), f);
	}
	
	/**
	 * This method gets the value of a fact as a String.
	 * @param k the key name of the fact.
	 * @return the value of the fact with key k.
	 */
	public String getReference(String k){
		if(facts.containsKey(k)){
			return facts.get(k).getValue().toString();
		} else {
			System.out.println("Common Ground: Field ["+k+"] does not exist");
			return null;
		}
	}
}
