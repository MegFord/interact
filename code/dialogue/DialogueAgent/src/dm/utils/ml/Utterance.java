/**
 * 
 */
package dm.utils.ml;

/**
 * @author TimD
 *
 */
public class Utterance {
	
	protected String utterance;
	
	protected String[] utteranceTerms;
	
	protected double distanceScore;
	
	protected int interestValue;
	
	public Utterance(){
		//default
	}
	
	public Utterance(int x, String tempString){
		setUtterance(tempString);
		setInterestValue(x);
	}
	
	public Utterance(int x, String[] tempStringArray){
		setUtteranceTerms(tempStringArray);
		setInterestValue(x);
	}
	
	public void setUtterance(String tempString){
		utterance = tempString;
	}
	
	public String getUtterance(){
		return utterance;
	}

	public void setUtteranceTerms(String[] tempStringArray){
		utteranceTerms = tempStringArray;
	}
	
	public String[] getUtteranceTerms(){
		return utteranceTerms;
	}
	
	public void setDistanceScore(double x){
		distanceScore = x;
	}
	
	public double getDistanceScore(){
		return distanceScore;
	}
	
	public void setInterestValue(int x){
		interestValue = x;
	}
	
	public int getInterestValue(){
		return interestValue;
	}
}
