import java.util.ArrayList;

public class BankTeller extends Thread {
	private TransactionInfo trans;
	private ArrayList<BankAccount>accounts;
	private boolean done= false;
	
	public BankTeller(TransactionInfo trans,ArrayList<BankAccount>accounts)/*constructor for the bank teller*/
	{
		this.trans=trans;
		this.accounts=accounts;
	}
	public void run()/*override run, teller gets a transaction and do it for a specific account. each transaction that is done the teller wait 100 milisec*/
	{
		
		super.run();
		BankAccount temp=trans.getTransaction();
		while(temp!=null)
		{
			for(BankAccount a: accounts)
			{
				if(temp.GetId()==a.GetId())
					{
						a.transaction(temp.GetAccountRemain());
						System.out.println("During Threading: this is "+currentThread().getName()+ ": with the transaction on account: "+a.GetId()+" with the remainning: "+a.GetAccountRemain() );
						try {
							sleep(100);							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						break;
					}	
			}
			temp=trans.getTransaction();
		}
		done=true;
			
	}
	public synchronized boolean gettellerDone()
	/*return if this teller is done while mutual exclusion*/ 
	{
		return this.done;
	}
	
}
