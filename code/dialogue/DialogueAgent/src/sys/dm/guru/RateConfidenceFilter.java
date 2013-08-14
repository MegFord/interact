/**
 * 
 */
package sys.dm.guru;

import java.util.Properties;

import dm.filter.MessageFilter;
import dm.nlp.Message;
import dm.utils.ml.Classifier;

/**
 * @author TimD
 *
 */
public class RateConfidenceFilter extends MessageFilter{
	
	String inputField;
	String outputField;

	public RateConfidenceFilter(String name, Properties properties) {
		super(name, properties);
	}

	@Override
	public void setMandatoryFields() {
		mandatoryFields = new String[2];
		mandatoryFields[0]="inputField";
		mandatoryFields[1]="outputField";
	}

	@Override
	public void init() {
		inputField = properties.getProperty("inputField");
		outputField = properties.getProperty("outputField");
	}

	@Override
	public Message processFilter(Message msg) {
		String userText = msg.getProperty(inputField);
		Classifier cls = new Classifier();
		msg.setProperty(outputField, ""+cls.classify(userText));
		return msg;
	}

}
