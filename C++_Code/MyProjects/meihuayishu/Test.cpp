#include<iostream>
#include<string>
using namespace std;

int* getYao(int i) {
	int yao[3] = { 1,1,1 };
	switch (i) {
	case 1:
		yao[2] = 1;
		yao[1] = 1;
		yao[0] = 1;
		break;
	case 2:
		yao[2] = 0;
		yao[1] = 1;
		yao[0] = 1;
		break;
	case 3:
		yao[2] = 1;
		yao[1] = 0;
		yao[0] = 1;
		break;
	case 4:
		yao[2] = 0;
		yao[1] = 0;
		yao[0] = 1;
		break;
	case 5:
		yao[2] = 1;
		yao[1] = 1;
		yao[0] = 0;
		break;
	case 6:
		yao[2] = 0;
		yao[1] = 1;
		yao[0] = 0;
		break;
	case 7:
		yao[2] = 1;
		yao[1] = 0;
		yao[0] = 0;
		break;
	case 8:
		yao[2] = 0;
		yao[1] = 0;
		yao[0] = 0;
		break;

	default:
		break;
	}
	return yao;
};

int getIndex(int* i) {
	int idx = 0;
	if (i[2] == 0)
		idx = idx + 1;
	if (i[1] == 0)
		idx = idx + 2;
	if (i[0] == 0)
		idx = idx + 4;
	return idx + 1;
}

class XTBG {
private:
	string xtbg[8][5];
public:
	XTBG();
//	friend void getYao(int i);
	string* getXtbg(int i) {
		return xtbg[i - 1];
	};
};


class Gua {
private :
	XTBG xtbg;
	string g[5];
	int yao[3];
public :
	Gua() {};
	Gua(XTBG txtbg, int i);
	void changeGua(int i);
	string getGIndex(int i);
};
string Gua::getGIndex(int i) {
	return g[i - 1];
}

Gua::Gua(XTBG txtbg, int i) {
	xtbg = txtbg;
	string* tg = xtbg.getXtbg(i);
	int* tyao = getYao(i);
	int j=0;
	for (; j < 5; j++) {
		g[j] = tg[j];
	}
	j=0;
	for (; j < 3; j++) {
		yao[j] = tyao[j];
	}
}
void Gua::changeGua(int i) {
	if (i > 3)
		i = i - 3;
	i = i - 1;
	if (yao[i] == 0) {
		yao[i] = 1;
	}
	else {
		yao[i] = 0;
	}
	int idx = getIndex(yao);
	string* tg = xtbg.getXtbg(idx);
	int* tyao = getYao(idx);
	int j=0;
	for (; j < 5; j++) {
		g[j] = tg[j];
	}
	j=0;
	for (; j < 3; j++) {
		yao[j] = tyao[j];
	}
}


XTBG::XTBG() {
	string temp[8][5] = {
		{"Ǭ","1","1","��","��"},
		{"��","2","2","��","��"},
		{"��","3","3","��","��"},
		{"��","4","4","��","ľ"},
		{"��","5","5","��","ľ"},
		{"��","6","6","��","ˮ"},
		{"��","7","7","��","��"},
		{"��","8","8","��","��"},
	};
	for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 5; j++) {
			xtbg[i][j] = temp[i][j];
		}
	}
}
int getIndexWX(string str) {
	int i = 0;
	if (0 == str.compare("ľ")) {
		i = 0;
	}else if (0 == str.compare("��")) {
		i = 1;
	}else if (0 == str.compare("��")) {
		i = 2;
	}else if (0 == str.compare("��")) {
		i = 3;
	}else if (0 == str.compare("ˮ")) {
		i = 4;
	}
	return i ;
}

int getRelation(int a, int b) {//aΪ�壬bΪ��;
	int relation[5][5] = {
		{1,3,2,4,0},
		{0,1,3,2,4},
		{4,0,1,3,2},
		{2,4,0,1,3},
		{3,2,4,0,1}
	};

	return relation[a][b];
}

void display(int i) {
	switch (i)
	{
	case 0:
		cout << "�󼪣��±س�" << endl;
		break;
	case 1:
		cout << "�μ������׳�" << endl;
		break;
	case 2:
		cout << "�м����¿ɳ�" << endl;
		break;
	case 3:
		cout << "С�ף����ѳ�" << endl;
		break;
	case 4:
		cout << "���ף��²���" << endl;
		break;
	default:
		break;
	}
}

void info1(Gua s, Gua x) {
	cout << "����Ϊ��" << endl;
	cout << "    �Ϲң� " << s.getGIndex(1) << " �¹ң�" << x.getGIndex(1) << endl;
	
}
void info2(Gua s, Gua x, int i) {
	if (i < 3) {
		x.changeGua(i);
	}
	else {
		s.changeGua(i);
	}
	cout << "����Ϊ��" << endl;
	cout << "    �Ϲң� " << s.getGIndex(1) << " �¹ң�" << x.getGIndex(1) << endl;
}
void copyright() {
	cout << "Copyright ��c�� 2021-2999 ��Т��" << endl;
	cout << "��Ȩ���������У�δ����Ȩ�������ã�" << endl;
	cout << "�������������ϵQQ��2933902772  ��΢�ţ�dreamofheart1 " << endl;
	cout << endl;
	cout << "��ӭ���� ÷������ 0.2�汾��" << endl;
	cout << endl;
}
void main() {
	copyright();
	int a=1, b=1, c=10;
	int r;
	XTBG xtbg;
	Gua gt, gy;
	cout << "ڤ�룡���Լ�����ʶ�뱻Ԥ�����������ϵ" << endl;
	cout << endl;
	cout << "�������Ժ��г��ֵ�1-9������������,���Կո��س��ָ�" << endl;
	int i = 0;
	while (a > 9 || a < 1 || b>9 || b < 1 || c>9 || c < 1) {
		if (i > 0)
			cout << "�����������������룡" << endl;
		cin >> a >> b >> c;
		i++;
	}
	a = a % 8;
	b = b % 8;
	c = c % 6;
	if (a == 0)
		a = 8;
	if (b == 0)
		b = 8;
	if (c == 0)
		c = 6;
	Gua ga(xtbg, a) ,gb(xtbg, b);
	if (c < 3) {
		gt = ga;
		gy = gb;
		cout << "�����Ϲ�Ϊ��ң��¹�Ϊ�ù�" << endl;
	}
	else {
		gt = gb;
		gy = ga;
		cout << "�����¹�Ϊ��ң��Ϲ�Ϊ�ù�" << endl;
	}
//	cout << gt.getGIndex(5) << endl;
	r=getRelation(getIndexWX(gt.getGIndex(5)), getIndexWX(gy.getGIndex(5)));
	info1(ga, gb);
	cout << endl;
	cout << "��ǰ���ƣ�" << endl;
	display(r);
	cout << "-------------------------------------------------" << endl;
	cout << "-------------------------------------------------" << endl;

	gy.changeGua(c);
	r = getRelation(getIndexWX(gt.getGIndex(5)), getIndexWX(gy.getGIndex(5)));
	info2(ga, gb, c);
	cout << endl;
	cout << "�仯֮��" << endl;
	display(r);
	cout << "-------------------------------------------------" << endl;
	cout << endl;
	cout << "ע��ԭ�����ֵ��������ʼ����ʼ�׶ε���Ϣ����Ŀǰ����������������ɱ��Ա䶯�����ġ����Դ��������﷢չ�仯�����ս����" << endl;

	system("PAUSE");
}
