import java.util.ArrayList;

public class TransactionInfo {
	private ArrayList<BankAccount>trans;/*each transaction can be refer as a bank account because the options for id's are the same and the values can be present also as an action  */
	
	public TransactionInfo(ArrayList<BankAccount>trans)/*constructor for the list of transaction*/
	{
		this.trans=trans;
	}
	public synchronized BankAccount getTransaction()
	/*pull a transactions out of the list while mutual exclusion*/
	{
		if (this.trans.size()==0)
			return null;
		else
			return this.trans.remove(0);
	}
}
