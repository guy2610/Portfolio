#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <stdexcept>
#include "read_csv.h"//Documentation in Header file

std::vector<std::string> splitToCell(const std::string& s){
	std::vector<std::string> v;
	const char* c = s.c_str();  // Get a pointer to the string's internal character array
	std::string str;
	while (*c != '\0') {        // Loop until the null terminator is reached
		if (*c==',')
		{
			v.push_back(str);
			str = "";
		}
		else
		{
			str += *c;
		}
		c++;                    // Move to the next character
	}
	if (str!="")
	{
		v.push_back(str);
		str = "";
	}

	return v;
}
double valueChecker(const std::string& s)
{
	double num=0;
	try {
		// Attempt to convert the string to a double
		std::size_t pos;
		num = std::stod(s, &pos);

		// Ensure the entire string was converted (i.e., no leftover characters)
		if (pos == s.length())
		{
			return num;
		}
		else
		{
			return 0;
		}
	}
	catch (const std::invalid_argument& e) {
		// The string was not a valid double
		return 0;
	}
	catch (const std::out_of_range& e) {
		// The string represented a number out of the range of double
		return 0;
	}

}
void sumRow(std::vector<std::vector<std::string>>& row) {
	double sum=0;
	for (int i = 0; i < row.size(); i++)
	{
		for (int j = 0; j < row[i].size(); j++)
		{
			sum += valueChecker( row[i][j]);
		}
		std::cout << "Total of line: " << sum<<std::endl;
		sum = 0;
	}
}
int maxNum(std::vector<std::vector<std::string>>& row) {
	int max = row.size();
	for (int i = 0; i < row.size(); i++)
	{
		if (max< row[i].size())
		{
			max = row[i].size();
		}
	}
	
	return max;
}
void sumColumn(std::vector<std::vector<std::string>>& row) {
	double sum = 0;
	std::string s;
	int max_num= maxNum(row);
	int index = 0;
	std::cout << "Sums of columns:" << std::endl;
	bool ind_for_string = false;
	while (index <= max_num) {
		for (int i = 0; i < row.size(); i++)
		{
			try
			{
				if (index<row[i].size())
				{
					s = row.at(i).at(index);//if the coordinates out of range than catch the exception
					sum += valueChecker(s);
					ind_for_string = true;
				}
				
			}
			catch (const std::exception&)
			{
				//do nothing
			}
		}
		index++;
		if (ind_for_string==true)
		{
			std::cout << sum << " ";
		}
		ind_for_string = false;
		sum = 0;
	}

}
int main()
{
	std::fstream my_file;
	std::string line, cell;
	std::vector<std::string>column;
	std::vector<std::vector<std::string>>row;
	my_file.open("example4.csv",std::ios::in);
	if (!my_file.is_open()) {
		std::cerr << "Failed to open file" << std::endl;
		return 1; // exit error 1
	}
	while (std::getline(my_file,line))
	{
		//std::cout << line << std::endl;
		column = splitToCell(line);
		row.push_back(column);
	}
	sumRow(row);
	sumColumn(row);
	my_file.close();
	return 0;
}
