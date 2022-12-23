#include <iostream>
using namespace std;

class A {
public :
	virtual void display();
};

class B:public A{
public:
	void display();
};

class C:public B{
public:
	void display();
};
void A::display(){
	cout<<"display of A"<<endl;
}
void B::display(){
	cout<<"display of B"<<endl;
}
void C::display(){
	cout<<"display of C"<<endl;
}

void main(){
	A a;
	B b;
	C c;
	a.display();
	b.display();
	c.display();
	A &a2=b;
	A&a3=c;
	A *a4=&b;
	A *a5=&c;
	a2.display();
	a3.display();
	a3.A::display();
	a4->display();
	a5->display();

}