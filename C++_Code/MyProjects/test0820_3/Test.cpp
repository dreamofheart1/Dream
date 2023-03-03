#include <iostream>
#include <string>
using namespace std;

class Father;
class Son;
class A;

class Father{
public :
	int n;

public :
	Father(int a=10){
		n=a;
	}
	void display(A);
	void display2();
	void display3(){
		cout<<n<<endl;
	}
};

class Son: public Father{
private :
	int n,k;
public :
	Son(int a=1,int b=2,int c=3):Father(a){
		n=b;
		k=c;
	}
	void display(A a);
};

class A{
private:
	int a;
public :
	A(int ta=11){
		a=ta;
	}
	friend void Father::display(A);
};

void Father::display(A a){
	cout<<a.a<<endl;
}
void Father::display2(){
	cout<<n<<endl;
}

void Son::display(A a){
	cout<<a.a<<endl;
//		display3();
//		cout<<Father::n<<endl;
}

void main(){
	cout<<"-----------"<<endl;
	Father f1;
	Son s1;
	A a;
	f1.display(a);
//	s1.display(a);
//	s1.display2();
//	s1.display3();
	s1.display(a);
}