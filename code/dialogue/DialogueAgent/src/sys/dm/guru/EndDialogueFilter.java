/**
 * 
 */
package sys.dm.guru;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dm.filter.MessageFilter;
import dm.nlp.Message;

/**
 * @author TimD
 *
 */
public class EndDialogueFilter extends MessageFilter{

	String inputField;
	String regExp;
	String outputField;
	String responseNeeded;
	String matcherValue;
	String responseExp;
	
	public EndDialogueFilter(String name, Properties properties) {
		super(name, properties);
	}

	@Override
	public void setMandatoryFields() {
		mandatoryFields = new String[6];
		mandatoryFields[0]="inputField";
		mandatoryFields[1]="regExp";
		mandatoryFields[2]="outputField";
		mandatoryFields[3]="responseNeeded";
		mandatoryFields[4]="matcherValue";
		mandatoryFields[5]="responseExp";
	}

	@Override
	public void init() {
		inputField = properties.getProperty("inputField");//takes in user input
		regExp = properties.getProperty("regExp");//regexp used to filter user input
		outputField = properties.getProperty("outputField");//system output field
		responseNeeded = properties.getProperty("responseNeeded");//boolean field to determine if response needed
		matcherValue = properties.getProperty("matcherValue");//value found through matcher
		responseExp = properties.getProperty("responseExp");//response generated using statement + matcherValue
	}

	@Override
	public Message processFilter(Message msg) {
		String userText = msg.getProperty(inputField);
		System.out.println("Filter ["+name+"]. Regexp: ["+regExp+"] to match :["+userText+"]");//test message
		Pattern p = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(userText);
		while(m.find()){
			msg.setProperty(properties.getProperty("reponseNeeded"),"false");
			msg.setProperty(properties.getProperty("matcherValue"), m.group());
			msg.setProperty(outputField,responseExp);
			System.out.println("Found a match: " + m.group());//test message
		}
		return msg;
	}

}

}
