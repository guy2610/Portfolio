import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Data {
	private int x = 0;
	private int y = 0;
	public Data (int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	//סעיף א
	//running in parallel without making sure that the difference between the numbers is correct
	public int getDiff() 
	{
		System.out.println("SecondMonitor with"+Thread.currentThread().getName() +"-> the difference of Data between x and y is : "+Math.abs(x-y));
		return (Math.abs(x-y));
	}
	public void update(int dx, int dy)
	{
		x = x + dx;
		y = y + dy;
		System.out.println("FirstMonitor with "+Thread.currentThread().getName()+"-> data has been updated to: x: "+x+" and y: "+y);
	}
	
	//סעיף ב
	//running in parallel and making sure that the difference between the numbers is correct. mutual exclusion
	/*public synchronized int getDiff()
	{
		System.out.println("SecondMonitor with"+Thread.currentThread().getName() +"-> the difference of Data between x and y is : "+Math.abs(x-y));
		return (Math.abs(x-y));
	}
	public synchronized void update(int dx, int dy)
	{
		x = x + dx;
		y = y + dy;
		System.out.println("FirstMonitor with "+Thread.currentThread().getName()+"-> data has been updated to: x: "+x+" and y: "+y);
	}*/
	
	
	
	
	//סעיף ג, צריך לשנות גם במחלקה עם הפונקציה הראשית
	//running in parallel and making sure that the difference between the numbers is correct and if there is an action of an update only the current thread is running until it exited the method
	/*private Lock lock =new  ReentrantLock();
	private Condition cond= lock.newCondition();
	private boolean isLocked=false;
	public int getDiff()
	{
		lock.lock();
		try {
				if(isLocked)
					cond.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		finally{
			
			lock.unlock();
		}
		System.out.println("SecondMonitor with "+Thread.currentThread().getName()+"-> the difference of Data between x and y is : "+Math.abs(x-y));
		return (Math.abs(x-y));
	}
	public void update(int dx, int dy)
	{
		lock.lock();
		isLocked=true;
		try {
			x = x + dx;
			y = y + dy;
			System.out.println("FirstMonitor with "+Thread.currentThread().getName()+"-> data has been updated to: x: "+x+" and y: "+y);
			isLocked=false;
			cond.signalAll();
		}finally {
			lock.unlock();
		}
	}*/

}
