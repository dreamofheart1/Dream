#include <iostream>
#include <string>
using namespace std;


class Complex{
	private:
		string name;
		double real,imag;

	public :
		Complex(string str,double a=0,double b=0){
			this->name=str;
			this->real=a;
			this->imag=b;
		};
		Complex(double a=0,double b=0){
			this->real=a;
			this->imag=b;
		};
		void outCom(){
			cout<<"¸´Êý:  "<<name<<"="<<real<<"+"<<imag<<"i"<<endl;
		};
		void f(Complex &a){
			cout<<a.real<<endl;
		};
		Complex operator+(Complex &);
		Complex operator-(Complex &);
};
Complex Complex::operator+(Complex &b){
	cout<<"b: "<<b.real<<endl;
	return Complex(this->real+b.real,this->imag+b.imag);
};
Complex Complex::operator-(Complex &b){
	return Complex(this->real-b.real,this->imag-b.imag);
};
	



void main(){
	Complex c1("c1",2,3), c2("c2",1,2), res("res");
	c1.outCom();
	cout<<&c1<<endl;
	cout<<&c2<<endl;
	cout<<&res<<endl;
	res=c1+c2;
	cout<<&res<<endl;
	res=c2+c1;
	c1.outCom();
	res.outCom();

	res=c1-c2;
	res.outCom();
}