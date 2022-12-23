#include <iostream>
using namespace std;

class A{
public:
	A(){
		cout<<"A的构造方法"<<endl;
	}
	virtual ~A(){
		cout<<"A的析构函数"<<endl;
	}
};

class B:public A{
public:
	B(){
		cout<<"B的构造方法"<<endl;
	}
	~B(){
		cout<<"B的析构函数"<<endl;
	}
};

void main(){
//	B *b=new B;
	A *a=new B;
	delete a;
}