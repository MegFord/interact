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

	// Left here in case we need it.
	public boolean verify()
	{
		if ("".toLowerCase().matches("(^|\\b)(yes|yeah|yup|yep|correct|right)(\\b|$)"))
			return true;
		else
		{
			NLG.output("I'm sorry about that. Let's try again.");
			//InformationState.repeated = true;
			//DM.createStack(DataSource.questionsFilename);
		}
		return true;
	}

	@Override
	public void setMandatoryFields() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean verifyInformationState(InformationState is) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean process(InformationState is) {
		// TODO Auto-generated method stub
		return false;
	}
}