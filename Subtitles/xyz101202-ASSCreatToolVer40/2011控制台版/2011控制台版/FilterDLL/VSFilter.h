#include "stdafx.h"

static void LineBegin(short layer, char ST[11], char ET[11], char Font[20])
{
	ass_out<<"Dialogue:"<<layer<<","<<ST<<","<<ET<<","<<Font<<",NTP,0000,0000,0000,,";
}
static void StyleBegin()
{
	ass_out<<"{";
}
static void StyleEnd()
{
	ass_out<<"}";
}
static void LineEnd(char word[SingleWordLenth])
{
	ass_out<<word<<endl;
}
static void LineEnd()
{
	ass_out<<endl;
}


static void bord(short broad)
{
	ass_out<<(char)A92<<"bord"<<broad;
}
//描边，参数为宽度

static void xshad(short shadow)
{
	ass_out<<(char)A92<<"xshad"<<shadow;
}
//在x方向加阴影
static void yshad(short shadow)
{
	ass_out<<(char)A92<<"yshad"<<shadow;
}
//在y方向加阴影
static void shad(short shadow)
{
	ass_out<<(char)A92<<"shad"<<shadow;
}
//斜二侧画法的阴影

static void be(short BE)
{
	ass_out<<(char)A92<<"be"<<BE;
}
//1-柔化边缘，0-取消
static void blur(short B)
{
	ass_out<<(char)A92<<"blur"<<B;
}
//柔化边缘，参数是柔滑度

static void fn(char Fname[100])
{
	ass_out<<(char)A92<<"fn"<<Fname;
}
//载入已定义的字体
static void fs(short size)
{
	ass_out<<(char)A92<<"fs"<<size;
}
//换字号

static void fscx(unsigned short X)
{
	ass_out<<(char)A92<<"fscx"<<X;
}
//x方向拉伸
static void fscy(unsigned short Y)
{
	ass_out<<(char)A92<<"fscy"<<Y;
}
//y方向拉伸

static void fsp(short P)
{
	ass_out<<(char)A92<<"fsp"<<P;
}
//字间距，P的单位是像素

static void frx(short angle)
{
	ass_out<<(char)A92<<"frx"<<angle;
}
//x轴旋转
static void fry(short angle)
{
	ass_out<<(char)A92<<"fry"<<angle;
}
//y轴旋转
static void frz(short angle)
{
	ass_out<<(char)A92<<"frz"<<angle;
}
//z轴旋转

static void fe(char E[20])
{
	ass_out<<(char)A92<<"fe"<<E;
}
//强制载入E[20]的文字编码

static void Nc(short N, char color[7])
{
	ass_out<<(char)A92<<N<<"c&H"<<color<<"&";
}
//N-载入颜色的位置1~4，color-BBGGRR格式的颜色
static void Na(short N, short alpha)
{
	ass_out<<(char)A92<<N<<"a&H"<<hex<<alpha<<"&";
}
//N-载入透明度的位置1~4，color-FF格式的透明度

static void an(short KB)
{
	ass_out<<(char)A92<<"an"<<KB;
}
//按数字键盘定位1-9

static void tBegin()
{
	ass_out<<(char)A92<<"t(";
}
//只输出"t("的函数
static void tBegin(float A)
{
	ass_out<<(char)A92<<"t("<<A<<",";
}
//输出"t(A,",A-accel(0~1-由快变慢，>1-由慢变快)
static void tBegin(unsigned int t1, unsigned int t2)
{
	ass_out<<(char)A92<<"t("<<t1<<","<<t2<<",";
}
//输出"t(t1,t2,"
static void tBegin(unsigned int t1, unsigned int t2, float A)
{
	ass_out<<(char)A92<<"t("<<t1<<","<<t2<<","<<A<<",";
}
//输出"t(t1,t2,A,"
static void tEnd()
{
	ass_out<<")";
}
//输出")"

static void move(short x1, short y1, short x2,short y2)
{
	ass_out<<(char)A92<<"move("<<x1<<","<<y1<<","<<x2<<","<<y2<<")";
}
static void move(short x1, short y1, short x2,short y2, unsigned int t1, unsigned int t2)
{
	ass_out<<(char)A92<<"move("<<x1<<","<<y1<<","<<x2<<","<<y2<<","<<t1<<","<<t2<<")";
}
static void pos(short x, short y)
{
	ass_out<<(char)A92<<"pos("<<x<<","<<y<<")";
}

static void org(short x, short y)
{
	ass_out<<(char)A92<<"org("<<x<<","<<y<<")";
}
//移动对象的原点

static void fade(short a1, short a2, short a3, int t1, int t2, int t3)
{
	ass_out<<(char)A92<<"fade("<<hex<<a1<<","<<hex<<a2<<","<<hex<<a3<<","<<t1<<","<<t2<<","<<t3<<")";
}
static void fad(int t1, int t2)
{
	ass_out<<(char)A92<<"fad("<<t1<<","<<t2<<")";
}

static void clip(short P, char draw[1000])
{
	ass_out<<(char)A92<<"clip("<<P<<","<<draw<<")";
}
//P是比例，draw是绘图命令
static void pN(short N, char draw[1000])
{
	ass_out<<"{"<<(char)A92<<"p"<<N<<"}"<<draw<<"{"<<(char)A92<<"p0}";
}
//N-比例，draw-绘图命令
