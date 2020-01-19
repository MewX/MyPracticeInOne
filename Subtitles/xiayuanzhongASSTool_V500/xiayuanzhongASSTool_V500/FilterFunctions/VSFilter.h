#pragma once
#include <string>
#include <cmath>

class VSFilter
{

	/*为了在Union里保持兼容性，在Union中所有函数前面加下划线'_'，这里不要加*/

public:
	/*VSFilter*/
	std::string LineBegin( short layer, std::string ST, std::string ET, std::string Font );
	std::string StyleBegin( );
	std::string StyleEnd( );
	std::string LineEnd( std::string word );
	std::string LineEnd( );//Notice
	std::string bord( short broad );//描边，参数为宽度
	std::string xshad( short shadow );//在x方向加阴影
	std::string yshad( short shadow );//在y方向加阴影
	std::string shad( short shadow );//斜二侧画法的阴影
	std::string be( short BE );//1-柔化边缘，0-取消
	std::string blur( short B );//柔化边缘，参数是柔滑度
	std::string fn( std::string Fname );//载入已定义的字体
	std::string fs( short size );//换字号
	std::string fscx( unsigned short X );//x方向拉伸
	std::string fscy( unsigned short Y );//y方向拉伸
	std::string fsp( short P );//字间距，P的单位是像素
	std::string frx( short angle );//x轴旋转，水平方向（大于0，自前向后）
	std::string fry( short angle );//y轴旋转，竖直方向（大于0，自西向东）
	std::string frz( short angle );//z轴旋转，里外方向（大于0，逆时针）
	std::string Nc( short N, char color[ 7 ] );//N-载入颜色的位置1~4，color-BBGGRR格式的颜色
	std::string Na( short N, short alpha );//N-载入透明度的位置1~4，color-FF格式的透明度
	std::string an( short KB );//按数字键盘定位1-9
	std::string tBegin( );//只输出"t("的函数
	std::string tBegin( float A );//输出"t(A,",A-accel(0~1-由快变慢，>1-由慢变快)
	std::string tBegin( int t1, int t2 );//输出"t(t1,t2,"
	std::string tBegin( int t1, int t2, float A );//输出"t(t1,t2,A,"
	std::string tEnd( );//输出')'
	std::string move( short x1, short y1, short x2,short y2 );
	std::string move( short x1, short y1, short x2,short y2, int t1, int t2 );
	std::string pos( short x, short y );
	std::string org( short x, short y );//移动对象的原点
	std::string fade( short a1, short a2, short a3, int t1, int t2, int t3 );
	std::string fad( int t1, int t2 );
	std::string clip( short x1, short y1, short x2, short y2 );
	std::string clip( short P, std::string draw );//P是比例，draw是绘图命令
	std::string pN( short N, std::string draw );//N-比例，draw-绘图命令
	/*VSFilterMod*/
	std::string fsc(unsigned short scale );//字体放大%
	std::string fsvp( short leading );//纵向偏移
	std::string frs( short angle );//基线倾斜
	std::string z( short arg );//z坐标
	std::string distort( float u1, float v1, float u2, float v2, float u3, float v3 );//扭曲文字,通过移动角上坐标具体确定相对的坐标
	std::string rnd( short arg );
	std::string rndx( short arg );
	std::string rndy( short arg );
	std::string rndz( short arg );//边界变形
	std::string Nvc( short N, char LT[ 7 ], char RT[ 7 ], char LB[ 7 ], char RB[ 7 ] );
	std::string Nva( short N, short LT, short RT, short LB, short RB );//渐变色,渐变透明
	std::string Nimg( short N, std::string path );
	std::string Nimg( short N, std::string path, short xoffset, short yoffset );//图片代替色填充
	std::string mover( short x1, short y1,short x2,short y2,short angle1, short angle2, short radius1, short radius2 );
	std::string mover( short x1, short y1,short x2,short y2,short angle1, short angle2, short radius1, short radius2, int t1, int t2 );//极限运动:angle1开始时旋转角度;angle2结束时旋转角度;radius1开始时旋转半径;radius2结束时旋转半径
	std::string moves3( short x1, short y1, short x2, short y2, short x3, short y3 );
	std::string moves3( short x1, short y1, short x2, short y2, short x3, short y3, int t1, int t2 );
	std::string moves4( short x1, short y1, short x2, short y2, short x3, short y3, short x4, short y4 );
	std::string moves4( short x1, short y1, short x2, short y2, short x3, short y3, short x4, short y4, int t1, int t2 );//带提示点的曲线运动
	std::string jitter( short left, short right, short up, short down, float period );
	std::string jitter( short left, short right, short up, short down, float period, short seed );//抖动
	std::string movevc( short x1, short y1 );
	std::string movevc( short x1, short y1, short x2, short y2 );
	std::string movevc( short x1, short y1, short x2, short y2, int t1, int t2 );//移动矢量clip

private:
	std::string DecToHex( short DEC );                             //将0-255的数字转成字符型的hex
	std::string FloatToStr( float Float );                         //将浮点型转换成字符串
	std::string IntToStr( int Int );                               //将整型转成字符串
};
