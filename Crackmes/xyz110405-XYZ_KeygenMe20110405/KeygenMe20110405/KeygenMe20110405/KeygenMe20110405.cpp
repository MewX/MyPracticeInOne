#include "stdafx.h"

/*	算法思路	*/
/*
	1.输入一个长度小于20的数A，检验必须有大写
	2.接收长度小于50的通行码B，检验
	3.取A的长度存到large中,large *= 2, large += 10,
	4.如果large != 50, large -= 7, 
	5.如果第一个字母是大写，large += 2
	6.如果最后一个字母是大写，large += 3
	7.检查B.length( )是否等于large
	8.挑出大写A~P的，存到一个string C中，记录长度LC
	9.开一个变量X = large / C.length( );
   10.判断大写是否在C[ i ] =? B[ i * X ]，否则不通过
   11.其余的字母如果有C.length( ) != A.length( ),其他字母依次排在空位,再有空,依次填xyz
*/

void Announcement( );									  //显示头部说明
void Error( short Switch );								//传值显示错误内容，给错误内容插花，干扰分析
char KEEP( char tempKEEP );								//这里面调用ASCII转换函数，达到插花的目的
short CTS( char tempCTS );								 //char 的ASCII * 2 - 100
short STS( short tempSTS );								//short - 100
char STC( short tempSTC );								 //short + ( 4 * 25 + 10 + 2 * 20 + 5 * 10 )
void Congratulations( );								   //通关标志
char XYZ( short x );

int _tmain(int argc, _TCHAR* argv[])
{
	Announcement( );
	string A, B, C, D;
	char CC[ 51 ]/*, DD[ 51 ]*/;
	short large = 0, S = -250;
	while( 1 ) {
		S = 4560;/*should be 11808*/
		while ( 1 ) {
			short K = -250;
			Error( 8394 );
			cin >> A;
			K = 4650;
			if ( 0 < A.length( ) && A.length( ) < 21 ) {
				K = 456;
				for ( short i = 0; i < A.length( ); i ++ ) {
					if ( 64 < ( short )A[ i ] && ( short )A[ i ] < 91 ) {//检验大写
						K = 4560;
					}
					else {
						Error( 133 );//return NULL
					}
				}
			}
			if ( 0 < A.length( ) && A.length( ) < 21 && 4560 == K ) break;
			else Error( 1 );//return NULL
			Error( 43 );//return NULL
			Error( 53 );//return NULL
			Error( 23 );//return NULL
			Error( 13 );//return NULL
			Error( 133 );//return NULL
			Error( 233 );//return NULL
			Error( 244 );//return NULL
			Error( 9245 );//TRUE****
		}
		S = 250;//cheat
		while ( 1 ) {
			Error( 8237 );
			cin >> B;
			if ( 0 < B.length( ) && B.length( ) < 51 ) break;
			else Error( 1 );//return NULL
			Error( 43 );//return NULL
			Error( 53 );//return NULL
			Error( 23 );//return NULL
			Error( 13 );//return NULL
			Error( 133 );//return NULL
			Error( 233 );//return NULL
			Error( 244 );//return NULL
			Error( 9245 );//TRUE****
		}
		//XiauanzhongxyzYxyzxyzxyzxyzx（还有角码超出范围的问题）
		large = A.length( );
		S = 12525;//cheat
		if ( A.length( ) > B.length( ) ) {
			Error( 2354 );
			continue;
		}
		else if ( A.length( ) == B.length( ) ) {
			Error( 2354 );
			continue;
		}
		else {
			Error( 2355 );//Cheat
		}
		S = 11111;//cheat
		/////////////////////////////////////////////insert/////////////////////////////////////////
		large *= 2;
		large += 10;//3.
		if ( large != 50 ) large -= 7;//4.
		if ( 64 < ( short )A[ 0 ] && ( short )A[ 0 ] < 91 ) large += 2;//5.
		if ( 64 < ( short )A[ A.length( ) - 1 ] && ( short )A[ A.length( ) - 1 ] < 91 ) large += 3;//6.
		if ( B.length( ) != large ) {
			Error( 2354 );
			continue;
		}//7
		short Ct = 0;
		for ( short i = 0; i < A.length( ); i ++ ) {
			if ( 64 < ( short )A[ i ] && ( short )A[ i ] < 91 ) {
				CC[ Ct ] = A[ i ];
				Ct ++;
			}
		}//8.
		CC[ Ct ] = '\0';
		C = CC;
		short X = large / C.length( );//9.
		/*short F = -250;
		for ( short i = 0; i < C.length( ); i ++ ) {
			if ( C[ i ] == B[ i * X ] ) continue;
			else F = 0;
		}
		if ( F == 0 ) Error( 2354 );*///10.
		short F = 0, J = 0, M = 1;//F索引D，J索引C，M索引"xyz" 
		S = 11808;//TRUE!!!!!!
		for ( short i = 0; i < large; i ++ ) {
			if ( i == F * X  && F < C.length( ) ) {
				//DD[ F * X ] = C[ F ];
				if ( B[ F * X ] == C[ F ] ) {}
				else {
					Error( 2354 );
					S = 999;
					break;
				}
				F ++;
			}
			else {
				while ( J < A.length( ) ) {//大写
					if ( 64 < ( short )A[ J ] && ( short )A[ J ] < 91 ) J ++;
					else break;
				}
				if ( J >=  A.length( ) ) {
					//DD[ i ] = XYZ( M % 3 );
					if ( B[ i ] == XYZ( M % 3 ) ) {}
					else {
						Error( 2354 );
						S = 99;
						break;
					}
					M ++;
				}
				else {
					//DD[ i ] = A[ J ];
					if ( B[ i ] == A[ J ] ) {}
					else {
						Error( 2354 );
						S = 9;
						break;
					}
					J ++;
				}
			}
		}
		//DD[ large ] = '\0';
		//D = DD;
		if( 11808 == S ) break;
	}

	/*Just Cheat You!~*/
	if ( S > 10000 ) {
		if ( S < 20000 ) {
			if ( S != 2500 && S != 5200 ) {
				if ( S == 11808 ) Congratulations( );//THE ONLY
				else Error( 12523 );
			}
			else {
				if ( S == 11808 ) Error( 12523 );
				else Error( 12523 );
			}
		}
		else {
			if ( S != 2500 && S != 5200 ) {
				if ( S == 11808 ) Error( 12523 );
				else Error( 12523 );
			}
			else {
				if ( S == 11808 ) Error( 12523 );
				else Error( 12523 );
			}
		}
	}
	else {
		if ( S < 20000 ) {
			if ( S != 2500 && S != 5200 ) {
				if ( S == 11808 ) Error( 12523 );
				else Error( 12523 );
			}
			else {
				if ( S == 11808 ) Error( 12523 );
				else Error( 12523 );
			}
		}
		else {
			if ( S != 2500 && S != 5200 ) {
				if ( S == 11808 ) Error( 12523 );
				else Error( 12523 );
			}
			else {
				if ( S == 11808 ) Error( 12523 );
				else Error( 12523 );
			}
		}
	}

	system( "PAUSE" );
	return 0;
}

