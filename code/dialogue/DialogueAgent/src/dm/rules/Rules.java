package dm.rules;

import dm.infostate.InformationState;
import dm.nlp.Message;

public abstract class Rules {

	protected InformationState infoState;
	public InformationState getInfoState() {
		return infoState;
	}

	public void setInfoState(InformationState infoState) {
		this.infoState = infoState;
	}

	public Rules() {
		
	}
	
	public abstract void process(Message message);
	
	

}
