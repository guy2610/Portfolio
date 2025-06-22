#ifndef READ_CSV_H
#define READ_CSV_H

#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <stdexcept>

std::vector<std::string> splitToCell(const std::string& s);// Splits a comma-separated string into a vector of strings

double valueChecker(const std::string& s);// Converts and validate a string to a double, returns 0 if conversion fails

void sumRow(std::vector<std::vector<std::string>>& row);// Sums the values in each row and prints the result

int maxNum(std::vector<std::vector<std::string>>& row);// Returns the maximum number of columns in the 2D vector

void sumColumn(std::vector<std::vector<std::string>>& row);// Sums the values in each column and prints the result

#endif // READ_CSV_H
#pragma once
