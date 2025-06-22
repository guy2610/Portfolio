import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

public class HangManController {

    @FXML
    private Canvas canv;

    @FXML
    private TextArea usedChars;

    @FXML
    private TextArea strLine;

    @FXML
    private GridPane grid;
    @FXML
    private Button reset;

    @FXML
    void resetPressed(ActionEvent event)/*moving to the next word and reseting everything*/
    {
    	level=0;
    	wordNumber++;
    	if(wordNumber<words.size())
    	{
    		/*words.get(wordNumber);*/
    		if (words.get(wordNumber).length()==0)
    		{
    			System.out.println("There is an empty word in the file in line: "+(wordNumber+1));
    			System.exit(1);
    		}	
    	}
    	else
    		System.exit(0);
    	drawing(0);
    	buttonInitialize();
    	textInitialize();
    }
    
    private GraphicsContext gc;
    private Button[] buttons; Button btnX;
    private final int numOfChars=26;
    private final int charLines=13;
    private final int charColumn=2;
    private int level;
    private ArrayList<String> words;
    private int wordNumber;
    
    public void initialize() /*initializing the application*/
    {
    	gc = canv.getGraphicsContext2D();
    	fileHandle();
    	shuffleWords();
    	buttonInitialize();
    	textInitialize();
    	drawing(0);
    }
    
    private void textInitialize() /*initialing all the text in the application*/
    {
    	int strLength=words.get(wordNumber).length();
    	strLine.setPrefSize(strLine.getPrefWidth()/strLength, strLine.getPrefHeight()/strLength);
    	strLine.setEditable(false);
    	usedChars.setEditable(false);
    	usedChars.setText("Those are the labels\nyou have used for\nword number "+(wordNumber+1)+":\n");
    	strLine.clear();
    	
    	for(int i=0;i<strLength;i++)
    		strLine.appendText("_");
    }
    
    private void buttonInitialize()/*initialing all the buttons in the application and reseting for the next word*/
    {
    	if (wordNumber==0) 
    	{
    		buttons= new Button[numOfChars];
    		for(int i=0;i<charColumn*charLines;i++)
    		{
    			buttons[i]=new Button(""+(char)(97+i));/*'a'*/ 
    			buttons[i].setPrefSize(grid.getPrefWidth(), grid.getPrefHeight());
    			buttons[i].setOnAction(new EventHandler<ActionEvent>() {
    				@Override public void handle(ActionEvent e) {
    					handleButton(e);
    				}
    			});
    			grid.add(buttons[i], (i%charColumn), (i/charColumn));
    		}
    	}
    	btnX=null;
    	for(Button b: buttons ) 
    		b.setDisable(false);
    		
    	
    }
    
    private void handleButton(ActionEvent e)/*handling the buttons with alphabet and pop a message if win or lose  */
    {
    	if (btnX==null)
    		btnX=(Button)e.getSource();
    	else {
    	 if (btnX.getText()!=""&&strLine.getText().contains("_"))
    	 {
    		 if(level<6) 
    		 {
    			 usedChars.appendText(btnX.getText()+" ,\n");
    			 btnX.setDisable(true);
    		 }
    		 charInText(btnX,words.get(wordNumber));
    	 }
    	 if(strLine.getText().equals(words.get(wordNumber))) 
 			JOptionPane.showMessageDialog(null,"You have Won this game, Lets see your luck in next game!!\nclick ok and then next word button.","Hang-Man",JOptionPane.INFORMATION_MESSAGE);
    	 if(level>=6&&!strLine.getText().equals(words.get(wordNumber)))
     		JOptionPane.showMessageDialog(null,"You have Lost this game, try your luck in next game!!\nclick ok and then next word button.","Hang-Man",JOptionPane.INFORMATION_MESSAGE);
    	 btnX=null;
    	}
    	
    }

    private void charInText(Button btnX,String word)/*check if the char in the word which to be guessed*/
    {
    	int charExist=0;
    	for(int i=0;i<word.length();i++) {
    		if(btnX.getText().charAt(0)==word.charAt(i))
    			if(strLine.getText().charAt(i)=='_')
    			{
    				strLine.setText(strLine.getText().substring(0, i)+word.charAt(i)
    				+strLine.getText().substring(i+1,word.length()));
    				charExist=1;
    			}				
    	}
    	if (charExist==0)
    	{
    		level+=1;
    		drawing(level);
    		if(level<6&&!strLine.getText().equals(words.get(wordNumber)))/*level<6||*/
       		 	btnX.setDisable(true);
    	}
    	else if (charExist==1&&strLine.getText().equals(words.get(wordNumber))) 
    		btnX.setDisable(true); 		 	
    }
    
