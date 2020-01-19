#include "..\CommenHead.h"

static void LineBegin(short layer, string ST, string ET, char Font[20])
{
	char TE[10];
	memset(TE,10,'\0');
	_itoa_s(layer,TE,10);
	ass_out<<"Dialogue:"<<TE<<','<<ST<<','<<ET<<','<<Font<<",NTP,0000,0000,0000,,";
}
static void LineBegin(short layer, string ST, string ET, char Font)
{
	char TE[10];
	memset(TE,10,'\0');
	_itoa_s(layer,TE,10);
	ass_out<<"Dialogue:"<<TE<<','<<ST<<','<<ET<<','<<Font<<",NTP,0000,0000,0000,,";
}
static void StyleBegin()
{
	ass_out<<'{';
}
static void StyleEnd()
{
	ass_out<<'}';
}
static void LineEnd(char word[SingleWordLenth])
{
	ass_out<<word<<endl;
}
static void LineEnd(char word)
{
	ass_out<<word<<endl;
}
static void LineEnd(short word)
{
	ass_out<<word<<endl;
}
static void LineEnd()
{
	ass_out<<endl;
}


static void bord(short broad)
{
	char TE[10];
	memset(TE,10,'\0');
	_itoa_s(broad,TE,10);
	ass_out<<"\\bord"<<TE;
}
//描边，参数为宽度

static void xshad(short shadow)
{
	char TE[10];
	memset(TE,10,'\0');
	_itoa_s(shadow,TE,10);
	ass_out<<"\\xshad"<<TE;
}
//在x方向加阴影
static void yshad(short shadow)
{
	char TE[10];
	memset(TE,10,'\0');
	_itoa_s(shadow,TE,10);
	ass_out<<"\\yshad"<<TE;
}
//在y方向加阴影
static void shad(short shadow)
{
	char TE[10];
	memset(TE,10,'\0');
	_itoa_s(shadow,TE,10);
	ass_out<<"\\shad"<<TE;
}
//斜二侧画法的阴影

static void be(short BE)
{
	char TE[10];
	memset(TE,10,'\0');
	_itoa_s(BE,TE,10);
	ass_out<<"\\be"<<TE;
}
//1-柔化边缘，0-取消
static void blur(short B)
{
	char TE[10];
	memset(TE,10,'\0');
	_itoa_s(B,TE,10);
	ass_out<<"\\blur"<<TE;
}
//柔化边缘，参数是柔滑度

static void fn(char Fname[100])
{
	ass_out<<"\\fn"<<Fname;
}
//载入已定义的字体
static void fs(short size)
{
	char TE[10];
	memset(TE,10,'\0');
	_itoa_s(size,TE,10);
	ass_out<<"\\fs"<<TE;
}
//换字号

static void fscx(unsigned short X)
{
	char TE[10];
	memset(TE,10,'\0');
	_itoa_s(X,TE,10);
	ass_out<<"\\fscx"<<TE;
}
//x方向拉伸
static void fscy(unsigned short Y)
{
	char TE[10];
	memset(TE,10,'\0');
	_itoa_s(Y,TE,10);
	ass_out<<"\\fscy"<<TE;
}
//y方向拉伸

static void fsp(short P)
{
	char TE[10];
	memset(TE,10,'\0');
	_itoa_s(P,TE,10);
	ass_out<<"\\fsp"<<TE;
}
//字间距，P的单位是像素

static void frx(short angle)
{
	char TE[10];
	memset(TE,10,'\0');
	_itoa_s(angle,TE,10);
	ass_out<<"\\frx"<<TE;
}
//x轴旋转
static void fry(short angle)
{
	char TE[10];
	memset(TE,10,'\0');
	_itoa_s(angle,TE,10);
	ass_out<<"\\fry"<<TE;
}
//y轴旋转
static void frz(short angle)
{
	char TE[10];
	memset(TE,10,'\0');
	_itoa_s(angle,TE,10);
	ass_out<<"\\frz"<<TE;
}
//z轴旋转

static void fe(char E[20])
{
	ass_out<<"\\fe"<<E;
}
//强制载入E[20]的文字编码

static void Nc(short N, char color[7])
{
	char TE[10];
	memset(TE,10,'\0');
	_itoa_s(N,TE,10);
	ass_out<<'\\'<<TE<<"c&H"<<color<<"&";
}
//N-载入颜色的位置1~4，color-BBGGRR格式的颜色
static void Na(short N, short alpha)
{
	char TE[10];
	memset(TE,10,'\0');
	_itoa_s(N,TE,10);
	ass_out<<'\\'<<TE<<"a&H"<<hex<<alpha<<"&";
}
//N-载入透明度的位置1~4，color-FF格式的透明度

static void an(short KB)
{
	char TE[10];
	memset(TE,10,'\0');
	_itoa_s(KB,TE,10);
	ass_out<<"\\an"<<TE;
}
//按数字键盘定位1-9

