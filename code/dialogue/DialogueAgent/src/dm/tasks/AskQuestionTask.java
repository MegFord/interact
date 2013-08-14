package dm.tasks;

import java.util.ArrayList;
import java.util.Properties;

import dm.infostate.InformationState;
import dm.infostate.grounding.Fact;

public class AskQuestionTask extends Task 
{
	ArrayList<String> isFields; // Info State fields necessary to satisfy this task
	String regexpValidSpaces = InformationState.AGENT_BELIEFS+"|"+
							   InformationState.COMMON_GROUND+"|"+
							   InformationState.CONVERSATION_BELIEFS+"|"+
							   InformationState.CONVERSATION_BELIEFS;
	
	public String question,inFields,outField;
	
	public AskQuestionTask(String name,Properties properties)
	{
		super(name,properties);
	}

	@Override
	public void setMandatoryFields() {
		mandatoryFields = new String[3];
		mandatoryFields[0] = "question"; // The question to ask to find this out
		mandatoryFields[1] = "inFields"; //The fields in the Information State that must be present to consider that the information requested has been found. Format: Comma separated <space:field> fields to use in the response pattern
		mandatoryFields[2] = "outField"; //which field is the question going in (in the IS) space:field
	}


	@Override
	public void init() {
		isFields = new ArrayList<String>();
		question = properties.getProperty("question");
		outField = properties.getProperty("outField");
		inFields = properties.getProperty("inFields");
		String[] inFieldsList = inFields.split(",");
		for(String inField : inFieldsList){
			if(isValidISField(inField))
					this.isFields.add(inField);
				else
					System.out.println("Namespace ["+inField+"] is not a valid namespace or not in the right format");
		}
		if(!isValidISField(outField))
			System.out.println("Namespace ["+outField+"] is not a valid namespace or not in the right format");
		
	}
	
	@Override
	public boolean verifyInformationState(InformationState is) {
		boolean fulfilled = true;
		for(String field:isFields){
			if(is.getISFieldAsString(field)==null){
				fulfilled=false;
				System.out.println("Field:"+field+" is needed to complete the task:"+this.name);
			}
		}	
		return fulfilled;
	}


	@Override
	// Needs to call verifyInformationState (can use it).
	public boolean process(InformationState is) {
		System.out.println("Beginning Task");
		boolean finished = false;
		if(verifyInformationState(is)){
			//System.out.println("Task ["+name+"] is complete.");
			String responseExp = is.getCommonGround().getReference("question");
			System.out.println("Setting ["+outField+"] to ["+responseExp+"]");
			is.setISField(outField, responseExp);
			if(responseExp.isEmpty())
				finished = true;
		} else {
			System.out.println("Task ["+name+"] is NOT complete. Setting ["+outField+"] to ["+question+"]");
			finished = false;
			is.setISField(outField, question);
		}
		System.out.println("Ending Task");		
		return finished;
	}
}
