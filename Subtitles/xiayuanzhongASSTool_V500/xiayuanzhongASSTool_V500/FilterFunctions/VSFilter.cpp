#include "StdAfx.h"
#include "VSFilter.h"

/*VSFilter*/
std::string VSFilter::LineBegin( short layer, std::string ST, std::string ET, std::string Font )
{
	return "Dialogue:" + IntToStr( layer ) + "," + ST + "," + ET + "," + Font + ",NTP,0000,0000,0000,,";
}
std::string VSFilter::StyleBegin( )
{
	return "{";
}
std::string VSFilter::StyleEnd( )
{
	return "}";
}
std::string VSFilter::LineEnd( std::string word )
{
	return word + "\n";
}
std::string VSFilter::LineEnd( )
{
	return "\n";
}
std::string VSFilter::bord( short broad )
{
	return "\\bord" + IntToStr( broad );
}//描边，参数为宽度
std::string VSFilter::xshad( short shadow )
{
	return "\\xshad" + IntToStr( shadow );
}//在x方向加阴影
std::string VSFilter::yshad( short shadow )
{
	return "\\yshad" + IntToStr( shadow );
}//在y方向加阴影
std::string VSFilter::shad( short shadow )
{
	return "\\shad" + IntToStr( shadow );
}//斜二侧画法的阴影
std::string VSFilter::be( short BE )
{
	return "\\be" + IntToStr( BE );
}//1-柔化边缘，0-取消
std::string VSFilter::blur( short B )
{
	return "\\blur" + IntToStr( B );
}//柔化边缘，参数是柔滑度
std::string VSFilter::fn( std::string Fname )
{
	return "\\fn" + Fname;
}//载入已定义的字体
std::string VSFilter::fs( short size )
{
	return "\\fs" + IntToStr( size );
}//换字号
std::string VSFilter::fscx( unsigned short X )
{
	return "\\fscx" + IntToStr( X );
}//x方向拉伸
std::string VSFilter::fscy( unsigned short Y )
{
	return "\\fscy" + IntToStr( Y );
}//y方向拉伸
std::string VSFilter::fsp( short P )
{
	return "\\fsp" + IntToStr( P );
}//字间距，P的单位是像素
std::string VSFilter::frx( short angle )
{
	return "\\frx" + IntToStr( angle );
}//x轴旋转，水平方向（大于0，自前向后）
std::string VSFilter::fry( short angle )
{
	return "\\fry" + IntToStr( angle );
}//y轴旋转，竖直方向（大于0，自西向东）
std::string VSFilter::frz( short angle )
{
	return "\\frz" + IntToStr( angle );
}//z轴旋转，里外方向（大于0，逆时针）
std::string VSFilter::Nc( short N, char color[7] )
{
	return "\\" + IntToStr( N ) + "c&H" + color + "&";
}//N-载入颜色的位置1~4，color-BBGGRR格式的颜色
std::string VSFilter::Na( short N, short alpha )
{
	return "\\" + IntToStr( N ) + "a&H" + DecToHex( alpha ) + "&";
}//N-载入透明度的位置1~4，color-FF格式的透明度
std::string VSFilter::an( short KB )
{
	return "\\an" + IntToStr( KB );
}//按数字键盘定位1-9
std::string VSFilter::tBegin( )
{
	return "\\t(";
}//只输出"t("的函数
std::string VSFilter::tBegin( float A )
{
	return "\\t(" + FloatToStr( A ) + ",";
}//输出"t(A,",A-accel(0~1-由快变慢，>1-由慢变快)
std::string VSFilter::tBegin( int t1, int t2 )
{
	return "\\t(" + IntToStr( t1 ) + "," + IntToStr( t2 ) + ",";
}//输出"t(t1,t2,"
std::string VSFilter::tBegin( int t1, int t2, float A )
{
	return "\\t(" + IntToStr( t1 ) + "," + IntToStr( t2 ) + "," + FloatToStr( A ) + ",";
}//输出"t(t1,t2,A,"
std::string VSFilter::tEnd( )
{
	return ")";
}//输出")"
std::string VSFilter::move( short x1, short y1, short x2,short y2 )
{
	return "\\move(" + IntToStr( x1 ) + "," + IntToStr( y1 ) + "," + IntToStr( x2 ) + "," + IntToStr( y2 ) + ")";
}
std::string VSFilter::move( short x1, short y1, short x2,short y2, int t1, int t2 )
{
	return "\\move(" + IntToStr( x1 ) + "," + IntToStr( y1 ) + "," + IntToStr( x2 ) + "," + IntToStr( y2 ) + "," + IntToStr( t1 ) + "," + IntToStr( t2 ) + ")";
}
std::string VSFilter::pos( short x, short y )
{
	return "\\pos(" + IntToStr( x ) + "," + IntToStr( y ) + ")";
}
std::string VSFilter::org( short x, short y )
{
	return "\\org(" + IntToStr( x ) + "," + IntToStr( y ) + ")";
}//移动对象的原点
std::string VSFilter::fade( short a1, short a2, short a3, int t1, int t2, int t3 )
{
	return "\\fade(" + DecToHex( a1 ) + "," + DecToHex( a2 ) + "," + DecToHex( a3 ) + "," + IntToStr( t1 ) + "," + IntToStr( t2 ) + "," + IntToStr( t3 ) + ")";
}
std::string VSFilter::fad( int t1, int t2 )
{
	return "\\fad(" + IntToStr( t1 ) + "," + IntToStr( t2 ) + ")";
}
std::string VSFilter::clip( short x1, short y1, short x2, short y2 )
{
	return "\\clip(" + IntToStr( x1 ) + "," + IntToStr( y1 ) + "," + IntToStr( x2 ) + "," + IntToStr( y2 ) + ")";
}
std::string VSFilter::clip( short P, std::string draw )
{
	return "\\clip(" + IntToStr( P ) + "," + draw + ")";
}//P是比例，draw是绘图命令
std::string VSFilter::pN( short N, std::string draw )
{
	return "{\\p" + IntToStr( N ) + "}" + draw + "{\\p0}";
}//N-比例，draw-绘图命令

