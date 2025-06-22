import java.util.Scanner;//using this action to get input from the user
public class Resistors
{
    public static void Main(String[] args) /*the program gets 3 resistors in int 
    , calculate the total resistance and then prints to the user the total resistance */
    {
        Scanner scan = new Scanner (System.in);//the program make a new object from class Scanner called "scan"
        System.out.println ("Please enter 3 integers:");//prints for the user to know what to do
        System.out.println ("Please enter r1:");//asking from the user the first  resistor
        int r1 = scan.nextInt();//inputs the first resistance from the user to r1 
        System.out.println ("Please enter r2:");//asking from the user the second resistor
        int r2 = scan.nextInt();//inputs the second resistance from the user to r2
        System.out.println ("Please enter r3:");//asking from the user the third resistor
        int r3 = scan.nextInt(); //inputs the third resistance from the user to r3 
        Double Total= 1.0/(1.0/r1+1.0/r2+1.0/r3);//the program calculate the total resistance
        System.out.println("The total resistance of resistors "+r1+ ", "+r2+" and "+r3+ " connected in parallel is: " +Total);
        //prints to the user the total resistance

    }
}
