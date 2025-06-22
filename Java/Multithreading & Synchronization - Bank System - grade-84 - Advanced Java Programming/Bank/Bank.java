import java.util.ArrayList;
import java.util.Random;

public class Bank {
	
	private final static int NumOfBanks=5;
	private final static int NumOfTeller=10;
	
	public static void main(String[] args) {
		Random rd= new Random();
		int maxTrans=rd.nextInt(NumOfBanks*NumOfTeller);
		BankTeller [] bt= new BankTeller[NumOfTeller];
		ArrayList<BankAccount>accounts= new ArrayList<BankAccount>();
		ArrayList<BankAccount>trans=new ArrayList<BankAccount>();
		double tempValue;
		int bankNum;
		TransactionInfo ti;
		for (int i = 0; i < NumOfBanks; i++) 
		{
			accounts.add(new BankAccount(i, 0));
			System.out.println("Before: this is account: "+i+" with value: 0");
			
		}
		System.out.println("\nThe number of transactions is: "+maxTrans );
		System.out.println("Those are the transactions:");
		for (int i = 0; i < maxTrans; i++)
		{
			bankNum=rd.nextInt(NumOfBanks);
			tempValue=actionValue();
			trans.add(new BankAccount(bankNum,tempValue));
			System.out.println("Before: this is transaction on account: "+bankNum+" with value of transaction: "+tempValue );
		}
		System.out.println();
		ti=new TransactionInfo(trans);
		for (int i = 0; i < NumOfTeller; i++) 
		{
			bt[i]= new BankTeller(ti, accounts);
		}
		for (int i = 0; i < NumOfTeller; i++) 
		{
			bt[i].start();
		}
		while(!isDone(bt));
		System.out.println();
		for (int i = 0; i < NumOfBanks; i++)
			System.out.println("After: this is account: "+i+" with value: "+accounts.get(i).GetAccountRemain());
		
		
		
		
		
/*הגדירו את מחלקת הבנק המייצרת את האלמנטים שהוגדרו לעיל:
- 5 חשבונות בנק עם מספרי חשבון 0..4 .אתחלו את החשבונות עם יתרה 0 .
- מאגר עם תנועות עבור 5 החשבונות עם הפקדות ומשיכות רנדומליות בטווח של 1000 -עד
.1000
- 10 פקידים המקבלים בבנאי הפנייה למאגר התנועות והפנייה למבנה נתונים המכיל את
החשבונות.
- הריצו את הפקידים עם הדפסות מלוות כדי שתוכלו לעקוב אחר פעילות המערכת*/
	}
	private static double actionValue()
	/*making random values while lowering the chance to get a negative number to 1/4 */
	{ 
		Random rd= new Random();
		double tempValue= Math.random()*1000;
		if (rd.nextInt(4)==1) 
			return tempValue*-1;
		else
			return tempValue;
		/*return tempValue;*/
		
	}
	private static boolean isDone(BankTeller [] bt)
	/*checks if all of the bank tellers are done*/
	{
		for (int i = 0; i <NumOfTeller ; i++) {
			if(!bt[i].gettellerDone())
				return false;
			
		}
		return true;
	}

}