/*VSFilterMod*/
std::string VSFilter::fsc( unsigned short scale )
{
	return "\\fsc" +IntToStr( scale );
}//字体放大%
std::string VSFilter::fsvp( short leading )
{
	return "\\fsvp" + IntToStr( leading );
}//纵向偏移
std::string VSFilter::frs( short angle )
{
	return "\\frs" + IntToStr( angle );
}//基线倾斜
std::string VSFilter::z( short arg )
{
	return "\\z" + IntToStr( arg );
}//z坐标
std::string VSFilter::distort( float u1, float v1, float u2, float v2, float u3, float v3 )
{
	return "\\distort(" + FloatToStr( u1 ) + "," + FloatToStr( v1 ) + "," + FloatToStr( u2 ) + "," + FloatToStr( v2 ) + "," + FloatToStr( u3 ) + "," + FloatToStr( v3 ) + ")";
}//扭曲文字,通过移动角上坐标具体确定相对的坐标
std::string VSFilter::rnd( short arg )
{
	return "\\rnd" + IntToStr( arg );
}
std::string VSFilter::rndx( short arg )
{
	return "\\rndx" + IntToStr( arg );
}
std::string VSFilter::rndy( short arg )
{
	return "\\rndy" + IntToStr( arg );
}
std::string VSFilter::rndz( short arg )
{
	return "\\rndz" + IntToStr( arg );
}//边界变形
std::string VSFilter::Nvc( short N, char LT[7], char RT[7], char LB[7], char RB[7] )
{
	return "\\" + IntToStr( N ) + "vc(&H" + LT + "&,&H" + RT + "&,&H" + LB + "&,&H" + RB + "&)";
}
std::string VSFilter::Nva( short N, short LT, short RT, short LB, short RB )
{
	return "\\" + IntToStr( N ) + "va(&H" + DecToHex( LT ) + "&,&H" + DecToHex( RT ) + "&,&H" + DecToHex( LB ) + "&,&H" + DecToHex( RB ) + "&)";
}//渐变色,渐变透明
std::string VSFilter::Nimg(short N, std::string path)
{
	return "\\" + IntToStr( N ) + "img(" + path + ")";
}
std::string VSFilter::Nimg(short N, std::string path, short xoffset, short yoffset)
{
	return "\\" + IntToStr( N ) + "img(" + path + "," + IntToStr( xoffset ) + "," + IntToStr( yoffset ) + ")";
}//图片代替色填充
std::string VSFilter::mover( short x1, short y1,short x2,short y2,short angle1, short angle2, short radius1, short radius2 )
{
	return "\\mover(" + IntToStr( x1 ) + "," + IntToStr( y1 ) + "," + IntToStr( x2 ) + "," + IntToStr( y2 ) + "," + IntToStr( angle1 ) + "," + IntToStr( angle2 ) + "," + IntToStr( radius1 ) + "," + IntToStr( radius2 ) + ")";
}
std::string VSFilter::mover( short x1, short y1,short x2,short y2,short angle1, short angle2, short radius1, short radius2, int t1, int t2 )
{
	return "\\mover(" + IntToStr( x1 ) + "," + IntToStr( y1 ) + "," + IntToStr( x2 ) + "," + IntToStr( y2 ) + "," + IntToStr( angle1 ) + "," + IntToStr( angle2 ) + "," + IntToStr( radius1 ) + "," + IntToStr( radius2 ) + "," + IntToStr( t1 ) + "," + IntToStr( t2 ) + ")";
}//极限运动:angle1开始时旋转角度;angle2结束时旋转角度;radius1开始时旋转半径;radius2结束时旋转半径
std::string VSFilter::moves3( short x1, short y1, short x2, short y2, short x3, short y3 )
{
	return "\\moves3(" + IntToStr( x1 ) + "," + IntToStr( y1 ) + "," + IntToStr( x2 ) + "," + IntToStr( y2 ) + "," + IntToStr( x3 ) + "," + IntToStr( y3 ) + ")";
}
std::string VSFilter::moves3( short x1, short y1, short x2, short y2, short x3, short y3, int t1, int t2 )
{
	return "\\moves3(" + IntToStr( x1 ) + "," + IntToStr( y1 ) + "," + IntToStr( x2 ) + "," + IntToStr( y2 ) + "," + IntToStr( x3 ) + "," + IntToStr( y3 ) + "," + IntToStr( t1 ) + "," + IntToStr( t2 ) + ")";
}
std::string VSFilter::moves4( short x1, short y1, short x2, short y2, short x3, short y3, short x4, short y4 )
{
	return "\\moves4(" + IntToStr( x1 ) + "," + IntToStr( y1 ) + "," + IntToStr( x2 ) + "," + IntToStr( y2 ) + "," + IntToStr( x3 ) + "," + IntToStr( y3 ) + "," + IntToStr( x4 ) + "," + IntToStr( y4 ) + ")";
}
std::string VSFilter::moves4( short x1, short y1, short x2, short y2, short x3, short y3, short x4, short y4, int t1, int t2 )
{
	return "\\moves4(" + IntToStr( x1 ) + "," + IntToStr( y1 ) + "," + IntToStr( x2 ) + "," + IntToStr( y2 ) + "," + IntToStr( x3 ) + "," + IntToStr( y3 ) + "," + IntToStr( x4 ) + "," + IntToStr( y4 ) + "," + IntToStr( t1 ) + "," + IntToStr( t2 ) + ")";
}//带提示点的曲线运动
std::string VSFilter::jitter( short left, short right, short up, short down, float period )
{
	return "\\jitter(" + IntToStr( left ) + "," + IntToStr( right ) + "," + IntToStr( up ) + "," + IntToStr( down ) + "," + FloatToStr( period ) + ")";
}
std::string VSFilter::jitter( short left, short right, short up, short down, float period, short seed )
{
	return "\\jitter(" + IntToStr( left ) + "," + IntToStr( right ) + "," + IntToStr( up ) + "," + IntToStr( down ) + "," + FloatToStr( period ) + "," + IntToStr( seed ) + ")";
}//抖动
std::string VSFilter::movevc( short x1, short y1 )
{
	return "\\movevc(" + IntToStr( x1 ) + "," + IntToStr( y1 ) + ")";
}
std::string VSFilter::movevc( short x1, short y1, short x2, short y2 )
{
	return "\\movevc(" + IntToStr( x1 ) + "," + IntToStr( y1 ) + "," + IntToStr( x2 ) + "," + IntToStr( y2 ) + ")";
}
std::string VSFilter::movevc( short x1, short y1, short x2, short y2, int t1, int t2 )
{
	return "\\movevc(" + IntToStr( x1 ) + "," + IntToStr( y1 ) + "," + IntToStr( x2 ) + "," + IntToStr( y2 ) + "," + IntToStr( t1 ) + "," + IntToStr( t2 ) + ")";
}


