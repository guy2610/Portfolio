#ifndef MY_VEC_H
#define MY_VEC_H

#include <iostream>

// Class definition for my_vec
class my_vec
{
private:
    double x;
    double y;
    double z;

public:
    // Constructors
    my_vec();//creating vector with 3 values and the values equal to zero
    my_vec(double x, double y , double z);//creating vector with 3 values and the values equal to x,y,z respectively. we can unite the two constructors by writing my_vec(double x=0, double y=0 , double z=0);
    my_vec(const my_vec& myvec);//copy-constructor
    my_vec& operator=(const my_vec& myvec);// operator = overload

    // Destructor
    ~my_vec();

    // Accessors
    double getX() const;
    double getY() const;
    double getZ() const;

    // Mutators
    void setX(double x);
    void setY(double y);
    void setZ(double z);
};

// Operator overloads
std::ostream& operator<<(std::ostream& os, const my_vec& vec);//Printing vector to the console
my_vec operator+(const my_vec& vec1, const my_vec& vec2);//Vector addition
my_vec operator-(const my_vec& vec1, const my_vec& vec2);//Vector subtraction
double operator*(const my_vec& vec1, const my_vec& vec2);//Inner product
my_vec operator*(const my_vec& vec1, double scalar);//If the multiplication of a vector in a scalar is shown in reverse
my_vec operator*(double scalar, const my_vec& vec1);//Multiplies between a vector and a scalar


#endif // MY_VEC_H
