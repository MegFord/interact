package sys.dm;

import java.util.ArrayList;
import java.util.Scanner;

import dm.dialogue.manager.DM;
import dm.filter.InformationStateFilter;
import dm.filter.MessageFilter;
import dm.goals.Goal;
import dm.loader.Loader;
import dm.nlp.Message;

public class Agent {

	protected DM dialogue;
	protected String initialMessage;
	
	public Agent() {
		initSystem();
	}
	
	public void initSystem(){
		initialMessage = "Hi, how are you?";
		dialogue = new DM(new DummyLoader(""));
		dialogue.setRules(new DummyRules());
	}
	
	public void execute(){
		this.initSystem();
		Scanner kbd = new Scanner(System.in);
		String text = dialogue.takeTurn(null);
		System.out.println(text);
		while(!dialogue.isOver()){
			text = kbd.nextLine();
			Message msg = new Message(text);
			String out = dialogue.takeTurn(msg);
			System.out.println(out);
		}
	}
	
	public static void main(String[] args){
		Agent a = new Agent();
		a.execute();

	}

}
