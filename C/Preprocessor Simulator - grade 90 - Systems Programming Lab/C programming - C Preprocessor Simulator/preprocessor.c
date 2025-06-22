#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "preprocessor.h"
#define MAX_LENGTH 500 //length of a line 
enum status{OUT,LEFT_SLASH,IN_COMMENT,RIGHT_STAR,IN_STRING,IN_CPP_COM,HASHTAG,I,N,C,L,U,D,E,QUOTES,SPACE,TAB};/*for the switch case status*/

int main (int argc,char *argv[])
{
    FILE *fp_name_input,*fp_name_without_comment,*fp_header,*fp_with_header;
    int c,state=OUT;
    char *file_without_comment,*file_with_header,*include,*str,*file_input_name,*file_header_name_c;
    file_input_name=malloc(strlen(argv[1])*sizeof(char));
    strcpy(file_input_name,argv[1]);//name of the first file 
    if(check_cfile(argv[1])>0)// checking if the file in the argument is a c file
        exit(1);
    fp_name_input=fopen(argv[1],"r");//the file in the argumnet "file.c"
    if(fp_name_input==NULL)
    {
        perror("myprog_inputfile");
        exit(2);
    }
    file_without_comment=malloc((strlen(argv[1])+1)*sizeof(char));
    strcpy(file_without_comment,strcat(file_input_name,"1"));
    fp_name_without_comment=fopen(file_without_comment,"w");//this is "file.c1"
    while((c=fgetc(fp_name_input))!=EOF)//this is a program which ignores comments in "file.c" and writing the code without comment in "file.c1"
      {  
        switch(state)
        {
            case OUT:
            if(c=='/')
                state=LEFT_SLASH;
            else{
                fputc(c,fp_name_without_comment);
                if(c=='\"')
                    state=IN_STRING;
            }
            break;
            case LEFT_SLASH:
            if(c=='*')
                state=IN_COMMENT;
                else if(c=='/')
                    state=IN_CPP_COM;
                else    
                {
                    fputc('/',fp_name_without_comment);
                    fputc(c,fp_name_without_comment);
                    state=OUT;
                }
            break;
            case IN_COMMENT:
                if(c=='*')
                    state=RIGHT_STAR;
            break;
            case IN_CPP_COM:
                if(c=='\n')
                {    
                    state=OUT;
                    fputc(c,fp_name_without_comment);    
                }
            break;
            case RIGHT_STAR:
                if(c=='/')
                    state=OUT;
                else if(c!='*')
                        state=IN_COMMENT;
            break;
            case IN_STRING:
                if(c=='\"')
                    state=OUT;
            fputc(c,fp_name_without_comment);
            break;
            
        }
      }
      fclose(fp_name_without_comment);//closing "file.c1"
      fclose(fp_name_input);//closing "file.c"
      
      fp_name_without_comment=fopen(file_without_comment,"r");//opening "file.c1"
      if(fp_name_without_comment==NULL)
    {
        perror("myprog_file_with_no_comment");
        exit(3);
    }
    file_without_comment[strlen(file_without_comment)-1]='2';//"file.c2"
    
    fp_with_header=fopen(file_without_comment,"w");
    state=IN_STRING;
    while(c=fgetc(fp_name_without_comment)!=EOF)//this program searching the header in "file.c1" writting the content in "file.c2"
    {
        switch(state)
        {
            case IN_STRING:
            if(c=='#')
                state=HASHTAG;
            else
            fputc(c,fp_with_header);    
            break;
            case HASHTAG:
                if(c=='i')
                    state=I;
                else
                    {
                        state=IN_STRING;
                        fputc('#',fp_with_header);
                        fputc(c,fp_with_header);
                    }
            break;
            case I:
                if(c=='n')
                    state=N;
                else
                    {
                        state=IN_STRING;
                        fputc('#',fp_with_header);
                        fputc('i',fp_with_header);
                        fputc(c,fp_with_header);
                    }
            break;
            case N:
                if(c=='c')
                    state=C;
                else
                    {
                        state=IN_STRING;
                        fputc('#',fp_with_header);
                        fputc('i',fp_with_header);
                        fputc('n',fp_with_header);
                        fputc(c,fp_with_header);
                    }
            break;
            case C:
                if(c=='l')
                    state=L;
                else
                    {
                        state=IN_STRING;
                        fputc('#',fp_with_header);
                        fputc('i',fp_with_header);
                        fputc('n',fp_with_header);
                        fputc('c',fp_with_header);
                        fputc(c,fp_with_header);
                    }
            break;
            case L:
                if(c=='u')
                    state=U;
                else
                    {
                        state=IN_STRING;
                        fputc('#',fp_with_header);
                        fputc('i',fp_with_header);
                        fputc('n',fp_with_header);
                        fputc('c',fp_with_header);
                        fputc('l',fp_with_header);
                        fputc(c,fp_with_header);
                    }
            break;
            case U:
                if(c=='d')
                    state=D;
                else
                    {
                        state=IN_STRING;
                        fputc('#',fp_with_header);
                        fputc('i',fp_with_header);
                        fputc('n',fp_with_header);
                        fputc('c',fp_with_header);
                        fputc('l',fp_with_header);
                        fputc('u',fp_with_header);
                        fputc(c,fp_with_header);
                    }
            break;
            case D:
                if(c=='e')
                    state=E;
                else
                    {
                        state=IN_STRING;
                        fputc('#',fp_with_header);
                        fputc('i',fp_with_header);
                        fputc('n',fp_with_header);
                        fputc('c',fp_with_header);
                        fputc('l',fp_with_header);
                        fputc('u',fp_with_header);
                        fputc('d',fp_with_header);
                        fputc(c,fp_with_header);
                    }
            break;
            case E:
                if(c=='\"')
                    state=QUOTES;
               else if(c==' '||c=='\t')
                        state=SPACE;
                    else{
                        state=IN_STRING;
                        fputc('#',fp_with_header);
                        fputc('i',fp_with_header);
                        fputc('n',fp_with_header);
                        fputc('c',fp_with_header);
                        fputc('l',fp_with_header);
                        fputc('u',fp_with_header);
                        fputc('d',fp_with_header);
                        fputc('e',fp_with_header);
                        fputc(c,fp_with_header);
                    }
            break;
            case SPACE:
                if(c=='\"')
                    state=QUOTES;
               else if(c==' '||c=='\t')
                        {
                            state=SPACE;
                            fputc(c,fp_with_header);
                        }
                    else{
                        state=IN_STRING;
                        fputc(c,fp_with_header);
                    }
             
                    
            case QUOTES:
                include=malloc(sizeof(char));
                *include=c;
                str=malloc(MAX_LENGTH*sizeof(char));
                file_header_name_c=malloc(sizeof(char));
                while(c=fgetc(fp_name_without_comment)!=EOF)
                    {
                        if(c=='"')// the header in "file.c1" is found
                        {
                            strcpy(include,str);
                            fp_header=fopen(include,"r");// opening the header in "file.c1"
                            if(fp_header==NULL)
                             {
                                perror("myprog_file_header");
                                exit(4);
                             }
                            while(c=fgetc(fp_header)!=EOF)// writting the content of the header in "file.c2"
                            {
                                fputc(c,fp_with_header);
                            }
                            fclose(fp_header);// closing the header
                            break;
                        }
                        else        // searching for the header name in "file.c1"
                        {
                            *file_header_name_c=c;
                            strcpy(str,include);
                            include=realloc(include,(strlen(include)*sizeof(char)));
                            strcat(str,file_header_name_c);
                        }
                    }
                
            state=IN_STRING;
            break;
        }
    }
    fclose(fp_with_header);// closing "file.c2"
    fclose(fp_name_without_comment);// closing "file.c1"
  return 0;
}
int check_cfile(char *b)/*checking if the file is a c file*/
{
    if(strlen(b)>2)
    {
        if (b[strlen(b)-2]=='.'&&b[strlen(b)-1]=='c')
            return 0;
        else {
             printf("the file isn't a c format file:%s",b);
             return 1;
        }
    }
    else
    {
        printf("the file has no name/this is not a file:%s",b);
        return 2;
    }
    
}

