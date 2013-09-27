/**
 * 
 */
package dm.utils.ml;

import java.util.ArrayList;

import dm.utils.Stemmer;

/**
 * @author TimD
 *
 */
public class StoredUtterance {

	ArrayList<Utterance> utteranceList = new ArrayList<Utterance>();
	
	Stemmer tempStemmer = new Stemmer();
	
	public StoredUtterance() {
		//default
	}
	
	public StoredUtterance(ArrayList<Utterance> tempList){
		setUtteranceList(tempList);
	}
	
	@SuppressWarnings("static-access")
	public String[] stringToArray(String tempString){
		tempString = tempStemmer.stemText(tempString);
		String[] tempStringArray = tempString.split(" +");
		return tempStringArray;
	}
	
	public void addUtterance(int x, String tempString){
		utteranceList.add(new Utterance(x, tempString));
	}

	public void add(Utterance tempUtterance){
		utteranceList.add(tempUtterance);
	}
	
	public void setUtteranceList(ArrayList<Utterance> tempList){
		utteranceList = tempList;
	}
	
	public ArrayList<Utterance> getUtteranceList(){
		return utteranceList;
	}
	
	public Utterance get(int x){
		return utteranceList.get(x);
	}
	
	public void setUtterance(int x, String tempString){
		utteranceList.get(x).setUtterance(tempString);
	}
	
	public String getUtterance(int x){
		return utteranceList.get(x).getUtterance();
	}

	public void setUtteranceTerms(int x, String[] tempStringArray){
		utteranceList.get(x).setUtteranceTerms(tempStringArray);
	}
	
	public String[] getUtteranceTerms(int x){
		return utteranceList.get(x).getUtteranceTerms();
	}
	
	public void setDistanceScore(int x, double y){
		utteranceList.get(x).setDistanceScore(y);
	}
	
	public double getDistanceScore(int x){
		return utteranceList.get(x).getDistanceScore();
	}
	
	public void setInterestValue(int x, int y){
		utteranceList.get(x).setInterestValue(y);
	}
	
	public int getInterestValue(int x){
		return utteranceList.get(x).getInterestValue();
	}
}