void Announcement( )
{
	for( short i = 0; i < 80; i ++ ) printf( "%c", '*' );
	printf( "%s", "\nXYZ_KeygenMe 20110405\n\nThanks for trying it! And you can ask me for help!~\nIt's my second time to write KeygenMe!~\n( I'm a Chinese, so there must have some mistakes in my words. )\n" );
	printf( "%s", "\nI think the most difficult part when you crack me is looking for string.\nIn fact, I also want to know how to crack it." );
	printf( "%s", "\nThus if you crack me successfully. You will be able to see my email address.\n" );
	printf( "%s", "Please and please teach me how you crack and keygen me. Thanks and Good Luck!~\n\n" );
	for( short i = 0; i < 80; i ++ ) printf( "%c", '*' );
	printf( "%c", '\n' );
	printf( "%s", "P.S. There must be at least a capitalization in Name.\n\n" );
}

void Error( short Switch )
{
	/*8394  - "Please enter the Name( less than 20 ): "*/
	/*9245  - "Illegimate string! Please ReType!\n\n"*/
	/*8237  - "Please enter the Password( less than 50 ): "*/
	/*2354  - "Error! Try Again Please!~\n\n"*/
	/*12523 - "!- Wrong -!\n\n"*/
	/*29432 - ""*/
	/*482   - ""*/
	if ( Switch == 8394 ) {
		printf( "%c", KEEP( 'P') );
		printf( "%c", KEEP( 'l') );
		printf( "%c", KEEP( 'e') );
		printf( "%c", KEEP( 'a') );
		printf( "%c", KEEP( 's') );
		printf( "%c", KEEP( 'e') );
		printf( "%c", KEEP( ' ') );
		printf( "%c", KEEP( 'e') );
		printf( "%c", KEEP( 'n') );
		printf( "%c", KEEP( 't') );
		printf( "%c", KEEP( 'e') );
		printf( "%c", KEEP( 'r') );
		printf( "%c", KEEP( ' ') );
		printf( "%c", KEEP( 't') );
		printf( "%c", KEEP( 'h') );
		printf( "%c", KEEP( 'e') );
		printf( "%c", KEEP( ' ') );
		printf( "%c", KEEP( 'N') );
		printf( "%c", KEEP( 'a') );
		printf( "%c", KEEP( 'm') );
		printf( "%c", KEEP( 'e') );
		printf( "%c", KEEP( '(') );
		printf( "%c", KEEP( ' ') );
		printf( "%c", KEEP( 'l') );
		printf( "%c", KEEP( 'e') );
		printf( "%c", KEEP( 's') );
		printf( "%c", KEEP( 's') );
		printf( "%c", KEEP( ' ') );
		printf( "%c", KEEP( 't') );
		printf( "%c", KEEP( 'h') );
		printf( "%c", KEEP( 'a') );
		printf( "%c", KEEP( 'n') );
		printf( "%c", KEEP( ' ') );
		printf( "%c", KEEP( '2') );
		printf( "%c", KEEP( '0') );
		printf( "%c", KEEP( ' ') );
		printf( "%c", KEEP( ')') );
		printf( "%c", KEEP( ':') );
	}
	else if ( Switch == 9245 ) {
		printf( "%c", KEEP( 'I') );
		printf( "%c", KEEP( 'l') );
		printf( "%c", KEEP( 'l') );
		printf( "%c", KEEP( 'e') );
		printf( "%c", KEEP( 'g') );
		printf( "%c", KEEP( 'i') );
		printf( "%c", KEEP( 'm') );
		printf( "%c", KEEP( 'a') );
		printf( "%c", KEEP( 't') );
		printf( "%c", KEEP( 'e') );
		printf( "%c", KEEP( ' ') );
		printf( "%c", KEEP( 's') );
		printf( "%c", KEEP( 't') );
		printf( "%c", KEEP( 'r') );
		printf( "%c", KEEP( 'i') );
		printf( "%c", KEEP( 'n') );
		printf( "%c", KEEP( 'g') );
		printf( "%c", KEEP( '!') );
		printf( "%c", KEEP( ' ') );
		printf( "%c", KEEP( 'P') );
		printf( "%c", KEEP( 'l') );
		printf( "%c", KEEP( 'e') );
		printf( "%c", KEEP( 'a') );
		printf( "%c", KEEP( 's') );
		printf( "%c", KEEP( 'e') );
		printf( "%c", KEEP( ' ') );
		printf( "%c", KEEP( 'R') );
		printf( "%c", KEEP( 'e') );
		printf( "%c", KEEP( 'T') );
		printf( "%c", KEEP( 'y') );
		printf( "%c", KEEP( 'p') );
		printf( "%c", KEEP( 'e') );
		printf( "%c", KEEP( '!') );
		printf( "%s", "\n\n" );
	}
	else if ( Switch == 8237 ) {
		
		printf( "%c", KEEP( 'P') );
		printf( "%c", KEEP( 'l') );
		printf( "%c", KEEP( 'e') );
		printf( "%c", KEEP( 'a') );
		printf( "%c", KEEP( 's') );
		printf( "%c", KEEP( 'e') );
		printf( "%c", KEEP( ' ') );
		printf( "%c", KEEP( 'e') );
		printf( "%c", KEEP( 'n') );
		printf( "%c", KEEP( 't') );
		printf( "%c", KEEP( 'e') );
		printf( "%c", KEEP( 'r') );
		printf( "%c", KEEP( ' ') );
		printf( "%c", KEEP( 't') );
		printf( "%c", KEEP( 'h') );
		printf( "%c", KEEP( 'e') );
		printf( "%c", KEEP( ' ') );
		printf( "%c", KEEP( 'P') );
		printf( "%c", KEEP( 'a') );
		printf( "%c", KEEP( 's') );
		printf( "%c", KEEP( 's') );
		printf( "%c", KEEP( 'w') );
		printf( "%c", KEEP( 'o') );
		printf( "%c", KEEP( 'r') );
		printf( "%c", KEEP( 'd') );
		printf( "%c", KEEP( '(') );
		printf( "%c", KEEP( ' ') );
		printf( "%c", KEEP( 'l') );
		printf( "%c", KEEP( 'e') );
		printf( "%c", KEEP( 's') );
		printf( "%c", KEEP( 's') );
		printf( "%c", KEEP( ' ') );
		printf( "%c", KEEP( 't') );
		printf( "%c", KEEP( 'h') );
		printf( "%c", KEEP( 'a') );
		printf( "%c", KEEP( 'n') );
		printf( "%c", KEEP( ' ') );
		printf( "%c", KEEP( '5') );
		printf( "%c", KEEP( '0') );
		printf( "%c", KEEP( ' ') );
		printf( "%c", KEEP( ')') );
		printf( "%c", KEEP( ':') );
	}
	else if ( Switch == 2354 ) {
		printf( "%c", KEEP( 'E') );
		printf( "%c", KEEP( 'r') );
		printf( "%c", KEEP( 'r') );
		printf( "%c", KEEP( 'o') );
		printf( "%c", KEEP( 'r') );
		printf( "%c", KEEP( '!') );
		printf( "%c", KEEP( ' ') );
		printf( "%c", KEEP( 'T') );
		printf( "%c", KEEP( 'r') );
		printf( "%c", KEEP( 'y') );
		printf( "%c", KEEP( ' ') );
		printf( "%c", KEEP( 'A') );
		printf( "%c", KEEP( 'g') );
		printf( "%c", KEEP( 'a') );
		printf( "%c", KEEP( 'i') );
		printf( "%c", KEEP( 'n') );
		printf( "%c", KEEP( ' ') );
		printf( "%c", KEEP( 'P') );
		printf( "%c", KEEP( 'l') );
		printf( "%c", KEEP( 'e') );
		printf( "%c", KEEP( 'a') );
		printf( "%c", KEEP( 's') );
		printf( "%c", KEEP( 'e') );
		printf( "%c", KEEP( '!') );
		printf( "%c", KEEP( '~') );
		printf( "%s", "\n\n" );
	}
	else if ( Switch == 12523 ) {
		printf( "%c", KEEP( '!') );
		printf( "%c", KEEP( '-') );
		printf( "%c", KEEP( ' ') );
		printf( "%c", KEEP( 'W') );
		printf( "%c", KEEP( 'r') );
		printf( "%c", KEEP( 'o') );
		printf( "%c", KEEP( 'n') );
		printf( "%c", KEEP( 'g') );
		printf( "%c", KEEP( ' ') );
		printf( "%c", KEEP( '-') );
		printf( "%c", KEEP( '!') );
		printf( "%s", "\n\n" );
	}
	else if ( Switch == 29432 ) {
	}
	else if ( Switch == 482 ) {
	}
	else return;

}

