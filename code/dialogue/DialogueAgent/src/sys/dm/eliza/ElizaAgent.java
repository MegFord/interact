package sys.dm.eliza;

import dm.dialogue.manager.DM;
import sys.dm.Agent;

public class ElizaAgent extends Agent {

	public ElizaAgent() {
		super();
	}
	
	
	public void initSystem() {
		ElizaAgentLoader eal = new ElizaAgentLoader("");
		dialogue = new DM(eal);
		dialogue.setRules(new ElizaRules());
	}
	
	
	public static void main(String[] args){
		ElizaAgent ea = new ElizaAgent();
		ea.execute();
	}

}
