import java.util.Scanner;

public class Football
{
    /*
 * *this program in Football class calculates the final score of the university football team that which played 3 games against a variety of teams.
 */
    public static void Main(String []args)
    {
        Scanner scan=new Scanner(System.in);
        int fHomeGoal,fOtherGoal,sHomeGoal,sOtherGoal,lHomeGoal,lOtherGoal;
        /* HomeGoal stands for university goals,OtherGoal stands for other team goals
         * "fHomeGoal" -"fOtherGoal"=first game 
        "sHomeGoal" -"sOtherGoal"=second game 
        "lHomeGoal" -"lOtherGoal"=last game */
        int FinalScore=0,CountWon=0,CountTie=0;
        /*FinalScore= final score,CountWon=univesity wins,CountTie=game end with tie */
        System.out.println("Please enter result for 3 games");//insert game results
        System.out.println("The first game:()  ");
        fHomeGoal=scan.nextInt();
        fOtherGoal=scan.nextInt();
        System.out.println("The second game: ");
        sHomeGoal=scan.nextInt();
        sOtherGoal=scan.nextInt();
        System.out.println("The third game: ");
        lHomeGoal=scan.nextInt();
        lOtherGoal=scan.nextInt();
        if(fHomeGoal> fOtherGoal||sHomeGoal>sOtherGoal)//if the university won
        {
            FinalScore+=3;
            CountWon++;
        }
        if(fHomeGoal<fOtherGoal|sHomeGoal<sOtherGoal||lHomeGoal<  lOtherGoal)//if the university lose
        {
            FinalScore-=4;
        }
        if(fHomeGoal==fOtherGoal||sHomeGoal== sOtherGoal||lHomeGoal==  lOtherGoal)//end with tie
        {
            FinalScore+=2;
            CountTie++;
        }
       
        if(lHomeGoal>  lOtherGoal) //if the university won in the last game
        {
            FinalScore+=3;
            FinalScore+=(lHomeGoal)*2;
            CountWon++;
        }
        
        boolean FirstCondiotion,SecondCondiotion,ThirdCondiotion;
        FirstCondiotion=lHomeGoal-lOtherGoal>3;//last game university won with more than 3 points difference
        SecondCondiotion=fHomeGoal-fOtherGoal>2&&sHomeGoal-sOtherGoal>2;//in the first and second games university won with more than 2 points difference
        ThirdCondiotion=lHomeGoal==lOtherGoal&&sHomeGoal==sOtherGoal&&fHomeGoal== fOtherGoal;//the 3 games end with a tie
        if(FirstCondiotion||SecondCondiotion||ThirdCondiotion)// if one of the conditions is true
        {   
            FinalScore+=5;
        }
        System.out.println("Number of games which the university team won: " +CountWon);
        System.out.println("Number of games with tie result: " +CountTie);
        System.out.println("Final score of the university team: " +FinalScore);

    }
}
