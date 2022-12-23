#include <iostream>
using namespace std;

class A{
public:
	int a ,b,c;
	A():a(1),b(2),c(3){}
};


A f(A a){
	cout<<&a<<endl;
	return a;
}
A* f(A* a){
	cout<<&*a<<endl;
	return a;
}

void main(){
	A a;
	cout<<&a<<endl;
	f(a);
	A* c=&a;
	cout<<&*c<<endl;
	f(c);
}
