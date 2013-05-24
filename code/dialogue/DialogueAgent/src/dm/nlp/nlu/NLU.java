package dm.nlp.nlu;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dm.data.DataLocations;
import dm.infostate.InfoState;
public class NLU
{
	public static void main (String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		//String input = keyboard.nextLine();
		//tag(input);
		tagTo("I want to go to California tomorrow.","to");
	}
	public static void tag(String s, String tag)
	{
		//String[] tags = new String[s.length()];
		
		//return tags;
		tagTo(s, tag);
		tagFrom(s, tag);
		tagDate(s, tag);
		tagTime(s, tag);
	}
	
	private static void tagTo(String s, String tag)
	{
		String locations = DataLocations.getLocations();
		s = s.toLowerCase();
		if (s.matches("^"+locations+"$"))
			InfoState.beliefs.put(tag,s);
		else
		{
			Pattern p = Pattern.compile("(?<=to\\s)("+locations+")", Pattern.CASE_INSENSITIVE);//("(?<=to\\s)\\w+", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(s);
			while(m.find())
				InfoState.beliefs.put("to",m.group());
		}
	}
	
	private static void tagFrom(String s, String tag)
	{
		String locations = DataLocations.getLocations();
		s = s.toLowerCase();
		if (s.matches("^"+locations+"$"))
			InfoState.beliefs.put(tag,s);
		else
		{
			Pattern p = Pattern.compile("(?<=from\\s)("+locations+")", Pattern.CASE_INSENSITIVE);//("(?<=to\\s)\\w+", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(s);
			while(m.find())
				InfoState.beliefs.put("from",m.group());
		}
	}
	
	private static void tagTime(String s, String tag)
	{
		int hours = 0;
		String regex = "(1[012]|[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)|([01][0-9]|2[0-3]):([0-5][0-9])";
		s = s.toLowerCase();
		if (s.matches("^"+regex+"$"))
			InfoState.beliefs.put(tag,s);
		else
		{
			Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);//("(?<=to\\s)\\w+", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(s);
			while(m.find())
			{
				String match = m.group();
				if (match.contains("pm"))
				{
					int colonIdx = match.indexOf(":");
					String time = "";
					hours = Integer.parseInt(match.substring(0,colonIdx));
					hours = (hours + 12)%24;
					time = hours + match.substring(colonIdx,colonIdx+3);
					if (hours == 0)
						time = "0" + time;
					InfoState.beliefs.put("time",time);
				}
				else
				{
					InfoState.beliefs.put("time",match);
				}
			}
		}
	}
	
	private static void tagDate(String s, String tag)
	{
		//String regex = "(?:Jan(?:uary)?|Feb(?:ruary)?|Mar(?:ch)?|Apr(?:il)?|May|Jun(?:e)?|Jul(?:y)?|Aug(?:ust)?|Sept?|September|Oct(?:ober)?|Nov(?:ember)?|Dec(?:ember)?)";
		String regex = "((19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01]))|((0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d\\d)";
		s = s.toLowerCase();
		if (s.matches("^"+regex+"$"))
			InfoState.beliefs.put(tag,s);
		else
		{
			Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);//("(?<=to\\s)\\w+", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(s);
			while(m.find())
				InfoState.beliefs.put("date",m.group());
		}
	}
	
	private void printArray(String[] arr)
	{
		for (String s: arr)
			System.out.print(s);
	}
}