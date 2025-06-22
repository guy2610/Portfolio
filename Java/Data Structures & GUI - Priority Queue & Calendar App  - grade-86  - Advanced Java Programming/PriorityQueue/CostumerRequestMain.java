import java.util.Iterator;
import java.util.Random;
public class CostumerRequestMain {

	public static void main(String[] args) {/*tester for CostumerRequest,using random inputs ,prints the action it does*/
		// TODO Auto-generated method stub
		Random rd =new Random();
		String name,request;
		int name_length;
		int request_length;
		int id;
		int maxPriorityForRandom=1+rd.nextInt(10);
		int maxPriority=maxPriorityForRandom-1;
		int priority,num,word,k;
		CostumerRequest cr;
		int max=0;
		if(maxPriority==0) 
		{
			System.out.println("maxPriority is 0 as result of random number -> not valid argument ");
			System.exit(1);
		}
		PriorityQueue pq=new PriorityQueue(maxPriority);
		
		for(int i=0;i<maxPriority;i++)
		{
			num=rd.nextInt(5);
			if(num>max)
				max=num;
			while(num>0)
				{
					
					priority=rd.nextInt(maxPriority);
					name_length=rd.nextInt(10);
					name=makeString(name_length);
					request_length=rd.nextInt(20);
					request=makeString(request_length);
					id=rd.nextInt(100);
					cr=new CostumerRequest(name,id,request);
					pq.add(cr, priority);
					num--;
				}
			
		}
		Iterator<CostumerRequest> it1 = pq.iterator();
		System.out.println("this is the maxPriority: "+maxPriority+". max Costumers Requests in a priority is: "+max);
		System.out.println("\nbefore poll 3 times:");
		for(int i=0;i<pq.size();i++) 
		{
			System.out.println(it1.next());
		}
		CostumerRequest test,test2;
		System.out.println("\nafter poll 3 times:");
		System.out.println(pq.poll().toString());
		test=(CostumerRequest) pq.poll();
		System.out.println(test.toString());
		System.out.println(pq.poll().toString());
		System.out.println("\ndoes \""+test+"\" exist after poll: "+pq.contains(test));
		k=(maxPriority!=0)?rd.nextInt(maxPriority):1;
		pq.add(test, k);
		Iterator<CostumerRequest> it2 = pq.iterator();
		System.out.println("\ndoing iterator");
		for(int i=0;i<pq.size();i++) 
		{
			System.out.println(it2.next());
		}
		System.out.println("\ndoes \""+test+"\" exist afer add to priority "+k+": "+pq.contains(test));
		pq.remove(test);
		System.out.println("\ndoes \""+test+"\" exist after removing from priority "+k+": "+pq.contains(test));
		pq.add(test , k);
		test2=new CostumerRequest("aaaa",test.getId(),test.getRequest());
		pq.add(test2 , k);
		System.out.println("\ndoes \""+test2.toString()+"\"  has equal request and id to? \""+test.toString()+"\" => "+test.equals(test2));
		test=(CostumerRequest) pq.poll();
		System.out.println("\ndoes \""+test2.toString()+"\"  has equal request and id to? \""+test.toString()+"\" => "+test.equals(test2));
		
		
		
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
