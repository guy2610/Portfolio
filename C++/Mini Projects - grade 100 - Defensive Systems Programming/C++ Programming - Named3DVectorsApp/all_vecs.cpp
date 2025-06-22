#include <iostream>
#include<map>
#include<string>
#include <typeinfo>
#include <limits>
#include "my_vec.h"
#include "all_vecs.h"//Documentation in Header file
#define NUMOFNAMES 10
#define VARIABLES 3
    all_vecs::all_vecs() {}
    all_vecs::~all_vecs() {}
    my_vec* all_vecs::searchInVecs(const std::string& s) {
        auto it = this->mp.find(s);
        if (it != this->mp.end()) {
            std::cout << s << "'s vector exists: " << it->second << std::endl;
            return &it->second;
        }
        else {
            return nullptr;
        }
    }
    void all_vecs::insertInVecs(const std::string& s, const my_vec& vec) {
        if (searchInVecs(s) == nullptr) {
            this->mp.insert(std::make_pair(s, vec));
            std::cout << s << " is added" << std::endl;
        }
        else
        {
            std::cout << "you can enter unique names only" << std::endl;
        }
    }
    void all_vecs::changeVecInVecs(const std::string & s, my_vec & vec) {
        my_vec* vc;
        vc = searchInVecs(s);
        if (vc == nullptr) {
            std::cout << s << "please try different name" << std::endl;
        }
        else
        {
            vc = &vec;
            std::cout << s << " has been changed to "<<vec << std::endl;
        }
    }
    void all_vecs::removeVecFromVecs(const std::string& s) {
        my_vec* vc;
        vc = searchInVecs(s);
        if (vc == nullptr) {
            std::cout << s << " can not be removed" << std::endl;
        }
        else
        {
           this->mp.erase(s);
            std::cout << s << " has been removed" << std::endl;
        }
    }
    void all_vecs::printVecs() {
        auto it = this->mp.begin();
        while (it != this->mp.end())
        {
            std::cout << *(& it->first) << " " << *( & it->second);//std::endl
            it.operator++();
        }
    }
    void all_vecs::emptyVecs() {
        this->mp.clear();
        std::cout << "all_vecs has been cleared" << std::endl;
    }
int isFirstCharacterValid(const std::string& s) {
    char c=s[0];
    if ((c>='A'&&c<='Z')|| (c >= 'a' && c <= 'z'))
    {
        return 1;
    }
    else
    {
        return 0;
    }
}
bool valueChecker(const std::string& s)
{
    double num = 0;
    try {
        // Attempt to convert the string to a double
        std::size_t pos;
        num = std::stod(s, &pos);

        // Ensure the entire string was converted (i.e., no leftover characters)
        if (pos == s.length())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    catch (const std::invalid_argument& e) {
        // The string was not a valid double
        return false;
    }
    catch (const std::out_of_range& e) {
        // The string represented a number out of the range of double
        return false;
    }

}
all_vecs inputFromUserForVecs() {
    all_vecs al = all_vecs();
    std::string str_name, input_variable,value_checker;
    enum options { ENTER_EMPTY=1,ENTER_NUMBERS=2 };
    double arr_num[VARIABLES];
    for (int i = 0; i < NUMOFNAMES; i++)
    {
        if (i == 0){
            std::cout << "please enter "<<i+1<<"st name" << std::endl;
        }
        else if(i<4) {
            std::cout << "please enter " << i + 1 << "nd name" << std::endl;
        }
        else {
            std::cout << "please enter " << i + 1 << "th name" << std::endl;
        }
        std::cin >> str_name;
        while (!isFirstCharacterValid(str_name)|| (al.searchInVecs(str_name) != nullptr))
        {
            std::cout << "please enter a name start with a letter and you can enter unique names only" << std::endl;
            std::cin >> str_name;
        }
        std::cout << "\nwhich option do you want to enter the vector:" << std::endl;
        std::cout << "1) instant initialization" << std::endl;
        std::cout << "2) setting numbers" << std::endl;
        std::cin >> input_variable;
        while (input_variable !="1"&& input_variable !="2") {
            std::cout <<"please enter a number from the options" << std::endl;
            std::cin >> input_variable;
        }
        switch (std::stoi(input_variable))
        {
        case ENTER_EMPTY:
            al.insertInVecs(str_name, my_vec());
            break;
        case ENTER_NUMBERS:
            for (int j = 0;j < VARIABLES;j++) {
                if (j == 0)
                {
                    std::cout << "enter the " << j + 1 << "'st number" << std::endl;
                    std::cin >> value_checker;
                    while (!valueChecker(value_checker)) {
                        std::cout << "this is not a number please try again" << std::endl;
                        std::cin >> value_checker;
                    }
                    arr_num[j] =stod(value_checker);
                }
                else
                {
                    std::cout << "enter the " << j + 1 << "'nd number" << std::endl;
                    std::cin >> value_checker;
                    while (!valueChecker(value_checker)) {
                        std::cout << "this is not a number please try again" << std::endl;
                        std::cin >> value_checker;
                    }
                    arr_num[j] = stod(value_checker);
                }
            }
            al.insertInVecs(str_name, my_vec(arr_num[0], arr_num[1], arr_num[2]));
            break;
        default:
            std::cout << "please choose from the list of the options" << std::endl;
            break;
        }
        
    }
    return al;
}



int main() {
    all_vecs al = inputFromUserForVecs();
    std::cout << "\nwhich user do you want to print out?" << std::endl;
    std::string str;
    std::cin >> str;
    while (al.searchInVecs(str) == nullptr)
    {
        std::cout << "name doesnt exist please try again" << std::endl;
        std::cin >> str;
    }
    std::cout << "\nthis is all the list" << std::endl;//to show only for the tester for comfort
    al.printVecs();
	return 0;
}