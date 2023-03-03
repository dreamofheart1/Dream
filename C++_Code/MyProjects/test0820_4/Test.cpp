#include <iostream>
using namespace std;

class A{};

class B:public A{};

class C:public A{};

void main(){
	B b;
	C c;
}
