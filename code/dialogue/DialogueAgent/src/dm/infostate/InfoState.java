package dm.infostate;
import java.util.Stack;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import dm.tasks.Task;


public class InfoState 
{
	//error, no support documentation found
	public static Stack<Task> mainStack = new Stack<Task>();
	public static HashMap<String, String> questions = new HashMap<String,String>();
	public static HashMap<String, String> beliefs = new HashMap<String,String>();  
	public static boolean repeated = false;
	
	
	
	/*public InfoState(String filename) 
	{
		boolean startOfFile = true;
		try
		{
			File file = new File(filename);
			Scanner in = new Scanner(file);
			while (in.hasNext()) 
			{
				String line = in.nextLine();
				String[] splitLine = line.split(",");
				String tag = splitLine[0];
				String question = splitLine[1];
				questions.put(tag, question);
				beliefs.put(tag,"");
				if (startOfFile)
				{
					mainStack.add(new EndingTask(tag, question));
					startOfFile = false;
				}
				else if(!in.hasNext())
					mainStack.add(new StartingTask(tag, question));
				else				
					mainStack.add(new Task(tag, question));
			}
			
		} catch (FileNotFoundException fnfe) {
			System.out.println("File not found.");
		}
	}*/
	
	/*public static void setProgramDone(Boolean b)
	{
		programDone = b;
	}*/
	
	/*public static boolean isProgramDone()
	{
		return programDone;
	}*/
}