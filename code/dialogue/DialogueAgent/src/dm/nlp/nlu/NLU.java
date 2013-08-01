package dm.nlp.nlu;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dm.data.DataLocations;
import dm.filter.Filter;
import dm.filter.MessageFilter;
import dm.filter.message.RegExpFilter;
import dm.infostate.InformationState;
import dm.nlp.Message;
public class NLU
{
	private ArrayList<MessageFilter> filters;
	
	public static void main (String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		//String input = keyboard.nextLine();
		//tag(input);
		Message msg = new Message("I want to from Chicago to California tomorrow in the morning.");
		NLU nlu = new NLU(new ArrayList<MessageFilter>());
		msg = nlu.parse(msg);
		System.out.println(msg);
	}
	
	public NLU(ArrayList<MessageFilter> filters){
		this.filters = filters;
	}
	
	public Message parse(Message message){
		loadFilters();
		// message passes through a lot of 
		// filters and is returned.
		for (MessageFilter f:filters){
			System.out.println("NLU Processing Filter:"+f.getFilterName());
			message = f.processFilter(message);
		}
		return message;
	}
	
	public void loadFilters(){
		
	}
	
		
	public ArrayList<MessageFilter> getFilters() {
		return filters;
	}

	public void setFilters(ArrayList<MessageFilter> filters) {
		this.filters = filters;
	}

	private void printArray(String[] arr)
	{
		for (String s: arr)
			System.out.print(s);
	}
}