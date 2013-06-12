package sys.dm.travel;

import dm.dialogue.manager.DM;
import sys.dm.Agent;

public class TravelAgent extends Agent {

	public TravelAgent() {
		super();
	}

	@Override
	public void initSystem() {
		TravelAgentLoader tal = new TravelAgentLoader("");
		dialogue = new DM(tal);
		dialogue.setRules(new TravelRules());
	}
	
	public static void main(String[] args){
		TravelAgent ta = new TravelAgent();
		ta.execute();
	}
	
	
	

}
