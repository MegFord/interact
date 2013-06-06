package dm.infostate;
import dm.infostate.beliefs.AgentBelief;
import dm.infostate.beliefs.Beliefs;
import dm.infostate.beliefs.ConversationBelief;
import dm.infostate.beliefs.UserBelief;
import dm.infostate.grounding.CommonGround;
import dm.nlp.Message;


public class InformationState 
{
	public String id;
	private Beliefs<AgentBelief> agentBeliefs;
	private Beliefs<ConversationBelief> conversationBeliefs;
	private Beliefs<UserBelief> userBeliefs;
	private CommonGround commonGround;
	/*public static Stack<Task> mainStack = new Stack<Task>();
	public static HashMap<String, String> questions = new HashMap<String,String>();
	public static HashMap<String, String> beliefs = new HashMap<String,String>();  
	public static boolean repeated = false;
	*/
	
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
	public void setConversationBeliefs(
			Beliefs<ConversationBelief> conversationBeliefs) {
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
	
}