static void tBegin()
{
	ass_out<<"\\t(";
}
//只输出"t("的函数
static void tBegin(float A)
{
	ass_out<<"\\t("<<A<<',';
}
//输出"t(A,",A-accel(0~1-由快变慢，>1-由慢变快)
static void tBegin(unsigned int t1, unsigned int t2)
{
	char TE1[10];
	memset(TE1,10,'\0');
	_itoa_s(t1,TE1,10);
	char TE2[10];
	memset(TE2,10,'\0');
	_itoa_s(t2,TE2,10);
	ass_out<<"\\t("<<TE1<<','<<TE2<<',';
}
//输出"t(t1,t2,"
static void tBegin(unsigned int t1, unsigned int t2, float A)
{
	char TE1[10];
	memset(TE1,10,'\0');
	_itoa_s(t1,TE1,10);
	char TE2[10];
	memset(TE2,10,'\0');
	_itoa_s(t2,TE2,10);
	ass_out<<"\\t("<<TE1<<','<<TE2<<','<<A<<',';
}
//输出"t(t1,t2,A,"
static void tEnd()
{
	ass_out<<')';
}
//输出')'

static void move(short x1, short y1, short x2,short y2)
{
	char TE1[10];
	memset(TE1,10,'\0');
	_itoa_s(x1,TE1,10);
	char TE2[10];
	memset(TE2,10,'\0');
	_itoa_s(y1,TE2,10);
	char TE3[10];
	memset(TE3,10,'\0');
	_itoa_s(x2,TE3,10);
	char TE4[10];
	memset(TE4,10,'\0');
	_itoa_s(y2,TE4,10);
	ass_out<<"\\move("<<TE1<<','<<TE2<<','<<TE3<<','<<TE4<<')';
}
static void move(short x1, short y1, short x2,short y2, unsigned int t1, unsigned int t2)
{
	char TE1[10];
	memset(TE1,10,'\0');
	_itoa_s(x1,TE1,10);
	char TE2[10];
	memset(TE2,10,'\0');
	_itoa_s(y1,TE2,10);
	char TE3[10];
	memset(TE3,10,'\0');
	_itoa_s(x2,TE3,10);
	char TE4[10];
	memset(TE4,10,'\0');
	_itoa_s(y2,TE4,10);
	char TE5[10];
	memset(TE5,10,'\0');
	_itoa_s(t1,TE5,10);
	char TE6[10];
	memset(TE6,10,'\0');
	_itoa_s(t2,TE6,10);
	ass_out<<"\\move("<<TE1<<','<<TE2<<','<<TE3<<','<<TE4<<','<<TE5<<','<<TE6<<')';
}
static void pos(short x, short y)
{
	char TE1[10];
	memset(TE1,10,'\0');
	_itoa_s(x,TE1,10);
	char TE2[10];
	memset(TE2,10,'\0');
	_itoa_s(y,TE2,10);
	ass_out<<"\\pos("<<TE1<<','<<TE2<<')';
}

static void org(short x, short y)
{
	char TE1[10];
	memset(TE1,10,'\0');
	_itoa_s(x,TE1,10);
	char TE2[10];
	memset(TE2,10,'\0');
	_itoa_s(y,TE2,10);
	ass_out<<"\\org("<<TE1<<','<<TE2<<')';
}
//移动对象的原点

static void fade(short a1, short a2, short a3, int t1, int t2, int t3)
{
	char TE4[10];
	memset(TE4,10,'\0');
	_itoa_s(t1,TE4,10);
	char TE5[10];
	memset(TE5,10,'\0');
	_itoa_s(t2,TE5,10);
	char TE6[10];
	memset(TE6,10,'\0');
	_itoa_s(t3,TE6,10);
	ass_out<<"\\fade("<<hex<<a1<<','<<hex<<a2<<','<<hex<<a3<<','<<TE4<<','<<TE5<<','<<TE6<<')';
}
static void fad(int t1, int t2)
{
	char TE1[10];
	memset(TE1,10,'\0');
	_itoa_s(t1,TE1,10);
	char TE2[10];
	memset(TE2,10,'\0');
	_itoa_s(t2,TE2,10);
	ass_out<<"\\fad("<<TE1<<','<<TE2<<')';
}
static void clip(short x1, short y1, short x2, short y2)
{
	char TE1[10];
	memset(TE1,10,'\0');
	_itoa_s(x1,TE1,10);
	char TE2[10];
	memset(TE2,10,'\0');
	_itoa_s(y1,TE2,10);
	char TE3[10];
	memset(TE3,10,'\0');
	_itoa_s(x2,TE3,10);
	char TE4[10];
	memset(TE4,10,'\0');
	_itoa_s(y2,TE4,10);
	ass_out<<"\\clip("<<TE1<<','<<TE2<<','<<TE3<<','<<TE4<<')';
}
static void clip(short P, char draw[1000])
{
	char TE[10];
	memset(TE,10,'\0');
	_itoa_s(P,TE,10);
	ass_out<<"\\clip("<<TE<<','<<draw<<')';
}
//P是比例，draw是绘图命令
static void pN(short N, char draw[1000])
{
	char TE[10];
	memset(TE,10,'\0');
	_itoa_s(N,TE,10);
	ass_out<<'{'<<"\\p"<<TE<<'}'<<draw<<'{'<<"\\p0}";
}
//N-比例，draw-绘图命令
