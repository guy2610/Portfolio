
public class BankAccount {
	
	private int accountId;
	private double accountRemain;
	
	public BankAccount(int accountId,double accountRemain)/*constructor for a bank account*/
	{
		this.accountId=accountId;
		this.accountRemain=accountRemain;
	}
	public synchronized void transaction(double money)
	/*gets the transactions money
	 * if it is positive than add the money
	 * if it is negative and could make the account to be in debt, waits until there is no possibility for debt.
	 * if the account can get in debt and there are no more transactions that will pull it from debt, the program will froze  */
	{
		if(money>=0)
			{
				accountRemain+=money;	
			}
		else {
				while(accountRemain+money<0)
					{
						try {
							wait();
						}catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							}
					}
				accountRemain+=money;
								
			}
		notifyAll();
	}
	public synchronized double GetAccountRemain()
	/*return the remaining while mutual exclusion */
	{
		return accountRemain;
	}
	public synchronized int GetId()
	/*return the id while mutual exclusion*/
	{
		return accountId;
	}
	
}