char KEEP( char tempKEEP )								 //这里面调用ASCII转换函数，达到插花的目的
{
	return STC( STS( CTS( tempKEEP ) ) );
}
short CTS( char tempCTS )								  //char 的ASCII * 2 - 100
{
	short temp;
	temp = ( short ) tempCTS;
	temp *= 2;
	temp -= 100;
	return temp;
}
short STS( short tempSTS )								 //short - 100
{
	return tempSTS - 100;
}
char STC( short tempSTC )								  //(short + ( 4 * 25 + 10 - 35 + 2 * 20 + 17 * 5 ))/2
{
	tempSTC += 4 * 25;
	tempSTC += 10;
	tempSTC -= 35;
	tempSTC += 2 * 20;
	tempSTC += 17;
	tempSTC += 17;
	tempSTC += 17;
	tempSTC += 17;
	tempSTC += 17;
	tempSTC /= 2;
	return ( char )tempSTC;
}


void Congratulations( )
{
	printf( "%c", '\n' );
	for( short i = 0; i < 80; i ++ ) printf( "%c", '*' );
	printf( "%c", KEEP( 'Y' ) );
	printf( "%c", KEEP( 'o' ) );
	printf( "%c", KEEP( 'u' ) );
	printf( "%c", KEEP( '\'' ) );
	printf( "%c", KEEP( 'r' ) );
	printf( "%c", KEEP( 'e' ) );
	printf( "%c", KEEP( ' ' ) );
	printf( "%c", KEEP( 's' ) );
	printf( "%c", KEEP( 'o' ) );
	printf( "%c", KEEP( ' ' ) );
	printf( "%c", KEEP( 'C' ) );
	printf( "%c", KEEP( 'O' ) );
	printf( "%c", KEEP( 'O' ) );
	printf( "%c", KEEP( 'L' ) );
	printf( "%c", KEEP( '!' ) );
	printf( "%c", KEEP( '~' ) );
	printf( "%c", KEEP( ' ' ) );
	printf( "%c", KEEP( 'W' ) );
	printf( "%c", KEEP( 'e' ) );
	printf( "%c", KEEP( 'l' ) );
	printf( "%c", KEEP( 'l' ) );
	printf( "%c", KEEP( '-' ) );
	printf( "%c", KEEP( 'd' ) );
	printf( "%c", KEEP( 'o' ) );
	printf( "%c", KEEP( 'n' ) );
	printf( "%c", KEEP( 'e' ) );
	printf( "%c", KEEP( '!' ) );
	printf( "%c", '\n' );
	printf( "%c", KEEP( 'M' ) );
	printf( "%c", KEEP( 'y' ) );
	printf( "%c", KEEP( ' ' ) );
	printf( "%c", KEEP( 'e' ) );
	printf( "%c", KEEP( 'm' ) );
	printf( "%c", KEEP( 'a' ) );
	printf( "%c", KEEP( 'i' ) );
	printf( "%c", KEEP( 'l' ) );
	printf( "%c", KEEP( ' ' ) );
	printf( "%c", KEEP( 'a' ) );
	printf( "%c", KEEP( 'd' ) );
	printf( "%c", KEEP( 'd' ) );
	printf( "%c", KEEP( 'r' ) );
	printf( "%c", KEEP( 'e' ) );
	printf( "%c", KEEP( 's' ) );
	printf( "%c", KEEP( 's' ) );
	printf( "%c", KEEP( ' ' ) );
	printf( "%c", KEEP( 'i' ) );
	printf( "%c", KEEP( 's' ) );
	printf( "%c", KEEP( ' ' ) );
	printf( "%c", KEEP( 'x' ) );
	printf( "%c", KEEP( 'i' ) );
	printf( "%c", KEEP( 'a' ) );
	printf( "%c", KEEP( 'y' ) );
	printf( "%c", KEEP( 'u' ) );
	printf( "%c", KEEP( 'a' ) );
	printf( "%c", KEEP( 'n' ) );
	printf( "%c", KEEP( 'z' ) );
	printf( "%c", KEEP( 'h' ) );
	printf( "%c", KEEP( 'o' ) );
	printf( "%c", KEEP( 'n' ) );
	printf( "%c", KEEP( 'g' ) );
	printf( "%c", KEEP( '@' ) );
	printf( "%c", KEEP( 'g' ) );
	printf( "%c", KEEP( 'm' ) );
	printf( "%c", KEEP( 'a' ) );
	printf( "%c", KEEP( 'i' ) );
	printf( "%c", KEEP( 'l' ) );
	printf( "%c", KEEP( '.' ) );
	printf( "%c", KEEP( 'c' ) );
	printf( "%c", KEEP( 'o' ) );
	printf( "%c", KEEP( 'm' ) );
	printf( "%c", '\n' );
	printf( "%c", KEEP( 'M' ) );
	printf( "%c", KEEP( 'y' ) );
	printf( "%c", KEEP( ' ' ) );
	printf( "%c", KEEP( 'n' ) );
	printf( "%c", KEEP( 'a' ) );
	printf( "%c", KEEP( 'm' ) );
	printf( "%c", KEEP( 'e' ) );
	printf( "%c", KEEP( ' ' ) );
	printf( "%c", KEEP( 'i' ) );
	printf( "%c", KEEP( 's' ) );
	printf( "%c", KEEP( ' ' ) );
	printf( "%c", KEEP( 'X' ) );
	printf( "%c", KEEP( 'i' ) );
	printf( "%c", KEEP( 'a' ) );
	printf( "%c", KEEP( ' ' ) );
	printf( "%c", KEEP( 'Y' ) );
	printf( "%c", KEEP( 'u' ) );
	printf( "%c", KEEP( 'a' ) );
	printf( "%c", KEEP( 'n' ) );
	printf( "%c", KEEP( 'z' ) );
	printf( "%c", KEEP( 'h' ) );
	printf( "%c", KEEP( 'o' ) );
	printf( "%c", KEEP( 'n' ) );
	printf( "%c", KEEP( 'g' ) );
	printf( "%c", '\n' );
	for( short i = 0; i < 80; i ++ ) printf( "%c", '*' );
	printf( "%c", '\n' );
}

char XYZ( short x )
{
	if ( x == 1 ) return 'x';
	else if( x == 2 ) return 'y';
	else if( x == 0 ) return 'z';
	else return '0';
}
