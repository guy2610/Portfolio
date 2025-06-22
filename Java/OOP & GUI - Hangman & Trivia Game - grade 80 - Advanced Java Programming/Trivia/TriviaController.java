import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javax.swing.JOptionPane;
public class TriviaController {
    @FXML
    private TextArea score;
    @FXML
    private TextArea question;

    @FXML
    private RadioButton radio1;

    @FXML
    private ToggleGroup radioToggle;

    @FXML
    private RadioButton radio2;

    @FXML
    private RadioButton radio3;

    @FXML
    private RadioButton radio4;

    @FXML
    void exitGamePressed(ActionEvent event)/*when user click on exit game, a message will pop up and tell him the current score*/ 
    {
    	JOptionPane.showMessageDialog(null,"You have finish the game with the score: "+points+" points.","Trivia",JOptionPane.INFORMATION_MESSAGE);
    	System.exit(0);
    }

    @FXML
    void newGamePressed(ActionEvent event)/*when user click on new game, it will initialize the attributes and shuffling the questions and the possibles answers to the first questions */ 
    {
    	points=0;
    	questionsShuffle();
    	questionNumber=0;
    	choiceTemp=null;
    	radio1.setSelected(false);
    	radio2.setSelected(false);
    	radio3.setSelected(false);
    	radio4.setSelected(false);
    	question.setText(questions.get(questionNumber).getQuestion());
    	questions.get(questionNumber).shuffleAnswers();
    	radio1.setText(questions.get(questionNumber).getAnswers().get(0));
    	radio2.setText(questions.get(questionNumber).getAnswers().get(1));
    	radio3.setText(questions.get(questionNumber).getAnswers().get(2));
    	radio4.setText(questions.get(questionNumber).getAnswers().get(3));
    }

    @FXML
    void nextQuestionPressed(ActionEvent event)
    /*when user click on next question, it will exit the game and pop a message with the score if there is no questions.
     * else it will set the next questions and it's possible answers and reset the toggle buttons.
     * and also it will calculate the score for the user if he was right or wrong.*/
    {
    	if(choiceTemp==null)
    		points=points-5;
    	else if(choiceTemp.equals(questions.get(questionNumber).getCorrectAnswer()))
    		points=points+10;
    	else
    		points=points-5;
    	questionNumber++;
    	score.setText("Your current score is: " +points+" points.");
    	if(questionNumber>=questions.size()) 
    	{
    		JOptionPane.showMessageDialog(null,"You have finish the game with the score: "+points+" points.","Trivia",JOptionPane.INFORMATION_MESSAGE);
    		System.exit(0);
    	}
    	question.setText(questions.get(questionNumber).getQuestion());
    	questions.get(questionNumber).shuffleAnswers();
    	radio1.setText(questions.get(questionNumber).getAnswers().get(0));
    	radio2.setText(questions.get(questionNumber).getAnswers().get(1));
    	radio3.setText(questions.get(questionNumber).getAnswers().get(2));
    	radio4.setText(questions.get(questionNumber).getAnswers().get(3));
    	radio1.setSelected(false);
    	radio2.setSelected(false);
    	radio3.setSelected(false);
    	radio4.setSelected(false);
    }

    @FXML
    void radio1Pressed(ActionEvent event)/*changing the toggle button text for the first possible answer*/ 
    {
    	choiceTemp=radio1.getText();
    }

    @FXML
    void radio2Pressed(ActionEvent event)/*changing the toggle button text for the second possible answer*/
    {
    	choiceTemp=radio2.getText();
    }

    @FXML
    void radio3Pressed(ActionEvent event)/*changing the toggle button text for the third possible answer*/ 
    {
    	choiceTemp=radio3.getText();
    }

