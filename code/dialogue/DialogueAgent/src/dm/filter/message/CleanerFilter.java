package dm.filter.message;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Scanner;

import dm.filter.MessageFilter;
import dm.nlp.Message;
import dm.utils.Stemmer;

public class CleanerFilter extends MessageFilter {

	String inputField;
	String outputField;
	Boolean removeStopWords;
	static HashSet<String> stopwords;// TODO add this to the infostate somehow
	
	
	public CleanerFilter(String name, Properties properties) {
		super(name, properties);
	}

	@Override
	public void setMandatoryFields() {
		mandatoryFields = new String[3];
		mandatoryFields[0]="inputField";
		mandatoryFields[1]="outputField";
		mandatoryFields[2]="removeStopWords";

	}

	@Override
	public void init() {
		inputField = properties.getProperty("inputField");
		outputField = properties.getProperty("outputField");
		removeStopWords = properties.getProperty("removeStopWords").equalsIgnoreCase("true") ? true: false;
		try 
		{
			stopwords = new HashSet<String>();
			File f = new File("data/stopwords.txt");
			Scanner in = new Scanner(f);
			while (in.hasNext())
			{
				stopwords.add(in.next());
			}
		} catch (FileNotFoundException fnfe){
			System.err.println(fnfe);
		}
	}

	@Override
	public Message processFilter(Message msg) {
		String userText = msg.getProperty(inputField);
		if (removeStopWords)
			userText = removeStopWords(userText);
		String cleanText = Stemmer.stemText(userText);
		msg.setProperty(outputField, cleanText);
		return msg;
	}

	private static String removeStopWords(String str)
	  {
		String result = "";
		str = Stemmer.removePunctuation(str);
		String[] words = str.split(" ");
		for (String word : words)
			if (!stopwords.contains(word))
				result += word;
		return result;		  
	  }
}
