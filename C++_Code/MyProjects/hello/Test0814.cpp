#include <iostream>
#include <string>
using namespace std;

class Father{
private :
	string id;

protected:
	int age;

public:
	string name;
	void setId(string i);
	void setAge(int age);
	void disPlay() ;

};

void Father::setId (string i){
	id=i;
}

void Father::setAge(int a){
	age=a;
}

void Father::disPlay() {
	cout<<"id:"<<id<<" name:"<<name<<" age:"<<age<<endl;
		
}


int main (){
	Father father;
//	father.id="1111";
	father.setId("111111");
//	father.age=21;
	father.setAge(21);
	father.name="wang";
	father.disPlay();
	cout<<"=========="<<endl;
	return 0;
}