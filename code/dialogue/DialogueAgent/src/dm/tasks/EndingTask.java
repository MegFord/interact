package dm.tasks;

import java.util.ArrayList;
import java.util.Properties;

import dm.data.DataFlightSchedule;
import dm.infostate.InformationState;

public class EndingTask extends Task
{

	
	public EndingTask(String name, Properties p) {
		super(name, p);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setMandatoryFields() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean verifyInformationState(InformationState is) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean process(InformationState is) {
		// TODO Auto-generated method stub
		return false;
	}
}