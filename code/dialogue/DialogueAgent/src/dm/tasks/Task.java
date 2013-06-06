package dm.tasks;
import java.util.Scanner;

import dm.infostate.InformationState;
import dm.nlp.nlg.NLG;
import dm.nlp.nlu.NLU;

public abstract class Task 
{
	//private String question = "";
	protected String response = "";
	protected String hashtag = "";
	
	public Task()
	{
		super();
	}
	
	public Task(String hashtag)
	{
		this.hashtag = hashtag;
	}
	
	protected void prompt() 
	{
		//String question = InformationState.questions.get(hashtag);
		//NLG.output(question);
	}
	
	protected void getResponse()
	{
		/*Scanner keyboard = new Scanner(System.in);
		String answer = keyboard.nextLine();
		NLU.tag(answer,hashtag);
		//InfoState.beliefs.put(hashtag, answer);
		response = answer;
		//return answer;
		 
		 */
	}

	public abstract boolean perform();
	/*{
		prompt(); 
		getResponse();
		return isValidResponse();
	}*/
	/*
	public boolean wasAnswered()
	{
		return !InformationState.beliefs.get(hashtag).isEmpty();
	}
	
	public String getHashtag()
	{
		return hashtag;
	}
	
	protected boolean verify()
	{
		return true;
	}
	
	private boolean isValidResponse()
	{
		return !InfoState.beliefs.get(hashtag).isEmpty();
	}*/
	
}