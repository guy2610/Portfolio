
public class Ex14 {
	/**
	 * A class used to solve Mmn 14
	 * @author Guy Even
	 */
	
		
		/** 
		 *  * question1
		 *   this answer has efficiency O(n) time/ O(1) place
		 *   the method gets an array of coins 
		 * uses 3 private methods :
		 * 1.whichGame
		 * 2.zugi
		 * 3.ezugi
		 *  the method decides how to play the game that amir will win the game or will tie with tamar.
		 * @param array of coins
		 * @prints the movements of amir and tamar and 
		 * @prints the sum of each one of them
		 */
		/////////////////////////question1////////////////////////

		public static void win(int []arr)
		{
			int amirSum=0,tamarSum=0;
			int left=0;
			int right=arr.length -1;
			if(whichGame(arr)==2)
				zugi(arr,amirSum,tamarSum,left,right);
			if(whichGame(arr)==1)
				ezugi(arr,amirSum,tamarSum,left,right);
			if(whichGame(arr)==0)
				zugi(arr,amirSum,tamarSum,left,right);
		}
		/**
		 * the method get an array ,calculate the the summary of the Zugi  and Ezugi indexes of the array
		 * * @param array of coins
		 *  @return A value which game to play with the higher summary 
		 */
		private static int whichGame(int [] arr )
		{
			int  zugiSum=0,ezugiSum=0;
			for(int i=0;i<arr.length;i++)
			{
				if (i%2==0)
					zugiSum+=arr[i];	
				else 
					ezugiSum+=arr[i];	
			}
			if (zugiSum==ezugiSum)
				return 0;
			if (zugiSum>ezugiSum)
				return 2;
			else return 1;
			
			
			
		}
		
		private static void zugi(int [] arr,int amirSum,int tamarSum,int left,int right )
		{
			while(left<right)
			{
				if(right%2==0)
				{
					amirSum+=arr[right];
					System.out.println("Amir took "+arr[right]);
					right--;
				}
				else
				{
					amirSum+=arr[left];
					System.out.println("Amir took "+arr[left]);
					left++;
				}
					if(arr[right]>=arr[left])
					{
						tamarSum+=arr[right];
						System.out.println("Tamar took "+arr[right]);
						right--;

					}
					else
					{
						tamarSum+=arr[left];
						System.out.println("Tamar took "+arr[left]);
						
						left++;
					

				}
				
				}
				System.out.println("Final score:");
				System.out.println("Amir total "+ amirSum);
				System.out.println("Tamar total "+ tamarSum);
			
		}
		
		private static void ezugi(int [] arr,int amirSum,int tamarSum,int left,int right )
		{
			while(left<right)
			{
				
			if(right%2==1)
			{
				amirSum+=arr[right];
				System.out.println("Amir took "+arr[right]);
				right--;
			}
			else
			{
				amirSum+=arr[left];
				System.out.println("Amir took "+arr[left]);
				left++;
			}
				if(arr[right]>=arr[left])
				{
					tamarSum+=arr[right];
					System.out.println("Tamar took "+arr[right]);
					right--;

				}
				else
				{
					tamarSum+=arr[left];
					System.out.println("Tamar took "+arr[left]);
					
					left++;
				

			}
			
			}
			System.out.println("Final score:");
			System.out.println("Amir total "+ amirSum);
			System.out.println("Tamar total "+ tamarSum);
		}
		
		
 /////////////////////question2////////////////////////////////////
		
//1.The method "what" gets an array and a number,searching for 2 indexes in the array that if we sum up the numbers between them it will equal to the number that he method  got.
	//if it will find the 2 indexes ,it will print the two indexes and return true ,if not it will print no and return false
	//2. this answer has efficiency O(n^3) time/ O(n) place
	//3. answer below
	//4. this answer has efficiency O(n^2) time/ O(n) place
	/** 
	 *  * question2
	 *   this answer has efficiency O(n^2) time/ O(n) place
	 *   the method gets an array and a number 
	 * @param array
	 * @param number
	 * @return true if the indexes with the sum between them is the number
	 *  @return false if the didnt found 
	 *  @prints the indexes if the method found them
	 *  @prints if it doesnt found prints No
	 */
		public static boolean what (int [] a,int num)
		{
			int sum=0;
			int []arr=new int [a.length];
			for(int i=0;i<a.length;i++)
			{	
				if(a[i]==num)
				{
					System.out.println("between " +i+ " and "+i);  
					return true; 
				}
				else {
					sum+=a[i];
					arr[i]=sum;
				}
			}
			if(sum<num )
			{
				System.out.println("No ");  
				return false;
			}
			else {
				if(sum==num )
					{
					System.out.println("between " +(0)+ " and "+(a.length-1));  
				return true;
				}
				else
				for(int i=0;i<arr.length;i++)
				{
					for(int j=i;j<arr.length;j++)
					{



						if(-arr[i]+arr[j]==num)
						{System.out.println("between " +(i+1)+ " and "+j);  
						return true;      
						}  }   
				} 

				System.out.println("No ");   
				return false; 




			}}

