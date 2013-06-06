package dm.dialogue.manager;
import java.io.*;
import java.util.*;
import java.text.*;

import dm.data.DataLocations;
import dm.data.DataSource;
import dm.filter.Filter;
import dm.filter.InformationStateFilter;
import dm.filter.MessageFilter;
import dm.goals.Goal;
import dm.infostate.InformationState;
import dm.infostate.beliefs.ConversationBelief;
import dm.loader.Loader;
import dm.nlp.Message;
import dm.nlp.nlg.NLG;
import dm.nlp.nlu.NLU;
import dm.rules.Rules;
import dm.tasks.ConfirmTask;
import dm.tasks.EndingTask;
import dm.tasks.FindInfoTask;
import dm.tasks.StartingTask;
import dm.tasks.Task;


public class DM
{
	public static final String NLG = "nlg";
	public static final String NLU = "nlu";
	private InformationState informationState;
	private NLU nlu;
	private NLG nlg;
	private ArrayList<Goal> goals;
	private ArrayList<MessageFilter> nluFilters;
	private ArrayList<MessageFilter> nlgFilters;
	private ArrayList<InformationStateFilter> isFilters;
	private Rules rules;
	
	public DM(Loader loader){
		nlgFilters = loader.loadMessageFilters(NLG);
		nluFilters = loader.loadMessageFilters(NLU);
		isFilters = loader.loadISFilters("");
		goals = loader.loadGoals();
		initNlu();
		initNlg();
		initInfoState();
		initGoals();
	}
	
	public boolean isOver(){
		return informationState.getConversationBeliefs().getBeliefString("status").toString().equals("end");
	}
	
	public String takeTurn(Message m){
		Message msg = nlu.parse(m);
		rules.process(msg);
		informationState.update();
		Message response = updateGoals();
		return nlg.generate(response);
	}
	
	/*
	 * The idea is to provide further refinement on f
	 * filters if needed.
	 */
	
	private Message updateGoals() {
		// This method should update and check what goals are still
		// unsatisfied. pick one and generate a Message oblea.
		return null;
	}

	private void initNlg() {
		nlg = new NLG(nlgFilters);
	}

	public void initNlu(){
		nlu = new NLU(nluFilters);
	}
	
	public void initGoals(){
		// Do nothing here.
	}
	
	public void initInfoState(){
		// Set the dialogue to active:
		informationState = new InformationState("1234");
		informationState.getConversationBeliefs().believe(new ConversationBelief("status", "in_progress"));
	}

	public Rules getRules() {
		return rules;
	}

	public void setRules(Rules rules) {
		this.rules = rules;
		rules.setInfoState(informationState);
	}
	
	
}