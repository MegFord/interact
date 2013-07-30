/**
 * 
 */
package sys.dm.guru;

import java.util.Properties;

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
		// TODO Auto-generated method stub
		return null;
	}

}
