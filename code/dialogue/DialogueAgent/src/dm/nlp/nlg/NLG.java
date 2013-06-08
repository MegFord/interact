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
	
	public String generate(Message message){
		// message passes through a lot of 
		// filters and is returned.
		if(message!=null){
			for (MessageFilter f:filters){
				System.out.println("NLG Processing Filter:"+f.getFilterName());
				message = f.processFilter(message);
			}
			return message.getMessageText();
		} else {
			return null;
		}
	}
}