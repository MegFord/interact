package dm.tasks;

import dm.data.DataSource;
import dm.dialogue.manager.DM;
import dm.infostate.InfoState;
import dm.nlp.nlg.NLG;

public class ConfirmTask extends Task
{
	public ConfirmTask(String hashtag)
	{
		super(hashtag);
	}
	
	public boolean perform()
	{
		InfoState.questions.put("confirm", "It looks like you want to go from " + InfoState.beliefs.get("from") + 
						" to " + InfoState.beliefs.get("to") + " on " + InfoState.beliefs.get("date") + 
						" at " + InfoState.beliefs.get("time") + ". Is that correct?");
						
		prompt();
		getResponse();
		return verify();
	}
	
	protected boolean verify()
	{
		if (response.toLowerCase().matches("(^|\\b)(yes|yeah|yup|yep|correct|right)(\\b|$)"))
			return true;
		else
		{
			NLG.output("I'm sorry about that. Let's try again.");
			InfoState.repeated = true;
			DM.createStack(DataSource.questionsFilename);
		}
		return true;
	}
}