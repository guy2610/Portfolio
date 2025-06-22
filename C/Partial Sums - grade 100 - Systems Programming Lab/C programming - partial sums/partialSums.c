#include <stdio.h>
#include <stdlib.h>
#include "partialSums.h"//including the header file

int main()
{
    int *pi=NULL;//pointer
    int length;//length of the array
    int i;//an index for the for's
    int *arr;//pointer of the array
    printf("please enter the length of the array:");
    scanf("%d",&length);//gets the requested array's length
    printf("%d\n",length);//print the length in the output file
    arr=(int*)calloc(length,sizeof(int));// allocating memory with 0's with the requested array's length size
    for(i=0;i<length;i++)//gets the numbers for the array
    {
	printf("enter a number:");
        scanf("%d",&arr[i]);
	printf("%d\n",arr[i]);//print the value of the cell in the output file
    }
    printf("this is your array:"); 
    for(i=0;i<length;i++)// print the array of the numbers the user put
    {
         printf(" %d",arr[i]);
    }
    pi=partialSums(arr,length);//put the partial sums array in the pointer
    printf("\nthis is your partial sums array:");
    for(i=0;i<length;i++)// print the partial sums array
    {
         printf(" %d", pi[i]);
    }
    printf("\n");
    free(pi) ;//deallocate the memory of the pointer
    free(arr) ;//deallocate the memory of the array
    return 0;
}
int*  partialSums(int arr[], int arr_length)//gets an array and his length,calculate the partail sums and return a pointer with the partial sums' array
{
    int *pi;
    int i;
    pi=(int*)calloc(arr_length,sizeof(int));//allocating memory with 0's for the pointer,because the original array can't be manipulated
    for(i=0;i<arr_length;i++)
    {
    pi[i]=i==0?arr[i]:pi[i-1]+arr[i];
    }
    return pi;
  
}
