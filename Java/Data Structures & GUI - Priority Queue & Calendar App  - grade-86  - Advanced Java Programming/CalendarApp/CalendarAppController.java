

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class CalendarAppController {

    @FXML
    private MenuButton month;

    @FXML
    private MenuButton year;

    @FXML
    private GridPane grid;

    @FXML
    private TextArea meeting;
    Button []dateButton=new Button[42] ;
    Button btnx;
    String monthNum="";
    String yearNum="";
    private final int startYear=1999;
    private final int untilYear=2050;
    Calendar c=Calendar.getInstance();
    HashMap<Integer,ArrayList<String>> date= new HashMap<Integer,ArrayList<String>>();
    ArrayList<String>meetings;
    int dateInNumber=0;
    String massage;
    private int dateToInt(String monthNum,String yearNum,String day) 
    /*get month,year and day in strings and returning the date in int with the format yyyymmdd*/
    {
    	if(day.length()==1)
    		day="0"+day;
    	if(monthNum.length()==1)
    		monthNum="0"+monthNum;
    	String s=yearNum+monthNum+day;
    	return Integer.parseInt(s);
    }
    public void initialize() 
    /*Initializing the Application*/
    {
    	monthInitialize();
    	yearInitialize();
    	gridRefresh("","");
    }
    private void monthInitialize() 
    /*initializing the menu button for month*/
    {
    	MenuItem m;
    	EventHandler<ActionEvent> action;
    	for(int i=0;i<12;i++) 
    	{
    		m=new MenuItem(""+(i+1));
    		action=changeMonthName();
    		m.setOnAction(action);
    		month.getItems().add(m);
    	}	
    }
    private EventHandler<ActionEvent> changeMonthName()
    /*for each select month, changing menu button name and refreshing the grid*/ 
    {
        return new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                month.setText(((MenuItem) event.getSource()).getText());
                monthNum=month.getText();
                gridRefresh(monthNum,yearNum);
            }
        };
    }

    private void yearInitialize()
    /*initializing the menu button for year*/ 
    {
    	MenuItem m;
    	EventHandler<ActionEvent> action;

    	for(int i=startYear;i<untilYear;i++)
    	{
        	m=new MenuItem(""+(i+1));
    		action=changeYearName();
    		m.setOnAction(action);
    		year.getItems().add(m);
    	}
    }
    private EventHandler<ActionEvent> changeYearName() 
    /*for each select year, changing menu button name and refreshing the grid*/
    {
        return new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                year.setText(((MenuItem) event.getSource()).getText());
                yearNum=year.getText();
                gridRefresh(monthNum,yearNum);
            }
        };
    }
    
    private void gridRefresh(String monthNum,String yearNum) 
    /*gets current year and month in strings
     * refreshing the grid due to the current month and year,
     *  initialing the button on the grid with their names and making an event when clicking on them.
     once the buttons are initialized, theirs names are changing due to the current month and year*/
    {
    	int firstDay;
    	int numOfDaysInMonth;
    	int counter=1;
    	for(int i=0;i<42;i++)
   		{
   			if(monthNum.equals("")||yearNum.equals(""))
   			{
   				dateButton[i]=new Button("");
   				dateButton[i].setPrefSize(grid.getPrefWidth()/6, grid.getPrefHeight()/6);
   	   			grid.add(dateButton[i], (i%7), (i/7));
   			}
   			else
   			{
   				firstDay=(dayInCalendar(monthNum,yearNum,1));
   				numOfDaysInMonth=numOfMonth(monthNum,yearNum);
   				if (firstDay<=(i+1)&&firstDay+numOfDaysInMonth>(i+1))
   					dateButton[i].setText(""+(counter-firstDay+1));
   				else
   					dateButton[i].setText("");
   				dateButton[i].setOnAction(new EventHandler<ActionEvent>() {
   	   				@Override public void handle(ActionEvent e) {
   	   					handleGridButton(e);
   	   				}
   	   			});
   			}
   			counter++;	
   		}
    	btnx=null;
    }
    private int numOfMonth(String monthNum,String yearNum)
    /*gets the current month and year
     * returning the number of days in the month while checking if February is in a leap year or not*/ 
    {
    	int monthNumber=Integer.parseInt(monthNum);
    	int yearNumer=Integer.parseInt(yearNum);
    	int numOfDaysInMonth=0;
    	boolean isLeapYear = yearNumer%4 == 0 && (yearNumer%100 != 0 || yearNumer%400 == 0);
    	switch (monthNumber)
    	{
    		case 1:
    			numOfDaysInMonth=31;
    			break;
    		case 2:
    			if(isLeapYear)
    				numOfDaysInMonth= 29;
    			else
    				numOfDaysInMonth= 28;
    			break;
    		case 3:
    			numOfDaysInMonth= 31;
    			break;
    		case 4:
    			numOfDaysInMonth= 30;
    			break;
    		case 5:
    			numOfDaysInMonth= 31;
    			break;
    		case 6:
    			numOfDaysInMonth= 30;
    			break;
    		case 7:
    			numOfDaysInMonth= 31;
    			break;
    		case 8:
    			numOfDaysInMonth= 31;
    			break;
    		case 9:
    			numOfDaysInMonth= 30;
    			break;
    		case 10:
    			numOfDaysInMonth= 31;
    			break;
    		case 11:
    			numOfDaysInMonth= 30;
    			break;
    		case 12:
    			numOfDaysInMonth= 31;
    			break;
    	}
    	return numOfDaysInMonth;	
    }

	private int dayInCalendar(String monthNum,String yearNum, int i)
	/*gets the current month and year and the day in the date
	 * returning the day of the week of the current date  */
    {
    	c.set(Integer.parseInt(yearNum), Integer.parseInt(monthNum)-1, i);
    	return c.get(Calendar.DAY_OF_WEEK);
    }
    private void handleGridButton(ActionEvent e) 
    /*gets the event from the button on the grid.
     * checking if the button has a number in string. 
     * if the date(current year, current month and button number) 
     * 		exist in the HashMap, printing the meetings in this date, asking the user via dialog message if he want to (add,edit,delete or closing the window), and saving the list to value when the HashMap's key is the date in number
     * else 
     * 		asking the user via dialog message if he want to (add,edit,delete or closing the window), and saving the list to value when the HashMap's key is the date in number
     * printing in the TextArea the meetings for this current date*/
    {
    	if (btnx==null)
    		btnx=(Button)e.getSource();
    	meeting.setText("");
    	if(!monthNum.equals("")&&!yearNum.equals(""))
   		{
    		if(!btnx.getText().equals("")) 
    		{
    			dateInNumber=dateToInt(monthNum,yearNum,btnx.getText());
    			if (date.containsKey(dateInNumber)) 
    			{
    				meetings=date.get(dateInNumber);
    				printingMettings(dateInNumber,meetings);
    				String st;
    				st=JOptionPane.showInputDialog("please select the correct option\nfor add press 1\nfor edit press 2\nfor delete press 3\nto close the window press 4");
    				dialogWithUser(st,meetings,dateInNumber);  
    			}
    			else 
    			{
    				meetings=new ArrayList<String>();
    				dialogWithUser("1",meetings,dateInNumber);
    				date.put(dateInNumber, meetings);
    			}
    			printingMettings(dateInNumber,meetings);
    		}
    	}	 	
    	btnx=null;	
    }
    private void printingMettings(Integer dateInNumber,ArrayList<String> s) 
    /*gets the date in number and a list of the meetings in this date
     * printing to the TextArea the meetings*/
    {
    	String dt=""+dateInNumber%100+"\\"+(dateInNumber%10000)/100+"\\"+dateInNumber/10000;
    	String str= "those are the following meetings for "+dt+" : \n";
    	int counter=1;
    	for(String item:s)
    	{
    		str=str+counter+") "+item+"\n";
    	}
    	meeting.setText(str);
    }
    private void dialogWithUser(String option,ArrayList<String>s,Integer dateInNumber) 
    /*gets option via user and date number
     * interacting with user via dialog messages
     * checking for input's correction
     * changing the list(value in the HashMap) of this current date number(key in the HashMap)  */
    {
    	String dt=""+dateInNumber%100+"\\"+(dateInNumber%10000)/100+"\\"+dateInNumber/10000;
    	String str="";
    	String massage="";
    	int i=0;
    	switch(option) 
    	{
    		case "0":/*there are no meetings*/
    			massage="do you want to add a meeting?";
    			JOptionPane.showMessageDialog(null,massage,"Error",JOptionPane.ERROR_MESSAGE);
    			option=JOptionPane.showInputDialog("please enter proper input for your guess");
    			
    			break;
    		case "1": /*there are meetings and user wanted to add a meeting*/
    			str=JOptionPane.showInputDialog("please write the meeting for "+dt+" :");
    			s.add(str);
    			option=JOptionPane.showInputDialog("to do more actions please select the correct option for date "+dt+"\nfor add press 1\nfor edit press 2\nfor delete press 3\nto close the window press 4");
    			dialogWithUser( option,s,dateInNumber);
    			break;
    		case "2": /*there are meeting and user wanted to edit a meeting */
    			str=JOptionPane.showInputDialog("you have "+s.size()+" please write the number of meeting you want to edit for date " +dt+" :");
    			str.toLowerCase();
    			/*check if the meeting number is currect*/
    			while(!currectMeetingNum(str,""+s.size()))
    			{
    				JOptionPane.showMessageDialog(null,"the input is not a meeting number","Error",JOptionPane.ERROR_MESSAGE);
    				str=JOptionPane.showInputDialog("you have "+s.size()+" please write the number of meeting you want to edit for date"+dt+" :");
        			str.toLowerCase();
    			}
    			i=Integer.parseInt(str)-1;
    			str=JOptionPane.showInputDialog("this is the meeting you pick to edit:\n"+s.get(i)+"\n copy the meeting and edit it as you like");
    			s.set(i, str);
    			option=JOptionPane.showInputDialog("to do more actions please select the correct option for date "+dt+"\nfor add press 1\nfor edit press 2\nfor delete press 3\nto close the window press 4");
    			dialogWithUser( option,s,dateInNumber);	
    			break;
    		case "3": /*there are meeting and user wanted to delete a meeting*/
    			if(s.size()==0)
    			{
    				JOptionPane.showMessageDialog(null,"there are no meetings in this date "+dt+" , delete option is not possible please start over","Error",JOptionPane.ERROR_MESSAGE);
    				dialogWithUser("4",s,dateInNumber);
    				break;
    			}
    			str=JOptionPane.showInputDialog("you have "+s.size()+" please write the number of meeting you want to delete:");
    			str.toLowerCase();
    			/*check if the meeting number is current*/
    			while(!currectMeetingNum(str,""+s.size()))
    			{
    				JOptionPane.showMessageDialog(null,"the input is not a meeting number","Error",JOptionPane.ERROR_MESSAGE);
    				str=JOptionPane.showInputDialog("you have "+s.size()+" please write the number of meeting you want to delete for this date "+dt+":");
        			str.toLowerCase();
    			}
    			i=Integer.parseInt(str)-1;	
    			JOptionPane.showMessageDialog(null,"this is the meeting you deleting\n"+s.get(i),"Error",JOptionPane.ERROR_MESSAGE);
    			s.remove(i);
    			option=JOptionPane.showInputDialog("to do more actions please select the correct option for this date"+dt+"\nfor add press 1\nfor edit press 2\nfor delete press 3\nto close the window press 4");
    			dialogWithUser( option,s,dateInNumber);	
    			break;
    		case "4":/*the user wanted to close the window*/
    			break;
    		default:/*wrong input*/
    			JOptionPane.showMessageDialog(null,"wrong input please try again","Error",JOptionPane.ERROR_MESSAGE);
    			option=JOptionPane.showInputDialog("please select the correct option for this date "+dt+"\nfor add press 1\nfor edit press 2\nfor delete press 3\nto close the window press 4");
    			dialogWithUser( option,s,dateInNumber);	
    	} 
    }
    private boolean currectMeetingNum(String str,String length) 
    /*gets string and string which representing the size of the list due to the current date number(key in the HashMap)
     * returns false if the string is not a number
     * returns false if the number of the string is bigger than length (as number) 
     * else returns false*/
    {
    	for(int i=0;i<str.length();i++)
    		if(str.charAt(i)<'0'||str.charAt(i)>'9')
    			return false;
    	if(Integer.parseInt(str)>Integer.parseInt(length))
    		return false;
    	else
    		return true;	
    }
}
