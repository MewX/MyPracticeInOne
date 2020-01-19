#include <cstdlib>
#include <iostream>
#include <string>

using namespace std;
/*    算法思路    */
/*
    1.输入一个长度小于20的数A，检验必须有大写
    2.接收长度小于50的通行码B，检验
    3.取A的长度存到large中,large *= 2, large += 10,
	4.如果large != 50, large -= 7, 
	5.如果第一个字母是大写，large += 2
	6.如果最后一个字母是大写，large += 3
	7.检查B.length( )是否等于large
    8.挑出大写存到一个string C中，记录长度LC
	9.开一个变量X = large / C.length( );
   10.判断大写是否在C[ i ] =? B[ i * X ]，否则不通过
   11.其余的字母如果有C.length( ) != A.length( ),其他字母依次排在空位,再有空,依次填xyz
*/

char XYZ( short x )
{
	if ( x == 1 ) return 'x';
	else if ( x == 2 ) return 'y';
	else if ( x == 0 ) return 'z';
	else return '0';
}
int main(int argc, char *argv[])
{
    short large;
    string A, B, C, D;
    char CC[ 51 ], DD[ 51 ];
    cout << "Input: " ;
    cin >> A;
    //1.
    //cin >> B;
    //2.
    cout <<"2"<<endl;
    large = A.length( );
    cout << A.length( )<<endl;
    large *= 2;
    large +=10;//3.
    cout <<"3"<<endl;
    if ( large != 50 ) large -= 7;//4.
    cout <<"large = "<<large<<endl;
    cout <<"4"<<endl;
    if ( 64 < ( short )A[ 0 ] && ( short )A[ 0 ] < 91 ) large += 2;//5.
    
    cout <<"large = "<<large<<endl;
    cout <<"5"<<endl;
    if ( 64 < ( short )A[ A.length( ) - 1 ] && ( short )A[ A.length( ) - 1 ] < 91 ) large += 3;//6.
    cout <<"6"<<endl;
    cout <<"large = "<<large<<endl;
    //7
    //string C;
    short Ct = 0;
    for ( short i = 0; i < A.length( ); i ++ ) {
        if ( 64 < ( short )A[ i ] && ( short )A[ i ] < 91 ) {
            CC[ Ct ] = A[ i ];
            cout <<"CC[ "<<Ct<<" ] = "<<CC[Ct]<<endl;
            Ct ++;
        }
    }//8.
    CC[ Ct ] = '\0';
    C = CC;
    cout << "C.length( ) = "<<C.length( )<<endl;
    cout <<"8"<<endl;
    short X = large / C.length( );//9.
    /*short F = -250;
    for ( short i = 0; i < C.length( ); i ++ ) {
        if ( C[ i ] == B[ i * X ] ) continue;
        else F = 0;
    }
    if ( F == 0 ) printf( "%s", "Error!" );*///10.
    //string D;
    cout <<"9------X = "<<X<<endl;
    cout <<"large = "<<large<<endl;
    short F = 0, J = 0, M = 1;//F索引D，J索引A，M索引"xyz"
    for ( short i = 0; i < large; i ++ ) {//循环large次
        cout << "i = " << i << "; In ";
        if ( i == F * X && F < C.length( ) ) {//如果是X的倍数
            DD[ F * X ] = C[ F ];
            F ++;
            cout << "1"<< endl;
        }
        else {//不是X的倍数
			while ( J < A.length( ) ) {//大写
				if ( 64 < ( short )A[ J ] && ( short )A[ J ] < 91 ) {
                    J ++;
                    cout << "+";
                }
				else break;
			}
            if ( J >=  A.length( ) ) {//超长
                DD[ i ] = XYZ( M % 3 );
                M ++;
            cout << "2"<< endl;
            }
            else {//非大写的，没越界 
                DD[ i ] = A[ J ];
                J ++;
            cout << "3"<< endl;
            }
        }
    }
    DD[ large ] = '\0';
    cout << strlen(DD)<<endl;
    D = DD;
    /*for ( short i = 0; i < C.length( ); i ++ ) {
        C[ i ] == D[ i * X ];
    }*/
    //char a[50];
    //for( short i = 0; i < D.length( ); i ++ ) a[i] = D[i];
    cout << D<<endl;
    
    system("PAUSE");
    return EXIT_SUCCESS;
}
