#include <stdio.h>
#include "count_odd_bits.h"//a header which declare the count_odd_bits function


int main()
{
	long int x;
	
	printf("Hello, please insert a number\n");
	scanf("%lu",&x);//input from the user expecting unsigned long integer and put it in the variable x
	while(x!=EOF)// checking if the input isn't EOF
	{
		printf("Your number is: %lu\nthe number of E-zugi odd bits is: %lu\n",x,count_odd_bits(x));//prints the input number and the counter of E-zugi odd bits 
		printf("\nplease insert a number\n");//asking for another input 
		scanf("%lu",&x);//put the input in the variable x and expecting for unsigned long intege
	}
	return 0;
}


long int count_odd_bits(unsigned long int num)//gets a unsigned long integer,counts the E-zugi odd bits and return the counter
{
	long int count =0;
	int odd=1;
	int i=0;
	while(odd!=0)
	{
		if((num&odd)&&(i%2==1))
			count++;
		odd=odd<<1;
		i++;
		
	}
	return count;
}
