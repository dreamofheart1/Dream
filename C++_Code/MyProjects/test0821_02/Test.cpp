#include <iostream>
using namespace std;

class A{
public:
	A(){
		cout<<"A�Ĺ��췽��"<<endl;
	}
	virtual ~A(){
		cout<<"A����������"<<endl;
	}
};

class B:public A{
public:
	B(){
		cout<<"B�Ĺ��췽��"<<endl;
	}
	~B(){
		cout<<"B����������"<<endl;
	}
};

void main(){
//	B *b=new B;
	A *a=new B;
	delete a;
}