#include <iostream>
#include <string>
using namespace std;

class A{
public :
	int a[2];
private:
    static int totle;
	int b;
};
int A::totle = 0;
//int A::b=0;
void main(){
	A a;
//	a.a[0]=0;
	a.a[1]=1;
	cout<<"hello "<<a.a[1]<<endl;
	char c='ºÃ';
	cout<<c<<endl;
    string str("ÄãºÃ");
    cout << str << endl;
    string str2 = str;
    cout << str2 << endl;
}