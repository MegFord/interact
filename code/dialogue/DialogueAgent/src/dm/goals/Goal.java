package dm.goals;

import java.util.Stack;

import dm.infostate.InformationState;
import dm.nlp.Message;
import dm.tasks.Task;

/**
 * A goal represents a "manager" of Tasks that aim at one objective.
 * There should be a method to look at an info state and if a goal cannot
 * be performed, check what is the offending field and which task should
 * take care of it and put it in the stack. In the same way, if the user 
 * changes her mind, there should be mechanisms in place to re-populate the
 * relevant tasks in the stack.
 * @author fdiacobe
 *
 */
public abstract class Goal {
	protected Stack<Goal> subgoals;
	protected Stack<Task> tasks;
	protected InformationState is;
	protected String name;
	protected Task currentTask;
	
	public boolean isAchieved(){
		return tasks.empty() && subgoals.empty();
	};
	
	public Goal(String name){
		subgoals = new Stack<Goal>();
		tasks = new Stack<Task>();
		this.name = name;
	}
	
	public void setInformationState(InformationState is){
		this.is = is;
	}
	
	public void pushTask(Task t){
		tasks.push(t);
	}
	
	public void addSubGoal(Goal g){
		subgoals.push(g);
	}
	
	public Message execute(){
		processTasks();
		processSubgoals();
		return createMessage();
	}
	
	public Stack<Goal> getSubgoals() {
		return subgoals;
	}

	public void setSubgoals(Stack<Goal> subgoals) {
		this.subgoals = subgoals;
	}

	public Stack<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Stack<Task> tasks) {
		this.tasks = tasks;
	}

	public InformationState getIs() {
		return is;
	}

	public void setIs(InformationState is) {
		this.is = is;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Task getCurrentTask() {
		return currentTask;
	}

	public void setCurrentTask(Task currentTask) {
		this.currentTask = currentTask;
	}

	public void processTasks(){
		System.out.println("Goal:["+name+"]: Processing Tasks");
		removeCompleteTasks();
		if(!tasks.isEmpty()){
			Task t = tasks.peek();
			currentTask = t;
			t.perform(is);
		} else {
			System.out.println("Goal:["+name+"]: No more tasks");
		}
		
		
	}
	
	private void removeCompleteTasks(){
		Task t = tasks.isEmpty()?null:tasks.peek();
		while(!tasks.isEmpty() && t.isComplete()){
			tasks.pop();
			if (!tasks.isEmpty())
				t = tasks.peek();
		}
	}
	
	public void processSubgoals(){
		if(!subgoals.isEmpty()){
			removeCompleteSubgoals();
			Goal g = subgoals.peek();
			//g.execute();
		}
		
	}
	
	private void removeCompleteSubgoals(){
		Goal g = subgoals.peek();
		while(g.isAchieved()){
			subgoals.pop();
			g = subgoals.peek();
		}
	}
	
	public void endDialogue(){
		this.is.setISField(InformationState.AGENT_BELIEFS+":status", "end");
	}

	/**
	 * Returns the Goal's confidence in that the
	 * this goal is the current goal in the dialogue.
	 * This method should look into the info state to
	 * determine its confidence.
	 * @return
	 */
	public abstract double getConfidence();
	
	/**
	 * Once a goal is executed, this method
	 * looks at the information state and creates
	 * a message, that within this goal, makes the
	 * best next utterance. You must override it.
	 * @return
	 */
	public abstract Message createMessage();
}
