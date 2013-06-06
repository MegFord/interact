package dm.tasks;

import dm.data.DataSource;
import dm.dialogue.manager.DM;
import dm.infostate.InformationState;
import dm.nlp.nlg.NLG;

public class ConfirmTask extends Task
{
	public ConfirmTask(String hashtag)
	{
		super(hashtag);
	}
	
	public boolean perform()
	{
/*		InformationState.questions.put("confirm", "It looks like you want to go from " + InformationState.beliefs.get("from") + 
						" to " + InformationState.beliefs.get("to") + " on " + InformationState.beliefs.get("date") + 
						" at " + InformationState.beliefs.get("time") + ". Is that correct?");
						
		prompt();
		getResponse();
		return verify();
		*/
		return false;
	}
	
	protected boolean verify()
	{
		if (response.toLowerCase().matches("(^|\\b)(yes|yeah|yup|yep|correct|right)(\\b|$)"))
			return true;
		else
		{
			NLG.output("I'm sorry about that. Let's try again.");
			//InformationState.repeated = true;
			//DM.createStack(DataSource.questionsFilename);
		}
		return true;
	}
}