package dm.utils.ml;

public class Classifier {

	public Classifier() {
		// TODO Auto-generated constructor stub
	}

	public int classify(String msg){
		return (int)Math.ceil(Math.random()*10)%3;
	}
	

}
