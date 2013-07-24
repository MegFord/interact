package sys.dm.eliza;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dm.filter.MessageFilter;
import dm.nlp.Message;

public class ElizaRegExpFilter extends MessageFilter 
{
	String regex;
	String outField;
	String inField;
	String responseExp;
	
	@Override
	public void setMandatoryFields() {
		mandatoryFields = new String[4];
		mandatoryFields[0] = "regexp";
		mandatoryFields[1] = "outputField";
		mandatoryFields[2] = "inputField";
		mandatoryFields[3] = "responseExp";
	}

	@Override
	public void init() {
		regex = properties.getProperty("regexp");
		outField = properties.getProperty("outputField");
		inField = properties.getProperty("inputField");
		responseExp = properties.getProperty("responseExp");
	}

	@Override
	public Message processFilter(Message msg) {
		// There should be two inFields. The content and the regexp
		// the output should be one. The tag
		String s = msg.getProperty(inField);
		System.out.println("Filter ["+name+"]. Regexp: ["+regex+"] to match :["+s+"]");
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);//("(?<=to\\s)\\w+", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(s);
		while(m.find()){
			if(properties.containsKey("value")){ // there is a place to put the match.
				String valueField = properties.getProperty("value");
				msg.setProperty(valueField, m.group());
				System.out.println("Found a match: " + m.group());
				msg.setProperty(outField,responseExp);
			}
		}
		return msg;
	}
	
	public ElizaRegExpFilter(String name, Properties p){
		super(name,p);
	}
}
