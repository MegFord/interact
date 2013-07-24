package dm.tasks;

import java.util.Properties;

import dm.data.DataSource;
import dm.dialogue.manager.DM;
import dm.infostate.InformationState;
import dm.nlp.nlg.NLG;

public class ConfirmTask extends Task
{

	
	public ConfirmTask(String name, Properties p) {
		super(name, p);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void setMandatoryFields() {
		// TODO Auto-generated method stub
		mandatoryFields = new String[2];
		mandatoryFields[0] = "outField"; // the field that will contain the output
		mandatoryFields[1] = "messagePattern"; // the patterned message to output
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean verifyInformationState(InformationState is) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean process(InformationState is) {
		is.setISField(properties.getProperty(mandatoryFields[0]), properties.getProperty(mandatoryFields[1]));
		is.setISField(is.CONVERSATION_BELIEFS+":status", "end");
		return true;
	}
}