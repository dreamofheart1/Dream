#include <iostream>
#include <string>
using namespace std;


void main(){
	int* a=new int;
	*a=2;
	cout<<a<<endl;
	cout<<*a<<endl;
	int& b=*a;
	b=3;
	cout<<b<<endl;
}