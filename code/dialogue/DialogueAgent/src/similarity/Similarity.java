
package similarity;
//in sum, the Similarity class should have a private TfIdf wordVals variable 
//with getters and setters and it should have a public double similarity(String 
//text1, String text2) method.
public class Similarity {
	private double /*TfIdf*/ wordVals;
	public Similarity() {
		// TODO Auto-generated constructor stub
	}

	public double /*TfIdf*/ getWordVals(String word)
	{
		return 0.0;
	}	
	
	public void /*TfIdf*/ setWordVals(double value)
	{
	}
	
	
	
	public double getSimilarity(String text1, String text2)
	{
		String[] uniqueWords = getUniqueWords(text1, text2);
		
		double[] vect1 = new double[uniqueWords.length];
		double[] vect2 = new double[uniqueWords.length];
		
		//initializing vectors
		for(int x = 0; x < uniqueWords.length; x++)
		{
			if(text1.contains(uniqueWords[x]))
			{
				vect1[x] = getWordVals(uniqueWords[x]);
			}
			if(text2.contains(uniqueWords[x]))
			{
				vect2[x] = getWordVals(uniqueWords[x]);
			}
		}
		return cosineSimilarity(vect1, vect2);
	}
	
	
	private String[] getUniqueWords(String text1, String text2)
	{
		String[] doc1 = text1.split(" ");
		String[] doc2 = text2.split(" ");
		String[] doc3 = new String[(doc2.length + doc1.length)];
		int count = 0;//count how many duplicated words there are in the doc1+doc2 string[]
		String[] doc4;
		
		//add all words to doc3		
		for(int x = 0; x < doc1.length; x++)
		{
			doc3[x] = doc1[x];
		}
		for(int x = 0; x < doc2.length; x++)
		{
			doc3[x+doc1.length] = doc2[x];
		}
		
		//make all repeated words into blanks and count them
		for(int x = 0; x < doc3.length-1; x++)
		{
			String temp = doc3[x];
			if(!temp.equals(" ")){
			for(int y = x + 1; y < doc3.length; y++)
			{
				if(temp.equals(doc3[y]))
				{
					doc3[y] = " ";
					count++;
				}
			}}
		}
		
		//set length of uniqueWords string[] and add the words
		doc4 = new String[doc3.length-count];
		int doc4Trace = 0;
		for(int x = 0; x < doc3.length; x++)
		{
			if(!doc3[x].equals(" "))
			{
				doc4[doc4Trace] = doc3[x];
				doc4Trace++;
			}
		}
		
		return doc4;
	}//end of getUniqueWords(String a, String b)
	
	private double cosineSimilarity(double[] vect1, double[] vect2)
	{
		return dotProduct(vect1, vect2)/(magnitude(vect1)*magnitude(vect2));
	}
	
	private double dotProduct(double[] vect1, double[] vect2)
	{
		double dotProd = 0.0;
		for(int x = 0; x < vect1.length; x++)
		{
			dotProd = dotProd + (vect1[x] * vect2[x]);
		}
		return dotProd;
	}
	
	private double magnitude(double[] vect)
	{
		double magnitude = 0.0;
		for(int x = 0; x < vect.length; x++)
		{
			magnitude = magnitude + (Math.pow(vect[x], 2));
		}
		
		magnitude = Math.sqrt(magnitude);
		return magnitude;
	}
}
