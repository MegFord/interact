package dm.nlp.nlg;

import java.util.ArrayList;

import dm.filter.MessageFilter;
import dm.nlp.Message;

public class NLG
{
	private ArrayList<MessageFilter> filters;
	
	public NLG(ArrayList<MessageFilter> filters){
		this.filters = filters;
	}
	
	public ArrayList<MessageFilter> getFilters() {
		return filters;
	}

	public void setFilters(ArrayList<MessageFilter> filters) {
		this.filters = filters;
	}

	public static void output(String output)
	{
		System.out.println(output);
	}
	
	public String generate(Message m){
		return m.getMessageText();
	}
}