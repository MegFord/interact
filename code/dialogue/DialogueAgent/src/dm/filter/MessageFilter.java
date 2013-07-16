package dm.filter;

import java.util.Properties;

import dm.nlp.Message;

public abstract class MessageFilter extends Filter {

	public MessageFilter(String name, Properties properties) {
		super(name, properties);
		// TODO Auto-generated constructor stub
	}

	@Override
	public abstract void setMandatoryFields();
	
	@Override
	public abstract void init();

	public abstract Message processFilter(Message msg);

}
