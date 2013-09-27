/**
 * 
 */
package dm.utils.ml;

import java.util.ArrayList;

/**
 * @author TimD
 *
 */
public class SentimentAnalysis{
	
	Similarity tempSim;
	
	StoredUtterance tempSU;
	
	public SentimentAnalysis(){
		//default
	}

	public SentimentAnalysis(MapTerms tempMap, ArrayList<Utterance> tempList){
		tempSim = new Similarity(tempMap);
		tempSU = new StoredUtterance(tempList);
	}

	public int calculateSentAnalysis(int x, String userUtterance){
		for(Utterance tempString: tempSU.getUtteranceList()){
			tempString.setDistanceScore((tempSim.getSimilarity(userUtterance, tempString.getUtterance())));
		}
		sortArrayList(tempSU.getUtteranceList());
		return scoreTwoStrats(calculateKNN(x, tempSU.getUtteranceList()));
	}
	
	public void sortArrayList(ArrayList<Utterance> tempList){
		for(int i=0; i<tempList.size(); i++){
			for(int j=0; j<tempList.size()-1; j++){
				if(tempList.get(j).getDistanceScore() < tempList.get(j+1).getDistanceScore()){
					Utterance temp = tempList.get(j);
					tempList.set(j, tempList.get(j+1));
					tempList.set(j+1, temp);
				}
			}
		}
	}
	
	public int[] calculateKNN(int x, ArrayList<Utterance> tempList){
		int[] temp = new int[x];
		for(int i=0; i<x; i++){
			temp[i]=tempList.get(i).getInterestValue();
		}
		return temp;
	}
	
	public int scoreTwoStrats(int[] x){
		int interest = 0;
		int nointerest = 0;
		for(int i=0; i<x.length; i++){
			if(x[i] == 1)
				interest++;
			else
				nointerest++;
		}
		if(interest > nointerest)
			return 1;
		else
			return 0;
	}

}
