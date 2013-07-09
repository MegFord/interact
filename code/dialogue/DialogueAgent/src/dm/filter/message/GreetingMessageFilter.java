/**
 * 
 */
package dm.filter.message;

import java.util.Properties;

import dm.filter.MessageFilter;
import dm.nlp.Message;

/**
 * @author iacobelli
 *
 */
public class GreetingMessageFilter extends MessageFilter {

	/**
	 * @param name
	 * @param properties
	 */
	public GreetingMessageFilter(String name, Properties properties) {
		super(name, properties);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see dm.filter.MessageFilter#setMandatoryFields()
	 */
	@Override
	public void setMandatoryFields() {
		mandatoryFields = new String[4];
		mandatoryFields[0] = "input";
		mandatoryFields[1] = "output";
		mandatoryFields[2] = "mood";
		mandatoryFields[3] = "value";
	}

	/* (non-Javadoc)
	 * @see dm.filter.MessageFilter#init()
	 */
	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see dm.filter.MessageFilter#processFilter(dm.nlp.Message)
	 */
	@Override
	public Message processFilter(Message msg) {
		/* check whether the input field in the message contains
		   a greeting. If it does, then set the output to "true"
		 */
		String input = properties.getProperty("input");
		String outField = properties.getProperty("output");
		String mood = properties.getProperty("mood");
		String text = msg.getProperty(input);
		String value = properties.getProperty("value");
		
		String goodMoodMatcher = "(good|well|fine|great)";
		boolean greet =text.matches(".*([hH]i|[hH]ello|[Hh]ow|[Hh]ey|terrib|well|good|fine|doin).*");
		boolean badMood = text.matches(".*(?i)(?<!(not|not so)\\s)(bad|terribl|horribl|sad|not.+"+goodMoodMatcher+").*");
		if(greet)
			msg.setProperty(outField, "true");
		if (badMood) {
			msg.setProperty(mood, "bad");
			msg.setProperty(value, "Sorry to hear that");
		} else {
			msg.setProperty(mood, "good");
			msg.setProperty(value, "That\'s good to hear");
		}
		return msg;
	}

}
