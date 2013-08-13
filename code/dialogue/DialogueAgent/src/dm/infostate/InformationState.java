package dm.infostate;
import dm.infostate.beliefs.AgentBelief;
import dm.infostate.beliefs.Beliefs;
import dm.infostate.beliefs.ConversationBelief;
import dm.infostate.beliefs.UserBelief;
import dm.infostate.grounding.CommonGround;
import dm.infostate.grounding.Fact;
import dm.nlp.Message;


public class InformationState 
{
	public String id;
	private Beliefs<AgentBelief> agentBeliefs;
	private Beliefs<ConversationBelief> conversationBeliefs;
	private Beliefs<UserBelief> userBeliefs;
	private CommonGround commonGround;

	public static final String AGENT_BELIEFS="agentBeliefs";
	public static final String USER_BELIEFS = "userBeliefs";
	public static final String COMMON_GROUND = "commonGround";
	public static final String CONVERSATION_BELIEFS = "conversationBeliefs";
	
	public InformationState(String id){
		this.id = id;
		agentBeliefs = new Beliefs<AgentBelief>();
		conversationBeliefs = new Beliefs<ConversationBelief>();
		userBeliefs = new Beliefs<UserBelief>();
		commonGround = new CommonGround();
	}
	
	public void update(){
		// This should go through the filters.
	}
	
	public CommonGround getCommonGround() {
		return commonGround;
	}
	public Beliefs<AgentBelief> getAgentBeliefs() {
		return agentBeliefs;
	}
	public void setAgentBeliefs(Beliefs<AgentBelief> agentBeliefs) {
		this.agentBeliefs = agentBeliefs;
	}
	public Beliefs<ConversationBelief> getConversationBeliefs() {
		return conversationBeliefs;
	}
	public void setConversationBeliefs(Beliefs<ConversationBelief> conversationBeliefs) {
		this.conversationBeliefs = conversationBeliefs;
	}
	public Beliefs<UserBelief> getUserBeliefs() {
		return userBeliefs;
	}
	public void setUserBeliefs(Beliefs<UserBelief> userBeliefs) {
		this.userBeliefs = userBeliefs;
	}
	public void setCommonGround(CommonGround commonGround) {
		this.commonGround = commonGround;
	}
	
	public void setISField(String key,String value){
		String[] keyFields = key.split(":");
		if (keyFields.length==2){
			if (keyFields[0].equals(AGENT_BELIEFS)){
				this.agentBeliefs.believe(new AgentBelief(keyFields[1], value));
			}
			else if (keyFields[0].equals(CONVERSATION_BELIEFS)){
				this.conversationBeliefs.believe(new ConversationBelief(keyFields[1], value));
			}
			else if (keyFields[0].equals(USER_BELIEFS)){
				this.userBeliefs.believe(new UserBelief(keyFields[1], value));
			}
			else if (key.startsWith(COMMON_GROUND)){
				this.commonGround.ground(new Fact(keyFields[1], value));
			}
			else{
				System.out.println("Key does not contain a valid information state field");
			}
		}
	}
	
	public String getISFieldAsString(String key){
		String[] keyFields = key.split(":");
		String response =null;
		if (keyFields.length==2){
			if (keyFields[0].equals(AGENT_BELIEFS)){
				response = this.agentBeliefs.getBeliefString(keyFields[1]);
			}
			else if (keyFields[0].equals(CONVERSATION_BELIEFS)){
				response = this.conversationBeliefs.getBeliefString(keyFields[1]);
			}
			else if (keyFields[0].equals(USER_BELIEFS)){
				response = this.userBeliefs.getBeliefString(keyFields[1]);
			}
			else if (key.startsWith(COMMON_GROUND)){
				response = this.commonGround.getReference(keyFields[1]);
			}
			else{
				System.out.println("Key does not contain a valid information state field");
			}
		}
		return response;
	}
	
}