package dm.dialogue.manager;
import java.io.*;
import java.util.*;
import java.text.*;

import dm.data.DataLocations;
import dm.data.DataSource;
import dm.infostate.InfoState;
import dm.nlp.nlg.NLG;
import dm.tasks.ConfirmTask;
import dm.tasks.EndingTask;
import dm.tasks.QuestionTask;
import dm.tasks.StartingTask;
import dm.tasks.Task;


public class DM
{
	public static void main(String[] args) throws ParseException
	{
		/*DateFormat formatter1;
		formatter1 = new SimpleDateFormat("MMMM dddd yyyy");
		System.out.println((Date)formatter1.parse("December 16th 2013"));*/
		createStack(DataSource.questionsFilename);
		
		while(!InfoState.mainStack.isEmpty())
			checkState();
	}

	public static void checkState()
	{
		boolean isValid;
		boolean wasAnswered;
		Task currentTask = InfoState.mainStack.pop();
		//System.out.println(currentTask.getHashtag()+": " + InfoState.beliefs.get(currentTask.getHashtag()));
		wasAnswered = !(InfoState.beliefs.get(currentTask.getHashtag()).isEmpty());
		if (!wasAnswered)
		{
			isValid = currentTask.perform();
			if (!isValid) 
			{
				NLG.output("I'm sorry, but it doesn't look like you answered my question.");
				InfoState.mainStack.add(currentTask);
			}
		}

	}
	
	public static void createStack(String filename)
	{
		try
		{
			InfoState.mainStack = new Stack<Task>();
			File file = new File(filename);
			Scanner in = new Scanner(file);
			DataLocations.setLocations(in.nextLine());
			while (in.hasNext()) 
			{
				String line = in.nextLine();
				String[] splitLine = line.split(",");
				String tag = splitLine[0];
				String question = splitLine[1];
				
				InfoState.questions.put(tag, question);
				InfoState.beliefs.put(tag,"");

				if (tag.equals("greet"))
				{
					if(!InfoState.repeated)
						InfoState.mainStack.add(new StartingTask(tag));
				}
				else if (tag.equals("confirm"))
					InfoState.mainStack.add(new ConfirmTask(tag));
				else if (tag.equals("end"))
					InfoState.mainStack.add(new EndingTask(tag));
				else
					InfoState.mainStack.add(new QuestionTask(tag));

			}
			
		} catch (FileNotFoundException fnfe) {
			System.out.println("File not found.");
		}

	}
}