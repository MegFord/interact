package dm.tasks;
public class StartingTask extends Task 
{
	public StartingTask(String hashtag)
	{
		super(hashtag);
	}
	
	public boolean perform()
	{
		prompt();
		getResponse();
		return true;
	}
	/*private boolean verify()
	{
		if (InfoState.beliefs.get("greet").matches("(\\bYes\\b)|(\\bYeah\\b)|(\\bYup\\b)|(\\bYep\\b)"))
			return true;
		else if (InfoState.beliefs.get("greet").matches("(\\bNo\\b)|(\\bNope\\b)|(\\bNah\\b)"))
		{
			NLG.output("It was nice talking to you. I hope to hear from you again soon!");
			InfoState.setProgramDone(true);
			return true;
		} 
		else
		{
			//InfoState.beliefs.put("greet","");
			return false;
		}

	}*/
}