#include <iostream>
using namespace std;

void main(){
	int n=0;
	char c;
	while((c=cin.get())!=EOF){
		cout<<"-";
		cout.put(c);
		n++;
	}
	cout<<"Ò»¹²ÊäÈë£º"<<n<<endl;
}