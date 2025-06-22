#include <stdio.h>
#include "count_bits.h"//the header of count_bits function


int main()
{
	long int x; //the input from the user.
	printf("Hello, please insert a number\n");
	scanf("%li",&x);//expecting a long type input and store it in x.
	printf("\nYour number is: %li\nthe number of bit which equals to 1 is: %li\n",x,count_bits(x));//print the input number and the counter of odd bits by using count_bits function.
return 0;
}
 long int count_bits(long int num)//gets a long type number ,counts the odd bits in the number with bitwise actions, and returns it.
{
	long int count =0;
	int i=1;
	while(i!=0)
	{
		if(num&i)
			count++;
		
		i=i<<1;
	}
	return count;
}
