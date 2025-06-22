#include "Header.h"
#include "util.h"
#include "data.h"
#include "FirstPass.h"
#include "label.h"
#include "ScondPass.h"
#define MAX_LABEL_LEN 31/*���� ������*/
#define MAX_ARR_LEN 150/*���� �������*/
#define WRD_ARR_BITS_LEN 12/*���� ������� �� ������*/
#define TABLE_SIZE (sizeof(lookuptable)/sizeof(code))
#define MAX_LINE_LENGTH 80
#define IcStart 100
bool fileProcess(char* fileName);
int main(int argc, char* argv[])
{
    char *fileName=malloc(strlen(argv[0])*sizeof(char)));
    strcpy(fileName,argv[0]);
	if(fileProcess(fileName)==F)
		printf("%s is failed\n");




	return 0;

}
bool fileProcess(char* fileName)
{
	char temp ;
	int IC = IcStart, DC = 0;
	int ICF, DCF;
	lblb bhead;
	bool succes = T;
	bhead.head=NULL;
	FILE* currFile;
	char* fullFileName = malloc(strlen(fileName)*sizeof(char));
	line currLine;
	strcpy(fullFileName, fileName);
	strcat(fullFileName, ".as");
	currFile = fopen(fullFileName, "r");
	if (!currFile)
	{
		printf("Error: The file\"%s.as\" could not be opened for reading", fileName);
		free(fullFileName);
		return F;
	}
	init_cmd_array();
    init_data_array();
	currLine.file_name = fullFileName;
	currLine.info = (char*)malloc(MAX_LINE_LENGTH * sizeof(char));
	currLine.line_number = 0;
	while (fgets(currLine.info, MAX_LINE_LENGTH + 1, currFile))
	{

		currLine.line_number += 1;
		if (!strchr(currLine.info, '\n')&& (!feof(currFile)))
		{
			succes = F;
			while (temp != EOF && temp != '\n')
			{
				temp = fgetc(currFile);
			}
		}
		else
		{
            bhead=proceesLine(currLine, &DC, &IC, bhead.head);
         	if (bhead.b== F)
			{
				succes = F;
			}
		}
	}

if (succes==T)
	{
		temp = ' ';
		ICF = IC;
		DCF = DC;
		IC = IcStart;
		DC = 0;
		currLine.line_number = 0;
        /*updateAdrressData(IC,DCF);
        updateAdrresslabel(ICF-100,bhead.head);*/

        rewind(currFile);
		while (fgets(currLine.info, MAX_LINE_LENGTH + 1, currFile))
		{
			currLine.line_number += 1;
			if (!strchr(currLine.info, '\n') && (!feof(currFile)))
			{
				succes = F;
				while (temp != EOF && temp != '\n')
				{
					temp = fgetc(currFile);
				}

			}
			else
			{
			    bhead=SecondProceesLine(currLine, &DC, &IC, bhead.head);
				if ( bhead.b== F)
				{
					succes = F;
				}
			}
		}

		if (succes==T) {
			fclose(currFile);
			printing_to_files(fileName,bhead.head ,IC-IcStart,DCF);
			data_free_arr();
			cmd_free_arr();
			label_free(bhead.head);
		}
	}


	return succes;
}
