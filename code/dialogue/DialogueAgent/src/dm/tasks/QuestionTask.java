package dm.tasks;

import dm.infostate.InfoState;

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
		return !InfoState.beliefs.get(hashtag).isEmpty();
	}
}