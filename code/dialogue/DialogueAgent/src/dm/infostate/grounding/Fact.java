package dm.infostate.grounding;

public class Fact {
	private String key;
	private Object value;
	
	public Fact(String k, Object v){
		this.key = k;
		this.value = v;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
}