std::string VSFilter::DecToHex( short DEC )
{
	std::string A;
	if( DEC < 16 ) {
		switch( DEC ) {
			case 10:   A += "A"; break;
			case 11:   A += "B"; break;
			case 12:   A += "C"; break;
			case 13:   A += "D"; break;
			case 14:   A += "E"; break;
			case 15:   A += "F"; break;
			default:   char TE[ 10 ]; memset( TE, 10, '\0'); _itoa_s( DEC, TE, 10 ); A += TE;
		}
	}
	else {
		A += DecToHex( DEC / 16 );
		switch( DEC % 16 ) {
			case 10:   A += "A"; break;
			case 11:   A += "B"; break;
			case 12:   A += "C"; break;
			case 13:   A += "D"; break;
			case 14:   A += "E"; break;
			case 15:   A += "F"; break;
			default:   char TE[ 10 ]; memset( TE,10,'\0'); _itoa_s( DEC % 16, TE, 10 ); A += TE;
		}
	}
	return A;
}
std::string VSFilter::FloatToStr( float Float )
{
	const int KEEP = 3;
	std::string TEMP1, TEMP2;
	float t = Float * (int)pow( (double)10, KEEP );
	int tt = ( int )t;
	int Length = 1;
	while ( 1 ) {
		if ( tt / (int)pow( (double)10, Length ) > 0 ) Length ++;
		else break;
	}
	for ( int i = Length; i > 0; i -- ) {
		TEMP1 += IntToStr( tt / (int)pow( (double)10, i - 1 ) );
		tt -= ( tt / (int)pow( (double)10, i - 1 ) ) * (int)pow( (double)10, i - 1 );
	}
	bool PointOrNot = false;
	int L = TEMP1.length( );
	for ( int i = 0; i < L; i ++ ) {
		if ( i == L - KEEP ) TEMP2 += ".";
		TEMP2 += TEMP1[ i ];
	}
	return TEMP2;
}
std::string VSFilter::IntToStr( int Int )
{
	char TE[ 10 ];
	memset( TE, 10, '\0');
	_itoa_s( Int, TE, 10 );
	std::string _TE = TE;
	return _TE;
}