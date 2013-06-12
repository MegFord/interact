package dm.filter;

import java.util.Properties;

import dm.infostate.InformationState;
import dm.nlp.Message;

public abstract class InformationStateFilter extends Filter{

	public InformationStateFilter(String name, Properties properties) {
		super(name, properties);
		// TODO Auto-generated constructor stub
	}

	@Override
	public abstract void setMandatoryFields();
	
	@Override
	public abstract void init() ;

	public abstract InformationState processFilter(InformationState msg);


}
