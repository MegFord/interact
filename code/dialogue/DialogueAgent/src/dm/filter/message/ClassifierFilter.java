package dm.filter.message;

import java.util.Properties;

import dm.filter.MessageFilter;
import dm.nlp.Message;
import dm.utils.ml.Classifier;

public class ClassifierFilter extends MessageFilter {

	String inputField;
	String outputField;
	
	public ClassifierFilter(String name, Properties properties) {
		super(name, properties);
		// TODO Auto-generated constructor stub
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
		Classifier clazz = new Classifier();
		String category = ""+clazz.classify(userText);
		msg.setProperty(outputField, category);
		return msg;
	}

}
