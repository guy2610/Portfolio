#include <iostream>
#include "my_vec.h"//Documentation in Header file

my_vec::my_vec(double x, double y, double z) :x(x), y(y), z(z) {}
my_vec::my_vec() :x(0), y(0), z(0) {}
my_vec::my_vec(const my_vec& myvec) :x(myvec.x), y(myvec.y), z(myvec.z) {}
my_vec::~my_vec() { }
double my_vec::getX() const { return x; }
double my_vec::getY() const { return y; }
double my_vec::getZ() const { return z; }
void my_vec::setX(double x) { this->x = x; }
void my_vec::setY(double y) { this->y = y; }
void my_vec::setZ(double z) { this->z = z; }

std::ostream& operator<<(std::ostream& os, const my_vec& vec)
{
	os <<"("<<vec.getX()<<","<<vec.getY()<<"," << vec.getZ() << ")" << std::endl;
	return os;
}
my_vec operator+(const my_vec& vec1, const my_vec& vec2)
{
	return  my_vec(vec1.getX()+vec2.getX(), vec1.getY() + vec2.getY(), vec1.getZ() + vec2.getZ());
}
my_vec operator-(const my_vec& vec1, const my_vec& vec2)
{
	return  my_vec(vec1.getX() - vec2.getX(), vec1.getY() - vec2.getY(), vec1.getZ() - vec2.getZ());
}
double operator*(const my_vec& vec1, const my_vec& vec2)
{
	return  vec1.getX() * vec2.getX()+ vec1.getY() * vec2.getY()+ vec1.getZ() * vec2.getZ();
}
my_vec operator*(const my_vec& vec1, double scalar)
{
	return  my_vec(vec1.getX() * scalar , vec1.getY() * scalar , vec1.getZ() * scalar);
}
my_vec operator*(double scalar, const my_vec& vec1)
{
	return  operator*(vec1,scalar);
}
my_vec& my_vec::operator=(const my_vec& myvec)
{
	if (this!=&myvec)
	{
		this->x = myvec.getX();
		this->y = myvec.getY();
		this->z = myvec.getZ();
	}
	return *this;
}

