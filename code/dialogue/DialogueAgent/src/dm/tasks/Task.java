package dm.tasks;

import java.util.Properties;

import dm.filter.InformationStateFilter;
import dm.infostate.InformationState;
import dm.nlp.nlg.NLG;
import dm.nlp.nlu.NLU;

public abstract class Task{
	protected InformationState is;
	protected boolean complete;
	protected String[] mandatoryFields;
	protected Properties properties;	
	public static final String AGENT_BELIEFS="agentBeliefs";
	public static final String USER_BELIEFS = "userBeliefs";
	public static final String COMMON_GROUND = "commonGround";
	public static final String CONVERSATION_BELIEFS = "conversationBeliefs";
	
	
	public Task(Properties p){ // This could easily extend Filter and become a TaskFilter.
		this.properties = p;
		setMandatoryFields();
		if (checkMandatoryFields())
			init();
	}
	
	public boolean checkMandatoryFields(){
		if (mandatoryFields == null || mandatoryFields.length==0){
			System.out.println("Please provide Mandatory fields when creating the filter in your JAVA code.");
			return false;
		}
		else
			for(String field : mandatoryFields){
				boolean in=false;
				if(!properties.containsKey(field)){
					System.out.println("Field:"+field+": Not provided");
					return false;
				}
			}
		return true;
	}
	/**
	 * Fills in the mandatoryFields string array with
	 * the fields that are mandatory for this Task.
	 */
	public abstract void setMandatoryFields();
	
	public abstract void init() ;

	public boolean isComplete(){
		return complete;
	}
	
	public boolean perform(InformationState is){
		boolean response = verifyInformationState(is);
		if (response)
			response = process(is);
		complete = response; // this could change to something more sophisticated later;
		return response;
	}
	
	/**
	 * Verifies that the task can be completed with the information in
	 * the InformationState
	 * @param is the information state containing the current IS of the dialogue
	 * @return true if the IS contains sufficient information to complete the task
	 */
	public abstract boolean verifyInformationState(InformationState is);
	
	/**
	 * Assuming that the information state has everything needed to be able
	 * to perform this task, this method performs the task
	 * @param is the information state.
	 * @return true if the processing was successful.
	 */
	public abstract boolean process(InformationState is);
	
}