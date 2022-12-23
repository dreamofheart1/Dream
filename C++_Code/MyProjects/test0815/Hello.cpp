#include <iostream>
#include <string>
using namespace std;

class Father{
private :
	string id;

protected:
	int age;

public:
	static int eye;
	string name;
	void setId(string i);
	void setAge(int age);
	void disPlay() ;
	void f(){
		cout<<"eye:"<<eye<<endl;
		eye=4;
		cout<<"eye:"<<eye<<endl;
		eye=3;
		cout<<"eye:"<<eye<<endl;
	};

};

int Father::eye=2;

void Father::setId (string i){
	id=i;
}

void Father::setAge(int a){
	age=a;
}

void Father::disPlay() {
	cout<<"id:"<<id<<" name:"<<name<<" age:"<<age<<endl;
		
}


void main (){
	Father father;
	father.f();
//	father.disPlay();
	cout<<"=========="<<endl;
}