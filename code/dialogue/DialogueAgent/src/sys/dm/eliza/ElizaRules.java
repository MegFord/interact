package sys.dm.eliza;

import dm.infostate.grounding.Fact;
import dm.nlp.Message;
import sys.dm.DummyRules;

public class ElizaRules extends DummyRules {

	public ElizaRules() {
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