 /////////////////////////////question3////////////////////////////////	
	/** 
	 * question3
	 
	* the method gets a matrix and a number
	 * @param mat-matrix
	 * @param num-number which presents the difference between two adjacent numbers
	 * @return the length of longest slope we can find in the matrix with the difference num between two adjacent numbers
	 */
		// i didnt finish it 
	public static int longestSlope (int [][] mat, int num)
	{
		return 6;
		
		
	}
/////////////////////////////question4/////////////////////////////////////
	/** 
	 * question4
	 
	 * the method gets length of the array and a maximum 
	 * uses 5 private methods:
	 * 1.howManySorted-overloading method
	 * 2.toOne-return the array with the number 1 
	 * 3.position-arrange the array 
	 * 4.isup-checks if the array is sorted upwards
	 * 5.areEqualToMax-checks if this array is the last that we can make (only the numbers max inside )
	 * @param n-length of the array
	 * @param max
	 * @return the number of the arrays that we can make with the length of n,1-max in every position and the arrays must be sorted-upwards
	 */
	public static int howManySorted(int n, int max) 
	{	
		int []arr=new int [n];


		toOne(arr,max,0);
		return howManySorted(n,max,arr,0,n-1);

	}
	private static int[] toOne(int []arr,int max,int i)
	{
		if(i==arr.length)
			return arr;
		arr[i]=1;
		return toOne(arr,max,i+1);

	}
	private static int howManySorted(int n, int max,int []arr,int i,int pos)
	{
		if(areEqualToMax(arr,0,max))
			return 1;
		if(i==arr.length-1)

		{

			arr[i]++;
			i=0;
			if(isup(arr,0,0))

				return 1+howManySorted(n,max,arr,i,pos);
			return howManySorted(n,max,arr,i,pos);
		}

		if(arr[pos]==max)
		{
			position(arr,pos,max);

		}




		return howManySorted(n,max,arr,i+1,pos);
	}
	private static void position(int []arr,int i,int max)
	{
		if(i==0)
			return;
		if(arr[i-1]!=max)
		{arr[i-1]++;
		toOne(arr,max,i);
		}
		else
			position(arr, i-1, max);

	}

	private static boolean isup(int[]arr,int before,int i)
	{
		if(i==arr.length)
			return true;


		if (arr[before]>arr[i])
			return false;
		if(i==0)
			return isup(arr,before,i+1);
		else
			return isup(arr,before+1,i+1);
	}
	private static boolean areEqualToMax(int[]arr,int i,int max)
	{

		if (i<arr.length&&arr[i]!=max)
			return false;
		if(i==arr.length)
			return true;
		return areEqualToMax(arr,i+1,max);

	}

}


