package dm.tasks;

import java.util.ArrayList;
import java.util.Properties;

import dm.infostate.InformationState;

public class FindInfoTask extends Task
{
	ArrayList<String> userBeliefsFields;
	ArrayList<String> conversationBeliefFields;
	ArrayList<String> commonGroundFields;
	ArrayList<String> agentBeliefFields;
	String regexpValidSpaces = Task.AGENT_BELIEFS+"|"+Task.COMMON_GROUND+"|"+Task.CONVERSATION_BELIEFS+"|"+Task.CONVERSATION_BELIEFS;
	
	public FindInfoTask(Properties properties)
	{
		super(properties);
	}

	@Override
	public void setMandatoryFields() {
		mandatoryFields = new String[3];
		mandatoryFields[0] = "question";
		mandatoryFields[1] = "inFields"; //comma separated space:field
		mandatoryFields[2] = "outField"; //which field is the question going space:field
	}


	@Override
	public void init() {
		String[] inFields = properties.getProperty("inFields").split(",");
		for(String inField : inFields){
			String[] field = inField.split(":");
			if(field.length!=2){
				System.out.println(field[0]+": Not in the correct format. <namespace>:<fieldname>");
			}
			else{
				if(field[0].equals(Task.AGENT_BELIEFS))
					this.agentBeliefFields.add(field[1]);
				else if (field[0].equals(Task.COMMON_GROUND))
					this.commonGroundFields.add(field[0]);
				else if (field[0].equals(Task.CONVERSATION_BELIEFS))
					this.conversationBeliefFields.add(field[0]);
				else if (field[0].equals(Task.USER_BELIEFS))
					this.userBeliefsFields.add(field[1]);
				else
					System.out.println("Namespace: "+field[0]+" is not a valid namespace");
			}
		}
		
	}


	@Override
	public boolean verifyInformationState(InformationState is) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean process(InformationState is) {
		// TODO Auto-generated method stub
		return false;
	}
}