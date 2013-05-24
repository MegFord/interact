package dm.tasks;

import java.util.ArrayList;

import dm.data.DataFlightSchedule;
import dm.infostate.InfoState;

public class EndingTask extends Task
{
	public EndingTask(String hashtag)
	{
		super(hashtag);
	}
	
	public boolean perform()
	{
		return true;
	}
	
	private String[] searchFlights()
	{
		ArrayList<String[]> results = new ArrayList<String[]>();
		boolean inRange = false;
		
		for (int i = 0; i < DataFlightSchedule.flightSchedule.length; i++)
		{
			results.add(DataFlightSchedule.flightSchedule[i]);
		}
		
		for (String[] flight : results)
		{
			if (!flight[0].equalsIgnoreCase(InfoState.beliefs.get("from")))
				results.remove(flight);
		}
		
		for (String[] flight : results)
		{
			if (!flight[1].equalsIgnoreCase(InfoState.beliefs.get("to")))
				results.remove(flight);
		}
		return null;
	}
}