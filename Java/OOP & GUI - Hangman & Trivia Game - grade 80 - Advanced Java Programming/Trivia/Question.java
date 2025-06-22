import java.util.ArrayList;
import java.util.Random;

public class Question {
	private String question;
	private String correctAnswer;
	private String wrongAnswer1;
	private String wrongAnswer2;
	private String wrongAnswer3;
	private ArrayList<String> answers;
	private ArrayList<String> randomAnswers;
	private Random rand;
	
	public Question(String q, String cA, String wA1,String wA2,String wA3)
	{
		this.setQuestion(q);
		this.setCorrectAnswer(cA);
		this.setWrongAnswer1(wA1);
		this.setWrongAnswer2(wA2);
		this.setWrongAnswer3(wA3);
		this.answers=new ArrayList<String>();
		this.randomAnswers=new ArrayList<String>();
		rand = new Random();
		this.answers.add(cA);
		this.answers.add(wA1);
		this.answers.add(wA2);
		this.answers.add(wA3);
		
	}

	public ArrayList<String> getAnswers()
	{
		return this.answers;
	}
	public void shuffleAnswers()/*shuffling the answers*/
	{
		int k;
		for(int i=0;i<4;i++) 
		{
			k=rand.nextInt(this.answers.size());
			this.randomAnswers.add(this.answers.get(k));
			this.answers.remove(k);
		}
		for(int i=0;i<4;i++) 
			this.answers.add(randomAnswers.get(i));
	}
	public String getWrongAnswer2() {
		return wrongAnswer2;
	}
	public void setWrongAnswer2(String wrongAnswer2) {
		this.wrongAnswer2 = wrongAnswer2;
	}
	public String getWrongAnswer1() {
		return wrongAnswer1;
	}
	public void setWrongAnswer1(String wrongAnswer1) {
		this.wrongAnswer1 = wrongAnswer1;
	}
	public String getWrongAnswer3() {
		return wrongAnswer3;
	}
	public void setWrongAnswer3(String wrongAnswer3) {
		this.wrongAnswer3 = wrongAnswer3;
	}

	public String getCorrectAnswer() {
		return this.correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
}
