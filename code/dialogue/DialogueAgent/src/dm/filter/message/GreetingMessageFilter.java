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
		mandatoryFields = new String[2];
		mandatoryFields[0] = "input";
		mandatoryFields[1] = "output";
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
		String text = msg.getProperty(input);
		
		boolean greet =text.matches(".*([hH]i|[hH]ello|[Hh]ow|[Hh]ey|terrib|well|good|fine|doin).*");
		if(greet)
			msg.setProperty(outField, "true");
		
		return msg;
	}

}
