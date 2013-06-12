package dm.nlp;

import java.util.Properties;

public class Message {
	public static final String TEXT = "text";
	private Properties properties;
	
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public Message(String text){
		properties = new Properties();
		properties.setProperty(TEXT, text);
	}
	
	public String getMessageText(){ return properties.getProperty(TEXT); }
	
	public void setProperty(String key, String val){
		properties.setProperty(key, val);
	}
	
	public String getProperty(String key){
		return properties.getProperty(key);
	}
	
	public String getProperty(String key, String defaultValue){
		return properties.getProperty(key, defaultValue);
	}
	
	public Integer getIntProperty(String key){
		return new Integer(properties.getProperty(key));
	}
	
	public String toString(){
		return properties.toString();
	}
}
