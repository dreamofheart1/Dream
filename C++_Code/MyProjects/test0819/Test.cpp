#include <iostream.h>
//#include <string>
//using namespace std;

class Complex {
private :
	double real , imag;
	
public :
	Complex(double a=0,double b=0){
		this->real=a;
		this->imag=b;
	}
	void display(){
		cout<<"¸´Êý: "<<this->real<<" + "<<imag<<"i"<<endl;
	}
	friend Complex operator +(Complex &,Complex &);
	friend Complex operator +(double,Complex &);
	friend Complex operator +(Complex &,double);

	friend Complex operator -(Complex &,Complex &);
	friend Complex operator -(double,Complex &);
	friend Complex operator -(Complex &,double);
};

Complex operator+(Complex &a,Complex &b){
	cout<<"a ="<<a.real<<"     b ="<<b.real<<endl;
	return Complex(a.real+b.real,a.imag+b.imag);
}
Complex operator+(double a,Complex &b){
	cout<<"a ="<<a<<"     b ="<<b.real<<endl;
	return Complex(a+b.real,b.imag);
}
Complex operator+(Complex &a,double b){
	return Complex(a.real+b,a.imag);
}

Complex operator-(Complex &a,Complex &b){
	cout<<"a ="<<a.real<<"     b ="<<b.real<<endl;
	return Complex(a.real -b.real,a.imag-b.imag);
}
Complex operator-(double a,Complex &b){
	cout<<"a ="<<a<<"     b ="<<b.real<<endl;
	return Complex(a-b.real,b.imag);
}
Complex operator-(Complex &a,double b){
	return Complex(a.real-b,a.imag);
}

void main(){
	Complex c1(2,3), c2(1,2), res;
	c1.display();
	res=c1+c2;
	res.display();
	res=1+c1;
	res.display();
	res=c1+1;
	res.display();
	cout<<"--------------------"<<endl;
	res=c1-c2;
	res.display();
	res=1-c1;
	res.display();
	res=c1-1;
	res.display();
}