import java.util.Random;
public class Game {
     private String secretNum;
     private Random rand=new Random();
     public Game()/*build an object which is a random 4 digits string */
     {
         int a,b,c,d;
         a=rand.nextInt(10);
         do{
            b=rand.nextInt(10);
         }while (a==b);
         do{
             c=rand.nextInt(10);
         }while (a==c||b==c);
         do{
             d=rand.nextInt(10);
         }while (a==d||b==d||c==d);
         secretNum=String.valueOf(a)+String.valueOf(b)+String.valueOf(c)+String.valueOf(d);
     }
     public String Feedback(String str)/*this method gets a string and use other methods and gives feedback to Main */
     {
         String massage;
         massage=Takin(str);
         if (massage!=null)
             return massage;
         else{
             massage=Results(str);
             return massage;
         }
     }
     private String Takin(String str)/*check if the input is correct, and return if there is an error */
     {
         String massage=null;
         if(str.length()!=4)
             massage= "the input length is incorrect, it must have 4 digit numbers without duplicates. please try again.";
         else if(str.charAt(0)==str.charAt(1) ||str.charAt(0)==str.charAt(2)||str.charAt(0)==str.charAt(3)||str.charAt(1)==str.charAt(2)||str.charAt(1)==str.charAt(3)||str.charAt(2)==str.charAt(3))
             massage= "the input has duplicates, it must have 4 digit numbers without duplicates. please try again";
         else if (str.charAt(0)<'0'|| str.charAt(0)>'9'||str.charAt(1)<'0'|| str.charAt(1)>'9'||str.charAt(2)<'0'|| str.charAt(2)>'9'||str.charAt(3)<'0'|| str.charAt(3)>'9') {
             massage="the input has non numbers characters, it must have 4 digit numbers without duplicates. please try again";
         }
         return massage;
     }
     private String Results(String str)/*counts the number of bull and pgiah and return a string with the results divided by char "|" */
     {
         int bull=0;
         int pgiah=0;
         for (int i=0;i<=3;i++)
         {
             for (int j=0;j<=3;j++)
             {
                 if(secretNum.charAt(i)==str.charAt(j)) {
                     if (i == j)
                         bull++;
                     else
                         pgiah++;
                 }
             }
         }
         return bull+"|"+pgiah;
     }
}
