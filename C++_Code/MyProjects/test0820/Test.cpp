#include <iostream>
#include <string>
using namespace std;

class Father;
class Son;
class A{

public :
	void display(Father);
//	void display(Son);
};

class Father{
private :
	int n;

public :
	Father(int a=10){
		n=a;
	}

	friend void A::display(Father);
//	friend void A::display(Son);
};

class Son:public Father{
private :
	int n,k;
public :
	Son(int a=1,int b=2,int c=3):Father(a){
		n=b;
		k=c;
	}
};

void A::display(Father f){
	cout<<f.n<<endl;
}
//void A::display(Son s){
//	cout<<s.n<<endl;
//}

void main(){
	cout<<"-----------"<<endl;
	Father f1;
	Son s1;
	A a;
	a.display(f1);
	a.display(s1);
}