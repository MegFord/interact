/**
 *
 */
package dm.utils.ml;

import java.util.ArrayList;

/**
 * @author TimD
 *
 */
public class Term {
	
	//total individual term count in document
	protected double termCount;
	//total term count in document
	protected double totalTermCount;
	//number of documents which term was found
	protected double docCount;
	//total number of documents used
	protected static double totalDocCount;
	//frequency of term found in document
	protected double termFrequency;
	//frequency of documents which term was found
	protected double docFrequency;
	//TFIDF = Term Frequency Inverse Document Frequency
	protected double TFIDF;
	//names of documents term was found in
	protected ArrayList<String> docNames = new ArrayList<String>();
	
	public Term() {
		//default
	}
	
	public Term(double ttc, String name) {
		setTermCount(1);
		setTotalTermCount(ttc);
		setDocCount(1);
		setTermFrequency(computeTermFrequency());
		addDocNames(name);
	}
	
	//computes using tf(t,d)/d
	public double computeTermFrequency(){
		return (((getTermCount()/getTotalTermCount())/getDocCount()));
	}
	//unneeded function at the moment
	public double computeDocFrequency(){
		return (getDocCount()/getTotalDocCount());
	}
	//computes using (tf(t,d)/d)*log(|D|/|d|)
	public double computeTFIDF(){
		return (getTermFrequency()*Math.log(Math.abs(getTotalDocCount())/Math.abs(getDocCount())));
	}
	
	public double getTermCount() {
		return termCount;
	}
	
	public void setTermCount(double x) {
		termCount = x;
	}
	
	public void addTermCount() {
		termCount++;
	}
	
	public double getTotalTermCount() {
		return totalTermCount;
	}
	
	public void setTotalTermCount(double x) {
		totalTermCount = x;
	}
	
	public double getDocCount() {
		return docCount;
	}
	
	public void setDocCount(double x) {
		docCount = x;
	}
	
	public static double getTotalDocCount() {
		return totalDocCount;
	}
	
	public static void setTotalDocCount(double x) {
		totalDocCount = x;
	}
	
	public double getTermFrequency() {
		return termFrequency;
	}
	
	public void setTermFrequency(double x) {
		termFrequency = x;
	}
	
	public double getDocFrequency() {
		return docFrequency;
	}
	
	public void setDocFrequency(double x) {
		docFrequency = x;
	}
	
	public double getTFIDF() {
		return TFIDF;
	}
	
	public void setTFIDF(double x) {
		TFIDF = x;
	}
	
	public ArrayList<String> getDocNames() {
		return docNames;
	}
	
	public void addDocNames(String temp) {
		docNames.add(temp);
	}
	
}
