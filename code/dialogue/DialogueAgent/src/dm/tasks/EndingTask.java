package dm.tasks;

import java.util.ArrayList;

import dm.data.DataFlightSchedule;
import dm.infostate.InformationState;

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
	
	private String[] searchFlights(InformationState is)
	{
		ArrayList<String[]> results = new ArrayList<String[]>();
		boolean inRange = false;
		
		for (int i = 0; i < DataFlightSchedule.flightSchedule.length; i++)
		{
			results.add(DataFlightSchedule.flightSchedule[i]);
		}
		
		for (String[] flight : results)
		{
			if (!flight[0].equalsIgnoreCase(is.getAgentBeliefs().getBeliefString("from")))
				results.remove(flight);
		}
		
		for (String[] flight : results)
		{
			if (!flight[1].equalsIgnoreCase(is.getAgentBeliefs().getBeliefString("to")))
				results.remove(flight);
		}
		return null;
	}
}