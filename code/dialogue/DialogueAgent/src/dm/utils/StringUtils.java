package dm.utils;

import dm.filter.message.CleanerFilter;

public class StringUtils {
	public static String stemWord(String str)
	  {
		  Stemmer s = new Stemmer();
		  s.add(str.toLowerCase().toCharArray(), str.length());
		  s.stem();
		  return s.toString();
	  }
	  
	  public static String removePunctuation(String str)
	  {
		  str = str.replaceAll("[^a-zA-Z\\s]", "");
		  return str;
	  }
	  
	  public static String stemText(String text)
	  {
		  String result = "";
		  text = text.toLowerCase();
		  String str = removePunctuation(text);
		  String[] words = str.split(" ");
		  for (String w : words)
			  result += stemWord(w) + " ";
		  return result.substring(0, result.length()-1); //get rid of the last space
	  }
	  
	  public static String removeStopWords(String str)
	  {
		String result = "";
		str = StringUtils.removePunctuation(str);
		String[] words = str.split(" ");
		for (String word : words)
			if (!CleanerFilter.stopwords.contains(word.toLowerCase()))
				result += word + " ";
		return result;		  
	  }
	  
	  public static String cleanText(String str) {
		  String result = removeStopWords(str);
		  result = stemText(result);
		  return result;
	  }
}
