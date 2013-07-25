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
	
	public boolean isResponseNeeded(){
		return informationState.getConversationBeliefs().getBeliefString("response_needed").toString().equals("true");
	}
	
	public String takeTurn(Message m){
		if(m!=null){
			Message msg = nlu.parse(m);
			System.out.println(msg);
			rules.process(msg);
		}
		informationState.update(); // in case the IS has update rules
		Message response = checkGoals();
		if(response!=null)
			return nlg.generate(response);
		else
			return null;
	}
	
	/*
	 * The idea is to provide further refinement on f
	 * filters if needed.
	 */
	
	/**
	 * This method will look at the set of goals and determine how to best proceed.
	 * @return
	 */
	private Message checkGoals() {//should take an argument of goal stack
		Goal nextGoal=goals.get(0);
		double confidence=-0.1;
		for(Goal g:goals){
			if(g.getConfidence()>confidence && !g.isAchieved()){
				nextGoal = g;
				confidence = g.getConfidence();
			}
		}
		return nextGoal.execute();
		
	}

	private void initNlg() {
		nlg = new NLG(nlgFilters);
	}

	public void initNlu(){
		nlu = new NLU(nluFilters);
	}
	
	public void initGoals(){
		for(Goal g:goals){
			g.setInformationState(this.informationState);
		}
	}
	
	public void initInfoState(){
		// Set the dialogue to active:
		informationState = new InformationState("1234");
		informationState.getConversationBeliefs().believe(new ConversationBelief("status", "in_progress"));
	}

	public Rules getRules() {
		return rules;
	}

	/**
	 * Rules are a set of directives to perform when the NLU component hands
	 * over a message. Rules have to be extended and it can be assumed that they
	 * have the correct information state associated to them.
	 * @param rules
	 */
	public void setRules(Rules rules) {
		this.rules = rules;
		rules.setInfoState(informationState);
	}
	
	
}