#include <iostream>
using namespace std;

class A{
public:
	A(){
		cout<<"A�Ĺ��췽��"<<endl;
	}
	A(A &a){
		cout<<"A�ĸ��ƹ��췽��"<<endl;
	}
	~A(){
		cout<<"A����������"<<endl;
	}
virtual void display(){
		cout<<"A��display"<<endl;
	}
};

class B:public A{
public:
	B(){
		cout<<"B�Ĺ��췽��"<<endl;
	}
	B(B &b):A(b){
		cout<<"B�ĸ��ƹ��췽��"<<endl;
	}
	~B(){
		cout<<"B����������"<<endl;
	}
void display(){
		cout<<"B��display"<<endl;
	}
};

void display(A a){
	cout<<"-----------222222----------"<<&a<<endl;
//	B &b=(B) a;
	a.display();
	cout<<"-----------333333----------"<<&a<<endl;
}

void main(){
	
//	A a;
	B b;
	A &a1=b;
//	b.A::display();
	cout<<"----------1111111-----------"<<&a1<<endl;
	display(a1);
	cout<<"-----------444444----------"<<&a1<<endl;
}