    @FXML
    void radio4Pressed(ActionEvent event)/*changing the toggle button text for the fourth possible answer*/
    {
    	choiceTemp=radio4.getText();
    }
    private ArrayList<Question> questions;/*list of the questions, each question is an object with the question line and its possible answers*/
    private int rowNumber;/*counting row number for checking the input file format*/
    private int questionNumber;/*question index*/
    private int points=0;/*points in the game*/
    private String choiceTemp=null;/*the text of the toggle button user choice*/
	private ArrayList<Question> randomQuestions= new ArrayList<Question>();/*list that helps for random the question*/
	private Random rand=new Random();
    public void initialize() /*initializing the application*/
    {
    	fileHandle();
    	questionsShuffle();
    	question.setText(questions.get(questionNumber).getQuestion());
    	questions.get(questionNumber).shuffleAnswers();
    	radio1.setText(questions.get(questionNumber).getAnswers().get(0));
    	radio2.setText(questions.get(questionNumber).getAnswers().get(1));
    	radio3.setText(questions.get(questionNumber).getAnswers().get(2));
    	radio4.setText(questions.get(questionNumber).getAnswers().get(3));
    	score.setText("Your current score is: " +points+" points.");
    }
    private void questionsShuffle() /*shuffling the questions */
    {
    	int k;
    	int randomQuestionsStartSize=randomQuestions.size();
    	for(int i=0;i<questions.size();i++) 
   		{
   			k=rand.nextInt(this.questions.size());
   			if(randomQuestionsStartSize!=0)
    			this.randomQuestions.set(i, this.questions.get(k));
    		else
    			this.randomQuestions.add(this.questions.get(k));
   			this.questions.remove(k);
    	}
    	for(int i=0;i<randomQuestions.size();i++) 
    		this.questions.add(randomQuestions.get(i));
    }
    private void fileHandle()
    /*trying to open the file it didn't succeed it will pop an error.
     otherwise it checks the input file format and if it is the correct format.
     the list of Questions will be built and each of the Question is an object of question, correct answer and 3 more false answers */
    {
    	try {
  	      File myObj = new File("TriviaQuestionsJava.txt");
  	      Scanner myReader = new Scanner(myObj);
  	      questions=new ArrayList<Question>();
  	      String temp;
  	      String questionTemp=null;
  	      String correctAnswerTemp=null;
  	      String wrongAnswerTemp1=null;
  	      String wrongAnswerTemp2=null;
  	      String wrongAnswerTemp3=null;
  	      while (myReader.hasNextLine())
  	      {
  	    	  temp=myReader.nextLine();
  	    	  if(rowNumber%5==0&&!temp.contains("?")) /*ensuring there is a question mark in the question*/
  	    	  {
  	    		System.out.println("A question line suppose to have a question mark. the line is: \""+temp+ "\" in row number: "+rowNumber);
  	    		System.exit(1);
  	    	  }
  	    	  if(rowNumber%5==0)
  	    		  questionTemp=temp;  	    	  
  	    	  if(rowNumber%5==1)
  	    		  correctAnswerTemp=temp;
  	    	  if(rowNumber%5==2)
  	    		  wrongAnswerTemp1=temp;
  	    	  if(rowNumber%5==3)
  	    		  wrongAnswerTemp2=temp;
  	    	  if(rowNumber%5==4)
  	    		  wrongAnswerTemp3=temp;
  	    	  if(questionTemp!=null&&correctAnswerTemp!=null&&wrongAnswerTemp1!=null&&wrongAnswerTemp2!=null&&wrongAnswerTemp3!=null)
  	    	  {
  	    		  questions.add(new Question(questionTemp,correctAnswerTemp,wrongAnswerTemp1,wrongAnswerTemp2,wrongAnswerTemp3));
  	    		  questions.get(questionNumber).shuffleAnswers();
  	    		  questionTemp=null;
  	    		  correctAnswerTemp=null;
  	    		  wrongAnswerTemp1=null;
  	    		  wrongAnswerTemp2=null;
  	    		  wrongAnswerTemp3=null;
	    	  }
  	    	  rowNumber++; 
  	      }
  	      myReader.close();
  	      if(rowNumber%5!=0) 
  	      {
  	    	System.out.println("input is not valid. It should be in the correct format\nQuetion\nCorrect answer\nWrong answer No. 1\nWrong answer No. 2\nWrong answer No. 3   ");
  	    	System.exit(1);
  	    	
  	      }
  	    } catch (FileNotFoundException e) {
  	      System.out.println("An error occurred with opening the file.");
  	      e.printStackTrace();
  	      System.exit(1);
  	    }
    }

}
