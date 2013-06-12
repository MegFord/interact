package dm.filter.message;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dm.filter.MessageFilter;
import dm.infostate.InformationState;
import dm.infostate.beliefs.AgentBelief;
import dm.infostate.grounding.CommonGround;
import dm.nlp.Message;

public class FormatMessageFilter extends MessageFilter {


	public static String VAR_LEFT_DELIMITER = "<";
	public static String VAR_RIGHT_DELIMITER = ">";
	public static Pattern vars = Pattern.compile(VAR_LEFT_DELIMITER+ "([a-zA-Z0-9_\\[\\]]+)"+ VAR_RIGHT_DELIMITER);

	private String inField;
	private String outField;
	
	public FormatMessageFilter(String name, Properties properties) {
		super(name, properties);
	}

	public void setMandatoryFields() {
		mandatoryFields = new String[2];
		mandatoryFields[0] = "inField";
		mandatoryFields[1] = "outField";	
	}

	@Override
	public void init() {
		inField = properties.getProperty("inField");
		outField = properties.getProperty("outField");	
	}


	/**
	 * Given a message with placeholders and the dialogue's information state,
	 * the placeholders get replaced with properties of the Information state.
	 * @param message
	 * @param msg
	 * @return
	 */
    public String expandVariables(String message, Message msg) {
        // Extract variables
        String finalMsg = message;
        Matcher m = vars.matcher(finalMsg);
        while (m.find()) {
            String var = m.group(1);
            System.out.println("VAR:" +  var);
            if(var.contains("[")) {
                int pos = Integer.parseInt(var.replaceAll("[a-zA-Z0-9]+\\[([0-9]+)\\]", "$1"));
                var = var.replaceAll("\\[[0-9]+\\]", "");
                String value = msg.getProperty(var);
                String [] values = value.split("~");
                finalMsg.replaceAll(VAR_LEFT_DELIMITER + var + VAR_RIGHT_DELIMITER, values[pos]);
            }
            else {
                finalMsg = finalMsg.replaceAll(VAR_LEFT_DELIMITER + var + VAR_RIGHT_DELIMITER, msg.getProperty(var));
            }
        }
        return finalMsg;
    }

	@Override
	public Message processFilter(Message msg) {
		String patternMsg = msg.getProperty(inField);
		String response = this.expandVariables(patternMsg, msg);
		msg.setProperty(outField, response);
		return msg;
	}
}
