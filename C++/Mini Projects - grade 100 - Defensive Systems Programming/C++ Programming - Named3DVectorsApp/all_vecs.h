#ifndef ALL_VECS_H
#define ALL_VECS_H

#include <iostream>
#include<map>
#include<string>
#include <typeinfo>
#include <limits>
#include "my_vec.h"
#include "all_vecs.h"
#define NUMOFNAMES 10//how many names will be in the data structure
#define VARIABLES 3// the amout of variables for my_vec,cannot be changed unless we implement the constructor my_vec(double x=0, double y=0 , double z=0);

class all_vecs {
private:
    std::map<std::string, my_vec> mp;// our data structure using a map to store the names and their my_vec's

public:
    all_vecs();//default constructor
    ~all_vecs();//default destructor

    my_vec* searchInVecs(const std::string& s);//search method for our data structure and return the a pointer for my_vec if exist, otherwise nullptr
    void insertInVecs(const std::string& s, const my_vec& vec);//insert method for our data structure, inserting a string and my_vec
    void changeVecInVecs(const std::string& s, my_vec& vec);//change method for our data structure, for a name in the data structure we can change the my_vec
    void removeVecFromVecs(const std::string& s);//remove method for our data structure, we can remove a name in the data structure with his my_vec
    void printVecs();//print method in our data structure, print all the keys and their values from our data structure
    void emptyVecs();//empty method in our data structure, initialize our data structure to be empty
};

int isFirstCharacterValid(const std::string& s);//checks if the string in the name begins with a letter from the ABC alphabet
bool valueChecker(const std::string& s);// Validate if a string to a double, returns false if conversion fails, return true if succeeded
all_vecs inputFromUserForVecs();//get input from the user, validate the input and adds it to our data structure

#endif // ALL_VECS_H
#pragma once
