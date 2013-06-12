package dm.infostate.beliefs;

public abstract class Belief {
	public String key;
	public Object value;
	
	public Belief(String k, Object v){
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
	
	public boolean isEmpty(){
		return value!=null;
	}
}
