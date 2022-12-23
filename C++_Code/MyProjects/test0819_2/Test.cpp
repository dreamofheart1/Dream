#include <iostream>
using namespace std;

class A{
private:
	int aa,bb;
	
public :
	A(int a=0,int b=0){
		aa=a;
		bb=b;
	}
	void display(){
		cout<<aa<<"   "<<bb<<endl;
	}
};

A & f(A &a1){
	cout<<&a1<<endl;
	A a;
	cout<<&a<<endl;
	return a;
}

void main(){
	A a(1,1),b;
	cout<<&a<<endl;
	cout<<&b<<endl;
//	b=f(a);
	cout<<&f(a)<<endl;
	
}

