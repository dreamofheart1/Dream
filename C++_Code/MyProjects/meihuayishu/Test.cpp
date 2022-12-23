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
		{"乾","1","1","阳","金"},
		{"兑","2","2","阴","金"},
		{"离","3","3","阴","火"},
		{"震","4","4","阳","木"},
		{"巽","5","5","阴","木"},
		{"坎","6","6","阳","水"},
		{"艮","7","7","阳","土"},
		{"坤","8","8","阴","土"},
	};
	for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 5; j++) {
			xtbg[i][j] = temp[i][j];
		}
	}
}
int getIndexWX(string str) {
	int i = 0;
	if (0 == str.compare("木")) {
		i = 0;
	}else if (0 == str.compare("火")) {
		i = 1;
	}else if (0 == str.compare("土")) {
		i = 2;
	}else if (0 == str.compare("金")) {
		i = 3;
	}else if (0 == str.compare("水")) {
		i = 4;
	}
	return i ;
}

int getRelation(int a, int b) {//a为体，b为用;
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
		cout << "大吉，事必成" << endl;
		break;
	case 1:
		cout << "次吉，事易成" << endl;
		break;
	case 2:
		cout << "中吉，事可成" << endl;
		break;
	case 3:
		cout << "小凶，事难成" << endl;
		break;
	case 4:
		cout << "大凶，事不成" << endl;
		break;
	default:
		break;
	}
}

void info1(Gua s, Gua x) {
	cout << "本卦为：" << endl;
	cout << "    上挂： " << s.getGIndex(1) << " 下挂：" << x.getGIndex(1) << endl;
	
}
void info2(Gua s, Gua x, int i) {
	if (i < 3) {
		x.changeGua(i);
	}
	else {
		s.changeGua(i);
	}
	cout << "变卦为：" << endl;
	cout << "    上挂： " << s.getGIndex(1) << " 下挂：" << x.getGIndex(1) << endl;
}
void copyright() {
	cout << "Copyright （c） 2021-2999 孟孝龙" << endl;
	cout << "版权归作者所有，未经授权请勿商用！" << endl;
	cout << "如需合作，请联系QQ：2933902772  或微信：dreamofheart1 " << endl;
	cout << endl;
	cout << "欢迎来到 梅花易数 0.2版本！" << endl;
	cout << endl;
}
void main() {
	copyright();
	int a=1, b=1, c=10;
	int r;
	XTBG xtbg;
	Gua gt, gy;
	cout << "冥想！将自己的意识与被预测的事物相联系" << endl;
	cout << endl;
	cout << "请输入脑海中出现的1-9的任意三个数,并以空格或回车分隔" << endl;
	int i = 0;
	while (a > 9 || a < 1 || b>9 || b < 1 || c>9 || c < 1) {
		if (i > 0)
			cout << "输入有误请重新输入！" << endl;
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
		cout << "其中上挂为体挂，下挂为用挂" << endl;
	}
	else {
		gt = gb;
		gy = ga;
		cout << "其中下挂为体挂，上挂为用挂" << endl;
	}
//	cout << gt.getGIndex(5) << endl;
	r=getRelation(getIndexWX(gt.getGIndex(5)), getIndexWX(gy.getGIndex(5)));
	info1(ga, gb);
	cout << endl;
	cout << "当前局势：" << endl;
	display(r);
	cout << "-------------------------------------------------" << endl;
	cout << "-------------------------------------------------" << endl;

	gy.changeGua(c);
	r = getRelation(getIndexWX(gt.getGIndex(5)), getIndexWX(gy.getGIndex(5)));
	info2(ga, gb, c);
	cout << endl;
	cout << "变化之后：" << endl;
	display(r);
	cout << "-------------------------------------------------" << endl;
	cout << endl;
	cout << "注：原卦体现的是事物初始、开始阶段的信息，或目前的情况。而变卦是由本卦变动而来的。变卦代表着事物发展变化的最终结果。" << endl;

	system("PAUSE");
}
