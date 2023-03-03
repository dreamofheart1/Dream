#include <iostream>
using namespace std;

class A{
public:
	A(){
		cout<<"A的构造方法"<<endl;
	}
	A(A &a){
		cout<<"A的复制构造方法"<<endl;
	}
	~A(){
		cout<<"A的析构方法"<<endl;
	}
virtual void display(){
		cout<<"A的display"<<endl;
	}
};

class B:public A{
public:
	B(){
		cout<<"B的构造方法"<<endl;
	}
	B(B &b):A(b){
		cout<<"B的复制构造方法"<<endl;
	}
	~B(){
		cout<<"B的析构方法"<<endl;
	}
void display(){
		cout<<"B的display"<<endl;
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