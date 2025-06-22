import java.util.Iterator;
import java.util.Random;

public class StringMain {

	public static void main(String[] args) {/*tester for String ,using random inputs ,prints the action it does*/
		// TODO Auto-generated method stub
		Random rd =new Random();
		int maxPriorityForRandom=1+rd.nextInt(10);
		int maxPriority=maxPriorityForRandom-1;
		int priority,num,word_length,k;
		char ch;
		String s="";
		int max_in_priority=0;
		if(maxPriority==0) 
		{
			System.out.println("maxPriority is 0 as result of random number -> not valid argument ");
			System.exit(1);
		}
		PriorityQueue pq=new PriorityQueue(maxPriority);
		
		for(int i=0;i<maxPriority;i++)
		{
			num=rd.nextInt(5);
			if(num>max_in_priority)
				max_in_priority=num;
			while(num>0)
				{
					priority=rd.nextInt(maxPriority);
					word_length=5+rd.nextInt(3);
					s=makeString(word_length);
					pq.add(s, priority);
					num--;
				}
			
		}
		Iterator<String> it1 = pq.iterator();
		System.out.println("this is the maxPriority: "+maxPriority+". max words in a priority is: "+max_in_priority);
		System.out.println("\nbefore poll 3 times:");
		for(int i=0;i<pq.size();i++) 
		{
			System.out.println(it1.next());
		}
		String test;
		System.out.println("\nafter poll 3 times:");
		System.out.println(pq.poll());
		test=(String) pq.poll();
		System.out.println(test);
		System.out.println(pq.poll());
		System.out.println("\ndoes \""+test+"\" exist after poll: "+pq.contains(test));
		k=(maxPriority!=0)?rd.nextInt(maxPriority):1;
		pq.add(test, k);
		Iterator<String> it2 = pq.iterator();
		for(int i=0;i<pq.size();i++) 
		{
			System.out.println(it2.next());
		}
		System.out.println("\ndoes \""+test+"\" exist afer add to priority "+k+": "+pq.contains(test));
		pq.remove(test);
		System.out.println("\ndoes \""+test+"\" exist after removing from priority "+k+": "+pq.contains(test));
		
		
	}
	public static String makeString(int length)/*making a random string*/
	{
		Random rd =new Random();
		char ch;
		String s="";
		while(length>0)
		{
			ch=(char) (97+rd.nextInt(26));
			s=s+ch;
			length--;
		}
		return s;	
	}
}
