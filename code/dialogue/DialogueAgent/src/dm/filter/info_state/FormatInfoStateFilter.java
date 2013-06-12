package dm.filter.info_state;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dm.filter.Filter;
import dm.filter.InformationStateFilter;
import dm.infostate.InformationState;
import dm.infostate.beliefs.AgentBelief;
import dm.infostate.grounding.CommonGround;
import dm.nlp.Message;

public class FormatInfoStateFilter extends InformationStateFilter{

	public static String VAR_LEFT_DELIMITER = "<";
	public static String VAR_RIGHT_DELIMITER = ">";
	public static Pattern vars = Pattern.compile(VAR_LEFT_DELIMITER+ "([a-zA-Z0-9_\\[\\]]+)"+ VAR_RIGHT_DELIMITER);

	private String inField;
	private String outField;
	
	public FormatInfoStateFilter(String name, Properties properties) {
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


	
	@Override
	public InformationState processFilter(InformationState is) {
		String message = is.getAgentBeliefs().getBeliefString(inField);
		String response = this.expandVariables(message, is);
		is.getAgentBeliefs().believe(new AgentBelief(outField, response));
		return is;
	}

	/**
	 * Given a message with placeholders and the dialogue's information state,
	 * the placeholders get replaced with properties of the Information state.
	 * @param message
	 * @param msg
	 * @return
	 */
    public String expandVariables(String message, InformationState is) {
        // Extract variables
        String finalMsg = message;
        CommonGround cg = is.getCommonGround();
        Matcher m = vars.matcher(finalMsg);
        while (m.find()) {
            String var = m.group(1);
            System.out.println("VAR:" +  var);
            if(var.contains("[")) {
                int pos = Integer.parseInt(var.replaceAll("[a-zA-Z0-9]+\\[([0-9]+)\\]", "$1"));
                var = var.replaceAll("\\[[0-9]+\\]", "");
                String value = cg.getReference(var);
                String [] values = value.split("~");
                finalMsg.replaceAll(VAR_LEFT_DELIMITER + var + VAR_RIGHT_DELIMITER, values[pos]);
            }
            else {
                finalMsg = finalMsg.replaceAll(VAR_LEFT_DELIMITER + var + VAR_RIGHT_DELIMITER, cg.getReference(var));
            }
        }
        return finalMsg;
    }
}
