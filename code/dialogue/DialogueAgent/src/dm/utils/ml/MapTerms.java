package dm.utils.ml;

import java.util.HashMap;

import dm.utils.Stemmer;

/**
 * @author TimD
 *
 */
public class MapTerms {

	private HashMap<String, Term> vocabulary = new HashMap<String, Term>();

	private Stemmer tempStemmer = new Stemmer();
	
	public MapTerms() {
		//default
	}
	
	public MapTerms(String tempString) {
		Term.setTotalDocCount(1);
		setVocabulary(createMapTerms(tempString));
		completeMapTerms();
	}
	
	//creates a new HashMap of Terms or adds to existing HashMap of Terms
	public void addMapTerms(String tempString) {
		if(getVocabulary().isEmpty()) {
			Term.setTotalDocCount(1);
			setVocabulary(createMapTerms(tempString));
		}
		else{
			Term.setTotalDocCount(Term.getTotalDocCount()+1);
			HashMap<String, Term> tempVocabulary = createMapTerms(tempString);
			for(String key: tempVocabulary.keySet()) {
				if(getVocabulary().containsKey(key)) {
					getVocabulary().get(key).setTermCount(getVocabulary().get(key).getTermCount()+tempVocabulary.get(key).getTermCount());
					getVocabulary().get(key).setTotalTermCount(getVocabulary().get(key).getTotalTermCount()+tempVocabulary.get(key).getTotalTermCount());
					getVocabulary().get(key).setDocCount(getVocabulary().get(key).getDocCount()+tempVocabulary.get(key).getDocCount());
				}
				else {
					getVocabulary().put(key, tempVocabulary.get(key));
				}
			}
		}
		completeMapTerms();
	}
	
	//builds the HashMap of terms
	private HashMap<String, Term> createMapTerms(String tempString) {
		Term term;
		String[] tempStringArray = parseString(tempString);
		HashMap<String, Term> tempVocabulary = new HashMap<String, Term>();
		for(String key: tempStringArray) {
			if(tempVocabulary.containsKey(key)) {
				term = tempVocabulary.get(key);
				term.addTermCount();
				//term.setTermFrequency(term.computeTermFrequency());
				tempVocabulary.put(key, term);
			}
			else {
				term = new Term(tempStringArray.length, null);
				tempVocabulary.put(key, term);
			}
		}
		return tempVocabulary;
	}
	
	//completes the HashMap of Terms by computing the Term Frequency and TFIDF of each term
	private void completeMapTerms(){
		for(String key: getVocabulary().keySet()){
			getVocabulary().get(key).setTermFrequency(getVocabulary().get(key).computeTermFrequency());
			getVocabulary().get(key).setTFIDF(getVocabulary().get(key).computeTFIDF());
		}
	}
	
	//parses a string into a string array
	@SuppressWarnings("static-access")
	private String[] parseString(String tempString){
		//tempString = tempString.replaceAll("[\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\[\\]\\{\\}\\;\\:\\'\"\\,\\<\\.\\>\\?]"," ").toLowerCase();
		tempString = tempStemmer.stemText(tempString);
		String[] tempStringArray = tempString.split(" +");
		return tempStringArray;
	}
	
	public HashMap<String, Term> getVocabulary() {
		return vocabulary;
	}
	
	private void setVocabulary(HashMap<String, Term> tempHashMap) {
		vocabulary = tempHashMap;
	}
	
	public double getTermCount(String key) {
		return getVocabulary().get(key).getTermCount();
	}
	
	public double getTotalTermCount(String key) {
		return getVocabulary().get(key).getTotalTermCount();
	}
	
	public double getDocCount(String key) {
		return getVocabulary().get(key).getDocCount();
	}
	
	public double getTermFrequency(String key) {
		return getVocabulary().get(key).getTermFrequency();
	}
	
	public double getDocFrequency(String key) {
		return getVocabulary().get(key).getDocFrequency();
	}
	
	public double getTFIDF(String key) {
		if(getVocabulary().containsKey(key))
			return getVocabulary().get(key).getTFIDF();
		else
			return 0;
	}
}