package dm.tasks;

import java.util.Properties;

import dm.filter.InformationStateFilter;
import dm.infostate.InformationState;
import dm.infostate.beliefs.ConversationBelief;
import dm.nlp.nlg.NLG;
import dm.nlp.nlu.NLU;

public abstract class Task{
	protected InformationState is;
	protected boolean complete;
	protected String[] mandatoryFields;
	protected Properties properties;	
	protected String name;
	
	public Task(String name,Properties p){ // This could easily extend Filter and become a TaskFilter.
		this.properties = p;
		this.name = name;
		this.complete = false;
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
		System.out.println("Performing Task:"+this.name);
		complete = process(is);
		return complete;
	}
	
	// UTILITY METHODS
	
	public  boolean isValidISField(String fName){
		String[] field = fName.split(":");
		return field.length==2 &&
			   ( field[0].equals(InformationState.AGENT_BELIEFS) ||
			     field[0].equals(InformationState.COMMON_GROUND) ||
			     field[0].equals(InformationState.CONVERSATION_BELIEFS)||
			     field[0].equals(InformationState.USER_BELIEFS));
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

	public InformationState getIs() {
		return is;
	}

	public void setIs(InformationState is) {
		this.is = is;
	}

	public String[] getMandatoryFields() {
		return mandatoryFields;
	}

	public void setMandatoryFields(String[] mandatoryFields) {
		this.mandatoryFields = mandatoryFields;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	

}