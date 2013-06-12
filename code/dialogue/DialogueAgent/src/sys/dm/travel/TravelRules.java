package sys.dm.travel;

import java.util.Enumeration;
import java.util.Map.Entry;

import dm.infostate.InformationState;
import dm.infostate.grounding.Fact;
import dm.nlp.Message;
import dm.rules.Rules;

public class TravelRules extends Rules {

	public TravelRules() {
		// TODO Auto-generated constructor stub
	}

	// What to do when a message arrives here.
	@Override
	public void process(Message message) {
		// This simply copies fields into the CommonGround object in the info state.
		for(String key:message.getProperties().stringPropertyNames()){
			this.infoState.getCommonGround().ground(new Fact(key, message.getProperty(key)));
		}
	}

}
