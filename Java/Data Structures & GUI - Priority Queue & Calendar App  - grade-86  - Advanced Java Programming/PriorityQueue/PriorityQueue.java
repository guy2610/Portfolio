import java.util.ArrayList;
import java.util.Iterator;

public class PriorityQueue<E> {
	private ArrayList<E> [] arr;
	private int maxPriority;
	
	public PriorityQueue(int n) 
	/*builder for PriorityQueue
	 * initializing the list the the main array(queue)*/
	{
		this.arr= new ArrayList[n];
		for(int i=0;i<n;i++)
		{
			this.arr[i]=new ArrayList<E>();
		}
		this.maxPriority=n;
	}
	public void add(E e, int i)
	/*gets an item and priority and adds it to the queue in the list with this priority*/
	{
		if(i>this.maxPriority||i<0)
			i=this.maxPriority;
		this.arr[i].add(e);
	}
	public E poll()
	/*polling the item which is the first in the queue*/
	{
		E e=null;
		for(int i=0;i<this.maxPriority;i++)
		{
			if(this.arr[i].size()!=0)
			{
				/*e=this.arr[i].get(0);*/
				e=this.arr[i].remove(0);
				break;
			}
		}
		return e;
	}
	public boolean contains(E e)
	/*gets an item and checking if exist in the PriorityQueue*/
	{ 
		E temp=null;
		for(int i=0;i<this.maxPriority;i++)
		{
			for(int j=0;j<this.arr[i].size();j++)
			{
				temp=this.arr[i].get(j);
				if(temp.equals(e))
					return true;
			}
		}
		return false;
	}

	public boolean remove(E e)
	/*gets an item and removing its first appearance in the PriorityQueue
	 * returns true if the item has removed successfully else return false
	 * */
	{
		int k;
		for(int i=0;i<this.maxPriority;i++)
		{
			k=this.arr[i].indexOf(e);
			if(k!=-1)
			{
				this.arr[i].remove(k);
				return true;
			}
		}
		return false;
	}
	public  int size()
	/* returning the size of the PriorityQueue
	 * */
	{
		int s=0;
		for(int i=0;i<this.maxPriority;i++)
			s=s+this.arr[i].size();
		return s;
	}
	private ArrayList<E> united()
	/*creating a list which concatenate all the list in PriorityQueue in order of their priority*/
	{
		ArrayList<E>unity=new ArrayList<E>();
		for(int i=0;i<maxPriority;i++)
			for(int j=0;j<this.arr[i].size();j++)
				unity.add(this.arr[i].get(j));
		return unity;
	}
	
	public Iterator<E> iterator()
	/*returning an Iterator which allow us to move in the PriorityQueue */
	{
		ArrayList<E>unity=united();
		 return new Iterator<E>() {

		        int index = 0;

		        @Override
		        public boolean hasNext()
		        {
		            return index < size();
		        }

		        @Override
		        public E next()
		        {
		            if (hasNext())
		            {
		            	E value=unity.get(index);
		                index++;
		                return value;
		            }
		            else
		            {
		            	System.out.println("the iterator has reached the limit");
		            	return null;
		            }
		        }
		 };
	}
}
