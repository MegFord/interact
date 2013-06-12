package dm.filter.message;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dm.filter.Filter;
import dm.filter.MessageFilter;
import dm.infostate.InformationState;
import dm.nlp.Message;

public class RegExpFilter extends MessageFilter{

	String regex;
	String tag;
	String outField;
	String inField;
	
	@Override
	public void setMandatoryFields() {
		mandatoryFields = new String[4];
		mandatoryFields[0] = "regexp";
		mandatoryFields[1] = "tag";
		mandatoryFields[2] = "outputField";
		mandatoryFields[3] = "inputField";
	}

	@Override
	public void init() {
		regex = properties.getProperty("regexp");
		tag = properties.getProperty("tag");
		outField = properties.getProperty("outputField");
		inField = properties.getProperty("inputField");
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
			msg.setProperty(outField, tag);
			if(properties.containsKey("value")){ // there is a place to put the match.
				String valueField = properties.getProperty("value");
				msg.setProperty(valueField, m.group());
			}
		}
		return msg;
	}
	
	public RegExpFilter(String name, Properties p){
		super(name,p);
	}
	
}
