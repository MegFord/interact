package dm.filter;

import java.util.Properties;

import dm.nlp.Message;

public abstract class Filter {

	protected String name;
	protected String[] mandatoryFields; // mandatory fields in the filter config.
	protected Properties properties; // properties of the filter.
	
	public boolean checkMandatoryFields(){
		if (mandatoryFields == null || mandatoryFields.length==0){
			System.out.println("Please provide Mandatory fields when creating the filter in your JAVA code.");
			return false;
		}
		else
			for(String field : mandatoryFields){
				boolean in=false;
				if(!properties.containsKey(field)){
					System.out.println("Field:"+field+": Not provided");
					return false;
				}
			}
		return true;
	}
	
	public String getFilterName(){
		return name;
	}
	
	public Filter(String name, Properties properties){
		this.name = name;
		setMandatoryFields();
		this.properties = properties;
		if (checkMandatoryFields())
			init();
	}
	
	/**
	 * This method should set the array mandatoryFields to the 
	 * mandatory inFields.
	 */
	public abstract void setMandatoryFields();
	
	public abstract void init();
}
