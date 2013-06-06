package dm.tasks;

import dm.infostate.InformationState;

public class QuestionTask extends Task
{
	public QuestionTask(String hashtag)
	{
		super(hashtag);
	}
	
	
	public boolean perform()
	{
		prompt();
		getResponse();
		return verify();
	}
	
	protected boolean verify()
	{
		return false; //!InformationState.beliefs.get(hashtag).isEmpty();
	}
}