    private void drawing(int level)/*printing on the canvas*/
    {
    	switch(level) 
    	{
    	case 0:/*draw the Gallows*/
    		gc.clearRect(0, 0, canv.getWidth(), canv.getHeight());
    		gc.setLineWidth(1);
        	gc.strokeLine(canv.getWidth()*4/5, canv.getHeight()/5, canv.getWidth()*4/5, canv.getHeight()*4/5);
        	gc.strokeLine(canv.getWidth()*2/5, canv.getHeight()/5,  canv.getWidth()*4/5, canv.getHeight()/5);
        	gc.strokeLine(canv.getWidth()*3.5/5,canv.getHeight()*4/5,canv.getWidth()*4.5/5,canv.getHeight()*4/5);
        	gc.strokeLine(canv.getWidth()*2/5, canv.getHeight()/5, canv.getWidth()*2/5, canv.getHeight()*1.5/5);
        	
        	break;
    	case 1:
    		/*draw head*/
    		gc.setLineWidth(5);
    		gc.strokeOval(canv.getWidth()*1.65/5, canv.getHeight()*1.5/5, canv.getWidth()*1.5/10, canv.getHeight()*1.4/10);
    		break;
    	case 2:
    		/*draw body*/
    		gc.strokeLine(canv.getWidth()*2/5, canv.getHeight()*2.2/5, canv.getWidth()*2/5, canv.getHeight()*5.9/10);
    		break;
    	case 3:
    		/*draw left hand */
    		gc.strokeLine(canv.getWidth()*1.95/5, canv.getHeight()*2.3/5, canv.getWidth()*1.5/5, canv.getHeight()*2.7/5);
    		break;
    	case 4:
    		/*draw right hand*/
    		gc.strokeLine(canv.getWidth()*2.05/5, canv.getHeight()*2.3/5, canv.getWidth()*2.55/5, canv.getHeight()*2.7/5);
    		break;
    	case 5:
    		/*draw left leg*/
    		gc.strokeLine(canv.getWidth()*1.95/5, canv.getHeight()*2.97/5, canv.getWidth()*1.5/5, canv.getHeight()*3.3/5);
    		break;
    	case 6:
    		gc.strokeLine(canv.getWidth()*2.05/5, canv.getHeight()*2.97/5, canv.getWidth()*2.55/5, canv.getHeight()*3.3/5);
    		/*draw right leg*/
    		break;
    	}	
    	
    }
    
    private boolean isNotLabel(String str)/*checking if the word has only alphabet characters*/
    {
    	for(int i=0; i<str.length();i++)
    		if(str.charAt(i)<'a'||str.charAt(i)>'z')
    			return true;
    	return false;
    }
    private void shuffleWords()/*shuffling the list */
    {
    	Collections.shuffle(words);
    }
    private void fileHandle()/*reading from the file the words that need to be guessed and pop and eror if the file doesn't exist or ending the program if a word has non alphabetic chars*/
    {
    	try {
    	      File myObj = new File("hangman_words.txt");
    	      Scanner myReader = new Scanner(myObj);
    	      words=new ArrayList<String>();
    	      String[] lines;
    	      String temp;
    	      while (myReader.hasNextLine())
    	      {
    	    	  temp=myReader.nextLine().toLowerCase();
    	    	  lines=temp.split(" ");
    	    	  for(String s:lines) 
    	    	  {
    	    		  if(isNotLabel(s)) 
    	    		  {
    	    			  System.out.println("input has non alphabetic labels. the word which has the problem in the input file is : "+s);
        	    		  System.exit(1);
    	    		  }
    	    			  
    	    	  }
    	    	  for(String s:lines) {
	    			  words.add(s.toLowerCase());
	    		  }
    	      }
    	      myReader.close();
    	    } catch (FileNotFoundException e) {
    	      System.out.println("An error occurred with opening the file.");
    	      e.printStackTrace();
    	      System.exit(1);
    	    }	
    }
}
